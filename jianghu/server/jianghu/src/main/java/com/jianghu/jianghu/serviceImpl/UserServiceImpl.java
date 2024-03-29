package com.jianghu.jianghu.serviceImpl;

import com.jianghu.jianghu.entity.User;
import com.jianghu.jianghu.entity.UserAuth;
import com.jianghu.jianghu.mapper.UserAuthMapper;
import com.jianghu.jianghu.mapper.UserMapper;
import com.jianghu.jianghu.service.AuthenticationService;
import com.jianghu.jianghu.service.UserService;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService, AuthenticationService {

    @Autowired
    private UserAuthMapper userAuthMapper;

    @Autowired
    private UserMapper userMapper;

    /** Initialize the user_info table.
     *
     * @return true if initialized the user_info table, false if the table already exists
     */
    public Boolean initializeUserInfoTable(){
        if (!userMapper.existUserInfoTable()){
            userMapper.createUserInfoTable();
            System.out.println("Initialization: Successfully initiated table user_info");
            return true;
        }
        System.out.println("Initialization: table user_info already exists");
        return false;
    }

    /** Initialize the user_auth table.
     *
     * @return true if initialized the user_auth table, false if the table already exists
     */
    public Boolean initializeUserAuthTable(){
        if (!userAuthMapper.existUserAuthTable()){
            userAuthMapper.createUserAuthTable();
            System.out.println("Initialization: Successfully initiated table user_auth");
            return true;
        }
        System.out.println("Initialization: table user_auth already exists");
        return false;
    }

    /** Add user information to the database.
     *
     * @param username username of the user
     * @param email email of the user
     * @param phone phone number of the user
     * @return the UUID of the user
     */
    @Override
    public String addUserInfo(String username, String email, String phone) {
        String userId = UUID.randomUUID().toString();
        if ((email != null && userMapper.getUserIdByEmail(email) != null) ||
                (phone != null && userMapper.getUserIdByPhone(phone) != null)) {
            return null;
        }
        userMapper.createUserInfo(userId, username, email, phone, 0);
        return userId;
    }

    public void printUsers(){
        List<User> users = userMapper.getAllUsers();
        for (User user:users){
            System.out.println(user.getUsername());
        }
    }

    /**
     * Remove the user profile.
     *
     * @param userId The UUID of the user
     * @return true if successfully removed user info
     */
    @Override
    public void removeUserInfo(String userId) {
        userMapper.deleteUserInfo(userId);
    }

    /**
     * Get the user information.
     *
     * @param userId the UUID of the user
     * @return the user object that represents the user information
     */
    @Override
    public User getUserByUserId(String userId) {
        return userMapper.getUserByUserId(userId);
    }

    /**
     * Get user UUID by email.
     *
     * @param email email of the user
     * @return the UUID of the user
     */
    @Override
    public String getUserIdByEmail(String email) {
        return userMapper.getUserIdByEmail(email);
    }

    /**
     * Get user UUID by phone.
     *
     * @param phone phone number of the user
     * @return the UUID of the user
     */
    @Override
    public String getUserIdByPhone(String phone) {
        return userMapper.getUserIdByPhone(phone);
    }

    /**
     * Add user authentication information.
     *
     * @param userId   The ID of the user
     * @param password The password for the user
     * @return True if successfully added, False otherwise
     */
    @Override
    public Boolean addUserCredential(String userId, String password) {

        // get random salt
        byte[] salt = getRandomSalt();
        try {
            // get hash function and hash the password
            String saltedHash = hashPassword(salt, password);
            // add credential to database
            userAuthMapper.createUserCredential(userId, HexUtils.toHexString(salt), saltedHash);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Authenticate user with the given input
     *
     * @param userId   The ID of the user
     * @param password The password entered
     * @return True if the password is correct, False otherwise
     */
    @Override
    public Boolean authenticateUser(String userId, String password) {
        UserAuth userAuth = userAuthMapper.getCredentialByUserId(userId);
        // user does not exist, return false
        if (userAuth == null) {
            return false;
        }
        byte[] salt = HexUtils.fromHexString(userAuth.getSalt());
        try {
            String actualHash = hashPassword(salt, password);
            return actualHash.equals(userAuth.getHash());
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /** Generate salt for hashing.
     *
     * @return the random salt
     */
    private byte[] getRandomSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    /** Hash password.
     *
     * @param salt the salt for hash
     * @param password the password to hash
     * @return the salted hash password
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private String hashPassword(byte[] salt, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        String hexSaltedHash = HexUtils.toHexString(hash);
        spec.clearPassword();
        return hexSaltedHash;
    }
}
