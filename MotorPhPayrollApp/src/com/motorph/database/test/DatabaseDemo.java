/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.database.test;

import com.motorph.database.connection.DatabaseService;
import com.motorph.database.execution.SQLExecutor;
import com.motorph.database.execution.EmployeeScript;
import com.motorph.employeemanagement.model.PersonalInformation;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 63909
 */

/**
 * Demo class to show how to use DatabaseService and SQLExecutor to fetch data.
 */
public class DatabaseDemo {
    public static void main(String[] args) {        
        // ✅ Create SQLExecutor with the shared connection
        // ✅ Get a connection from DatabaseService
        SQLExecutor executor = new SQLExecutor(DatabaseService.connectToMotorPH());       
        String employeeID = "10001";
        // SELECT demo
        List<PersonalInformation> records;
        try {
            // ✅ Run SELECT query and map results
            records = executor.executeQuery(EmployeeScript.SELECT_ALL_EMPLOYEES, //SELECT_ALL_EMPLOYEES
                    //List.of("10001"),
                    resultSet -> new PersonalInformation(
                            resultSet.getInt("employee_id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getObject("birthday", LocalDate.class),
                            resultSet.getString("phone_number"),
                            resultSet.getString("email")
                    )
            );
            
            // ✅ Print results
            for (PersonalInformation pi : records) {
                System.out.println("Employee ID: " + pi.getEmployeeID());
                System.out.println("First Name: " + pi.getFirstName());
                System.out.println("Last Name: " + pi.getLastName());
                System.out.println("Birthday: " + pi.getBirthday());
                System.out.println("Phone Number: " + pi.getPhoneNumber());
                System.out.println("Email: " + pi.getEmail());
                System.out.println("---------------");
            }
            
        } catch (SQLException e) {
            Logger.getLogger(DatabaseDemo.class.getName()).log(Level.SEVERE, "Error executing query", e);
        }
        

        // INSERT demo
        int rowsInserted = executor.executeUpdate(EmployeeScript.ADD_EMPLOYEE_ADDRESS,
            List.of(10035, "123", "Main St", "Barangay 1", "City", "Province", "1234", "PH", 1)
        );
        System.out.println("Inserted rows: " + rowsInserted);

//        // ✅ Get a connection from DatabaseService
//        Connection connection = DatabaseService.connectToMotorPH();
//
//        // ✅ Create SQLExecutor with the shared connection
//        SQLExecutor executor = new SQLExecutor(connection);
    }
}
