package com.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.dtos.UserDTO;
import com.auth.models.UserModel;
import com.auth.repositories.UserRepository;
import com.auth.util.JwtUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class UserService {

    @Autowired
    private JwtUtil jwt;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    

    public void createNewUser(UserDTO user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(new UserModel(user));
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public UserDTO getBySignInData(String username, String password) {
        UserModel fuser = userRepository.getOne(username);
        if (fuser != null && passwordEncoder.matches(password, fuser.getPassword())) {
            return new UserDTO(fuser.getId(), jwt.generateJwtToken(fuser.getUsername()));
        }
        return null; // Empty user data transfer object 
    }

    public UserDTO getById(Long id) {
        UserModel user = userRepository.getOne(id);
        return new UserDTO(user);
    }

    public UserDTO getByUsername(String username) {
        UserModel user = userRepository.getOne(username);
        return new UserDTO(user);
    }

    public UserDTO getByToken(String token) throws JWTVerificationException {
        UserModel user = userRepository.getOne(jwt.verifyToken(token).getSubject());
        if (user != null) return new UserDTO(user);
        return null;
    }
}   

