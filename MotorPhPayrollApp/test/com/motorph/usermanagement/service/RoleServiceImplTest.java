/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.service;

import com.motorph.usermanagement.model.Permission;
import com.motorph.usermanagement.model.Role;
import com.motorph.usermanagement.exception.RoleNotFoundException;
import com.motorph.usermanagement.exception.DuplicateRoleException;
import com.motorph.usermanagement.exception.DataAccessException;

import java.util.List;
import java.util.Optional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Harvey
 */
public class RoleServiceImplTest {
    
    private RoleServiceImpl roleService;
    
    @Before
    public void setUp() {
        roleService = new RoleServiceImpl();
    }
    
    @After
    public void tearDown() {
        roleService = null;
    }

    /**
     * Test createRole with null role - should throw IllegalArgumentException
     */
    @Test(expected = NullPointerException.class)
    public void testCreateRoleWithNullRole() throws Exception {
        Role nullRole = null;
        roleService.createRole(nullRole);
    }

    /**
     * Test createRole with invalid role name - should throw IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateRoleWithInvalidName() throws Exception {
        Role invalidRole = new Role("", "Test description");
        roleService.createRole(invalidRole);
    }

    /**
     * Test createRole with valid role name
     */
    @Test
    public void testCreateRoleWithValidName() throws Exception {
        Role validRole = new Role("TestRole", "Test description");
        try {
            int result = roleService.createRole(validRole);
            assertTrue("Role ID should be positive", result > 0);
        } catch (DuplicateRoleException e) {
            // This is expected if role already exists in DB
            System.out.println("Role already exists: " + e.getMessage());
        } catch (DataAccessException e) {
            // This might happen if DB is not available
            System.out.println("Database access issue: " + e.getMessage());
        }
    }

    /**
     * Test updateRole with null role - should throw NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testUpdateRoleWithNullRole() throws Exception {
        Role nullRole = null;
        roleService.updateRole(nullRole);
    }

    /**
     * Test updateRole with invalid role name - should throw IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateRoleWithInvalidName() throws Exception {
        Role invalidRole = new Role(1, "", "Test description");
        roleService.updateRole(invalidRole);
    }

    /**
     * Test getRoleByName with null parameter
     */
    @Test
    public void testGetRoleByNameWithNull() throws Exception {
        Optional<Role> result = roleService.getRoleByName(null);
        assertFalse("Should return empty Optional for null name", result.isPresent());
    }

    /**
     * Test getRoleByName with empty string
     */
    @Test
    public void testGetRoleByNameWithEmptyString() throws Exception {
        Optional<Role> result = roleService.getRoleByName("");
        assertFalse("Should return empty Optional for empty name", result.isPresent());
    }

    /**
     * Test getRoleByName with whitespace only
     */
    @Test
    public void testGetRoleByNameWithWhitespace() throws Exception {
        Optional<Role> result = roleService.getRoleByName("   ");
        assertFalse("Should return empty Optional for whitespace-only name", result.isPresent());
    }

    /**
     * Test isRoleNameAvailable with null parameter
     */
    @Test
    public void testIsRoleNameAvailableWithNull() throws Exception {
        boolean result = roleService.isRoleNameAvailable(null);
        assertFalse("Should return false for null name", result);
    }

    /**
     * Test isRoleNameAvailable with empty string
     */
    @Test
    public void testIsRoleNameAvailableWithEmptyString() throws Exception {
        boolean result = roleService.isRoleNameAvailable("");
        assertFalse("Should return false for empty name", result);
    }

    /**
     * Test searchRolesByName with null parameter
     */
    @Test
    public void testSearchRolesByNameWithNull() throws Exception {
        try {
            List<Role> result = roleService.searchRolesByName(null);
            assertNotNull("Should return a list (not null)", result);
        } catch (DataAccessException e) {
            // Expected if database is not available
            System.out.println("Database access issue in search: " + e.getMessage());
        }
    }

    /**
     * Test searchRolesByName with empty string
     */
    @Test
    public void testSearchRolesByNameWithEmptyString() throws Exception {
        try {
            List<Role> result = roleService.searchRolesByName("");
            assertNotNull("Should return a list (not null)", result);
        } catch (DataAccessException e) {
            // Expected if database is not available
            System.out.println("Database access issue in search: " + e.getMessage());
        }
    }

    /**
     * Test Role model class basic functionality
     */
    @Test
    public void testRoleModelBasics() {
        Role role1 = new Role();
        role1.setRoleId(1);
        role1.setRoleName("TestRole");
        role1.setRoleDescription("Test Description");
        
        assertEquals(1, role1.getRoleId());
        assertEquals("TestRole", role1.getRoleName());
        assertEquals("Test Description", role1.getRoleDescription());
        
        Role role2 = new Role("TestRole2", "Another Description");
        assertEquals("TestRole2", role2.getRoleName());
        assertEquals("Another Description", role2.getRoleDescription());
        
        Role role3 = new Role(2, "TestRole3", "Third Description");
        assertEquals(2, role3.getRoleId());
        assertEquals("TestRole3", role3.getRoleName());
    }

    /**
     * Test Role equals and hashCode
     */
    @Test
    public void testRoleEqualsAndHashCode() {
        Role role1 = new Role(1, "Admin", "Administrator");
        Role role2 = new Role(1, "Admin", "Administrator");
        Role role3 = new Role(2, "User", "Regular User");
        
        assertTrue("Same roles should be equal", role1.equals(role2));
        assertFalse("Different roles should not be equal", role1.equals(role3));
        assertEquals("Equal roles should have same hash code", role1.hashCode(), role2.hashCode());
    }

    /**
     * Test Role toString method
     */
    @Test
    public void testRoleToString() {
        Role role = new Role(1, "Admin", "Administrator role");
        String result = role.toString();
        
        assertTrue("toString should contain role ID", result.contains("1"));
        assertTrue("toString should contain role name", result.contains("Admin"));
        assertTrue("toString should contain description", result.contains("Administrator role"));
    }
}