/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
public class JDBCUtilsTest {
    
    public JDBCUtilsTest() {
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
     * Test of closeConnection method, of class JDBCUtils.
     */
    @Test
    public void testCloseConnection() {
        System.out.println("closeConnection");
        Connection connection = null;
        JDBCUtils.closeConnection(connection);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of closeStatement method, of class JDBCUtils.
     */
    @Test
    public void testCloseStatement_PreparedStatement() {
        System.out.println("closeStatement");
        PreparedStatement statement = null;
        JDBCUtils.closeStatement(statement);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of closeStatement method, of class JDBCUtils.
     */
    @Test
    public void testCloseStatement_Statement() {
        System.out.println("closeStatement");
        Statement statement = null;
        JDBCUtils.closeStatement(statement);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of closeResultSet method, of class JDBCUtils.
     */
    @Test
    public void testCloseResultSet() {
        System.out.println("closeResultSet");
        ResultSet resultSet = null;
        JDBCUtils.closeResultSet(resultSet);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of closeResources method, of class JDBCUtils.
     */
    @Test
    public void testCloseResources_3args_1() {
        System.out.println("closeResources");
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Connection connection = null;
        JDBCUtils.closeResources(resultSet, statement, connection);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of closeResources method, of class JDBCUtils.
     */
    @Test
    public void testCloseResources_3args_2() {
        System.out.println("closeResources");
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;
        JDBCUtils.closeResources(resultSet, statement, connection);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rollbackTransaction method, of class JDBCUtils.
     */
    @Test
    public void testRollbackTransaction() {
        System.out.println("rollbackTransaction");
        Connection connection = null;
        JDBCUtils.rollbackTransaction(connection);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of commitTransaction method, of class JDBCUtils.
     */
    @Test
    public void testCommitTransaction() throws Exception {
        System.out.println("commitTransaction");
        Connection connection = null;
        JDBCUtils.commitTransaction(connection);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of beginTransaction method, of class JDBCUtils.
     */
    @Test
    public void testBeginTransaction() throws Exception {
        System.out.println("beginTransaction");
        Connection connection = null;
        JDBCUtils.beginTransaction(connection);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endTransaction method, of class JDBCUtils.
     */
    @Test
    public void testEndTransaction() {
        System.out.println("endTransaction");
        Connection connection = null;
        JDBCUtils.endTransaction(connection);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isConnectionValid method, of class JDBCUtils.
     */
    @Test
    public void testIsConnectionValid() {
        System.out.println("isConnectionValid");
        Connection connection = null;
        boolean expResult = false;
        boolean result = JDBCUtils.isConnectionValid(connection);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setParameter method, of class JDBCUtils.
     */
    @Test
    public void testSetParameter() throws Exception {
        System.out.println("setParameter");
        PreparedStatement statement = null;
        int parameterIndex = 0;
        Object value = null;
        JDBCUtils.setParameter(statement, parameterIndex, value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getString method, of class JDBCUtils.
     */
    @Test
    public void testGetString() throws Exception {
        System.out.println("getString");
        ResultSet resultSet = null;
        String columnName = "";
        String expResult = "";
        String result = JDBCUtils.getString(resultSet, columnName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInteger method, of class JDBCUtils.
     */
    @Test
    public void testGetInteger() throws Exception {
        System.out.println("getInteger");
        ResultSet resultSet = null;
        String columnName = "";
        Integer expResult = null;
        Integer result = JDBCUtils.getInteger(resultSet, columnName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBoolean method, of class JDBCUtils.
     */
    @Test
    public void testGetBoolean() throws Exception {
        System.out.println("getBoolean");
        ResultSet resultSet = null;
        String columnName = "";
        Boolean expResult = null;
        Boolean result = JDBCUtils.getBoolean(resultSet, columnName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
