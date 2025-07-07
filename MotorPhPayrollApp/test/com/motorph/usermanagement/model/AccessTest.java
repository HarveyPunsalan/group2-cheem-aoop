/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.model;

import com.motorph.employeemanagement.model.Employee;
import com.motorph.payrollprocessing.model.payroll.PayPeriod;
import javax.swing.JFrame;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Focuses on login logic only
 * @author Harvey 
 */
public class AccessTest {
    
    private Admin testAdmin;
    private NonAdmin testNonAdmin;
    private User testUser;
    
    public AccessTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        testAdmin = new Admin("admin123", "hashedpass", 1001, 1);
        testNonAdmin = new NonAdmin("user456", "hashedpass", 1002, 2);
        testUser = new User("genericuser", "hashedpass", 1003, 3);
    }
    
    @After
    public void tearDown() {
        testAdmin = null;
        testNonAdmin = null;
        testUser = null;
    }

    /**
     * Test User canLogin method with active account
     */
    @Test
    public void testUserCanLogin_ActiveAccount() {
        System.out.println("Testing canLogin with active account");
        
        assertTrue("Active user should be able to login", testUser.canLogin());
    }

    /**
     * Test User canLogin method with inactive account
     */
    @Test
    public void testUserCanLogin_InactiveAccount() {
        System.out.println("Testing canLogin with inactive account");
        
        testUser.deactivateAccount();
        assertFalse("Inactive user should not be able to login", testUser.canLogin());
    }

    /**
     * Test User account activation
     */
    @Test
    public void testUserAccountActivation() {
        System.out.println("Testing account activation");
        
        testUser.deactivateAccount();
        assertFalse("Account should be inactive", testUser.isActive());
        
        testUser.activateAccount();
        assertTrue("Account should be active after activation", testUser.isActive());
    }

    /**
     * Test User account deactivation
     */
    @Test
    public void testUserAccountDeactivation() {
        System.out.println("Testing account deactivation");
        
        assertTrue("Account should be active initially", testUser.isActive());
        
        testUser.deactivateAccount();
        assertFalse("Account should be inactive after deactivation", testUser.isActive());
    }

    /**
     * Test Admin isAdmin method
     */
    @Test
    public void testAdminIsAdmin() {
        System.out.println("Testing Admin isAdmin method");
        
        assertTrue("Admin should return true for isAdmin()", testAdmin.isAdmin());
    }

    /**
     * Test NonAdmin isAdmin method
     */
    @Test
    public void testNonAdminIsAdmin() {
        System.out.println("Testing NonAdmin isAdmin method");
        
        assertFalse("NonAdmin should return false for isAdmin()", testNonAdmin.isAdmin());
    }

    /**
     * Test User authentication result setting
     */
    @Test
    public void testUserAuthenticationResult() {
        System.out.println("Testing authentication result setting");
        
        testUser.setAuthenticationResult(true);
        assertTrue("Authentication result should be true", testUser.getAuthenticationResult());
        
        testUser.setAuthenticationResult(false);
        assertFalse("Authentication result should be false", testUser.getAuthenticationResult());
    }

    /**
     * Test User constructor with credentials
     */
    @Test
    public void testUserConstructorWithCredentials() {
        System.out.println("Testing User constructor with credentials");
        
        User user = new User("testuser", "testpass", 123, 2);
        
        assertEquals("Username should match", "testuser", user.getUsername());
        assertEquals("Password should match", "testpass", user.getPasswordHashed());
        assertEquals("Employee ID should match", 123, user.getEmployeeId());
        assertEquals("Role ID should match", 2, user.getRoleId());
        assertTrue("New user should be active", user.isActive());
    }

    /**
     * Test Admin constructor with credentials
     */
    @Test
    public void testAdminConstructorWithCredentials() {
        System.out.println("Testing Admin constructor with credentials");
        
        Admin admin = new Admin("adminuser", "adminpass", 456, 1);
        
        assertEquals("Username should match", "adminuser", admin.getUsername());
        assertEquals("Password should match", "adminpass", admin.getPasswordHashed());
        assertEquals("Employee ID should match", 456, admin.getEmployeeId());
        assertEquals("Role ID should match", 1, admin.getRoleId());
        assertTrue("New admin should be active", admin.isActive());
        assertTrue("Admin should return true for isAdmin()", admin.isAdmin());
    }

    /**
     * Test NonAdmin constructor with credentials
     */
    @Test
    public void testNonAdminConstructorWithCredentials() {
        System.out.println("Testing NonAdmin constructor with credentials");
        
        NonAdmin nonAdmin = new NonAdmin("empuser", "emppass", 789, 3);
        
        assertEquals("Username should match", "empuser", nonAdmin.getUsername());
        assertEquals("Password should match", "emppass", nonAdmin.getPasswordHashed());
        assertEquals("Employee ID should match", 789, nonAdmin.getEmployeeId());
        assertEquals("Role ID should match", 3, nonAdmin.getRoleId());
        assertTrue("New non-admin should be active", nonAdmin.isActive());
        assertFalse("NonAdmin should return false for isAdmin()", nonAdmin.isAdmin());
    }

    /**
     * Test User default constructor
     */
    @Test
    public void testUserDefaultConstructor() {
        System.out.println("Testing User default constructor");
        
        User user = new User();
        
        assertTrue("Default user should be active", user.isActive());
        assertNotNull("Account creation timestamp should be set", user.getAccountCreated());
        assertEquals("Default user ID should be 0", 0, user.getUserId());
    }

    /**
     * Test User last login update
     */
    @Test
    public void testUserLastLoginUpdate() {
        System.out.println("Testing last login update");
        
        assertNull("Initial last login should be null", testUser.getLastLogin());
        
        testUser.updateLastLogin();
        
        assertNotNull("Last login should be set after update", testUser.getLastLogin());
    }

    /**
     * Test User toString method
     */
    @Test
    public void testUserToString() {
        System.out.println("Testing User toString method");
        
        testUser.setUserId(100);
        String result = testUser.toString();
        
        assertNotNull("toString should not return null", result);
        assertTrue("toString should contain username", result.contains("genericuser"));
        assertTrue("toString should contain employee ID", result.contains("1003"));
    }

    /**
     * Test Admin copy constructor
     */
    @Test
    public void testAdminCopyConstructor() {
        System.out.println("Testing Admin copy constructor");
        
        Admin copiedAdmin = new Admin(testUser);
        
        assertEquals("Username should match", testUser.getUsername(), copiedAdmin.getUsername());
        assertEquals("Employee ID should match", testUser.getEmployeeId(), copiedAdmin.getEmployeeId());
        assertTrue("Copied admin should return true for isAdmin()", copiedAdmin.isAdmin());
    }

    /**
     * Test NonAdmin copy constructor
     */
    @Test
    public void testNonAdminCopyConstructor() {
        System.out.println("Testing NonAdmin copy constructor");
        
        NonAdmin copiedNonAdmin = new NonAdmin(testUser);
        
        assertEquals("Username should match", testUser.getUsername(), copiedNonAdmin.getUsername());
        assertEquals("Employee ID should match", testUser.getEmployeeId(), copiedNonAdmin.getEmployeeId());
        assertFalse("Copied non-admin should return false for isAdmin()", copiedNonAdmin.isAdmin());
    }
}