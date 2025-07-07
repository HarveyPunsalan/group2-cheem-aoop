/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.controller;

import com.motorph.usermanagement.model.Permission;
import com.motorph.usermanagement.service.PermissionService;
import com.motorph.usermanagement.service.PermissionServiceImpl;
import com.motorph.usermanagement.exception.PermissionNotFoundException;
import com.motorph.usermanagement.exception.DuplicatePermissionException;
import com.motorph.usermanagement.exception.DataAccessException;
import com.motorph.usermanagement.view.PermissionManagementPage;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * This manages CRUD operations for permissions and role permission assignments
 * 
 * @author Harvey 
 */
public class PermissionController {
    private static final Logger logger = Logger.getLogger(PermissionController.class.getName());
    
    private final PermissionService permissionService;
    private final PermissionManagementPage permissionView;
    
    /**
     * Constructor initializes the controller with required services and view.
     * 
     * @param permissionView the view that displays and manages permissions
     */
    public PermissionController(PermissionManagementPage permissionView) {
        this.permissionView = permissionView;
        this.permissionService = new PermissionServiceImpl();
        
        logger.info("PermissionController initialized successfully");
    }
    
    /**
     * Loads all permissions and refreshes the view.
     */
    public void loadAllPermissions() {
        try {
            List<Permission> permissions = permissionService.getAllPermissions();
            updatePermissionTable(permissions);
            logger.info(() -> "Loaded " + permissions.size() + " permissions successfully");
            
        } catch (DataAccessException e) {
            showErrorMessage("Error loading permissions: " + e.getMessage());
            logger.log(Level.SEVERE, "Error loading permissions", e);
        }
    }
    
    /**
     * Loads only active permissions and refreshes the view.
     */
    public void loadActivePermissions() {
        try {
            List<Permission> activePermissions = permissionService.getActivePermissions();
            updatePermissionTable(activePermissions);
            logger.info(() -> "Loaded " + activePermissions.size() + " active permissions successfully");
            
        } catch (DataAccessException e) {
            showErrorMessage("Error loading active permissions: " + e.getMessage());
            logger.log(Level.SEVERE, "Error loading active permissions", e);
        }
    }
    
    /**
     * Creates a new permission with the provided information.
     * 
     * @param permissionName the name of the permission
     * @param categoryId the ID of the permission category
     * @param resourceId the ID of the associated resource
     * @param actionId the ID of the action
     * @param requiresApproval indicates if this permission requires approval
     */
    public void createPermission(String permissionName, int categoryId, int resourceId, int actionId, boolean requiresApproval) {
        try {
            // Basic validation
            if (permissionName == null || permissionName.trim().isEmpty()) {
                showErrorMessage("Permission name is required");
                return;
            }
            
            // Create permission object
            Permission newPermission = new Permission();
            newPermission.setAccessName(permissionName.trim());
            newPermission.setAccessCategoryId(categoryId);
            newPermission.setResourceId(resourceId);
            newPermission.setActionId(actionId);
            newPermission.setRequiresApproval(requiresApproval);
            newPermission.setActive(true);
            
            // Create the permission
            int permissionId = permissionService.createPermission(newPermission);
            
            if (permissionId > 0) {
                showSuccessMessage("Permission created successfully with ID: " + permissionId);
                loadAllPermissions(); // Refresh the permission list
                logger.info(() -> "Successfully created permission: " + permissionName + " with ID: " + permissionId);
            } else {
                showErrorMessage("Failed to create permission. Please try again.");
            }
            
        } catch (DuplicatePermissionException e) {
            showErrorMessage("Permission name already exists. Please choose a different name.");
            logger.warning(() -> "Duplicate permission name attempt: " + permissionName);
            
        } catch (DataAccessException e) {
            showErrorMessage("Database error: " + e.getMessage());
            logger.log(Level.SEVERE, "Database error creating permission", e);
            
        } catch (Exception e) {
            showErrorMessage("Unexpected error occurred while creating permission");
            logger.log(Level.SEVERE, "Unexpected error creating permission", e);
        }
    }
    
    /**
     * Updates an existing permission's information.
     * 
     * @param permissionId the ID of the permission to update
     * @param permissionName the new name of the permission
     * @param categoryId the updated category ID
     * @param resourceId the updated resource ID
     * @param actionId the updated action ID
     * @param requiresApproval true if the permission should require approval
     */
    public void updatePermission(int permissionId, String permissionName, int categoryId, int resourceId, int actionId, boolean requiresApproval) {
        try {
            // Get existing permission first
            Optional<Permission> existingPermission = permissionService.getPermissionById(permissionId);
            if (!existingPermission.isPresent()) {
                showErrorMessage("Permission not found");
                return;
            }
            
            Permission permissionToUpdate = existingPermission.get();
            permissionToUpdate.setAccessName(permissionName.trim());
            permissionToUpdate.setAccessCategoryId(categoryId);
            permissionToUpdate.setResourceId(resourceId);
            permissionToUpdate.setActionId(actionId);
            permissionToUpdate.setRequiresApproval(requiresApproval);
            
            boolean success = permissionService.updatePermission(permissionToUpdate);
            
            if (success) {
                showSuccessMessage("Permission updated successfully");
                loadAllPermissions(); // Refresh the permission list
                logger.info(() -> "Successfully updated permission ID: " + permissionId);
            } else {
                showErrorMessage("Failed to update permission");
            }
            
        } catch (PermissionNotFoundException e) {
            showErrorMessage("Permission not found: " + e.getMessage());
            logger.warning(() -> "Permission not found for update: " + permissionId);
            
        } catch (DataAccessException e) {
            showErrorMessage("Database error: " + e.getMessage());
            logger.log(Level.SEVERE, "Database error updating permission", e);
        }
    }
    
