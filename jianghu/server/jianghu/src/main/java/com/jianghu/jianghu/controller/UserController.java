package com.jianghu.jianghu.controller;

import com.jianghu.jianghu.Entity.User;
import com.jianghu.jianghu.dto.AddUserInputDto;

import com.jianghu.jianghu.dto.GetUserIdOutput;
import com.jianghu.jianghu.dto.HttpExceptionOutputDto;
import com.jianghu.jianghu.dto.SignInUserInputDto;
import com.jianghu.jianghu.serviceImpl.UserServiceImpl;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 *  The controller for the user API
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    /** Add user to the database.
     *
     * @param addUserInputDto The user information
     * @return the user ID of the new user
     */
    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody AddUserInputDto addUserInputDto) {

        // check for bad input
        if (!addUserInputDto.check()){
            return new ResponseEntity<>(new HttpExceptionOutputDto("Bad Input"), HttpStatus.valueOf(400));
        }
        String username = addUserInputDto.getUsername();
        String password = addUserInputDto.getUsername();
        String email = addUserInputDto.getEmail();
        String phone = addUserInputDto.getPhone();

        // add user information
        String userId = userServiceImpl.addUserInfo(username, email, phone);

        // email or phone number already in use
        if (userId == null) {
            return new ResponseEntity<>(
                    new HttpExceptionOutputDto("The user with this phone number or email already exists"),
                    HttpStatus.valueOf(409)
            );
        }

        // add user credential
        Boolean addedCredential = userServiceImpl.addUserCredential(userId, password);
        if (!addedCredential) {
            return new ResponseEntity<>(
                    new HttpExceptionOutputDto("Failed to add user credentials"),
                    HttpStatus.valueOf(500)
            );
        }

        return new ResponseEntity<>(new GetUserIdOutput(userId), HttpStatus.valueOf(200));
    }

    /** Get the user information of the user with given UUID.
     *
     * @param userId the UUID of the user
     * @return the user information
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserByUserId(@PathVariable String userId) {

        // Null input check, should not happen due to RESTful rules
        if (userId == null) {
            return new ResponseEntity<>(
                    new HttpExceptionOutputDto("Bad Input"),
                    HttpStatus.valueOf(400)
            );
        }

        User user = userServiceImpl.getUserByUserId(userId);
        // user with this UUID does not exist
        if (user == null){
            return new ResponseEntity<>(
                    new HttpExceptionOutputDto("Cannot find user with this ID"),
                    HttpStatus.valueOf(404)
            );
        }
        return new ResponseEntity<>(user, HttpStatus.valueOf(200));
    }

    /** Get the UUID of user with given phone number or email.
     *
     * @param email the email of the user
     * @param phone the phone number of the user
     * @return the UUID of the user
     */
    @GetMapping("/userId")
    public ResponseEntity<?> getUserId(@RequestParam(required = false) String email,
                                       @RequestParam(required = false) String phone) {

        // phone number and email address both not given
        if (email == null && phone == null){
            return new ResponseEntity<>(
                    new HttpExceptionOutputDto("Bad Input"),
                    HttpStatus.valueOf(404)
            );
        }

        String userId = null;
        // email address is given
        if (email != null){
            userId = userServiceImpl.getUserIdByEmail(email);
        }
        // phone number is given
        if (phone != null){
            userId = userServiceImpl.getUserIdByPhone(phone);
        }

        // cannot find user with given information
        if (userId == null){
            return new ResponseEntity<>(
                    new HttpExceptionOutputDto("Cannot find userId with given phone number or email address"),
                    HttpStatus.valueOf(404)
            );
        }

        return new ResponseEntity<>(
                new GetUserIdOutput(userId),
                HttpStatus.valueOf(200)
        );

    }

    @PostMapping(value = "/sign-in")
    public ResponseEntity<?> signInUser(@RequestBody SignInUserInputDto signInUserInputDto) {

        // check for bad input
        if (!signInUserInputDto.check()){
            return new ResponseEntity<>(new HttpExceptionOutputDto("Bad Input"), HttpStatus.valueOf(400));
        }
        String userId = signInUserInputDto.getUserId();
        String password = signInUserInputDto.getPassword();

        // authenticate user
        Boolean authenticated = userServiceImpl.authenticateUser(userId, password);

        return new ResponseEntity<>(authenticated, HttpStatus.valueOf(200));
    }


}
