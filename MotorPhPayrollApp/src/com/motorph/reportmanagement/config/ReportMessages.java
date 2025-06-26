/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.reportmanagement.config;

/**
 *
 * @author 63909
 */
public class ReportMessages {
    // ===== Success Messages =====
    public static final String REPORT_GENERATED_SUCCESS = "Report generated successfully.";
    public static final String REPORT_COMPILATION_SUCCESS = "Report template compiled successfully.";

    // ===== Error Messages =====
    public static final String ERROR_COMPILING_TEMPLATE = "An error occurred while compiling the JasperReport template.";
    public static final String ERROR_FILLING_REPORT = "An error occurred while filling the report with data.";
    public static final String ERROR_DB_CONNECTION = "Failed to establish a connection to the database.";
    public static final String ERROR_EXPORTING_REPORT = "An error occurred while exporting the report to file.";
    public static final String ERROR_TEMPLATE_NOT_FOUND = "The specified report template file was not found.";

    // ===== Warning / Informational =====
    public static final String MISSING_PARAMETERS = "Required report parameters are missing or incomplete.";
    public static final String REPORT_PREVIEW_MODE = "The report is being generated in preview mode. Data may not be final.";

}
