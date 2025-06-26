/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.EMS.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Service class responsible for deleting an employee and all related data 
 * across multiple tables in a single transaction for atomicity.
 */

public class EmployeeDeletionService {

    private final Connection connection;

    public EmployeeDeletionService(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Deletes all records associated with the given employee ID.
     * Ensures that deletions respect foreign key constraints by order.
     *
     * @param employeeId the unique ID of the employee to delete
     * @throws SQLException if any deletion fails, triggering a rollback
     */
    public void deleteEmployee(int employeeId) throws SQLException {
        try {
            connection.setAutoCommit(false);

            deleteAllowances(employeeId);
            deleteSupervisorAssignments(employeeId);
            deleteEmploymentInformation(employeeId);
            deleteGovernmentInformation(employeeId);
            deleteAddress(employeeId);
            deletePersonalInformation(employeeId);

            connection.commit();
            System.out.println("Employee ID " + employeeId + " successfully deleted.");
        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException("Failed to delete employee with ID: " + employeeId, e);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private void deleteAllowances(int employeeId) throws SQLException {
        String sql = "DELETE FROM employee_allowance WHERE employee_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            stmt.executeUpdate();
        }
    }

    private void deleteSupervisorAssignments(int employeeId) throws SQLException {
        String sql = "DELETE FROM supervisor_assignment WHERE employee_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            stmt.executeUpdate();
        }
    }

    private void deleteEmploymentInformation(int employeeId) throws SQLException {
        String sql = "DELETE FROM employee_employment_information WHERE employee_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            stmt.executeUpdate();
        }
    }

    private void deleteGovernmentInformation(int employeeId) throws SQLException {
        String sql = "DELETE FROM employee_government_information WHERE employee_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            stmt.executeUpdate();
        }
    }

    private void deleteAddress(int employeeId) throws SQLException {
        String sql = "DELETE FROM employee_address WHERE employee_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            stmt.executeUpdate();
        }
    }

    private void deletePersonalInformation(int employeeId) throws SQLException {
        String sql = "DELETE FROM employee_personal_information WHERE employee_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            stmt.executeUpdate();
        }
    }
}