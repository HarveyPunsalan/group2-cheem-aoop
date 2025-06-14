/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

/**
 * Represents government-issued identification numbers for an employee.
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
    // Constructor for insert
    public GovernmentInformation(int employeeID, String sssNumber, String philhealthNumber,
                                 String pagibigNumber, String taxIdentificationNumber) {
        super(employeeID);
        this.sssNumber = sssNumber;
        this.philhealthNumber = philhealthNumber;
        this.pagibigNumber = pagibigNumber;
        this.taxIdentificationNumber = taxIdentificationNumber;
    }

    // Constructor for retrieval
    public GovernmentInformation(int govInfoID, int employeeID, String sssNumber, String philhealthNumber,
                                 String pagibigNumber, String taxIdentificationNumber) {
        super(employeeID);
        this.govInfoID = govInfoID;
        this.sssNumber = sssNumber;
        this.philhealthNumber = philhealthNumber;
        this.pagibigNumber = pagibigNumber;
        this.taxIdentificationNumber = taxIdentificationNumber;
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

    @Override
    public String toString() {
        return String.format("Government Info [Employee ID: %d, SSS: %s, PhilHealth: %s, Pag-IBIG: %s, TIN: %s]",
                employeeID, sssNumber, philhealthNumber, pagibigNumber, taxIdentificationNumber);
    }
}