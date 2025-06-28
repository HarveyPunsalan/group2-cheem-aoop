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
        System.out.println("getRoleId");
        Role instance = new Role();
        int expResult = 0;
        int result = instance.getRoleId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRoleId method, of class Role.
     */
    @Test
    public void testSetRoleId() {
        System.out.println("setRoleId");
        int roleId = 0;
        Role instance = new Role();
        instance.setRoleId(roleId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRoleName method, of class Role.
     */
    @Test
    public void testGetRoleName() {
        System.out.println("getRoleName");
        Role instance = new Role();
        String expResult = "";
        String result = instance.getRoleName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRoleName method, of class Role.
     */
    @Test
    public void testSetRoleName() {
        System.out.println("setRoleName");
        String roleName = "";
        Role instance = new Role();
        instance.setRoleName(roleName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRoleDescription method, of class Role.
     */
    @Test
    public void testGetRoleDescription() {
        System.out.println("getRoleDescription");
        Role instance = new Role();
        String expResult = "";
        String result = instance.getRoleDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRoleDescription method, of class Role.
     */
    @Test
    public void testSetRoleDescription() {
        System.out.println("setRoleDescription");
        String roleDescription = "";
        Role instance = new Role();
        instance.setRoleDescription(roleDescription);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Role.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Role instance = new Role();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Role.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Role instance = new Role();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Role.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Role instance = new Role();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
