/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.reportmanagement.exporter;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * Implementation of {@link ReportExporter} that exports reports to a PDF file.
 */
public class PdfReportExporter implements ReportExporter {

    /**
     * Exports the given {@link JasperPrint} to a PDF file at the specified output path.
     *
     * @param jasperPrint the filled report to export
     * @param outputPath  the file path where the PDF should be saved
     * @throws Exception if export fails due to I/O or report generation errors
     */
    @Override
    public void exportToFile(JasperPrint jasperPrint, String outputPath) throws Exception {
        if (jasperPrint == null) {
            throw new IllegalArgumentException("JasperPrint must not be null.");
        }
        
        if (outputPath == null || outputPath.isBlank()) {
            throw new IllegalArgumentException("Output path must not be null or blank.");
        }

        JasperExportManager.exportReportToPdfFile(jasperPrint, outputPath);
    }
}
