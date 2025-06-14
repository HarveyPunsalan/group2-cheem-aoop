/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

/**
 * Represents a department in the organization.
 * 
 */
public class Department {

    private int departmentID;         
    private String departmentName;    

    public Department() {
    }

    // Constructor for insert
    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    // Constructor for retrieval
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

    @Override
    public String toString() {
        return String.format("Department [ID: %d, Name: %s]", departmentID, departmentName);
    }
}