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
public class UserControllerTest {
    
    public UserControllerTest() {
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
     * Test of loadAllUsers method, of class UserController.
     */
    @Test
    public void testLoadAllUsers() {
        System.out.println("loadAllUsers");
        UserController instance = null;
        instance.loadAllUsers();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadActiveUsers method, of class UserController.
     */
    @Test
    public void testLoadActiveUsers() {
        System.out.println("loadActiveUsers");
        UserController instance = null;
        instance.loadActiveUsers();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createUser method, of class UserController.
     */
    @Test
    public void testCreateUser() {
        System.out.println("createUser");
        String username = "";
        String password = "";
        int employeeId = 0;
        int roleId = 0;
        UserController instance = null;
        instance.createUser(username, password, employeeId, roleId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateUser method, of class UserController.
     */
    @Test
    public void testUpdateUser() {
        System.out.println("updateUser");
        int userId = 0;
        String username = "";
        int employeeId = 0;
        int roleId = 0;
        UserController instance = null;
        instance.updateUser(userId, username, employeeId, roleId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUserStatus method, of class UserController.
     */
    @Test
    public void testSetUserStatus() {
        System.out.println("setUserStatus");
        int userId = 0;
        boolean isActive = false;
        UserController instance = null;
        instance.setUserStatus(userId, isActive);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchUsers method, of class UserController.
     */
    @Test
    public void testSearchUsers() {
        System.out.println("searchUsers");
        String searchTerm = "";
        UserController instance = null;
        instance.searchUsers(searchTerm);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetUserPassword method, of class UserController.
     */
    @Test
    public void testResetUserPassword() {
        System.out.println("resetUserPassword");
        int userId = 0;
        String newPassword = "";
        UserController instance = null;
        instance.resetUserPassword(userId, newPassword);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateUserCount method, of class UserController.
     */
    @Test
    public void testUpdateUserCount() {
        System.out.println("updateUserCount");
        UserController instance = null;
        instance.updateUserCount();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
