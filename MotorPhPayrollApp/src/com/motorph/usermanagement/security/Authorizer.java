/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.motorph.usermanagement.security;

import com.motorph.usermanagement.model.User;
import com.motorph.usermanagement.model.Permission;
import com.motorph.usermanagement.exception.DataAccessException;
import java.util.List;
/**
 * This will handles permission checking and role-based access control.
 * 
 * @author Harvey 
 */
public interface Authorizer {
    /**
     * Checks if a user has a specific permission.
     * 
     * @param user User to check permissions for
     * @param permissionName Name of the permission to check
     * @return true if user has the permission
     * @throws DataAccessException if database operation fails
     */
    boolean hasPermission(User user, String permissionName) throws DataAccessException;
    
    /**
     * Checks if a user has any of the specified permissions.
     * 
     * @param user User to check permissions for
     * @param permissionNames List of permission names to check
     * @return true if user has at least one of the permissions
     * @throws DataAccessException if database operation fails
     */
    boolean hasAnyPermission(User user, List<String> permissionNames) throws DataAccessException;
    
    /**
     * Checks if a user has all of the specified permissions.
     * 
     * @param user User to check permissions for
     * @param permissionNames List of permission names to check
     * @return true if user has all the permissions
     * @throws DataAccessException if database operation fails
     */
    boolean hasAllPermissions(User user, List<String> permissionNames) throws DataAccessException;
    
    /**
     * Retrieves all permissions for a specific user.
     * 
     * @param user User to get permissions for
     * @return List of Permission objects the user has
     * @throws DataAccessException if database operation fails
     */
    List<Permission> getUserPermissions(User user) throws DataAccessException;
    
    /**
     * Checks if a user has access to a specific resource with a given action.
     * 
     * @param user User to check access for
     * @param resourceName Name of the resource
     * @param actionName Name of the action
     * @return true if user has access
     * @throws DataAccessException if database operation fails
     */
    boolean hasResourceAccess(User user, String resourceName, String actionName) throws DataAccessException;
    
    /**
     * Checks if a user is an administrator.
     * 
     * @param user User to check
     * @return true if user has admin role
     * @throws DataAccessException if database operation fails
     */
    boolean isAdmin(User user) throws DataAccessException;
}
