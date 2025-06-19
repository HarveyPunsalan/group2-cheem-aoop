/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

/**
 * Represents a type of address (e.g., Permanent, Current, Temporary).
 * 
 * This class is used to define different categories of addresses.
 * It contains an ID, a name, and a description.
 *
 */
public class AddressType {

    private int addressTypeID; // Unique identifier for the address type            
    private String addressTypeName; // Name of the address type        
    private String addressTypeDescription; // Description of the address type

    //Default constructor
    public AddressType() {
    }

    /**
     * Constructor used when creating a new address type to insert into a database.
     * 
     * @param addressTypeName        the name of the address type
     * @param addressTypeDescription the description of the address type
     */
    public AddressType(String addressTypeName, String addressTypeDescription) {
        this.addressTypeName = addressTypeName;
        this.addressTypeDescription = addressTypeDescription;
    }

    /**
     * Constructor used when retrieving an address type from a database.
     * 
     * @param addressTypeID          the ID of the address type
     * @param addressTypeName        the name of the address type
     * @param addressTypeDescription the description of the address type
     */
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

    /**
     * Returns a formatted string representation of the address type.
     * 
     * @return formatted string with ID, name, and description
     */
    @Override
    public String toString() {
        return String.format("AddressType [ID: %d, Name: %s, Description: %s]",
                addressTypeID, addressTypeName,
                (addressTypeDescription != null ? addressTypeDescription : "N/A"));
    }
}