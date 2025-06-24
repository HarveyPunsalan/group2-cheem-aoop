/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

import com.motorph.database.execution.SQLExecutor;
import com.motorph.database.execution.Script;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;

/**
 * This service class is responsible for retrieving employee data from the database.
 * 
 * It supports:
 * - Retrieving a list of all active employees with basic information
 * - Fetching a complete employee profile by ID
 * 
 * All data retrievals use the provided SQLExecutor utility to run queries and map results.
 */
public class EmployeeRetrievalService {

    private final SQLExecutor executor;

    /**
     * Constructor to initialize the retrieval service with a SQLExecutor.
     * 
     * @param executor the SQLExecutor utility used for database operations
     */
    public EmployeeRetrievalService(SQLExecutor executor) {
        this.executor = executor;
    }

    /**
     * Retrieves a list of all active employees with basic information.
     *
     * @return list of active Employee objects
     * @throws SQLException if a database error occurs during retrieval
     */
    public List<Employee> getActiveEmployees() throws SQLException {
        return executor.executeQuery(
            Script.GET_ACTIVE_EMPLOYEES,
            this::mapBasicEmployee
        );
    }

    /**
     * Retrieves a full employee profile using their unique employee ID.
     *
     * @param id The employee ID to look up
     * @return Employee object containing full profile, or null if not found
     * @throws SQLException if a database error occurs during retrieval
     */
    public Employee getEmployeeById(int id) throws SQLException {
        List<Employee> employees = executor.executeQuery(
            Script.GET_EMPLOYEE_BY_ID,
            List.of(id),
            this::mapFullEmployee
        );

        return employees.isEmpty() ? null : employees.get(0);
    }

    /**
     * Maps basic employee fields from a ResultSet row to an Employee object.
     * This includes only core personal information.
     *
     * @param rs The result set containing employee data
     * @return Mapped Employee object with basic details
     * @throws SQLException if column access fails
     */
    private Employee mapBasicEmployee(ResultSet rs) throws SQLException {
        Employee emp = new Employee();
        emp.setEmployeeId(rs.getInt("employee_id"));
        emp.setFirstName(rs.getString("first_name"));
        emp.setLastName(rs.getString("last_name"));
        emp.setBirthday(rs.getDate("birthday"));
        emp.setPhoneNumber(rs.getString("phone_number"));
        emp.setEmail(rs.getString("email"));
        return emp;
    }

    /**
     * Maps full employee profile from a ResultSet row.
     * 
     * @param rs The result set containing all employee details from a join query
     * @return Fully populated Employee object
     * @throws SQLException if column access fails
     */
    private Employee mapFullEmployee(ResultSet rs) throws SQLException {
        Employee emp = new Employee();

        // Personal Information
        emp.setEmployeeId(rs.getInt("employee_id"));
        emp.setFirstName(rs.getString("first_name"));
        emp.setLastName(rs.getString("last_name"));
        emp.setBirthday(rs.getDate("birthday"));
        emp.setPhoneNumber(rs.getString("phone_number"));
        emp.setEmail(rs.getString("email"));

        // Address
        emp.setHouseNumber(rs.getString("house_number"));
        emp.setStreet(rs.getString("street"));
        emp.setBarangay(rs.getString("barangay"));
        emp.setMunicipality(rs.getString("municipality"));
        emp.setProvince(rs.getString("province"));
        emp.setPostalCode(rs.getString("postal_code"));
        emp.setCountry(rs.getString("country"));
        emp.setAddressType(rs.getString("address_type"));

        // Government Information
        emp.setSssNumber(rs.getString("sss_number"));
        emp.setPhilhealthNumber(rs.getString("philhealth_number"));
        emp.setPagibigNumber(rs.getString("pagibig_number"));
        emp.setTaxIdentificationNumber(rs.getString("tax_identification_number"));

        // Employment Information
        emp.setEmploymentType(rs.getString("employment_type"));
        emp.setEmploymentStatus(rs.getString("employment_status"));
        emp.setDateHired(rs.getDate("date_hired"));
        emp.setJobTitle(rs.getString("Job_title"));
        emp.setDepartment(rs.getString("department_name"));

        // Supervisor
        emp.setSupervisorId(rs.getInt("supervisor_id"));

        // Salary
        emp.setSalaryGrade(rs.getInt("salary_grade"));
        emp.setBasicSalary(rs.getBigDecimal("basic_salary"));
        emp.setGrossSemiMonthlyRate(rs.getBigDecimal("gross_semi_monthly_rate"));
        emp.setHourlyRate(rs.getBigDecimal("hourly_rate"));

        // Allowances 
        loadAllowances(emp);
 
        return emp;
    }
   
    /**
     * Loads all allowance records associated with the employee and maps them
     * into the correct fields in the Employee object.
     *
     * @param emp The employee whose allowances need to be retrieved
     * @throws SQLException if the allowance query fails
     */
    private void loadAllowances(Employee emp) throws SQLException {
        List<AllowanceRecord> allowances = executor.executeQuery(
            Script.GET_EMPLOYEE_ALLOWANCES,
            List.of(emp.getEmployeeId()),
            rs -> new AllowanceRecord(rs.getString("allowance_name"), rs.getBigDecimal("amount"))
        );

        for (AllowanceRecord ar : allowances) {
            switch (ar.name) {
                case "Rice Subsidy" -> emp.setRiceSubsidy(ar.amount);
                case "Phone Allowance" -> emp.setPhoneAllowance(ar.amount);
                case "Clothing Allowance" -> emp.setClothingAllowance(ar.amount);
            }
        }
    }

    /**
     * Helper class for mapping allowance rows into structured records.
     * Used internally during allowance data extraction.
     */
    private static class AllowanceRecord {
        String name;
        BigDecimal amount;

        AllowanceRecord(String name, BigDecimal amount) {
            this.name = name;
            this.amount = amount;
        }
    }
}