/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.motorph.usermanagement.dao;

import com.motorph.usermanagement.model.Role;
import com.motorph.usermanagement.model.Permission;
import com.motorph.usermanagement.exception.RoleNotFoundException;
import com.motorph.usermanagement.exception.DuplicateRoleException;
import com.motorph.usermanagement.exception.DataAccessException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Harvey
 */
public interface RoleDAO {
    /**
     * Creates a new role record in the database.
     * 
     * @param role Role object containing the data to be inserted
     * @return Generated role ID from database
     * @throws DuplicateRoleException if role name already exists
     * @throws DataAccessException if database operation fails
     */
    int createRole(Role role) throws DuplicateRoleException, DataAccessException;
    
    /**
     * Retrieves a role by its unique database ID.
     * 
     * @param roleId Primary key to search for
     * @return Optional containing Role if found, empty if not found
     * @throws DataAccessException if database operation fails
     */
    Optional<Role> findById(int roleId) throws DataAccessException;
    
    /**
     * Retrieves a role by its unique name.
     * 
     * @param roleName Role name to search for
     * @return Optional containing Role if found, empty if not found
     * @throws DataAccessException if database operation fails
     */
    Optional<Role> findByName(String roleName) throws DataAccessException;
    
    /**
     * Retrieves all roles from the database.
     * 
     * @return List of all Role objects
     * @throws DataAccessException if database operation fails
     */
    List<Role> findAll() throws DataAccessException;
    
    /**
     * Updates an existing role record in the database.
     * 
     * @param role Role object with updated information
     * @return true if update was successful, false if no rows affected
     * @throws RoleNotFoundException if role doesn't exist
     * @throws DataAccessException if database operation fails
     */
    boolean updateRole(Role role) throws RoleNotFoundException, DataAccessException;
    
    /**
     * Deletes a role from the database.
     * Should check for role dependencies before deletion.
     * 
     * @param roleId Role's database ID
     * @return true if deletion was successful
     * @throws RoleNotFoundException if role doesn't exist
     * @throws DataAccessException if database operation fails
     */
    boolean deleteRole(int roleId) throws RoleNotFoundException, DataAccessException;
    
    /**
     * Checks if a role name already exists in the database.
     * 
     * @param roleName Role name to check
     * @return true if role name exists, false otherwise
     * @throws DataAccessException if database operation fails
     */
    boolean roleNameExists(String roleName) throws DataAccessException;
    
    /**
     * Counts the total number of roles in the system.
     * 
     * @return Total role count
     * @throws DataAccessException if database operation fails
     */
    int getRoleCount() throws DataAccessException;
    
    /**
     * Gets the count of users assigned to a specific role.
     * This method queries the system_user table to count users with the given role_id.
     * 
     * @param roleId Role ID to count users for
     * @return Number of users assigned to this role
     * @throws DataAccessException if database operation fails
     */
    int getUserCountForRole(int roleId) throws DataAccessException;
    
    /**
     * Searches for roles by name pattern.
     * 
     * @param searchTerm Search term to match against role names
     * @return List of roles matching the search criteria
     * @throws DataAccessException if database operation fails
     */
    List<Role> searchRolesByName(String searchTerm) throws DataAccessException;
    
    /**
     * Retrieves permissions associated with a specific role.
     * This joins role_access with user_access to get full permission details.
     * 
     * @param roleId Role's database ID
     * @return List of Permission objects for the role
     * @throws DataAccessException if database operation fails
     */
    List<Permission> getPermissionsForRole(int roleId) throws DataAccessException;
    
    /**
     * Assigns permissions to a role based on role_access table structure.
     * 
     * @param roleId Role ID to assign permissions to
     * @param accessIds List of access IDs to assign
     * @return true if assignment successful
     * @throws DataAccessException if database operation fails
     */
    boolean assignPermissionsToRole(int roleId, List<Integer> accessIds) throws DataAccessException;
    
    /**
     * Removes permissions from a role.
     * 
     * @param roleId Role ID to remove permissions from
     * @param accessIds List of access IDs to remove
     * @return true if removal successful
     * @throws DataAccessException if database operation fails
     */
    boolean removePermissionsFromRole(int roleId, List<Integer> accessIds) throws DataAccessException;
}
