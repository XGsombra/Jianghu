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
    public String addUser(String username, String email, String phone);

    /** Get the user information.
     * @param userid the UUID of the user
     * @return the user object that represents the user information
     */
    public User GetUser(String userid);

}
