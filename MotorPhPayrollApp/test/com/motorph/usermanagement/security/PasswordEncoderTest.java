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
public class PasswordEncoderTest {
    
    private PasswordEncoder encoder;
    
    public PasswordEncoderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        encoder = new PasswordEncoder();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testEncode() {
        String password = "test123";
        String encoded = encoder.encode(password);
        
        assertNotNull(encoded);
        assertFalse(encoded.isEmpty());
    }

    @Test
    public void testEncodeDifferentResults() {
        String password = "samepass";
        String encoded1 = encoder.encode(password);
        String encoded2 = encoder.encode(password);
        
        // should be different due to salt
        assertNotEquals(encoded1, encoded2);
    }

    @Test
    public void testMatches() {
        String password = "mypassword";
        String encoded = encoder.encode(password);
        
        boolean result = encoder.matches(password, encoded);
        assertTrue(result);
    }

    @Test
    public void testMatchesWrongPassword() {
        String correctPass = "correct123";
        String wrongPass = "wrong123";
        String encoded = encoder.encode(correctPass);
        
        boolean result = encoder.matches(wrongPass, encoded);
        assertFalse(result);
    }

    @Test
    public void testEmptyPassword() {
        String empty = "";
        String encoded = encoder.encode(empty);
        
        assertNotNull(encoded);
        assertTrue(encoder.matches(empty, encoded));
    }

    @Test
    public void testNullPassword() {
        String encoded = encoder.encode("somepass");
        
        boolean result = encoder.matches(null, encoded);
        assertFalse(result);
    }

    @Test
    public void testInvalidEncodedPassword() {
        String password = "test";
        String badEncoded = "notvalidbase64!@#";
        
        boolean result = encoder.matches(password, badEncoded);
        assertFalse(result);
    }

    @Test
    public void testCaseSensitive() {
        String password = "MyPass123";
        String encoded = encoder.encode(password);
        
        assertTrue(encoder.matches("MyPass123", encoded));
        assertFalse(encoder.matches("mypass123", encoded));
        assertFalse(encoder.matches("MYPASS123", encoded));
    }

    @Test
    public void testCommonPasswords() {
        String[] passwords = {"admin123", "user2024", "temp_pass"};
        
        for (String pass : passwords) {
            String encoded = encoder.encode(pass);
            assertTrue(encoder.matches(pass, encoded));
            assertFalse(encoder.matches(pass + "x", encoded));
        }
    }

    @Test
    public void testEncodedLength() {
        String password = "test";
        String encoded = encoder.encode(password);
        
        // base64 encoded 48 bytes should be 64 chars
        assertEquals(64, encoded.length());
    }
}