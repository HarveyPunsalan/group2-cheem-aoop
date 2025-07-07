/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.reportmanagement.util;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 63909
 */

/**
 * Utility class for customizing exported PDF reports.
 */
public class PDFExportHelper {

    private static final Logger LOGGER = Logger.getLogger(PDFExportHelper.class.getName());

    /**
     * Sets basic PDF metadata such as title and author.
     *
     * @param writer the PdfWriter associated with the document
     * @param title the title of the document
     * @param author the author name (e.g., MotorPH System)
     */
    public static void setPdfMetadata(PdfWriter writer, String title, String author) {
        Document document = writer.getDirectContent().getPdfDocument();
        document.addTitle(title);
        document.addAuthor(author);
        document.addCreationDate();
    }

    /**
     * Simple utility to test PDF creation (optional demo use).
     * @param outputPath
     */
    public static void createSimplePdf(String outputPath) {
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputPath));
            document.open();
            setPdfMetadata(writer, "MotorPH Report", "MotorPH System");
            document.add(new com.lowagie.text.Paragraph("This is a test PDF for export helper."));
            LOGGER.log(Level.INFO, "PDF generated: {0}", outputPath);
        } catch (DocumentException | FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "PDF generation failed: " + outputPath, e);
        } finally {
            document.close();
        }
    }
}
