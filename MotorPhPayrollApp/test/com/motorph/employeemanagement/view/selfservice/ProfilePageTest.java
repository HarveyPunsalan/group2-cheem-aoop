/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.employeemanagement.view.selfservice;

import com.motorph.employeemanagement.model.Employee;
import com.motorph.usermanagement.model.NonAdmin;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;

public class ProfilePageTest {

    private ProfilePage profilePage;
    private Employee testEmployee;

    @Before
    public void setUp() throws Exception {
        // Create a user to initialize ProfilePage, using NonAdmin with valid credentials
        NonAdmin user = new NonAdmin("testuser", "password", 1, 1);

        // Set up an employee with valid personal info for testing
        testEmployee = new Employee();
        testEmployee.setEmployeeId(1);
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-01"));
        testEmployee.setEmail("john.doe@example.com");
        testEmployee.setPhoneNumber("1234567890");
        testEmployee.setHouseNumber("123");
        testEmployee.setStreet("Main St");
        testEmployee.setBarangay("Barangay 1");
        testEmployee.setMunicipality("Manila");
        testEmployee.setProvince("Metro Manila");
        testEmployee.setPostalCode("1000");
        testEmployee.setCountry("Philippines");
        testEmployee.setAddressType("Permanent");

        // Initialize ProfilePage and directly set the employee data to avoid database calls
        profilePage = new ProfilePage(user);
        Field empField = ProfilePage.class.getDeclaredField("currentEmployee");
        empField.setAccessible(true);
        empField.set(profilePage, testEmployee);
    }

    @Test
    public void testViewPersonalInfoValidData() {
        // Test that ProfilePage correctly loads valid personal information
        // Ensures all personal info fields (ID, name, birthday, email, phone, address) are set properly
        Employee emp = profilePage.getCurrentEmployee();
        assertNotNull("Employee object should exist", emp);
        assertEquals("Employee ID should match", 1, emp.getEmployeeId());
        assertEquals("First name should be John", "John", emp.getFirstName());
        assertEquals("Last name should be Doe", "Doe", emp.getLastName());
        assertNotNull("Birthday should be set", emp.getBirthday());
        assertEquals("Email should be john.doe@example.com", "john.doe@example.com", emp.getEmail());
        assertEquals("Phone number should be 1234567890", "1234567890", emp.getPhoneNumber());
        assertEquals("House number should be 123", "123", emp.getHouseNumber());
        assertEquals("Street should be Main St", "Main St", emp.getStreet());
        assertEquals("Barangay should be Barangay 1", "Barangay 1", emp.getBarangay());
        assertEquals("Municipality should be Manila", "Manila", emp.getMunicipality());
        assertEquals("Province should be Metro Manila", "Metro Manila", emp.getProvince());
        assertEquals("Postal code should be 1000", "1000", emp.getPostalCode());
        assertEquals("Country should be Philippines", "Philippines", emp.getCountry());
        assertEquals("Address type should be Permanent", "Permanent", emp.getAddressType());
    }

    @Test
    public void testViewPersonalInfoMinimalData() {
        // Test ProfilePage with minimal valid personal info (e.g., "N/A" for fields)
        // Simulates a case where employee data has placeholder values
        try {
            Employee minimalEmp = new Employee();
            minimalEmp.setEmployeeId(2);
            minimalEmp.setFirstName("N/A");
            minimalEmp.setLastName("N/A");
            minimalEmp.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1900-01-01"));
            minimalEmp.setEmail("N/A");
            minimalEmp.setPhoneNumber("N/A");
            minimalEmp.setHouseNumber("N/A");
            minimalEmp.setStreet("N/A");
            minimalEmp.setBarangay("N/A");
            minimalEmp.setMunicipality("N/A");
            minimalEmp.setProvince("N/A");
            minimalEmp.setPostalCode("N/A");
            minimalEmp.setCountry("N/A");
            minimalEmp.setAddressType("N/A");

            Field empField = ProfilePage.class.getDeclaredField("currentEmployee");
            empField.setAccessible(true);
            empField.set(profilePage, minimalEmp);

            Employee emp = profilePage.getCurrentEmployee();
            assertNotNull("Employee object should exist", emp);
            assertEquals("Employee ID should be 2", 2, emp.getEmployeeId());
            assertEquals("First name should be N/A", "N/A", emp.getFirstName());
            assertEquals("Last name should be N/A", "N/A", emp.getLastName());
            assertNotNull("Birthday should be set", emp.getBirthday());
            assertEquals("Email should be N/A", "N/A", emp.getEmail());
            assertEquals("Phone number should be N/A", "N/A", emp.getPhoneNumber());
            assertEquals("House number should be N/A", "N/A", emp.getHouseNumber());
            assertEquals("Street should be N/A", "N/A", emp.getStreet());
            assertEquals("Barangay should be N/A", "N/A", emp.getBarangay());
            assertEquals("Municipality should be N/A", "N/A", emp.getMunicipality());
            assertEquals("Province should be N/A", "N/A", emp.getProvince());
            assertEquals("Postal code should be N/A", "N/A", emp.getPostalCode());
            assertEquals("Country should be N/A", "N/A", emp.getCountry());
            assertEquals("Address type should be N/A", "N/A", emp.getAddressType());
        } catch (Exception e) {
            fail("Test failed unexpectedly: " + e.getMessage());
        }
    }

