/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.employeemanagement.TestForConsole;

import com.motorph.employeemanagement.model.Employee;
import com.motorph.employeemanagement.service.EmployeeUpdateService;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

/**
 * Test class to demonstrate updating an employee's information using EmployeeUpdateService.
 * 
 * Steps:
 * 1. Establish a connection to the payroll database
 * 2. Create and populate an Employee object with updated data
 * 3. Call the update service to persist changes in all related tables
 * 
 * Pre-requisites:
 * - Employee ID must already exist in the database
 * - Job title and department must exist and be correctly linked
 * - Supervisor ID must also exist as a valid employee
 */
public class EmployeeUpdateTest {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/payrollsystem_db";
        String user = "root";
        String password = "admin";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            EmployeeUpdateService updateService = new EmployeeUpdateService(conn);// Initialize the update service

            // Create and populate an Employee object with updated data
            Employee emp = new Employee();
            emp.setEmployeeId(10052); // Make sure this employee exists
            emp.setFirstName("Juan");
            emp.setLastName("Dela Cruz");
            emp.setBirthday(new Date(87, 5, 15)); 
            emp.setPhoneNumber("09171234567");
            emp.setEmail("gdelacruz@motorph.com");

            emp.setHouseNumber("123");
            emp.setStreet("Rizal St.");
            emp.setBarangay("Barangay 2");
            emp.setMunicipality("Marikina City");
            emp.setProvince("Metro Manila");
            emp.setPostalCode("1200");
            emp.setCountry("Philippines");
            emp.setAddressType("1");

            emp.setSssNumber("3412345678");
            emp.setPhilhealthNumber("123456789018");
            emp.setPagibigNumber("123456789019");
            emp.setTaxIdentificationNumber("123456789011");

            emp.setEmploymentType("Regular");
            emp.setEmploymentStatus("Active");
            emp.setJobTitle("Payroll Manager"); // Must exist in job table
            emp.setDepartment("Payroll");       // Must exist in department table
            emp.setDateHired(new Date(120, 0, 10)); 

            emp.setSalaryGrade(4);
            emp.setBasicSalary(new BigDecimal("35600"));
            emp.setGrossSemiMonthlyRate(new BigDecimal("17500"));
            emp.setHourlyRate(new BigDecimal("200"));

            emp.setRiceSubsidy(new BigDecimal("600"));
            emp.setPhoneAllowance(new BigDecimal("200"));
            emp.setClothingAllowance(new BigDecimal("100"));

            emp.setSupervisorId(10002); // Ensure this is a valid employee ID

            updateService.updateEmployee(emp);

            System.out.println("Employee updated successfully!");

        } catch (SQLException ex) {
            System.err.println("Failed to update employee: " + ex.getMessage());
        }
    }
}