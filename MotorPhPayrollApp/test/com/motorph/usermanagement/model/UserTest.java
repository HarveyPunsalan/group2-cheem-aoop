/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
public class UserTest {
    
    private User user;
    
    public UserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Starting User class tests...");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("User class tests completed.");
    }
    
    @Before
    public void setUp() {
        // Create a fresh User instance before each test
        user = new User();
    }
    
    @After
    public void tearDown() {
        user = null;
    }

    /**
     * Test default constructor initializes user correctly
     */
    @Test
    public void testDefaultConstructor() {
        User newUser = new User();
        assertTrue("New user should be active by default", newUser.isActive());
        assertNotNull("Account created timestamp should not be null", newUser.getAccountCreated());
        assertEquals("Default userId should be 0", 0, newUser.getUserId());
        assertNull("Default username should be null", newUser.getUsername());
    }

    /**
     * Test parameterized constructor with all fields
     */
    @Test
    public void testFullConstructor() {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        User testUser = new User(1, "testuser", "hashedpassword", 100, 2, now, now, true);
        
        assertEquals("UserId should match", 1, testUser.getUserId());
        assertEquals("Username should match", "testuser", testUser.getUsername());
        assertEquals("Password should match", "hashedpassword", testUser.getPasswordHashed());
        assertEquals("EmployeeId should match", 100, testUser.getEmployeeId());
        assertEquals("RoleId should match", 2, testUser.getRoleId());
        assertTrue("User should be active", testUser.isActive());
    }

    /**
     * Test simple constructor for new user creation
     */
    @Test
    public void testSimpleConstructor() {
        User testUser = new User("manuelgarcia", "hashed123", 50, 1);
        
        assertEquals("Username should match", "manuelgarcia", testUser.getUsername());
        assertEquals("Password should match", "hashed123", testUser.getPasswordHashed());
        assertEquals("EmployeeId should match", 50, testUser.getEmployeeId());
        assertEquals("RoleId should match", 1, testUser.getRoleId());
        assertTrue("New user should be active", testUser.isActive());
        assertNotNull("Account created should not be null", testUser.getAccountCreated());
    }

    /**
     * Test updateLastLogin method
     */
    @Test
    public void testUpdateLastLogin() {
        assertNull("Initial last login should be null", user.getLastLogin());
        
        user.updateLastLogin();
        
        assertNotNull("Last login should be set after update", user.getLastLogin());
        assertTrue("Last login should be recent", 
                   user.getLastLogin().after(Timestamp.valueOf(LocalDateTime.now().minusMinutes(1))));
    }

    /**
     * Test canLogin method with active user
     */
    @Test
    public void testCanLoginWhenActive() {
        user.setActive(true);
        assertTrue("Active user should be able to login", user.canLogin());
    }

    /**
     * Test canLogin method with inactive user
     */
    @Test
    public void testCanLoginWhenInactive() {
        user.setActive(false);
        assertFalse("Inactive user should not be able to login", user.canLogin());
    }

    /**
     * Test deactivateAccount method
     */
    @Test
    public void testDeactivateAccount() {
        user.setActive(true);
        assertTrue("User should be active initially", user.isActive());
        
        user.deactivateAccount();
        
        assertFalse("User should be inactive after deactivation", user.isActive());
    }

    /**
     * Test activateAccount method
     */
    @Test
    public void testActivateAccount() {
        user.setActive(false);
        assertFalse("User should be inactive initially", user.isActive());
        
        user.activateAccount();
        
        assertTrue("User should be active after activation", user.isActive());
    }

    /**
     * Test getter and setter for userId
     */
    @Test
    public void testUserIdGetterSetter() {
        user.setUserId(123);
        assertEquals("UserId should match set value", 123, user.getUserId());
    }

    /**
     * Test getter and setter for username
     */
    @Test
    public void testUsernameGetterSetter() {
        user.setUsername("testuser");
        assertEquals("Username should match set value", "testuser", user.getUsername());
    }

    /**
     * Test getter and setter for employeeId
     */
    @Test
    public void testEmployeeIdGetterSetter() {
        user.setEmployeeId(456);
        assertEquals("EmployeeId should match set value", 456, user.getEmployeeId());
    }

    /**
     * Test deprecated getEmployeeID method
     */
    @Test
    public void testGetEmployeeID() {
        user.setEmployeeId(789);
        assertEquals("EmployeeID string should match", "789", user.getEmployeeID());
    }

    /**
     * Test getter and setter for passwordHashed
     */
    @Test
    public void testPasswordHashedGetterSetter() {
        user.setPasswordHashed("hashed_password_123");
        assertEquals("Password hash should match", "hashed_password_123", user.getPasswordHashed());
    }

    /**
     * Test getter and setter for roleId
     */
    @Test
    public void testRoleIdGetterSetter() {
        user.setRoleId(2);
        assertEquals("RoleId should match set value", 2, user.getRoleId());
    }

    /**
     * Test getter and setter for accountCreated
     */
    @Test
    public void testAccountCreatedGetterSetter() {
        Timestamp testTime = Timestamp.valueOf("2024-01-15 10:30:00");
        user.setAccountCreated(testTime);
        assertEquals("AccountCreated should match set value", testTime, user.getAccountCreated());
    }

    /**
     * Test getter and setter for lastLogin
     */
    @Test
    public void testLastLoginGetterSetter() {
        Timestamp testTime = Timestamp.valueOf("2024-06-20 14:25:30");
        user.setLastLogin(testTime);
        assertEquals("LastLogin should match set value", testTime, user.getLastLogin());
    }

    /**
     * Test getter and setter for active status
     */
    @Test
    public void testActiveGetterSetter() {
        user.setActive(false);
        assertFalse("User should be inactive", user.isActive());
        
        user.setActive(true);
        assertTrue("User should be active", user.isActive());
    }

    /**
     * Test getter and setter for authenticationResult
     */
    @Test
    public void testAuthenticationResultGetterSetter() {
        user.setAuthenticationResult(true);
        assertTrue("Authentication result should be true", user.getAuthenticationResult());
        
        user.setAuthenticationResult(false);
        assertFalse("Authentication result should be false", user.getAuthenticationResult());
    }

    /**
     * Test toString method returns expected format
     */
    @Test
    public void testToString() {
        user.setUserId(1);
        user.setUsername("testuser");
        user.setEmployeeId(100);
        user.setRoleId(2);
        user.setActive(true);
        
        String result = user.toString();
        
        assertTrue("ToString should contain userId", result.contains("userId=1"));
        assertTrue("ToString should contain username", result.contains("username='testuser'"));
        assertTrue("ToString should contain employeeId", result.contains("employeeId=100"));
        assertTrue("ToString should contain roleId", result.contains("roleId=2"));
        assertTrue("ToString should contain isActive", result.contains("isActive=true"));
    }

    /**
     * Test that toString doesn't contain sensitive information
     */
    @Test
    public void testToStringNoSensitiveData() {
        user.setPasswordHashed("secret_password");
        String result = user.toString();
        
        assertFalse("ToString should not contain password", result.contains("secret_password"));
    }
}