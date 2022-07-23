package com.auth.controllers;

import java.io.IOException;

import com.auth.dtos.ProfileImageDTO;
import com.auth.services.ProfileImageService;
import com.auth0.jwt.exceptions.JWTVerificationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping( path = "/api/images/profile")
public class ProfileImageController {
    
    @Autowired
    private ProfileImageService profileImageService;


    @GetMapping ( path = "/all/{id}" )
    public ResponseEntity<ProfileImageDTO> getProfileImageProperties (@PathVariable Long id) {
        ProfileImageDTO img = profileImageService.getProfileImage(id);       

        if (img != null) return ResponseEntity.status(200).body(img);
        return ResponseEntity.status(404).body(null);
    }

    @PostMapping ( path = "/set")
    public ResponseEntity<String> setProfileImage(@RequestHeader String token, @RequestParam MultipartFile file) {
        try {
            profileImageService.setProfileImage(token, file);
            return ResponseEntity.status(200).body(null);
        } 
        catch (IOException ex) { return ResponseEntity.status(403).body(null); } 
        catch (JWTVerificationException ex) { return ResponseEntity.status(401).body(null); }
    }
}

