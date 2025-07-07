/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.viewmodel.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author 63909
 */
public class BiWeeklyPayrollViewModel {
    
    private final int payPeriodId;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final LocalDate payDay;
    private final long numberOfEmployees;
    private final BigDecimal totalPayment;
    private final String status;
    private final LocalDate submittedDate;
    
    private BiWeeklyPayrollViewModel(Builder builder) {
        this.payPeriodId = builder.payPeriodId;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.payDay = builder.payDay;
        this.numberOfEmployees = builder.numberOfEmployees;
        this.totalPayment = builder.totalPayment;
        this.status = builder.status;
        this.submittedDate = builder.submittedDate;
    }
    
    public static final class Builder {
        private int payPeriodId;
        private LocalDate startDate;
        private LocalDate endDate;
        private LocalDate payDay;
        private long numberOfEmployees;
        private BigDecimal totalPayment;
        private String status;
        private LocalDate submittedDate;
        
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
        
        public Builder payDay(LocalDate payDay) {
            this.payDay = payDay;
            return this;
        }        
        
        public Builder numberOfEmployees(long numberOfEmployees) {
            this.numberOfEmployees = numberOfEmployees;
            return this;
        }
        
        public Builder totalPayment(BigDecimal totalPayment) {
            this.totalPayment = totalPayment;
            return this;
        }
        
        public Builder status(String status) {
            this.status = status;
            return this;
        }
        
        public Builder submittedDate(LocalDate submittedDate) {
            this.submittedDate = submittedDate;
            return this;
        }
        
        public BiWeeklyPayrollViewModel build() {
            return new BiWeeklyPayrollViewModel(this);
        }
    }

    public int getPayPeriodId() {
        return payPeriodId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getPayDay() {
        return payDay;
    }

    public long getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public BigDecimal getTotalPayment() {
        return totalPayment;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getSubmittedDate() {
        return submittedDate;
    }
    
    @Override
    public String toString() {
        return "BiWeeklyPayrollViewModel{" +
                "payPeriodId=" + payPeriodId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", payDay=" + payDay +
                ", numberOfEmployees=" + numberOfEmployees +
                ", totalPayment=" + totalPayment +
                ", status='" + status + '\'' +
                ", submittedDate=" + submittedDate +
                '}';
    }
}
