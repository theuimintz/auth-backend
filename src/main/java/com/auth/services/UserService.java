package com.auth.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.auth.dtos.ProfileImageDTO;
import com.auth.dtos.UserDTO;
import com.auth.models.ProfileImageModel;
import com.auth.models.UserModel;
import com.auth.repositories.ProfileImageRepository;
import com.auth.repositories.UserRepository;
import com.auth.util.JwtUtil;

@Service
public class UserService {

    @Autowired
    private JwtUtil jwt;

    @Autowired
    private ProfileImageRepository profileImageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public void createNewUser(UserModel user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public String verifyUserPassword(String username, String password) {
        UserModel user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword()))
            return jwt.generateJwtToken(user.getUsername());
        return null;
    }

    public void setProfileImage(String authToken, MultipartFile image) throws IOException {
        String username = jwt.verifyToken(authToken).getSubject();
        UserModel user = userRepository.findByUsername(username);
        if (user != null) {
            
            ProfileImageModel foundImg = profileImageRepository.getByUserId(user.getId());

            if (foundImg != null) {
                System.out.println(image.getContentType() + " " + image.getBytes().toString());
                foundImg.setImageBytes(image.getBytes());
                profileImageRepository.save(foundImg);
                return;
            }

            ProfileImageModel img = new ProfileImageModel(user.getId(), image.getBytes(), MediaType.parseMediaType(image.getContentType()));           
            profileImageRepository.save(img);
        }
    }

    public ProfileImageDTO getProfileImage(Long id) throws IOException {
        UserModel user = userRepository.findByUserId(id);

        if (user != null) {
            ProfileImageModel img = profileImageRepository.getByUserId(user.getId());

            if (img != null) {
                return new ProfileImageDTO(img.getId(), img.getUserId(), img.getImageBytes(), img.getImageType());
            }
        }

        return null;
    }

    public UserDTO getById(Long id) {
        UserModel user = userRepository.findByUserId(id);
        ProfileImageModel img = profileImageRepository.getByUserId(id);

        UserDTO userDTO = null;
        if (user != null) {
            userDTO = new UserDTO(user.getId(), new ProfileImageDTO(img.getId(), img.getUserId(), img.getImageBytes(), img.getImageType()), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getTelNumber());
        }

        return userDTO;
    }
}
