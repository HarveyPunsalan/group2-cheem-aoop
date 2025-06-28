/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.model;

import java.util.Objects;

/**
 * This represents a user role in the system
 * Corresponds to the user_role table in the database
 * 
 * @author harvey punsalan
 */
public class Role {
    private int roleId;                    // Primary key
    private String roleName;               // Human-readable role name
    private String roleDescription;        // Detailed description of role purpose
    
    /**
     * Default constructor for DAO operations
     */
    public Role() {}
    
    /**
     * Full constructor for creating Role objects from database results
     * 
     * @param roleId Database primary key
     * @param roleName Short descriptive name
     * @param roleDescription Detailed role explanation
     */
    public Role(int roleId, String roleName, String roleDescription) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleDescription = roleDescription;
    }
    
    /**
     * Constructor for creating new roles (ID will be auto-generated)
     * 
     * @param roleName Role identifier name
     * @param roleDescription What this role is responsible for
     */
    public Role(String roleName, String roleDescription) {
        this.roleName = roleName;
        this.roleDescription = roleDescription;
    }
    
    // Getters and Setters
    
    public int getRoleId() {
        return roleId;
    }
    
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    
    public String getRoleName() {
        return roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public String getRoleDescription() {
        return roleDescription;
    }
    
    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Role role = (Role) obj;
        return roleId == role.roleId && Objects.equals(roleName, role.roleName);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName);
    }
    
    @Override
    public String toString() {
        return String.format("Role{roleId=%d, roleName='%s', description='%s'}", 
                           roleId, roleName, roleDescription);
    }
}
