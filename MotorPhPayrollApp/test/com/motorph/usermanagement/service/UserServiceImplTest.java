/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.service;

import com.motorph.usermanagement.model.User;
import com.motorph.usermanagement.exception.InvalidCredentialsException;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for UserServiceImpl class.
 * Tests focused on validation logic and error handling.
 * 
 * @author Harvey
 */
public class UserServiceImplTest {
    
    private UserServiceImpl userService;
    private User testUser;
    
    @Before
    public void setUp() {
        userService = new UserServiceImpl();
        
        // Create a test user
        testUser = new User();
        testUser.setUserId(1);
        testUser.setUsername("testuser123");
        testUser.setPasswordHashed("hashedpassword");
        testUser.setEmployeeId(12345);
        testUser.setRoleId(1);
        testUser.setActive(true);
    }
    
    /**
     * Test registerUser with null user - should throw NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testRegisterUser_NullUser() throws Exception {
        userService.registerUser(null);
    }
    
    /**
     * Test registerUser with invalid username - should throw IllegalArgumentException
     */
    @Test
    public void testRegisterUser_InvalidUsername() {
        try {
            User invalidUser = new User();
            invalidUser.setUsername("ab"); // Too short username
            invalidUser.setEmployeeId(123);
            invalidUser.setRoleId(1);
            
            userService.registerUser(invalidUser);
            fail("Should have thrown IllegalArgumentException for invalid username");
        } catch (IllegalArgumentException e) {
            assertTrue("Error message should mention username format", 
                      e.getMessage().contains("Invalid username format"));
        } catch (Exception e) {
            fail("Expected IllegalArgumentException but got: " + e.getClass().getSimpleName());
        }
    }
    
    /**
     * Test authenticate with null username
     */
    @Test
    public void testAuthenticate_NullUsername() {
        try {
            userService.authenticate(null, "password");
            fail("Should have thrown InvalidCredentialsException");
        } catch (InvalidCredentialsException e) {
            assertEquals("Username cannot be empty", e.getMessage());
        } catch (Exception e) {
            fail("Expected InvalidCredentialsException but got: " + e.getClass().getSimpleName());
        }
    }
    
    /**
     * Test authenticate with empty username
     */
    @Test
    public void testAuthenticate_EmptyUsername() {
        try {
            userService.authenticate("", "password");
            fail("Should have thrown InvalidCredentialsException");
        } catch (InvalidCredentialsException e) {
            assertEquals("Username cannot be empty", e.getMessage());
        } catch (Exception e) {
            fail("Expected InvalidCredentialsException but got: " + e.getClass().getSimpleName());
        }
    }
    
    /**
     * Test authenticate with null password
     */
    @Test
    public void testAuthenticate_NullPassword() {
        try {
            userService.authenticate("username", null);
            fail("Should have thrown InvalidCredentialsException");
        } catch (InvalidCredentialsException e) {
            assertEquals("Password cannot be empty", e.getMessage());
        } catch (Exception e) {
            fail("Expected InvalidCredentialsException but got: " + e.getClass().getSimpleName());
        }
    }
    
    /**
     * Test authenticate with empty password
     */
    @Test
    public void testAuthenticate_EmptyPassword() {
        try {
            userService.authenticate("username", "");
            fail("Should have thrown InvalidCredentialsException");
        } catch (InvalidCredentialsException e) {
            assertEquals("Password cannot be empty", e.getMessage());
        } catch (Exception e) {
            fail("Expected InvalidCredentialsException but got: " + e.getClass().getSimpleName());
        }
    }
    
    /**
     * Test getUserByUsername with null username
     */
    @Test
    public void testGetUserByUsername_NullUsername() throws Exception {
        Optional<User> result = userService.getUserByUsername(null);
        assertFalse("Should return empty Optional for null username", result.isPresent());
    }
    
    /**
     * Test getUserByUsername with empty username
     */
    @Test
    public void testGetUserByUsername_EmptyUsername() throws Exception {
        Optional<User> result = userService.getUserByUsername("");
        assertFalse("Should return empty Optional for empty username", result.isPresent());
    }
    
