/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.security;

import com.motorph.usermanagement.model.User;
import com.motorph.usermanagement.model.Permission;
import com.motorph.usermanagement.dao.PermissionDAO;
import com.motorph.usermanagement.dao.PermissionDAOImpl;
import com.motorph.usermanagement.dao.RoleDAO;
import com.motorph.usermanagement.dao.RoleDAOImpl;
import com.motorph.usermanagement.exception.DataAccessException;

import java.util.List;
import java.util.logging.Logger;

/**
 * This provides role based access control and permission checking functionality.
 * 
 * @author Harvey
 */
public class AuthorizerImpl implements Authorizer {
    private static final Logger logger = Logger.getLogger(AuthorizerImpl.class.getName());
    
    private final PermissionDAO permissionDAO;
    private final RoleDAO roleDAO;
    
    // Constants for role names
    private static final String ADMIN_ROLE = "Administrator";
    private static final String HR_ADMIN_ROLE = "HR Administrator";
    
    /**
     * Constructor initializes required DAOs.
     */
    public AuthorizerImpl() {
        this.permissionDAO = new PermissionDAOImpl();
        this.roleDAO = new RoleDAOImpl();
        logger.info("AuthorizerImpl initialized successfully");
    }
    
    /**
     * Constructor
     * 
     * @param permissionDAO
     * @param roleDAO
     */
    public AuthorizerImpl(PermissionDAO permissionDAO, RoleDAO roleDAO) {
        this.permissionDAO = permissionDAO;
        this.roleDAO = roleDAO;
    }
    
    @Override
    public boolean hasPermission(User user, String permissionName) throws DataAccessException {
        if (user == null || permissionName == null || permissionName.trim().isEmpty()) {
            return false;
        }
        
        // Get user's permissions based on their role
        List<Permission> userPermissions = getUserPermissions(user);
        
        // Check if any of the user's permissions match the requested permission
        boolean hasPermission = userPermissions.stream()
                .anyMatch(permission -> permissionName.equalsIgnoreCase(permission.getAccessName()));
        
        logger.fine(() -> "Permission check for user " + user.getUsername() + 
                   " and permission " + permissionName + ": " + hasPermission);
        
        return hasPermission;
    }
    
    @Override
    public boolean hasAnyPermission(User user, List<String> permissionNames) throws DataAccessException {
        if (user == null || permissionNames == null || permissionNames.isEmpty()) {
            return false;
        }
        
        // Check if user has any of the specified permissions
        for (String permissionName : permissionNames) {
            if (hasPermission(user, permissionName)) {
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public boolean hasAllPermissions(User user, List<String> permissionNames) throws DataAccessException {
        if (user == null || permissionNames == null || permissionNames.isEmpty()) {
            return false;
        }
        
        // Check if user has all of the specified permissions
        for (String permissionName : permissionNames) {
            if (!hasPermission(user, permissionName)) {
                return false;
            }
        }
        
        return true;
    }
    
    @Override
    public List<Permission> getUserPermissions(User user) throws DataAccessException {
        if (user == null) {
            return List.of(); // Return empty list
        }
        
        try {
            int roleId = user.getRoleId();
            List<Permission> permissions = permissionDAO.findByRole(roleId);
            
            logger.fine(() -> "Retrieved " + permissions.size() + " permissions for user " + user.getUsername());
            
            return permissions;
        } catch (Exception e) {
            logger.warning(() -> "Error getting permissions for user " + user.getUsername() + ": " + e.getMessage());
            return List.of();
        }
    }
    
    @Override
    public boolean hasResourceAccess(User user, String resourceName, String actionName) throws DataAccessException {
        if (user == null || resourceName == null || actionName == null) {
            return false;
        }
        
        // Get user's permissions
        List<Permission> userPermissions = getUserPermissions(user);
        
        // Since Permission class uses resourceId and actionId, i need to modify this approach
        // For now, ill check against the access name which should contain resource+action info
        // i may need to adjust this based on my actual permission naming convention
        String combinedPermission = resourceName + "_" + actionName;
        
        boolean hasAccess = userPermissions.stream()
                .anyMatch(permission -> 
                    permission.getAccessName() != null && 
                    permission.getAccessName().toLowerCase().contains(resourceName.toLowerCase()) &&
                    permission.getAccessName().toLowerCase().contains(actionName.toLowerCase()));
        
        logger.fine(() -> "Resource access check for user " + user.getUsername() + 
                   " on resource " + resourceName + " with action " + actionName + ": " + hasAccess);
        
        return hasAccess;
    }
    
    @Override
    public boolean isAdmin(User user) throws DataAccessException {
        if (user == null) {
            return false;
        }
        
        try {
            int roleId = user.getRoleId();
            return roleDAO.findById(roleId)
                    .map(role -> ADMIN_ROLE.equalsIgnoreCase(role.getRoleName()) || 
                                HR_ADMIN_ROLE.equalsIgnoreCase(role.getRoleName()))
                    .orElse(false);
        } catch (Exception e) {
            logger.warning(() -> "Error checking admin status for user " + user.getUsername() + ": " + e.getMessage());
            return false;
        }
    }   
}