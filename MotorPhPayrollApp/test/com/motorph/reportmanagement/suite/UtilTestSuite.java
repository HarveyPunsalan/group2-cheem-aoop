/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.reportmanagement.suite;

import com.motorph.reportmanagement.util.PDFExportHelperTest;
import com.motorph.reportmanagement.util.ReportFileUtilsTest;
import com.motorph.reportmanagement.util.ReportParameterBuilderTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author 63909
 */
// 📦 Grouping all utility tests into one suite
@RunWith(Suite.class)
@Suite.SuiteClasses({
    ReportFileUtilsTest.class,
    ReportParameterBuilderTest.class,
    PDFExportHelperTest.class
})
public class UtilTestSuite {
    // No implementation needed — just annotation-based test aggregation
}
