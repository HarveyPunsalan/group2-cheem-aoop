/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4Suite.java to edit this template
 */
package com.motorph.usermanagement.exception;

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
@Suite.SuiteClasses({com.motorph.usermanagement.exception.RoleNotFoundExceptionTest.class, com.motorph.usermanagement.exception.InvalidCredentialsExceptionTest.class, com.motorph.usermanagement.exception.UserNotFoundExceptionTest.class, com.motorph.usermanagement.exception.InsufficientPermissionExceptionTest.class, com.motorph.usermanagement.exception.UserAlreadyExistsExceptionTest.class, com.motorph.usermanagement.exception.DuplicateRoleExceptionTest.class, com.motorph.usermanagement.exception.PermissionNotFoundExceptionTest.class, com.motorph.usermanagement.exception.DuplicatePermissionExceptionTest.class, com.motorph.usermanagement.exception.DuplicateUserExceptionTest.class, com.motorph.usermanagement.exception.DataAccessExceptionTest.class})
public class ExceptionSuite {

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
