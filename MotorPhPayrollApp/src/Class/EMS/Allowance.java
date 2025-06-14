/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

/**
 * Represents a type of allowance (e.g., Phone, Rice, Clothing).
 */
public class Allowance {

    private int allowanceID;         
    private String allowanceName;   
    private String description;     

    public Allowance() {
    }

    // Constructor for insert
    public Allowance(String allowanceName, String description) {
        this.allowanceName = allowanceName;
        this.description = description;
    }

    // Constructor for retrieval
    public Allowance(int allowanceID, String allowanceName, String description) {
        this.allowanceID = allowanceID;
        this.allowanceName = allowanceName;
        this.description = description;
    }

    // Getters and setters
    public int getAllowanceID() {
        return allowanceID;
    }

    public void setAllowanceID(int allowanceID) {
        this.allowanceID = allowanceID;
    }

    public String getAllowanceName() {
        return allowanceName;
    }

    public void setAllowanceName(String allowanceName) {
        this.allowanceName = allowanceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("Allowance [ID: %d, Name: %s, Description: %s]",
                allowanceID,
                allowanceName,
                (description != null ? description : "N/A"));
    }
}