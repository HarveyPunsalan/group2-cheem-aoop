/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.model;

import com.motorph.usermanagement.view.CompanyHomePage;
import com.motorph.employeemanagement.view.admin.RequestCenter;
import com.motorph.employeemanagement.view.selfservice.EmployeeRequests;
import com.motorph.employeemanagement.view.selfservice.ProfilePage;
import com.motorph.employeemanagement.view.admin.ViewEmployeeDetails;
import com.motorph.employeemanagement.view.admin.EmployeeInformation;
import com.motorph.attendancemanagement.view.selfservice.EmployeeAttendance;
import com.motorph.payrollprocessing.view.selfservice.EmployeePayslip;
import com.motorph.payrollprocessing.view.admin.PayrollList;
import com.motorph.payrollprocessing.view.admin.PayrollEmployeeSelection;
import com.motorph.payrollprocessing.view.admin.PayrollEarnings;
import com.motorph.payrollprocessing.view.admin.PayrollDeductions;
import com.motorph.payrollprocessing.view.admin.PayrollSummaryPage;
import com.motorph.payrollprocessing.view.admin.PayrollNetPay;
import com.motorph.attendancemanagement.view.admin.AttendanceBiweekly;
import com.motorph.attendancemanagement.view.admin.AttendanceDailyRecord;
import com.motorph.payrollprocessing.model.payroll.PayPeriod;
import com.motorph.employeemanagement.model.Employee;
import javax.swing.JFrame;
import java.util.logging.Logger;

/**
 * This Handles user access to various parts of the system
 * 
 * @author Harvey 
 */
public class Access {
    private static final Logger logger = Logger.getLogger(Access.class.getName());
    
    // Route admin to the company homepage
    public static JFrame accessCompanyHomePage(Admin admin) {
        if (admin == null) {
            logger.warning("Attempted to access Company Home Page with null admin reference.");
            return null;
        }
        logger.info(() -> "Opening Company Home Page for admin: " + admin.getUsername());
        CompanyHomePage companyHomePage = new CompanyHomePage(admin);
        companyHomePage.setVisible(true);
        return companyHomePage;
    }
    
    // Route NonAdmin to a landing page using ProfilePage as their main landing
    public static JFrame accessNonAdminLandingPage(NonAdmin nonAdmin) {
        if (nonAdmin == null) {
            logger.warning("Attempted to access NonAdmin Landing Page with null nonAdmin reference.");
            return null;
        }
        logger.info(() -> "Opening Landing Page for NonAdmin: " + nonAdmin.getUsername());
        ProfilePage landingPage = new ProfilePage(nonAdmin);
        landingPage.setVisible(true);
        return landingPage;
    }
    
    // Generic method to route any User to appropriate landing page
    public static JFrame accessUserLandingPage(User user) {
        if (user == null) {
            logger.warning("Attempted to access landing page with null user reference.");
            return null;
        }
        
        if (user instanceof Admin admin) {
            logger.info(() -> "Routing Admin to Company Home Page: " + admin.getUsername());
            return accessCompanyHomePage(admin);
        } else if (user instanceof NonAdmin nonAdmin) {
            logger.info(() -> "Routing NonAdmin to Landing Page: " + nonAdmin.getUsername());
            return accessNonAdminLandingPage(nonAdmin);
        } else {
            // Fallback for generic User objects (treat as NonAdmin)
            logger.info(() -> "Routing generic User to Profile Page: " + user.getUsername());
            ProfilePage profilePage = new ProfilePage(user);
            profilePage.setVisible(true);
            return profilePage;
        }
    }
        
    // Route admin to employee info management 
    public static JFrame accessEmployeeInformation(Admin admin) {
        if (admin == null) {
            logger.warning("Attempted to access Employee Information with null admin reference.");
            return null;
        }
        logger.info(() -> "Opening Employee Information for admin: " + admin.getUsername());
        EmployeeInformation employeeInformation = new EmployeeInformation(admin);
        employeeInformation.setVisible(true);
        return employeeInformation;
    }

    // Self Service Portal access for Admin 
    public static JFrame accessSelfServicePortal(Admin admin) {
        if (admin == null) {
            logger.warning("Attempted to access Self Service Portal with null admin reference.");
            return null;
        }
        logger.info(() -> "Opening Self Service Portal for admin: " + admin.getUsername());
        // For admin accessing self service portal, we should open their profile page
        ProfilePage profilePage = new ProfilePage(admin);
        profilePage.setVisible(true);
        return profilePage;
    }
    
