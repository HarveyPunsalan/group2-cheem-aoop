/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4Suite.java to edit this template
 */
package com.motorph.usermanagement.view;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author markpunsalan
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({com.motorph.usermanagement.view.UserManagementPageTest.class, com.motorph.usermanagement.view.SystemAdminPageTest.class, com.motorph.usermanagement.view.RoleManagementPageTest.class, com.motorph.usermanagement.view.LoginPageTest.class, com.motorph.usermanagement.view.PermissionManagementPageTest.class, com.motorph.usermanagement.view.CompanyHomePageTest.class})
public class ViewSuite {

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
