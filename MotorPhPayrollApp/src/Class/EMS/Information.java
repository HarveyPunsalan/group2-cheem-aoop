/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Class.EMS;

/**
 * Abstract class for employee-related information.
 *
 * This class serves as a superclass for different types of employee-related
 * data such as personal, government, and employment details.
 */
public abstract class Information {
    protected int employeeID;

    /**
     * Constructs an Information object with the specified employee ID.
     *
     * @param employeeID the unique identifier of the employee.
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
     * Sets the employee's ID.
     *
     * @param employeeID the new employee ID
     */
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * Override for customized display (optional).
     */
    @Override
    public String toString() {
        return "Employee ID: " + employeeID;
    }
}