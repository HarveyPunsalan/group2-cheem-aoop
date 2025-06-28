/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.employeemanagement.model;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents personal information of an employee.
 * 
 */
public class PersonalInformation extends Information {
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String phoneNumber;
    private String email;

    //Default constructor
    public PersonalInformation() {
        super(0); 
    }

    /**
     * Constructor used when creating new personal information
     * 
     * @param firstName    employee's first name
     * @param lastName     employee's last name
     * @param birthday     employee's birth date
     * @param phoneNumber  contact phone number
     * @param email        unique email address
     */
    public PersonalInformation(String firstName, String lastName, LocalDate birthday,
                               String phoneNumber, String email) {
        super(0); 
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    /**
     * Constructor used when retrieving personal information from the database.
     * 
     * @param employeeID   unique identifier for the employee
     * @param firstName    employee's first name
     * @param lastName     employee's last name
     * @param birthday     employee's birth date
     * @param phoneNumber  contact phone number
     * @param email        unique email address
     */
    public PersonalInformation(int employeeID, String firstName, String lastName,
                               LocalDate birthday, String phoneNumber, String email) {
        super(employeeID);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    
    public List<Object> toInsertParams() {
        return List.of(
            firstName,
            lastName, 
            java.sql.Date.valueOf(birthday),
            phoneNumber,
            email            
        );
    }

    public List<Object> toUpdateParams() {
        return List.of(
            firstName,
            lastName, 
            java.sql.Date.valueOf(birthday),
            phoneNumber,
            email,            
            employeeID
        );
    }
    
    /**
     * Retrieves the personal information as an array of strings.
     *
     * @return an array containing the employee ID, first name, last name, formatted birthday,
     * address, and phone number.
     */
    @Override
    public String[] getInformation() {
        // Return an array with the personal details.
        return new String[] {String.valueOf(employeeID),                            
                            lastName,
                            firstName,
                            birthday.toString(),
                            phoneNumber
                            };
    }

    // Getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return a string representation of the personal info.
     */
    @Override
    public String toString() {
        return String.format("Employee #%d: %s %s", employeeID, firstName, lastName);
    }
}