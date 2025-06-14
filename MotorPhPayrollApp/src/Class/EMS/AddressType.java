/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

/**
 * Represents a type of address (e.g., Permanent, Current).
 *
 */
public class AddressType {

    private int addressTypeID;            
    private String addressTypeName;         
    private String addressTypeDescription; 

    public AddressType() {
    }

    // Constructor for insert
    public AddressType(String addressTypeName, String addressTypeDescription) {
        this.addressTypeName = addressTypeName;
        this.addressTypeDescription = addressTypeDescription;
    }

    // Constructor for retrieval
    public AddressType(int addressTypeID, String addressTypeName, String addressTypeDescription) {
        this.addressTypeID = addressTypeID;
        this.addressTypeName = addressTypeName;
        this.addressTypeDescription = addressTypeDescription;
    }

    // Getters and setters
    public int getAddressTypeID() {
        return addressTypeID;
    }

    public void setAddressTypeID(int addressTypeID) {
        this.addressTypeID = addressTypeID;
    }

    public String getAddressTypeName() {
        return addressTypeName;
    }

    public void setAddressTypeName(String addressTypeName) {
        this.addressTypeName = addressTypeName;
    }

    public String getAddressTypeDescription() {
        return addressTypeDescription;
    }

    public void setAddressTypeDescription(String addressTypeDescription) {
        this.addressTypeDescription = addressTypeDescription;
    }

    @Override
    public String toString() {
        return String.format("AddressType [ID: %d, Name: %s, Description: %s]",
                addressTypeID, addressTypeName,
                (addressTypeDescription != null ? addressTypeDescription : "N/A"));
    }
}