/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.motorph.usermanagement.dao;

import com.motorph.usermanagement.model.Permission;
import com.motorph.usermanagement.exception.DataAccessException;
import java.util.List;

/**
 * Simplified Data Access Object interface for Permission entity operations.
 * Focused on login and authorization functionality only.
 * 
 * @author Harvey
 */
public interface PermissionDAO {
    
    /**
     * Retrieves all permissions assigned to a specific role.
     * This is the primary method used by AuthorizerImpl for authorization checks.
     * 
     * @param roleId Role's database ID
     * @return List of Permission objects assigned to the role
     * @throws DataAccessException if database operation fails
     */
    List<Permission> findByRole(int roleId) throws DataAccessException;
}