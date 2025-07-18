/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.security;

import org.mindrot.jbcrypt.BCrypt;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Password encoder using BCrypt for secure password hashing.
 * Simplified to work with the authentication system.
 * 
 * @author Harvey 
 */
public class PasswordEncoder {
    private static final Logger logger = Logger.getLogger(PasswordEncoder.class.getName());
    private static final int BCRYPT_ROUNDS = 12;
    
    /**
     * Encodes a plain text password using BCrypt
     * 
     * @param plainPassword the password provided by the user in plain text
     * @return the BCrypt hashed password
     */
    public String encode(String plainPassword) {
        if (plainPassword == null || plainPassword.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        
        try {
            String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt(BCRYPT_ROUNDS));
            logger.fine("Password encoded successfully using BCrypt");
            return hashedPassword;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Password encoding failed", e);
            throw new RuntimeException("Password encoding failed", e);
        }
    }
    
    /**
     * Verifies if a plain password matches the encoded password
     * 
     * @param plainPassword the password entered by the user in plain text
     * @param encodedPassword the previously encoded password from storage
     * @return true if the password matches, false otherwise
     */
    public boolean matches(String plainPassword, String encodedPassword) {
        if (plainPassword == null || encodedPassword == null) {
            logger.warning("Password verification failed: null input provided");
            return false;
        }
        
        try {
            boolean matches = BCrypt.checkpw(plainPassword, encodedPassword);
            logger.fine(() -> "Password verification completed, result: " + matches);
            return matches;
        } catch (Exception e) {
            logger.log(Level.WARNING, "Password verification failed due to exception", e);
            return false;
        }
    }
    
    /**
     * Checks if a password string is BCrypt encoded
     * 
     * @param password the password string to check
     * @return true if the password appears to be BCrypt encoded
     */
    public boolean isEncoded(String password) {
        if (password == null || password.length() < 7) {
            return false;
        }
        
        // BCrypt hashes have the format $2[abxy]$rounds$salt+hash
        return password.startsWith("$2") && password.length() == 60;
    }
}