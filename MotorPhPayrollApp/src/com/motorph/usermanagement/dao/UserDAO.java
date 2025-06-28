/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.motorph.usermanagement.dao;

import com.motorph.usermanagement.model.User;
import com.motorph.usermanagement.exception.UserNotFoundException;
import com.motorph.usermanagement.exception.DuplicateUserException;
import com.motorph.usermanagement.exception.DataAccessException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author harvey punsalan
 */
public interface UserDAO {
    /**
     * Creates a new user record in the database.
     * 
     * @param user User object containing the data to be inserted
     * @return Generated user ID from database
     * @throws DuplicateUserException if username already exists
     * @throws DataAccessException if database operation fails
     */
    int createUser(User user) throws DuplicateUserException, DataAccessException;
    
    /**
     * Retrieves a user by their unique database ID.
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
     * Retrieves a user by their associated employee ID.
     * Useful for linking employee records with user accounts.
     * 
     * @param employeeId Employee identifier to search for
     * @return Optional containing User if found, empty if not found
     * @throws DataAccessException if database operation fails
     */
    Optional<User> findByEmployeeId(String employeeId) throws DataAccessException;
    
    /**
     * Retrieves all users from the database.
     * Typically used for admin user management interfaces.
     * 
     * @return List of all User objects
     * @throws DataAccessException if database operation fails
     */
    List<User> findAll() throws DataAccessException;
    
    /**
     * Retrieves all users with a specific role.
     * Useful for role-based user management and reporting.
     * 
     * @param roleId Role identifier to filter by
     * @return List of Users with the specified role
     * @throws DataAccessException if database operation fails
     */
    List<User> findByRole(int roleId) throws DataAccessException;
    
    /**
     * Retrieves only active users from the database.
     * Excludes deactivated accounts from results.
     * 
     * @return List of active User objects
     * @throws DataAccessException if database operation fails
     */
    List<User> findActiveUsers() throws DataAccessException;
    
    /**
     * Updates an existing user record in the database.
     * Updates all user fields except password (use updatePassword for that).
     * 
     * @param user User object with updated information
     * @return true if update was successful, false if no rows affected
     * @throws UserNotFoundException if user doesn't exist
     * @throws DataAccessException if database operation fails
     */
    boolean updateUser(User user) throws UserNotFoundException, DataAccessException;
    
    /**
     * Updates only the user's password (for password change operations).
     * 
     * @param userId User's database ID
     * @param newPasswordHash New BCrypt hashed password
     * @return true if update was successful
     * @throws UserNotFoundException if user doesn't exist
     * @throws DataAccessException if database operation fails
     */
    boolean updatePassword(int userId, String newPasswordHash) throws UserNotFoundException, DataAccessException;
    
    /**
     * Updates the user's last login timestamp.
     * Called after successful authentication.
     * 
     * @param userId User's database ID
     * @return true if update was successful
     * @throws DataAccessException if database operation fails
     */
    boolean updateLastLogin(int userId) throws DataAccessException;
    
    /**
     * Assigns a role to a user by updating their role_id.
     * 
     * @param userId User's database ID
     * @param roleId Role ID to assign
     * @return true if assignment was successful
     * @throws UserNotFoundException if user doesn't exist
     * @throws DataAccessException if database operation fails
     */
    boolean assignRole(int userId, int roleId) throws UserNotFoundException, DataAccessException;
    
    /**
     * Sets the active status of a user account.
     * 
     * @param userId User's database ID
     * @param isActive true to activate, false to deactivate
     * @return true if status change was successful
     * @throws UserNotFoundException if user doesn't exist
     * @throws DataAccessException if database operation fails
     */
    boolean setUserStatus(int userId, boolean isActive) throws UserNotFoundException, DataAccessException;
    
    /**
     * Deactivates a user account (soft delete).
     * Sets is_active flag to false instead of deleting the record.
     * 
     * @param userId User's database ID
     * @return true if deactivation was successful
     * @throws UserNotFoundException if user doesn't exist
     * @throws DataAccessException if database operation fails
     */
    boolean deactivateUser(int userId) throws UserNotFoundException, DataAccessException;
    
    /**
     * Reactivates a previously deactivated user account.
     * 
     * @param userId User's database ID
     * @return true if reactivation was successful
     * @throws UserNotFoundException if user doesn't exist
     * @throws DataAccessException if database operation fails
     */
    boolean activateUser(int userId) throws UserNotFoundException, DataAccessException;
    
    /**
     * Changes a user's password with proper validation.
     * 
     * @param userId User's database ID
     * @param hashedPassword New hashed password
     * @return true if password change was successful
     * @throws UserNotFoundException if user doesn't exist
     * @throws DataAccessException if database operation fails
     */
    boolean changePassword(int userId, String hashedPassword) throws UserNotFoundException, DataAccessException;
    
    /**
     * Permanently deletes a user from the database.
     * Should be used with extreme caution as this cannot be undone.
     * 
     * @param userId User's database ID
     * @return true if deletion was successful
     * @throws UserNotFoundException if user doesn't exist
     * @throws DataAccessException if database operation fails
     */
    boolean deleteUser(int userId) throws UserNotFoundException, DataAccessException;
    
    /**
     * Checks if a username already exists in the database.
     * Used for validation during user registration.
     * 
     * @param username Username to check
     * @return true if username exists, false otherwise
     * @throws DataAccessException if database operation fails
     */
    boolean usernameExists(String username) throws DataAccessException;
    
    /**
     * Checks if an employee ID is already associated with a user account.
     * Prevents duplicate user accounts for the same employee.
     * 
     * @param employeeId Employee ID to check
     * @return true if employee ID is already linked to a user account
     * @throws DataAccessException if database operation fails
     */
    boolean employeeHasAccount(String employeeId) throws DataAccessException;
    
    /**
     * Counts the total number of users in the system.
     * Useful for dashboard statistics and pagination.
     * 
     * @return Total user count
     * @throws DataAccessException if database operation fails
     */
    int getUserCount() throws DataAccessException;
    
    /**
     * Counts the number of active users in the system.
     * 
     * @return Active user count
     * @throws DataAccessException if database operation fails
     */
    int getActiveUserCount() throws DataAccessException;
    
    /**
     * Counts the number of inactive/deactivated users in the system.
     * 
     * @return Inactive user count
     * @throws DataAccessException if database operation fails
     */
    int getInactiveUserCount() throws DataAccessException;
    
    /**
     * Counts users by role.
     * 
     * @param roleId Role ID to count
     * @return Number of users with the specified role
     * @throws DataAccessException if database operation fails
     */
    int getUserCountByRole(int roleId) throws DataAccessException;
}
    