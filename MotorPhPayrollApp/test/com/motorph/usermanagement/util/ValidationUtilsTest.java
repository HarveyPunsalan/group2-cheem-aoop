/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.util;

//import com.toedter.calendar.JDateChooser;
//import javax.swing.JTextField;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
//import java.util.Date;
//import java.util.Calendar;

/**
 * @author Harvey
 */
public class ValidationUtilsTest {
    
    public ValidationUtilsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Starting ValidationUtils tests...");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("ValidationUtils tests completed.");
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // USERNAME VALIDATION TESTS
    
    @Test
    public void testIsValidUsername_ValidUsername() {
        System.out.println("Testing valid usernames");
        assertTrue("Valid username should pass", ValidationUtils.isValidUsername("user123"));
        assertTrue("Valid username should pass", ValidationUtils.isValidUsername("admin_user"));
        assertTrue("Valid username should pass", ValidationUtils.isValidUsername("test_123"));
    }
    
    @Test
    public void testIsValidUsername_InvalidUsername() {
        System.out.println("Testing invalid usernames");
        assertFalse("Empty username should fail", ValidationUtils.isValidUsername(""));
        assertFalse("Null username should fail", ValidationUtils.isValidUsername(null));
        assertFalse("Too short username should fail", ValidationUtils.isValidUsername("ab"));
        assertFalse("Too long username should fail", ValidationUtils.isValidUsername("thisusernameistoolongtobevalid"));
        assertFalse("Username with special chars should fail", ValidationUtils.isValidUsername("user@123"));
    }

    // PASSWORD VALIDATION TESTS 
    
    @Test
    public void testIsValidPassword_ValidPassword() {
        System.out.println("Testing valid passwords");
        assertTrue("Valid password should pass", ValidationUtils.isValidPassword("password123"));
        assertTrue("Valid password should pass", ValidationUtils.isValidPassword("myPass123"));
        assertTrue("Valid password should pass", ValidationUtils.isValidPassword("test1234"));
    }
    
    @Test
    public void testIsValidPassword_InvalidPassword() {
        System.out.println("Testing invalid passwords");
        assertFalse("Null password should fail", ValidationUtils.isValidPassword(null));
        assertFalse("Too short password should fail", ValidationUtils.isValidPassword("pass1"));
        assertFalse("Password without numbers should fail", ValidationUtils.isValidPassword("password"));
        assertFalse("Password without letters should fail", ValidationUtils.isValidPassword("12345678"));
    }

    // EMPLOYEE ID VALIDATION TESTS 
    
    @Test
    public void testIsValidEmployeeId_ValidId() {
        System.out.println("Testing valid employee IDs");
        assertTrue("Valid employee ID should pass", ValidationUtils.isValidEmployeeId("123"));
        assertTrue("Valid employee ID should pass", ValidationUtils.isValidEmployeeId("1234567890"));
        assertTrue("Valid employee ID should pass", ValidationUtils.isValidEmployeeId("1"));
    }
    
    @Test
    public void testIsValidEmployeeId_InvalidId() {
        System.out.println("Testing invalid employee IDs");
        assertFalse("Null employee ID should fail", ValidationUtils.isValidEmployeeId(null));
        assertFalse("Empty employee ID should fail", ValidationUtils.isValidEmployeeId(""));
        assertFalse("Non-numeric employee ID should fail", ValidationUtils.isValidEmployeeId("abc123"));
        assertFalse("Too long employee ID should fail", ValidationUtils.isValidEmployeeId("12345678901"));
    }

    // PHONE NUMBER VALIDATION 
    
    @Test
    public void testIsValidPhoneNumber_ValidNumbers() {
        System.out.println("Testing valid phone numbers");
        assertEquals("Valid mobile number should pass", "", ValidationUtils.isValidPhoneNumber("09123456789"));
        assertEquals("Valid mobile number with +63 should pass", "", ValidationUtils.isValidPhoneNumber("+639123456789"));
        assertEquals("Valid landline should pass", "", ValidationUtils.isValidPhoneNumber("0212345678"));
    }
    
