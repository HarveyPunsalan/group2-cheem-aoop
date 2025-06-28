/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4Suite.java to edit this template
 */
package com.motorph.usermanagement.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Harvey
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({com.motorph.usermanagement.model.RoleTest.class, com.motorph.usermanagement.model.NonAdminTest.class, com.motorph.usermanagement.model.AdminTest.class, com.motorph.usermanagement.model.PermissionTest.class, com.motorph.usermanagement.model.AccessTest.class, com.motorph.usermanagement.model.UserTest.class})
public class ModelSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
