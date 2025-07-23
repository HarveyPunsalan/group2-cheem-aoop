/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.attendancemanagement.service;

import com.motorph.attendancemanagement.model.Leave;
import org.junit.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class LeaveServiceTest {

    private static LeaveService leaveService;
    private Leave leave;

    @BeforeClass
    public static void setUpClass() {
        leaveService = new LeaveService();
    }

    @Before
    public void setUp() {
        leave = new Leave();
        leave.setEmployeeId(1); // make sure this employee ID exists in your database
        leave.setLeaveType("Vacation");
        leave.setStartDate(LocalDate.of(2025, 7, 25));
        leave.setEndDate(LocalDate.of(2025, 7, 27));
        leave.setTotalDays(3.0);
    }

    @Test
    public void testCreateLeave() {
        boolean result = leaveService.createLeave(leave);
        assertTrue("Leave should be created", result);
        assertTrue("Leave ID should be set after insert", leave.getLeaveRequestId() > 0);
    }

    @Test
    public void testGetLeaveById() {
        leaveService.createLeave(leave);
        assertTrue("Leave ID should be set after insert", leave.getLeaveRequestId() > 0);

        int id = leave.getLeaveRequestId();
        Leave retrieved = leaveService.getLeaveById(id);

        assertNotNull("Retrieved leave should not be null", retrieved);
        assertEquals("Leave type should match", leave.getLeaveType(), retrieved.getLeaveType());
        assertEquals("Total days should match", leave.getTotalDays(), retrieved.getTotalDays(), 0.01);
    }

    @Test
    public void testGetAllLeaves() {
        leaveService.createLeave(leave);
        List<Leave> leaves = leaveService.getAllLeaves();
        assertNotNull("Leave list should not be null", leaves);
        assertTrue("Leave list should contain at least one entry", leaves.size() > 0);
    }

    @Test
    public void testUpdateLeave() {
        leaveService.createLeave(leave);
        assertTrue("Leave ID should be set", leave.getLeaveRequestId() > 0);

        leave.setLeaveType("Sick Leave");
        leave.setTotalDays(2.0);

        boolean result = leaveService.updateLeave(leave);
        assertTrue("Leave should be updated", result);

        Leave updated = leaveService.getLeaveById(leave.getLeaveRequestId());
        assertEquals("Updated leave type should match", "Sick Leave", updated.getLeaveType());
        assertEquals("Updated total days should match", 2.0, updated.getTotalDays(), 0.01);
    }

    @Test
    public void testDeleteLeave() {
        leaveService.createLeave(leave);
        assertTrue("Leave ID should be set", leave.getLeaveRequestId() > 0);

        boolean result = leaveService.deleteLeave(leave.getLeaveRequestId());
        assertTrue("Leave should be deleted", result);

        Leave deleted = leaveService.getLeaveById(leave.getLeaveRequestId());
        assertNull("Deleted leave should not be found", deleted);
    }
}
