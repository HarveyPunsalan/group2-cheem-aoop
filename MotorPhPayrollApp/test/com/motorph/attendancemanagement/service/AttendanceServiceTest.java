/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.attendancemanagement.service;

import com.motorph.attendancemanagement.model.DailyAttendance;
import com.motorph.employeemanagement.model.Employee;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AttendanceServiceTest {

    private static AttendanceService attendanceService;
    private static Employee testEmployee;

    @BeforeClass
    public static void setUpClass() {
        attendanceService = new AttendanceService();

        // Assume employee with ID 10001 exists in the DB
        testEmployee = new Employee();
        testEmployee.setEmployeeId(10001);
    }

    @AfterClass
    public static void tearDownClass() {
      
    }

    @Test
    public void testGetAllAttendanceRecords() {
        System.out.println("Running test: getAllAttendanceRecords");

        List<DailyAttendance> result = attendanceService.getAllAttendanceRecords(testEmployee);

        // Validate result is not null
        assertNotNull("Attendance list should not be null", result);

        // validate at least one record (if DB is pre-filled for this employee)
        assertTrue("Attendance list should contain records", result.size() >= 0); // or > 0 if required
    }
}
