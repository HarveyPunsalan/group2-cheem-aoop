/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EMS;

import com.motorph.common.Parser;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * Represents employment-related information for an employee.
 *
 * <p>This class extends Information and can be used to store details such as job title,
 * department, salary, and employment status. Extend this class with employment-specific properties and methods as needed.</p>
 */
public class EmploymentInformation extends Information {
    // Formatter for dates in "M/d/yyyy" format.
    private static final DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("M/d/yyyy");
    
    // Employment details
    private Job jobTitle; 
    private String employmentType; 
    private String employmentStatus;
    private LocalDate dateHired; 
    private String immediateSupervisor;
    
    // Salary and allowances
    private Salary salary;
    private String basicSalary, grossSemiMonthlyRate, hourlyRate;
    private String riceSubsidy, phoneAllowance, clothingAllowance;
    
    /**
     * Constructs an EmploymentInformation object using the provided employment data.
     *
     * <p>The constructor initializes the employment information for an employee based on an array of strings.
     * The first element (employeeID) is passed to the superclass constructor. The array must have at least 12
     * elements corresponding to job title, employment type, status, date hired, supervisor, salary, allowances,
     * and rates.</p>
     *
     * @param employeeID the employee's unique identifier.
     * @param employmentData an array of strings representing employment details.
     * @throws IllegalArgumentException if the employmentData array contains fewer than 12 elements.
     */
    public EmploymentInformation(String employeeID, String[] employmentData) {
        super(employeeID); // Initialize superclass with employeeID
        
        // Ensure the provided employment data array has the expected number of elements.
        if (employmentData.length < 12) {
            throw new IllegalArgumentException("Invalid data: Employee information must have 12 elements.");
        }
        
        // Initialize employment-related fields using the data array.
        this.jobTitle = new Job(employmentData[1]);
        this.employmentType = employmentData[2];
        this.employmentStatus = employmentData[3];
//        this.dateHired = Parser.parseDate(employmentData[4], null);
        this.immediateSupervisor = employmentData[5];
        this.basicSalary = employmentData[6];
        this.riceSubsidy = employmentData[7];
        this.phoneAllowance = employmentData[8];
        this.clothingAllowance = employmentData[9];
        this.grossSemiMonthlyRate = employmentData[10];
        this.hourlyRate = employmentData[11];
    }
    
        public EmploymentInformation(String employeeID, String jobTitle, String employmentType, String employmentStatus, LocalDate dateHired, 
                                        String immediateSupervisor, String basicSalary, String grossSemiMonthlyRate, String hourlyRate,
                                        String riceSubsidy, String phoneAllowance, String clothingAllowance) {
        super(employeeID); // Initialize superclass with employeeID
        
        // Initialize employment-related fields using the data array.
        this.jobTitle = new Job(jobTitle);
        this.employmentType = employmentType;
        this.employmentStatus = employmentStatus;
        this.dateHired = dateHired;
        this.immediateSupervisor = immediateSupervisor;
        
        this.basicSalary = basicSalary;
        this.grossSemiMonthlyRate = grossSemiMonthlyRate;
        this.hourlyRate = hourlyRate;
        
        this.riceSubsidy = riceSubsidy;
        this.phoneAllowance = phoneAllowance;
        this.clothingAllowance = clothingAllowance;
        
    }
        
    public List<Object> toInsertParams() {
        return List.of(
            employeeID,
            jobTitle.getJobID(),
            salary.getSalaryID(),
            employmentType,
            employmentStatus,
            java.sql.Date.valueOf(dateHired)        
        );
    }

    public List<Object> toUpdateParams() {
        return List.of(
            employeeID,
            jobTitle.getJobID(),
            salary.getSalaryID(),
            employmentType,
            employmentStatus,
            java.sql.Date.valueOf(dateHired),    
            employeeID
        );
    }    
    
    /**
     * Retrieves key employment information as an array of strings.
     *
     * @return an array containing the employee ID, job title, employment type, employment status,
     * and date hired.
     */
    @Override
    public String[] getInformation() {
        // Return an array with selected employment details.
        return new String[] {employeeID,
                            jobTitle.getJobName(),
                            employmentType,
                            employmentStatus,
                            dateHired.toString(),
                            immediateSupervisor,
                            basicSalary,
                            riceSubsidy,
                            phoneAllowance,
                            clothingAllowance,
                            grossSemiMonthlyRate,
                            hourlyRate,
                            };
    }
    
    // Getters
    public static DateTimeFormatter getFormatterDate() {
        return formatterDate;
    }

    public Job getJobTitle() {
        return jobTitle;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public LocalDate getDateHired() {
        return dateHired;
    }

    public String getImmediateSupervisor() {
        return immediateSupervisor;
    }

    public String getBasicSalary() {
        return basicSalary;
    }

    public String getRiceSubsidy() {
        return riceSubsidy;
    }

    public String getPhoneAllowance() {
        return phoneAllowance;
    }

    public String getClothingAllowance() {
        return clothingAllowance;
    }

    public String getGrossSemiMonthlyRate() {
        return grossSemiMonthlyRate;
    }

    public String getHourlyRate() {
        return hourlyRate;
    }
    
}
