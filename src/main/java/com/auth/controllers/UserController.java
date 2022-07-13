package com.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.auth.dtos.ImageDTO;
import com.auth.dtos.UserDTO;
import com.auth.services.UserService;


@RestController
@RequestMapping( path = "/api/users" )
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping( path = "/profile/create" )
    public ResponseEntity<String> singUp(@RequestBody UserDTO user) {
        userService.createNewUser(user);
        return ResponseEntity.ok().body("ok");
    }

    @PostMapping( path = "/profile/get" )
    public ResponseEntity<UserDTO> signIn(@RequestBody UserDTO user) {
        return ResponseEntity.ok().body(userService.getUser(user.getUsername(), user.getPassword()));
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

    @GetMapping(path = "/profile/image/{id}")
    public ResponseEntity<ImageDTO> getProfileImage(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(userService.getUser(id).getProfileImage());
        }
        catch (Exception ex) {}
        return null;
    }

    @GetMapping( path = "/profile/all/{id}")
    public ResponseEntity<UserDTO> getPublicUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getUser(id));
    }
}
