/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.model.deduction;

import java.time.LocalDateTime;
import java.util.Objects;

public class Deduction {
    private final int deductionId;
    private final LocalDateTime createdAt;

    private String deductionCode;
    private String deductionName;
    private String deductionDescription;
    private LocalDateTime updatedAt;

    private Deduction(Builder builder) {
        this.deductionId = builder.deductionId;
        this.createdAt = builder.createdAt;
        this.deductionCode = builder.deductionCode;
        this.deductionName = builder.deductionName;
        this.deductionDescription = builder.deductionDescription;
        this.updatedAt = builder.updatedAt;
    }    

    public static class Builder {
        private int deductionId;
        private String deductionCode;
        private String deductionName;
        private String deductionDescription;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder deductionId(int deductionId) {
            this.deductionId = deductionId;
            return this;
        }

        public Builder deductionCode(String deductionCode) {
            this.deductionCode = deductionCode;
            return this;
        }

        public Builder deductionName(String deductionName) {
            this.deductionName = deductionName;
            return this;
        }

        public Builder deductionDescription(String deductionDescription) {
            this.deductionDescription = deductionDescription;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Deduction build() {
            return new Deduction(this);
        }
    }
    
    // Getters
    public int getDeductionId() {
        return deductionId;
    }

    public String getDeductionCode() {
        return deductionCode;
    }

    public String getDeductionName() {
        return deductionName;
    }

    public String getDeductionDescription() {
        return deductionDescription;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // ✅ Setters for mutable fields
    public void setDeductionCode(String deductionCode) {
        this.deductionCode = deductionCode;
    }

    public void setDeductionName(String deductionName) {
        this.deductionName = deductionName;
    }

    public void setDeductionDescription(String deductionDescription) {
        this.deductionDescription = deductionDescription;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // equals(), hashCode(), toString()
    @Override
    public boolean equals(Object Object) {
        if (this == Object) return true;
        if (!(Object instanceof Deduction)) return false;
        Deduction that = (Deduction) Object;
        return deductionId == that.deductionId &&
               Objects.equals(deductionCode, that.deductionCode) &&
               Objects.equals(deductionName, that.deductionName) &&
               Objects.equals(deductionDescription, that.deductionDescription) &&
               Objects.equals(createdAt, that.createdAt) &&
               Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deductionId, deductionCode, deductionName, deductionDescription, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "DeductionChartType{" +
               "deductionId=" + deductionId +
               ", deductionCode='" + deductionCode + '\'' +
               ", deductionName='" + deductionName + '\'' +
               ", deductionDescription='" + deductionDescription + '\'' +
               ", createdAt=" + createdAt +
               ", updatedAt=" + updatedAt +
               '}';
    }
}


