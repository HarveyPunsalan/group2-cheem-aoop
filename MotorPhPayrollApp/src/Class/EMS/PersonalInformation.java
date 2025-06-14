/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

import java.time.LocalDate;

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

    public PersonalInformation() {
        super(0); 
    }

    // Constructor for insert
    public PersonalInformation(String firstName, String lastName, LocalDate birthday,
                               String phoneNumber, String email) {
        super(0); 
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Constructor for retrieval
    public PersonalInformation(int employeeID, String firstName, String lastName,
                               LocalDate birthday, String phoneNumber, String email) {
        super(employeeID);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.email = email;
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

    @Override
    public String toString() {
        return String.format("Employee #%d: %s %s", employeeID, firstName, lastName);
    }
}