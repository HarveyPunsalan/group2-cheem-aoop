/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.exception;

/**
 * Exception thrown when attempting to create a user with a username that already exists
 * This helps maintain data integrity by preventing duplicate usernames in the system
 * 
 * @author Harvey
 */
public class DuplicateUserException extends Exception {
    private static final long serialVersionUID = 1L;
    
    /**
     * Creates a new DuplicateUserException with a default message.
     */
    public DuplicateUserException() {
        super("Username already exists in the system");
    }
    
    /**
     * Creates a new DuplicateUserException with the specified message.
     * 
     * @param message The detail message explaining the exception
     */
    public DuplicateUserException(String message) {
        super(message);
    }
    
    /**
     * Creates a new DuplicateUserException with the specified message and cause.
     * 
     * @param message The detail message explaining the exception
     * @param cause The underlying cause of the exception
     */
    public DuplicateUserException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Creates a new DuplicateUserException with the specified cause.
     * 
     * @param cause The underlying cause of the exception
     */
    public DuplicateUserException(Throwable cause) {
        super("Username already exists in the system", cause);
    }
}
