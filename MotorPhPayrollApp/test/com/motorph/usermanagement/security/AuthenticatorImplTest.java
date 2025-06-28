/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.security;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * @author Harvey
 */
public class AuthenticatorImplTest {
    
    private AuthenticatorImpl authenticator;
    
    @Before
    public void setUp() {
        authenticator = new AuthenticatorImpl();
    }

    @Test
    public void testHashPassword() {
        System.out.println("Testing hashPassword");
        String plainPassword = "TestPass123!";
        
        String result = authenticator.hashPassword(plainPassword);
        
        assertNotNull(result);
        assertEquals(60, result.length()); // BCrypt hash length
        System.out.println(" Password hashed successfully");
    }

    @Test
    public void testVerifyPassword() {
        System.out.println("Testing verifyPassword");
        String plainPassword = "TestPass123!";
        String hashedPassword = authenticator.hashPassword(plainPassword);
        
        boolean result = authenticator.verifyPassword(plainPassword, hashedPassword);
        
        assertTrue(result);
        System.out.println(" Password verified successfully");
    }

    @Test
    public void testGenerateSalt() {
        System.out.println("Testing generateSalt");
        
        String salt = authenticator.generateSalt();
        
        assertNotNull(salt);
        assertTrue(salt.startsWith("$2"));
        System.out.println(" Salt generated successfully");
    }

    @Test
    public void testIsPasswordSecure_ValidPassword() {
        System.out.println("Testing isPasswordSecure with valid password");
        String password = "MyPass123!";
        
        boolean result = authenticator.isPasswordSecure(password);
        
        assertTrue(result);
        System.out.println(" Password security check passed");
    }

    @Test
    public void testIsPasswordSecure_InvalidPassword() {
        System.out.println("Testing isPasswordSecure with invalid password");
        String password = "weak";
        
        boolean result = authenticator.isPasswordSecure(password);
        
        assertFalse(result);
        System.out.println(" Weak password correctly rejected");
    }
}