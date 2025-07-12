/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.attendancemanagement.service;

import com.motorph.attendancemanagement.model.Overtime;
import com.motorph.database.connection.DatabaseService;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class OvertimeServiceTest {

    private final OvertimeService instance = new OvertimeService();

    // Helper method to get a valid request_id
    private int getValidRequestId() {
        try (Connection conn = DatabaseService.connectToMotorPH();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT request_id FROM request LIMIT 1")) {
            if (rs.next()) return rs.getInt("request_id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1; // fallback
    }

    @Test
    public void testGetOvertimeByEmployeeId() {
        int employeeId = 10001; // Must exist
        List<Overtime> result = instance.getOvertimeByEmployeeId(employeeId);

        assertNotNull("Result should not be null", result);
        System.out.println("✅ Retrieved " + result.size() + " overtime record(s).");
    }

    @Test
    public void testSaveOvertime() {
        Overtime overtime = new Overtime();
        overtime.setEmployeeID(10001); // Must exist
        overtime.setRequestId(getValidRequestId()); // Must exist
        overtime.setDtrId(1); // Must exist

        overtime.setDate(LocalDate.now());
        overtime.setStartTime(LocalTime.of(18, 0));
        overtime.setEndTime(LocalTime.of(20, 0));
        overtime.setTotalHours(2.0);
        overtime.setPayableHours(1.5);
        overtime.setApproved(false);

        boolean result = instance.saveOvertime(overtime);
        assertTrue("Overtime should be saved successfully", result);
        System.out.println("✅ Overtime inserted successfully.");
    }

    @Test
    public void testUpdateOvertimeRequest() {
        int employeeId = 10001;
        int requestId = getValidRequestId();
        int dtrId = 1;

        // Save a dummy record first
        Overtime overtime = new Overtime();
        overtime.setEmployeeID(employeeId);
        overtime.setRequestId(requestId);
        overtime.setDtrId(dtrId);
        instance.saveOvertime(overtime);

        // Get the last inserted overtime_id
        List<Overtime> overtimeList = instance.getOvertimeByEmployeeId(employeeId);
        String latestOvertimeId = overtimeList.get(overtimeList.size() - 1).getID();

        // Update the request ID
        boolean result = instance.updateOvertimeRequest(latestOvertimeId, requestId);
        assertTrue("Overtime request ID should be updated", result);
        System.out.println("✅ Overtime request updated for ID: " + latestOvertimeId);
    }

    @Test
    public void testDeleteOvertimeById() {
        int employeeId = 10001;
        int requestId = getValidRequestId();
        int dtrId = 1;

        // Insert a dummy record first
        Overtime overtime = new Overtime();
        overtime.setEmployeeID(employeeId);
        overtime.setRequestId(requestId);
        overtime.setDtrId(dtrId);
        instance.saveOvertime(overtime);

        // Get the latest inserted overtime record
        List<Overtime> overtimeList = instance.getOvertimeByEmployeeId(employeeId);
        String latestOvertimeId = overtimeList.get(overtimeList.size() - 1).getID();

        // Delete it
        boolean result = instance.deleteOvertimeById(latestOvertimeId);
        assertTrue("Overtime should be deleted successfully", result);
        System.out.println("✅ Overtime deleted with ID: " + latestOvertimeId);
    }
}
