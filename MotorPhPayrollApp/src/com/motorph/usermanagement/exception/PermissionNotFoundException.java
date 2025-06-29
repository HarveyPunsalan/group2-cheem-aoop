/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.exception;

/**
 * Exception thrown when a permission is not found in the system
 * This is used in permission management operations that require an existing permission
 * 
 * @author Harvey
 */
public class PermissionNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    
    /**
     * Creates a new PermissionNotFoundException with a default message.
     */
    public PermissionNotFoundException() {
        super("Permission not found in the system");
    }
    
    /**
     * Creates a new PermissionNotFoundException with the specified message.
     * 
     * @param message The detail message explaining the exception
     */
    public PermissionNotFoundException(String message) {
        super(message);
    }
    
    /**
     * Creates a new PermissionNotFoundException with the specified message and cause.
     * 
     * @param message The detail message explaining the exception
     * @param cause The underlying cause of the exception
     */
    public PermissionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Creates a new PermissionNotFoundException with the specified cause.
     * 
     * @param cause The underlying cause of the exception
     */
    public PermissionNotFoundException(Throwable cause) {
        super("Permission not found in the system", cause);
    }
}
