/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.service;

import com.motorph.usermanagement.model.User;
import com.motorph.usermanagement.dao.UserDAO;
import com.motorph.usermanagement.exception.InvalidCredentialsException;
import com.motorph.usermanagement.exception.DataAccessException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for UserServiceImpl focusing on employee login functionality.
 * These tests cover various scenarios that can happen during login attempts.
 *
 * @author Harvey
 */
public class UserServiceImplTest {
    
    private UserServiceImpl userService;
    private MockUserDAO mockUserDAO;
    
    public UserServiceImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Starting UserServiceImpl login functionality tests...");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("Completed UserServiceImpl login functionality tests.");
    }
    
    @Before
    public void setUp() {
        // Initialize mock DAO to simulate database operations
        mockUserDAO = new MockUserDAO();
        // Create service instance with mock DAO for testing
        userService = new UserServiceImpl(mockUserDAO);
    }
    
    @After
    public void tearDown() {
        userService = null;
        mockUserDAO = null;
    }

    /**
     * Test successful login with valid credentials.
     * This is the happy path scenario where everything works correctly.
     */
    @Test
    public void testSuccessfulLogin() throws Exception {
        System.out.println("Testing successful employee login...");
        
        // Setup test data - create a valid active user
        User testUser = new User(1, "john.doe", "password123", 1001, 2, 
                               Timestamp.valueOf(LocalDateTime.now().minusDays(30)), 
                               null, true);
        
        // Configure mock to return this user when searched
        mockUserDAO.setUserToReturn(testUser);
        
        // Attempt login with correct credentials
        User result = userService.authenticate("john.doe", "password123");
        
        // Verify the login was successful
        assertNotNull("Login should return a user object", result);
        assertEquals("Username should match", "john.doe", result.getUsername());
        assertEquals("Employee ID should match", 1001, result.getEmployeeId());
        assertTrue("User should be active", result.isActive());
        
        // Verify that last login was updated
        assertTrue("Last login should have been updated", mockUserDAO.wasLastLoginUpdated());
    }
    
    /**
     * Test login failure with empty username.
     * Empty usernames should not be allowed.
     */
    @Test
    public void testLoginWithEmptyUsername() {
        System.out.println("Testing login with empty username...");
        
        try {
            userService.authenticate("", "password123");
            fail("Should have thrown InvalidCredentialsException for empty username");
        } catch (InvalidCredentialsException e) {
            assertEquals("Username cannot be empty", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception type: " + e.getClass().getName());
        }
    }
    
    /**
     * Test login failure with null username.
     * Null usernames should be handled gracefully.
     */
    @Test
    public void testLoginWithNullUsername() {
        System.out.println("Testing login with null username...");
        
        try {
            userService.authenticate(null, "password123");
            fail("Should have thrown InvalidCredentialsException for null username");
        } catch (InvalidCredentialsException e) {
            assertEquals("Username cannot be empty", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception type: " + e.getClass().getName());
        }
    }
    
    /**
     * Test login failure with empty password.
     * Empty passwords should not be allowed.
     */
    @Test
    public void testLoginWithEmptyPassword() {
        System.out.println("Testing login with empty password...");
        
        try {
            userService.authenticate("john.doe", "");
            fail("Should have thrown InvalidCredentialsException for empty password");
        } catch (InvalidCredentialsException e) {
            assertEquals("Password cannot be empty", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception type: " + e.getClass().getName());
        }
    }
    
    /**
     * Test login failure with null password.
     * Null passwords should be handled gracefully.
     */
    @Test
    public void testLoginWithNullPassword() {
        System.out.println("Testing login with null password...");
        
        try {
            userService.authenticate("john.doe", null);
            fail("Should have thrown InvalidCredentialsException for null password");
        } catch (InvalidCredentialsException e) {
            assertEquals("Password cannot be empty", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception type: " + e.getClass().getName());
        }
    }
    
    /**
     * Test login failure with non-existent username.
     * Users that don't exist should not be able to login.
     */
    @Test
    public void testLoginWithNonExistentUser() {
        System.out.println("Testing login with non-existent user...");
        
        // Configure mock to return no user (user doesn't exist)
        mockUserDAO.setUserToReturn(null);
        
        try {
            userService.authenticate("nonexistent.user", "password123");
            fail("Should have thrown InvalidCredentialsException for non-existent user");
        } catch (InvalidCredentialsException e) {
            assertEquals("Invalid username or password", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception type: " + e.getClass().getName());
        }
    }
    
    /**
     * Test login failure with inactive user account.
     * Inactive users should not be able to login even with correct credentials.
     */
    @Test
    public void testLoginWithInactiveUser() {
        System.out.println("Testing login with inactive user account...");
        
        // Create an inactive user
        User inactiveUser = new User(2, "jane.smith", "password456", 1002, 2, 
                                   Timestamp.valueOf(LocalDateTime.now().minusDays(30)), 
                                   null, false); // inactive
        
        mockUserDAO.setUserToReturn(inactiveUser);
        
        try {
            userService.authenticate("jane.smith", "password456");
            fail("Should have thrown InvalidCredentialsException for inactive user");
        } catch (InvalidCredentialsException e) {
            assertEquals("User account is deactivated", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception type: " + e.getClass().getName());
        }
    }
    
    /**
     * Test login failure with wrong password.
     * Incorrect passwords should not allow login.
     */
    @Test
    public void testLoginWithWrongPassword() {
        System.out.println("Testing login with incorrect password...");
        
        // Create a valid user with a specific password
        User testUser = new User(3, "bob.wilson", "correctpassword", 1003, 2, 
                               Timestamp.valueOf(LocalDateTime.now().minusDays(30)), 
                               null, true);
        
        mockUserDAO.setUserToReturn(testUser);
        
        try {
            userService.authenticate("bob.wilson", "wrongpassword");
            fail("Should have thrown InvalidCredentialsException for wrong password");
        } catch (InvalidCredentialsException e) {
            assertEquals("Invalid username or password", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception type: " + e.getClass().getName());
        }
    }
    
    /**
     * Test login with user that has no password set.
     * Users without passwords should not be able to login.
     */
    @Test
    public void testLoginWithUserWithoutPassword() {
        System.out.println("Testing login with user that has no password set...");
        
        // Create a user without a password
        User userWithoutPassword = new User(4, "alice.brown", null, 1004, 2, 
                                          Timestamp.valueOf(LocalDateTime.now().minusDays(30)), 
                                          null, true);
        
        mockUserDAO.setUserToReturn(userWithoutPassword);
        
        try {
            userService.authenticate("alice.brown", "anypassword");
            fail("Should have thrown InvalidCredentialsException for user without password");
        } catch (InvalidCredentialsException e) {
            assertEquals("No password set for user", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception type: " + e.getClass().getName());
        }
    }
    
    /**
     * Test login handles whitespace in username properly.
     * Usernames with leading/trailing spaces should be trimmed.
     */
    @Test
    public void testLoginTrimsWhitespaceFromUsername() throws Exception {
        System.out.println("Testing login trims whitespace from username...");
        
        User testUser = new User(5, "mike.davis", "password789", 1005, 2, 
                               Timestamp.valueOf(LocalDateTime.now().minusDays(30)), 
                               null, true);
        
        mockUserDAO.setUserToReturn(testUser);
        
        // Test with spaces around username
        User result = userService.authenticate("  mike.davis  ", "password789");
        
        assertNotNull("Login should succeed with trimmed username", result);
        assertEquals("Username should match", "mike.davis", result.getUsername());
        assertEquals("Should have searched for trimmed username", "mike.davis", mockUserDAO.getLastSearchedUsername());
    }
    
    /**
     * Test database error handling during authentication.
     * Database errors should be properly handled and wrapped.
     */
    @Test
    public void testDatabaseErrorHandling() {
        System.out.println("Testing database error handling...");
        
        // Configure mock to throw database error
        mockUserDAO.setShouldThrowDatabaseError(true);
        
        try {
            userService.authenticate("test.user", "password");
            fail("Should have thrown DataAccessException for database error");
        } catch (DataAccessException e) {
            assertEquals("Authentication failed due to system error", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception type: " + e.getClass().getName());
        }
    }

    /**
     * Mock implementation of UserDAO for testing purposes.
     * This allows us to control what the DAO returns without needing a real database.
     */
    private static class MockUserDAO implements UserDAO {
        private User userToReturn;
        private boolean lastLoginUpdated = false;
        private boolean shouldThrowDatabaseError = false;
        private String lastSearchedUsername;
        
        public void setUserToReturn(User user) {
            this.userToReturn = user;
        }
        
        public boolean wasLastLoginUpdated() {
            return lastLoginUpdated;
        }
        
        public void setShouldThrowDatabaseError(boolean shouldThrow) {
            this.shouldThrowDatabaseError = shouldThrow;
        }
        
        public String getLastSearchedUsername() {
            return lastSearchedUsername;
        }
        
        @Override
        public Optional<User> findById(int userId) throws DataAccessException {
            if (shouldThrowDatabaseError) {
                throw new DataAccessException("Simulated database error");
            }
            return userToReturn != null ? Optional.of(userToReturn) : Optional.empty();
        }
        
        @Override
        public Optional<User> findByUsername(String username) throws DataAccessException {
            if (shouldThrowDatabaseError) {
                throw new DataAccessException("Simulated database error");
            }
            
            this.lastSearchedUsername = username;
            
            if (userToReturn != null && userToReturn.getUsername().equals(username)) {
                return Optional.of(userToReturn);
            }
            return Optional.empty();
        }
        
        @Override
        public boolean updateLastLogin(int userId) throws DataAccessException {
            if (shouldThrowDatabaseError) {
                throw new DataAccessException("Simulated database error");
            }
            
            lastLoginUpdated = true;
            return true;
        }
        
        @Override
        public boolean updatePasswordHash(int userId, String hashedPassword) throws DataAccessException {
            if (shouldThrowDatabaseError) {
                throw new DataAccessException("Simulated database error");
            }
            return true;
        }
    }
}