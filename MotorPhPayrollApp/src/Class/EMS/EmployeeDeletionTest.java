/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Test class to verify that the EmployeeDeletionService correctly deletes
 * all data associated with a specific employee ID from the database.
*/
public class EmployeeDeletionTest {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/payrollsystem_db", "root", "admin")) {
            
            // Instantiate the deletion service with the active DB connection
            EmployeeDeletionService deletionService = new EmployeeDeletionService(conn);
            
            // Test deletion of a specific employee by ID
            deletionService.deleteEmployee(10205);  // Example employee ID to delete

        } catch (Exception e) {
            System.err.println("Error: deletion failed");
        }
    }
}