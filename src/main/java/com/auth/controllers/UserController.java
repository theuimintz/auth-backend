package com.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.models.UserModel;
import com.auth.services.UserService;
import com.auth.util.JwtUtil;


@RestController
@RequestMapping( path = "/api/users" )
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwt;
    
    @PostMapping( path = "signup" )
    public ResponseEntity<String> singUp(@RequestBody UserModel user) {
        userService.createNewUser(user);
        return ResponseEntity.ok().body("ok");
    }

    @PostMapping( path = "signin" )
    public ResponseEntity<String> signIn(@RequestBody UserModel user) {
        UserModel dbUser = userService.verifyUserPassword(user.getUsername(), user.getPassword());       
        if (dbUser != null) {
            return ResponseEntity.ok().body(jwt.generateJwtToken(user.getUsername()));
        } 
        return ResponseEntity.status(400).body("Incorrect username or password");
    }
        
}
