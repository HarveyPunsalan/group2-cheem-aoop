/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.dao;

import com.motorph.usermanagement.model.Role;
import com.motorph.usermanagement.model.Permission;
import com.motorph.usermanagement.exception.RoleNotFoundException;
import com.motorph.usermanagement.exception.DuplicateRoleException;
import com.motorph.usermanagement.exception.DataAccessException;
import com.motorph.database.connection.DatabaseService;
import com.motorph.database.execution.ResultSetMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author Harvey
 */
public class RoleDAOImpl implements RoleDAO {
    private static final Logger logger = Logger.getLogger(RoleDAOImpl.class.getName());
    private final Connection connection;
    
    // SQL queries aligned in database schema
    private static final String INSERT_ROLE_SQL = 
        "INSERT INTO user_role (role_name, role_description) VALUES (?, ?)";
    
    private static final String SELECT_ROLE_BY_ID_SQL = 
        "SELECT role_id, role_name, role_description FROM user_role WHERE role_id = ?";
    
    private static final String SELECT_ROLE_BY_NAME_SQL = 
        "SELECT role_id, role_name, role_description FROM user_role WHERE role_name = ?";
    
    private static final String SELECT_ALL_ROLES_SQL = 
        "SELECT role_id, role_name, role_description FROM user_role ORDER BY role_name";
    
    private static final String UPDATE_ROLE_SQL = 
        "UPDATE user_role SET role_name = ?, role_description = ? WHERE role_id = ?";
    
    private static final String DELETE_ROLE_SQL = 
        "DELETE FROM user_role WHERE role_id = ?";
    
    private static final String CHECK_ROLE_NAME_EXISTS_SQL = 
        "SELECT COUNT(*) FROM user_role WHERE role_name = ?";
    
    private static final String COUNT_ROLES_SQL = 
        "SELECT COUNT(*) FROM user_role";
    
    // Query to count users assigned to a role matches to system_user table
    private static final String COUNT_USERS_FOR_ROLE_SQL = 
        "SELECT COUNT(*) FROM system_user WHERE role_id = ? AND is_active = 1";
    
    // Query to search roles by name pattern
    private static final String SEARCH_ROLES_BY_NAME_SQL = 
        "SELECT role_id, role_name, role_description FROM user_role " +
        "WHERE role_name LIKE ? ORDER BY role_name";
    
    // Query to get permissions for a role joins role_access with user_access
    private static final String SELECT_PERMISSIONS_FOR_ROLE_SQL = 
        "SELECT ua.access_id, ua.access_name, ua.access_category_id, ua.resource_id, ua.action_id, " +
        "ua.requires_approval, ua.is_active " +
        "FROM role_access ra " +
        "JOIN user_access ua ON ra.access_id = ua.access_id " +
        "WHERE ra.role_id = ? AND ua.is_active = 1";
    
    // Query to assign permission to role
    private static final String INSERT_ROLE_ACCESS_SQL = 
        "INSERT INTO role_access (role_id, access_id) VALUES (?, ?) " +
        "ON DUPLICATE KEY UPDATE role_id = role_id"; // Prevent duplicates
    
    // Query to remove permission from role
    private static final String DELETE_ROLE_ACCESS_SQL = 
        "DELETE FROM role_access WHERE role_id = ? AND access_id = ?";
    
    /**
     * ResultSetMapper for converting database rows to Role objects.
     */
    private static final ResultSetMapper<Role> ROLE_MAPPER = resultSet -> {
        Role role = new Role();
        role.setRoleId(resultSet.getInt("role_id"));
        role.setRoleName(resultSet.getString("role_name"));
        role.setRoleDescription(resultSet.getString("role_description"));
        return role;
    };
    
    /**
     * ResultSetMapper for converting permission query results to Permission objects.
     */
    private static final ResultSetMapper<Permission> PERMISSION_MAPPER = resultSet -> {
        Permission permission = new Permission();
        permission.setAccessId(resultSet.getInt("access_id"));
        permission.setAccessName(resultSet.getString("access_name"));
        permission.setAccessCategoryId(resultSet.getInt("access_category_id"));
        permission.setResourceId(resultSet.getInt("resource_id"));
        permission.setActionId(resultSet.getInt("action_id"));
        permission.setRequiresApproval(resultSet.getBoolean("requires_approval"));
        permission.setActive(resultSet.getBoolean("is_active"));
        return permission;
    };
    
    /**
     * Constructor initializes database connection.
     */
    public RoleDAOImpl() {
        try {
            this.connection = DatabaseService.connectToMotorPH();
            logger.info("RoleDAOImpl initialized successfully with database connection");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to initialize RoleDAOImpl", e);
            throw new RuntimeException("Unable to establish database connection for RoleDAO", e);
        }
    }
    
