package com.jianghu.jianghu.controller;

import com.jianghu.jianghu.entity.User;
import com.jianghu.jianghu.dto.AddUserInputDto;

import com.jianghu.jianghu.dto.GetUserIdOutput;
import com.jianghu.jianghu.dto.HttpExceptionOutputDto;
import com.jianghu.jianghu.dto.SignInUserInputDto;
import com.jianghu.jianghu.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 *  The controller for the user API
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /** Add user to the database.
     *
     * @param addUserInputDto The user information
     * @return the user ID of the new user
     */
    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody AddUserInputDto addUserInputDto, HttpServletRequest request) {

//        if (request.getSession().getAttribute("userId") == null){
//            return new ResponseEntity<>(
//                    new HttpExceptionOutputDto("Access Denied"),
//                    HttpStatus.valueOf(403)
//            );
//        }

        // check for bad input
        if (!addUserInputDto.check()){
            return new ResponseEntity<>(
                    new HttpExceptionOutputDto("Bad Input"),
                    HttpStatus.valueOf(400)
            );
        }
        String username = addUserInputDto.getUsername();
        String password = addUserInputDto.getPassword();
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
            // roll back the user information and return 500
            userServiceImpl.removeUserInfo(userId);
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
    @GetMapping("/login/userId")
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

    /** Sign in the user, set the session of the user.
     *
     * @param signInUserInputDto the user authentication information
     * @return if the user is successfully authenticated
     */
    @PostMapping(value = "/login")
    public ResponseEntity<?> signInUser(@RequestBody SignInUserInputDto signInUserInputDto, HttpSession session) {

        // check for bad input
        if (!signInUserInputDto.check()){
            return new ResponseEntity<>(
                    new HttpExceptionOutputDto("Bad Input"),
                    HttpStatus.valueOf(400)
            );
        }
        String userId = signInUserInputDto.getUserId();
        String password = signInUserInputDto.getPassword();

        // authenticate user
        Boolean authenticated = userServiceImpl.authenticateUser(userId, password);
        if (!authenticated){
            return new ResponseEntity<>(
                    new HttpExceptionOutputDto("Wrong password or user does not exist"),
                    HttpStatus.valueOf(401)
            );
        }

        // set user session
        session.setAttribute("userId", userId);
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", session.getId());
        map.put("userId",session.getAttribute("userId")) ;
        map.put("creationTime", session.getCreationTime());
        map.put("lastAccessedTime", session.getLastAccessedTime());

                // return the authentication information
        return new ResponseEntity<>(map, HttpStatus.valueOf(200));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logOut(HttpSession session){
        session.removeAttribute("userId");
        session.invalidate();
        return new ResponseEntity<>("Successfully logged out", HttpStatus.valueOf(200));
    }

    @GetMapping("/test")
    public ResponseEntity<?> test(){
        userServiceImpl.printUsers();
        return new ResponseEntity<>("Successful", HttpStatus.valueOf(200));
    }
}
