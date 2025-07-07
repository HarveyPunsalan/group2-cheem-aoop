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
public class AuthenticatorImplTest {
    
    private AuthenticatorImpl authenticator;
    
    public AuthenticatorImplTest() {
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
     * Test of hashPassword method, of class AuthenticatorImpl.
     */
    @Test
    public void testHashPassword() {
        System.out.println("hashPassword");
        String plainPassword = "mypassword123";
        
        String result = authenticator.hashPassword(plainPassword);
        
        assertNotNull(result);
        assertEquals(60, result.length());
        assertTrue(result.startsWith("$2"));
    }

    /**
     * Test of verifyPassword method, of class AuthenticatorImpl.
     */
    @Test
    public void testVerifyPassword() {
        System.out.println("verifyPassword");
        String plainPassword = "testpass123";
        String hashedPassword = authenticator.hashPassword(plainPassword);
        
        boolean result = authenticator.verifyPassword(plainPassword, hashedPassword);
        
        assertTrue(result);
    }

    /**
     * Test of authenticateAndMigrate method, of class AuthenticatorImpl.
     */
    @Test
    public void testAuthenticateAndMigrate() {
        System.out.println("authenticateAndMigrate");
        String plainPassword = "plaintext123";
        String storedPassword = "plaintext123";
        int userId = 1;
        
        boolean result = authenticator.authenticateAndMigrate(plainPassword, storedPassword, userId);
        
        assertTrue(result);
    }

    /**
     * Test of authenticate method, of class AuthenticatorImpl.
     */
    @Test
    public void testAuthenticate() {
        System.out.println("authenticate");
        String username = "nonexistent";
        String plainPassword = "password123";
        
        boolean result = authenticator.authenticate(username, plainPassword);
        
        assertFalse(result);
    }

    /**
     * Test of generateSalt method, of class AuthenticatorImpl.
     */
    @Test
    public void testGenerateSalt() {
        System.out.println("generateSalt");
        
        String result = authenticator.generateSalt();
        
        assertNotNull(result);
        assertTrue(result.startsWith("$2"));
    }

    /**
     * Test of isPasswordSecure method, of class AuthenticatorImpl.
     */
    @Test
    public void testIsPasswordSecure() {
        System.out.println("isPasswordSecure");
        String password = "SecurePass123!";
        
        boolean result = authenticator.isPasswordSecure(password);
        
        assertTrue(result);
    }
    
    @Test
    public void testHashPasswordWithNull() {
        System.out.println("hashPassword with null");
        try {
            authenticator.hashPassword(null);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }
    
    @Test
    public void testVerifyPasswordWrongPassword() {
        System.out.println("verifyPassword wrong password");
        String plainPassword = "correct123";
        String wrongPassword = "wrong123";
        String hashedPassword = authenticator.hashPassword(plainPassword);
        
        boolean result = authenticator.verifyPassword(wrongPassword, hashedPassword);
        
        assertFalse(result);
    }
    
    @Test
    public void testVerifyPasswordNullInputs() {
        System.out.println("verifyPassword null inputs");
        
        boolean result1 = authenticator.verifyPassword(null, "hash");
        boolean result2 = authenticator.verifyPassword("pass", null);
        
        assertFalse(result1);
        assertFalse(result2);
    }
    
    @Test
    public void testAuthenticateAndMigrateWithHashedPassword() {
        System.out.println("authenticateAndMigrate with hashed password");
        String plainPassword = "password123";
        String hashedPassword = authenticator.hashPassword(plainPassword);
        int userId = 1;
        
        boolean result = authenticator.authenticateAndMigrate(plainPassword, hashedPassword, userId);
        
        assertTrue(result);
    }
    
    @Test
    public void testAuthenticateAndMigrateWrongPassword() {
        System.out.println("authenticateAndMigrate wrong password");
        String plainPassword = "wrong123";
        String storedPassword = "correct123";
        int userId = 1;
        
        boolean result = authenticator.authenticateAndMigrate(plainPassword, storedPassword, userId);
        
        assertFalse(result);
    }
    
    @Test
    public void testIsPasswordSecureShort() {
        System.out.println("isPasswordSecure short password");
        String password = "Short1!";
        
        boolean result = authenticator.isPasswordSecure(password);
        
        assertFalse(result);
    }
    
    @Test
    public void testIsPasswordSecureNoUppercase() {
        System.out.println("isPasswordSecure no uppercase");
        String password = "password123!";
        
        boolean result = authenticator.isPasswordSecure(password);
        
        assertFalse(result);
    }
    
    @Test
    public void testIsPasswordSecureNoSpecialChar() {
        System.out.println("isPasswordSecure no special char");
        String password = "Password123";
        
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