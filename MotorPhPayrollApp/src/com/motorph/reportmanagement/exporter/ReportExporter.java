/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.motorph.reportmanagement.exporter;

import net.sf.jasperreports.engine.JasperPrint;

/**
 * Strategy interface for exporting a {@link JasperPrint} report to a file.
 * 
 * <p>Implementations of this interface handle specific export formats,
 * such as PDF, Excel, CSV, etc.</p>
 */
public interface ReportExporter {
    
    /**
     * Exports the given {@link JasperPrint} to a file at the specified output path.
     *
     * @param jasperPrint the compiled and filled report to export
     * @param outputPath the full file system path where the output should be written
     * @throws Exception if an error occurs during the export process
     */
    void exportToFile(JasperPrint jasperPrint, String outputPath) throws Exception;
}
