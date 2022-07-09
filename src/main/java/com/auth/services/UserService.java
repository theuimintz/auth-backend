package com.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.models.UserModel;
import com.auth.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public void createNewUser(UserModel user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public UserModel verifyUserPassword(String username, String password) {
        UserModel user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword()))
            return user;
        return null;
    }

}
