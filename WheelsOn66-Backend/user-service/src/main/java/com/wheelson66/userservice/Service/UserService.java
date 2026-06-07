package com.wheelson66.userservice.Service;

import com.wheelson66.userservice.Entity.User;
import com.wheelson66.userservice.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder PasswordEncoder;
    private final JwtService jwtService;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        PasswordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public User registerUser(String email, String rawPassword , String pseudo) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        if(existingUser.isPresent()){
            throw new RuntimeException("Cet email est deja utilise");

        }
        String encodedPassword = PasswordEncoder.encode(rawPassword);
        User newUser = new User(email, encodedPassword, pseudo);
        return userRepository.save(newUser);
    }

    public String loginUser(String email, String rawPassword){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(PasswordEncoder.matches(rawPassword, user.getPassword())){
            return jwtService.generateToken(user.getEmail());

        }else {
            throw new RuntimeException("Invalid username or password");
        }
    }
}
