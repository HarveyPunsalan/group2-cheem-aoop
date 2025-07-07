/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.motorph.usermanagement.dao;

import com.motorph.usermanagement.model.Permission;
import com.motorph.usermanagement.exception.PermissionNotFoundException;
import com.motorph.usermanagement.exception.DuplicatePermissionException;
import com.motorph.usermanagement.exception.DataAccessException;
import java.util.List;
import java.util.Optional;

/**
 * Data Access Object interface for Permission entity operations.
 * Defines the contract for permission related database operations.
 *
 * @author Harvey
 */
public interface PermissionDAO {
    /**
     * Creates a new permission record in the database.
     * 
     * @param permission Permission object containing the data to be inserted
     * @return Generated permission ID from database
     * @throws DuplicatePermissionException if permission already exists
     * @throws DataAccessException if database operation fails
     */
    int createPermission(Permission permission) throws DuplicatePermissionException, DataAccessException;
    
    /**
     * Retrieves a permission by its unique database ID.
     * 
     * @param permissionId Primary key to search for
     * @return Optional containing Permission if found, empty if not found
     * @throws DataAccessException if database operation fails
     */
    Optional<Permission> findById(int permissionId) throws DataAccessException;
    
    /**
     * Retrieves a permission by its unique name.
     * 
     * @param permissionName Permission name to search for
     * @return Optional containing Permission if found, empty if not found
     * @throws DataAccessException if database operation fails
     */
    Optional<Permission> findByName(String permissionName) throws DataAccessException;
    
    /**
     * Retrieves all permissions from the database.
     * 
     * @return List of all Permission objects
     * @throws DataAccessException if database operation fails
     */
    List<Permission> findAll() throws DataAccessException;
    
    /**
     * Retrieves permissions by category ID.
     * 
     * @param categoryId Category ID to filter permissions
     * @return List of Permission objects for the specified category
     * @throws DataAccessException if database operation fails
     */
    List<Permission> findByCategory(int categoryId) throws DataAccessException;
    
    /**
     * Retrieves only active permissions from the database.
     * 
     * @return List of active Permission objects
     * @throws DataAccessException if database operation fails
     */
    List<Permission> findActivePermissions() throws DataAccessException;
    
    /**
     * Updates an existing permission record in the database.
     * 
     * @param permission Permission object with updated information
     * @return true if update was successful, false if no rows affected
     * @throws PermissionNotFoundException if permission doesn't exist
     * @throws DataAccessException if database operation fails
     */
    boolean updatePermission(Permission permission) throws PermissionNotFoundException, DataAccessException;
    
    /**
     * Activates or deactivates a permission.
     * 
     * @param permissionId Permission's database ID
     * @param isActive Status to set (true = active, false = inactive)
     * @return true if activation/deactivation was successful
     * @throws PermissionNotFoundException if permission doesn't exist
     * @throws DataAccessException if database operation fails
     */
    boolean setPermissionStatus(int permissionId, boolean isActive) throws PermissionNotFoundException, DataAccessException;
    
    /**
     * Deletes a permission from the database.
     * Should check for permission dependencies before deletion.
     * 
     * @param permissionId Permission's database ID
     * @return true if deletion was successful
     * @throws PermissionNotFoundException if permission doesn't exist
     * @throws DataAccessException if database operation fails
     */
    boolean deletePermission(int permissionId) throws PermissionNotFoundException, DataAccessException;
    
    /**
     * Checks if a permission name already exists in the database.
     * 
     * @param permissionName Permission name to check
     * @return true if permission name exists, false otherwise
     * @throws DataAccessException if database operation fails
     */
    boolean permissionNameExists(String permissionName) throws DataAccessException;
    
    /**
     * Counts the total number of permissions in the system.
     * 
     * @return Total permission count
     * @throws DataAccessException if database operation fails
     */
    int getPermissionCount() throws DataAccessException;
    
    /**
     * Counts the number of active permissions in the system.
     * 
     * @return Active permission count
     * @throws DataAccessException if database operation fails
     */
    int getActivePermissionCount() throws DataAccessException;
    
    // NEW METHODS - Add these three missing methods:
    
    /**
     * Assigns a permission to a role by creating a record in role_access table.
     * 
     * @param roleId Role's database ID 
     * @param permissionId Permission's database ID (access_id)
     * @return true if assignment was successful
     * @throws DataAccessException if database operation fails
     */
    boolean assignToRole(int roleId, int permissionId) throws DataAccessException;
    
    /**
     * Removes a permission from a role by deleting record from role_access table.
     * 
     * @param roleId Role's database ID
     * @param permissionId Permission's database ID (access_id) 
     * @return true if removal was successful
     * @throws DataAccessException if database operation fails
     */
    boolean removeFromRole(int roleId, int permissionId) throws DataAccessException;
    
    /**
     * Retrieves all permissions assigned to a specific role.
     * 
     * @param roleId Role's database ID
     * @return List of Permission objects assigned to the role
     * @throws DataAccessException if database operation fails
     */
    List<Permission> findByRole(int roleId) throws DataAccessException;
}
