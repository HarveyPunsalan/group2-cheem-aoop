/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.attendancemanagement.service;

import com.motorph.attendancemanagement.model.Overtime;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class OvertimeServiceTest {

    private static OvertimeService service;
    private static int insertedId;

    @BeforeClass
    public static void setUpClass() {
        service = new OvertimeService();
        insertedId = (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
        // Just in case, ensure there's no existing test record
        service.deleteOvertime(insertedId);
    }

    @AfterClass
    public static void tearDownClass() {
        service.deleteOvertime(insertedId);
    }

    @Test
    public void testAddOvertime() {
        Overtime overtime = new Overtime();
        overtime.setOvertimeId(insertedId);
        overtime.setRequestId(1);
        overtime.setEmployeeId(10001);
        overtime.setDtrId(1);
        overtime.setApproved(false);

        service.addOvertime(overtime);

        List<Overtime> all = service.getAllOvertimes();
        assertFalse(all.isEmpty());

        Overtime latest = all.stream()
                .filter(o -> o.getOvertimeId() == insertedId)
                .findFirst()
                .orElse(null);

        assertNotNull(latest);
        assertEquals(1, latest.getRequestId());
        assertEquals(10001, latest.getEmployeeId());
        assertEquals(1, latest.getDtrId());
        assertFalse(latest.isApproved());
    }

    @Test
    public void testUpdateOvertime() {
        // Make sure the record exists before updating
        Overtime original = new Overtime();
        original.setOvertimeId(insertedId);
        original.setRequestId(1);
        original.setEmployeeId(10001);
        original.setDtrId(1);
        original.setApproved(false);
        service.addOvertime(original);

        Overtime updated = new Overtime();
        updated.setOvertimeId(insertedId);
        updated.setRequestId(2);
        updated.setEmployeeId(10002);
        updated.setDtrId(2);
        updated.setApproved(true);

        service.updateOvertime(updated);

        List<Overtime> all = service.getAllOvertimes();
        Overtime fetched = all.stream()
                .filter(o -> o.getOvertimeId() == insertedId)
                .findFirst()
                .orElse(null);

        assertNotNull(fetched);
        assertEquals(2, fetched.getRequestId());
        assertEquals(10002, fetched.getEmployeeId());
        assertEquals(2, fetched.getDtrId());
        assertTrue(fetched.isApproved());
    }

    @Test
    public void testDeleteOvertime() {
        service.deleteOvertime(insertedId);

        List<Overtime> all = service.getAllOvertimes();
        boolean exists = all.stream()
                .anyMatch(o -> o.getOvertimeId() == insertedId);

        assertFalse(exists);
    }
}
