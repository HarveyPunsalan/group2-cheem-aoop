/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.PPS;

import java.math.BigDecimal;

/**
 * Represents an allowance.
 *
 * <p>This class encapsulates allowance-related details such as the type of allowance and its amount.
 * Extend this class with additional properties and methods as needed.</p>
 */
public class Allowance {
    
    private int id;
    private int payslipId;
    private BigDecimal rice;
    private BigDecimal phone;
    private BigDecimal clothing;
    private BigDecimal other;
    
    
    /**
     * Default constructor for Allowance.
     *
     * <p>Creates an instance of Allowance with default values.</p>
     */
    
    /**
     * Constructs an Allowance with the specified attributes.
     *
     * @param allowanceID the unique identifier for the allowance.
     * @param allowanceName the name of the allowance.
     * @param description a description of the allowance.
     */
    public Allowance(int payslipId, BigDecimal rice, BigDecimal phone, BigDecimal clothing, BigDecimal other) {
        this.payslipId = payslipId;
        this.rice            = rice;
        this.clothing   = clothing;
        this.phone        = phone;
        this.other          = other;
    }    

    // Getters and Setters
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getPayslipId(){
        return payslipId;
    }
    public void setPayslipId(int payslipId){
        this.payslipId = payslipId;
    }
    public BigDecimal getRice() {
        return rice;
    }
    public void setRice(BigDecimal rice) {
        this.rice = rice;
    }

    public BigDecimal getPhone() {
        return phone;
    }
    public void setPhone(BigDecimal phone) {
        this.phone = phone;
    }

    public BigDecimal getClothing() {
        return clothing;
    }
    public void setClothing(BigDecimal clothing) {
        this.clothing = clothing;
    }

    public BigDecimal getOther() {
        return other;
    }
    public void setOther(BigDecimal other) {
        this.other = other;
    }
}

