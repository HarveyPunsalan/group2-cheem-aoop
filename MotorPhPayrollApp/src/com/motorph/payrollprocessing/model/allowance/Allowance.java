/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.model.allowance;

import java.util.Objects;

public class Allowance {
    private final int allowanceId;
    private String allowanceName;
    private String description;

    // === Constructors ===

    public Allowance(Builder build) {
        this.allowanceId = build.allowanceId;
        this.allowanceName = build.allowanceName;
        this.description = build.description;
    }

    // === Builder Pattern ===
    public static class Builder {
        private int allowanceId;
        private String allowanceName;
        private String description;

        public Builder allowanceId(int id) {
            this.allowanceId = id;
            return this;
        }

        public Builder allowanceName(String name) {
            this.allowanceName = name;
            return this;
        }

        public Builder description(String desc) {
            this.description = desc;
            return this;
        }

        public Allowance build() {
            return new Allowance(this);
        }
    }

    // === Getters and Setters ===

    public int getAllowanceId() {
        return allowanceId;
    }

    public String getAllowanceName() {
        return allowanceName;
    }

    public void setAllowanceName(String allowanceName) {
        this.allowanceName = allowanceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    // ✅ equals(), hashCode(), toString()
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Allowance)) return false;
        Allowance that = (Allowance) object;
        return allowanceId == that.allowanceId &&
               Objects.equals(allowanceName, that.allowanceName) &&
               Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(allowanceId, allowanceName, description);
    }

    @Override
    public String toString() {
        return "Allowance{" +
               "allowanceId=" + allowanceId +
               ", allowanceName='" + allowanceName + '\'' +
               ", description='" + description + '\'' +
               '}';
    }
}

