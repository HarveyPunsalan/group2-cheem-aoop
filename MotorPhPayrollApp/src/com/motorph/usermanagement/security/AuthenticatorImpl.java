/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.security;

import org.mindrot.jbcrypt.BCrypt;
import com.motorph.usermanagement.dao.UserDAO;
import com.motorph.usermanagement.dao.UserDAOImpl;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.regex.Pattern;

/**
 * Provides secure password handling with BCrypt hashing and verification.
 * 
 * @author Harvey 
 */
public class AuthenticatorImpl implements Authenticator {
    private static final Logger logger = Logger.getLogger(AuthenticatorImpl.class.getName());
    private static final int BCRYPT_ROUNDS = 12;
    private final UserDAO userDAO;
    
    // Password security requirements
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final Pattern UPPERCASE_PATTERN = Pattern.compile("[A-Z]");
    private static final Pattern LOWERCASE_PATTERN = Pattern.compile("[a-z]");
    private static final Pattern DIGIT_PATTERN = Pattern.compile("[0-9]");
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]");
    
    /**
     * Default constructor
     */
    public AuthenticatorImpl() {
        this.userDAO = new UserDAOImpl();
        logger.info(() -> "AuthenticatorImpl initialized with BCrypt rounds: " + BCRYPT_ROUNDS);
    }
    
    /**
     * Constructor with dependency injection
     * 
     * @param userDAO User data access object
     */
    public AuthenticatorImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
        logger.info("AuthenticatorImpl initialized with injected UserDAO");
    }
    
    @Override
    public String hashPassword(String plainTextPassword) {
        if (plainTextPassword == null || plainTextPassword.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        
        try {
            String hashedPassword = BCrypt.hashpw(plainTextPassword, BCrypt.gensalt(BCRYPT_ROUNDS));
            logger.fine("Password hashed successfully");
            return hashedPassword;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to hash password", e);
            throw new RuntimeException("Password hashing failed", e);
        }
    }
    
    @Override
    public boolean verifyPassword(String plainTextPassword, String hashedPassword) {
        if (plainTextPassword == null || hashedPassword == null) {
            logger.warning("Password verification failed: null input provided");
            return false;
        }
        
        try {
            boolean isValid = BCrypt.checkpw(plainTextPassword, hashedPassword);
            logger.fine(() -> "Password verification result: " + isValid);
            return isValid;
        } catch (IllegalArgumentException e) {
            // Handle "Invalid salt revision" error
            if (e.getMessage().contains("Invalid salt revision")) {
                logger.warning("Invalid BCrypt salt format detected, trying plain text comparison");
                return plainTextPassword.equals(hashedPassword);
            }
            logger.log(Level.WARNING, "Password verification failed due to exception", e);
            return false;
        } catch (Exception e) {
            logger.log(Level.WARNING, "Password verification failed due to exception", e);
            return false;
        }
    }
    
    @Override
    public boolean authenticate(String username, String plainPassword) {
        if (username == null || username.trim().isEmpty() || plainPassword == null) {
            logger.warning("Authentication failed: invalid input provided");
            return false;
        }
        
        try {
            // Find user by username
            var userOpt = userDAO.findByUsername(username.trim());
            
            if (!userOpt.isPresent()) {
                logger.warning(() -> "Authentication failed: user not found - " + username);
                return false;
            }
            
            var user = userOpt.get();
            
            // Check if user is active
            if (!user.isActive()) {
                logger.warning(() -> "Authentication failed: user account is deactivated - " + username);
                return false;
            }
            
            String storedPassword = user.getPasswordHashed();
            if (storedPassword == null || storedPassword.trim().isEmpty()) {
                logger.warning(() -> "Authentication failed: no password set for user - " + username);
                return false;
            }
            
            // Remove any whitespace from stored password
            storedPassword = storedPassword.trim();
            
            boolean authenticated = false;
            
            // Check if stored password is BCrypt hashed
            if (isHashedPassword(storedPassword)) {
                // Use BCrypt verification
                authenticated = verifyPassword(plainPassword, storedPassword);
            } else {
                // Handle plain text password (legacy) - also try BCrypt as fallback
                if (plainPassword.equals(storedPassword)) {
                    authenticated = true;
                } else {
                    // Try BCrypt as fallback in case detection failed
                    try {
                        authenticated = BCrypt.checkpw(plainPassword, storedPassword);
                    } catch (Exception e) {
                        // BCrypt failed, authentication remains false
                    }
                }
            }
            
            if (authenticated) {
                logger.info(() -> "Authentication successful for user: " + username);
            } else {
                logger.warning(() -> "Authentication failed for user: " + username);
            }
            
            return authenticated;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Authentication error for user: " + username, e);
            return false;
        }
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
    
    /**
     * Determines if a password string is already hashed using BCrypt
     */
    private boolean isHashedPassword(String password) {
        if (password == null || password.length() < 59) {
            return false;
        }
  
        boolean hasValidFormat = (password.startsWith("$2a$") || password.startsWith("$2b$") || 
                                 password.startsWith("$2x$") || password.startsWith("$2y$")) &&
                                password.length() >= 59 && password.length() <= 60;
        
        if (!hasValidFormat && password.startsWith("$2") && password.length() >= 59) {
            return true;
        }
        
        return hasValidFormat;
    }
}