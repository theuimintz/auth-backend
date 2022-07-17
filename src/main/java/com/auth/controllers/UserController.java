package com.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    public @ResponseBody ResponseEntity<UserDTO> signIn(@RequestBody UserDTO user) {
        UserDTO fuser = userService.getBySignInData(user.getUsername(), user.getPassword());

        if (fuser != null) return ResponseEntity.status(200).body(fuser);
        return ResponseEntity.status(401).body(null); // For unauthorized
    }


    @GetMapping( path = "/profile/all/{id}")
    public ResponseEntity<UserDTO> getPublicUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getById(id));
    }
}
