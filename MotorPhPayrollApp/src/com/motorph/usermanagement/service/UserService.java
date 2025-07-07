/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.motorph.usermanagement.service;

import com.motorph.usermanagement.model.User;
import com.motorph.usermanagement.exception.UserNotFoundException;
import com.motorph.usermanagement.exception.DuplicateUserException;
import com.motorph.usermanagement.exception.InvalidCredentialsException;
import com.motorph.usermanagement.exception.DataAccessException;
import java.util.List;
import java.util.Optional;

/**
 * This interface defines the contract for user management functionality
 * including authentication, registration, and user administration.
 * 
 * @author Harvey
 */
public interface UserService {
    /**
     * Registers a new user in the system.
     * Validates user data and ensures business rules are met before creation.
     * 
     * @param user User object containing registration details
     * @return Generated user ID from successful registration
     * @throws DuplicateUserException if username already exists
     * @throws DataAccessException if database operation fails
     */
    int registerUser(User user) throws DuplicateUserException, DataAccessException;
    
    /**
     * Authenticates a user with username and password credentials.
     * Updates last login timestamp upon successful authentication.
     * 
     * @param username User's login username
     * @param password User's plain text password
     * @return User object if authentication successful
     * @throws InvalidCredentialsException if credentials are invalid
     * @throws DataAccessException if database operation fails
     */
    User authenticate(String username, String password) throws InvalidCredentialsException, DataAccessException;
    
    /**
     * Assigns a specific role to a user account.
     * Validates that both user and role exist before assignment.
     * 
     * @param userId User's database ID
     * @param roleId Role's database ID
     * @return true if role assignment successful
     * @throws UserNotFoundException if user doesn't exist
     * @throws DataAccessException if database operation fails
     */
    boolean assignRole(int userId, int roleId) throws UserNotFoundException, DataAccessException;
    
    /**
     * Updates user profile information.
     * Validates updated data before applying changes.
     * 
     * @param user User object with updated information
     * @return true if update successful
     * @throws UserNotFoundException if user doesn't exist
     * @throws DataAccessException if database operation fails
     */
    boolean updateUser(User user) throws UserNotFoundException, DataAccessException;
    
    /**
     * Retrieves a user by their unique database ID.
     * 
     * @param userId User's database ID
     * @return Optional containing User if found
     * @throws DataAccessException if database operation fails
     */
    Optional<User> getUserById(int userId) throws DataAccessException;
    
    /**
     * Retrieves a user by their username.
     * 
     * @param username User's login username
     * @return Optional containing User if found
     * @throws DataAccessException if database operation fails
     */
    Optional<User> getUserByUsername(String username) throws DataAccessException;
    
    /**
     * Retrieves a user by their employee ID.
     * Links user account to employee records.
     * 
     * @param employeeId Employee's ID from HR system
     * @return Optional containing User if found
     * @throws DataAccessException if database operation fails
     */
    Optional<User> getUserByEmployeeId(int employeeId) throws DataAccessException;
    
    /**
     * Retrieves all users in the system.
     * 
     * @return List of all User objects
     * @throws DataAccessException if database operation fails
     */
    List<User> getAllUsers() throws DataAccessException;
    
    /**
     * Retrieves users filtered by role.
     * 
     * @param roleId Role ID to filter by
     * @return List of User objects with specified role
     * @throws DataAccessException if database operation fails
     */
    List<User> getUsersByRole(int roleId) throws DataAccessException;
    
    /**
     * Retrieves only active user accounts.
     * 
     * @return List of active User objects
     * @throws DataAccessException if database operation fails
     */
    List<User> getActiveUsers() throws DataAccessException;
    
    /**
     * Activates or deactivates a user account.
     * 
     * @param userId User's database ID
     * @param isActive Status to set (true = active, false = inactive)
     * @return true if status change successful
     * @throws UserNotFoundException if user doesn't exist
     * @throws DataAccessException if database operation fails
     */
    boolean setUserStatus(int userId, boolean isActive) throws UserNotFoundException, DataAccessException;
    
    /**
     * Changes a user's password with proper validation.
     * 
     * @param userId User's database ID
     * @param newPassword New plain text password
     * @return true if password change successful
     * @throws UserNotFoundException if user doesn't exist
     * @throws DataAccessException if database operation fails
     */
    boolean changePassword(int userId, String newPassword) throws UserNotFoundException, DataAccessException;
    
    /**
     * Validates if a username is available for registration.
     * 
     * @param username Username to check
     * @return true if username is available
     * @throws DataAccessException if database operation fails
     */
    boolean isUsernameAvailable(String username) throws DataAccessException;
    
    /**
     * Gets total count of users in the system.
     * 
     * @return Total user count
     * @throws DataAccessException if database operation fails
     */
    int getUserCount() throws DataAccessException;
    
    /**
     * Gets count of active users in the system.
     * 
     * @return Active user count
     * @throws DataAccessException if database operation fails
     */
    int getActiveUserCount() throws DataAccessException;
    
    /**
     * Searches for users by username using partial matching.
     * Called by UserController.searchUsers() method.
     * 
     * @param searchTerm Partial username to search for
     * @return List of User objects matching the search criteria
     * @throws DataAccessException if database operation fails
     */
    List<User> searchUsersByUsername(String searchTerm) throws DataAccessException;
    
    /**
     * Resets a user's password to a new value.
     * Called by UserController.resetUserPassword() method.
     *
     * @param userId User's database ID
     * @param newPassword New password to set (will be hashed in implementation)
     * @return true if password reset successful
     * @throws UserNotFoundException if user doesn't exist
     * @throws DataAccessException if database operation fails
     */
    boolean resetPassword(int userId, String newPassword) throws UserNotFoundException, DataAccessException;
}
