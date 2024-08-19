package br.com.fiap.apisphere.auth;

import br.com.fiap.apisphere.user.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Token login (@RequestBody Credentials credentials) {
        var user = userRepository.findByEmail(credentials.email())
            .orElseThrow( () -> new RuntimeException("Invalid Email"));

        if ( !passwordEncoder.matches(credentials.password(), user.getPassword()) )
            throw new RuntimeException("Invalid Password");


        Algorithm algorithm = Algorithm.HMAC256("assinatura");
        var expiresAt = LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.ofHours(-3));
        String token = JWT.create()
                .withSubject(credentials.email())
                .withIssuer("Sphere")
                .withExpiresAt(expiresAt)
                .withClaim("role", "ADMIN")
                .sign(algorithm);

        return new Token(token, credentials.email());
    }
}
