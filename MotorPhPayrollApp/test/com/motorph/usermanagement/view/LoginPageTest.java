/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.view;

import com.motorph.usermanagement.model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.Method;

/**
 * Tests login functionality for SystemAdmin, Admin, and NonAdmin users
 * @author Harvey
 */
public class LoginPageTest {
    
    private LoginPage loginPage;
    
    public LoginPageTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        loginPage = new LoginPage();
    }
    
    @After
    public void tearDown() {
        if (loginPage != null) {
            loginPage.dispose();
        }
    }

    /**
     * Test SystemAdmin bypass functionality
     */
    @Test
    public void testSystemAdminBypass() throws Exception {
        // Use reflection to test the private method
        Method method = LoginPage.class.getDeclaredMethod("isSystemAdminBypass", String.class, String.class);
        method.setAccessible(true);
        
        // Test valid systemadmin credentials
        boolean result = (boolean) method.invoke(loginPage, "systemadmin", "systemtest123");
        assertTrue("SystemAdmin bypass should work with correct credentials", result);
        
        // Test invalid credentials
        boolean invalidResult = (boolean) method.invoke(loginPage, "systemadmin", "wrongpassword");
        assertFalse("SystemAdmin bypass should fail with wrong password", invalidResult);
        
        boolean invalidUser = (boolean) method.invoke(loginPage, "wronguser", "systemtest123");
        assertFalse("SystemAdmin bypass should fail with wrong username", invalidUser);
    }

    /**
     * Test Admin role detection for manuel.garcia
     */
    @Test
    public void testAdminRoleDetection() throws Exception {
        // Create a test user representing manuel.garcia
        User adminUser = new User();
        adminUser.setUsername("manuel.garcia");
        adminUser.setEmployeeId(1);
        adminUser.setRoleId(1); // Admin role
        
        // Use reflection to test the private method
        Method method = LoginPage.class.getDeclaredMethod("isAdminRole", User.class);
        method.setAccessible(true);
        
        boolean result = (boolean) method.invoke(loginPage, adminUser);
        assertTrue("manuel.garcia should be detected as admin", result);
        
        // Test with different user
        User nonAdminUser = new User();
        nonAdminUser.setUsername("other.user");
        nonAdminUser.setEmployeeId(5);
        nonAdminUser.setRoleId(2);
        
        boolean nonAdminResult = (boolean) method.invoke(loginPage, nonAdminUser);
        assertFalse("Other users should not be detected as admin", nonAdminResult);
    }

    /**
     * Test NonAdmin role detection for andrea.villanueva
     */
    @Test
    public void testNonAdminRoleDetection() throws Exception {
        // Create a test user representing andrea.villanueva
        User nonAdminUser = new User();
        nonAdminUser.setUsername("andrea.villanueva");
        nonAdminUser.setEmployeeId(2);
        nonAdminUser.setRoleId(2); // NonAdmin role
        
        // Use reflection to test the private method
        Method method = LoginPage.class.getDeclaredMethod("isNonAdminRole", User.class);
        method.setAccessible(true);
        
        boolean result = (boolean) method.invoke(loginPage, nonAdminUser);
        assertTrue("andrea.villanueva should be detected as non-admin", result);
        
        // Test with admin user
        User adminUser = new User();
        adminUser.setUsername("manuel.garcia");
        adminUser.setEmployeeId(1);
        adminUser.setRoleId(1);
        
        boolean adminResult = (boolean) method.invoke(loginPage, adminUser);
        assertFalse("Admin users should not be detected as non-admin", adminResult);
    }

    /**
     * Test SystemAdmin role detection
     */
    @Test
    public void testSystemAdminRoleDetection() throws Exception {
        // Create a test user with SystemAdmin role
        User systemAdminUser = new User();
        systemAdminUser.setUsername("systemadmin");
        systemAdminUser.setEmployeeId(0);
        systemAdminUser.setRoleId(0); // SystemAdmin role
        
        // Use reflection to test the private method
        Method method = LoginPage.class.getDeclaredMethod("isSystemAdminRole", User.class);
        method.setAccessible(true);
        
        boolean result = (boolean) method.invoke(loginPage, systemAdminUser);
        assertTrue("User with role ID 0 should be detected as SystemAdmin", result);
        
        // Test with regular user
        User regularUser = new User();
        regularUser.setUsername("regular.user");
        regularUser.setEmployeeId(3);
        regularUser.setRoleId(2);
        
        boolean regularResult = (boolean) method.invoke(loginPage, regularUser);
        assertFalse("Regular users should not be detected as SystemAdmin", regularResult);
    }

    /**
     * Test input validation - empty username
     */
    @Test
    public void testValidateInput_EmptyUsername() throws Exception {
        Method method = LoginPage.class.getDeclaredMethod("validateInput", String.class, String.class);
        method.setAccessible(true);
        
        boolean result = (boolean) method.invoke(loginPage, "", "password123");
        assertFalse("Validation should fail for empty username", result);
    }

    /**
     * Test input validation - empty password
     */
    @Test
    public void testValidateInput_EmptyPassword() throws Exception {
        Method method = LoginPage.class.getDeclaredMethod("validateInput", String.class, String.class);
        method.setAccessible(true);
        
        boolean result = (boolean) method.invoke(loginPage, "username", "");
        assertFalse("Validation should fail for empty password", result);
    }

    /**
     * Test input validation - valid inputs
     */
    @Test
    public void testValidateInput_ValidInputs() throws Exception {
        Method method = LoginPage.class.getDeclaredMethod("validateInput", String.class, String.class);
        method.setAccessible(true);
        
        boolean result = (boolean) method.invoke(loginPage, "username", "password");
        assertTrue("Validation should pass for valid inputs", result);
    }

    /**
     * Test the three user types with their expected roles
     */
    @Test
    public void testUserRoleAssignments() throws Exception {
        // Test SystemAdmin
        User systemAdmin = new User();
        systemAdmin.setUsername("systemadmin");
        systemAdmin.setRoleId(0);
        systemAdmin.setEmployeeId(0);
        
        Method isSystemAdminMethod = LoginPage.class.getDeclaredMethod("isSystemAdminRole", User.class);
        isSystemAdminMethod.setAccessible(true);
        assertTrue("SystemAdmin should have role ID 0", 
                  (boolean) isSystemAdminMethod.invoke(loginPage, systemAdmin));
        
        // Test Admin - manuel.garcia
        User admin = new User();
        admin.setUsername("manuel.garcia");
        admin.setRoleId(1);
        admin.setEmployeeId(1);
        
        Method isAdminMethod = LoginPage.class.getDeclaredMethod("isAdminRole", User.class);
        isAdminMethod.setAccessible(true);
        assertTrue("manuel.garcia should be recognized as admin", 
                  (boolean) isAdminMethod.invoke(loginPage, admin));
        
        // Test NonAdmin - andrea.villanueva
        User nonAdmin = new User();
        nonAdmin.setUsername("andrea.villanueva");
        nonAdmin.setRoleId(2);
        nonAdmin.setEmployeeId(2);
        
        Method isNonAdminMethod = LoginPage.class.getDeclaredMethod("isNonAdminRole", User.class);
        isNonAdminMethod.setAccessible(true);
        assertTrue("andrea.villanueva should be recognized as non-admin", 
                  (boolean) isNonAdminMethod.invoke(loginPage, nonAdmin));
    }

    /**
     * Test basic field operations
     */
    @Test
    public void testGetUsername() {
        String result = loginPage.getUsername();
        assertEquals("Initial username should be empty", "", result);
    }

    @Test
    public void testGetPassword() {
        String result = loginPage.getPassword();
        assertEquals("Initial password should be empty", "", result);
    }

    @Test
    public void testClearUsername() {
        loginPage.clearUsername();
        assertEquals("Username should be empty after clearing", "", loginPage.getUsername());
    }

    @Test
    public void testClearPassword() {
        loginPage.clearPassword();
        assertEquals("Password should be empty after clearing", "", loginPage.getPassword());
    }

    @Test
    public void testClearFields() {
        loginPage.clearFields();
        assertEquals("Username should be empty after clearing fields", "", loginPage.getUsername());
        assertEquals("Password should be empty after clearing fields", "", loginPage.getPassword());
    }

    @Test
    public void testSetIncorrectCredentialsVisible() {
        loginPage.setIncorrectCredentialsVisible(true);
        loginPage.setIncorrectCredentialsVisible(false);
        // Test passes if no exception is thrown
        assertTrue("Error message visibility should be settable", true);
    }

    @Test
    public void testFocusUsername() {
        loginPage.focusUsername();
        // Test passes if no exception is thrown
        assertTrue("Username field should be focusable", true);
    }
}