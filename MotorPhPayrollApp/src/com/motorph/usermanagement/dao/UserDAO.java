/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.motorph.usermanagement.dao;

import com.motorph.usermanagement.model.User;
import com.motorph.usermanagement.exception.DataAccessException;
import java.util.Optional;

/**
 * DAO interface focused on login functionality only.
 * 
 * @author Harvey
 */
public interface UserDAO {
    /**
     * Retrieves a user by their unique database ID.
     * Used for user lookup after authentication.
     * 
     * @param userId Primary key to search for
     * @return Optional containing User if found, empty if not found
     * @throws DataAccessException if database operation fails
     */
    Optional<User> findById(int userId) throws DataAccessException;
    
    /**
     * Retrieves a user by their unique username.
     * Primary method for login authentication lookups.
     * 
     * @param username Username to search for
     * @return Optional containing User if found, empty if not found
     * @throws DataAccessException if database operation fails
     */
    Optional<User> findByUsername(String username) throws DataAccessException;
    
    /**
     * Updates the user's last login timestamp.
     * Called after successful authentication to track login activity.
     * 
     * @param userId User's database ID
     * @return true if update was successful
     * @throws DataAccessException if database operation fails
     */
    boolean updateLastLogin(int userId) throws DataAccessException;
    
    /**
     * Updates the user's password hash in the database.
     * Used for migrating plain text passwords to hashed passwords.
     * 
     * @param userId User's database ID
     * @param hashedPassword The BCrypt hashed password
     * @return true if update was successful
     * @throws DataAccessException if database operation fails
     */
    boolean updatePasswordHash(int userId, String hashedPassword) throws DataAccessException;
}
    