/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.motorph.usermanagement.dao;

import com.motorph.usermanagement.model.Role;
import com.motorph.usermanagement.exception.DataAccessException;
import java.util.Optional;

/**
 * Simplified RoleDAO interface for authentication and authorization purposes only.
 * Focused on role lookup operations needed by AuthorizerImpl.
 * 
 * @author Harvey
 */
public interface RoleDAO {
    /**
     * Retrieves a role by its unique database ID.
     * This method is essential for AuthorizerImpl to validate user roles.
     * 
     * @param roleId Primary key to search for
     * @return Optional containing Role if found, empty if not found
     * @throws DataAccessException if database operation fails
     */
    Optional<Role> findById(int roleId) throws DataAccessException;
}