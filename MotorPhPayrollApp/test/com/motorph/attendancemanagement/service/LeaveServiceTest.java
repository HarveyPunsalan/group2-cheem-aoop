/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.attendancemanagement.service;

import com.motorph.attendancemanagement.model.Leave;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import junit.framework.TestCase;

public class LeaveServiceTest extends TestCase {

    private static LeaveService leaveService;
    private static String testLeaveId = null;

    @Override
    protected void setUp() throws Exception {
        leaveService = new LeaveService();

        // Generate a unique leave ID using UUID
        String generatedLeaveId = "TEST-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        Leave testLeave = new Leave();
        testLeave.setLeaveID(generatedLeaveId);      // Unique ID
        testLeave.setEmployeeId(10001);              // Must match existing Employee in DB
        testLeave.setLeaveType("Sick Leave");
        testLeave.setStartDate(LocalDate.of(2025, 8, 1));
        testLeave.setEndDate(LocalDate.of(2025, 8, 3));
        testLeave.setTotalDays(3.0);
        testLeave.setRequestId(1);                   // Must match existing Request ID in DB

        boolean isCreated = leaveService.createLeave(testLeave);
        assertTrue("Initial test leave should be created", isCreated);

        // Confirm leave is present and fetch leave ID for later tests
        List<Leave> leaves = leaveService.getAllLeaves();
        for (Leave l : leaves) {
            if (l.getLeaveID().equals(generatedLeaveId)) {
                testLeaveId = l.getLeaveID();
                break;
            }
        }

        assertNotNull("Test leave ID should be set after creation", testLeaveId);
        assertFalse("Test leave ID should not be empty", testLeaveId.isEmpty());
    }

    public void testUpdateLeave() {
        Leave leaveToUpdate = leaveService.getLeaveById(testLeaveId);
        assertNotNull("Leave to update should exist", leaveToUpdate);

        leaveToUpdate.setLeaveType("Updated Sick Leave");

        boolean isUpdated = leaveService.updateLeave(leaveToUpdate);
        assertTrue("Leave should be updated", isUpdated);

        Leave updatedLeave = leaveService.getLeaveById(testLeaveId);
        assertEquals("Leave type should be updated correctly", "Updated Sick Leave", updatedLeave.getLeaveType());
    }

    public void testDeleteLeave() {
        boolean isDeleted = leaveService.deleteLeave(testLeaveId);
        assertTrue("Leave should be deleted", isDeleted);

        Leave deletedLeave = leaveService.getLeaveById(testLeaveId);
        assertNull("Deleted leave should no longer exist in the database", deletedLeave);
    }
}
