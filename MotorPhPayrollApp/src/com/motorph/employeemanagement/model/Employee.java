/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.employeemanagement.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Represents an Employee entity with comprehensive details.
 *
 * <p>This class serves as a data model encapsulating personal, employment, 
 * government, address, job, supervisor, salary, and allowance information 
 * related to an employee in the payroll system.</p>
 *
 * <p>The employee ID is auto-generated by the database and serves as the primary key.
 * The class provides getter and setter methods for all attributes, facilitating
 * easy data manipulation and retrieval.</p>
 */
public class Employee {

    // Personal Information
    private int employeeId;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String phoneNumber;
    private String email;

    // Address
    private String houseNumber;
    private String street;
    private String barangay;
    private String municipality;
    private String province;
    private String postalCode;
    private String country;
    private String addressType;

    // Government IDs
    private String sssNumber;
    private String philhealthNumber;
    private String pagibigNumber;
    private String taxIdentificationNumber;

    // Employment Information
    private String employmentType;
    private String employmentStatus;
    private String jobTitle;
    private int supervisorId;
    private String department;
    private Date dateHired;

    // Salary details
    private int salaryGrade;
    private BigDecimal basicSalary;
    private BigDecimal grossSemiMonthlyRate;
    private BigDecimal hourlyRate;

    // Allowances
    private BigDecimal riceSubsidy;
    private BigDecimal phoneAllowance;
    private BigDecimal clothingAllowance;

    /**
     * Retrieves all employee information as a String array.
     *
     * @return A String array containing employee details.
     */
    public String[] getEmployeeInformation() {
        return new String[] {String.valueOf(employeeId),
                            lastName,
                            firstName,
                            birthday.toString(),
//                            address,
                            phoneNumber,
                            sssNumber,
                            philhealthNumber,
                            taxIdentificationNumber,
                            pagibigNumber,
                            employmentStatus,
                            jobTitle,
//                            immediateSupervisor,
                            basicSalary.toString(),
                            riceSubsidy.toString(),
                            phoneAllowance.toString(),
                            clothingAllowance.toString(),
                            grossSemiMonthlyRate.toString(),
                            hourlyRate.toString()
                            };
    }
    
    // Getters and Setters
    public int getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

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

    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
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

    public String getAddressType() {
        return addressType;
    }
    public void setAddressType(String addressType) {
        this.addressType = addressType;
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

    public String getEmploymentType() {
        return employmentType;
    }
    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }
    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getSupervisorId() {
        return supervisorId;
    }
    public void setSupervisorId(int supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getDateHired() {
        return dateHired;
    }
    public void setDateHired(Date dateHired) {
        this.dateHired = dateHired;
    }

    public int getSalaryGrade() {
        return salaryGrade;
    }
    public void setSalaryGrade(int salaryGrade) {
        this.salaryGrade = salaryGrade;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }
    public void setBasicSalary(BigDecimal basicSalary) {
        this.basicSalary = basicSalary;
    }

    public BigDecimal getGrossSemiMonthlyRate() {
        return grossSemiMonthlyRate;
    }
    public void setGrossSemiMonthlyRate(BigDecimal grossSemiMonthlyRate) {
        this.grossSemiMonthlyRate = grossSemiMonthlyRate;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }
    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public BigDecimal getRiceSubsidy() {
        return riceSubsidy;
    }
    public void setRiceSubsidy(BigDecimal riceSubsidy) {
        this.riceSubsidy = riceSubsidy;
    }

    public BigDecimal getPhoneAllowance() {
        return phoneAllowance;
    }
    public void setPhoneAllowance(BigDecimal phoneAllowance) {
        this.phoneAllowance = phoneAllowance;
    }

    public BigDecimal getClothingAllowance() {
        return clothingAllowance;
    }
    public void setClothingAllowance(BigDecimal clothingAllowance) {
        this.clothingAllowance = clothingAllowance;
    }
}