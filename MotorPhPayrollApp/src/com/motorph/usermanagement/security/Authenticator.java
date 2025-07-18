/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.motorph.usermanagement.security;

/**
 * Handles password hashing and verification for secure user authentication.
 * 
 * @author Harvey
 */
public interface Authenticator {
    /**
     * Hashes a plain text password using a secure hashing algorithm.
     * 
     * @param plainTextPassword Plain text password to hash
     * @return Hashed password string
     * @throws IllegalArgumentException if password is null or empty
     */
    String hashPassword(String plainTextPassword);
    
    /**
     * Verifies a plain text password against a stored hash.
     * 
     * @param plainTextPassword Plain text password to verify
     * @param hashedPassword Stored hashed password
     * @return true if password matches hash
     * @throws IllegalArgumentException if parameters are null or empty
     */
    boolean verifyPassword(String plainTextPassword, String hashedPassword);
    
    /**
     * Authenticates a user by username and password.
     * 
     * @param username User's login username
     * @param plainPassword User's plain text password
     * @return true if authentication successful
     */
    boolean authenticate(String username, String plainPassword);
    
    /**
     * Checks if a password meets security requirements.
     * 
     * @param password Password to validate
     * @return true if password meets requirements
     */
    boolean isPasswordSecure(String password);
}
