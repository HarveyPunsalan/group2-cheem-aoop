/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.util;

import java.util.regex.Pattern;
/**
 * Utility class providing common validation methods for user input.
 * Contains methods for validating usernames, passwords, and other user data
 * 
 * @author harvey punsalan
 */

public class ValidationUtils {
    private static final Pattern USERNAME_PATTERN = 
        Pattern.compile("^[a-zA-Z0-9_]{3,20}$");
    
    private static final Pattern EMPLOYEE_ID_PATTERN = 
        Pattern.compile("^[0-9]{1,10}$");
    
    private static final Pattern PERMISSION_NAME_PATTERN = 
        Pattern.compile("^[a-zA-Z0-9\\s\\-_.]{3,100}$");
    
    private static final Pattern ROLE_NAME_PATTERN = 
    Pattern.compile("^[a-zA-Z0-9\\s\\-_.]{2,30}$");
    
    // Private constructor to prevent instantiation of utility class
    private ValidationUtils() {
        throw new IllegalStateException("Utility class should not be instantiated");
    }
    
    /**
     * Validates role name format and length.
     * Role names must be 2-30 characters and contain only letters, 
     * numbers, spaces, hyphens, underscores, and dots.
     * 
     * @param roleName The role name to validate
     * @return true if the role name is valid, false otherwise
     */
    public static boolean isValidRoleName(String roleName) {
    if (roleName == null || roleName.trim().isEmpty()) {
        return false;
    }
    
    String trimmed = roleName.trim();
    
    // Check length constraints (2-30 characters)
    if (trimmed.length() < 2 || trimmed.length() > 30) {
        return false;
    }
    
    // Check for valid characters using the pattern
    return ROLE_NAME_PATTERN.matcher(trimmed).matches();
}
    
    /**
     * Validates if a username meets the required criteria.
     * Username must be 3-20 characters long and contain only letters, numbers, and underscores.
     * 
     * @param username The username to validate
     * @return true if username is valid, false otherwise
     */
    public static boolean isValidUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        return USERNAME_PATTERN.matcher(username.trim()).matches();
    }
    
    /**
     * Validates password strength based on business requirements.
     * Password must be at least 8 characters and contain at least one letter and one number.
     * 
     * @param password The password to validate
     * @return true if password meets strength requirements, false otherwise
     */
    public static boolean isValidPassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        
        boolean hasLetter = false;
        boolean hasNumber = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            }
            
            // Early exit if both conditions are met
            if (hasLetter && hasNumber) {
                return true;
            }
        }
        
        return false;
    }
     
    /**
     * Validates employee ID format.
     * Employee ID should be numeric and between 1-10 digits.
     * 
     * @param employeeId The employee ID to validate
     * @return true if employee ID is valid, false otherwise
     */
    public static boolean isValidEmployeeId(String employeeId) {
        if (employeeId == null || employeeId.trim().isEmpty()) {
            return false;
        }
        return EMPLOYEE_ID_PATTERN.matcher(employeeId.trim()).matches();
    }
    
    /**
     * Validates permission name format and length.
     * Permission names must be 3-100 characters and contain only letters, 
     * numbers, spaces, hyphens, underscores, and dots.
     * 
     * @param permissionName The permission name to validate
     * @return true if the permission name is valid, false otherwise
     */
    public static boolean isValidPermissionName(String permissionName) {
        if (permissionName == null || permissionName.trim().isEmpty()) {
            return false;
        }
        
        String trimmed = permissionName.trim();
        
        // Check length constraints (3-100 characters)
        if (trimmed.length() < 3 || trimmed.length() > 100) {
            return false;
        }
        
        // Check for valid characters using the pattern
        return PERMISSION_NAME_PATTERN.matcher(trimmed).matches();
    }
    
    /**
     * Validates if a string is not null and not empty after trimming.
     * 
     * @param input The string to validate
     * @return true if string is not null and not empty, false otherwise
     */
    public static boolean isNotEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }
    
    /**
     * Validates if a string length is within specified bounds.
     * 
     * @param input The string to validate
     * @param minLength Minimum length allowed
     * @param maxLength Maximum length allowed
     * @return true if length is within bounds, false otherwise
     */
    public static boolean isValidLength(String input, int minLength, int maxLength) {
        if (input == null) {
            return false;
        }
        int length = input.trim().length();
        return length >= minLength && length <= maxLength;
    }
    
    /**
     * Sanitizes input by trimming whitespace and removing potentially harmful characters.
     * This helps prevent basic injection attacks.
     * 
     * @param input The string to sanitize
     * @return Sanitized string, or empty string if input is null
     */
    public static String sanitizeInput(String input) {
        if (input == null) {
            return "";
        }
        
        // Trim whitespace and remove common injection characters
        return input.trim()
                   .replaceAll("[<>\"'%;()&+]", "")
                   .replaceAll("--", "")
                   .replaceAll("/\\*.*?\\*/", "");
    }
    
    /**
     * Validates role ID - should be a positive integer.
     * 
     * @param roleId The role ID to validate
     * @return true if role ID is valid, false otherwise
     */
    public static boolean isValidRoleId(int roleId) {
        return roleId > 0;
    }
    
    /**
     * Checks if password and confirm password match.
     * 
     * @param password The original password
     * @param confirmPassword The confirmation password
     * @return true if passwords match, false otherwise
     */
    public static boolean passwordsMatch(String password, String confirmPassword) {
        if (password == null || confirmPassword == null) {
            return false;
        }
        return password.equals(confirmPassword);
    }
}
