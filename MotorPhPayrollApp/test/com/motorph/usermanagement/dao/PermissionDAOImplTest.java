/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.dao;

import com.motorph.usermanagement.model.Permission;
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
public class PermissionDAOImplTest {
    
    public PermissionDAOImplTest() {
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
     * Test of createPermission method, of class PermissionDAOImpl.
     */
    @Test
    public void testCreatePermission() throws Exception {
        System.out.println("createPermission");
        Permission permission = null;
        PermissionDAOImpl instance = new PermissionDAOImpl();
        int expResult = 0;
        int result = instance.createPermission(permission);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class PermissionDAOImpl.
     */
    @Test
    public void testFindById() throws Exception {
        System.out.println("findById");
        int permissionId = 0;
        PermissionDAOImpl instance = new PermissionDAOImpl();
        Optional<Permission> expResult = null;
        Optional<Permission> result = instance.findById(permissionId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByName method, of class PermissionDAOImpl.
     */
    @Test
    public void testFindByName() throws Exception {
        System.out.println("findByName");
        String permissionName = "";
        PermissionDAOImpl instance = new PermissionDAOImpl();
        Optional<Permission> expResult = null;
        Optional<Permission> result = instance.findByName(permissionName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class PermissionDAOImpl.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        PermissionDAOImpl instance = new PermissionDAOImpl();
        List<Permission> expResult = null;
        List<Permission> result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByCategory method, of class PermissionDAOImpl.
     */
    @Test
    public void testFindByCategory() throws Exception {
        System.out.println("findByCategory");
        int categoryId = 0;
        PermissionDAOImpl instance = new PermissionDAOImpl();
        List<Permission> expResult = null;
        List<Permission> result = instance.findByCategory(categoryId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findActivePermissions method, of class PermissionDAOImpl.
     */
    @Test
    public void testFindActivePermissions() throws Exception {
        System.out.println("findActivePermissions");
        PermissionDAOImpl instance = new PermissionDAOImpl();
        List<Permission> expResult = null;
        List<Permission> result = instance.findActivePermissions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePermission method, of class PermissionDAOImpl.
     */
    @Test
    public void testUpdatePermission() throws Exception {
        System.out.println("updatePermission");
        Permission permission = null;
        PermissionDAOImpl instance = new PermissionDAOImpl();
        boolean expResult = false;
        boolean result = instance.updatePermission(permission);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPermissionStatus method, of class PermissionDAOImpl.
     */
    @Test
    public void testSetPermissionStatus() throws Exception {
        System.out.println("setPermissionStatus");
        int permissionId = 0;
        boolean isActive = false;
        PermissionDAOImpl instance = new PermissionDAOImpl();
        boolean expResult = false;
        boolean result = instance.setPermissionStatus(permissionId, isActive);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletePermission method, of class PermissionDAOImpl.
     */
    @Test
    public void testDeletePermission() throws Exception {
        System.out.println("deletePermission");
        int permissionId = 0;
        PermissionDAOImpl instance = new PermissionDAOImpl();
        boolean expResult = false;
        boolean result = instance.deletePermission(permissionId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of permissionNameExists method, of class PermissionDAOImpl.
     */
    @Test
    public void testPermissionNameExists() throws Exception {
        System.out.println("permissionNameExists");
        String permissionName = "";
        PermissionDAOImpl instance = new PermissionDAOImpl();
        boolean expResult = false;
        boolean result = instance.permissionNameExists(permissionName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPermissionCount method, of class PermissionDAOImpl.
     */
    @Test
    public void testGetPermissionCount() throws Exception {
        System.out.println("getPermissionCount");
        PermissionDAOImpl instance = new PermissionDAOImpl();
        int expResult = 0;
        int result = instance.getPermissionCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of assignToRole method, of class PermissionDAOImpl.
     */
    @Test
    public void testAssignToRole() throws Exception {
        System.out.println("assignToRole");
        int roleId = 0;
        int permissionId = 0;
        PermissionDAOImpl instance = new PermissionDAOImpl();
        boolean expResult = false;
        boolean result = instance.assignToRole(roleId, permissionId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeFromRole method, of class PermissionDAOImpl.
     */
    @Test
    public void testRemoveFromRole() throws Exception {
        System.out.println("removeFromRole");
        int roleId = 0;
        int permissionId = 0;
        PermissionDAOImpl instance = new PermissionDAOImpl();
        boolean expResult = false;
        boolean result = instance.removeFromRole(roleId, permissionId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActivePermissionCount method, of class PermissionDAOImpl.
     */
    @Test
    public void testGetActivePermissionCount() throws Exception {
        System.out.println("getActivePermissionCount");
        PermissionDAOImpl instance = new PermissionDAOImpl();
        int expResult = 0;
        int result = instance.getActivePermissionCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByRole method, of class PermissionDAOImpl.
     */
    @Test
    public void testFindByRole() throws Exception {
        System.out.println("findByRole");
        int roleId = 0;
        PermissionDAOImpl instance = new PermissionDAOImpl();
        List<Permission> expResult = null;
        List<Permission> result = instance.findByRole(roleId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
