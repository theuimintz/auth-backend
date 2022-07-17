package com.auth.services;

import java.io.IOException;

import com.auth.dtos.ProfileImageDTO;
import com.auth.dtos.UserDTO;
import com.auth.models.ProfileImageModel;
import com.auth.repositories.ProfileImageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProfileImageService {

    @Autowired
    private ProfileImageRepository profileImageRepository;

    @Autowired
    private UserService userService;
    

    public ProfileImageDTO getProfileImage (Long userId) {
        ProfileImageModel img = profileImageRepository.getByUserId(userId);       
        if (img != null) return new ProfileImageDTO(img);
        return null;
    }

    public void setProfileImage(String token, MultipartFile img) throws IOException {
        UserDTO user = userService.getByToken(token);
        if (user == null) return;

        Long userId = user.getId();
        ProfileImageModel profileImg = profileImageRepository.getByUserId(userId);
        profileImageRepository.save((profileImg == null) ? new ProfileImageModel(userId, img) : profileImg);
    }

       
}