    // Generic Self Service Portal access for any User
    public static JFrame accessSelfServicePortal(User user) {
        if (user == null) {
            logger.warning("Attempted to access Self Service Portal with null user reference.");
            return null;
        }
        logger.info(() -> "Opening Self Service Portal for user: " + user.getUsername());
        ProfilePage profilePage = new ProfilePage(user);
        profilePage.setVisible(true);
        return profilePage;
    }
    
    // Admin views a specific employee's DTR (by ID + pay period)
    public static JFrame accessDTR(Admin admin, int employeeID, PayPeriod selectedPayPeriod) {
        if (admin == null) {
            logger.warning("Attempted to access DTR with null admin reference.");
            return null;
        }
        logger.info(() -> "Opening DTR for employee: " + employeeID + " by admin: " + admin.getUsername());
        AttendanceDailyRecord dailyAttendanceRecord = new AttendanceDailyRecord(admin, employeeID, selectedPayPeriod);
        dailyAttendanceRecord.setVisible(true);
        return dailyAttendanceRecord;
    }
    
    // Admin views a specific employee's DTR (by Employee object)
    public static JFrame accessDTR(Admin admin, Employee employee) {
        if (admin == null || employee == null) {
            logger.warning("Attempted to access DTR with null admin or employee reference.");
            return null;
        }
        logger.info(() -> "Opening DTR for employee: " + employee.getEmployeeId() + " by admin: " + admin.getUsername());
        AttendanceDailyRecord dailyAttendanceRecord = new AttendanceDailyRecord(admin, employee);
        dailyAttendanceRecord.setVisible(true);
        return dailyAttendanceRecord;
    }
    
    // Employee views their own DTR
    public static JFrame accessDTR(Employee employee) {
        if (employee == null) {
            logger.warning("Attempted to access DTR with null employee reference.");
            return null;
        }
        logger.info(() -> "Opening DTR for employee: " + employee.getEmployeeId());
        AttendanceDailyRecord dailyAttendanceRecord = new AttendanceDailyRecord(employee);
        dailyAttendanceRecord.setVisible(true);
        return dailyAttendanceRecord;
    }
    
    // Admin views biweekly attendance records
    public static JFrame accessAttendanceBiweekly(Admin admin) {
        if (admin == null) {
            logger.warning("Attempted to access Attendance Biweekly with null admin reference.");
            return null;
        }
        logger.info(() -> "Opening Attendance Biweekly for admin: " + admin.getUsername());
        AttendanceBiweekly attendanceBiweeklyRecord = new AttendanceBiweekly(admin);
        attendanceBiweeklyRecord.setVisible(true);
        return attendanceBiweeklyRecord;
    }
    
    // View another employee's details
    public static JFrame accessViewEmployeeDetails(User user, int employeeID) {
        if (user == null) {
            logger.warning("Attempted to access Employee Details with null user reference.");
            return null;
        }
        logger.info(() -> "Opening Employee Details for employee: " + employeeID + " by user: " + user.getUsername());
        ViewEmployeeDetails employeeDetails = new ViewEmployeeDetails((Admin) user, employeeID);
        employeeDetails.setVisible(true);
        return employeeDetails;
    }
    
    // View own or someone else's details, depends on role 
    public static JFrame accessViewEmployeeDetails(User user) {
        if (user == null) {
            logger.warning("Attempted to access employee details with a null user reference.");
            return null;
        }

        if (user instanceof Admin admin) {
            logger.info(() -> "Opening Employee Details for admin: " + admin.getUsername());
            ViewEmployeeDetails employeeDetails = new ViewEmployeeDetails(admin);
            employeeDetails.setVisible(true);
            return employeeDetails;
        } else {
            logger.warning(() -> "Non-admin user attempted to access employee details: " + user.getUsername());
            return null;
        }
    } 
    
    // View another employee's details
    public static JFrame accessViewEmployeeDetails(int employeeID) {
//        if (user == null) {
//            logger.warning("Attempted to access Employee Details with null user reference.");
//            return null;
//        }
        logger.info(() -> "Opening Employee Details for employee: " + employeeID);
        ViewEmployeeDetails employeeDetails = new ViewEmployeeDetails(employeeID);
        employeeDetails.setVisible(true);
        return employeeDetails;
    }
    
