/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.common.util.validation;

import com.motorph.common.model.DateRange;
import static com.motorph.common.util.validation.ValidationMessages.DATE_RANGE_INVALID;

/**
 * Validator implementation for validating a date range.
 */
public class DateRangeValidator implements Validator<DateRange> {

    private final String errorMessage;

    public DateRangeValidator() {
        this(DATE_RANGE_INVALID);
    }

    public DateRangeValidator(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void validate(DateRange range) {
        if (range.getStartDate() != null && range.getEndDate() != null
            && range.getStartDate().isAfter(range.getEndDate())) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}