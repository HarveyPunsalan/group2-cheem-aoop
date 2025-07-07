/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.controller;

import com.motorph.usermanagement.model.Role;
import com.motorph.usermanagement.service.RoleService;
import com.motorph.usermanagement.service.RoleServiceImpl;
import com.motorph.usermanagement.exception.RoleNotFoundException;
import com.motorph.usermanagement.exception.DuplicateRoleException;
import com.motorph.usermanagement.exception.DataAccessException;
import com.motorph.usermanagement.view.RoleManagementPage;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * This manages CRUD operations for roles and coordinates with the view layer
 * 
 * @author Harvey 
 */
public class RoleController {
    private static final Logger logger = Logger.getLogger(RoleController.class.getName());
    
    private final RoleService roleService;
    private final RoleManagementPage roleView;
    
    /**
     * Constructor initializes the controller with required services and view.
     * 
     * @param roleView the view component that displays role information
     */
    public RoleController(RoleManagementPage roleView) {
        this.roleView = roleView;
        this.roleService = new RoleServiceImpl();
        
        logger.info("RoleController initialized successfully");
    }
    
    /**
     * Loads all roles and refreshes the view.
     */
    public void loadAllRoles() {
        try {
            List<Role> roles = roleService.getAllRoles();
            updateRoleTable(roles);
            logger.info(() -> "Loaded " + roles.size() + " roles successfully");
            
        } catch (DataAccessException e) {
            showErrorMessage("Error loading roles: " + e.getMessage());
            logger.log(Level.SEVERE, "Error loading roles", e);
        }
    }
    
    /**
     * Creates a new role with the provided information.
     * 
     * @param roleName the name of the role
     * @param roleDescription a short description for the role
     */
    public void createRole(String roleName, String roleDescription) {
        try {
            // Basic validation
            if (roleName == null || roleName.trim().isEmpty()) {
                showErrorMessage("Role name is required");
                return;
            }
            
            // Create role object
            Role newRole = new Role();
            newRole.setRoleName(roleName.trim());
            newRole.setRoleDescription(roleDescription != null ? roleDescription.trim() : "");
            
            // Create the role
            int roleId = roleService.createRole(newRole);
            
            if (roleId > 0) {
                showSuccessMessage("Role created successfully with ID: " + roleId);
                loadAllRoles(); // Refresh the role list
                logger.info(() -> "Successfully created role: " + roleName + " with ID: " + roleId);
            } else {
                showErrorMessage("Failed to create role. Please try again.");
            }
            
        } catch (DuplicateRoleException e) {
            showErrorMessage("Role name already exists. Please choose a different name.");
            logger.warning(() -> "Duplicate role name attempt: " + roleName);
            
        } catch (DataAccessException e) {
            showErrorMessage("Database error: " + e.getMessage());
            logger.log(Level.SEVERE, "Database error creating role", e);
            
        } catch (Exception e) {
            showErrorMessage("Unexpected error occurred while creating role");
            logger.log(Level.SEVERE, "Unexpected error creating role", e);
        }
    }
    
    /**
     * Updates an existing role's information.
     * 
     * @param roleId the ID of the role to update
     * @param roleName the new name to assign to the role
     * @param roleDescription the new description for the role
     */
    public void updateRole(int roleId, String roleName, String roleDescription) {
        try {
            // Get existing role first
            Optional<Role> existingRole = roleService.getRoleById(roleId);
            if (!existingRole.isPresent()) {
                showErrorMessage("Role not found");
                return;
            }
            
            Role roleToUpdate = existingRole.get();
            roleToUpdate.setRoleName(roleName.trim());
            roleToUpdate.setRoleDescription(roleDescription != null ? roleDescription.trim() : "");
            
            boolean success = roleService.updateRole(roleToUpdate);
            
            if (success) {
                showSuccessMessage("Role updated successfully");
                loadAllRoles(); // Refresh the role list
                logger.info(() -> "Successfully updated role ID: " + roleId);
            } else {
                showErrorMessage("Failed to update role");
            }
            
        } catch (RoleNotFoundException e) {
            showErrorMessage("Role not found: " + e.getMessage());
            logger.warning(() -> "Role not found for update: " + roleId);
            
        } catch (DataAccessException e) {
            showErrorMessage("Database error: " + e.getMessage());
            logger.log(Level.SEVERE, "Database error updating role", e);
        }
    }
    
    /**
     * Deletes a role if it's not assigned to any users.
     * 
     * @param roleId the ID of the role to delete
     */
    public void deleteRole(int roleId) {
        try {
            // Confirm deletion
            int confirm = JOptionPane.showConfirmDialog(
                roleView,
                "Are you sure you want to delete this role? This action cannot be undone.",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );
            
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
            
            boolean success = roleService.deleteRole(roleId);
            
            if (success) {
                showSuccessMessage("Role deleted successfully");
                loadAllRoles(); // Refresh the role list
                logger.info(() -> "Successfully deleted role ID: " + roleId);
            } else {
                showErrorMessage("Failed to delete role. It may be assigned to existing users.");
            }
            
        } catch (RoleNotFoundException e) {
            showErrorMessage("Role not found: " + e.getMessage());
            logger.warning(() -> "Role not found for deletion: " + roleId);
            
        } catch (DataAccessException e) {
            showErrorMessage("Database error: " + e.getMessage());
            logger.log(Level.SEVERE, "Database error deleting role", e);
        }
    }
    
    /**
     * Searches for roles by name.
     * 
     * @param searchTerm the keyword used to find roles
     */
    public void searchRoles(String searchTerm) {
        try {
            if (searchTerm == null || searchTerm.trim().isEmpty()) {
                loadAllRoles(); // Load all roles if search term is empty
                return;
            }
            
            List<Role> searchResults = roleService.searchRolesByName(searchTerm.trim());
            updateRoleTable(searchResults);
            
            logger.info(() -> "Search completed for term: " + searchTerm + ", found " + searchResults.size() + " results");
            
        } catch (DataAccessException e) {
            showErrorMessage("Error searching roles: " + e.getMessage());
            logger.log(Level.SEVERE, "Error searching roles", e);
        }
    }
    
    /**
     * Updates the role table in the view with the provided role list.
     */
    private void updateRoleTable(List<Role> roles) {
        DefaultTableModel tableModel = new DefaultTableModel();
        
        // Set column headers
        String[] columnNames = {"Role ID", "Role Name", "Description", "User Count"};
        tableModel.setColumnIdentifiers(columnNames);
        
        // Add role data to table model
        for (Role role : roles) {
            Object[] rowData = {
                role.getRoleId(),
                role.getRoleName(),
                role.getRoleDescription(),
                getUserCountForRole(role.getRoleId())
            };
            tableModel.addRow(rowData);
        }
        
        // Update the view's table
        roleView.setTableModel(tableModel);
    }
    
    /**
     * Gets user count for a specific role.
     */
    private int getUserCountForRole(int roleId) {
        try {
            return roleService.getUserCountForRole(roleId);
        } catch (DataAccessException e) {
            logger.log(Level.WARNING, "Error getting user count for role " + roleId, e);
            return 0;
        }
    }
    
    /**
     * Shows error message to user.
     */
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(
            roleView, 
            message, 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
    }
    
    /**
     * Shows success message to user.
     */
    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(
            roleView, 
            message, 
            "Success", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
