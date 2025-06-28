/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.view;

import com.motorph.usermanagement.controller.SystemController;
import javax.swing.JButton;
import javax.swing.JPanel;
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
public class SystemAdminPageTest {
    
    public SystemAdminPageTest() {
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
     * Test of getPermissionButton method, of class SystemAdminPage.
     */
    @Test
    public void testGetPermissionButton() {
        System.out.println("getPermissionButton");
        SystemAdminPage instance = new SystemAdminPage();
        JButton expResult = null;
        JButton result = instance.getPermissionButton();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRoleButton method, of class SystemAdminPage.
     */
    @Test
    public void testGetRoleButton() {
        System.out.println("getRoleButton");
        SystemAdminPage instance = new SystemAdminPage();
        JButton expResult = null;
        JButton result = instance.getRoleButton();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserButton method, of class SystemAdminPage.
     */
    @Test
    public void testGetUserButton() {
        System.out.println("getUserButton");
        SystemAdminPage instance = new SystemAdminPage();
        JButton expResult = null;
        JButton result = instance.getUserButton();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLogoutButton method, of class SystemAdminPage.
     */
    @Test
    public void testGetLogoutButton() {
        System.out.println("getLogoutButton");
        SystemAdminPage instance = new SystemAdminPage();
        JButton expResult = null;
        JButton result = instance.getLogoutButton();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContentPanel method, of class SystemAdminPage.
     */
    @Test
    public void testGetContentPanel() {
        System.out.println("getContentPanel");
        SystemAdminPage instance = new SystemAdminPage();
        JPanel expResult = null;
        JPanel result = instance.getContentPanel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSystemController method, of class SystemAdminPage.
     */
    @Test
    public void testGetSystemController() {
        System.out.println("getSystemController");
        SystemAdminPage instance = new SystemAdminPage();
        SystemController expResult = null;
        SystemController result = instance.getSystemController();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class SystemAdminPage.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        SystemAdminPage.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
