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
import java.util.Set;
import java.util.logging.Logger;

/**
 * This provides role based access control and permission checking functionality.
 * Optimized for login functionality with streamlined role operations.
 * 
 * @author Harvey
 */
public class AuthorizerImpl implements Authorizer {
    private static final Logger logger = Logger.getLogger(AuthorizerImpl.class.getName());
    
    private final PermissionDAO permissionDAO;
    private final RoleDAO roleDAO;
    
    // Role constants for better maintainability
    private static final Set<Integer> ADMIN_ROLE_IDS = Set.of(1, 2, 3, 4);
    private static final int NON_ADMIN_ROLE_ID = 5;
    private static final String ADMIN_ROLE = "admin";
    
    /**
     * Constructor initializes required DAOs
     */
    public AuthorizerImpl() {
        this.permissionDAO = new PermissionDAOImpl();
        this.roleDAO = new RoleDAOImpl();
        logger.info("AuthorizerImpl initialized successfully for login functionality");
    }
    
    /**
     * Constructor with dependency injection
     * 
     * @param permissionDAO
     * @param roleDAO
     */
    public AuthorizerImpl(PermissionDAO permissionDAO, RoleDAO roleDAO) {
        this.permissionDAO = permissionDAO;
        this.roleDAO = roleDAO;
        logger.info("AuthorizerImpl initialized with injected dependencies");
    }
    
