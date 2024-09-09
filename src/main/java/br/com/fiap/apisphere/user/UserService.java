package br.com.fiap.apisphere.user;

import br.com.fiap.apisphere.user.dto.UserProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User create(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public UserProfileResponse getProfile(String email) {
        return repository.findByEmail(email)
                .map(UserProfileResponse::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    public void updateAvatar(String email, MultipartFile file) {

        // validar o arquivo
        if(file.isEmpty()){
            throw new RuntimeException("Invalid File");
        }

        // salvar arquivo no disco
        try(InputStream is = file.getInputStream()){
            Path destinationDir = Path.of("src/main/resources/static/avatars");
            Path destinationFile = destinationDir
                    .resolve(System.currentTimeMillis() + file.getOriginalFilename() )
                    .normalize()
                    .toAbsolutePath();

            Files.copy(is, destinationFile);

            System.out.println("Arquivo salvo com sucesso");

            var user = repository.findByEmail(email).orElseThrow( () -> new UsernameNotFoundException("User not found"));
            var baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
            var avatarUrl = baseUrl + "/users/avatars/" + destinationFile.getFileName();
            user.setAvatar(avatarUrl);
            repository.save(user);

        }catch (Exception e){
            System.out.println("Erro ao copiar arquivo. " + e.getCause());
        }

    }

    public List<User> findByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }
}