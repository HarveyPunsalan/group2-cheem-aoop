/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.model.allowance;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author 63909
 */
public class EmployeeDefaultAllowance {
    
    private final int employeeAllowanceId;
    private final int allowanceId;
    private final int employeeId;
    
    // ✅ Mutable Fields
    private BigDecimal monthlyAmount;
    private BigDecimal biweeklyAmount;
    private LocalDate effectiveDate;
    private LocalDate createdDate;
    private String allowanceFrequency;

    // === Constructors ===

    private EmployeeDefaultAllowance(Builder builder) {
        this.employeeAllowanceId = builder.employeeAllowanceId;
        this.allowanceId = builder.allowanceId;
        this.employeeId = builder.employeeId;
        this.createdDate = builder.createdDate;

        this.monthlyAmount = builder.monthlyAmount;
        this.biweeklyAmount = builder.biweeklyAmount;
        this.effectiveDate = builder.effectiveDate;
        this.allowanceFrequency = builder.allowanceFrequency;
    }
    
    // === Builder Pattern ===
    public static class Builder {
        private int employeeAllowanceId;
        private int allowanceId;
        private int employeeId;
        private BigDecimal monthlyAmount;
        private BigDecimal biweeklyAmount;
        private LocalDate effectiveDate;
        private LocalDate createdDate;
        private String allowanceFrequency;

        public Builder employeeAllowanceId(int id) {
            this.employeeAllowanceId = id;
            return this;
        }

        public Builder allowanceId(int allowanceId) {
            this.allowanceId = allowanceId;
            return this;
        }

        public Builder employeeId(int employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public Builder monthlyAmount(BigDecimal monthlyAmount) {
            this.monthlyAmount = monthlyAmount;
            return this;
        }

        public Builder biweeklyAmount(BigDecimal biweeklyAmount) {
            this.biweeklyAmount = biweeklyAmount;
            return this;
        }

        public Builder effectiveDate(LocalDate effectiveDate) {
            this.effectiveDate = effectiveDate;
            return this;
        }

        public Builder createdDate(LocalDate createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder allowanceFrequency(String frequency) {
            this.allowanceFrequency = frequency;
            return this;
        }

        public EmployeeDefaultAllowance build() {
            return new EmployeeDefaultAllowance(this);
        }
    }

   // === Getters ===

    public int getEmployeeAllowanceId() {
        return employeeAllowanceId;
    }

    public int getAllowanceId() {
        return allowanceId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public BigDecimal getMonthlyAmount() {
        return monthlyAmount;
    }

    public BigDecimal getBiweeklyAmount() {
        return biweeklyAmount;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public String getAllowanceFrequency() {
        return allowanceFrequency;
    }

    // === Selective Setters (Mutable fields) ===

    public void setMonthlyAmount(BigDecimal monthlyAmount) {
        this.monthlyAmount = monthlyAmount;
    }

    public void setBiweeklyAmount(BigDecimal biweeklyAmount) {
        this.biweeklyAmount = biweeklyAmount;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public void setAllowanceFrequency(String allowanceFrequency) {
        this.allowanceFrequency = allowanceFrequency;
    }
   
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof EmployeeDefaultAllowance)) return false;
        EmployeeDefaultAllowance that = (EmployeeDefaultAllowance) object;
        return employeeAllowanceId == that.employeeAllowanceId &&
               allowanceId == that.allowanceId &&
               employeeId == that.employeeId &&
               Objects.equals(monthlyAmount, that.monthlyAmount) &&
               Objects.equals(biweeklyAmount, that.biweeklyAmount) &&
               Objects.equals(effectiveDate, that.effectiveDate) &&
               Objects.equals(createdDate, that.createdDate) &&
               Objects.equals(allowanceFrequency, that.allowanceFrequency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeAllowanceId, allowanceId, employeeId,
                            monthlyAmount, biweeklyAmount,
                            effectiveDate, createdDate, allowanceFrequency);
    }

    @Override
    public String toString() {
        return "EmployeeDefaultAllowance{" +
               "employeeAllowanceId=" + employeeAllowanceId +
               ", allowanceId=" + allowanceId +
               ", employeeId=" + employeeId +
               ", monthlyAmount=" + monthlyAmount +
               ", biweeklyAmount=" + biweeklyAmount +
               ", effectiveDate=" + effectiveDate +
               ", createdDate=" + createdDate +
               ", allowanceFrequency='" + allowanceFrequency + '\'' +
               '}';
    }
}
