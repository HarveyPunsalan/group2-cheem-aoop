/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.exception;

/**
 * Exception thrown when user provides invalid login credentials
 * This is used during authentication to indicate username/password mismatch
 * 
 * @author harvey punsalan
 */

public class InvalidCredentialsException extends Exception {
    private static final long serialVersionUID = 1L;
    
    /**
     * Creates a new InvalidCredentialsException with a default message.
     */
    public InvalidCredentialsException() {
        super("Invalid username or password provided");
    }
    
    /**
     * Creates a new InvalidCredentialsException with the specified message.
     * 
     * @param message The detail message explaining the exception
     */
    public InvalidCredentialsException(String message) {
        super(message);
    }
    
    /**
     * Creates a new InvalidCredentialsException with the specified message and cause.
     * 
     * @param message The detail message explaining the exception
     * @param cause The underlying cause of the exception
     */
    public InvalidCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Creates a new InvalidCredentialsException with the specified cause.
     * 
     * @param cause The underlying cause of the exception
     */
    public InvalidCredentialsException(Throwable cause) {
        super("Invalid username or password provided", cause);
    }
}
    