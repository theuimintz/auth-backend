package com.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.auth.models.ProfileImageModel;
import com.auth.models.UserModel;
import com.auth.services.UserService;


@RestController
@RequestMapping( path = "/api/users" )
public class UserController {

    @Autowired
    private UserService userService;
    

    @PostMapping( path = "/signup" )
    public ResponseEntity<String> singUp(@RequestBody UserModel user) {
        userService.createNewUser(user);
        return ResponseEntity.ok().body("ok");
    }

    @PostMapping( path = "/signin" )
    public ResponseEntity<String> signIn(@RequestBody UserModel user) {
        String token = userService.verifyUserPassword(user.getUsername(), user.getPassword());       
        if ( token != null) {
            return ResponseEntity.ok().body(token);
        } 
        return ResponseEntity.status(400).body("Incorrect username or password");
    }

    @PostMapping( path = "/profile/image" )
    public ResponseEntity<String> setProfileImage(@RequestHeader String token, @RequestParam MultipartFile file) {
        try {
            userService.setProfileImage(token, file);
            return ResponseEntity.ok().body("ok");      
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return ResponseEntity.badRequest().body("error");
    }

    @GetMapping( path = "/profile/image")
    public ResponseEntity<ProfileImageModel> getProfileImage(@RequestHeader String token) {
        try {
            ProfileImageModel img = userService.getProfileImage(token);
            if (img != null) return ResponseEntity.ok().body(img);
        }
        catch (Exception ex) {
        } 

        return ResponseEntity.badRequest().body(null);
    }
        
}
