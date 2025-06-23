/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.PPS;

import java.math.BigDecimal;

/**
 * Represents a deduction.
 *
 * <p>This class encapsulates details related to deductions, such as the deduction type and amount.
 * Extend this class with additional fields and methods as needed for managing deduction details.</p>
 */
public class Deduction {
    public enum Type {SSS, PHILHEALTH, PAGIBIG}
    
    private int id;
    private int payslipId; 
    private Type type;
    private BigDecimal amount;

    /**
     * Default constructor for Deduction.
     *
     * <p>Creates an instance of Deduction with default values.</p>
     */
    public Deduction() {
    }
    
    /**
     * Constructs a Deduction with the specified deduction ID.
     *
     * @param deductionID the unique identifier for the deduction.
     */
    public Deduction(int payslipId, Type type, BigDecimal amount) {
        this.payslipId = payslipId;
        this.type      = type;
        this.amount    = amount;
    }
    
    // ——— Getters & Setters ———

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPayslipId() { return payslipId; }
    public void setPayslipId(int payslipId) { this.payslipId = payslipId; }

    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}