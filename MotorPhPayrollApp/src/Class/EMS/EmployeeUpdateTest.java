/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

/**
 * Test class to demonstrate updating an employee's information using EmployeeUpdateService.
 * 
 * Connects to the database, creates an Employee object with updated data,
 * and calls the update service to persist the changes.
 */

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

public class EmployeeUpdateTest {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/payrollsystem_db";
        String user = "root";
        String password = "admin";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            EmployeeUpdateService updateService = new EmployeeUpdateService(conn);

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

            emp.setSupervisorId(10002); // Make sure this supervisor exists

            updateService.updateEmployee(emp);

            System.out.println("Employee updated successfully!");

        } catch (SQLException ex) {
            System.err.println("Failed to update employee: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}