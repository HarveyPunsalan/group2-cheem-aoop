/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4Suite.java to edit this template
 */
package com.motorph.usermanagement;

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
@Suite.SuiteClasses({com.motorph.usermanagement.dao.DaoSuite.class, com.motorph.usermanagement.util.UtilSuite.class, com.motorph.usermanagement.model.ModelSuite.class, com.motorph.usermanagement.service.ServiceSuite.class, com.motorph.usermanagement.security.SecuritySuite.class})
public class UsermanagementSuite {

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
