/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.motorph.usermanagement.service;

import com.motorph.usermanagement.model.Permission;
import com.motorph.usermanagement.exception.PermissionNotFoundException;
import com.motorph.usermanagement.exception.DuplicatePermissionException;
import com.motorph.usermanagement.exception.DataAccessException;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for permission related business operations.
 * Defines the contract for permission management functionality.
 * @author Harvey 
 */
public interface PermissionService {
    /**
     * Creates a new permission in the system.
     * 
     * @param permission Permission object containing permission details
     * @return Generated permission ID
     * @throws DuplicatePermissionException if permission name already exists
     * @throws DataAccessException if database operation fails
     */
    int createPermission(Permission permission) throws DuplicatePermissionException, DataAccessException;
    
    /**
     * Retrieves a permission by its database ID.
     * 
     * @param permissionId Permission's database ID
     * @return Optional containing Permission if found
     * @throws DataAccessException if database operation fails
     */
    Optional<Permission> getPermissionById(int permissionId) throws DataAccessException;
    
    /**
     * Retrieves a permission by its name.
     * 
     * @param permissionName Permission name to search for
     * @return Optional containing Permission if found
     * @throws DataAccessException if database operation fails
     */
    Optional<Permission> getPermissionByName(String permissionName) throws DataAccessException;
    
    /**
     * Retrieves all permissions in the system.
     * 
     * @return List of all Permission objects
     * @throws DataAccessException if database operation fails
     */
    List<Permission> getAllPermissions() throws DataAccessException;
    
    /**
     * Retrieves permissions by category.
     * 
     * @param categoryId Category ID to filter by
     * @return List of Permission objects for the category
     * @throws DataAccessException if database operation fails
     */
    List<Permission> getPermissionsByCategory(int categoryId) throws DataAccessException;
    
    /**
     * Retrieves only active permissions.
     * 
     * @return List of active Permission objects
     * @throws DataAccessException if database operation fails
     */
    List<Permission> getActivePermissions() throws DataAccessException;
    
    /**
     * Updates an existing permission.
     * 
     * @param permission Permission object with updated information
     * @return true if update successful
     * @throws PermissionNotFoundException if permission doesn't exist
     * @throws DataAccessException if database operation fails
     */
    boolean updatePermission(Permission permission) throws PermissionNotFoundException, DataAccessException;
    
    /**
     * Activates or deactivates a permission.
     * 
     * @param permissionId Permission's database ID
     * @param isActive Status to set
     * @return true if status change successful
     * @throws PermissionNotFoundException if permission doesn't exist
     * @throws DataAccessException if database operation fails
     */
    boolean setPermissionStatus(int permissionId, boolean isActive) throws PermissionNotFoundException, DataAccessException;
    
    /**
     * Deletes a permission from the system.
     * 
     * @param permissionId Permission's database ID
     * @return true if deletion successful
     * @throws PermissionNotFoundException if permission doesn't exist
     * @throws DataAccessException if database operation fails
     */
    boolean deletePermission(int permissionId) throws PermissionNotFoundException, DataAccessException;
    
    /**
     * Checks if a permission name is available.
     * 
     * @param permissionName Permission name to check
     * @return true if permission name is available
     * @throws DataAccessException if database operation fails
     */
    boolean isPermissionNameAvailable(String permissionName) throws DataAccessException;
    
    /**
     * Gets total count of permissions.
     * 
     * @return Total permission count
     * @throws DataAccessException if database operation fails
     */
    int getPermissionCount() throws DataAccessException;
    
    /**
     * Gets count of active permissions.
     * 
     * @return Active permission count
     * @
     * @throws DataAccessException if database operation fails
     */
    int getActivePermissionCount() throws DataAccessException;
    
    /**
     * Assigns a permission to a specific role.
     * Creates the role-permission relationship.
     * 
     * @param roleId Role's database ID
     * @param permissionId Permission's database ID
     * @return true if assignment successful
     * @throws DataAccessException if database operation fails
     */
    boolean assignPermissionToRole(int roleId, int permissionId) throws DataAccessException;
    
    /**
     * Removes a permission from a specific role.
     * 
     * @param roleId Role's database ID
     * @param permissionId Permission's database ID
     * @return true if removal successful
     * @throws DataAccessException if database operation fails
     */
    boolean removePermissionFromRole(int roleId, int permissionId) throws DataAccessException;
    
    /**
     * Retrieves all permissions assigned to a specific role.
     * 
     * @param roleId Role's database ID
     * @return List of Permission objects assigned to the role
     * @throws DataAccessException if database operation fails
     */
    List<Permission> getPermissionsForRole(int roleId) throws DataAccessException;
}

