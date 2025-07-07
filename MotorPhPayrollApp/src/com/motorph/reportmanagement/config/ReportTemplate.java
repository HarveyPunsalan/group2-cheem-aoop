/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.reportmanagement.config;

/**
 *
 * @author 63909
 */
public enum ReportTemplate {
  PAYSLIP("MotorPH_Payslip.jrxml"),
  PAYROLL_MONTHLY_SUMMARY("Summary_Report.jrxml");

  private static final String BASE_DIR = ReportConfig.TEMPLATE_DIRECTORY;
  private final String fileName;

  ReportTemplate(String fileName) {
    this.fileName = fileName;
  }

  public String getPath() {
    return BASE_DIR + fileName;
  }
}
