package com.auth.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping ( path = "/api" )
public class TestController {

    @GetMapping
    public ResponseEntity<String> apiConnectionTest() {
        return ResponseEntity.ok().body("API is working!");
    }

}