    /**
     * Activates or deactivates a permission.
     * 
     * @param permissionId the ID of the permission
     * @param isActive true to activate, false to deactivate
     */
    public void setPermissionStatus(int permissionId, boolean isActive) {
        try {
            boolean success = permissionService.setPermissionStatus(permissionId, isActive);
            
            if (success) {
                String statusText = isActive ? "activated" : "deactivated";
                showSuccessMessage("Permission " + statusText + " successfully");
                loadAllPermissions(); // Refresh the permission list
                logger.info(() -> "Successfully " + statusText + " permission ID: " + permissionId);
            } else {
                showErrorMessage("Failed to change permission status");
            }
            
        } catch (PermissionNotFoundException e) {
            showErrorMessage("Permission not found: " + e.getMessage());
            logger.warning(() -> "Permission not found for status change: " + permissionId);
            
        } catch (DataAccessException e) {
            showErrorMessage("Database error: " + e.getMessage());
            logger.log(Level.SEVERE, "Database error changing permission status", e);
        }
    }
    
    /**
     * Assigns a permission to a role.
     * 
     * @param roleId the ID of the role
     * @param permissionId the ID of the permission to assign
     */
    public void assignPermissionToRole(int roleId, int permissionId) {
        try {
            boolean success = permissionService.assignPermissionToRole(roleId, permissionId);
            
            if (success) {
                showSuccessMessage("Permission assigned to role successfully");
                logger.info(() -> "Successfully assigned permission " + permissionId + " to role " + roleId);
            } else {
                showErrorMessage("Failed to assign permission to role");
            }
            
        } catch (DataAccessException e) {
            showErrorMessage("Database error: " + e.getMessage());
            logger.log(Level.SEVERE, "Database error assigning permission to role", e);
        }
    }
    
    /**
     * Removes a permission from a role.
     * 
     * @param roleId the ID of the role
     * @param permissionId the ID of the permission to remove
     */
    public void removePermissionFromRole(int roleId, int permissionId) {
        try {
            // Confirm removal
            int confirm = JOptionPane.showConfirmDialog(
                permissionView,
                "Are you sure you want to remove this permission from the role?",
                "Confirm Remove",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );
            
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
            
            boolean success = permissionService.removePermissionFromRole(roleId, permissionId);
            
            if (success) {
                showSuccessMessage("Permission removed from role successfully");
                logger.info(() -> "Successfully removed permission " + permissionId + " from role " + roleId);
            } else {
                showErrorMessage("Failed to remove permission from role");
            }
            
        } catch (DataAccessException e) {
            showErrorMessage("Database error: " + e.getMessage());
            logger.log(Level.SEVERE, "Database error removing permission from role", e);
        }
    }
    
    /**
     * Loads permissions for a specific role.
     * 
     * @param roleId the ID of the role whose permissions will be loaded
     */
    public void loadPermissionsForRole(int roleId) {
        try {
            List<Permission> rolePermissions = permissionService.getPermissionsForRole(roleId);
            updatePermissionTable(rolePermissions);
            logger.info(() -> "Loaded " + rolePermissions.size() + " permissions for role ID: " + roleId);
            
        } catch (DataAccessException e) {
            showErrorMessage("Error loading permissions for role: " + e.getMessage());
            logger.log(Level.SEVERE, "Error loading permissions for role", e);
        }
    }
    
    /**
     * Updates the permission table in the view with the provided permission list.
     */
    private void updatePermissionTable(List<Permission> permissions) {
        DefaultTableModel tableModel = new DefaultTableModel();
        
        // Set column headers
        String[] columnNames = {"Permission ID", "Name", "Category", "Resource", "Action", "Requires Approval", "Status"};
        tableModel.setColumnIdentifiers(columnNames);
        
        // Add permission data to table model
        for (Permission permission : permissions) {
            Object[] rowData = {
                permission.getAccessId(),
                permission.getAccessName(),
                permission.getCategoryName(),
                permission.getResourceName(),
                permission.getActionName(),
                permission.isRequiresApproval() ? "Yes" : "No",
                permission.isActive() ? "Active" : "Inactive"
            };
            tableModel.addRow(rowData);
        }
        
        // Update the view's table
        permissionView.setTableModel(tableModel);
    }
    
    /**
     * Shows error message to user.
     */
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(
            permissionView, 
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
            permissionView, 
            message, 
            "Success", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
