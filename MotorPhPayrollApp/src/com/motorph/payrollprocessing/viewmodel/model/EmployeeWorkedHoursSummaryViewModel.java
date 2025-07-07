/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.viewmodel.model;

import java.math.BigDecimal;

public class EmployeeWorkedHoursSummaryViewModel {

    private final int employeeId;
    private final int payPeriodId;
    private final String employeeName;
    private final String employeeType;
    private final BigDecimal totalRegularHours;
    private final BigDecimal totalOvertime;
    private final BigDecimal payableHours;

    // Private constructor
    private EmployeeWorkedHoursSummaryViewModel(Builder builder) {
        this.employeeId = builder.employeeId;
        this.payPeriodId = builder.payPeriodId;
        this.employeeName = builder.employeeName;
        this.employeeType = builder.employeeType;
        this.totalRegularHours = builder.totalRegularHours;
        this.totalOvertime = builder.totalOvertime;
        this.payableHours = builder.payableHours;
    }

    // === Builder Class ===
    public static class Builder {
        private int employeeId;
        private int payPeriodId;
        private String employeeName;
        private String employeeType;
        private BigDecimal totalRegularHours;
        private BigDecimal totalOvertime;
        private BigDecimal payableHours;

        public Builder employeeId(int employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public Builder payPeriodId(int payPeriodId) {
            this.payPeriodId = payPeriodId;
            return this;
        }

        public Builder employeeName(String employeeName) {
            this.employeeName = employeeName;
            return this;
        }

        public Builder employeeType(String employeeType) {
            this.employeeType = employeeType;
            return this;
        }

        public Builder totalRegularHours(BigDecimal totalRegularHours) {
            this.totalRegularHours = totalRegularHours;
            return this;
        }

        public Builder totalOvertime(BigDecimal totalOvertime) {
            this.totalOvertime = totalOvertime;
            return this;
        }

        public Builder payableHours(BigDecimal payableHours) {
            this.payableHours = payableHours;
            return this;
        }

        public EmployeeWorkedHoursSummaryViewModel build() {
            return new EmployeeWorkedHoursSummaryViewModel(this);
        }
    }
    
        // Getters
    public int getEmployeeId() {
        return employeeId;
    }

    public int getPayPeriodId() {
        return payPeriodId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public BigDecimal getTotalRegularHours() {
        return totalRegularHours;
    }

    public BigDecimal getTotalOvertime() {
        return totalOvertime;
    }

    public BigDecimal getPayableHours() {
        return payableHours;
    }
    
    @Override
    public String toString() {
        return "EmployeeWorkHoursSummaryViewModel{" +
                "employeeId=" + employeeId +
                ", payPeriodId=" + payPeriodId +
                ", employeeName='" + employeeName + '\'' +
                ", employeeType='" + employeeType + '\'' +
                ", totalRegularHours=" + totalRegularHours +
                ", totalOvertime=" + totalOvertime +
                ", payableHours=" + payableHours +
                '}';
    }
}
