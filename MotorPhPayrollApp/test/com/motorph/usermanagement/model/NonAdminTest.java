/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * @author Harvey
 */
public class NonAdminTest {
    
    private NonAdmin nonAdmin;
    
    public NonAdminTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        nonAdmin = new NonAdmin("testuser", "hashedpass", 123, 2);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isAdmin method should always return false
     */
    @Test
    public void testIsAdmin() {
        System.out.println("Testing isAdmin");
        boolean result = nonAdmin.isAdmin();
        assertFalse("NonAdmin should not be admin", result);
    }

    /**
     * Test default constructor
     */
    @Test
    public void testDefaultConstructor() {
        System.out.println("Testing default constructor");
        NonAdmin instance = new NonAdmin();
        assertNotNull("Instance should not be null", instance);
        assertFalse("Should not be admin", instance.isAdmin());
    }

    /**
     * Test constructor with parameters
     */
    @Test
    public void testConstructorWithParams() {
        System.out.println("Testing constructor with parameters");
        NonAdmin instance = new NonAdmin("user1", "pass123", 456, 3);
        assertNotNull("Instance should not be null", instance);
        assertEquals("Username should match", "user1", instance.getUsername());
        assertEquals("Employee ID should match", 456, instance.getEmployeeId());
        assertFalse("Should not be admin", instance.isAdmin());
    }

    /**
     * Test login method just check it doesn't crash
     */
    @Test
    public void testLogin() {
        System.out.println("Testing login method");
        try {
            User testUser = new User("testuser", "testpass", 123, 2);
            NonAdmin.login(testUser);
            // If we get here without exception, test passes
            assertTrue("Login method executed without error", true);
        } catch (Exception e) {
            fail("Login method should not throw exception: " + e.getMessage());
        }
    }
}