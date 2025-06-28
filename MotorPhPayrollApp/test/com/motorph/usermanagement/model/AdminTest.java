/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * @author Harvey 
 */
public class AdminTest {
    
    private Admin admin;
    
    @Before
    public void setUp() {
        admin = new Admin();
    }

    /**
     * Test of isAdmin method Admin should return true
     */
    @Test
    public void testIsAdmin() {
        boolean result = admin.isAdmin();
        assertTrue("Admin should return true for isAdmin()", result);
    }
    
    /**
     * Test isAdmin for different role IDs
     */
    @Test
    public void testIsAdminWithRoleId() {
        // Test admin role (assuming roleId 1 = admin)
        Admin adminUser = new Admin("admin", "pass", 100, 1);
        assertTrue("User with admin roleId should be admin", adminUser.isAdmin());
        
        // Test non-admin role 
        Admin nonAdminUser = new Admin("user", "pass", 101, 2);
        // This will still return true because it's Admin class
        assertTrue("Admin class always returns true", nonAdminUser.isAdmin());
    }

    /**
     * Test of getRoleName and setRoleName methods
     */
    @Test
    public void testGetSetRoleName() {
        String roleName = "Administrator";
        admin.setRoleName(roleName);
        assertEquals(roleName, admin.getRoleName());
    }

    /**
     * Test of getRoleDescription and setRoleDescription methods
     */
    @Test
    public void testGetSetRoleDescription() {
        String description = "System Admin";
        admin.setRoleDescription(description);
        assertEquals(description, admin.getRoleDescription());
    }

    /**
     * Test constructor with parameters
     */
    @Test
    public void testConstructor() {
        Admin newAdmin = new Admin("admin", "password123", 100, 1);
        assertNotNull(newAdmin);
        assertTrue(newAdmin.isAdmin());
    }
}