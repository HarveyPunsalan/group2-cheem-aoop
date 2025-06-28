/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.employeemanagement.model;

/**
 * Represents a type of allowance (e.g., Phone, Rice, Clothing).
 * 
 * This class is used to define and manage different types of allowances
 * that are provided to employees. Each allowance has
 * an ID, a name, and a description.
 * 
 */
public class Allowance {

    private int allowanceID; // Unique identifier for the allowance type        
    private String allowanceName;  // Name of the allowance 
    private String description; //description providing details about the allowance    

    //Default constructor
    public Allowance() {
    }

    /**
     * Constructor used when creating a new allowance to be inserted into a database.
     * 
     * @param allowanceName the name of the allowance
     * @param description   a description of the allowance
     */
    public Allowance(String allowanceName, String description) {
        this.allowanceName = allowanceName;
        this.description = description;
    }

    /**
     * Constructor used when retrieving an allowance from the database.
     * 
     * @param allowanceID   the ID of the allowance
     * @param allowanceName the name of the allowance
     * @param description   the description of the allowance
     */
    public Allowance(int allowanceID, String allowanceName, String description) {
        this.allowanceID = allowanceID;
        this.allowanceName = allowanceName;
        this.description = description;
    }

    // Getters and setters
    public int getAllowanceID() {
        return allowanceID;
    }

    public void setAllowanceID(int allowanceID) {
        this.allowanceID = allowanceID;
    }

    public String getAllowanceName() {
        return allowanceName;
    }

    public void setAllowanceName(String allowanceName) {
        this.allowanceName = allowanceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns a formatted string representation of the allowance.
     * 
     * @return string containing ID, name, and description
     */
    @Override
    public String toString() {
        return String.format("Allowance [ID: %d, Name: %s, Description: %s]",
                allowanceID,
                allowanceName,
                (description != null ? description : "N/A"));
    }
}