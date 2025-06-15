/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package databasetest;

import Class.EMS.PersonalInformation;
import com.motorph.database.connection.DatabaseService;
import com.motorph.database.execution.SQLExecutor;
import com.motorph.database.execution.Script;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emmar
 */
public class DatabaseDemo {
    public static void main(String[] args) {        
        // ✅ Create SQLExecutor with the shared connection
        // ✅ Get a connection from DatabaseService
        
        // SELECT demo
        List<PersonalInformation> records;
        try { SQLExecutor executor = new SQLExecutor(DatabaseService.connectToMotorPH());       
        String employeeID = "10001";
            // ✅ Run SELECT query and map results
            records = executor.executeQuery(
                    Script.SELECT_ALL_EMPLOYEES, //SELECT_ALL_EMPLOYEES
                    //List.of("10001"),
                    resultSet -> new PersonalInformation(
                            resultSet.getString("ID"),
                            resultSet.getString("Last Name"),
                            resultSet.getString("First Name"),
                            resultSet.getObject("Birthday", LocalDate.class),
                            resultSet.getString("Phone Number"),
                            resultSet.getString("Email")
                            
                            
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
//        int rowsInserted = executor.executeUpdate(
//            Script.ADD_EMPLOYEE_ADDRESS,
//            List.of(10035, "123", "Main St", "Barangay 1", "City", "Province", "1234", "PH", 1)
//        );
//        System.out.println("Inserted rows: " + rowsInserted);

//        // ✅ Get a connection from DatabaseService
//        Connection connection = DatabaseService.connectToMotorPH();
//
//        // ✅ Create SQLExecutor with the shared connection
//        SQLExecutor executor = new SQLExecutor(connection);
    }
}
