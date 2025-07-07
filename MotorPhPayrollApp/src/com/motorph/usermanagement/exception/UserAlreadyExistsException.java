/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.exception;

/**
 *Exception thrown when attempting to create a user that already exists
 *This is typically used in registration or user creation operations
 * 
 * @author Harvey
 */
public class UserAlreadyExistsException extends Exception{
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new UserAlreadyExistsException with a default message.
     */
    public UserAlreadyExistsException() {
        super("User already exists in the system");
    }

    /**
     * Creates a new UserAlreadyExistsException with the specified message.
     * 
     * @param message The detail message explaining the exception
     */
    public UserAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Creates a new UserAlreadyExistsException with the specified message and cause.
     * 
     * @param message The detail message explaining the exception
     * @param cause The underlying cause of the exception
     */
    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new UserAlreadyExistsException with the specified cause.
     * 
     * @param cause The underlying cause of the exception
     */
    public UserAlreadyExistsException(Throwable cause) {
        super("User already exists in the system", cause);
    }
}
