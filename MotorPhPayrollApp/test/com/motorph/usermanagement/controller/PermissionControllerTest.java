/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.controller;

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
public class PermissionControllerTest {
    
    public PermissionControllerTest() {
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
     * Test of loadAllPermissions method, of class PermissionController.
     */
    @Test
    public void testLoadAllPermissions() {
        System.out.println("loadAllPermissions");
        PermissionController instance = null;
        instance.loadAllPermissions();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadActivePermissions method, of class PermissionController.
     */
    @Test
    public void testLoadActivePermissions() {
        System.out.println("loadActivePermissions");
        PermissionController instance = null;
        instance.loadActivePermissions();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createPermission method, of class PermissionController.
     */
    @Test
    public void testCreatePermission() {
        System.out.println("createPermission");
        String permissionName = "";
        int categoryId = 0;
        int resourceId = 0;
        int actionId = 0;
        boolean requiresApproval = false;
        PermissionController instance = null;
        instance.createPermission(permissionName, categoryId, resourceId, actionId, requiresApproval);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePermission method, of class PermissionController.
     */
    @Test
    public void testUpdatePermission() {
        System.out.println("updatePermission");
        int permissionId = 0;
        String permissionName = "";
        int categoryId = 0;
        int resourceId = 0;
        int actionId = 0;
        boolean requiresApproval = false;
        PermissionController instance = null;
        instance.updatePermission(permissionId, permissionName, categoryId, resourceId, actionId, requiresApproval);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPermissionStatus method, of class PermissionController.
     */
    @Test
    public void testSetPermissionStatus() {
        System.out.println("setPermissionStatus");
        int permissionId = 0;
        boolean isActive = false;
        PermissionController instance = null;
        instance.setPermissionStatus(permissionId, isActive);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of assignPermissionToRole method, of class PermissionController.
     */
    @Test
    public void testAssignPermissionToRole() {
        System.out.println("assignPermissionToRole");
        int roleId = 0;
        int permissionId = 0;
        PermissionController instance = null;
        instance.assignPermissionToRole(roleId, permissionId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removePermissionFromRole method, of class PermissionController.
     */
    @Test
    public void testRemovePermissionFromRole() {
        System.out.println("removePermissionFromRole");
        int roleId = 0;
        int permissionId = 0;
        PermissionController instance = null;
        instance.removePermissionFromRole(roleId, permissionId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadPermissionsForRole method, of class PermissionController.
     */
    @Test
    public void testLoadPermissionsForRole() {
        System.out.println("loadPermissionsForRole");
        int roleId = 0;
        PermissionController instance = null;
        instance.loadPermissionsForRole(roleId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