    @Test
    public void testIsValidPhoneNumber_InvalidNumbers() {
        System.out.println("Testing invalid phone numbers");
        assertNotEquals("Null phone should fail", "", ValidationUtils.isValidPhoneNumber(null));
        assertNotEquals("Empty phone should fail", "", ValidationUtils.isValidPhoneNumber(""));
        assertNotEquals("Invalid format should fail", "", ValidationUtils.isValidPhoneNumber("123456789"));
        assertNotEquals("Too short number should fail", "", ValidationUtils.isValidPhoneNumber("091234567"));
    }

    // GOVERNMENT ID VALIDATION TESTS
    
    @Test
    public void testIsValidGovernmentIDNumber_SSS() {
        System.out.println("Testing SSS validation");
        assertEquals("Valid SSS format should pass", "", ValidationUtils.isValidGovernmentIDNumber("12-3456789-1", "SSS", 10));
        assertEquals("Valid SSS digits should pass", "", ValidationUtils.isValidGovernmentIDNumber("1234567890", "SSS", 10));
        assertNotEquals("Invalid SSS should fail", "", ValidationUtils.isValidGovernmentIDNumber("123-456-789", "SSS", 10));
    }
    
    @Test
    public void testIsValidGovernmentIDNumber_TIN() {
        System.out.println("Testing TIN validation");
        assertEquals("Valid TIN format should pass", "", ValidationUtils.isValidGovernmentIDNumber("123-456-789-012", "TIN", 12));
        assertEquals("Valid TIN digits should pass", "", ValidationUtils.isValidGovernmentIDNumber("123456789012", "TIN", 12));
        assertNotEquals("Invalid TIN should fail", "", ValidationUtils.isValidGovernmentIDNumber("123-456-789", "TIN", 12));
    }

    // AMOUNT VALIDATION TESTS 
    
    @Test
    public void testIsValidAmount_ValidAmounts() {
        System.out.println("Testing valid amounts");
        assertEquals("Valid amount should pass", "", ValidationUtils.isValidAmount("1000", "Salary"));
        assertEquals("Valid decimal amount should pass", "", ValidationUtils.isValidAmount("1000.50", "Salary"));
        assertEquals("Valid amount with comma should pass", "", ValidationUtils.isValidAmount("1,000.00", "Salary"));
    }
    
    @Test
    public void testIsValidAmount_InvalidAmounts() {
        System.out.println("Testing invalid amounts");
        assertNotEquals("Null amount should fail", "", ValidationUtils.isValidAmount(null, "Salary"));
        assertNotEquals("Empty amount should fail", "", ValidationUtils.isValidAmount("", "Salary"));
        assertNotEquals("Negative amount should fail", "", ValidationUtils.isValidAmount("-100", "Salary"));
        assertNotEquals("Invalid format should fail", "", ValidationUtils.isValidAmount("abc", "Salary"));
    }

    // ROLE NAME VALIDATION TESTS 
    
    @Test
    public void testIsValidRoleName_ValidNames() {
        System.out.println("Testing valid role names");
        assertTrue("Valid role name should pass", ValidationUtils.isValidRoleName("Admin"));
        assertTrue("Valid role name should pass", ValidationUtils.isValidRoleName("HR Manager"));
        assertTrue("Valid role name should pass", ValidationUtils.isValidRoleName("Employee_Role"));
    }
    
    @Test
    public void testIsValidRoleName_InvalidNames() {
        System.out.println("Testing invalid role names");
        assertFalse("Null role name should fail", ValidationUtils.isValidRoleName(null));
        assertFalse("Empty role name should fail", ValidationUtils.isValidRoleName(""));
        assertFalse("Too short role name should fail", ValidationUtils.isValidRoleName("A"));
        assertFalse("Too long role name should fail", ValidationUtils.isValidRoleName("This role name is way too long to be valid and should definitely fail"));
    }

    // UTILITY METHOD TESTS 
    
    @Test
    public void testIsNotEmpty() {
        System.out.println("Testing isNotEmpty method");
        assertTrue("Non-empty string should return true", ValidationUtils.isNotEmpty("test"));
        assertTrue("String with spaces should return true", ValidationUtils.isNotEmpty("  test  "));
        assertFalse("Empty string should return false", ValidationUtils.isNotEmpty(""));
        assertFalse("Null string should return false", ValidationUtils.isNotEmpty(null));
        assertFalse("Whitespace only should return false", ValidationUtils.isNotEmpty("   "));
    }
    
