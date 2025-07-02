/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.exception;

/**
 * Exception thrown when attempting to create a permission that already exists
 * This prevents duplicate permissions from being created in the system
 * 
 * @author Harvey
 */
public class DuplicatePermissionException extends Exception {
    private static final long serialVersionUID = 1L;
    
    /**
     * Creates a new DuplicatePermissionException with a default message.
     */
    public DuplicatePermissionException() {
        super("Permission already exists in the system");
    }
    
    /**
     * Creates a new DuplicatePermissionException with the specified message.
     * 
     * @param message The detail message explaining the exception
     */
    public DuplicatePermissionException(String message) {
        super(message);
    }
    
    /**
     * Creates a new DuplicatePermissionException with the specified message and cause.
     * 
     * @param message The detail message explaining the exception
     * @param cause The underlying cause of the exception
     */
    public DuplicatePermissionException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Creates a new DuplicatePermissionException with the specified cause.
     * 
     * @param cause The underlying cause of the exception
     */
    public DuplicatePermissionException(Throwable cause) {
        super("Permission already exists in the system", cause);
    }
}
