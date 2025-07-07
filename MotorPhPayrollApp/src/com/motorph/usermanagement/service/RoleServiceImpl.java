/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.service;

import com.motorph.usermanagement.model.Role;
import com.motorph.usermanagement.model.Permission;
import com.motorph.usermanagement.dao.RoleDAO;
import com.motorph.usermanagement.dao.RoleDAOImpl;
import com.motorph.usermanagement.exception.RoleNotFoundException;
import com.motorph.usermanagement.exception.DuplicateRoleException;
import com.motorph.usermanagement.exception.DataAccessException;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
/**
 *
 * @author Harvey
 */
public class RoleServiceImpl implements RoleService {
    private static final Logger logger = Logger.getLogger(RoleServiceImpl.class.getName());
    
    private final RoleDAO roleDAO;
    
    /**
     * Constructor initializes required DAOs.
     */
    public RoleServiceImpl() {
        this.roleDAO = new RoleDAOImpl();
        logger.info("RoleServiceImpl initialized successfully");
    }
    
    /**
     * Constructor 
     * @param roleDAO
     */
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }
    
    @Override
    public int createRole(Role role) throws DuplicateRoleException, DataAccessException {
        // Validate role data
        if (!isValidRoleName(role.getRoleName())) {
            throw new IllegalArgumentException("Invalid role name format. Role name must be 2-30 characters and contain only letters, numbers, spaces, and common punctuation.");
        }
        
        // Check if role name already exists
        if (roleDAO.roleNameExists(role.getRoleName())) {
            throw new DuplicateRoleException("Role name '" + role.getRoleName() + "' already exists");
        }
        
        // Create the role
        int roleId = roleDAO.createRole(role);
        logger.info(() -> "Successfully created new role with ID: " + roleId + ", name: " + role.getRoleName());
        
        return roleId;
    }
    
    @Override
    public Optional<Role> getRoleById(int roleId) throws DataAccessException {
        return roleDAO.findById(roleId);
    }
    
    @Override
    public Optional<Role> getRoleByName(String roleName) throws DataAccessException {
        if (roleName == null || roleName.trim().isEmpty()) {
            return Optional.empty();
        }
        return roleDAO.findByName(roleName.trim());
    }
    
    @Override
    public List<Role> getAllRoles() throws DataAccessException {
        return roleDAO.findAll();
    }
    
    @Override
    public boolean updateRole(Role role) throws RoleNotFoundException, DataAccessException {
        // Validate role data
        if (!isValidRoleName(role.getRoleName())) {
            throw new IllegalArgumentException("Invalid role name format");
        }
        
        // Verify role exists
        if (!roleDAO.findById(role.getRoleId()).isPresent()) {
            throw new RoleNotFoundException("Role with ID " + role.getRoleId() + " not found");
        }
        
        boolean success = roleDAO.updateRole(role);
        
        if (success) {
            logger.info(() -> "Successfully updated role with ID: " + role.getRoleId());
        }
        
        return success;
    }
    
    @Override
    public boolean deleteRole(int roleId) throws RoleNotFoundException, DataAccessException {
        // Verify role exists
        if (!roleDAO.findById(roleId).isPresent()) {
            throw new RoleNotFoundException("Role with ID " + roleId + " not found");
        }
        
        // Check if role is assigned to any users before deletion
        int userCount = getUserCountForRole(roleId);
        if (userCount > 0) {
            throw new DataAccessException("Cannot delete role - it is assigned to " + userCount + " user(s). Remove role assignments first.");
        }
        
        boolean success = roleDAO.deleteRole(roleId);
        
        if (success) {
            logger.info(() -> "Successfully deleted role with ID: " + roleId);
        }
        
        return success;
    }
    
    @Override
    public List<Permission> getPermissionsForRole(int roleId) throws DataAccessException {
        // Implementation to get permissions from role_access and user_access tables
        // This will join role_access with user_access to get full permission details
        return roleDAO.getPermissionsForRole(roleId);
    }
    
    @Override
    public boolean isRoleNameAvailable(String roleName) throws DataAccessException {
        if (roleName == null || roleName.trim().isEmpty()) {
            return false;
        }
        return !roleDAO.roleNameExists(roleName.trim());
    }
    
    @Override
    public int getRoleCount() throws DataAccessException {
        return roleDAO.getRoleCount();
    }
    
    @Override
    public int getUserCountForRole(int roleId) throws DataAccessException {
        return roleDAO.getUserCountForRole(roleId);
    }
    
    @Override
    public List<Role> searchRolesByName(String searchTerm) throws DataAccessException {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllRoles();
        }
        return roleDAO.searchRolesByName(searchTerm.trim());
    }
    
    @Override
    public boolean assignPermissionsToRole(int roleId, List<Integer> accessIds) throws RoleNotFoundException, DataAccessException {
        // Verify role exists
        if (!roleDAO.findById(roleId).isPresent()) {
            throw new RoleNotFoundException("Role with ID " + roleId + " not found");
        }
        
        return roleDAO.assignPermissionsToRole(roleId, accessIds);
    }
    
    @Override
    public boolean removePermissionsFromRole(int roleId, List<Integer> accessIds) throws RoleNotFoundException, DataAccessException {
        // Verify role exists
        if (!roleDAO.findById(roleId).isPresent()) {
            throw new RoleNotFoundException("Role with ID " + roleId + " not found");
        }
        
        return roleDAO.removePermissionsFromRole(roleId, accessIds);
    }
    
    /**
     * Validates role name format.
     * Role name must be 2-30 characters and contain only letters, numbers, spaces, and common punctuation.
     */
    private boolean isValidRoleName(String roleName) {
        if (roleName == null || roleName.trim().isEmpty()) {
            return false;
        }
        
        String trimmed = roleName.trim();
        return trimmed.length() >= 2 && 
               trimmed.length() <= 30 &&
               trimmed.matches("^[a-zA-Z0-9\\s\\-_\\.]+$");
    }
}
