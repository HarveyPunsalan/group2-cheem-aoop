/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.employeemanagement.service;

import com.motorph.employeemanagement.model.Employee;
import java.math.BigDecimal;
import java.sql.*;

/**
 * Service class responsible for handling the complete onboarding process of a new employee.
 * <p>
 * This includes the coordinated insertion of employee personal details, address, government IDs,
 * job assignment, salary details, allowances, and supervisor assignment across multiple database tables.
 * All operations are executed within a single database transaction to ensure data integrity.
 * </p>
 */

public class EmployeeCreationService {

    private final Connection connection;
    
    /**
     * Initializes the service with an active JDBC connection.
     *
     * @param connection a valid open JDBC connection
     */
    public EmployeeCreationService(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Orchestrates the entire employee creation process in one atomic transaction.
     *
     * @param employee the Employee object containing all relevant data
     * @throws SQLException if any database operation fails
     */
    public void addEmployee(Employee employee) throws SQLException {
        try {
            connection.setAutoCommit(false); // Begin transaction

            int employeeId = insertPersonalInformation(employee);
            insertAddress(employeeId, employee);
            insertGovernmentInformation(employeeId, employee);
            int jobId = getJobId(employee.getJobTitle(), employee.getDepartment());
            int salaryId = insertSalary(employee);
            insertEmploymentInformation(employeeId, jobId, salaryId, employee);
            insertAllowances(employeeId, employee);
            insertSupervisorAssignment(employeeId, employee.getSupervisorId());

            connection.commit();// Commit transaction if everything succeeds
            
        } catch (SQLException ex) {
            connection.rollback(); // Roll back if any step fails
            throw ex;
        } finally {
            connection.setAutoCommit(true); // Reset auto-commit
        }
    }

    //Helper methods
    private int insertPersonalInformation(Employee employee) throws SQLException {
        String sql = "INSERT INTO employee_personal_information " +
                "(first_name, last_name, birthday, phone_number, email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, employee.getFirstName());
            stmt.setString(2, employee.getLastName());
            stmt.setDate(3, new java.sql.Date(employee.getBirthday().getTime()));
            stmt.setString(4, employee.getPhoneNumber());
            stmt.setString(5, employee.getEmail());

            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // return generated employee_id
                } else {
                    throw new SQLException("Failed to insert employee personal information.");
                }
            }
        }
    }

    private void insertAddress(int employeeId, Employee employee) throws SQLException {
        String sql = "INSERT INTO employee_address " +
                "(employee_id, house_number, street, barangay, municipality, province, postal_code, country, address_type) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            stmt.setString(2, employee.getHouseNumber());
            stmt.setString(3, employee.getStreet());
            stmt.setString(4, employee.getBarangay());
            stmt.setString(5, employee.getMunicipality());
            stmt.setString(6, employee.getProvince());
            stmt.setString(7, employee.getPostalCode());
            stmt.setString(8, employee.getCountry());
            stmt.setInt(9, Integer.parseInt(employee.getAddressType())); 

            stmt.executeUpdate();
        }
    }

    private void insertGovernmentInformation(int employeeId, Employee employee) throws SQLException {
        String sql = "INSERT INTO employee_government_information " +
                "(employee_id, sss_number, philhealth_number, pagibig_number, tax_identification_number) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            stmt.setString(2, employee.getSssNumber());
            stmt.setString(3, employee.getPhilhealthNumber());
            stmt.setString(4, employee.getPagibigNumber());
            stmt.setString(5, employee.getTaxIdentificationNumber());

            stmt.executeUpdate();
        }
    }

    private int getJobId(String jobTitle, String departmentName) throws SQLException {
    int departmentId = getDepartmentId(departmentName); 

    String sql = "SELECT job_id FROM job WHERE Job_title = ? AND department_id = ?";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, jobTitle);
        stmt.setInt(2, departmentId);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("job_id");
            } else {
                throw new SQLException("Job not found for title: " + jobTitle + " in department: " + departmentName);
            }
        }
    }
}

    private int insertSalary(Employee employee) throws SQLException {
        String sql = "INSERT INTO salary (salary_grade, basic_salary, gross_semi_monthly_rate, hourly_rate) " +
                "VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, employee.getSalaryGrade());
            stmt.setBigDecimal(2, employee.getBasicSalary());
            stmt.setBigDecimal(3, employee.getGrossSemiMonthlyRate());
            stmt.setBigDecimal(4, employee.getHourlyRate());

            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // salary_id
                } else {
                    throw new SQLException("Failed to insert salary information.");
                }
            }
        }
    }

    private int getDepartmentId(String departmentName) throws SQLException {
        String sql = "SELECT department_id FROM department WHERE department_name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, departmentName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("department_id");
                } else {
                    throw new SQLException("Department not found: " + departmentName);
                }
            }
        }
    }

    private void insertEmploymentInformation(int employeeId, int jobId, int salaryId, Employee employee) throws SQLException {
        int departmentId = getDepartmentId(employee.getDepartment());

        String sql = "INSERT INTO employee_employment_information " +
                "(employee_id, job_id, salary_id, Employment_type, employment_status, date_hired) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            stmt.setInt(2, jobId);
            stmt.setInt(3, salaryId);
            stmt.setString(4, employee.getEmploymentType());
            stmt.setString(5, employee.getEmploymentStatus());
            stmt.setDate(6, new java.sql.Date(employee.getDateHired().getTime()));

            stmt.executeUpdate();
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

    private void insertAllowance(int employeeId, int allowanceId, BigDecimal amount) throws SQLException {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return; 
        }

        String sql = "INSERT INTO employee_allowance (allowance_id, employee_id, amount, effective_date, created_date, allowance_frequency) " +
                "VALUES (?, ?, ?, CURDATE(), CURDATE(), 'Monthly')";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, allowanceId);
            stmt.setInt(2, employeeId);
            stmt.setBigDecimal(3, amount);

            stmt.executeUpdate();
        }
    }

    private void insertAllowances(int employeeId, Employee employee) throws SQLException {
        int riceAllowanceId = getAllowanceId("Rice Subsidy");
        int phoneAllowanceId = getAllowanceId("Phone Allowance");
        int clothingAllowanceId = getAllowanceId("Clothing Allowance");

        insertAllowance(employeeId, riceAllowanceId, employee.getRiceSubsidy());
        insertAllowance(employeeId, phoneAllowanceId, employee.getPhoneAllowance());
        insertAllowance(employeeId, clothingAllowanceId, employee.getClothingAllowance());
    }
    
        private void insertSupervisorAssignment(int employeeId, int supervisorId) throws SQLException {
        String sql = "INSERT INTO supervisor_assignment (employee_id, supervisor_id, start_date) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            stmt.setInt(2, supervisorId);
            stmt.setDate(3, new java.sql.Date(System.currentTimeMillis())); // Use current date as start_date
            stmt.executeUpdate();
        }
    }
}