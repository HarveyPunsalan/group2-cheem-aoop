/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.security;

import com.motorph.usermanagement.model.Permission;
import com.motorph.usermanagement.model.User;
import java.util.Arrays;
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
    
    private Authorizer authorizer;
    private User testUser;
    
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
        authorizer = new AuthorizerImpl();
        
        // Create a test user
        testUser = new User();
        testUser.setUserId(1);
        testUser.setUsername("testuser");
        testUser.setRoleId(1);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of hasPermission method with null user.
     */
    @Test
    public void testHasPermission_NullUser() throws Exception {
        System.out.println("hasPermission with null user");
        
        boolean result = authorizer.hasPermission(null, "READ_ACCESS");
        
        assertFalse("Null user should not have permissions", result);
    }

    /**
     * Test of hasPermission method with null permission name.
     */
    @Test
    public void testHasPermission_NullPermission() throws Exception {
        System.out.println("hasPermission with null permission");
        
        boolean result = authorizer.hasPermission(testUser, null);
        
        assertFalse("User should not have null permission", result);
    }

    /**
     * Test of hasPermission method with empty permission name.
     */
    @Test
    public void testHasPermission_EmptyPermission() throws Exception {
        System.out.println("hasPermission with empty permission");
        
        boolean result = authorizer.hasPermission(testUser, "");
        
        assertFalse("User should not have empty permission", result);
    }

    /**
     * Test of hasAnyPermission method with null user.
     */
    @Test
    public void testHasAnyPermission_NullUser() throws Exception {
        System.out.println("hasAnyPermission with null user");
        
        List<String> permissions = Arrays.asList("READ_ACCESS", "WRITE_ACCESS");
        boolean result = authorizer.hasAnyPermission(null, permissions);
        
        assertFalse("Null user should not have any permissions", result);
    }

    /**
     * Test of hasAnyPermission method with null permission list.
     */
    @Test
    public void testHasAnyPermission_NullPermissions() throws Exception {
        System.out.println("hasAnyPermission with null permissions");
        
        boolean result = authorizer.hasAnyPermission(testUser, null);
        
        assertFalse("User should not have null permissions", result);
    }

    /**
     * Test of hasAllPermissions method with null user.
     */
    @Test
    public void testHasAllPermissions_NullUser() throws Exception {
        System.out.println("hasAllPermissions with null user");
        
        List<String> permissions = Arrays.asList("READ_ACCESS", "WRITE_ACCESS");
        boolean result = authorizer.hasAllPermissions(null, permissions);
        
        assertFalse("Null user should not have all permissions", result);
    }

    /**
     * Test of hasAllPermissions method with null permission list.
     */
    @Test
    public void testHasAllPermissions_NullPermissions() throws Exception {
        System.out.println("hasAllPermissions with null permissions");
        
        boolean result = authorizer.hasAllPermissions(testUser, null);
        
        assertFalse("User should not have null permissions", result);
    }

    /**
     * Test of getUserPermissions method with null user.
     */
    @Test
    public void testGetUserPermissions_NullUser() throws Exception {
        System.out.println("getUserPermissions with null user");
        
        List<Permission> result = authorizer.getUserPermissions(null);
        
        assertNotNull("Should return empty list for null user", result);
        assertTrue("Should return empty list for null user", result.isEmpty());
    }

    /**
     * Test of getUserPermissions method with valid user.
     */
    @Test
    public void testGetUserPermissions_ValidUser() throws Exception {
        System.out.println("getUserPermissions with valid user");
        
        List<Permission> result = authorizer.getUserPermissions(testUser);
        
        assertNotNull("Should return a list", result);
        // Since this depends on database data, we just check it's not null
    }

    /**
     * Test of hasResourceAccess method with null parameters.
     */
    @Test
    public void testHasResourceAccess_NullParameters() throws Exception {
        System.out.println("hasResourceAccess with null parameters");
        
        boolean result1 = authorizer.hasResourceAccess(null, "resource", "action");
        assertFalse("Null user should not have resource access", result1);
        
        boolean result2 = authorizer.hasResourceAccess(testUser, null, "action");
        assertFalse("Null resource should not be accessible", result2);
        
        boolean result3 = authorizer.hasResourceAccess(testUser, "resource", null);
        assertFalse("Null action should not be accessible", result3);
    }

    /**
     * Test of isAdmin method with null user.
     */
    @Test
    public void testIsAdmin_NullUser() throws Exception {
        System.out.println("isAdmin with null user");
        
        boolean result = authorizer.isAdmin(null);
        
        assertFalse("Null user should not be admin", result);
    }

    /**
     * Test of isAdmin method with valid user.
     */
    @Test
    public void testIsAdmin_ValidUser() throws Exception {
        System.out.println("isAdmin with valid user");
        
        boolean result = authorizer.isAdmin(testUser);
        
        // Just check it doesn't throw an exception
        // The actual result depends on database data
        assertNotNull("Should return a boolean value", result);
    }
}