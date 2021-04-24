package com.jianghu.jianghu.service;

import java.security.NoSuchAlgorithmException;

public interface AuthenticationService {

    /** Add user authentication information.
     *
     * @param userId The ID of the user
     * @param password The password for the user
     * @return True if successfully added, False otherwise
     */
    Boolean addUserCredential(String userId, String password) throws NoSuchAlgorithmException;

    /** Authenticate user with the given input
     * @param userId The ID of the user
     * @param password The password entered
     * @return True if the password is correct, False otherwise
     */
    Boolean authenticateUser(String userId, String password);
}
