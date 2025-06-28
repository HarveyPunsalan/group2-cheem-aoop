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
        String plainTextPassword = "";
        Authenticator instance = new AuthenticatorImpl();
        String expResult = "";
        String result = instance.hashPassword(plainTextPassword);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verifyPassword method, of class Authenticator.
     */
    @Test
    public void testVerifyPassword() {
        System.out.println("verifyPassword");
        String plainTextPassword = "";
        String hashedPassword = "";
        Authenticator instance = new AuthenticatorImpl();
        boolean expResult = false;
        boolean result = instance.verifyPassword(plainTextPassword, hashedPassword);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateSalt method, of class Authenticator.
     */
    @Test
    public void testGenerateSalt() {
        System.out.println("generateSalt");
        Authenticator instance = new AuthenticatorImpl();
        String expResult = "";
        String result = instance.generateSalt();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isPasswordSecure method, of class Authenticator.
     */
    @Test
    public void testIsPasswordSecure() {
        System.out.println("isPasswordSecure");
        String password = "";
        Authenticator instance = new AuthenticatorImpl();
        boolean expResult = false;
        boolean result = instance.isPasswordSecure(password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class AuthenticatorImpl implements Authenticator {

        public String hashPassword(String plainTextPassword) {
            return "";
        }

        public boolean verifyPassword(String plainTextPassword, String hashedPassword) {
            return false;
        }

        public String generateSalt() {
            return "";
        }

        public boolean isPasswordSecure(String password) {
            return false;
        }
    }
    
}
