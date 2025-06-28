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
public class PasswordEncoderTest {
    
    private PasswordEncoder passwordEncoder;
    
    @Before
    public void setUp() {
        passwordEncoder = new PasswordEncoder();
    }

    /**
     * Test that encode method works
     */
    @Test
    public void testEncode() {
        String plainPassword = "myPassword123";
        
        String result = passwordEncoder.encode(plainPassword);
        
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    /**
     * Test that matches method works with correct password
     */
    @Test
    public void testMatches_CorrectPassword() {
        String plainPassword = "testPassword";
        
        String encodedPassword = passwordEncoder.encode(plainPassword);
        boolean result = passwordEncoder.matches(plainPassword, encodedPassword);
        
        assertTrue(result);
    }

    /**
     * Test that matches method rejects wrong password
     */
    @Test
    public void testMatches_WrongPassword() {
        String correctPassword = "correctPassword";
        String wrongPassword = "wrongPassword";
        
        String encodedPassword = passwordEncoder.encode(correctPassword);
        boolean result = passwordEncoder.matches(wrongPassword, encodedPassword);
        
        assertFalse(result);
    }
}