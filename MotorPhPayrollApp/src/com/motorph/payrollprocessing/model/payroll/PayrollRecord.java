/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.model.payroll;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public final class PayrollRecord {
    private  int payrollId;
    private  int payPeriodId;
    private  int employeeId;
    private  int attendanceProcessingId;

    private  BigDecimal basicSalary;
    private  BigDecimal overtimePay;
    private  BigDecimal totalAllowance;
    private  BigDecimal grossSalary;
    private  BigDecimal totalDeduction;
    private  BigDecimal netSalary;

    private  LocalDateTime createdDate;
    private  LocalDateTime updatedDate;
    private  LocalDate submittedDate;

    public PayrollRecord() {
    }
    
    
    
    private PayrollRecord(Builder builder) {
        this.payrollId = builder.payrollId;
        this.payPeriodId = builder.payPeriodId;
        this.employeeId = builder.employeeId;
        this.attendanceProcessingId = builder.attendanceProcessingId;
        this.basicSalary = builder.basicSalary;
        this.overtimePay = builder.overtimePay;
        this.totalAllowance = builder.totalAllowance;
        this.grossSalary = builder.grossSalary;
        this.totalDeduction = builder.totalDeduction;
        this.netSalary = builder.netSalary;
        this.createdDate = builder.createdDate;
        this.updatedDate = builder.updatedDate;
        this.submittedDate = builder.submittedDate;
    }

    public static final class Builder {
        private int payrollId;
        private int payPeriodId;
        private int employeeId;
        private int attendanceProcessingId;

        private BigDecimal basicSalary;
        private BigDecimal overtimePay;
        private BigDecimal totalAllowance;
        private BigDecimal grossSalary;
        private BigDecimal totalDeduction;
        private BigDecimal netSalary;

        private LocalDateTime createdDate;
        private LocalDateTime updatedDate;
        private LocalDate submittedDate;

        public Builder payrollId(int payrollId) {
            this.payrollId = payrollId;
            return this;
        }

        public Builder payPeriodId(int payPeriodId) {
            this.payPeriodId = payPeriodId;
            return this;
        }

        public Builder employeeId(int employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public Builder attendanceProcessingId(int attendanceProcessingId) {
            this.attendanceProcessingId = attendanceProcessingId;
            return this;
        }

        public Builder basicSalary(BigDecimal basicSalary) {
            this.basicSalary = basicSalary;
            return this;
        }

        public Builder overtimePay(BigDecimal overtimePay) {
            this.overtimePay = overtimePay;
            return this;
        }

        public Builder totalAllowance(BigDecimal totalAllowance) {
            this.totalAllowance = totalAllowance;
            return this;
        }

        public Builder grossSalary(BigDecimal grossSalary) {
            this.grossSalary = grossSalary;
            return this;
        }

        public Builder totalDeduction(BigDecimal totalDeduction) {
            this.totalDeduction = totalDeduction;
            return this;
        }

        public Builder netSalary(BigDecimal netSalary) {
            this.netSalary = netSalary;
            return this;
        }

        public Builder createdDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder updatedDate(LocalDateTime updatedDate) {
            this.updatedDate = updatedDate;
            return this;
        }

        public Builder submittedDate(LocalDate submittedDate) {
            this.submittedDate = submittedDate;
            return this;
        }

        public PayrollRecord build() {
            return new PayrollRecord(this);
        }
    }
    
    
    public int getPayrollId() {
        return payrollId;
    }

    public int getPayPeriodId() {
        return payPeriodId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getAttendanceProcessingId() {
        return attendanceProcessingId;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public BigDecimal getOvertimePay() {
        return overtimePay;
    }

    public BigDecimal getTotalAllowance() {
        return totalAllowance;
    }

    public BigDecimal getGrossSalary() {
        return grossSalary;
    }

    public BigDecimal getTotalDeduction() {
        return totalDeduction;
    }

    public BigDecimal getNetSalary() {
        return netSalary;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public LocalDate getSubmittedDate() {
        return submittedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PayrollRecord)) return false;
        PayrollRecord that = (PayrollRecord) o;
        return payrollId == that.payrollId &&
               payPeriodId == that.payPeriodId &&
               employeeId == that.employeeId &&
               attendanceProcessingId == that.attendanceProcessingId &&
               Objects.equals(basicSalary, that.basicSalary) &&
               Objects.equals(overtimePay, that.overtimePay) &&
               Objects.equals(totalAllowance, that.totalAllowance) &&
               Objects.equals(grossSalary, that.grossSalary) &&
               Objects.equals(totalDeduction, that.totalDeduction) &&
               Objects.equals(netSalary, that.netSalary) &&
               Objects.equals(createdDate, that.createdDate) &&
               Objects.equals(updatedDate, that.updatedDate) &&
               Objects.equals(submittedDate, that.submittedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payrollId, payPeriodId, employeeId, attendanceProcessingId,
                            basicSalary, overtimePay, totalAllowance, grossSalary,
                            totalDeduction, netSalary, createdDate, updatedDate, submittedDate);
    }

    @Override
    public String toString() {
        return "PayrollRecord{" +
               "payrollId=" + payrollId +
               ", payPeriodId=" + payPeriodId +
               ", employeeId=" + employeeId +
               ", attendanceProcessingId=" + attendanceProcessingId +
               ", basicSalary=" + basicSalary +
               ", overtimePay=" + overtimePay +
               ", totalAllowance=" + totalAllowance +
               ", grossSalary=" + grossSalary +
               ", totalDeduction=" + totalDeduction +
               ", netSalary=" + netSalary +
               ", createdDate=" + createdDate +
               ", updatedDate=" + updatedDate +
               ", submittedDate=" + submittedDate +
               '}';
    }
}