    // Show profile page (employee or admin) 
    public static JFrame accessProfilePage(User user) {
        if (user == null) {
            logger.warning("Attempted to access Profile Page with null user reference.");
            return null;
        }
        logger.info(() -> "Opening Profile Page for user: " + user.getUsername());
        ProfilePage profilePage = new ProfilePage(user);
        profilePage.setVisible(true);
        return profilePage;
    }
    
    // Show payslip page
    public static JFrame accessEmployeePayslip(User user) {
        if (user == null) {
            logger.warning("Attempted to access Employee Payslip with null user reference.");
            return null;
        }
        logger.info(() -> "Opening Employee Payslip for user: " + user.getUsername());
        EmployeePayslip employeePayslip = new EmployeePayslip(user);
        employeePayslip.setVisible(true);
        return employeePayslip;
    }
    
    // Show attendance view
    public static JFrame accessEmployeeAttendance(User user) {
        if (user == null) {
            logger.warning("Attempted to access Employee Attendance with null user reference.");
            return null;
        }
        logger.info(() -> "Opening Employee Attendance for user: " + user.getUsername());
        EmployeeAttendance employeeAttendance = new EmployeeAttendance(user);
        employeeAttendance.setVisible(true);
        return employeeAttendance;
    }
    
    // Show payroll list (admin only)
    public static JFrame accessPayrollList(Admin admin) {
        if (admin == null) {
            logger.warning("Attempted to access Payroll List with null admin reference.");
            return null;
        }
        if (admin.isAdmin()) {
            logger.info(() -> "Opening Payroll List for admin: " + admin.getUsername());
            PayrollList payrollList = new PayrollList(admin);
            payrollList.setVisible(true);
            return payrollList;
        } else {
            logger.warning(() -> "Non-admin user attempted to access payroll list: " + admin.getUsername());
            throw new SecurityException("Access denied: Admin privileges required");
        }
    }
    
    // Show payroll employee selector for processing
    public static JFrame accessPayrollEmployeeSelection(Admin admin, PayPeriod payPeriod) {
        if (admin == null) {
            logger.warning("Attempted to access Payroll Employee Selection with null admin reference.");
            return null;
        }
        if (admin.isAdmin()) {
            logger.info(() -> "Opening Payroll Employee Selection for admin: " + admin.getUsername());
            PayrollEmployeeSelection payrollEmployeeSelection = new PayrollEmployeeSelection(admin, payPeriod);
            payrollEmployeeSelection.setVisible(true);
            return payrollEmployeeSelection;
        } else {
            logger.warning(() -> "Non-admin user attempted to access payroll employee selection: " + admin.getUsername());
            throw new SecurityException("Access denied: Admin privileges required");
        }
    }
    
    // Show earnings (admin only)
    public static JFrame accessPayrollEarnings(Admin admin, PayPeriod payPeriod) {
        if (admin == null) {
            logger.warning("Attempted to access Payroll Earnings with null admin reference.");
            return null;
        }
        if (admin.isAdmin()) {
            logger.info(() -> "Opening Payroll Earnings for admin: " + admin.getUsername());
            PayrollEarnings payrollEmployeeEarning = new PayrollEarnings(admin, payPeriod);
            payrollEmployeeEarning.setVisible(true);
            return payrollEmployeeEarning;
        } else {
            throw new SecurityException("Access denied: Admin privileges required");
        }
    }
    
    // Show earnings (generic, no admin check)
    public static JFrame accessPayrollEarnings(PayPeriod payPeriod) {
        if (payPeriod == null) {
            logger.warning("Attempted to access Payroll Earnings with null PayPeriod reference.");
            return null;
        }
        logger.info(() -> "Opening Payroll Earnings for pay period: " + payPeriod);
        PayrollEarnings payrollEmployeeEarning = new PayrollEarnings(payPeriod);
        payrollEmployeeEarning.setVisible(true);
        return payrollEmployeeEarning;
    }
    
