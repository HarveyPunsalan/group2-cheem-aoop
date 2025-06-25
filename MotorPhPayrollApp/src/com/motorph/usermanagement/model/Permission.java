/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.model;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Represents a permission that controls access to specific system features.
 * Corresponds to the user_access table in the database.
 * 
 * @author harvey 
 */
public class Permission {
    // Core database fields
    private int accessId;                       // Unique ID for this permission
    private String accessName;             // Name for the permission
    private int accessCategoryId;           // Points to category (like Payroll, HR, etc.)
    private int resourceId;                    // What resource this permission applies to
    private int actionId;                       // What action is allowed (e.g., READ, WRITE)
    private boolean requiresApproval;    // Whether this access requires approval 
    private boolean isActive;                // True if this permission is currently enabled
    private Timestamp createdAt;         // When this permission was added
    
    // Additional fields for display purposes, not stored in database
    private String categoryName;           
    private String resourceName;          
    private String actionName;              
    
    /**
     * Default constructor used when loading data or creating empty instances
     */
    public Permission() {}
    
    /**
     * Full constructor used when creating a permission object from the database.
     * 
     * @param accessId Database primary key
     * @param accessName Descriptive label for access point
     * @param accessCategoryId Category ID this permission belongs to
     * @param resourceId Resource this permission applies to
     * @param actionId Action this permission allows
     * @param requiresApproval Whether approval is required
     * @param isActive Whether this permission is active
     * @param createdAt Creation timestamp
     */
    public Permission(int accessId, String accessName, int accessCategoryId, 
                     int resourceId, int actionId, boolean requiresApproval, 
                     boolean isActive, Timestamp createdAt) {
        this.accessId = accessId;
        this.accessName = accessName;
        this.accessCategoryId = accessCategoryId;
        this.resourceId = resourceId;
        this.actionId = actionId;
        this.requiresApproval = requiresApproval;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }
    
    /**
     * Constructor used when creating a new permission (ID is auto-generated).
     * 
     * @param accessName Descriptive name for the permission
     * @param accessCategoryId Category this permission belongs to
     * @param resourceId Resource this permission applies to
     * @param actionId Action this permission allows
     * @param requiresApproval Whether approval is required
     * @param isActive Whether this permission is active
     */
    public Permission(String accessName, int accessCategoryId, int resourceId, 
                     int actionId, boolean requiresApproval, boolean isActive) {
        this.accessName = accessName;
        this.accessCategoryId = accessCategoryId;
        this.resourceId = resourceId;
        this.actionId = actionId;
        this.requiresApproval = requiresApproval;
        this.isActive = isActive;
    }
    
    // ---------- Getters and Setters ----------
    
    public int getAccessId() {
        return accessId;
    }
    
    public void setAccessId(int accessId) {
        this.accessId = accessId;
    }
    
    public String getAccessName() {
        return accessName;
    }
    
    public void setAccessName(String accessName) {
        this.accessName = accessName;
    }
    
    public int getAccessCategoryId() {
        return accessCategoryId;
    }
    
    public void setAccessCategoryId(int accessCategoryId) {
        this.accessCategoryId = accessCategoryId;
    }
    
    public int getResourceId() {
        return resourceId;
    }
    
    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
    
    public int getActionId() {
        return actionId;
    }
    
    public void setActionId(int actionId) {
        this.actionId = actionId;
    }
    
    public boolean isRequiresApproval() {
        return requiresApproval;
    }
    
    public void setRequiresApproval(boolean requiresApproval) {
        this.requiresApproval = requiresApproval;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    // ---------- Display Name Fields ----------
    
    public String getCategoryName() {
        return categoryName != null ? categoryName : "Unknown Category";
    }
    
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    public String getResourceName() {
        return resourceName != null ? resourceName : "Unknown Resource";
    }
    
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
    
    public String getActionName() {
        return actionName != null ? actionName : "Unknown Action";
    }
    
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }
    
    // ---------- Utility Methods ----------
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Permission permission = (Permission) obj;
        return accessId == permission.accessId && 
               Objects.equals(accessName, permission.accessName);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(accessId, accessName);
    }
    
    @Override
    public String toString() {
        return String.format("Permission{accessId=%d, accessName='%s', categoryId=%d, " +
                           "resourceId=%d, actionId=%d, requiresApproval=%s, isActive=%s, " +
                           "categoryName='%s', resourceName='%s', actionName='%s'}", 
                           accessId, accessName, accessCategoryId, resourceId, 
                           actionId, requiresApproval, isActive, categoryName, resourceName, actionName);
    }
}
