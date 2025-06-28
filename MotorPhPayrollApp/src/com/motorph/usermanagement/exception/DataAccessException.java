/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.exception;

/**
 * Exception that wraps database-related exceptions like SQLException
 * This provides a consistent way to handle data access problems across the application
 * 
 * @author harvey punsalan
 */
public class DataAccessException extends Exception {
    private static final long serialVersionUID = 1L;
    
    /**
     * Creates a new DataAccessException with a default message.
     */
    public DataAccessException() {
        super("An error occurred while accessing the database");
    }
    
    /**
     * Creates a new DataAccessException with the specified message.
     * 
     * @param message The detail message explaining the exception
     */
    public DataAccessException(String message) {
        super(message);
    }
    
    /**
     * Creates a new DataAccessException with the specified message and cause.
     * This is commonly used to wrap SQLException and other database exceptions.
     * 
     * @param message The detail message explaining the exception
     * @param cause The underlying cause of the exception (typically SQLException)
     */
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Creates a new DataAccessException with the specified cause.
     * 
     * @param cause The underlying cause of the exception
     */
    public DataAccessException(Throwable cause) {
        super("An error occurred while accessing the database", cause);
    }
}
