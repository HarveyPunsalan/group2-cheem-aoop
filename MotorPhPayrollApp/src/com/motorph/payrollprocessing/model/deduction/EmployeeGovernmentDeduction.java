/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.model.deduction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class EmployeeGovernmentDeduction {
    private final int employeeGovernmentDeductionId;
    private final int chartId;
    private final int payrollId;
    private final LocalDate createdDate;

    // ✅ Mutable fields
    private BigDecimal amount;
    private LocalDate effectiveDate;

    private EmployeeGovernmentDeduction(Builder builder) {
        this.employeeGovernmentDeductionId = builder.employeeGovernmentDeductionId;
        this.chartId = builder.chartId;
        this.payrollId = builder.payrollId;
        this.createdDate = builder.createdDate;

        this.amount = builder.amount;
        this.effectiveDate = builder.effectiveDate;
    }

    public static final class Builder {
        private int employeeGovernmentDeductionId;
        private int chartId;
        private int payrollId;
        private BigDecimal amount;
        private LocalDate effectiveDate;
        private LocalDate createdDate;

        public Builder employeeGovernmentDeductionId(int id) {
            this.employeeGovernmentDeductionId = id;
            return this;
        }

        public Builder chartId(int chartId) {
            this.chartId = chartId;
            return this;
        }

        public Builder payrollId(int payrollId) {
            this.payrollId = payrollId;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder effectiveDate(LocalDate date) {
            this.effectiveDate = date;
            return this;
        }

        public Builder createdDate(LocalDate date) {
            this.createdDate = date;
            return this;
        }

        public EmployeeGovernmentDeduction build() {
            return new EmployeeGovernmentDeduction(this);
        }
    }
    
    public int getEmployeeGovernmentDeductionId() {
        return employeeGovernmentDeductionId;
    }

    public int getChartId() {
        return chartId;
    }

    public int getPayrollId() {
        return payrollId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    // ✅ Setters for mutable fields
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof EmployeeGovernmentDeduction)) return false;
        EmployeeGovernmentDeduction that = (EmployeeGovernmentDeduction) object;
        return employeeGovernmentDeductionId == that.employeeGovernmentDeductionId &&
               chartId == that.chartId &&
               payrollId == that.payrollId &&
               Objects.equals(amount, that.amount) &&
               Objects.equals(effectiveDate, that.effectiveDate) &&
               Objects.equals(createdDate, that.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeGovernmentDeductionId, chartId, payrollId, amount, effectiveDate, createdDate);
    }

    @Override
    public String toString() {
        return "EmployeeGovernmentDeduction{" +
               "employeeGovernmentDeductionId=" + employeeGovernmentDeductionId +
               ", chartId=" + chartId +
               ", payrollId=" + payrollId +
               ", amount=" + amount +
               ", effectiveDate=" + effectiveDate +
               ", createdDate=" + createdDate +
               '}';
    }
}

