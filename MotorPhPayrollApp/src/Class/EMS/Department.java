/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

/**
 * Represents a department within the organization.
 * 
 * Each department has a unique ID and a name.
 * 
 */
public class Department {

    private int departmentID; // Unique identifier for the department        
    private String departmentName;  // Name of the department  

    //Default constructor
    public Department() {
    }

    /**
     * Constructor for inserting a new department (without ID).
     * 
     * @param departmentName the name of the department
     */
    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * Constructor for retrieving a department from the database.
     * 
     * @param departmentID   the department's unique ID
     * @param departmentName the name of the department
     */
    public Department(int departmentID, String departmentName) {
        this.departmentID = departmentID;
        this.departmentName = departmentName;
    }

    // Getters and setters
    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * Returns a string representation of the department.
     * 
     * @return formatted string with department ID and name
     */
    @Override
    public String toString() {
        return String.format("Department [ID: %d, Name: %s]", departmentID, departmentName);
    }
}