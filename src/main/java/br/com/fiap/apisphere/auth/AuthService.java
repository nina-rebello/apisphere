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
    private final TokenService tokenService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public Token login (Credentials credentials) {
        var user = userRepository.findByEmail(credentials.email())
            .orElseThrow( () -> new RuntimeException("Invalid Email"));

        if ( !passwordEncoder.matches(credentials.password(), user.getPassword()) )
            throw new RuntimeException("Invalid Password");

        return tokenService.createToken(credentials);





    }
}
