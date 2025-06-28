/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.model;

import java.sql.Timestamp;
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
public class PermissionTest {
    
    public PermissionTest() {
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
     * Test of getAccessId method, of class Permission.
     */
    @Test
    public void testGetAccessId() {
        System.out.println("getAccessId");
        Permission instance = new Permission();
        int expResult = 0;
        int result = instance.getAccessId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAccessId method, of class Permission.
     */
    @Test
    public void testSetAccessId() {
        System.out.println("setAccessId");
        int accessId = 0;
        Permission instance = new Permission();
        instance.setAccessId(accessId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccessName method, of class Permission.
     */
    @Test
    public void testGetAccessName() {
        System.out.println("getAccessName");
        Permission instance = new Permission();
        String expResult = "";
        String result = instance.getAccessName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAccessName method, of class Permission.
     */
    @Test
    public void testSetAccessName() {
        System.out.println("setAccessName");
        String accessName = "";
        Permission instance = new Permission();
        instance.setAccessName(accessName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccessCategoryId method, of class Permission.
     */
    @Test
    public void testGetAccessCategoryId() {
        System.out.println("getAccessCategoryId");
        Permission instance = new Permission();
        int expResult = 0;
        int result = instance.getAccessCategoryId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAccessCategoryId method, of class Permission.
     */
    @Test
    public void testSetAccessCategoryId() {
        System.out.println("setAccessCategoryId");
        int accessCategoryId = 0;
        Permission instance = new Permission();
        instance.setAccessCategoryId(accessCategoryId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResourceId method, of class Permission.
     */
    @Test
    public void testGetResourceId() {
        System.out.println("getResourceId");
        Permission instance = new Permission();
        int expResult = 0;
        int result = instance.getResourceId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setResourceId method, of class Permission.
     */
    @Test
    public void testSetResourceId() {
        System.out.println("setResourceId");
        int resourceId = 0;
        Permission instance = new Permission();
        instance.setResourceId(resourceId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActionId method, of class Permission.
     */
    @Test
    public void testGetActionId() {
        System.out.println("getActionId");
        Permission instance = new Permission();
        int expResult = 0;
        int result = instance.getActionId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setActionId method, of class Permission.
     */
    @Test
    public void testSetActionId() {
        System.out.println("setActionId");
        int actionId = 0;
        Permission instance = new Permission();
        instance.setActionId(actionId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isRequiresApproval method, of class Permission.
     */
    @Test
    public void testIsRequiresApproval() {
        System.out.println("isRequiresApproval");
        Permission instance = new Permission();
        boolean expResult = false;
        boolean result = instance.isRequiresApproval();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRequiresApproval method, of class Permission.
     */
    @Test
    public void testSetRequiresApproval() {
        System.out.println("setRequiresApproval");
        boolean requiresApproval = false;
        Permission instance = new Permission();
        instance.setRequiresApproval(requiresApproval);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isActive method, of class Permission.
     */
    @Test
    public void testIsActive() {
        System.out.println("isActive");
        Permission instance = new Permission();
        boolean expResult = false;
        boolean result = instance.isActive();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setActive method, of class Permission.
     */
    @Test
    public void testSetActive() {
        System.out.println("setActive");
        boolean isActive = false;
        Permission instance = new Permission();
        instance.setActive(isActive);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCreatedAt method, of class Permission.
     */
    @Test
    public void testGetCreatedAt() {
        System.out.println("getCreatedAt");
        Permission instance = new Permission();
        Timestamp expResult = null;
        Timestamp result = instance.getCreatedAt();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCreatedAt method, of class Permission.
     */
    @Test
    public void testSetCreatedAt() {
        System.out.println("setCreatedAt");
        Timestamp createdAt = null;
        Permission instance = new Permission();
        instance.setCreatedAt(createdAt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCategoryName method, of class Permission.
     */
    @Test
    public void testGetCategoryName() {
        System.out.println("getCategoryName");
        Permission instance = new Permission();
        String expResult = "";
        String result = instance.getCategoryName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCategoryName method, of class Permission.
     */
    @Test
    public void testSetCategoryName() {
        System.out.println("setCategoryName");
        String categoryName = "";
        Permission instance = new Permission();
        instance.setCategoryName(categoryName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResourceName method, of class Permission.
     */
    @Test
    public void testGetResourceName() {
        System.out.println("getResourceName");
        Permission instance = new Permission();
        String expResult = "";
        String result = instance.getResourceName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setResourceName method, of class Permission.
     */
    @Test
    public void testSetResourceName() {
        System.out.println("setResourceName");
        String resourceName = "";
        Permission instance = new Permission();
        instance.setResourceName(resourceName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActionName method, of class Permission.
     */
    @Test
    public void testGetActionName() {
        System.out.println("getActionName");
        Permission instance = new Permission();
        String expResult = "";
        String result = instance.getActionName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setActionName method, of class Permission.
     */
    @Test
    public void testSetActionName() {
        System.out.println("setActionName");
        String actionName = "";
        Permission instance = new Permission();
        instance.setActionName(actionName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Permission.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Permission instance = new Permission();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Permission.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Permission instance = new Permission();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Permission.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Permission instance = new Permission();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