    @Override
    public boolean hasPermission(User user, String permissionName) throws DataAccessException {
        if (user == null || permissionName == null || permissionName.trim().isEmpty()) {
            logger.fine("Permission check failed - null user or empty permission name");
            return false;
        }
        
        try {
            // Get user's permissions based on their role using the streamlined DAO
            List<Permission> userPermissions = getUserPermissions(user);
            
            // Check if any of the user's permissions match the requested permission
            boolean hasPermission = userPermissions.stream()
                    .anyMatch(permission -> permissionName.equalsIgnoreCase(permission.getAccessName()));
            
            logger.fine(() -> "Permission check for user " + user.getUsername() + 
                       " and permission " + permissionName + ": " + hasPermission);
            
            return hasPermission;
        } catch (Exception e) {
            logger.warning(() -> "Error checking permission for user " + user.getUsername() + ": " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean hasAnyPermission(User user, List<String> permissionNames) throws DataAccessException {
        if (user == null || permissionNames == null || permissionNames.isEmpty()) {
            logger.fine("Any permission check failed - null user or empty permission list");
            return false;
        }
        
        try {
            // Check if user has any of the specified permissions
            for (String permissionName : permissionNames) {
                if (hasPermission(user, permissionName)) {
                    logger.fine(() -> "User " + user.getUsername() + " has permission: " + permissionName);
                    return true;
                }
            }
            
            logger.fine(() -> "User " + user.getUsername() + " has none of the required permissions");
            return false;
        } catch (Exception e) {
            logger.warning(() -> "Error checking any permission for user " + user.getUsername() + ": " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean hasAllPermissions(User user, List<String> permissionNames) throws DataAccessException {
        if (user == null || permissionNames == null || permissionNames.isEmpty()) {
            logger.fine("All permissions check failed - null user or empty permission list");
            return false;
        }
        
        try {
            // Check if user has all of the specified permissions
            for (String permissionName : permissionNames) {
                if (!hasPermission(user, permissionName)) {
                    logger.fine(() -> "User " + user.getUsername() + " missing permission: " + permissionName);
                    return false;
                }
            }
            
            logger.fine(() -> "User " + user.getUsername() + " has all required permissions");
            return true;
        } catch (Exception e) {
            logger.warning(() -> "Error checking all permissions for user " + user.getUsername() + ": " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public List<Permission> getUserPermissions(User user) throws DataAccessException {
        if (user == null) {
            logger.fine("Cannot get permissions for null user");
            return List.of(); // Return empty list
        }
        
        try {
            int roleId = user.getRoleId();
            // Use the streamlined DAO method to get permissions by role
            List<Permission> permissions = permissionDAO.findByRole(roleId);
            
            logger.fine(() -> "Retrieved " + permissions.size() + " permissions for user " + user.getUsername() + " (role ID: " + roleId + ")");
            
            return permissions;
        } catch (Exception e) {
            logger.warning(() -> "Error getting permissions for user " + user.getUsername() + ": " + e.getMessage());
            return List.of();
        }
    }
    
    @Override
    public boolean hasResourceAccess(User user, String resourceName, String actionName) throws DataAccessException {
        if (user == null || resourceName == null || actionName == null) {
            logger.fine("Resource access check failed - null parameters");
            return false;
        }
        
        try {
            // Get user's permissions
            List<Permission> userPermissions = getUserPermissions(user);
            
            // Check against the access name which should contain resource+action info
            boolean hasAccess = userPermissions.stream()
                    .anyMatch(permission -> 
                        permission.getAccessName() != null && 
                        permission.getAccessName().toLowerCase().contains(resourceName.toLowerCase()) &&
                        permission.getAccessName().toLowerCase().contains(actionName.toLowerCase()));
            
            logger.fine(() -> "Resource access check for user " + user.getUsername() + 
                       " on resource " + resourceName + " with action " + actionName + ": " + hasAccess);
            
            return hasAccess;
        } catch (Exception e) {
            logger.warning(() -> "Error checking resource access for user " + user.getUsername() + ": " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean isAdmin(User user) throws DataAccessException {
        if (user == null) {
            logger.fine("Cannot check admin status for null user");
            return false;
        }
        
        try {
            int roleId = user.getRoleId();
            // Check both role name and role ID for comprehensive admin detection
            return roleDAO.findById(roleId)
                    .map(role -> ADMIN_ROLE.equalsIgnoreCase(role.getRoleName()) || 
                                ADMIN_ROLE_IDS.contains(roleId))
                    .orElse(ADMIN_ROLE_IDS.contains(roleId));
        } catch (Exception e) {
            logger.warning(() -> "Error checking admin status for user " + user.getUsername() + ": " + e.getMessage());
            // Fallback to role ID check if database lookup fails
            return ADMIN_ROLE_IDS.contains(user.getRoleId());
        }
    }
    
    @Override
    public boolean isAdminRole(User user) throws DataAccessException {
        if (user == null) {
            logger.fine("Cannot check admin role for null user");
            return false;
        }
        
        int roleId = user.getRoleId();
        int employeeId = user.getEmployeeId();
        String username = user.getUsername();
        
        logger.info(() -> String.format("Checking admin role for user: %s, employeeId: %d, roleId: %d", 
                                      username, employeeId, roleId));
        
        // Check if user has admin role ID (roles 1, 2, 3, or 4)
        boolean hasAdminRoleId = ADMIN_ROLE_IDS.contains(roleId);
        
        // Additional validation using RoleDAO for role name verification
        try {
            return roleDAO.findById(roleId)
                    .map(role -> {
                        boolean isAdminByName = ADMIN_ROLE.equalsIgnoreCase(role.getRoleName());
                        boolean isAdminByIdSet = ADMIN_ROLE_IDS.contains(roleId);
                        
                        logger.info(() -> String.format("Admin check result for %s: %s (roleId: %d, roleName: %s)", 
                                                      username, isAdminByName || isAdminByIdSet, roleId, role.getRoleName()));
                        
                        return isAdminByName || isAdminByIdSet;
                    })
                    .orElse(hasAdminRoleId);
        } catch (Exception e) {
            logger.warning(() -> "Error validating admin role via RoleDAO for user " + username + ": " + e.getMessage());
            // Fallback to role ID check
            logger.info(() -> String.format("Fallback admin check result for %s: %s (roleId: %d)", 
                                          username, hasAdminRoleId, roleId));
            return hasAdminRoleId;
        }
    }
    
    @Override
    public boolean isNonAdminRole(User user) throws DataAccessException {
        if (user == null) {
            logger.fine("Cannot check non-admin role for null user");
            return false;
        }
        
        int roleId = user.getRoleId();
        int employeeId = user.getEmployeeId();
        String username = user.getUsername();
        
        logger.info(() -> String.format("Checking NonAdmin role for user: %s, employeeId: %d, roleId: %d", 
                                      username, employeeId, roleId));
        
        // Check if user has non-admin role ID (only role 5)
        boolean hasNonAdminRoleId = (roleId == NON_ADMIN_ROLE_ID);
        
        // Additional validation using RoleDAO for role name verification
        try {
            return roleDAO.findById(roleId)
                    .map(role -> {
                        boolean isNonAdminById = (roleId == NON_ADMIN_ROLE_ID);
                        
                        logger.info(() -> String.format("NonAdmin check result for %s: %s (roleId: %d, roleName: %s)", 
                                                      username, isNonAdminById, roleId, role.getRoleName()));
                        
                        return isNonAdminById;
                    })
                    .orElse(hasNonAdminRoleId);
        } catch (Exception e) {
            logger.warning(() -> "Error validating non-admin role via RoleDAO for user " + username + ": " + e.getMessage());
            // Fallback to role ID check
            logger.info(() -> String.format("Fallback NonAdmin check result for %s: %s (roleId: %d)", 
                                          username, hasNonAdminRoleId, roleId));
            return hasNonAdminRoleId;
        }
    }
}