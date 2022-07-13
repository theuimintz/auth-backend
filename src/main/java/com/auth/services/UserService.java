package com.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.auth.dtos.ImageDTO;
import com.auth.dtos.UserDTO;
import com.auth.models.UserModel;
import com.auth.repositories.UserRepository;
import com.auth.util.JwtUtil;

@Service
public class UserService {

    @Autowired
    private JwtUtil jwt;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    
    public void setProfileImage(String token, MultipartFile file) {
        try {
            String username = jwt.verifyToken(token).getSubject();
            UserModel user = userRepository.getOne(username);

            if (user == null) return;

            user.setProfileImage(new ImageDTO(file));
            userRepository.save(user);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void createNewUser(UserDTO user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(new UserModel(user));
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public UserDTO getUser(String username, String password) {
        UserModel fuser = userRepository.getOne(username);
        if (fuser != null && passwordEncoder.matches(password, fuser.getPassword())) {
            return new UserDTO(fuser.getId(), jwt.generateJwtToken(fuser.getUsername()));
        }
        return new UserDTO(); // Empty user data transfer object 
    }

    public UserDTO getUser(Long id) {
        UserModel user = userRepository.getOne(id);
        return new UserDTO(user);
    }

    public UserDTO getUser(String username) {
        UserModel user = userRepository.getOne(username);
        return new UserDTO(user);
    }
}

