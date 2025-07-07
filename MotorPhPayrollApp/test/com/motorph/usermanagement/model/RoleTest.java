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
public class RoleTest {
    
    public RoleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getRoleId method, of class Role.
     */
    @Test
    public void testGetRoleId() {
        Role instance = new Role();
        instance.setRoleId(1);
        int result = instance.getRoleId();
        assertEquals(1, result);
    }

    /**
     * Test of setRoleId method, of class Role.
     */
    @Test
    public void testSetRoleId() {
        int roleId = 5;
        Role instance = new Role();
        instance.setRoleId(roleId);
        assertEquals(roleId, instance.getRoleId());
    }

    /**
     * Test of getRoleName method, of class Role.
     */
    @Test
    public void testGetRoleName() {
        Role instance = new Role();
        instance.setRoleName("ADMIN");
        String result = instance.getRoleName();
        assertEquals("ADMIN", result);
    }

    /**
     * Test of setRoleName method, of class Role.
     */
    @Test
    public void testSetRoleName() {
        String roleName = "USER";
        Role instance = new Role();
        instance.setRoleName(roleName);
        assertEquals(roleName, instance.getRoleName());
    }

    /**
     * Test of getRoleDescription method, of class Role.
     */
    @Test
    public void testGetRoleDescription() {
        Role instance = new Role();
        instance.setRoleDescription("Admin role");
        String result = instance.getRoleDescription();
        assertEquals("Admin role", result);
    }

    /**
     * Test of setRoleDescription method, of class Role.
     */
    @Test
    public void testSetRoleDescription() {
        String roleDescription = "User role";
        Role instance = new Role();
        instance.setRoleDescription(roleDescription);
        assertEquals(roleDescription, instance.getRoleDescription());
    }

    /**
     * Test of equals method, of class Role.
     */
    @Test
    public void testEquals() {
        Role role1 = new Role(1, "ADMIN", "Administrator");
        Role role2 = new Role(1, "ADMIN", "Administrator");
        boolean result = role1.equals(role2);
        assertEquals(true, result);
    }

    /**
     * Test of hashCode method, of class Role.
     */
    @Test
    public void testHashCode() {
        Role instance = new Role(1, "ADMIN", "Administrator");
        int result = instance.hashCode();
        assertTrue(result != 0);
    }

    /**
     * Test of toString method, of class Role.
     */
    @Test
    public void testToString() {
        Role instance = new Role(1, "ADMIN", "Administrator");
        String result = instance.toString();
        assertTrue(result.contains("ADMIN"));
        assertTrue(result.contains("1"));
    }
    
    /**
     * Test constructors
     */
    @Test
    public void testConstructors() {
        Role role1 = new Role();
        assertNotNull(role1);
        
        Role role2 = new Role("USER", "Regular user");
        assertEquals("USER", role2.getRoleName());
        assertEquals("Regular user", role2.getRoleDescription());
        
        Role role3 = new Role(1, "ADMIN", "Administrator");
        assertEquals(1, role3.getRoleId());
        assertEquals("ADMIN", role3.getRoleName());
        assertEquals("Administrator", role3.getRoleDescription());
    }
}