/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.service;

import com.motorph.usermanagement.model.User;
import com.motorph.usermanagement.dao.UserDAO;
import com.motorph.usermanagement.dao.UserDAOImpl;
import com.motorph.usermanagement.exception.InvalidCredentialsException;
import com.motorph.usermanagement.exception.DataAccessException;
import com.motorph.usermanagement.security.AuthenticatorImpl;   //
import com.motorph.usermanagement.security.PasswordEncoder;

import java.util.Optional;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Simplified implementation focused on login functionality only.
 * Contains business logic for user authentication operations.
 * 
 * @author Harvey 
 */
public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
    
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    
    /**
     * Constructor initializes required DAO and security components.
     */
    public UserServiceImpl() {
        this.userDAO = new UserDAOImpl();
        this.passwordEncoder = new PasswordEncoder();
        logger.info("UserServiceImpl initialized successfully for login functionality");
    }
    
    /**
     * Constructor useful for testing
     * 
     * @param userDAO provides access to user-related data
     */
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
        this.passwordEncoder = new PasswordEncoder();
    }
    
    @Override
    public User authenticate(String username, String password) throws InvalidCredentialsException, DataAccessException {
        try {
            // Input validation
            if (username == null || username.trim().isEmpty()) {
                throw new InvalidCredentialsException("Username cannot be empty");
            }
            
            if (password == null || password.isEmpty()) {
                throw new InvalidCredentialsException("Password cannot be empty");
            }
            
            // Find user first
            Optional<User> userOpt = userDAO.findByUsername(username.trim());
            
            if (!userOpt.isPresent()) {
                throw new InvalidCredentialsException("Invalid username or password");
            }
            
            User user = userOpt.get();
            
            // Check if user is active
            if (!user.isActive()) {
                throw new InvalidCredentialsException("User account is deactivated");
            }
            
            String storedPassword = user.getPasswordHashed();
            if (storedPassword == null || storedPassword.trim().isEmpty()) {
                throw new InvalidCredentialsException("No password set for user");
            }
            
            storedPassword = storedPassword.trim();
            boolean authenticated = false;
            
            // Check if password needs to be migrated from plain text to hashed
            if (!passwordEncoder.isEncoded(storedPassword)) {
                // Password is in plain text, need to verify and then hash it
                if (password.equals(storedPassword)) {
                    authenticated = true;
                    
                    // Migrate password to hashed format
                    try {
                        String hashedPassword = passwordEncoder.encode(password);
                        boolean updated = userDAO.updatePasswordHash(user.getUserId(), hashedPassword);
                        if (updated) {
                            logger.info(() -> "Successfully migrated password to hashed format for user: " + username);
                        } else {
                            logger.warning(() -> "Failed to migrate password to hashed format for user: " + username);
                        }
                    } catch (Exception e) {
                        logger.log(Level.WARNING, "Error during password migration for user: " + username, e);
                        // Don't fail authentication due to migration error
                    }
                } else {
                    authenticated = false;
                }
            } else {
                // Password is already hashed, use proper verification
                authenticated = passwordEncoder.matches(password, storedPassword);
            }
            
            if (!authenticated) {
                throw new InvalidCredentialsException("Invalid username or password");
            }
            
            // Update last login timestamp after successful authentication
            try {
                userDAO.updateLastLogin(user.getUserId());
                logger.fine(() -> "Updated last login timestamp for user: " + username);
            } catch (DataAccessException e) {
                // Log the error but don't fail the authentication
                logger.log(Level.WARNING, "Failed to update last login timestamp for user: " + username, e);
            }
            
            logger.info(() -> "Successful authentication for user: " + username);
            return user;
            
        } catch (InvalidCredentialsException e) {
            logger.warning(() -> "Authentication failed for user: " + username + " - " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Authentication error for user: " + username, e);
            throw new DataAccessException("Authentication failed due to system error", e);
        }
    }
}