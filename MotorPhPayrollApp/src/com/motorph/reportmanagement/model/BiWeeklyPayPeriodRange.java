/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.reportmanagement.model;

/**
 *
 * @author 63909
 */
public class BiWeeklyPayPeriodRange {    
    private final String payPeriod; // e.g., "06-01-2024 to 06-15-2024"

    public BiWeeklyPayPeriodRange(String payPeriod) {
        this.payPeriod = payPeriod;
    }

    public String getPayPeriod() {
        return payPeriod;
    }
}