    @Test
    public void testIsValidLength() {
        System.out.println("Testing isValidLength method");
        assertTrue("Valid length should pass", ValidationUtils.isValidLength("test", 2, 10));
        assertTrue("Exact min length should pass", ValidationUtils.isValidLength("ab", 2, 10));
        assertTrue("Exact max length should pass", ValidationUtils.isValidLength("abcdefghij", 2, 10));
        assertFalse("Too short should fail", ValidationUtils.isValidLength("a", 2, 10));
        assertFalse("Too long should fail", ValidationUtils.isValidLength("abcdefghijk", 2, 10));
        assertFalse("Null should fail", ValidationUtils.isValidLength(null, 2, 10));
    }
    
    @Test
    public void testSanitizeInput() {
        System.out.println("Testing sanitizeInput method");
        assertEquals("Normal text should remain unchanged", "test", ValidationUtils.sanitizeInput("test"));
        assertEquals("Whitespace should be trimmed", "test", ValidationUtils.sanitizeInput("  test  "));
        assertEquals("Special chars should be removed", "test", ValidationUtils.sanitizeInput("te<>st"));
        assertEquals("Null should return empty", "", ValidationUtils.sanitizeInput(null));
    }
    
    @Test
    public void testIsValidRoleId() {
        System.out.println("Testing isValidRoleId method");
        assertTrue("Positive role ID should pass", ValidationUtils.isValidRoleId(1));
        assertTrue("Large role ID should pass", ValidationUtils.isValidRoleId(999));
        assertFalse("Zero role ID should fail", ValidationUtils.isValidRoleId(0));
        assertFalse("Negative role ID should fail", ValidationUtils.isValidRoleId(-1));
    }
    
    @Test
    public void testPasswordsMatch() {
        System.out.println("Testing passwordsMatch method");
        assertTrue("Matching passwords should return true", ValidationUtils.passwordsMatch("password", "password"));
        assertFalse("Different passwords should return false", ValidationUtils.passwordsMatch("password1", "password2"));
        assertFalse("Null passwords should return false", ValidationUtils.passwordsMatch(null, "password"));
        assertFalse("One null password should return false", ValidationUtils.passwordsMatch("password", null));
    }

    // DATE VALIDATION TESTS (no gui components) 
    
    @Test
    public void testIsValidBirthday_NullDateChooser() {
        System.out.println("Testing birthday validation with null date chooser");
        String result = ValidationUtils.isValidBirthday(null, "dd/MM/yyyy");
        assertNotEquals("Null date chooser should fail", "", result);
        assertTrue("Should contain required message", result.contains("required"));
    }
    
    @Test
    public void testIsValidDateHired_NullDateChooser() {
        System.out.println("Testing date hired validation with null date chooser");
        String result = ValidationUtils.isValidDateHired(null, "dd/MM/yyyy");
        assertNotEquals("Null date chooser should fail", "", result);
        assertTrue("Should contain required message", result.contains("required"));
    }

    // PERMISSION NAME VALIDATION TESTS
    
    @Test
    public void testIsValidPermissionName_Valid() {
        System.out.println("Testing valid permission names");
        assertTrue("Valid permission should pass", ValidationUtils.isValidPermissionName("READ_USERS"));
        assertTrue("Valid permission should pass", ValidationUtils.isValidPermissionName("Manage Employees"));
        assertTrue("Valid permission should pass", ValidationUtils.isValidPermissionName("Create-Reports"));
    }
    
    @Test
    public void testIsValidPermissionName_Invalid() {
        System.out.println("Testing invalid permission names");
        assertFalse("Null permission should fail", ValidationUtils.isValidPermissionName(null));
        assertFalse("Empty permission should fail", ValidationUtils.isValidPermissionName(""));
        assertFalse("Too short permission should fail", ValidationUtils.isValidPermissionName("AB"));
        
        // Create a string that's too long (over 100 characters)
        String tooLong = "This permission name is way too long and exceeds the maximum allowed length of one hundred characters";
        assertFalse("Too long permission should fail", ValidationUtils.isValidPermissionName(tooLong));
    }
}