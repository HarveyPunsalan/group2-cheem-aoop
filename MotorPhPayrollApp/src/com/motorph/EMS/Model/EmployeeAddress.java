/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.EMS.Model;

/**
 * Represents the address information of an employee.
 *
 * <p>This class extends {Information} to inherit the {employeeID},
 * linking an address record to a specific employee. It supports both insertion
 * (without address ID) and retrieval (with address ID).</p>
 *
 */
public class EmployeeAddress extends Information {
    private int addressID; 
    private String houseNumber;
    private String street;
    private String barangay;
    private String municipality;
    private String province;
    private String postalCode;
    private String country;
    private AddressType addressType;

    //Default constructor
    public EmployeeAddress() {
        super(0);
    }

    /**
     * Constructor for creating a new address record (without address ID).
     *
     * @param employeeID     the ID of the employee
     * @param houseNumber    house or unit number
     * @param street         street name
     * @param barangay       barangay or village
     * @param municipality   city or municipality
     * @param province       province or region
     * @param postalCode     ZIP or postal code
     * @param country        country name
     * @param addressType    type of address 
     */
    public EmployeeAddress(int employeeID, String houseNumber, String street, String barangay,
                           String municipality, String province, String postalCode,
                           String country, AddressType addressType) {
        super(employeeID);
        this.houseNumber = houseNumber;
        this.street = street;
        this.barangay = barangay;
        this.municipality = municipality;
        this.province = province;
        this.postalCode = postalCode;
        this.country = country;
        this.addressType = addressType;
    }
    
    /**
     * Constructor for retrieving an address from the database (with address ID).
     *
     * @param addressID      the unique ID of the address
     * @param employeeID     the employee's ID
     * @param houseNumber    house or unit number
     * @param street         street name
     * @param barangay       barangay or village
     * @param municipality   city or municipality
     * @param province       province or region
     * @param postalCode     ZIP or postal code
     * @param country        country name
     * @param addressType    type of address
     */
    public EmployeeAddress(int addressID, int employeeID, String houseNumber, String street, String barangay,
                           String municipality, String province, String postalCode,
                           String country, AddressType addressType) {
        super(employeeID);
        this.addressID = addressID;
        this.houseNumber = houseNumber;
        this.street = street;
        this.barangay = barangay;
        this.municipality = municipality;
        this.province = province;
        this.postalCode = postalCode;
        this.country = country;
        this.addressType = addressType;
    }

    // Getters and setters
    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBarangay() {
        return barangay;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    /**
     * @return a string representation of the address 
     */    
    @Override
        public String toString() {
            return String.format("Address [ID: %d, Employee ID: %d, %s %s, %s, %s, %s, %s, %s, Type: %s]",
                    addressID, employeeID,
                    houseNumber != null ? houseNumber : "N/A",
                    street != null ? street : "N/A",
                    barangay != null ? barangay : "N/A",
                    municipality != null ? municipality : "N/A",
                    province != null ? province : "N/A",
                    postalCode != null ? postalCode : "N/A",
                    country != null ? country : "N/A",
                    (addressType != null ? addressType.getAddressTypeName() : "N/A"));
        }
}