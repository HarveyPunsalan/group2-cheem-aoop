/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.dao;

import com.motorph.usermanagement.model.Permission;
import com.motorph.usermanagement.model.Role;
import java.util.List;
import java.util.Optional;
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
public class RoleDAOImplTest {
    
    public RoleDAOImplTest() {
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
     * Test of createRole method, of class RoleDAOImpl.
     */
    @Test
    public void testCreateRole() throws Exception {
        System.out.println("createRole");
        Role role = null;
        RoleDAOImpl instance = new RoleDAOImpl();
        int expResult = 0;
        int result = instance.createRole(role);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class RoleDAOImpl.
     */
    @Test
    public void testFindById() throws Exception {
        System.out.println("findById");
        int roleId = 0;
        RoleDAOImpl instance = new RoleDAOImpl();
        Optional<Role> expResult = null;
        Optional<Role> result = instance.findById(roleId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByName method, of class RoleDAOImpl.
     */
    @Test
    public void testFindByName() throws Exception {
        System.out.println("findByName");
        String roleName = "";
        RoleDAOImpl instance = new RoleDAOImpl();
        Optional<Role> expResult = null;
        Optional<Role> result = instance.findByName(roleName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class RoleDAOImpl.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        RoleDAOImpl instance = new RoleDAOImpl();
        List<Role> expResult = null;
        List<Role> result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateRole method, of class RoleDAOImpl.
     */
    @Test
    public void testUpdateRole() throws Exception {
        System.out.println("updateRole");
        Role role = null;
        RoleDAOImpl instance = new RoleDAOImpl();
        boolean expResult = false;
        boolean result = instance.updateRole(role);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteRole method, of class RoleDAOImpl.
     */
    @Test
    public void testDeleteRole() throws Exception {
        System.out.println("deleteRole");
        int roleId = 0;
        RoleDAOImpl instance = new RoleDAOImpl();
        boolean expResult = false;
        boolean result = instance.deleteRole(roleId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of roleNameExists method, of class RoleDAOImpl.
     */
    @Test
    public void testRoleNameExists() throws Exception {
        System.out.println("roleNameExists");
        String roleName = "";
        RoleDAOImpl instance = new RoleDAOImpl();
        boolean expResult = false;
        boolean result = instance.roleNameExists(roleName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRoleCount method, of class RoleDAOImpl.
     */
    @Test
    public void testGetRoleCount() throws Exception {
        System.out.println("getRoleCount");
        RoleDAOImpl instance = new RoleDAOImpl();
        int expResult = 0;
        int result = instance.getRoleCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserCountForRole method, of class RoleDAOImpl.
     */
    @Test
    public void testGetUserCountForRole() throws Exception {
        System.out.println("getUserCountForRole");
        int roleId = 0;
        RoleDAOImpl instance = new RoleDAOImpl();
        int expResult = 0;
        int result = instance.getUserCountForRole(roleId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchRolesByName method, of class RoleDAOImpl.
     */
    @Test
    public void testSearchRolesByName() throws Exception {
        System.out.println("searchRolesByName");
        String searchTerm = "";
        RoleDAOImpl instance = new RoleDAOImpl();
        List<Role> expResult = null;
        List<Role> result = instance.searchRolesByName(searchTerm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPermissionsForRole method, of class RoleDAOImpl.
     */
    @Test
    public void testGetPermissionsForRole() throws Exception {
        System.out.println("getPermissionsForRole");
        int roleId = 0;
        RoleDAOImpl instance = new RoleDAOImpl();
        List<Permission> expResult = null;
        List<Permission> result = instance.getPermissionsForRole(roleId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of assignPermissionsToRole method, of class RoleDAOImpl.
     */
    @Test
    public void testAssignPermissionsToRole() throws Exception {
        System.out.println("assignPermissionsToRole");
        int roleId = 0;
        List<Integer> accessIds = null;
        RoleDAOImpl instance = new RoleDAOImpl();
        boolean expResult = false;
        boolean result = instance.assignPermissionsToRole(roleId, accessIds);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removePermissionsFromRole method, of class RoleDAOImpl.
     */
    @Test
    public void testRemovePermissionsFromRole() throws Exception {
        System.out.println("removePermissionsFromRole");
        int roleId = 0;
        List<Integer> accessIds = null;
        RoleDAOImpl instance = new RoleDAOImpl();
        boolean expResult = false;
        boolean result = instance.removePermissionsFromRole(roleId, accessIds);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