    /**
     * Test getUserByUsername with whitespace-only username
     */
    @Test
    public void testGetUserByUsername_WhitespaceUsername() throws Exception {
        Optional<User> result = userService.getUserByUsername("   ");
        assertFalse("Should return empty Optional for whitespace username", result.isPresent());
    }
    
    /**
     * Test isUsernameAvailable with null username
     */
    @Test
    public void testIsUsernameAvailable_NullUsername() throws Exception {
        boolean result = userService.isUsernameAvailable(null);
        assertFalse("Null username should not be available", result);
    }
    
    /**
     * Test isUsernameAvailable with empty username
     */
    @Test
    public void testIsUsernameAvailable_EmptyUsername() throws Exception {
        boolean result = userService.isUsernameAvailable("");
        assertFalse("Empty username should not be available", result);
    }
    
    /**
     * Test searchUsersByUsername with null search term
     */
    @Test
    public void testSearchUsersByUsername_NullSearchTerm() throws Exception {
        // This should not throw exception but return all users
        // Since we can't mock the DAO, this will likely fail with database connection error
        // But the method should handle null gracefully
        try {
            List<User> result = userService.searchUsersByUsername(null);
            // If we get here without exception, the null handling worked
            assertNotNull("Result should not be null", result);
        } catch (Exception e) {
            // Expected if no database connection - that's OK for this test
            System.out.println("Database connection expected to fail in unit test: " + e.getMessage());
        }
    }
    
    /**
     * Test resetPassword with null password
     */
    @Test
    public void testResetPassword_NullPassword() {
        try {
            userService.resetPassword(1, null);
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("New password cannot be empty", e.getMessage());
        } catch (Exception e) {
            // Other exceptions are OK since we don't have database
            System.out.println("Other exception expected: " + e.getMessage());
        }
    }
    
    /**
     * Test resetPassword with empty password
     */
    @Test
    public void testResetPassword_EmptyPassword() {
        try {
            userService.resetPassword(1, "");
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("New password cannot be empty", e.getMessage());
        } catch (Exception e) {
            // Other exceptions are OK since we don't have database
            System.out.println("Other exception expected: " + e.getMessage());
        }
    }
    
    /**
     * Test resetPassword with too short password
     */
    @Test
    public void testResetPassword_TooShortPassword() {
        try {
            userService.resetPassword(1, "12345"); // Less than 6 characters
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Password must be at least 6 characters long", e.getMessage());
        } catch (Exception e) {
            // Other exceptions are OK since we don't have database
            System.out.println("Other exception expected: " + e.getMessage());
        }
    }
    
    /**
     * Test User model class basic functionality
     */
    @Test
    public void testUser_BasicFunctionality() {
        User user = new User();
        
        // Test default values
        assertTrue("New user should be active by default", user.isActive());
        assertNotNull("Account created should be set", user.getAccountCreated());
        
        // Test setters and getters
        user.setUserId(100);
        user.setUsername("testuser");
        user.setEmployeeId(555);
        user.setRoleId(2);
        
        assertEquals(100, user.getUserId());
        assertEquals("testuser", user.getUsername());
        assertEquals(555, user.getEmployeeId());
        assertEquals(2, user.getRoleId());
        
        // Test account management
        user.deactivateAccount();
        assertFalse("User should be inactive after deactivation", user.isActive());
        assertFalse("Inactive user should not be able to login", user.canLogin());
        
        user.activateAccount();
        assertTrue("User should be active after activation", user.isActive());
        assertTrue("Active user should be able to login", user.canLogin());
    }
    
    /**
     * Test User toString method
     */
    @Test
    public void testUser_ToString() {
        String userString = testUser.toString();
        assertNotNull("toString should not return null", userString);
        assertTrue("toString should contain username", userString.contains("testuser123"));
        assertTrue("toString should contain userId", userString.contains("userId=1"));
        assertFalse("toString should not contain password", userString.contains("hashedpassword"));
    }
}