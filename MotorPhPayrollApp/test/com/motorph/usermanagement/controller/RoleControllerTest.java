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
public class RoleControllerTest {
    
    public RoleControllerTest() {
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
     * Test of loadAllRoles method, of class RoleController.
     */
    @Test
    public void testLoadAllRoles() {
        System.out.println("loadAllRoles");
        RoleController instance = null;
        instance.loadAllRoles();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createRole method, of class RoleController.
     */
    @Test
    public void testCreateRole() {
        System.out.println("createRole");
        String roleName = "";
        String roleDescription = "";
        RoleController instance = null;
        instance.createRole(roleName, roleDescription);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateRole method, of class RoleController.
     */
    @Test
    public void testUpdateRole() {
        System.out.println("updateRole");
        int roleId = 0;
        String roleName = "";
        String roleDescription = "";
        RoleController instance = null;
        instance.updateRole(roleId, roleName, roleDescription);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteRole method, of class RoleController.
     */
    @Test
    public void testDeleteRole() {
        System.out.println("deleteRole");
        int roleId = 0;
        RoleController instance = null;
        instance.deleteRole(roleId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchRoles method, of class RoleController.
     */
    @Test
    public void testSearchRoles() {
        System.out.println("searchRoles");
        String searchTerm = "";
        RoleController instance = null;
        instance.searchRoles(searchTerm);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
