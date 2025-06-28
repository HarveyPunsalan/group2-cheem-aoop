/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.reportmanagement.suite;

import com.motorph.reportmanagement.controller.PayrollBiWeeklySummaryControllerTest;
import com.motorph.reportmanagement.controller.PayslipControllerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite that groups all controller tests related to report generation.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    PayslipControllerTest.class,
    PayrollBiWeeklySummaryControllerTest.class
    // Add more test classes here as needed
})
public class ReportControllerTestSuite {
    // No code needed. The annotations do everything.
}
