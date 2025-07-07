/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.model.deduction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public final class DeductionChart {
    private final int deductionChartId;
    private final int deductionTypeId;
    private final BigDecimal minimumRange;
    private final BigDecimal maximumRange;
    private final BigDecimal fixedAmount;
    private final BigDecimal employeeDeductionRate;
    private final BigDecimal employerContributionShare;
    private final boolean hasContributionCap;
    private final BigDecimal minContribution;
    private final BigDecimal maxContribution;
    private final LocalDate effectiveAt;
    private final LocalDate updatedAt;
    private final String notes;

    private DeductionChart(Builder builder) {
        this.deductionChartId = builder.deductionChartId;
        this.deductionTypeId = builder.deductionTypeId;
        this.minimumRange = builder.minimumRange;
        this.maximumRange = builder.maximumRange;
        this.fixedAmount = builder.fixedAmount;
        this.employeeDeductionRate = builder.employeeDeductionRate;
        this.employerContributionShare = builder.employerContributionShare;
        this.hasContributionCap = builder.hasContributionCap;
        this.minContribution = builder.minContribution;
        this.maxContribution = builder.maxContribution;
        this.effectiveAt = builder.effectiveAt;
        this.updatedAt = builder.updatedAt;
        this.notes = builder.notes;
    }

    public static final class Builder {
        private int deductionChartId;
        private int deductionTypeId;
        private BigDecimal minimumRange;
        private BigDecimal maximumRange;
        private BigDecimal fixedAmount;
        private BigDecimal employeeDeductionRate;
        private BigDecimal employerContributionShare;
        private boolean hasContributionCap;
        private BigDecimal minContribution;
        private BigDecimal maxContribution;
        private LocalDate effectiveAt;
        private LocalDate updatedAt;
        private String notes;

        public Builder deductionChartId(int deductionChartId) {
            this.deductionChartId = deductionChartId;
            return this;
        }

        public Builder deductionTypeId(int deductionTypeId) {
            this.deductionTypeId = deductionTypeId;
            return this;
        }

        public Builder minimumRange(BigDecimal minimumRange) {
            this.minimumRange = minimumRange;
            return this;
        }

        public Builder maximumRange(BigDecimal maximumRange) {
            this.maximumRange = maximumRange;
            return this;
        }

        public Builder fixedAmount(BigDecimal amount) {
            this.fixedAmount = amount;
            return this;
        }

        public Builder employeeDeductionRate(BigDecimal rate) {
            this.employeeDeductionRate = rate;
            return this;
        }

        public Builder employerContributionShare(BigDecimal share) {
            this.employerContributionShare = share;
            return this;
        }

        public Builder hasContributionCap(boolean hasCap) {
            this.hasContributionCap = hasCap;
            return this;
        }

        public Builder minContribution(BigDecimal minContribution) {
            this.minContribution = minContribution;
            return this;
        }

        public Builder maxContribution(BigDecimal maxContribution) {
            this.maxContribution = maxContribution;
            return this;
        }

        public Builder effectiveAt(LocalDate effectiveAt) {
            this.effectiveAt = effectiveAt;
            return this;
        }

        public Builder updatedAt(LocalDate updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public DeductionChart build() {
            return new DeductionChart(this);
        }
    }
    
    public int getDeductionChartId() {
        return deductionChartId;
    }

    public int getDeductionTypeId() {
        return deductionTypeId;
    }

    public BigDecimal getMinimumRange() {
        return minimumRange;
    }

    public BigDecimal getMaximumRange() {
        return maximumRange;
    }

    public BigDecimal getFixedAmount() {
        return fixedAmount;
    }

    public BigDecimal getEmployeeDeductionRate() {
        return employeeDeductionRate;
    }

    public BigDecimal getEmployerContributionShare() {
        return employerContributionShare;
    }

    public boolean hasContributionCap() {
        return hasContributionCap;
    }

    public BigDecimal getMinContribution() {
        return minContribution;
    }

    public BigDecimal getMaxContribution() {
        return maxContribution;
    }

    public LocalDate getEffectiveAt() {
        return effectiveAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof DeductionChart)) return false;
        DeductionChart that = (DeductionChart) object;
        return deductionChartId == that.deductionChartId &&
               deductionTypeId == that.deductionTypeId &&
               hasContributionCap == that.hasContributionCap &&
               Objects.equals(minimumRange, that.minimumRange) &&
               Objects.equals(maximumRange, that.maximumRange) &&
               Objects.equals(fixedAmount, that.fixedAmount) &&
               Objects.equals(employeeDeductionRate, that.employeeDeductionRate) &&
               Objects.equals(employerContributionShare, that.employerContributionShare) &&
               Objects.equals(minContribution, that.minContribution) &&
               Objects.equals(maxContribution, that.maxContribution) &&
               Objects.equals(effectiveAt, that.effectiveAt) &&
               Objects.equals(updatedAt, that.updatedAt) &&
               Objects.equals(notes, that.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deductionChartId, deductionTypeId, minimumRange, maximumRange, fixedAmount,
                            employeeDeductionRate, employerContributionShare, hasContributionCap,
                            minContribution, maxContribution, effectiveAt, updatedAt, notes);
    }

    @Override
    public String toString() {
        return "DeductionChart{" +
               "deductionChartId=" + deductionChartId +
               ", deductionTypeId=" + deductionTypeId +
               ", minimumRange=" + minimumRange +
               ", maximumRange=" + maximumRange +
               ", fixedAmount=" + fixedAmount +
               ", employeeDeductionRate=" + employeeDeductionRate +
               ", employerContributionShare=" + employerContributionShare +
               ", hasContributionCap=" + hasContributionCap +
               ", minContribution=" + minContribution +
               ", maxContribution=" + maxContribution +
               ", effectiveAt=" + effectiveAt +
               ", updatedAt=" + updatedAt +
               ", notes='" + notes + '\'' +
               '}';
    }
}
