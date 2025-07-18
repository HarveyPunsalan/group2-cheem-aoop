/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.attendancemanagement.service;

import com.motorph.attendancemanagement.model.Request;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import org.junit.*;

import static org.junit.Assert.*;

public class RequestServiceTest {

    private static RequestService requestService;
    private static int insertedId;

    @BeforeClass
    public static void setUpClass() {
        requestService = new RequestService();
    }

    @AfterClass
    public static void tearDownClass() {
        // Clean up after tests (delete inserted test data if needed)
        if (insertedId != 0) {
            requestService.deleteRequest(insertedId);
        }
    }

    @Test
    public void testAddRequest() {
        Request request = new Request();
        request.setEmployeeId(10001);
        request.setRequestDate(Date.valueOf("2025-07-12"));
        request.setReason("JUnit Test Request");
        request.setRequestStatus("PENDING");
        request.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        request.setProcessedBy(0); // Assuming not processed yet
        request.setProcessedDate(null);
        request.setRemarks("Test insert");

        boolean result = requestService.addRequest(request);
        assertTrue("Request should be added successfully", result);

        // Try to retrieve the latest inserted record
        List<Request> allRequests = requestService.getAllRequests();
        Request lastRequest = allRequests.get(allRequests.size() - 1);
        insertedId = lastRequest.getRequestId(); // Save for later update/delete tests

        assertEquals("JUnit Test Request", lastRequest.getReason());
    }

    @Test
    public void testGetRequestById() {
        if (insertedId == 0) testAddRequest(); // Ensure test data is inserted
        Request result = requestService.getRequestById(insertedId);
        assertNotNull("Should retrieve request by ID", result);
        assertEquals(insertedId, result.getRequestId());
    }

    @Test
    public void testGetAllRequests() {
        List<Request> result = requestService.getAllRequests();
        assertNotNull("Request list should not be null", result);
        assertTrue("There should be at least one request", result.size() > 0);
    }

    @Test
    public void testUpdateRequest() {
        if (insertedId == 0) testAddRequest();

        Request toUpdate = requestService.getRequestById(insertedId);
        toUpdate.setRequestStatus("APPROVED");
        toUpdate.setRemarks("Updated by test");

        boolean result = requestService.updateRequest(toUpdate);
        assertTrue("Request should be updated successfully", result);

        Request updated = requestService.getRequestById(insertedId);
        assertEquals("APPROVED", updated.getRequestStatus());
        assertEquals("Updated by test", updated.getRemarks());
    }

    @Test
    public void testDeleteRequest() {
        if (insertedId == 0) testAddRequest();

        boolean result = requestService.deleteRequest(insertedId);
        assertTrue("Request should be deleted", result);

        Request deleted = requestService.getRequestById(insertedId);
        assertNull("Deleted request should return null", deleted);

        insertedId = 0; // Mark as deleted
    }
}
