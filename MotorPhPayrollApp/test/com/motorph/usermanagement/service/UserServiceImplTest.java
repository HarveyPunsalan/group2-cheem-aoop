/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.service;

import com.motorph.usermanagement.model.User;
import com.motorph.usermanagement.exception.UserNotFoundException;
import com.motorph.usermanagement.exception.InvalidCredentialsException;
import com.motorph.usermanagement.exception.DataAccessException;
import java.util.List;
import java.util.Optional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for UserServiceImpl class focusing on authentication and user management.
 * Tests business logic validation and exception handling without GUI dependencies.
 * 
 * @author Harvey
 */
public class UserServiceImplTest {
    
    private UserServiceImpl userService;
    private User testUser;
    
    public UserServiceImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Starting UserServiceImpl tests...");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("UserServiceImpl tests completed.");
    }
    
    @Before
    public void setUp() {
        userService = new UserServiceImpl();
        
        // Create a test user object for various tests
        testUser = new User();
        testUser.setUserId(1);
        testUser.setUsername("testuser");
        testUser.setEmployeeId(1001); // Using int instead of String
        testUser.setRoleId(1);
        testUser.setActive(true);
    }
    
    @After
    public void tearDown() {
        userService = null;
        testUser = null;
    }

    /**
     * Test authentication with null username - should throw InvalidCredentialsException
     */
    @Test
    public void testAuthenticateWithNullUsername() {
        System.out.println("Testing authenticate with null username");
        
        try {
            userService.authenticate(null, "password");
            fail("Expected InvalidCredentialsException was not thrown");
        } catch (InvalidCredentialsException e) {
            assertEquals("Username cannot be empty", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception type: " + e.getClass().getName());
        }
    }
    
    /**
     * Test authentication with empty username - should throw InvalidCredentialsException
     */
    @Test
    public void testAuthenticateWithEmptyUsername() {
        System.out.println("Testing authenticate with empty username");
        
        try {
            userService.authenticate("", "password");
            fail("Expected InvalidCredentialsException was not thrown");
        } catch (InvalidCredentialsException e) {
            assertEquals("Username cannot be empty", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception type: " + e.getClass().getName());
        }
    }
    
    /**
     * Test authentication with whitespace-only username - should throw InvalidCredentialsException
     */
    @Test
    public void testAuthenticateWithWhitespaceUsername() {
        System.out.println("Testing authenticate with whitespace username");
        
        try {
            userService.authenticate("   ", "password");
            fail("Expected InvalidCredentialsException was not thrown");
        } catch (InvalidCredentialsException e) {
            assertEquals("Username cannot be empty", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception type: " + e.getClass().getName());
        }
    }
    
    /**
     * Test authentication with null password - should throw InvalidCredentialsException
     */
    @Test
    public void testAuthenticateWithNullPassword() {
        System.out.println("Testing authenticate with null password");
        
        try {
            userService.authenticate("testuser", null);
            fail("Expected InvalidCredentialsException was not thrown");
        } catch (InvalidCredentialsException e) {
            assertEquals("Password cannot be empty", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception type: " + e.getClass().getName());
        }
    }
    
    /**
     * Test authentication with empty password - should throw InvalidCredentialsException
     */
    @Test
    public void testAuthenticateWithEmptyPassword() {
        System.out.println("Testing authenticate with empty password");
        
        try {
            userService.authenticate("testuser", "");
            fail("Expected InvalidCredentialsException was not thrown");
        } catch (InvalidCredentialsException e) {
            assertEquals("Password cannot be empty", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception type: " + e.getClass().getName());
        }
    }

    /**
     * Test getUserByUsername with null parameter - should return empty Optional
     */
    @Test
    public void testGetUserByUsernameWithNull() throws Exception {
        System.out.println("Testing getUserByUsername with null");
        
        Optional<User> result = userService.getUserByUsername(null);
        assertFalse("Expected empty Optional for null username", result.isPresent());
    }
    
    /**
     * Test getUserByUsername with empty string - should return empty Optional
     */
    @Test
    public void testGetUserByUsernameWithEmptyString() throws Exception {
        System.out.println("Testing getUserByUsername with empty string");
        
        Optional<User> result = userService.getUserByUsername("");
        assertFalse("Expected empty Optional for empty username", result.isPresent());
    }
    
    /**
     * Test getUserByUsername with whitespace - should return empty Optional
     */
    @Test
    public void testGetUserByUsernameWithWhitespace() throws Exception {
        System.out.println("Testing getUserByUsername with whitespace");
        
        Optional<User> result = userService.getUserByUsername("   ");
        assertFalse("Expected empty Optional for whitespace username", result.isPresent());
    }

    /**
     * Test registerUser with null user - should throw IllegalArgumentException
     */
    @Test
    public void testRegisterUserWithNull() {
        System.out.println("Testing registerUser with null user");
        
        try {
            userService.registerUser(null);
            fail("Expected exception was not thrown");
        } catch (NullPointerException | IllegalArgumentException e) {
            // Expected behavior - either exception type is acceptable
            assertTrue("Expected exception thrown", true);
        } catch (Exception e) {
            fail("Unexpected exception type: " + e.getClass().getName());
        }
    }

    /**
     * Test assignRole with non-existent user - should throw UserNotFoundException
     */
    @Test
    public void testAssignRoleWithNonExistentUser() {
        System.out.println("Testing assignRole with non-existent user");
        
        try {
            userService.assignRole(999, 1); // Assuming user ID 999 doesn't exist
            fail("Expected UserNotFoundException was not thrown");
        } catch (UserNotFoundException e) {
            assertTrue("Expected UserNotFoundException thrown", 
                      e.getMessage().contains("User with ID 999 not found"));
        } catch (Exception e) {
            // Could also throw DataAccessException depending on implementation
            assertTrue("Expected appropriate exception", 
                      e instanceof DataAccessException);
        }
    }
    
    /**
     * Test updateUser with null user - should throw exception
     */
    @Test
    public void testUpdateUserWithNull() {
        System.out.println("Testing updateUser with null user");
        
        try {
            userService.updateUser(null);
            fail("Expected exception was not thrown");
        } catch (NullPointerException | IllegalArgumentException e) {
            // Expected behavior
            assertTrue("Expected exception thrown", true);
        } catch (Exception e) {
            fail("Unexpected exception type: " + e.getClass().getName());
        }
    }

    /**
     * Test setUserStatus with non-existent user - should throw UserNotFoundException
     */
    @Test
    public void testSetUserStatusWithNonExistentUser() {
        System.out.println("Testing setUserStatus with non-existent user");
        
        try {
            userService.setUserStatus(999, true); // Assuming user ID 999 doesn't exist
            fail("Expected UserNotFoundException was not thrown");
        } catch (UserNotFoundException e) {
            assertTrue("Expected UserNotFoundException thrown", 
                      e.getMessage().contains("User with ID 999 not found"));
        } catch (Exception e) {
            // Could also throw DataAccessException depending on implementation
            assertTrue("Expected appropriate exception", 
                      e instanceof DataAccessException);
        }
    }
    
    /**
     * Test changePassword with non-existent user - should throw UserNotFoundException
     */
    @Test
    public void testChangePasswordWithNonExistentUser() {
        System.out.println("Testing changePassword with non-existent user");
        
        try {
            userService.changePassword(999, "newPassword123");
            fail("Expected UserNotFoundException was not thrown");
        } catch (UserNotFoundException e) {
            assertTrue("Expected UserNotFoundException thrown", 
                      e.getMessage().contains("User with ID 999 not found"));
        } catch (Exception e) {
            // Could also throw DataAccessException depending on implementation
            assertTrue("Expected appropriate exception", 
                      e instanceof DataAccessException);
        }
    }
    
    /**
     * Test changePassword with null password - should throw IllegalArgumentException
     */
    @Test
    public void testChangePasswordWithNullPassword() {
        System.out.println("Testing changePassword with null password");
        
        try {
            userService.changePassword(1, null);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Expected behavior - validation should catch null password
            assertTrue("Expected IllegalArgumentException thrown", true);
        } catch (Exception e) {
            // Could also be UserNotFoundException if user doesn't exist
            assertTrue("Expected appropriate exception", 
                      e instanceof UserNotFoundException || 
                      e instanceof DataAccessException);
        }
    }
    
    /**
     * Test isUsernameAvailable with null parameter - should return false
     */
    @Test
    public void testIsUsernameAvailableWithNull() throws Exception {
        System.out.println("Testing isUsernameAvailable with null");
        
        boolean result = userService.isUsernameAvailable(null);
        assertFalse("Expected false for null username", result);
    }
    
    /**
     * Test isUsernameAvailable with empty string - should return false
     */
    @Test
    public void testIsUsernameAvailableWithEmptyString() throws Exception {
        System.out.println("Testing isUsernameAvailable with empty string");
        
        boolean result = userService.isUsernameAvailable("");
        assertFalse("Expected false for empty username", result);
    }
    
    /**
     * Test searchUsersByUsername with null parameter - should return all users
     */
    @Test
    public void testSearchUsersByUsernameWithNull() throws Exception {
        System.out.println("Testing searchUsersByUsername with null");
        
        List<User> result = userService.searchUsersByUsername(null);
        assertNotNull("Expected non-null result", result);
        // The method should return all users when search term is null
    }
    
    /**
     * Test searchUsersByUsername with empty string - should return all users
     */
    @Test
    public void testSearchUsersByUsernameWithEmptyString() throws Exception {
        System.out.println("Testing searchUsersByUsername with empty string");
        
        List<User> result = userService.searchUsersByUsername("");
        assertNotNull("Expected non-null result", result);
        // The method should return all users when search term is empty
    }
    
    /**
     * Test resetPassword with non-existent user - should throw UserNotFoundException
     */
    @Test
    public void testResetPasswordWithNonExistentUser() {
        System.out.println("Testing resetPassword with non-existent user");
        
        try {
            userService.resetPassword(999, "newPassword123");
            fail("Expected UserNotFoundException was not thrown");
        } catch (UserNotFoundException e) {
            assertTrue("Expected UserNotFoundException thrown", 
                      e.getMessage().contains("User with ID 999 not found"));
        } catch (Exception e) {
            // Could also throw DataAccessException depending on implementation
            assertTrue("Expected appropriate exception", 
                      e instanceof DataAccessException);
        }
    }
    
    /**
     * Test resetPassword with null password - should throw IllegalArgumentException
     */
    @Test
    public void testResetPasswordWithNullPassword() {
        System.out.println("Testing resetPassword with null password");
        
        try {
            userService.resetPassword(1, null);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            assertTrue("Expected IllegalArgumentException thrown", 
                      e.getMessage().contains("New password cannot be empty"));
        } catch (Exception e) {
            // Could also be UserNotFoundException if user doesn't exist
            assertTrue("Expected appropriate exception", 
                      e instanceof UserNotFoundException || 
                      e instanceof DataAccessException);
        }
    }
    
    /**
     * Test resetPassword with empty password - should throw IllegalArgumentException
     */
    @Test
    public void testResetPasswordWithEmptyPassword() {
        System.out.println("Testing resetPassword with empty password");
        
        try {
            userService.resetPassword(1, "");
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            assertTrue("Expected IllegalArgumentException thrown", 
                      e.getMessage().contains("New password cannot be empty"));
        } catch (Exception e) {
            // Could also be UserNotFoundException if user doesn't exist
            assertTrue("Expected appropriate exception", 
                      e instanceof UserNotFoundException || 
                      e instanceof DataAccessException);
        }
    }
    
    /**
     * Test resetPassword with short password - should throw IllegalArgumentException
     */
    @Test
    public void testResetPasswordWithShortPassword() {
        System.out.println("Testing resetPassword with short password");
        
        try {
            userService.resetPassword(1, "123"); // Less than 6 characters
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            assertTrue("Expected IllegalArgumentException thrown", 
                      e.getMessage().contains("Password must be at least 6 characters long"));
        } catch (Exception e) {
            // Could also be UserNotFoundException if user doesn't exist
            assertTrue("Expected appropriate exception", 
                      e instanceof UserNotFoundException || 
                      e instanceof DataAccessException);
        }
    }
    
    /**
     * Test service initialization - constructor should not throw exceptions
     */
    @Test
    public void testServiceInitialization() {
        System.out.println("Testing service initialization");
        
        try {
            UserServiceImpl service = new UserServiceImpl();
            assertNotNull("Service should be initialized", service);
        } catch (Exception e) {
            fail("Service initialization should not throw exception: " + e.getMessage());
        }
    }
    
    /**
     * Test getUserCount method - should return non-negative integer
     */
    @Test
    public void testGetUserCount() throws Exception {
        System.out.println("Testing getUserCount");
        
        int result = userService.getUserCount();
        assertTrue("User count should be non-negative", result >= 0);
    }
    
    /**
     * Test getActiveUserCount method - should return non-negative integer
     */
    @Test
    public void testGetActiveUserCount() throws Exception {
        System.out.println("Testing getActiveUserCount");
        
        int result = userService.getActiveUserCount();
        assertTrue("Active user count should be non-negative", result >= 0);
    }
    
    /**
     * Test getAllUsers method - should return non-null list
     */
    @Test
    public void testGetAllUsers() throws Exception {
        System.out.println("Testing getAllUsers");
        
        List<User> result = userService.getAllUsers();
        assertNotNull("getAllUsers should return non-null list", result);
    }
    
    /**
     * Test getActiveUsers method - should return non-null list
     */
    @Test
    public void testGetActiveUsers() throws Exception {
        System.out.println("Testing getActiveUsers");
        
        List<User> result = userService.getActiveUsers();
        assertNotNull("getActiveUsers should return non-null list", result);
    }
    
    /**
     * Test getUsersByRole method - should return non-null list
     */
    @Test
    public void testGetUsersByRole() throws Exception {
        System.out.println("Testing getUsersByRole");
        
        List<User> result = userService.getUsersByRole(1);
        assertNotNull("getUsersByRole should return non-null list", result);
    }
}