package com.jianghu.jianghu.service;

public interface AuthenticationService {

    /** Add user authentication information.
     *
     * @param userId The ID of the user
     * @param password The password for the user
     * @return True if successfully added, False otherwise
     */
    public Boolean addUserCredential(String userId, String password);

    /** Authenticate user with the given input
     * @param userId The ID of the user
     * @param password The password entered
     * @return True if the password is correct, False otherwise
     */
    public Boolean authenticateUser(String userId, String password);
}
