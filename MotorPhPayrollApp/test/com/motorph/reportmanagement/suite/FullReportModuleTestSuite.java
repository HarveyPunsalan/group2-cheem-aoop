/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.reportmanagement.suite;

import com.motorph.reportmanagement.controller.PayrollBiWeeklySummaryControllerTest;
import com.motorph.reportmanagement.controller.PayslipControllerTest;
import com.motorph.reportmanagement.exporter.PdfReportExporterTest;
import com.motorph.reportmanagement.service.PayrollBiWeeklySummaryReportServiceTest;
import com.motorph.reportmanagement.service.PayrollBiWeeklySummaryResultMapperTest;
import com.motorph.reportmanagement.service.PayslipResultMapperTest;
import com.motorph.reportmanagement.service.PayslipServiceTest;
import com.motorph.reportmanagement.util.PDFExportHelperTest;
import com.motorph.reportmanagement.util.ReportFileUtilsTest;
import com.motorph.reportmanagement.util.ReportParameterBuilderTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author 63909
 */
/**
 * Master test suite for the entire Report Management Module.
 * Runs all controller, service, exporter, and util test classes.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({    
    // Exporter tests
    PdfReportExporterTest.class,

    // Service & Mapper tests
    PayslipServiceTest.class,
    PayslipResultMapperTest.class,
    PayrollBiWeeklySummaryReportServiceTest.class,
    PayrollBiWeeklySummaryResultMapperTest.class,

    // Utility tests
    PDFExportHelperTest.class,
    ReportFileUtilsTest.class,
    ReportParameterBuilderTest.class,
        
    // Controller tests
    PayslipControllerTest.class,
    PayrollBiWeeklySummaryControllerTest.class
})
public class FullReportModuleTestSuite {
    // No code needed — this is purely annotation-driven
}
