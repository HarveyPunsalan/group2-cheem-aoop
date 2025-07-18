/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.attendancemanagement.service;

import com.motorph.attendancemanagement.model.DailyAttendance;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class AttendanceCalculatorTest {

    @Test
    public void testCalculatePayableHours() {
        // List of DailyAttendance records
        List<DailyAttendance> attendanceRecords = Arrays.asList(
            new DailyAttendance("EMP001", 1001, LocalDate.of(2024, 7, 1),
                LocalTime.of(8, 0), LocalTime.of(17, 0), 0.50, 1.00, 8.00),

            new DailyAttendance("EMP001", 1001, LocalDate.of(2024, 7, 2),
                LocalTime.of(8, 0), LocalTime.of(16, 30), 0.25, 0.00, 7.50),

            new DailyAttendance("EMP001", 1001, LocalDate.of(2024, 7, 3),
                LocalTime.of(0, 0), LocalTime.of(0, 0), 0.00, 0.00, 0.00),

            new DailyAttendance("EMP001", 1001, LocalDate.of(2024, 7, 4),
                LocalTime.of(8, 0), LocalTime.of(17, 0), 0.00, 2.00, 8.00)
        );

        // When: Calculate payable hours
        double payableHours = AttendanceCalculator.calculatePayableHours(attendanceRecords);

        // Then: Assert the payable hours (worked - late + overtime)
        assertEquals(25.75, payableHours, 0.01);
    }
}
