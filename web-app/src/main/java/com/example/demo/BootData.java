package com.example.demo;

import com.example.demo.models.user.User;
import com.example.demo.repositories.user.UserRepository;
import com.example.demo.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BootData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public BootData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        User admin = new User("admin",
            passwordEncoder.encode("admin"),
            UserRole.ADMIN,
            true,
            true,
            true,
            true);

        User user = new User("user",
                passwordEncoder.encode("user"),
                UserRole.USER,
                true,
                true,
                true,
                true);

        userRepository.save(admin);
        userRepository.save(user);
}
}
