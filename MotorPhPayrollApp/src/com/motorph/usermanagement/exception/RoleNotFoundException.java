/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.exception;

/**
 * Exception thrown when a role is not found in the system
 * This used in role management operations that require an existing role
 * 
 * @author harvey punsalan
 */
public class RoleNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    
    /**
     * Creates a new RoleNotFoundException with a default message.
     */
    public RoleNotFoundException() {
        super("Role not found in the system");
    }
    
    /**
     * Creates a new RoleNotFoundException with the specified message.
     * 
     * @param message The detail message explaining the exception
     */
    public RoleNotFoundException(String message) {
        super(message);
    }
    
    /**
     * Creates a new RoleNotFoundException with the specified message and cause.
     * 
     * @param message The detail message explaining the exception
     * @param cause The underlying cause of the exception
     */
    public RoleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Creates a new RoleNotFoundException with the specified cause.
     * 
     * @param cause The underlying cause of the exception
     */
    public RoleNotFoundException(Throwable cause) {
        super("Role not found in the system", cause);
    }
}
