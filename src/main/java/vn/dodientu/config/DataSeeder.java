package vn.dodientu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.dodientu.model.User;
import vn.dodientu.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByEmail("enderthelord@gmail.com").isEmpty()) {
                User adminUser = new User();
                adminUser.setUsername("admin");
                adminUser.setFullName("John Smith");
                adminUser.setEmail("enderthelord@gmail.com");
                adminUser.setPassword(passwordEncoder.encode("12345"));  // Encode password for security

                userRepository.save(adminUser);
                System.out.println("Admin user seeded");
            } else {
                System.out.println("Admin user already exists");
            }
        };
    }
}
