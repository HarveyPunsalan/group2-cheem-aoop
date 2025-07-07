/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.exception;

/**
 * Exception thrown when a user is not found in the system
 * This is typically used in operations that require an existing user
 * 
 * @author Harvey 
 */

public class UserNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    
    /**
     * Creates a new UserNotFoundException with no message.
     */
    public UserNotFoundException() {
        super("User not found in the system");
    }
    
    /**
     * Creates a new UserNotFoundException with the specified message.
     * 
     * @param message The detail message explaining the exception
     */
    public UserNotFoundException(String message) {
        super(message);
    }
    
    /**
     * Creates a new UserNotFoundException with the specified message and cause.
     * 
     * @param message The detail message explaining the exception
     * @param cause The underlying cause of the exception
     */
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Creates a new UserNotFoundException with the specified cause.
     * 
     * @param cause The underlying cause of the exception
     */
    public UserNotFoundException(Throwable cause) {
        super("User not found in the system", cause);
    }
}
