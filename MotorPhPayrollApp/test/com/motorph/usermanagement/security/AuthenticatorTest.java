/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.security;

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
public class AuthenticatorTest {
    
    private Authenticator authenticator;
    
    public AuthenticatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        authenticator = new AuthenticatorImpl();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of hashPassword method, of class Authenticator.
     */
    @Test
    public void testHashPassword() {
        System.out.println("hashPassword");
        String plainTextPassword = "testpassword123";
        
        String result = authenticator.hashPassword(plainTextPassword);
        
        assertNotNull(result);
        assertEquals(60, result.length());
        assertTrue(result.startsWith("$2"));
    }

    /**
     * Test of verifyPassword method, of class Authenticator.
     */
    @Test
    public void testVerifyPassword() {
        System.out.println("verifyPassword");
        String plainTextPassword = "mypassword123";
        String hashedPassword = authenticator.hashPassword(plainTextPassword);
        
        boolean result = authenticator.verifyPassword(plainTextPassword, hashedPassword);
        
        assertTrue(result);
    }

    /**
     * Test of generateSalt method, of class Authenticator.
     */
    @Test
    public void testGenerateSalt() {
        System.out.println("generateSalt");
        
        String result = authenticator.generateSalt();
        
        assertNotNull(result);
        assertTrue(result.startsWith("$2"));
    }

    /**
     * Test of isPasswordSecure method, of class Authenticator.
     */
    @Test
    public void testIsPasswordSecure() {
        System.out.println("isPasswordSecure");
        String password = "SecurePass123!";
        
        boolean result = authenticator.isPasswordSecure(password);
        
        assertTrue(result);
    }
    
    @Test
    public void testHashPasswordEmpty() {
        System.out.println("hashPassword empty");
        try {
            authenticator.hashPassword("");
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }
    
    @Test
    public void testHashPasswordNull() {
        System.out.println("hashPassword null");
        try {
            authenticator.hashPassword(null);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }
    
    @Test
    public void testVerifyPasswordWrong() {
        System.out.println("verifyPassword wrong");
        String plainTextPassword = "correct123";
        String wrongPassword = "wrong123";
        String hashedPassword = authenticator.hashPassword(plainTextPassword);
        
        boolean result = authenticator.verifyPassword(wrongPassword, hashedPassword);
        
        assertFalse(result);
    }
    
    @Test
    public void testVerifyPasswordNull() {
        System.out.println("verifyPassword null");
        String hashedPassword = authenticator.hashPassword("test123");
        
        boolean result1 = authenticator.verifyPassword(null, hashedPassword);
        boolean result2 = authenticator.verifyPassword("test123", null);
        
        assertFalse(result1);
        assertFalse(result2);
    }
    
    @Test
    public void testGenerateSaltUnique() {
        System.out.println("generateSalt unique");
        
        String salt1 = authenticator.generateSalt();
        String salt2 = authenticator.generateSalt();
        
        assertNotEquals(salt1, salt2);
    }
    
    @Test
    public void testIsPasswordSecureWeak() {
        System.out.println("isPasswordSecure weak");
        String password = "weak";
        
        boolean result = authenticator.isPasswordSecure(password);
        
        assertFalse(result);
    }
    
    @Test
    public void testIsPasswordSecureNoDigit() {
        System.out.println("isPasswordSecure no digit");
        String password = "Password!";
        
        boolean result = authenticator.isPasswordSecure(password);
        
        assertFalse(result);
    }
    
    @Test
    public void testIsPasswordSecureNull() {
        System.out.println("isPasswordSecure null");
        
        boolean result = authenticator.isPasswordSecure(null);
        
        assertFalse(result);
    }
}