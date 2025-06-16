/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.PPS;


import com.motorph.database.connection.DatabaseConnector;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import java.io.File;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;


/**
 *
 * @author emmar
 */
public class ReportUtil {
    
    private static final Logger LOGGER = Logger.getLogger(ReportUtil.class.getName());
    
    //Path/location to jrxml templates
    private static final String PAYSLIP_JRXML = "scr/Reports/MotorPH_Payslip.jrxml";
    private static final String SUMMARY_JRXML= "src/Reports/Summary_Report.jrxml";
    
    /**
     * Generates a PDF payslip report for a given payslip ID.
     */
    public static void generatePayslipFromDB(String payslipID) {
        try (Connection conn = DatabaseConnector.connect()) {

            JasperReport jasperReport = JasperCompileManager.compileReport(PAYSLIP_JRXML);

            // Fill parameters
            Map<String, Object> params = new HashMap<>();
            params.put("PayslipID", payslipID);
            params.put("GeneratedOn", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);

            // Ensure output directory
            String dirPath = "payslips";
            File dir = new File(dirPath);
            if (!dir.exists()) dir.mkdirs();

            // Filename with timestamp
            String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String safePayslipID = payslipID.replaceAll("\\s+", "_");
            String outputFile = String.format("%s/Payslip_%s_%s.pdf", dirPath, ts, safePayslipID);

            // Export PDF and open
            JasperExportManager.exportReportToPdfFile(jasperPrint, outputFile);
            java.awt.Desktop.getDesktop().open(new File(outputFile));

            LOGGER.info("Payslip generated: " + outputFile);

        } catch (Exception e) {
            LOGGER.severe("Error generating payslip: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Generates payroll summary report for the specified period (PDF and Excel).
     */
    public static void generateSummaryReportFromDB(String periodStart, String periodEnd) {
        try (Connection conn = DatabaseConnector.connect()) {

            JasperReport jasperReport = JasperCompileManager.compileReport(SUMMARY_JRXML);

            Map<String, Object> params = new HashMap<>();
            params.put("PeriodStart", periodStart);
            params.put("PeriodEnd", periodEnd);
            params.put("GeneratedOn", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);

            // Output folder
            String dirPath = "summary_reports";
            File dir = new File(dirPath);
            if (!dir.exists()) dir.mkdirs();

            // Timestamps
            String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String pdfFileName = String.format("%s/Summary_%s.pdf", dirPath, ts);
            String excelFileName = String.format("%s/Summary_%s.xlsx", dirPath, ts);

            // Export PDF
            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFileName);

            // Export Excel
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(excelFileName));
            SimpleXlsxReportConfiguration config = new SimpleXlsxReportConfiguration();
            config.setDetectCellType(true);
            config.setCollapseRowSpan(false);
            exporter.setConfiguration(config);
            exporter.exportReport();

            // Auto-open
            java.awt.Desktop.getDesktop().open(new File(pdfFileName));
            java.awt.Desktop.getDesktop().open(new File(excelFileName));

            LOGGER.info("Summary PDF: " + pdfFileName);
            LOGGER.info("Summary Excel: " + excelFileName);

        } catch (Exception e) {
            LOGGER.severe("Error generating summary report: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Helper to call summary report using PayPeriod object.
     */
    public static void generatePayrollSummaryReport(PayPeriod payrollPayPeriod) {
        String periodStart = payrollPayPeriod.getStartDate().toString();
        String periodEnd = payrollPayPeriod.getEndDate().toString();
        generateSummaryReportFromDB(periodStart, periodEnd);
    }
}
