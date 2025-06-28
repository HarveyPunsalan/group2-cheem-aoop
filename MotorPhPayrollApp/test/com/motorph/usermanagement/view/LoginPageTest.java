/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.view;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author markpunsalan
 */
public class LoginPageTest {
    
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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getUsername method, of class LoginPage.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        LoginPage instance = new LoginPage();
        String expResult = "";
        String result = instance.getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword method, of class LoginPage.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        LoginPage instance = new LoginPage();
        String expResult = "";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearUsername method, of class LoginPage.
     */
    @Test
    public void testClearUsername() {
        System.out.println("clearUsername");
        LoginPage instance = new LoginPage();
        instance.clearUsername();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearPassword method, of class LoginPage.
     */
    @Test
    public void testClearPassword() {
        System.out.println("clearPassword");
        LoginPage instance = new LoginPage();
        instance.clearPassword();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearFields method, of class LoginPage.
     */
    @Test
    public void testClearFields() {
        System.out.println("clearFields");
        LoginPage instance = new LoginPage();
        instance.clearFields();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of focusUsername method, of class LoginPage.
     */
    @Test
    public void testFocusUsername() {
        System.out.println("focusUsername");
        LoginPage instance = new LoginPage();
        instance.focusUsername();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIncorrectCredentialsVisible method, of class LoginPage.
     */
    @Test
    public void testSetIncorrectCredentialsVisible() {
        System.out.println("setIncorrectCredentialsVisible");
        boolean visible = false;
        LoginPage instance = new LoginPage();
        instance.setIncorrectCredentialsVisible(visible);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class LoginPage.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        LoginPage.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
