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
    ADD_GOVERNMENT_RECORD("INSERT INTO employee_government_information (employee_id, sss_number, philhealth_number, pagibig_number, tax_identification_number, withholding_tax_status) VALUES (?, ?, ?, ?, ?, ?)");

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




