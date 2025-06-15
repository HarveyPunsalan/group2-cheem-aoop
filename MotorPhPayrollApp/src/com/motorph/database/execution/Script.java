/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.database.execution;

/**
 *
 * @author 63909
 */

/**
 * Enum representing predefined SQL scripts for the MotorPH Payroll System.
 * Each constant corresponds to a specific SQL statement for querying or updating database records.
 */
public enum Script {
    SELECT_ALL_EMPLOYEES("SELECT * FROM employee_details_view"),
    SELECT_PERSONAL_RECORDS("SELECT * FROM employee_personal_information"),
    FIND_EMPLOYEE_BY_ID("SELECT * FROM employee WHERE employee_id = ?"),

    ADD_PERSONAL_RECORD("INSERT INTO employee_personal_information (first_name, last_name, birthday, phone_number, email) VALUES (?, ?, ?, ?, ?)"),
    ADD_EMPLOYEE_ADDRESS("INSERT INTO employee_address (employee_id, house_number, street, barangay, municipality, province, postal_code, country, address_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"),

    ADD_EMPLOYMENT_RECORD("INSERT INTO employee_employment_information (employee_id, job_id, salary_id, employment_type, employment_status, date_hired) VALUES (?, ?, ?, ?, ?, ?)"),
    ADD_GOVERNMENT_RECORD("INSERT INTO employee_government_information (employee_id, sss_number, philhealth_number, pagibig_number, tax_identification_number, withholding_tax_status) VALUES (?, ?, ?, ?, ?, ?)"),

    GET_ACTIVE_EMPLOYEES("""
        SELECT epi.employee_id AS employee_id, epi.first_name, epi.last_name, 
               epi.birthday, epi.phone_number, epi.email
        FROM employee_personal_information epi
        JOIN employee_employment_information eei ON epi.employee_id = eei.employee_id
        WHERE eei.employment_status = 'Active'
    """),

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
    
    GET_EMPLOYEE_ALLOWANCES(
        "SELECT a.allowance_name, ea.amount " +
        "FROM employee_allowance ea " +
        "JOIN allowance a ON ea.allowance_id = a.allowance_id " +
        "WHERE ea.employee_id = ?"),

    UPDATE_PERSONAL_INFORMATION("""
        UPDATE employee_personal_information
        SET first_name = ?, last_name = ?, birthday = ?, phone_number = ?, email = ?
        WHERE employee_id = ?
    """),

    UPDATE_ADDRESS("""
        UPDATE employee_address
        SET house_number = ?, street = ?, barangay = ?, municipality = ?, province = ?, postal_code = ?, country = ?, address_type = ?
        WHERE employee_id = ?
    """),

    UPDATE_GOVERNMENT_INFORMATION("""
        UPDATE employee_government_information
        SET sss_number = ?, philhealth_number = ?, pagibig_number = ?, tax_identification_number = ?
        WHERE employee_id = ?
    """),

    UPDATE_EMPLOYMENT_INFORMATION("""
        UPDATE employee_employment_information
        SET job_id = ?, employment_type = ?, employment_status = ?, date_hired = ?
        WHERE employee_id = ?
    """),

    UPDATE_SUPERVISOR_ASSIGNMENT("""
        UPDATE supervisor_assignment
        SET supervisor_id = ?
        WHERE employee_id = ? AND (end_date IS NULL OR end_date > NOW())
    """),

    UPDATE_SALARY("""
        UPDATE salary
        SET salary_grade = ?, basic_salary = ?, gross_semi_monthly_rate = ?, hourly_rate = ?
        WHERE salary_id = ?
    """),

    UPDATE_ALLOWANCE("""
        UPDATE employee_allowance
        SET amount = ?
        WHERE employee_id = ? AND allowance_id = ?
    """);
    
    private final String sql;

    /**
     * Constructor to associate a SQL string with each enum constant.
     *
     * @param sql the SQL statement to be assigned
     */
    Script(String sql) {
        this.sql = sql;
    }

    /**
     * Returns the SQL statement associated with the enum constant.
     *
     * @return SQL string
     */
    @Override
    public String toString() {
        return sql;
    }
}





