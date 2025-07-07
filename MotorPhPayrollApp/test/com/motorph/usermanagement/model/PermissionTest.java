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
    
    private Permission permission;
    
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
        permission = new Permission();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetAccessId() {
        System.out.println("getAccessId");
        // default value should be 0
        int result = permission.getAccessId();
        assertEquals(0, result);
    }

    @Test
    public void testSetAccessId() {
        System.out.println("setAccessId");
        // test if setting id works properly
        permission.setAccessId(123);
        assertEquals(123, permission.getAccessId());
    }

    @Test
    public void testGetAccessName() {
        System.out.println("getAccessName");
        // initial value should be null
        String result = permission.getAccessName();
        assertNull(result);
    }

    @Test
    public void testSetAccessName() {
        System.out.println("setAccessName");
        // test setting permission name
        permission.setAccessName("READ_USERS");
        assertEquals("READ_USERS", permission.getAccessName());
    }

    @Test
    public void testGetAccessCategoryId() {
        System.out.println("getAccessCategoryId");
        // should start at 0
        int result = permission.getAccessCategoryId();
        assertEquals(0, result);
    }

    @Test
    public void testSetAccessCategoryId() {
        System.out.println("setAccessCategoryId");
        // try setting category id to 5
        permission.setAccessCategoryId(5);
        assertEquals(5, permission.getAccessCategoryId());
    }

    @Test
    public void testGetResourceId() {
        System.out.println("getResourceId");
        // check default value
        int result = permission.getResourceId();
        assertEquals(0, result);
    }

    @Test
    public void testSetResourceId() {
        System.out.println("setResourceId");
        // set resource id and check
        permission.setResourceId(10);
        assertEquals(10, permission.getResourceId());
    }

    @Test
    public void testGetActionId() {
        System.out.println("getActionId");
        // default should be 0
        int result = permission.getActionId();
        assertEquals(0, result);
    }

    @Test
    public void testSetActionId() {
        System.out.println("setActionId");
        // set action id to 15
        permission.setActionId(15);
        assertEquals(15, permission.getActionId());
    }

    @Test
    public void testIsRequiresApproval() {
        System.out.println("isRequiresApproval");
        // boolean should be false by default
        boolean result = permission.isRequiresApproval();
        assertFalse(result);
    }

    @Test
    public void testSetRequiresApproval() {
        System.out.println("setRequiresApproval");
        // set to true and check
        permission.setRequiresApproval(true);
        assertTrue(permission.isRequiresApproval());
    }

    @Test
    public void testIsActive() {
        System.out.println("isActive");
        // should be false initially
        boolean result = permission.isActive();
        assertFalse(result);
    }

    @Test
    public void testSetActive() {
        System.out.println("setActive");
        // activate permission
        permission.setActive(true);
        assertTrue(permission.isActive());
    }

    @Test
    public void testGetCreatedAt() {
        System.out.println("getCreatedAt");
        // timestamp should be null at first
        Timestamp result = permission.getCreatedAt();
        assertNull(result);
    }

    @Test
    public void testSetCreatedAt() {
        System.out.println("setCreatedAt");
        // set timestamp to current time
        Timestamp now = new Timestamp(System.currentTimeMillis());
        permission.setCreatedAt(now);
        assertEquals(now, permission.getCreatedAt());
    }

    @Test
    public void testGetCategoryName() {
        System.out.println("getCategoryName");
        String result = permission.getCategoryName();
        assertEquals("Unknown Category", result);
    }

    @Test
    public void testSetCategoryName() {
        System.out.println("setCategoryName");
        permission.setCategoryName("User Management");
        assertEquals("User Management", permission.getCategoryName());
    }

    @Test
    public void testGetResourceName() {
        System.out.println("getResourceName");
        String result = permission.getResourceName();
        assertEquals("Unknown Resource", result);
    }

    @Test
    public void testSetResourceName() {
        System.out.println("setResourceName");
        permission.setResourceName("Users");
        assertEquals("Users", permission.getResourceName());
    }

    @Test
    public void testGetActionName() {
        System.out.println("getActionName");
        String result = permission.getActionName();
        assertEquals("Unknown Action", result);
    }

    @Test
    public void testSetActionName() {
        System.out.println("setActionName");
        permission.setActionName("Read");
        assertEquals("Read", permission.getActionName());
    }

    @Test
    public void testEquals() {
        System.out.println("equals");
        // create two permission objects with same values
        Permission p1 = new Permission();
        p1.setAccessId(1);
        p1.setAccessName("TEST");
        
        Permission p2 = new Permission();
        p2.setAccessId(1);
        p2.setAccessName("TEST");
        
        // should be equal
        assertTrue(p1.equals(p2));
        // null should not be equal
        assertFalse(p1.equals(null));
    }

    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        // set some values
        permission.setAccessId(1);
        permission.setAccessName("TEST");
        
        // hashcode should be consistent
        int hash1 = permission.hashCode();
        int hash2 = permission.hashCode();
        assertEquals(hash1, hash2);
    }

    @Test
    public void testToString() {
        System.out.println("toString");
        // set some test values
        permission.setAccessId(1);
        permission.setAccessName("READ_USERS");
        
        String result = permission.toString();
        assertNotNull(result);
        // should contain the id and name
        assertTrue(result.contains("accessId=1"));
        assertTrue(result.contains("READ_USERS"));
    }

    @Test
    public void testConstructorWithAllParams() {
        System.out.println("constructor with all params");
        // test the full constructor
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Permission p = new Permission(1, "READ_USERS", 5, 10, 15, true, true, now);
        
        // check all values were set correctly
        assertEquals(1, p.getAccessId());
        assertEquals("READ_USERS", p.getAccessName());
        assertEquals(5, p.getAccessCategoryId());
        assertEquals(10, p.getResourceId());
        assertEquals(15, p.getActionId());
        assertTrue(p.isRequiresApproval());
        assertTrue(p.isActive());
        assertEquals(now, p.getCreatedAt());
    }
    
    @Test
    public void testConstructorWithoutIdAndTimestamp() {
        System.out.println("constructor without id and timestamp");
        // test the partial constructor
        Permission p = new Permission("WRITE_ROLES", 2, 3, 4, false, true);
        
        // id should be 0 (not set)
        assertEquals(0, p.getAccessId());
        assertEquals("WRITE_ROLES", p.getAccessName());
        assertEquals(2, p.getAccessCategoryId());
        assertEquals(3, p.getResourceId());
        assertEquals(4, p.getActionId());
        assertFalse(p.isRequiresApproval());
        assertTrue(p.isActive());
        // timestamp should be null
        assertNull(p.getCreatedAt());
    }
}