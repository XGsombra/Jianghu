package com.jianghu.jianghu.service;

import com.jianghu.jianghu.Entity.User;

public interface UserService {

    /** Add user information to the database.
     *
     * @param username username of the user
     * @param email email of the user
     * @param phone phone number of the user
     * @return the UUID of the user
     */
    String addUserInfo(String username, String email, String phone);

    /** Remove the user profile.
     *
     * @param userId The UUID of the user
     */
    void removeUserInfo(String userId);

    /** Get the user information.
     *
     * @param userId the UUID of the user
     * @return the user object that represents the user information
     */
    User getUserByUserId(String userId);

    /** Get user UUID by email.
     *
     * @param email email of the user
     * @return the UUID of the user
     */
    String getUserIdByEmail(String email);

    /** Get user UUID by phone.
     *
     * @param phone phone number of the user
     * @return the UUID of the user
     */
    String getUserIdByPhone(String phone);

}
