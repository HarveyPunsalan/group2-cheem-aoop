/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.view;

import javax.swing.table.DefaultTableModel;
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
public class UserManagementPageTest {
    
    public UserManagementPageTest() {
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
     * Test of setTableModel method, of class UserManagementPage.
     */
    @Test
    public void testSetTableModel() {
        System.out.println("setTableModel");
        DefaultTableModel tableModel = null;
        UserManagementPage instance = new UserManagementPage();
        instance.setTableModel(tableModel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateUserCountDisplay method, of class UserManagementPage.
     */
    @Test
    public void testUpdateUserCountDisplay() {
        System.out.println("updateUserCountDisplay");
        int totalUsers = 0;
        int activeUsers = 0;
        UserManagementPage instance = new UserManagementPage();
        instance.updateUserCountDisplay(totalUsers, activeUsers);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
