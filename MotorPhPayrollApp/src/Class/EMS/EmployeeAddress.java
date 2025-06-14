/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

/**
 * Represents the address information of an employee.
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

    public EmployeeAddress() {
        super(0);
    }

    // Constructor without addressID (for insertion)
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
    // Constructor (for fetching from DB)
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