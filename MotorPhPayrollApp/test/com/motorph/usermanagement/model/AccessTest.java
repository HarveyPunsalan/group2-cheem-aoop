/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.model;

import com.motorph.employeemanagement.model.Employee;
import com.motorph.payrollprocessing.model.PayPeriod;
import javax.swing.JFrame;
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
public class AccessTest {
    
    public AccessTest() {
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
     * Test of accessCompanyHomePage method, of class Access.
     */
    @Test
    public void testAccessCompanyHomePage() {
        System.out.println("accessCompanyHomePage");
        Admin admin = null;
        JFrame expResult = null;
        JFrame result = Access.accessCompanyHomePage(admin);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of accessEmployeeInformation method, of class Access.
     */
    @Test
    public void testAccessEmployeeInformation() {
        System.out.println("accessEmployeeInformation");
        Admin admin = null;
        JFrame expResult = null;
        JFrame result = Access.accessEmployeeInformation(admin);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of accessDTR method, of class Access.
     */
    @Test
    public void testAccessDTR_3args() {
        System.out.println("accessDTR");
        Admin admin = null;
        String employeeID = "";
        PayPeriod selectedPayPeriod = null;
        JFrame expResult = null;
        JFrame result = Access.accessDTR(admin, employeeID, selectedPayPeriod);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of accessDTR method, of class Access.
     */
    @Test
    public void testAccessDTR_Admin_Employee() {
        System.out.println("accessDTR");
        Admin admin = null;
        Employee employee = null;
        JFrame expResult = null;
        JFrame result = Access.accessDTR(admin, employee);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of accessDTR method, of class Access.
     */
    @Test
    public void testAccessDTR_Employee() {
        System.out.println("accessDTR");
        Employee employee = null;
        JFrame expResult = null;
        JFrame result = Access.accessDTR(employee);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of accessAttendanceBiweekly method, of class Access.
     */
    @Test
    public void testAccessAttendanceBiweekly() {
        System.out.println("accessAttendanceBiweekly");
        Admin admin = null;
        JFrame expResult = null;
        JFrame result = Access.accessAttendanceBiweekly(admin);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of accessViewEmployeeDetails method, of class Access.
     */
    @Test
    public void testAccessViewEmployeeDetails_User_String() {
        System.out.println("accessViewEmployeeDetails");
        User user = null;
        String employeeID = "";
        JFrame expResult = null;
        JFrame result = Access.accessViewEmployeeDetails(user, employeeID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of accessViewEmployeeDetails method, of class Access.
     */
    @Test
    public void testAccessViewEmployeeDetails_User() {
        System.out.println("accessViewEmployeeDetails");
        User user = null;
        JFrame expResult = null;
        JFrame result = Access.accessViewEmployeeDetails(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of accessProfilePage method, of class Access.
     */
    @Test
    public void testAccessProfilePage() {
        System.out.println("accessProfilePage");
        User user = null;
        JFrame expResult = null;
        JFrame result = Access.accessProfilePage(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of accessEmployeePayslip method, of class Access.
     */
    @Test
    public void testAccessEmployeePayslip() {
        System.out.println("accessEmployeePayslip");
        User user = null;
        JFrame expResult = null;
        JFrame result = Access.accessEmployeePayslip(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of accessEmployeeAttendance method, of class Access.
     */
    @Test
    public void testAccessEmployeeAttendance() {
        System.out.println("accessEmployeeAttendance");
        User user = null;
        JFrame expResult = null;
        JFrame result = Access.accessEmployeeAttendance(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of accessPayrollList method, of class Access.
     */
    @Test
    public void testAccessPayrollList() {
        System.out.println("accessPayrollList");
        Admin admin = null;
        JFrame expResult = null;
        JFrame result = Access.accessPayrollList(admin);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of accessPayrollEmployeeSelection method, of class Access.
     */
    @Test
    public void testAccessPayrollEmployeeSelection() {
        System.out.println("accessPayrollEmployeeSelection");
        Admin admin = null;
        PayPeriod payPeriod = null;
        JFrame expResult = null;
        JFrame result = Access.accessPayrollEmployeeSelection(admin, payPeriod);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of accessPayrollEarnings method, of class Access.
     */
    @Test
    public void testAccessPayrollEarnings_Admin_PayPeriod() {
        System.out.println("accessPayrollEarnings");
        Admin admin = null;
        PayPeriod payPeriod = null;
        JFrame expResult = null;
        JFrame result = Access.accessPayrollEarnings(admin, payPeriod);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of accessPayrollEarnings method, of class Access.
     */
    @Test
    public void testAccessPayrollEarnings_PayPeriod() {
        System.out.println("accessPayrollEarnings");
        PayPeriod payPeriod = null;
        JFrame expResult = null;
        JFrame result = Access.accessPayrollEarnings(payPeriod);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of accessPayrollDeductions method, of class Access.
     */
    @Test
    public void testAccessPayrollDeductions_Admin_PayPeriod() {
        System.out.println("accessPayrollDeductions");
        Admin admin = null;
        PayPeriod payPeriod = null;
        JFrame expResult = null;
        JFrame result = Access.accessPayrollDeductions(admin, payPeriod);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of accessPayrollDeductions method, of class Access.
     */
    @Test
    public void testAccessPayrollDeductions_PayPeriod() {
        System.out.println("accessPayrollDeductions");
        PayPeriod payPeriod = null;
        JFrame expResult = null;
        JFrame result = Access.accessPayrollDeductions(payPeriod);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of accessPayrollNetPay method, of class Access.
     */
    @Test
    public void testAccessPayrollNetPay_Admin_PayPeriod() {
        System.out.println("accessPayrollNetPay");
        Admin admin = null;
        PayPeriod payPeriod = null;
        JFrame expResult = null;
        JFrame result = Access.accessPayrollNetPay(admin, payPeriod);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of accessPayrollNetPay method, of class Access.
     */
    @Test
    public void testAccessPayrollNetPay_PayPeriod() {
        System.out.println("accessPayrollNetPay");
        PayPeriod payPeriod = null;
        JFrame expResult = null;
        JFrame result = Access.accessPayrollNetPay(payPeriod);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of accessPayrollSummaryPage method, of class Access.
     */
    @Test
    public void testAccessPayrollSummaryPage_Admin_PayPeriod() {
        System.out.println("accessPayrollSummaryPage");
        Admin admin = null;
        PayPeriod payPeriod = null;
        JFrame expResult = null;
        JFrame result = Access.accessPayrollSummaryPage(admin, payPeriod);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of accessPayrollSummaryPage method, of class Access.
     */
    @Test
    public void testAccessPayrollSummaryPage_PayPeriod() {
        System.out.println("accessPayrollSummaryPage");
        PayPeriod payPeriod = null;
        JFrame expResult = null;
        JFrame result = Access.accessPayrollSummaryPage(payPeriod);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of accessEmployeeRequests method, of class Access.
     */
    @Test
    public void testAccessEmployeeRequests() {
        System.out.println("accessEmployeeRequests");
        Admin admin = null;
        JFrame expResult = null;
        JFrame result = Access.accessEmployeeRequests(admin);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of accessRequestCenter method, of class Access.
     */
    @Test
    public void testAccessRequestCenter() {
        System.out.println("accessRequestCenter");
        User user = null;
        JFrame expResult = null;
        JFrame result = Access.accessRequestCenter(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }   
}