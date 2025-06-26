/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.databasemanagement.config;

/**
 *
 * @author 63909
 */

/**
 * This class holds constants related to the MotorPH Payroll System's
 * database configuration file. It centralizes configuration file references
 * to promote consistency and maintainability across the database module.
 */
public class DatabaseConfigFile {

    /**
     * The relative path to the MotorPH database configuration file.
     * This file is located under the project's resources directory.
     * Used to load database connection properties such as URL, username, and password.
     */
    public static final String MOTORPH_DB_CONFIG_FILE = "resources/db.properties";

    /*
     * Private constructor to prevent instantiation of this utility class.
     * Since this class only contains constants, object creation is not allowed.
     */
    private DatabaseConfigFile() {}
}

