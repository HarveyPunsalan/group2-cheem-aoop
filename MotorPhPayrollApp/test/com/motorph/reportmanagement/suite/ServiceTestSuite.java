/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.reportmanagement.suite;

import com.motorph.reportmanagement.service.PayrollBiWeeklySummaryReportServiceTest;
import com.motorph.reportmanagement.service.PayrollBiWeeklySummaryResultMapperTest;
import com.motorph.reportmanagement.service.PayslipResultMapperTest;
import com.motorph.reportmanagement.service.PayslipServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite for all service and mapper tests in the Report Management module.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    PayslipServiceTest.class,
    PayslipResultMapperTest.class,
    PayrollBiWeeklySummaryReportServiceTest.class,
    PayrollBiWeeklySummaryResultMapperTest.class
})
public class ServiceTestSuite {
    // No implementation needed; used only to group test classes
}
