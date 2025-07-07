/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.model.payroll;

import com.motorph.common.model.DateRange;
import com.motorph.common.util.validation.DateRangeValidator;
import com.motorph.common.util.validation.Validator;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author 63909
 */
public class PayPeriod {

    private final int payPeriodId;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final LocalDate payDate;
    private final LocalDate payrollDueDate;

    // === Constructors ===

    private PayPeriod(Builder builder) {
        this.payPeriodId = builder.payPeriodId;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.payDate = builder.payDate;
        this.payrollDueDate = builder.payrollDueDate;
    }

    // === Builder Pattern ===
    public static class Builder {
        private int payPeriodId;
        private LocalDate startDate;
        private LocalDate endDate;
        private LocalDate payDate;
        private LocalDate payrollDueDate;

        public Builder payPeriodId(int payPeriodId) {
            this.payPeriodId = payPeriodId;
            return this;
        }

        public Builder startDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder payDate(LocalDate payDate) {
            this.payDate = payDate;
            return this;
        }

        public Builder payrollDueDate(LocalDate payrollDueDate) {
            this.payrollDueDate = payrollDueDate;
            return this;
        }

        public PayPeriod build() {
            validateDateOrder(new DateRange(startDate, endDate));
            return new PayPeriod(this);
        }
    }

    // === Getters ===
    public int getPayPeriodId() {
        return payPeriodId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getPayDate() {
        return payDate;
    }

    public LocalDate getPayrollDueDate() {
        return payrollDueDate;
    }

    // === Helper ===
    private static void validateDateOrder(DateRange dateRange) {
        Validator<DateRange> validator = new DateRangeValidator();
        validator.validate(dateRange);
    }
    
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof PayPeriod)) return false;
        PayPeriod that = (PayPeriod) object;
        return payPeriodId == that.payPeriodId &&
               Objects.equals(startDate, that.startDate) &&
               Objects.equals(endDate, that.endDate) &&
               Objects.equals(payDate, that.payDate) &&
               Objects.equals(payrollDueDate, that.payrollDueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payPeriodId, startDate, endDate, payDate, payrollDueDate);
    }

    @Override
    public String toString() {
        return "PayPeriod{" +
               "payPeriodId=" + payPeriodId +
               ", startDate=" + startDate +
               ", endDate=" + endDate +
               ", payDate=" + payDate +
               ", payrollDueDate=" + payrollDueDate +
               '}';
    }
}
