/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.security;

import com.motorph.usermanagement.exception.DataAccessException;
import com.motorph.usermanagement.model.Permission;
import com.motorph.usermanagement.model.User;
import java.util.List;
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
public class AuthorizerTest {
    
    public AuthorizerTest() {
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
     * Test of hasPermission method, of class Authorizer.
     */
    @Test
    public void testHasPermission() throws Exception {
        System.out.println("hasPermission");
        User user = null;
        String permissionName = "";
        Authorizer instance = new AuthorizerImpl();
        boolean expResult = false;
        boolean result = instance.hasPermission(user, permissionName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasAnyPermission method, of class Authorizer.
     */
    @Test
    public void testHasAnyPermission() throws Exception {
        System.out.println("hasAnyPermission");
        User user = null;
        List<String> permissionNames = null;
        Authorizer instance = new AuthorizerImpl();
        boolean expResult = false;
        boolean result = instance.hasAnyPermission(user, permissionNames);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasAllPermissions method, of class Authorizer.
     */
    @Test
    public void testHasAllPermissions() throws Exception {
        System.out.println("hasAllPermissions");
        User user = null;
        List<String> permissionNames = null;
        Authorizer instance = new AuthorizerImpl();
        boolean expResult = false;
        boolean result = instance.hasAllPermissions(user, permissionNames);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserPermissions method, of class Authorizer.
     */
    @Test
    public void testGetUserPermissions() throws Exception {
        System.out.println("getUserPermissions");
        User user = null;
        Authorizer instance = new AuthorizerImpl();
        List<Permission> expResult = null;
        List<Permission> result = instance.getUserPermissions(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasResourceAccess method, of class Authorizer.
     */
    @Test
    public void testHasResourceAccess() throws Exception {
        System.out.println("hasResourceAccess");
        User user = null;
        String resourceName = "";
        String actionName = "";
        Authorizer instance = new AuthorizerImpl();
        boolean expResult = false;
        boolean result = instance.hasResourceAccess(user, resourceName, actionName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isAdmin method, of class Authorizer.
     */
    @Test
    public void testIsAdmin() throws Exception {
        System.out.println("isAdmin");
        User user = null;
        Authorizer instance = new AuthorizerImpl();
        boolean expResult = false;
        boolean result = instance.isAdmin(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class AuthorizerImpl implements Authorizer {

        public boolean hasPermission(User user, String permissionName) throws DataAccessException {
            return false;
        }

        public boolean hasAnyPermission(User user, List<String> permissionNames) throws DataAccessException {
            return false;
        }

        public boolean hasAllPermissions(User user, List<String> permissionNames) throws DataAccessException {
            return false;
        }

        public List<Permission> getUserPermissions(User user) throws DataAccessException {
            return null;
        }

        public boolean hasResourceAccess(User user, String resourceName, String actionName) throws DataAccessException {
            return false;
        }

        public boolean isAdmin(User user) throws DataAccessException {
            return false;
        }
    }
    
}
