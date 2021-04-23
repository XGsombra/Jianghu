package com.jianghu.jianghu.controller;

import com.jianghu.jianghu.Entity.User;
import com.jianghu.jianghu.dto.AddUserInputDto;

import com.jianghu.jianghu.dto.HttpExceptionOutputDto;
import com.jianghu.jianghu.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  The controller for the user API
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    /** Add user to the database.
     * @param addUserInputDto The user information
     * @return the user ID of the new user
     */
    @PostMapping(value = "/")
    public ResponseEntity<?> createUser(@RequestBody AddUserInputDto addUserInputDto) {

        // check for bad input
        if (!addUserInputDto.check()){
            return new ResponseEntity<>(new HttpExceptionOutputDto("Bad input"), HttpStatus.valueOf(400));
        }
        String username = addUserInputDto.getUsername();
        String password = addUserInputDto.getUsername();
        String email = addUserInputDto.getEmail();
        String phone = addUserInputDto.getPhone();

        // add user information
        String userId = userServiceImpl.addUser(username, email, phone);
        // failed to add user
        if (userId == null) {
            return new ResponseEntity<>(
                    new HttpExceptionOutputDto("Failed to add user"),
                    HttpStatus.valueOf(500)
            );
        }
        // email or phone number already in use
        if (userId == "") {
            return new ResponseEntity<>(
                    new HttpExceptionOutputDto("The user with this phone number or email already exists"),
                    HttpStatus.valueOf(409)
            );
        }

        // add user credential
//        Boolean addedCredential = userServiceImpl.addUserCredential(userId, password);
//        if (!addedCredential) {
//            return new ResponseEntity<>(
//                    new HttpExceptionOutputDto("Failed to add user credentials"),
//                    HttpStatus.valueOf(500)
//            );
//        }

        return new ResponseEntity<>(userId, HttpStatus.valueOf(200));
    }

    @GetMapping("/h")
    public ResponseEntity<?> getUser() {
        return new ResponseEntity<>("12345567", HttpStatus.valueOf(200));
    }

}
