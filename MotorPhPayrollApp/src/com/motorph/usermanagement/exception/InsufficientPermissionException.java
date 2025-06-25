/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.exception;

/**
 * Exception thrown when a user attempts to perform an action they don't have permission for
 * This is used by the authorization system to enforce access control
 * 
 * @author harvey punsalan
 */
public class InsufficientPermissionException extends Exception {
    private static final long serialVersionUID = 1L;
    
    /**
     * Creates a new InsufficientPermissionException with a default message.
     */
    public InsufficientPermissionException() {
        super("User does not have sufficient permissions for this operation");
    }
    
    /**
     * Creates a new InsufficientPermissionException with the specified message.
     * 
     * @param message The detail message explaining the exception
     */
    public InsufficientPermissionException(String message) {
        super(message);
    }
    
    /**
     * Creates a new InsufficientPermissionException with the specified message and cause.
     * 
     * @param message The detail message explaining the exception
     * @param cause The underlying cause of the exception
     */
    public InsufficientPermissionException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Creates a new InsufficientPermissionException with the specified cause.
     * 
     * @param cause The underlying cause of the exception
     */
    public InsufficientPermissionException(Throwable cause) {
        super("User does not have sufficient permissions for this operation", cause);
    }
}
