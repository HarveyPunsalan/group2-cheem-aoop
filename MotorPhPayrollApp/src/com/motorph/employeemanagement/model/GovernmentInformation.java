/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.employeemanagement.model;

import java.util.List;

/**
 * Represents government-issued identification numbers associated with an employee.
 * This includes SSS, PhilHealth, Pag-IBIG, and TIN identifiers.
 * 
 */
public class GovernmentInformation extends Information {

    private int govInfoID; 
    private String sssNumber;
    private String philhealthNumber;
    private String pagibigNumber;
    private String taxIdentificationNumber; 

    // Default constructor
    public GovernmentInformation() {
        super(0);
    }
    
    /**
     * Constructor used when inserting new government information
     *
     * @param employeeID              ID of the employee
     * @param sssNumber               SSS number
     * @param philhealthNumber        PhilHealth number
     * @param pagibigNumber           Pag-IBIG number
     * @param taxIdentificationNumber TIN number
     */
    public GovernmentInformation(int employeeID, String sssNumber, String philhealthNumber,
                                 String pagibigNumber, String taxIdentificationNumber) {
        super(employeeID);
        this.sssNumber = sssNumber;
        this.philhealthNumber = philhealthNumber;
        this.pagibigNumber = pagibigNumber;
        this.taxIdentificationNumber = taxIdentificationNumber;
    }

    /**
     * Constructor used when retrieving government information from the database.
     *
     * @param govInfoID               Unique ID of the government record
     * @param employeeID              ID of the employee
     * @param sssNumber               SSS number
     * @param philhealthNumber        PhilHealth number
     * @param pagibigNumber           Pag-IBIG number
     * @param taxIdentificationNumber TIN
     */
    public GovernmentInformation(int govInfoID, int employeeID, String sssNumber, String philhealthNumber,
                                 String pagibigNumber, String taxIdentificationNumber) {
        super(employeeID);
        this.govInfoID = govInfoID;
        this.sssNumber = sssNumber;
        this.philhealthNumber = philhealthNumber;
        this.pagibigNumber = pagibigNumber;
        this.taxIdentificationNumber = taxIdentificationNumber;
    }

        public List<Object> toInsertParams() {
        return List.of(
            employeeID,
            sssNumber,
            philhealthNumber,
            pagibigNumber,
            taxIdentificationNumber
//            withholdingTaxStatus
        );
    }

    public List<Object> toUpdateParams() {
        return List.of(
            sssNumber,
            philhealthNumber,
            pagibigNumber,
            taxIdentificationNumber,
//            withholdingTaxStatus,
            employeeID
        );
    }
    
    /**
     * Retrieves the government-related information as an array of strings.
     *
     * @return an array containing the employee ID, SSS number, PhilHealth number,
     * Pag-IBIG number, and Tax Identification Number.
     */
    @Override
    public String[] getInformation() {
        // Return an array with government-related details.
        return new String[] {String.valueOf(employeeID),
                            sssNumber,
                            philhealthNumber,
                            pagibigNumber,
                            taxIdentificationNumber,
//                            withholdingTaxStatus
                            };
    }
    
    // Getters and setters
    public int getGovInfoID() {
        return govInfoID;
    }

    public void setGovInfoID(int govInfoID) {
        this.govInfoID = govInfoID;
    }

    public String getSssNumber() {
        return sssNumber;
    }

    public void setSssNumber(String sssNumber) {
        this.sssNumber = sssNumber;
    }

    public String getPhilhealthNumber() {
        return philhealthNumber;
    }

    public void setPhilhealthNumber(String philhealthNumber) {
        this.philhealthNumber = philhealthNumber;
    }

    public String getPagibigNumber() {
        return pagibigNumber;
    }

    public void setPagibigNumber(String pagibigNumber) {
        this.pagibigNumber = pagibigNumber;
    }

    public String getTaxIdentificationNumber() {
        return taxIdentificationNumber;
    }

    public void setTaxIdentificationNumber(String taxIdentificationNumber) {
        this.taxIdentificationNumber = taxIdentificationNumber;
    }

    /**
     * @return a formatted string containing the government identification details
     * of the employee
     */
    @Override
    public String toString() {
        return String.format("Government Info [Employee ID: %d, SSS: %s, PhilHealth: %s, Pag-IBIG: %s, TIN: %s]",
                employeeID, sssNumber, philhealthNumber, pagibigNumber, taxIdentificationNumber);
    }
}