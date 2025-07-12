/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.attendancemanagement.service;

import com.motorph.attendancemanagement.model.Leave;
import org.junit.Test;

import javax.swing.DefaultComboBoxModel;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class LeaveServiceTest {

    private final LeaveService instance = new LeaveService();

    @Test
    public void testGetLeaveTypeComboBoxModel() {
        System.out.println("▶️ getLeaveTypeComboBoxModel");
        DefaultComboBoxModel<String> result = instance.getLeaveTypeComboBoxModel();

        assertNotNull("ComboBox model should not be null", result);
        assertTrue("ComboBox should have items", result.getSize() > 0);
        System.out.println("✅ ComboBox loaded with " + result.getSize() + " leave type(s).");
    }

    @Test
    public void testInsertLeave() {
        System.out.println("▶️ insertLeave");

        Leave leave = new Leave();
        leave.setEmployeeID(10001);        // must exist in DB
        leave.setLeaveType("Vacation");    // should match allowed types
        leave.setStartDate(LocalDate.of(2025, 7, 15));
        leave.setEndDate(LocalDate.of(2025, 7, 17));
        leave.setTotalDays(3.0);
        leave.setRequestId(1);             // must exist in request table

        boolean result = instance.insertLeave(leave);
        assertTrue("Leave should be inserted successfully", result);
        System.out.println("✅ Leave inserted successfully.");
    }

    @Test
    public void testGetLeavesByEmployeeId() {
        System.out.println("▶️ getLeavesByEmployeeId");

        int employeeId = 10001; // Must exist
        List<Leave> result = instance.getLeavesByEmployeeId(employeeId);

        assertNotNull("Result should not be null", result);
        System.out.println("✅ Retrieved " + result.size() + " leave record(s).");
    }

    @Test
    public void testApproveLeave() {
        System.out.println("▶️ approveLeave");

        int requestId = 1;      // Must exist in request table
        boolean isApproved = true;

        boolean result = instance.approveLeave(requestId, isApproved);
        assertTrue("Leave approval status should be updated", result);
        System.out.println("✅ Leave request updated to approved.");
    }

    @Test
    public void testDeleteLeaveById() {
        System.out.println("▶️ deleteLeaveById");

        String leaveId = "1";   // Must exist in DB
        boolean result = instance.deleteLeaveById(leaveId);

        assertTrue("Leave should be deleted successfully", result);
        System.out.println("✅ Leave with ID " + leaveId + " deleted.");
    }
}
