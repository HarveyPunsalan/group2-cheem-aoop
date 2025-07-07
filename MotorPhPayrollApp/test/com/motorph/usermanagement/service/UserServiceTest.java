/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.service;

import com.motorph.usermanagement.model.User;
import com.motorph.usermanagement.exception.UserNotFoundException;
import com.motorph.usermanagement.exception.DuplicateUserException;
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
 * Test class for UserService interface.
 * This tests the interface contract using a mock implementation.
 * 
 * @author Harvey
 */
public class UserServiceTest {
    
    private UserService userService;
    
    public UserServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        userService = new UserServiceTestImpl();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of authenticate method with valid credentials.
     */
    @Test
    public void testAuthenticate_ValidCredentials() throws Exception {
        System.out.println("authenticate - Valid Credentials");
        String username = "testuser";
        String password = "password123";
        
        User result = userService.authenticate(username, password);
        
        assertNotNull("Authentication should return a user", result);
        assertEquals("Username should match", username, result.getUsername());
        assertTrue("User should be active", result.isActive());
    }
    
    /**
     * Test of authenticate method with invalid username.
     */
    @Test(expected = InvalidCredentialsException.class)
    public void testAuthenticate_InvalidUsername() throws Exception {
        System.out.println("authenticate - Invalid Username");
        String username = "nonexistent";
        String password = "password123";
        
        userService.authenticate(username, password);
    }
    
    /**
     * Test of authenticate method with invalid password.
     */
    @Test(expected = InvalidCredentialsException.class)
    public void testAuthenticate_InvalidPassword() throws Exception {
        System.out.println("authenticate - Invalid Password");
        String username = "testuser";
        String password = "wrongpassword";
        
        userService.authenticate(username, password);
    }
    
    /**
     * Test of authenticate method with null username.
     */
    @Test(expected = InvalidCredentialsException.class)
    public void testAuthenticate_NullUsername() throws Exception {
        System.out.println("authenticate - Null Username");
        String username = null;
        String password = "password123";
        
        userService.authenticate(username, password);
    }
    
    /**
     * Test of authenticate method with null password.
     */
    @Test(expected = InvalidCredentialsException.class)
    public void testAuthenticate_NullPassword() throws Exception {
        System.out.println("authenticate - Null Password");
        String username = "testuser";
        String password = null;
        
        userService.authenticate(username, password);
    }
    
    /**
     * Test of authenticate method with empty username.
     */
    @Test(expected = InvalidCredentialsException.class)
    public void testAuthenticate_EmptyUsername() throws Exception {
        System.out.println("authenticate - Empty Username");
        String username = "";
        String password = "password123";
        
        userService.authenticate(username, password);
    }
    
    /**
     * Test of authenticate method with empty password.
     */
    @Test(expected = InvalidCredentialsException.class)
    public void testAuthenticate_EmptyPassword() throws Exception {
        System.out.println("authenticate - Empty Password");
        String username = "testuser";
        String password = "";
        
        userService.authenticate(username, password);
    }

    /**
     * Test of getUserByUsername method with valid username.
     */
    @Test
    public void testGetUserByUsername_ValidUsername() throws Exception {
        System.out.println("getUserByUsername - Valid Username");
        String username = "testuser";
        
        Optional<User> result = userService.getUserByUsername(username);
        
        assertTrue("Should return a user", result.isPresent());
        assertEquals("Username should match", username, result.get().getUsername());
    }
    
    /**
     * Test of getUserByUsername method with invalid username.
     */
    @Test
    public void testGetUserByUsername_InvalidUsername() throws Exception {
        System.out.println("getUserByUsername - Invalid Username");
        String username = "nonexistent";
        
        Optional<User> result = userService.getUserByUsername(username);
        
        assertFalse("Should return empty optional", result.isPresent());
    }
    
    /**
     * Test of isUsernameAvailable method with available username.
     */
    @Test
    public void testIsUsernameAvailable_Available() throws Exception {
        System.out.println("isUsernameAvailable - Available Username");
        String username = "newuser";
        
        boolean result = userService.isUsernameAvailable(username);
        
        assertTrue("Username should be available", result);
    }
    
    /**
     * Test of isUsernameAvailable method with taken username.
     */
    @Test
    public void testIsUsernameAvailable_Taken() throws Exception {
        System.out.println("isUsernameAvailable - Taken Username");
        String username = "testuser";
        
        boolean result = userService.isUsernameAvailable(username);
        
        assertFalse("Username should be taken", result);
    }
    
