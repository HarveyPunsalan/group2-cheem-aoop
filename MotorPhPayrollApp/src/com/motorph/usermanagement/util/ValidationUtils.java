/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.util;

import java.util.regex.Pattern;
//import java.text.SimpleDateFormat;
//import java.text.ParseException;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.Period;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
/**
 * Utility class providing common validation methods for user input.
 * Contains methods for validating usernames, passwords, and other user data
 * 
 * @author Harvey 
 */

public class ValidationUtils {
    // Username and basic validation patterns
    private static final Pattern USERNAME_PATTERN = 
        Pattern.compile("^[a-zA-Z0-9_]{3,20}$");
    
    private static final Pattern EMPLOYEE_ID_PATTERN = 
        Pattern.compile("^[0-9]{1,10}$");
    
    private static final Pattern PERMISSION_NAME_PATTERN = 
        Pattern.compile("^[a-zA-Z0-9\\s\\-_.]{3,100}$");
    
    private static final Pattern ROLE_NAME_PATTERN = 
        Pattern.compile("^[a-zA-Z0-9\\s\\-_.]{2,30}$");
    
    // Phone number validation patterns (Philippine format)
    private static final Pattern PHONE_PATTERN = 
        Pattern.compile("^(\\+63|0)9\\d{9}$|^(\\+63|0)[2-8]\\d{7,8}$");
    
    // Government ID patterns
    private static final Pattern SSS_PATTERN = 
        Pattern.compile("^\\d{2}-\\d{7}-\\d{1}$|^\\d{10}$");
    
    private static final Pattern TIN_PATTERN = 
        Pattern.compile("^\\d{3}-\\d{3}-\\d{3}-\\d{3}$|^\\d{12}$");
    
    private static final Pattern PHILHEALTH_PATTERN = 
        Pattern.compile("^\\d{2}-\\d{9}-\\d{1}$|^\\d{12}$");
    
    private static final Pattern PAGIBIG_PATTERN = 
        Pattern.compile("^\\d{4}-\\d{4}-\\d{4}$|^\\d{12}$");
    
    // Amount validation pattern (allows decimal values)
    private static final Pattern AMOUNT_PATTERN = 
        Pattern.compile("^\\d+(\\.\\d{1,2})?$");
    
    // Private constructor to prevent instantiation
    private ValidationUtils() {
        throw new IllegalStateException("Utility class should not be instantiated");
    }
    
