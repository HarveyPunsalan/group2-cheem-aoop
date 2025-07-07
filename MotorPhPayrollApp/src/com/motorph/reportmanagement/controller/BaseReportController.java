/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.reportmanagement.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract base class for report controllers to standardize service invocation,
 * error handling, and logging behavior.
 *
 * @param <T> the type of the underlying report service (e.g., PayslipService, SummaryService)
 */
public abstract class BaseReportController<T> {

    protected final T service;
    private final Logger logger;

    /**
     * Constructs a controller wrapper with a specified report service and logger.
     *
     * @param service the report service to delegate business logic to
     * @param controllerClass the class used for logger scoping
     */
    public BaseReportController(T service, Class<?> controllerClass) {
        this.service = service;
        this.logger = Logger.getLogger(controllerClass.getName());
    }

    /**
     * Template method for executing a report-generation action with unified exception handling.
     *
     * @param action       the report generation logic (as a lambda or functional interface)
     * @param errorMessage the custom error message to log if an exception occurs
     * @return true if the action was successful; false if an exception was caught
     */
    protected boolean executeReportGeneration(ReportAction action, String errorMessage) {
        try {            
            return action.run();
        } catch (Exception e) {
            logger.log(Level.SEVERE, errorMessage, e);
            return false;
        }
    }

    /**
     * Functional interface representing a report generation action that may throw an exception.
     */
    @FunctionalInterface
    protected interface ReportAction {
        boolean run() throws Exception;
    }
}
