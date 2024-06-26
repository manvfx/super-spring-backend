package com.aryahamrah.app.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthUser registerUser(AuthUser user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already taken. Please try again");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        return userRepository.save(user);
    }

    public AuthUser loginUser(String username, String password) {
        Optional<AuthUser> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            AuthUser user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        throw new IllegalArgumentException("Invalid username or password");
    }

    public AuthUser getProfile(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public AuthUser updateUserProfile(AuthUser user) {
        Optional<AuthUser> existingUserOpt = userRepository.findById(user.getId());
        if (existingUserOpt.isPresent()) {
            AuthUser existingUser = existingUserOpt.get();
            existingUser.setEmail(user.getEmail());
            // other fields to update
            return userRepository.save(existingUser);
        } else {
            throw new RuntimeException("User not found");
        }
    }

}
