/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4Suite.java to edit this template
 */
package com.motorph.usermanagement.service;

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
@Suite.SuiteClasses({com.motorph.usermanagement.service.UserServiceTest.class, com.motorph.usermanagement.service.RoleServiceImplTest.class, com.motorph.usermanagement.service.PermissionServiceImplTest.class, com.motorph.usermanagement.service.UserServiceImplTest.class, com.motorph.usermanagement.service.PermissionServiceTest.class, com.motorph.usermanagement.service.RoleServiceTest.class})
public class ServiceSuite {

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
