/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.database.execution;

import com.motorph.database.execution.script.Script;

/**
 * Enum representing predefined SQL scripts for the MotorPH Payroll System.
 * Each constant corresponds to a specific SQL statement for querying or updating database records.
 */
public enum EmployeeScript implements Script {
    /** Retrieves all employee data from a consolidated view. */
    SELECT_ALL_EMPLOYEES("SELECT * FROM employee_details_view"),
    
    /** Selects all personal records from the personal information table. */
    SELECT_PERSONAL_RECORDS("SELECT * FROM employee_personal_information"),
    
    /** Finds a single employee by their employee ID from the employee table. */
    FIND_EMPLOYEE_BY_ID("SELECT * FROM employee WHERE employee_id = ?"),

    ADD_PERSONAL_RECORD("INSERT INTO employee_personal_information (first_name, last_name, birthday, phone_number, email) VALUES (?, ?, ?, ?, ?)"),
    
    /** Adds a new address for an employee. */
    ADD_EMPLOYEE_ADDRESS("INSERT INTO employee_address (employee_id, house_number, street, barangay, municipality, province, postal_code, country, address_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"),
    
    /** Adds new employment information including job and salary reference. */
    ADD_EMPLOYMENT_RECORD("INSERT INTO employee_employment_information (employee_id, job_id, salary_id, employment_type, employment_status, date_hired) VALUES (?, ?, ?, ?, ?, ?)"),
    
    /** Inserts government-related identifiers for an employee. */
    ADD_GOVERNMENT_RECORD("INSERT INTO employee_government_information (employee_id, sss_number, philhealth_number, pagibig_number, tax_identification_number, withholding_tax_status) VALUES (?, ?, ?, ?, ?, ?)"),

    /** Retrieves active employees by joining personal and employment information. */
    GET_ACTIVE_EMPLOYEES("""
        SELECT epi.employee_id AS employee_id, epi.first_name, epi.last_name, 
               epi.birthday, epi.phone_number, epi.email
        FROM employee_personal_information epi
        JOIN employee_employment_information eei ON epi.employee_id = eei.employee_id
                WHERE eei.employment_status = 'Active'
    """),

    
    /**
     * Gets detailed information about an employee by joining multiple tables:
     * personal, address, government, employment, job, department, supervisor, and salary.
     */
    GET_EMPLOYEE_BY_ID(
        "SELECT epi.*, ea.house_number, ea.street, ea.barangay, ea.municipality, ea.province, ea.postal_code, ea.country, ea.address_type, " +
        "egi.sss_number, egi.philhealth_number, egi.pagibig_number, egi.tax_identification_number, " +
        "eei.employment_type, eei.employment_status, eei.date_hired, " +
        "j.Job_title, d.department_name, s.supervisor_id, " +
        "sal.salary_grade, sal.basic_salary, sal.gross_semi_monthly_rate, sal.hourly_rate " +
        "FROM employee_personal_information epi " +
        "LEFT JOIN employee_address ea ON epi.employee_id = ea.employee_id " +
        "LEFT JOIN employee_government_information egi ON epi.employee_id = egi.employee_id " +
        "LEFT JOIN employee_employment_information eei ON epi.employee_id = eei.employee_id " +
        "LEFT JOIN job j ON eei.job_id = j.job_id " +
        "LEFT JOIN department d ON j.department_id = d.department_id " +
        "LEFT JOIN supervisor_assignment s ON epi.employee_id = s.employee_id AND (s.end_date IS NULL OR s.end_date > NOW()) " +
        "LEFT JOIN salary sal ON eei.salary_id = sal.salary_id " +
        "WHERE epi.employee_id = ?"
    ),
    
    /** Retrieves allowance name and amount for a specific employee. */
    GET_EMPLOYEE_ALLOWANCES(
        "SELECT a.allowance_name, ea.monthly_amount " +
        "FROM employee_default_allowance ea " +
        "JOIN allowance a ON ea.allowance_id = a.allowance_id " +
        "WHERE ea.employee_id = ?"),

    /** Updates an employee's personal information. */
    UPDATE_PERSONAL_INFORMATION("""
        UPDATE employee_personal_information
        SET first_name = ?, last_name = ?, birthday = ?, phone_number = ?, email = ?
        WHERE employee_id = ?
    """),

    /** Updates an employee's address details. */
    UPDATE_ADDRESS("""
        UPDATE employee_address
        SET house_number = ?, street = ?, barangay = ?, municipality = ?, province = ?, postal_code = ?, country = ?, address_type = ?
        WHERE employee_id = ?
    """),

    /** Updates government-issued identification numbers for an employee. */
    UPDATE_GOVERNMENT_INFORMATION("""
        UPDATE employee_government_information
        SET sss_number = ?, philhealth_number = ?, pagibig_number = ?, tax_identification_number = ?
        WHERE employee_id = ?
    """),

    /** Updates employment details such as job, status, and date hired. */
    UPDATE_EMPLOYMENT_INFORMATION("""
        UPDATE employee_employment_information
        SET job_id = ?, employment_type = ?, employment_status = ?, date_hired = ?
        WHERE employee_id = ?
    """),

    /** Updates an active supervisor assignment for an employee. */
    UPDATE_SUPERVISOR_ASSIGNMENT("""
        UPDATE supervisor_assignment
        SET supervisor_id = ?
        WHERE employee_id = ? AND (end_date IS NULL OR end_date > NOW())
    """),

    /** Updates the salary details for a salary record. */
    UPDATE_SALARY("""
        UPDATE salary
        SET salary_grade = ?, basic_salary = ?, gross_semi_monthly_rate = ?, hourly_rate = ?
        WHERE salary_id = ?
    """),

    /** Updates the allowance amount for a specific employee and allowance type. */
    UPDATE_ALLOWANCE("""
        UPDATE employee_default_allowance
        SET monthly_amount = ?
        WHERE employee_id = ? AND allowance_id = ?
    """);
    
    // ========================== ENUM INTERNALS ==========================
    private final String sql;

    /**
     * Constructor to associate a SQL string with each Enum constant.
     *
     * @param sql the SQL statement to be assigned
     */
    EmployeeScript(String sql) {
        this.sql = sql;
    }

    /**
     * Returns the SQL statement associated with the Enum constant.
     *
     * @return SQL string
     */
    @Override
    public String getQuery() {
        return sql;
    }
}