    /**
     * Constructor with external connection (useful for testing)
     * 
     * @param connection the database connection to be used by this DAO
     */
    public RoleDAOImpl(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public int createRole(Role role) throws DuplicateRoleException, DataAccessException {
        // Check if role name already exists
        if (roleNameExists(role.getRoleName())) {
            throw new DuplicateRoleException("Role name '" + role.getRoleName() + "' already exists in the system");
        }
        
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_ROLE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, role.getRoleName());
            stmt.setString(2, role.getRoleDescription());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new DataAccessException("Failed to create role - no rows affected");
            }
            
            // Retrieve the generated role ID
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedRoleId = generatedKeys.getInt(1);
                    role.setRoleId(generatedRoleId);
                    logger.info(() -> "Successfully created role with ID: " + generatedRoleId + ", name: " + role.getRoleName());
                    return generatedRoleId;
                } else {
                    throw new DataAccessException("Failed to retrieve generated role ID after insertion");
                }
            }
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while creating role: " + role.getRoleName(), e);
            throw new DataAccessException("Failed to create role due to database error", e);
        }
    }
    
    @Override
    public Optional<Role> findById(int roleId) throws DataAccessException {
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_ROLE_BY_ID_SQL)) {
            stmt.setInt(1, roleId);
            
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    Role role = ROLE_MAPPER.map(resultSet);
                    logger.fine(() -> "Found role by ID: " + roleId);
                    return Optional.of(role);
                } else {
                    logger.fine(() -> "No role found with ID: " + roleId);
                    return Optional.empty();
                }
            }
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while finding role by ID: " + roleId, e);
            throw new DataAccessException("Failed to find role by ID", e);
        }
    }
    
    @Override
    public Optional<Role> findByName(String roleName) throws DataAccessException {
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_ROLE_BY_NAME_SQL)) {
            stmt.setString(1, roleName);
            
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    Role role = ROLE_MAPPER.map(resultSet);
                    logger.fine(() -> "Found role by name: " + roleName);
                    return Optional.of(role);
                } else {
                    logger.fine(() -> "No role found with name: " + roleName);
                    return Optional.empty();
                }
            }
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while finding role by name: " + roleName, e);
            throw new DataAccessException("Failed to find role by name", e);
        }
    }
    
    @Override
    public List<Role> findAll() throws DataAccessException {
        List<Role> roles = new ArrayList<>();
        
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_ROLES_SQL);
             ResultSet resultSet = stmt.executeQuery()) {
            
            while (resultSet.next()) {
                roles.add(ROLE_MAPPER.map(resultSet));
            }
            
            logger.info(() -> "Retrieved " + roles.size() + " roles from database");
            return roles;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while retrieving all roles", e);
            throw new DataAccessException("Failed to retrieve all roles", e);
        }
    }
    
    @Override
    public boolean updateRole(Role role) throws RoleNotFoundException, DataAccessException {
        // First verify the role exists
        if (!findById(role.getRoleId()).isPresent()) {
            throw new RoleNotFoundException("Role with ID " + role.getRoleId() + " not found");
        }
        
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_ROLE_SQL)) {
            stmt.setString(1, role.getRoleName());
            stmt.setString(2, role.getRoleDescription());
            stmt.setInt(3, role.getRoleId());
            
            int affectedRows = stmt.executeUpdate();
            boolean updateSuccessful = affectedRows > 0;
            
            if (updateSuccessful) {
                logger.info(() -> "Successfully updated role with ID: " + role.getRoleId());
            } else {
                logger.warning(() -> "No rows affected when updating role with ID: " + role.getRoleId());
            }
            
            return updateSuccessful;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while updating role: " + role.getRoleId(), e);
            throw new DataAccessException("Failed to update role", e);
        }
    }
    
    @Override
    public boolean deleteRole(int roleId) throws RoleNotFoundException, DataAccessException {
        // First verify the role exists
        if (!findById(roleId).isPresent()) {
            throw new RoleNotFoundException("Role with ID " + roleId + " not found");
        }
        
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_ROLE_SQL)) {
            stmt.setInt(1, roleId);
            
            int affectedRows = stmt.executeUpdate();
            boolean deletionSuccessful = affectedRows > 0;
            
            if (deletionSuccessful) {
                logger.info(() -> "Successfully deleted role with ID: " + roleId);
            } else {
                logger.warning(() -> "No rows affected when deleting role with ID: " + roleId);
            }
            
            return deletionSuccessful;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while deleting role: " + roleId, e);
            throw new DataAccessException("Failed to delete role", e);
        }
    }
    
    @Override
    public boolean roleNameExists(String roleName) throws DataAccessException {
        try (PreparedStatement stmt = connection.prepareStatement(CHECK_ROLE_NAME_EXISTS_SQL)) {
            stmt.setString(1, roleName);
            
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    boolean exists = resultSet.getInt(1) > 0;
                    logger.fine(() -> "Role name existence check for '" + roleName + "': " + exists);
                    return exists;
                }
            }
            
            return false;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while checking role name existence: " + roleName, e);
            throw new DataAccessException("Failed to check role name existence", e);
        }
    }
    
    @Override
    public int getRoleCount() throws DataAccessException {
        try (PreparedStatement stmt = connection.prepareStatement(COUNT_ROLES_SQL);
             ResultSet resultSet = stmt.executeQuery()) {
            
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                logger.fine(() -> "Total role count: " + count);
                return count;
            }
            
            return 0;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while counting roles", e);
            throw new DataAccessException("Failed to count roles", e);
        }
    }
    
    @Override
    public int getUserCountForRole(int roleId) throws DataAccessException {
        try (PreparedStatement stmt = connection.prepareStatement(COUNT_USERS_FOR_ROLE_SQL)) {
            stmt.setInt(1, roleId);
            
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    logger.fine(() -> "User count for role " + roleId + ": " + count);
                    return count;
                }
            }
            
            return 0;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while counting users for role: " + roleId, e);
            throw new DataAccessException("Failed to count users for role", e);
        }
    }
    
    @Override
    public List<Role> searchRolesByName(String searchTerm) throws DataAccessException {
        List<Role> roles = new ArrayList<>();
        
        try (PreparedStatement stmt = connection.prepareStatement(SEARCH_ROLES_BY_NAME_SQL)) {
            stmt.setString(1, "%" + searchTerm + "%");
            
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    roles.add(ROLE_MAPPER.map(resultSet));
                }
            }
            
            logger.info(() -> "Found " + roles.size() + " roles matching search term: " + searchTerm);
            return roles;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while searching roles by name: " + searchTerm, e);
            throw new DataAccessException("Failed to search roles by name", e);
        }
    }
    
    @Override
    public List<Permission> getPermissionsForRole(int roleId) throws DataAccessException {
        List<Permission> permissions = new ArrayList<>();
        
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_PERMISSIONS_FOR_ROLE_SQL)) {
            stmt.setInt(1, roleId);
            
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    permissions.add(PERMISSION_MAPPER.map(resultSet));
                }
            }
            
            logger.info(() -> "Retrieved " + permissions.size() + " permissions for role ID: " + roleId);
            return permissions;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while getting permissions for role: " + roleId, e);
            throw new DataAccessException("Failed to get permissions for role", e);
        }
    }
    
    @Override
    public boolean assignPermissionsToRole(int roleId, List<Integer> accessIds) throws DataAccessException {
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_ROLE_ACCESS_SQL)) {
            connection.setAutoCommit(false);
            
            for (Integer accessId : accessIds) {
                stmt.setInt(1, roleId);
                stmt.setInt(2, accessId);
                stmt.addBatch();
            }
            
            int[] results = stmt.executeBatch();
            connection.commit();
            
            logger.info(() -> "Assigned " + accessIds.size() + " permissions to role ID: " + roleId);
            return results.length == accessIds.size();
            
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                logger.log(Level.SEVERE, "Failed to rollback permission assignment", rollbackEx);
            }
            logger.log(Level.SEVERE, "Database error while assigning permissions to role: " + roleId, e);
            throw new DataAccessException("Failed to assign permissions to role", e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.log(Level.WARNING, "Failed to reset auto-commit", e);
            }
        }
    }
    
    @Override
    public boolean removePermissionsFromRole(int roleId, List<Integer> accessIds) throws DataAccessException {
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_ROLE_ACCESS_SQL)) {
            connection.setAutoCommit(false);
            
            for (Integer accessId : accessIds) {
                stmt.setInt(1, roleId);
                stmt.setInt(2, accessId);
                stmt.addBatch();
            }
            
            int[] results = stmt.executeBatch();
            connection.commit();
            
            logger.info(() -> "Removed " + accessIds.size() + " permissions from role ID: " + roleId);
            return results.length == accessIds.size();
            
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                logger.log(Level.SEVERE, "Failed to rollback permission removal", rollbackEx);
            }
            logger.log(Level.SEVERE, "Database error while removing permissions from role: " + roleId, e);
            throw new DataAccessException("Failed to remove permissions from role", e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.log(Level.WARNING, "Failed to reset auto-commit", e);
            }
        }
    }
}
