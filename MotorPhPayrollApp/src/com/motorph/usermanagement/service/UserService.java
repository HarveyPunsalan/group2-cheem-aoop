/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.motorph.usermanagement.service;

import com.motorph.usermanagement.model.User;
import com.motorph.usermanagement.exception.InvalidCredentialsException;
import com.motorph.usermanagement.exception.DataAccessException;
import java.util.Optional;

/**
 * Simplified interface for user authentication functionality.
 * This interface now focuses only on login-related operations.
 * 
 * @author Harvey
 */
public interface UserService {
    
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
}