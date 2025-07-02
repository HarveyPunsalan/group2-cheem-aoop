/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.motorph.employeemanagement.model;

/**
 * Abstract base class for employee-related information records.
 *
 * This class acts as a superclass for various employee information models
 * such as personal, government, employment, and address details. It ensures
 * that each subclass is associated with a specific employee ID.
 */
public abstract class Information {
    protected int employeeID; // The unique identifier for the employee this information belongs to

    /**
     * Constructs an Information object for the specified employee.
     *
     * @param employeeID the ID of the employee associated with this information
     */
    public Information(int employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * Gets the employee's ID.
     *
     * @return the employee ID.
     */
    public int getEmployeeID() {
        return employeeID;
    }

    /**
     * Sets the employee ID.
     *
     * @param employeeID the new employee ID to associate with this information
     */
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
    
    /**
     * Retrieves the object's information as an array of strings.
     *
     * <p>Subclasses must implement this method to provide a formatted array of strings
     * representing the object's details.</p>
     *
     * @return an array of strings containing the object's information.
     */ 
    public abstract String[] getInformation();

    /**
     * Returns a string representation of the object for logging or display.
     *
     * @return a string containing the employee ID
     */
    @Override
    public String toString() {
        return "Employee ID: " + employeeID;
    }
}