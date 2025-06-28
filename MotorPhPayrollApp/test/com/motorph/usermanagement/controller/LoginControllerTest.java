/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.controller;

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
public class LoginControllerTest {
    
    public LoginControllerTest() {
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
     * Test of handleLogin method, of class LoginController.
     */
    @Test
    public void testHandleLogin() {
        System.out.println("handleLogin");
        String username = "";
        String password = "";
        LoginController instance = null;
        instance.handleLogin(username, password);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsernameFromView method, of class LoginController.
     */
    @Test
    public void testGetUsernameFromView() {
        System.out.println("getUsernameFromView");
        LoginController instance = null;
        String expResult = "";
        String result = instance.getUsernameFromView();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPasswordFromView method, of class LoginController.
     */
    @Test
    public void testGetPasswordFromView() {
        System.out.println("getPasswordFromView");
        LoginController instance = null;
        String expResult = "";
        String result = instance.getPasswordFromView();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