    @Test
    public void testViewPersonalInfoPartialData() {
        // Test ProfilePage with some incomplete personal info
        // Simulates a case where some fields have real values and others use placeholders
        try {
            Employee partialEmp = new Employee();
            partialEmp.setEmployeeId(3);
            partialEmp.setFirstName("Jane");
            partialEmp.setLastName("Smith");
            partialEmp.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1985-05-05"));
            partialEmp.setEmail("unknown@example.com");
            partialEmp.setPhoneNumber("0987654321");
            partialEmp.setHouseNumber("Unknown");
            partialEmp.setStreet("Elm St");
            partialEmp.setBarangay("Unknown");
            partialEmp.setMunicipality("Quezon City");
            partialEmp.setProvince("Unknown");
            partialEmp.setPostalCode("1100");
            partialEmp.setCountry("Unknown");
            partialEmp.setAddressType("Temporary");

            Field empField = ProfilePage.class.getDeclaredField("currentEmployee");
            empField.setAccessible(true);
            empField.set(profilePage, partialEmp);

            Employee emp = profilePage.getCurrentEmployee();
            assertNotNull("Employee object should exist", emp);
            assertEquals("Employee ID should be 3", 3, emp.getEmployeeId());
            assertEquals("First name should be Jane", "Jane", emp.getFirstName());
            assertEquals("Last name should be Smith", "Smith", emp.getLastName());
            assertNotNull("Birthday should be set", emp.getBirthday());
            assertEquals("Email should be unknown@example.com", "unknown@example.com", emp.getEmail());
            assertEquals("Phone number should be 0987654321", "0987654321", emp.getPhoneNumber());
            assertEquals("House number should be Unknown", "Unknown", emp.getHouseNumber());
            assertEquals("Street should be Elm St", "Elm St", emp.getStreet());
            assertEquals("Barangay should be Unknown", "Unknown", emp.getBarangay());
            assertEquals("Municipality should be Quezon City", "Quezon City", emp.getMunicipality());
            assertEquals("Province should be Unknown", "Unknown", emp.getProvince());
            assertEquals("Postal code should be 1100", "1100", emp.getPostalCode());
            assertEquals("Country should be Unknown", "Unknown", emp.getCountry());
            assertEquals("Address type should be Temporary", "Temporary", emp.getAddressType());
        } catch (Exception e) {
            fail("Test failed unexpectedly: " + e.getMessage());
        }
    }

    @Test
    public void testViewPersonalInfoNullEmployee() {
        // Test ProfilePage when no employee data is available
        // Should return null, matching error handling in ProfilePage
        try {
            Field empField = ProfilePage.class.getDeclaredField("currentEmployee");
            empField.setAccessible(true);
            empField.set(profilePage, null);

            Employee emp = profilePage.getCurrentEmployee();
            assertNull("Employee should be null when no data is loaded", emp);
        } catch (Exception e) {
            fail("Test failed unexpectedly: " + e.getMessage());
        }
    }

    @Test
    public void testViewPersonalInfoInvalidEmployeeId() {
        // Test ProfilePage with an invalid employee ID (0 or less)
        // Should return null, as ProfilePage handles invalid IDs with an error
        try {
            NonAdmin user = new NonAdmin("testuser2", "password", 0, 1);
            ProfilePage invalidPage = new ProfilePage(user);
            Employee emp = invalidPage.getCurrentEmployee();
            assertNull("Employee should be null for invalid employee ID", emp);
        } catch (Exception e) {
            fail("Test failed unexpectedly: " + e.getMessage());
        }
    }
}