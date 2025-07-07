/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.security;

import org.mindrot.jbcrypt.BCrypt;
import com.motorph.usermanagement.dao.UserDAO;
import com.motorph.usermanagement.dao.UserDAOImpl;
import java.security.SecureRandom;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.regex.Pattern;
/**
 * This will provides secure password handling with salt generation and verification.
 * 
 * @author Harvey 
 */
public class AuthenticatorImpl implements Authenticator {
    private static final Logger logger = Logger.getLogger(AuthenticatorImpl.class.getName());
    private static final int BCRYPT_ROUNDS = 12; // BCrypt work factor
    private final SecureRandom secureRandom;
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder; // Added PasswordEncoder 
    
    // Password security requirements
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final Pattern UPPERCASE_PATTERN = Pattern.compile("[A-Z]");
    private static final Pattern LOWERCASE_PATTERN = Pattern.compile("[a-z]");
    private static final Pattern DIGIT_PATTERN = Pattern.compile("[0-9]");
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]");
    
    /**
     * Constructor initializes secure random generator 
     */
    public AuthenticatorImpl() {
        this.secureRandom = new SecureRandom();
        this.userDAO = new UserDAOImpl(); // 
        this.passwordEncoder = new PasswordEncoder(); // 
        logger.info(() -> "AuthenticatorImpl initialized with BCrypt rounds: " + BCRYPT_ROUNDS);
    }
    
    /**
     * Constructor with dependency injection (recommended for testing)
     * 
     * @param userDAO handles access to user data from the database
     * @param passwordEncoder encodes and verifies user passwords
     */
    public AuthenticatorImpl(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.secureRandom = new SecureRandom();
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
        logger.info(() -> "AuthenticatorImpl initialized with injected dependencies");
    }
    
    @Override
    public String hashPassword(String plainPassword) {
        if (plainPassword == null || plainPassword.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        
        try {
            String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt(BCRYPT_ROUNDS));
            logger.fine("Password hashed successfully");
            return hashedPassword;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to hash password", e);
            throw new RuntimeException("Password hashing failed", e);
        }
    }
    
    @Override
    public boolean verifyPassword(String plainPassword, String hashedPassword) {
        if (plainPassword == null || hashedPassword == null) {
            logger.warning("Password verification failed: null input provided");
            return false;
        }
        
        try {
            boolean isValid = BCrypt.checkpw(plainPassword, hashedPassword);
            logger.fine(() -> "Password verification result: " + isValid);
            return isValid;
        } catch (Exception e) {
            logger.log(Level.WARNING, "Password verification failed due to exception", e);
            return false;
        }
    }
    
    /**
     * Enhanced authentication method that handles both plain text and hashed passwords
     * This method will automatically migrate ALL plain text passwords to hashed ones
     * 
     * @param plainPassword the password entered by the user
     * @param storedPassword the password currently stored in the system 
     * @param userId the ID of the user attempting to log in
     * @return true if the password is correct and the user is authenticated, false otherwise
     */
    public boolean authenticateAndMigrate(String plainPassword, String storedPassword, int userId) {
        if (plainPassword == null || storedPassword == null) {
            logger.warning("Authentication failed: null input provided");
            return false;
        }
        
        // Check if the stored password is already hashed (BCrypt format)
        if (isHashedPassword(storedPassword)) {
            // Use BCrypt verification for hashed passwords
            boolean isValid = verifyPassword(plainPassword, storedPassword);
            logger.fine(() -> "BCrypt authentication result for user " + userId + ": " + isValid);
            return isValid;
        } else {
            // Handle plain text password (legacy data) 
            logger.info(() -> "Found plain text password for user " + userId + ", attempting migration");
            
            if (plainPassword.equals(storedPassword)) {
                // Password matches, now hash it and update in database
                try {
                    String newHashedPassword = hashPassword(plainPassword);
                    boolean updated = userDAO.updatePassword(userId, newHashedPassword);
                    
                    if (updated) {
                        logger.info(() -> "Successfully migrated plain text password to hashed for user ID: " + userId);
                    } else {
                        logger.warning(() -> "Failed to migrate password for user ID: " + userId);
                    }
                    
                    return true; // Authentication successful
                    
                } catch (Exception e) {
                    logger.log(Level.WARNING, "Password migration failed for user ID: " + userId, e);
                    return true; // Still authenticate, but migration failed
                }
            } else {
                logger.fine(() -> "Plain text password authentication failed for user " + userId);
                return false;
            }
        }
    }
    
    /**
     * Main authentication method that should be used by your login system
     * This will ensure ALL users get their passwords migrated when they login
     * 
     * @param username the username of the user trying to log in
     * @param plainPassword the password entered by the user in plain text
     * @return true if the user is found and the password matches, false otherwise
     */
    public boolean authenticate(String username, String plainPassword) {
        try {
            // Find user by username
            java.util.Optional<com.motorph.usermanagement.model.User> userOpt = userDAO.findByUsername(username);
            
            if (!userOpt.isPresent()) {
                logger.warning(() -> "Authentication failed: user not found - " + username);
                return false;
            }
            
            com.motorph.usermanagement.model.User user = userOpt.get();
            
            // Use the migrate method to handle both plain text and hashed passwords
            boolean authenticated = authenticateAndMigrate(plainPassword, user.getPasswordHashed(), user.getUserId());
            
            if (authenticated) {
                // Update last login timestamp
                try {
                    userDAO.updateLastLogin(user.getUserId());
                    logger.info(() -> "Updated last login for user: " + username);
                } catch (Exception e) {
                    logger.log(Level.WARNING, "Failed to update last login for user: " + username, e);
                }
            }
            
            return authenticated;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Authentication error for user: " + username, e);
            return false;
        }
    }
    
    /**
     * Determines if a password string is already hashed using BCrypt
     * BCrypt hashes start with $2a$, $2b$, $2x$, or $2y$
     */
    private boolean isHashedPassword(String password) {
        if (password == null || password.length() < 7) {
            return false;
        }
        
        // BCrypt hashes have the format $2[abxy]$rounds$salt+hash
        return password.startsWith("$2") && password.length() == 60;
    }
    
    @Override
    public String generateSalt() {
        return BCrypt.gensalt(BCRYPT_ROUNDS, secureRandom);
    }
    
    @Override
    public boolean isPasswordSecure(String password) {
        if (password == null) {
            logger.warning("Password security check failed: password is null");
            return false;
        }
        
        // Check minimum length
        if (password.length() < MIN_PASSWORD_LENGTH) {
            logger.fine("Password security check failed: insufficient length");
            return false;
        }
        
        // Check for uppercase letter
        if (!UPPERCASE_PATTERN.matcher(password).find()) {
            logger.fine("Password security check failed: no uppercase letter");
            return false;
        }
        
        // Check for lowercase letter
        if (!LOWERCASE_PATTERN.matcher(password).find()) {
            logger.fine("Password security check failed: no lowercase letter");
            return false;
        }
        
        // Check for digit
        if (!DIGIT_PATTERN.matcher(password).find()) {
            logger.fine("Password security check failed: no digit");
            return false;
        }
        
        // Check for special character
        if (!SPECIAL_CHAR_PATTERN.matcher(password).find()) {
            logger.fine("Password security check failed: no special character");
            return false;
        }
        
        logger.fine("Password meets all security requirements");
        return true;
    }
}
