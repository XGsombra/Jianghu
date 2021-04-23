package com.jianghu.jianghu.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import com.jianghu.jianghu.Entity.User;
import com.jianghu.jianghu.dto.AddUserInputDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping(value = "/h")
    public ResponseEntity<?> create(@RequestBody AddUserInputDto user) {
        System.out.println(user.getName());
        return new ResponseEntity<>(user, HttpStatus.valueOf(200));
    }

    @GetMapping("/h")
    public ResponseEntity<?> getUser() {
        User user = new User("Jack");
        return new ResponseEntity<>(user, HttpStatus.valueOf(200));
    }

}
