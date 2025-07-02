/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.reportmanagement.util;

import com.motorph.reportmanagement.config.ReportConfig;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class for file operations related to report generation.
 * Handles export directory setup, timestamped filename creation, path generation, and file deletion.
 */
public class ReportFileUtils {
    private static final Logger LOGGER = Logger.getLogger(ReportFileUtils.class.getName());
    
    // Default export directory loaded from configuration
    private static final String DEFAULT_EXPORT_DIR = ReportConfig.OUTPUT_DIRECTORY;
    
    // Current export directory (overridable at runtime)
    private static String exportDirectory = ReportConfig.OUTPUT_DIRECTORY;
    
    // Timestamp format used in filenames (e.g., yyyyMMdd_HHmm)
    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern(ReportConfig.TIMESTAMP_FORMAT);
    
    /**
     * Overrides the export directory path. Useful for unit tests or runtime configuration.
     *
     * @param path directory path (e.g., "test-output/")
     */
    public static void setExportDirectory(String path) {
        if (path != null && !path.isBlank()) {
            exportDirectory = path.endsWith("/") ? path : path + "/";
        }
    }

    /**
     * Resets the export directory back to the default as defined in {@link ReportConfig}.
     */
    public static void resetExportDirectory() {
        exportDirectory = ReportConfig.OUTPUT_DIRECTORY;
    }

    /**
     * Gets the current export directory.
     *
     * @return current export path as a string
     */
    private static String getExportDirectory() {
        return exportDirectory;
    }
    
    /**
     * Builds a full filename with a timestamp and optional parts.
     *
     * Example output: Payslip_20250622_1205_EID123_PID456.pdf
     *
     * @param baseName  base filename (e.g., "Payslip", "Summary")
     * @param extension file extension without dot (e.g., "pdf", "csv")
     * @param parts     optional parts to append (e.g., employee ID, payroll ID)
     * @return filename string with timestamp and parts
     * @throws IllegalArgumentException if baseName or extension is null/blank
     */
    public static String buildFileName(String baseName, String extension, String... parts) {
        if (baseName == null || baseName.isBlank()) {
            throw new IllegalArgumentException("Base name must not be null or blank");
        }
        if (extension == null || extension.isBlank()) {
            throw new IllegalArgumentException("Extension must not be null or blank");
        }
    
        StringBuilder fileName = new StringBuilder(baseName);
        fileName.append("_").append(LocalDateTime.now().format(TIMESTAMP_FORMAT));

        // Append extra parts (e.g., EID101, PID5006)
        for (String part : parts) {
            if (part != null && !part.isBlank()) {
                fileName.append("_").append(part);
            }
        }

            fileName.append(".").append(extension);
            
        return fileName.toString();
    }    

    /**
     * Ensures the current export directory exists, and creates it if missing.
     * Logs status of directory creation.
     */
    public static void ensureExportDirectoryExists() {
        File dir = new File(getExportDirectory());
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            if (created) {
                LOGGER.info("Created export directory: " + getExportDirectory());
            } else {
                LOGGER.warning("Failed to create export directory: " + getExportDirectory());
            }
        }
    }

    /**
     * Generates a basic timestamped filename.
     * Example: "Summary_20250622_1530.pdf"
     *
     * @param baseName  base filename (e.g., "Summary")
     * @param extension file extension (e.g., "pdf")
     * @return filename with appended timestamp and extension
     * @throws IllegalArgumentException if baseName or extension is null
     */
    public static String generateTimestampedFilename(String baseName, String extension) {
        if (baseName == null || extension == null) {
            throw new IllegalArgumentException("Base name and extension must not be null");
        }
        
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
        return baseName + "_" + timestamp + "." + extension;
    }

    /**
     * Returns the full export path for a given filename, ensuring the export directory exists first.
     *
     * @param filename file name (e.g., "Payslip_20250622.pdf")
     * @return full file path as string (e.g., "output/Payslip_20250622.pdf")
     */
    public static String getExportPath(String filename) {
        ensureExportDirectoryExists();
        return getExportDirectory() + filename; 
    }

    /**
     * Deletes a file if it exists. Logs the result.
     *
     * @param filePath the full path to the file
     * @return true if file was deleted, false if it did not exist or failed to delete
     */
    public static boolean deleteFileIfExists(String filePath) {
        try {
            boolean deleted = Files.deleteIfExists(new File(filePath).toPath());
            if (deleted) {
                LOGGER.log(Level.INFO, "Deleted file: {0}", filePath);
            } else {
                LOGGER.log(Level.INFO, "No file to delete at: {0}", filePath);
            }
            return deleted;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to delete file: " + filePath, e);
            return false;
        }
    }
}
