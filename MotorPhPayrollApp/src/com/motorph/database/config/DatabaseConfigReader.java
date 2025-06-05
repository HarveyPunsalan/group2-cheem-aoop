/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.database.config;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author 63909
 */

/**
 * Singleton class to read and access MotorPH database configuration properties.
 * Ensures the configuration is loaded once and reused throughout the application.
 */
public class DatabaseConfigReader {
    private static final Logger logger = Logger.getLogger(DatabaseConfigReader.class.getName());
    private static DatabaseConfigReader instance;
    private final Properties properties;

    /**
     * Private constructor loads the properties file once to ensure efficiency
     * and to avoid redundant file I/O operations.
     */
    private DatabaseConfigReader() {
        properties = new Properties();

        String configFileName = DatabaseConfigFile.MOTORPH_DB_CONFIG_FILE;

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(configFileName)) {

            if (input != null) {
                properties.load(input);
                logger.info("Successfully loaded DB configuration from: " + configFileName);
            } else {
                logger.severe("Configuration file not found in classpath: " + configFileName);
                throw new IOException("Config file not found: " + configFileName);
            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading database configuration from: " + configFileName, e);
            throw new RuntimeException("Failed to load DB config: " + e.getMessage(), e);
        }
    }

    /**
     * Returns the singleton instance of the config reader.
     * Uses lazy initialization and is thread-safe.
     *
     * @return the single instance of {@code DatabaseConfigReader}
     */
    public static synchronized DatabaseConfigReader getInstance() {
        if (instance == null) {
            instance = new DatabaseConfigReader();
        }
        return instance;
    }

    /**
     * Retrieves the loaded {@code Properties} object.
     *
     * @return database configuration properties
     */
    public Properties getProperties() {
        return properties;
    }

    public String getUrl() {
        return properties.getProperty("db.url");
    }

    public String getUsername() {
        return properties.getProperty("db.username");
    }

    public String getPassword() {
        return properties.getProperty("db.password");
    }

    public String getDbName() {
        return properties.getProperty("db.name", "motorph_payroll_db");
    }
}
