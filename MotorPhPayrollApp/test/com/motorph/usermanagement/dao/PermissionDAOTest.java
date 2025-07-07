/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.dao;

import com.motorph.usermanagement.exception.DataAccessException;
import com.motorph.usermanagement.exception.DuplicatePermissionException;
import com.motorph.usermanagement.exception.PermissionNotFoundException;
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
public class PermissionDAOTest {
    
    public PermissionDAOTest() {
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
     * Test of createPermission method, of class PermissionDAO.
     */
    @Test
    public void testCreatePermission() throws Exception {
        System.out.println("createPermission");
        Permission permission = null;
        PermissionDAO instance = new PermissionDAOImpl();
        int expResult = 0;
        int result = instance.createPermission(permission);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class PermissionDAO.
     */
    @Test
    public void testFindById() throws Exception {
        System.out.println("findById");
        int permissionId = 0;
        PermissionDAO instance = new PermissionDAOImpl();
        Optional<Permission> expResult = null;
        Optional<Permission> result = instance.findById(permissionId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByName method, of class PermissionDAO.
     */
    @Test
    public void testFindByName() throws Exception {
        System.out.println("findByName");
        String permissionName = "";
        PermissionDAO instance = new PermissionDAOImpl();
        Optional<Permission> expResult = null;
        Optional<Permission> result = instance.findByName(permissionName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class PermissionDAO.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        PermissionDAO instance = new PermissionDAOImpl();
        List<Permission> expResult = null;
        List<Permission> result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByCategory method, of class PermissionDAO.
     */
    @Test
    public void testFindByCategory() throws Exception {
        System.out.println("findByCategory");
        int categoryId = 0;
        PermissionDAO instance = new PermissionDAOImpl();
        List<Permission> expResult = null;
        List<Permission> result = instance.findByCategory(categoryId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findActivePermissions method, of class PermissionDAO.
     */
    @Test
    public void testFindActivePermissions() throws Exception {
        System.out.println("findActivePermissions");
        PermissionDAO instance = new PermissionDAOImpl();
        List<Permission> expResult = null;
        List<Permission> result = instance.findActivePermissions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePermission method, of class PermissionDAO.
     */
    @Test
    public void testUpdatePermission() throws Exception {
        System.out.println("updatePermission");
        Permission permission = null;
        PermissionDAO instance = new PermissionDAOImpl();
        boolean expResult = false;
        boolean result = instance.updatePermission(permission);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPermissionStatus method, of class PermissionDAO.
     */
    @Test
    public void testSetPermissionStatus() throws Exception {
        System.out.println("setPermissionStatus");
        int permissionId = 0;
        boolean isActive = false;
        PermissionDAO instance = new PermissionDAOImpl();
        boolean expResult = false;
        boolean result = instance.setPermissionStatus(permissionId, isActive);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletePermission method, of class PermissionDAO.
     */
    @Test
    public void testDeletePermission() throws Exception {
        System.out.println("deletePermission");
        int permissionId = 0;
        PermissionDAO instance = new PermissionDAOImpl();
        boolean expResult = false;
        boolean result = instance.deletePermission(permissionId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of permissionNameExists method, of class PermissionDAO.
     */
    @Test
    public void testPermissionNameExists() throws Exception {
        System.out.println("permissionNameExists");
        String permissionName = "";
        PermissionDAO instance = new PermissionDAOImpl();
        boolean expResult = false;
        boolean result = instance.permissionNameExists(permissionName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPermissionCount method, of class PermissionDAO.
     */
    @Test
    public void testGetPermissionCount() throws Exception {
        System.out.println("getPermissionCount");
        PermissionDAO instance = new PermissionDAOImpl();
        int expResult = 0;
        int result = instance.getPermissionCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActivePermissionCount method, of class PermissionDAO.
     */
    @Test
    public void testGetActivePermissionCount() throws Exception {
        System.out.println("getActivePermissionCount");
        PermissionDAO instance = new PermissionDAOImpl();
        int expResult = 0;
        int result = instance.getActivePermissionCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of assignToRole method, of class PermissionDAO.
     */
    @Test
    public void testAssignToRole() throws Exception {
        System.out.println("assignToRole");
        int roleId = 0;
        int permissionId = 0;
        PermissionDAO instance = new PermissionDAOImpl();
        boolean expResult = false;
        boolean result = instance.assignToRole(roleId, permissionId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeFromRole method, of class PermissionDAO.
     */
    @Test
    public void testRemoveFromRole() throws Exception {
        System.out.println("removeFromRole");
        int roleId = 0;
        int permissionId = 0;
        PermissionDAO instance = new PermissionDAOImpl();
        boolean expResult = false;
        boolean result = instance.removeFromRole(roleId, permissionId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByRole method, of class PermissionDAO.
     */
    @Test
    public void testFindByRole() throws Exception {
        System.out.println("findByRole");
        int roleId = 0;
        PermissionDAO instance = new PermissionDAOImpl();
        List<Permission> expResult = null;
        List<Permission> result = instance.findByRole(roleId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class PermissionDAOImpl implements PermissionDAO {

        public int createPermission(Permission permission) throws DuplicatePermissionException, DataAccessException {
            return 0;
        }

        public Optional<Permission> findById(int permissionId) throws DataAccessException {
            return null;
        }

        public Optional<Permission> findByName(String permissionName) throws DataAccessException {
            return null;
        }

        public List<Permission> findAll() throws DataAccessException {
            return null;
        }

        public List<Permission> findByCategory(int categoryId) throws DataAccessException {
            return null;
        }

        public List<Permission> findActivePermissions() throws DataAccessException {
            return null;
        }

        public boolean updatePermission(Permission permission) throws PermissionNotFoundException, DataAccessException {
            return false;
        }

        public boolean setPermissionStatus(int permissionId, boolean isActive) throws PermissionNotFoundException, DataAccessException {
            return false;
        }

        public boolean deletePermission(int permissionId) throws PermissionNotFoundException, DataAccessException {
            return false;
        }

        public boolean permissionNameExists(String permissionName) throws DataAccessException {
            return false;
        }

        public int getPermissionCount() throws DataAccessException {
            return 0;
        }

        public int getActivePermissionCount() throws DataAccessException {
            return 0;
        }

        public boolean assignToRole(int roleId, int permissionId) throws DataAccessException {
            return false;
        }

        public boolean removeFromRole(int roleId, int permissionId) throws DataAccessException {
            return false;
        }

        public List<Permission> findByRole(int roleId) throws DataAccessException {
            return null;
        }
    }
    
}
