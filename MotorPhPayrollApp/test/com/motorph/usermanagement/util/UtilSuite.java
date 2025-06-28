/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4Suite.java to edit this template
 */
package com.motorph.usermanagement.util;

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
@Suite.SuiteClasses({com.motorph.usermanagement.util.ConstantsTest.class, com.motorph.usermanagement.util.JDBCUtilsTest.class, com.motorph.usermanagement.util.DateUtilsTest.class, com.motorph.usermanagement.util.ValidationUtilsTest.class})
public class UtilSuite {

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
