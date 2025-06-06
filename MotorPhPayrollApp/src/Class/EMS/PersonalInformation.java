/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

import com.motorph.employeemanagement.Information;
import java.time.LocalDate;
import java.util.List;

/**
 * Represents personal information for an employee.
 *
 * <p>This class extends the Information class and serves as a placeholder for storing
 * personal details such as name, birthday, address, and phone number. Extend this class
 * with additional fields and methods as needed.</p>
 */
public class PersonalInformation extends Information {
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String address;
    private String phoneNumber;
    private String email;

    /**
     * Constructs a PersonalInformation object using the provided personal data.
     *
     * <p>This constructor initializes personal information for an employee based on an array of strings.
     * The array is expected to contain at least 6 elements:
     * <ul>
     *   <li>Index 1: Last Name</li>
     *   <li>Index 2: First Name</li>
     *   <li>Index 3: Birthday (to be parsed)</li>
     *   <li>Index 4: Address</li>
     *   <li>Index 5: Phone Number</li>
     * </ul>
     * The employee ID is passed to the superclass. If the array has fewer than 6 elements, an exception is thrown.
     * </p>
     *
     * @param employeeID the employee's unique identifier.
     * @param personalData an array of strings representing personal information.
     * @throws IllegalArgumentException if the personalData array has fewer than 6 elements.
     */
    public PersonalInformation(String employeeID, String[] personalData) {
        super(employeeID); // Initialize the superclass with the employee ID.
        
        // Validate that the personalData array contains the expected number of elements.
        if (personalData.length < 6) {
            throw new IllegalArgumentException("Invalid data: Employee information must have 6 elements.");
        }
        
        // Assign first name and last name from the personalData array.
        this.lastName = personalData[1]; 
        this.firstName = personalData[2];               
        this.address = personalData[4];
        this.phoneNumber = personalData[5];
//        this.birthday = Parser.parseDate(personalData[3], null);
 
    }
    
    public PersonalInformation(String employeeID, String firstName, String lastName, LocalDate birthday, String phoneNumber, String email) {
        super(employeeID); // Initialize the superclass with the employee ID.
        
        // Assign first name and last name from the personalData array.
         
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
        return new String[] {employeeID,                            
                            lastName,
                            firstName,
                            birthday.toString(),
                            address,
                            phoneNumber
                            };
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
    
}
