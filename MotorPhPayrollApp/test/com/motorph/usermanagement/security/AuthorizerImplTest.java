/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.security;

import com.motorph.usermanagement.model.Permission;
import com.motorph.usermanagement.model.User;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * @author Harvey
 */
public class AuthorizerImplTest {
    
    private AuthorizerImpl authorizer;
    private User testUser;
    
    @Before
    public void setUp() {
        authorizer = new AuthorizerImpl();
        
        // Create a test user
        testUser = new User();
        testUser.setUserId(1);
        testUser.setUsername("testuser");
        testUser.setRoleId(2);
    }

    @Test
    public void testHasPermission_NullUser_ReturnsFalse() throws Exception {
        System.out.println("Testing hasPermission with null user");
        
        boolean result = authorizer.hasPermission(null, "USER_READ");
        
        assertFalse("Should return false for null user", result);
    }

    @Test
    public void testHasPermission_NullPermission_ReturnsFalse() throws Exception {
        System.out.println("Testing hasPermission with null permission");
        
        boolean result = authorizer.hasPermission(testUser, null);
        
        assertFalse("Should return false for null permission", result);
    }

    @Test
    public void testHasPermission_EmptyPermission_ReturnsFalse() throws Exception {
        System.out.println("Testing hasPermission with empty permission");
        
        boolean result = authorizer.hasPermission(testUser, "");
        
        assertFalse("Should return false for empty permission", result);
    }

    @Test
    public void testHasAnyPermission_NullUser_ReturnsFalse() throws Exception {
        System.out.println("Testing hasAnyPermission with null user");
        
        List<String> permissions = Arrays.asList("USER_READ", "USER_WRITE");
        boolean result = authorizer.hasAnyPermission(null, permissions);
        
        assertFalse("Should return false for null user", result);
    }

    @Test
    public void testHasAnyPermission_NullPermissions_ReturnsFalse() throws Exception {
        System.out.println("Testing hasAnyPermission with null permissions");
        
        boolean result = authorizer.hasAnyPermission(testUser, null);
        
        assertFalse("Should return false for null permissions", result);
    }

    @Test
    public void testHasAllPermissions_NullUser_ReturnsFalse() throws Exception {
        System.out.println("Testing hasAllPermissions with null user");
        
        List<String> permissions = Arrays.asList("USER_READ", "USER_WRITE");
        boolean result = authorizer.hasAllPermissions(null, permissions);
        
        assertFalse("Should return false for null user", result);
    }

    @Test
    public void testGetUserPermissions_NullUser_ReturnsEmptyList() throws Exception {
        System.out.println("Testing getUserPermissions with null user");
        
        List<Permission> result = authorizer.getUserPermissions(null);
        
        assertNotNull("Should not return null", result);
        assertTrue("Should return empty list for null user", result.isEmpty());
    }

    @Test
    public void testHasResourceAccess_NullUser_ReturnsFalse() throws Exception {
        System.out.println("Testing hasResourceAccess with null user");
        
        boolean result = authorizer.hasResourceAccess(null, "users", "read");
        
        assertFalse("Should return false for null user", result);
    }

    @Test
    public void testHasResourceAccess_NullResource_ReturnsFalse() throws Exception {
        System.out.println("Testing hasResourceAccess with null resource");
        
        boolean result = authorizer.hasResourceAccess(testUser, null, "read");
        
        assertFalse("Should return false for null resource", result);
    }

    @Test
    public void testHasResourceAccess_NullAction_ReturnsFalse() throws Exception {
        System.out.println("Testing hasResourceAccess with null action");
        
        boolean result = authorizer.hasResourceAccess(testUser, "users", null);
        
        assertFalse("Should return false for null action", result);
    }

    @Test
    public void testIsAdmin_NullUser_ReturnsFalse() throws Exception {
        System.out.println("Testing isAdmin with null user");
        
        boolean result = authorizer.isAdmin(null);
        
        assertFalse("Should return false for null user", result);
    }

    @Test
    public void testConstructor_DefaultConstructor_InitializesSuccessfully() {
        System.out.println("Testing default constructor");
        
        AuthorizerImpl newAuthorizer = new AuthorizerImpl();
        
        assertNotNull("Authorizer should be created successfully", newAuthorizer);
    }
}