    /**
     * Validates phone number format (Philippine format).
     * Accepts mobile numbers starting with 09 or +639, and landline numbers.
     * 
     * @param phoneNumber The phone number to validate
     * @return Empty string if valid, error message if invalid
     */
    public static String isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return "Phone number is required.";
        }
        
        String cleanPhone = phoneNumber.trim().replaceAll("[\\s\\-()]", "");
        
        if (!PHONE_PATTERN.matcher(cleanPhone).matches()) {
            return "Invalid phone number format. Use formats like: 09123456789, +639123456789, or landline formats.";
        }
        
        return "";
    }
    
    /**
     * Validates birthday using JDateChooser component.
     * Checks if the date is valid and the person is at least 16 years old.
     * 
     * @param dateChooser The JDateChooser component containing the birthday
     * @param dateFormat The expected date format (not used in this implementation)
     * @return Empty string if valid, error message if invalid
     */
    public static String isValidBirthday(JDateChooser dateChooser, String dateFormat) {
        if (dateChooser == null || dateChooser.getDate() == null) {
            return "Birthday is required.";
        }
        
        Date selectedDate = dateChooser.getDate();
        Date today = new Date();
        
        // Check if birthday is in the future
        if (selectedDate.after(today)) {
            return "Birthday cannot be in the future.";
        }
        
        // Convert to LocalDate for age calculation
        LocalDate birthDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        
        // Calculate age
        Period age = Period.between(birthDate, currentDate);
        int years = age.getYears();
        
        // Check minimum age requirement (16 years old)
        if (years < 16) {
            return "Employee must be at least 16 years old.";
        }
        
        // Check maximum reasonable age (100 years old)
        if (years > 100) {
            return "Invalid birthday. Age cannot exceed 100 years.";
        }
        
        return "";
    }
    
    /**
     * Validates government ID numbers (SSS, PhilHealth, TIN, Pag-IBIG).
     * 
     * @param textField The JTextField containing the ID number (for getting text)
     * @param idType The type of ID being validated
     * @param expectedLength The expected length of the ID number
     * @return Empty string if valid, error message if invalid
     */
    public static String isValidGovernmentIDNumber(JTextField textField, String idType, int expectedLength) {
        if (textField == null) {
            return idType + " field is required.";
        }
        
        String idNumber = textField.getText();
        return isValidGovernmentIDNumber(idNumber, idType, expectedLength);
    }
    
    /**
     * Validates government ID numbers (SSS, PhilHealth, TIN, Pag-IBIG).
     * 
     * @param idNumber The ID number string to validate
     * @param idType The type of ID being validated
     * @param expectedLength The expected length of the ID number
     * @return Empty string if valid, error message if invalid
     */
    public static String isValidGovernmentIDNumber(String idNumber, String idType, int expectedLength) {
        if (idNumber == null || idNumber.trim().isEmpty()) {
            return idType + " number is required.";
        }
        
        String cleanId = idNumber.trim().replaceAll("[\\s\\-]", "");
        
        // Validate based on ID type
        switch (idType.toUpperCase()) {
        case "SSS" -> {
            if (!SSS_PATTERN.matcher(idNumber.trim()).matches()) {
                return "Invalid SSS format. Use format: XX-XXXXXXX-X or 10 digits.";
            }
            if (cleanId.length() != 10) {
                return "SSS number must be exactly 10 digits.";
            }
        }
        case "PHILHEALTH" -> {
            if (!PHILHEALTH_PATTERN.matcher(idNumber.trim()).matches()) {
                return "Invalid PhilHealth format. Use format: XX-XXXXXXXXX-X or 12 digits.";
            }
            if (cleanId.length() != 12) {
                return "PhilHealth number must be exactly 12 digits.";
            }
        }
        case "TIN" -> {
            if (!TIN_PATTERN.matcher(idNumber.trim()).matches()) {
                return "Invalid TIN format. Use format: XXX-XXX-XXX-XXX or 12 digits.";
            }
            if (cleanId.length() != 12) {
                return "TIN number must be exactly 12 digits.";
            }
        }
        case "PAG-IBIG" -> {
            if (!PAGIBIG_PATTERN.matcher(idNumber.trim()).matches()) {
                return "Invalid Pag-IBIG format. Use format: XXXX-XXXX-XXXX or 12 digits.";
            }
            if (cleanId.length() != 12) {
                return "Pag-IBIG number must be exactly 12 digits.";
            }
        }
        default -> {
            if (cleanId.length() != expectedLength) {
                return idType + " number must be exactly " + expectedLength + " digits.";
            }
            if (!cleanId.matches("\\d+")) {
                return idType + " number must contain only digits.";
            }
        }
    }
    return "";
}
    
    /**
     * Validates monetary amounts.
     * Ensures the amount is a valid number and not negative.
     * 
     * @param amountText The amount as a string
     * @param fieldName The name of the field being validated (for error messages)
     * @return Empty string if valid, error message if invalid
     */
    public static String isValidAmount(String amountText, String fieldName) {
        if (amountText == null || amountText.trim().isEmpty()) {
            return fieldName + " is required.";
        }
        
        String cleanAmount = amountText.trim().replace(",", "");
        
        // Check if it matches the amount pattern
        if (!AMOUNT_PATTERN.matcher(cleanAmount).matches()) {
            return fieldName + " must be a valid number (e.g., 1000.00).";
        }
        
        try {
            double amount = Double.parseDouble(cleanAmount);
            
            // Check for negative amounts
            if (amount < 0) {
                return fieldName + " cannot be negative.";
            }
            
            // Check for unreasonably large amounts (optional business rule)
            if (amount > 1000000) {
                return fieldName + " seems unreasonably large. Please verify.";
            }
            
        } catch (NumberFormatException e) {
            return fieldName + " must be a valid number.";
        }
        
        return "";
    }
    
    // ========== EXISTING METHODS FROM ORIGINAL CLASS ==========
    
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
    
    /**
     * Validates date hired using JDateChooser component.
     * Checks if the date is valid and not in the future.
     * 
     * @param dateChooser The JDateChooser component containing the date hired
     * @param dateFormat The expected date format (not used in this implementation)
     * @return Empty string if valid, error message if invalid
      */
    public static String isValidDateHired(JDateChooser dateChooser, String dateFormat) {
        if (dateChooser == null || dateChooser.getDate() == null) {
             return "Date hired is required.";
    }
    
       Date selectedDate = dateChooser.getDate();
       Date today = new Date();
    
        // Check if date hired is in the future
       if (selectedDate.after(today)) {
        return "Date hired cannot be in the future.";
    }
    
    // Convert to LocalDate for additional validation
        LocalDate hiredDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
    
    // Check if hire date is too far in the past 
        LocalDate minimumHireDate = currentDate.minusYears(50); // 50 years ago
        if (hiredDate.isBefore(minimumHireDate)) {
         return "Date hired seems too far in the past. Please verify.";
      }
    return "";
   }
}
