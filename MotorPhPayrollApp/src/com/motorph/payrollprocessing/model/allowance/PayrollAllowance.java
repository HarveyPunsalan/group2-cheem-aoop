/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.model.allowance;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public final class PayrollAllowance {
    private final int payrollAllowanceId;
    private final int payrollId;
    private final int sourceAllowanceId;
    private final BigDecimal amount;
    private final LocalDateTime createdDate;

    private PayrollAllowance(Builder builder) {
        this.payrollAllowanceId = builder.payrollAllowanceId;
        this.payrollId = builder.payrollId;
        this.sourceAllowanceId = builder.sourceAllowanceId;
        this.amount = builder.amount;
        this.createdDate = builder.createdDate;
    } 

    public static class Builder {
        private int payrollAllowanceId;
        private int payrollId;
        private int sourceAllowanceId;
        private BigDecimal amount;
        private LocalDateTime createdDate;

        public Builder payrollAllowanceId(int id) {
            this.payrollAllowanceId = id;
            return this;
        }

        public Builder payrollId(int id) {
            this.payrollId = id;
            return this;
        }

        public Builder sourceAllowanceId(int id) {
            this.sourceAllowanceId = id;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder createdDate(LocalDateTime dateTime) {
            this.createdDate = dateTime;
            return this;
        }

        public PayrollAllowance build() {
            return new PayrollAllowance(this);
        }
    }
    
    public int getPayrollAllowanceId() {
        return payrollAllowanceId;
    }

    public int getPayrollId() {
        return payrollId;
    }

    public int getSourceAllowanceId() {
        return sourceAllowanceId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    // ✅ equals, hashCode, toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PayrollAllowance)) return false;
        PayrollAllowance that = (PayrollAllowance) o;
        return payrollAllowanceId == that.payrollAllowanceId &&
               payrollId == that.payrollId &&
               sourceAllowanceId == that.sourceAllowanceId &&
               Objects.equals(amount, that.amount) &&
               Objects.equals(createdDate, that.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payrollAllowanceId, payrollId, sourceAllowanceId, amount, createdDate);
    }

    @Override
    public String toString() {
        return "PayrollAllowance{" +
               "payrollAllowanceId=" + payrollAllowanceId +
               ", payrollId=" + payrollId +
               ", sourceAllowanceId=" + sourceAllowanceId +
               ", amount=" + amount +
               ", createdDate=" + createdDate +
               '}';
    }
}