    /**
     * Test of changePassword method with valid data.
     */
    @Test
    public void testChangePassword_ValidData() throws Exception {
        System.out.println("changePassword - Valid Data");
        int userId = 1;
        String newPassword = "newpassword123";
        
        boolean result = userService.changePassword(userId, newPassword);
        
        assertTrue("Password change should succeed", result);
    }
    
    /**
     * Test of changePassword method with invalid user ID.
     */
    @Test(expected = UserNotFoundException.class)
    public void testChangePassword_InvalidUserId() throws Exception {
        System.out.println("changePassword - Invalid User ID");
        int userId = 999;
        String newPassword = "newpassword123";
        
        userService.changePassword(userId, newPassword);
    }

    /**
     * Mock implementation of UserService for testing interface contract.
     */
    public class UserServiceTestImpl implements UserService {

        @Override
        public int registerUser(User user) throws DuplicateUserException, DataAccessException {
            if (user != null && "testuser".equals(user.getUsername())) {
                throw new DuplicateUserException("Username already exists");
            }
            return 1;
        }

        @Override
        public User authenticate(String username, String password) throws InvalidCredentialsException, DataAccessException {
            if (username == null || username.trim().isEmpty()) {
                throw new InvalidCredentialsException("Username cannot be empty");
            }
            if (password == null || password.isEmpty()) {
                throw new InvalidCredentialsException("Password cannot be empty");
            }
            
            if ("testuser".equals(username) && "password123".equals(password)) {
                User user = new User();
                user.setUserId(1);
                user.setUsername(username);
                user.setActive(true);
                user.setRoleId(1);
                return user;
            }
            
            throw new InvalidCredentialsException("Invalid credentials");
        }

        @Override
        public boolean assignRole(int userId, int roleId) throws UserNotFoundException, DataAccessException {
            if (userId == 999) {
                throw new UserNotFoundException("User not found");
            }
            return userId == 1;
        }

        @Override
        public boolean updateUser(User user) throws UserNotFoundException, DataAccessException {
            if (user == null || user.getUserId() == 999) {
                throw new UserNotFoundException("User not found");
            }
            return true;
        }

        @Override
        public Optional<User> getUserById(int userId) throws DataAccessException {
            if (userId == 1) {
                User user = new User();
                user.setUserId(1);
                user.setUsername("testuser");
                user.setActive(true);
                return Optional.of(user);
            }
            return Optional.empty();
        }

        @Override
        public Optional<User> getUserByUsername(String username) throws DataAccessException {
            if ("testuser".equals(username)) {
                User user = new User();
                user.setUserId(1);
                user.setUsername(username);
                user.setActive(true);
                return Optional.of(user);
            }
            return Optional.empty();
        }

        @Override
        public Optional<User> getUserByEmployeeId(int employeeId) throws DataAccessException {
            return Optional.empty();
        }

        @Override
        public List<User> getAllUsers() throws DataAccessException {
            return null;
        }

        @Override
        public List<User> getUsersByRole(int roleId) throws DataAccessException {
            return null;
        }

        @Override
        public List<User> getActiveUsers() throws DataAccessException {
            return null;
        }

        @Override
        public boolean setUserStatus(int userId, boolean isActive) throws UserNotFoundException, DataAccessException {
            if (userId == 999) {
                throw new UserNotFoundException("User not found");
            }
            return userId == 1;
        }

        @Override
        public boolean changePassword(int userId, String newPassword) throws UserNotFoundException, DataAccessException {
            if (userId == 999) {
                throw new UserNotFoundException("User not found");
            }
            return userId == 1;
        }

        @Override
        public boolean isUsernameAvailable(String username) throws DataAccessException {
            return !"testuser".equals(username);
        }

        @Override
        public int getUserCount() throws DataAccessException {
            return 1;
        }

        @Override
        public int getActiveUserCount() throws DataAccessException {
            return 1;
        }

        @Override
        public List<User> searchUsersByUsername(String searchTerm) throws DataAccessException {
            return null;
        }

        @Override
        public boolean resetPassword(int userId, String newPassword) throws UserNotFoundException, DataAccessException {
            if (userId == 999) {
                throw new UserNotFoundException("User not found");
            }
            return userId == 1;
        }
    }
}