    // Show deductions (admin only)
    public static JFrame accessPayrollDeductions(Admin admin, PayPeriod payPeriod) {
        if (admin == null) {
            logger.warning("Attempted to access Payroll Deductions with null admin reference.");
            return null;
        }
        if (admin.isAdmin()) {
            logger.info(() -> "Opening Payroll Deductions for admin: " + admin.getUsername());
            PayrollDeductions payrollEmployeeDeduction = new PayrollDeductions(admin, payPeriod);
            payrollEmployeeDeduction.setVisible(true);
            return payrollEmployeeDeduction;
        } else {
            throw new SecurityException("Access denied: Admin privileges required");
        }
    }
    
    // Show deductions (general)
    public static JFrame accessPayrollDeductions(PayPeriod payPeriod) {
        if (payPeriod == null) {
            logger.warning("Attempted to access Payroll Deductions with null PayPeriod reference.");
            return null;
        }
        logger.info(() -> "Opening Payroll Deductions for pay period: " + payPeriod);
        PayrollDeductions payrollEmployeeDeduction = new PayrollDeductions(payPeriod);
        payrollEmployeeDeduction.setVisible(true);
        return payrollEmployeeDeduction;
    }
    
    // Show net pay (admin)
    public static JFrame accessPayrollNetPay(Admin admin, PayPeriod payPeriod) {
        if (admin == null) {
            logger.warning("Attempted to access Payroll Net Pay with null admin reference.");
            return null;
        }
        if (admin.isAdmin()) {
            logger.info(() -> "Opening Payroll Net Pay for admin: " + admin.getUsername());
            PayrollNetPay payrollEmployeeNetPay = new PayrollNetPay(admin, payPeriod);
            payrollEmployeeNetPay.setVisible(true);
            return payrollEmployeeNetPay;
        } else {
            throw new SecurityException("Access denied: Admin privileges required");
        }
    }
    
    // Show net pay (open access)
    public static JFrame accessPayrollNetPay(PayPeriod payPeriod) {
        if (payPeriod == null) {
            logger.warning("Attempted to access Payroll Net Pay with null PayPeriod reference.");
            return null;
        }
        logger.info(() -> "Opening Payroll Net Pay for pay period: " + payPeriod);
        PayrollNetPay payrollEmployeeNetPay = new PayrollNetPay(payPeriod);
        payrollEmployeeNetPay.setVisible(true);
        return payrollEmployeeNetPay;
    }
    
    // Show summary page (admin)
    public static JFrame accessPayrollSummaryPage(Admin admin, PayPeriod payPeriod) {
        if (admin == null) {
            logger.warning("Attempted to access Payroll Summary with null admin reference.");
            return null;
        }
        if (admin.isAdmin()) {
            logger.info(() -> "Opening Payroll Summary for admin: " + admin.getUsername());
            PayrollSummaryPage payrollSummaryPage = new PayrollSummaryPage(admin, payPeriod);
            payrollSummaryPage.setVisible(true);
            return payrollSummaryPage;
        } else {
            throw new SecurityException("Access denied: Admin privileges required");
        }
    }
    
    // Show summary page (open access)
    public static JFrame accessPayrollSummaryPage(PayPeriod payPeriod) {
        if (payPeriod == null) {
            logger.warning("Attempted to access Payroll Summary with null PayPeriod reference.");
            return null;
        }
        logger.info(() -> "Opening Payroll Summary for pay period: " + payPeriod);
        PayrollSummaryPage payrollSummaryPage = new PayrollSummaryPage(payPeriod);
        payrollSummaryPage.setVisible(true);
        return payrollSummaryPage;
    }
    
    // Admin handles employee requests
    public static JFrame accessEmployeeRequests(Admin admin) {
        if (admin == null) {
            logger.warning("Attempted to access Employee Requests with null admin reference.");
            return null;
        }
        if (admin.isAdmin()) {
            logger.info(() -> "Opening Employee Requests for admin: " + admin.getUsername());
            EmployeeRequests employeeRequests = new EmployeeRequests(admin);
            employeeRequests.setVisible(true);
            return employeeRequests;
        } else {
            throw new SecurityException("Access denied: Admin privileges required");
        }
    }
    
    // Show request center (accessible by user)
    public static JFrame accessRequestCenter(User user) {
        if (user == null) {
            logger.warning("Attempted to access Request Center with null user reference.");
            return null;
        }
        logger.info(() -> "Opening Request Center for user: " + user.getUsername());
        RequestCenter employeeRequestCenter = new RequestCenter(user);
        employeeRequestCenter.setVisible(true);
        return employeeRequestCenter;
    }
}