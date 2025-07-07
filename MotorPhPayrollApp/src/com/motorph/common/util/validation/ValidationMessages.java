/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.common.util.validation;

/**
 * Centralized repository for all validation error messages.
 */
public final class ValidationMessages {

    public static final String DATE_RANGE_INVALID = "Start date must be before end date.";
    public static final String PHONE_NUMBER_INVALID = "Invalid phone number format.";
    public static final String SALARY_AMOUNT_INVALID = "Salary amount must be a positive number.";
    public static final String NULL_VALUE_ERROR = "Value cannot be null.";
    public static final String MANDATORY_FIELD_ERROR = "This field is required.";

    // Add more as needed...

    private ValidationMessages() {
        // Prevent instantiation
    }
}
