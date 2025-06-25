/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.service;

import com.motorph.usermanagement.model.Permission;
import com.motorph.usermanagement.dao.PermissionDAO;
import com.motorph.usermanagement.dao.PermissionDAOImpl;
import com.motorph.usermanagement.dao.RoleDAO;
import com.motorph.usermanagement.dao.RoleDAOImpl;
import com.motorph.usermanagement.exception.PermissionNotFoundException;
import com.motorph.usermanagement.exception.DuplicatePermissionException;
import com.motorph.usermanagement.exception.DataAccessException;
import com.motorph.usermanagement.util.ValidationUtils;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * This handles all business logic related to permission management operations.
 * Coordinates between DAOs and applies business validation rules.
 * 
 * @author harvey punsalan
 */
public class PermissionServiceImpl implements PermissionService {
    private static final Logger logger = Logger.getLogger(PermissionServiceImpl.class.getName());
    
    private final PermissionDAO permissionDAO;
    private final RoleDAO roleDAO;
    
    /**
     * Default constructor initializes required DAOs.
     */
    public PermissionServiceImpl() {
        this.permissionDAO = new PermissionDAOImpl();
        this.roleDAO = new RoleDAOImpl();
        logger.info("PermissionServiceImpl initialized successfully");
    }
    
    /**
     * Constructor for dependency injection (useful for testing).
     */
    public PermissionServiceImpl(PermissionDAO permissionDAO, RoleDAO roleDAO) {
        this.permissionDAO = permissionDAO;
        this.roleDAO = roleDAO;
    }
    
    @Override
    public int createPermission(Permission permission) throws DuplicatePermissionException, DataAccessException {
        // Validate permission input data
        if (!ValidationUtils.isValidPermissionName(permission.getAccessName())) {
            throw new IllegalArgumentException("Invalid permission name format. Permission name must be 3-100 characters and contain only letters, numbers, spaces, and common punctuation.");
        }
        
        // Check if permission name already exists
        if (permissionDAO.permissionNameExists(permission.getAccessName())) {
            throw new DuplicatePermissionException("Permission name '" + permission.getAccessName() + "' already exists");
        }
        
        // Set default values for new permission
        permission.setActive(true);
        
        // Create the permission record
        int permissionId = permissionDAO.createPermission(permission);
        logger.info(() -> "Successfully created new permission with ID: " + permissionId + ", name: " + permission.getAccessName());
        
        return permissionId;
    }
    
    @Override
    public Optional<Permission> getPermissionById(int permissionId) throws DataAccessException {
        return permissionDAO.findById(permissionId);
    }
    
    @Override
    public Optional<Permission> getPermissionByName(String permissionName) throws DataAccessException {
        if (permissionName == null || permissionName.trim().isEmpty()) {
            return Optional.empty();
        }
        return permissionDAO.findByName(permissionName.trim());
    }
    
    @Override
    public List<Permission> getAllPermissions() throws DataAccessException {
        return permissionDAO.findAll();
    }
    
    @Override
    public List<Permission> getPermissionsByCategory(int categoryId) throws DataAccessException {
        return permissionDAO.findByCategory(categoryId);
    }
    
    @Override
    public List<Permission> getActivePermissions() throws DataAccessException {
        return permissionDAO.findActivePermissions();
    }
    
    @Override
    public boolean updatePermission(Permission permission) throws PermissionNotFoundException, DataAccessException {
        // Validate permission data
        if (!ValidationUtils.isValidPermissionName(permission.getAccessName())) {
            throw new IllegalArgumentException("Invalid permission name format");
        }
        
        // Verify permission exists
        if (!permissionDAO.findById(permission.getAccessId()).isPresent()) {
            throw new PermissionNotFoundException("Permission with ID " + permission.getAccessId() + " not found");
        }
        
        boolean success = permissionDAO.updatePermission(permission);
        
        if (success) {
            logger.info(() -> "Successfully updated permission with ID: " + permission.getAccessId());
        }
        
        return success;
    }
    
    @Override
    public boolean setPermissionStatus(int permissionId, boolean isActive) throws PermissionNotFoundException, DataAccessException {
        // Verify permission exists
        if (!permissionDAO.findById(permissionId).isPresent()) {
            throw new PermissionNotFoundException("Permission with ID " + permissionId + " not found");
        }
        
        boolean success = permissionDAO.setPermissionStatus(permissionId, isActive);
        
        String statusText = isActive ? "activated" : "deactivated";
        if (success) {
            logger.info(() -> "Successfully " + statusText + " permission with ID: " + permissionId);
        }
        
        return success;
    }
    
    @Override
    public boolean deletePermission(int permissionId) throws PermissionNotFoundException, DataAccessException {
        // Verify permission exists
        if (!permissionDAO.findById(permissionId).isPresent()) {
            throw new PermissionNotFoundException("Permission with ID " + permissionId + " not found");
        }
        
        // Check if permission is assigned to any roles before deletion
        // This would require additional business logic based on your requirements
        
        boolean success = permissionDAO.deletePermission(permissionId);
        
        if (success) {
            logger.info(() -> "Successfully deleted permission with ID: " + permissionId);
        }
        
        return success;
    }
    
    @Override
    public boolean isPermissionNameAvailable(String permissionName) throws DataAccessException {
        if (permissionName == null || permissionName.trim().isEmpty()) {
            return false;
        }
        return !permissionDAO.permissionNameExists(permissionName.trim());
    }
    
    @Override
    public int getPermissionCount() throws DataAccessException {
        return permissionDAO.getPermissionCount();
    }
    
    @Override
    public int getActivePermissionCount() throws DataAccessException {
        return permissionDAO.getActivePermissionCount();
    }
    
    @Override
    public boolean assignPermissionToRole(int roleId, int permissionId) throws DataAccessException {
        // Verify role exists
        if (!roleDAO.findById(roleId).isPresent()) {
            throw new IllegalArgumentException("Role with ID " + roleId + " not found");
        }
        
        // Verify permission exists
        if (!permissionDAO.findById(permissionId).isPresent()) {
            throw new IllegalArgumentException("Permission with ID " + permissionId + " not found");
        }
        
        // This would require a method in RoleDAO or a separate RolePermissionDAO
        // For now, we'll implement basic logic
        boolean success = permissionDAO.assignToRole(roleId, permissionId);
        
        if (success) {
            logger.info(() -> "Successfully assigned permission " + permissionId + " to role " + roleId);
        }
        
        return success;
    }
    
    @Override
    public boolean removePermissionFromRole(int roleId, int permissionId) throws DataAccessException {
        boolean success = permissionDAO.removeFromRole(roleId, permissionId);
        
        if (success) {
            logger.info(() -> "Successfully removed permission " + permissionId + " from role " + roleId);
        }
        
        return success;
    }
    
    @Override
    public List<Permission> getPermissionsForRole(int roleId) throws DataAccessException {
        return permissionDAO.findByRole(roleId);
    }
}
