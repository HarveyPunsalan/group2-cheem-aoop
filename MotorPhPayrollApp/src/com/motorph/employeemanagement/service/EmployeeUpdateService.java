/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.employeemanagement.service;

import com.motorph.database.execution.Script;
import com.motorph.employeemanagement.model.Employee;
import java.math.BigDecimal;
import java.sql.*;

/**
 * Service class responsible for updating all employee-related data in the system.
 * 
 * All updates are wrapped in a single transaction to ensure atomicity.
 * If any update fails, the transaction is rolled back to prevent partial updates.
 */
public class EmployeeUpdateService {

    private final Connection connection;

    public EmployeeUpdateService(Connection connection) {
        this.connection = connection;
    }

    /**
     * Performs a full update of an employee's records across all related tables.
     * 
     * @param employee The employee object with updated fields
     * @throws SQLException If any update fails; triggers a rollback
     */
    public void updateEmployee(Employee employee) throws SQLException {
        try {
            connection.setAutoCommit(false);// Start transaction

            updatePersonalInformation(employee);
            updateAddress(employee);
            updateGovernmentInformation(employee);

            int jobId = getJobId(employee.getJobTitle(), employee.getDepartment());
            updateEmploymentInformation(employee, jobId);
            
            updateSalary(employee);
            updateAllowances(employee);

            updateSupervisorAssignment(employee.getEmployeeId(), employee.getSupervisorId());

            connection.commit();// Commit if all updates succeed
            
        } catch (SQLException ex) {
            connection.rollback();// Rollback on any error
            throw ex;
        } finally {
            connection.setAutoCommit(true);// Restore default behavior
        }
    }

    // ========== Individual Table Update Methods ==========
    private void updatePersonalInformation(Employee employee) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(Script.UPDATE_PERSONAL_INFORMATION.toString())) {
            stmt.setString(1, employee.getFirstName());
            stmt.setString(2, employee.getLastName());
            stmt.setDate(3, new java.sql.Date(employee.getBirthday().getTime()));
            stmt.setString(4, employee.getPhoneNumber());
            stmt.setString(5, employee.getEmail());
            stmt.setInt(6, employee.getEmployeeId());

            stmt.executeUpdate();
        }
    }

    private void updateAddress(Employee employee) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(Script.UPDATE_ADDRESS.toString())) {
            stmt.setString(1, employee.getHouseNumber());
            stmt.setString(2, employee.getStreet());
            stmt.setString(3, employee.getBarangay());
            stmt.setString(4, employee.getMunicipality());
            stmt.setString(5, employee.getProvince());
            stmt.setString(6, employee.getPostalCode());
            stmt.setString(7, employee.getCountry());
            stmt.setString(8, employee.getAddressType());
            stmt.setInt(9, employee.getEmployeeId());

            stmt.executeUpdate();
        }
    }

    private void updateGovernmentInformation(Employee employee) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(Script.UPDATE_GOVERNMENT_INFORMATION.toString())) {
            stmt.setString(1, employee.getSssNumber());
            stmt.setString(2, employee.getPhilhealthNumber());
            stmt.setString(3, employee.getPagibigNumber());
            stmt.setString(4, employee.getTaxIdentificationNumber());
            stmt.setInt(5, employee.getEmployeeId());

            stmt.executeUpdate();
        }
    }

    private void updateEmploymentInformation(Employee employee, int jobId) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(Script.UPDATE_EMPLOYMENT_INFORMATION.toString())) {
            stmt.setInt(1, jobId);
            stmt.setString(2, employee.getEmploymentType());
            stmt.setString(3, employee.getEmploymentStatus());
            stmt.setDate(4, new java.sql.Date(employee.getDateHired().getTime()));
            stmt.setInt(5, employee.getEmployeeId());

            stmt.executeUpdate();
        }
    }

    private void updateSalary(Employee employee) throws SQLException {
        // First get salary_id linked to employee_employment_information
        int salaryId = getSalaryIdByEmployeeId(employee.getEmployeeId());

        if (salaryId == -1) {
            throw new SQLException("Salary record not found for employee ID " + employee.getEmployeeId());
        }

        try (PreparedStatement stmt = connection.prepareStatement(Script.UPDATE_SALARY.toString())) {
            stmt.setInt(1, employee.getSalaryGrade());
            stmt.setBigDecimal(2, employee.getBasicSalary());
            stmt.setBigDecimal(3, employee.getGrossSemiMonthlyRate());
            stmt.setBigDecimal(4, employee.getHourlyRate());
            stmt.setInt(5, salaryId);

            stmt.executeUpdate();
        }
    }

    private void updateAllowances(Employee employee) throws SQLException {
        updateAllowanceAmount(employee.getEmployeeId(), "Rice Subsidy", employee.getRiceSubsidy());
        updateAllowanceAmount(employee.getEmployeeId(), "Phone Allowance", employee.getPhoneAllowance());
        updateAllowanceAmount(employee.getEmployeeId(), "Clothing Allowance", employee.getClothingAllowance());
    }

    private void updateAllowanceAmount(int employeeId, String allowanceName, BigDecimal amount) throws SQLException {
        int allowanceId = getAllowanceId(allowanceName);

        // Updates the amount for a specific allowance for the given employee.
        try (PreparedStatement stmt = connection.prepareStatement(Script.UPDATE_ALLOWANCE.toString())) {
            stmt.setBigDecimal(1, amount != null ? amount : BigDecimal.ZERO);
            stmt.setInt(2, employeeId);
            stmt.setInt(3, allowanceId);

            int updatedRows = stmt.executeUpdate();
            if (updatedRows == 0) {
                throw new SQLException("Allowance record for " + allowanceName + " not found for employee " + employeeId);
            }
        }
    }
    
    private void updateSupervisorAssignment(int employeeId, int supervisorId) throws SQLException {
        String sql = "UPDATE supervisor_assignment SET supervisor_id = ? WHERE employee_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, supervisorId);
            stmt.setInt(2, employeeId);
            int updatedRows = stmt.executeUpdate();

            if (updatedRows == 0) {
                // No existing supervisor_assignment, insert new record
                insertSupervisorAssignment(employeeId, supervisorId);
            }
        }
    }

    private void insertSupervisorAssignment(int employeeId, int supervisorId) throws SQLException {
        String sql = "INSERT INTO supervisor_assignment (employee_id, supervisor_id, start_date) VALUES (?, ?, CURDATE())";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            stmt.setInt(2, supervisorId);
            stmt.executeUpdate();
        }
    }
    
    // ========== Utility Methods ==========
    private int getSalaryIdByEmployeeId(int employeeId) throws SQLException {
        String sql = "SELECT salary_id FROM employee_employment_information WHERE employee_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("salary_id");
                }
            }
        }
        return -1;
    }

    private int getJobId(String jobTitle, String departmentName) throws SQLException {
        String sql = "SELECT job_id FROM job j JOIN department d ON j.department_id = d.department_id WHERE j.Job_title = ? AND d.department_name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, jobTitle);
            stmt.setString(2, departmentName);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("job_id");
                } else {
                    throw new SQLException("Job not found for title: " + jobTitle + " in department: " + departmentName);
                }
            }
        }
    }

    private int getAllowanceId(String allowanceName) throws SQLException {
        String sql = "SELECT allowance_id FROM allowance WHERE allowance_name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, allowanceName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("allowance_id");
                } else {
                    throw new SQLException("Allowance not found: " + allowanceName);
                }
            }
        }
    }
    
    /**
     * (Optional) Inserts a new allowance record if missing.
     * Can be used to handle new allowance types dynamically.
     */
    private void insertAllowance(int employeeId, int allowanceId, BigDecimal amount) throws SQLException {
        String sql = "INSERT INTO employee_allowance (allowance_id, employee_id, amount, effective_date, created_date, allowance_frequency) " +
                "VALUES (?, ?, ?, CURDATE(), CURDATE(), 'Monthly')";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, allowanceId);
            stmt.setInt(2, employeeId);
            stmt.setBigDecimal(3, amount);
            stmt.executeUpdate();
        }
    }
}