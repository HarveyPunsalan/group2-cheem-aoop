/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.dao;

import com.motorph.usermanagement.model.Permission;
import com.motorph.usermanagement.exception.PermissionNotFoundException;
import com.motorph.usermanagement.exception.DuplicatePermissionException;
import com.motorph.usermanagement.exception.DataAccessException;
import com.motorph.database.connection.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.logging.Level;
import com.motorph.database.execution.EntityMapper;

/**
 * JDBC implementation of PermissionDAO interface.
 * Handles all database operations related to Permission entity using the user_access table.
 *
 * @author harvey punsalan
 */

public class PermissionDAOImpl implements PermissionDAO {
    private static final Logger logger = Logger.getLogger(PermissionDAOImpl.class.getName());
    private final Connection connection;
    
    // SQL queries for permission operations - using user_access table
    private static final String INSERT_PERMISSION_SQL = 
        "INSERT INTO user_access (access_name, access_category_id, resource_id, action_id, requires_approval, is_active, created_at) " +
        "VALUES (?, ?, ?, ?, ?, ?, NOW())";
    
    private static final String SELECT_PERMISSION_BY_ID_SQL = 
        "SELECT access_id, access_name, access_category_id, resource_id, action_id, requires_approval, is_active, created_at " +
        "FROM user_access WHERE access_id = ?";
    
    private static final String SELECT_PERMISSION_BY_NAME_SQL = 
        "SELECT access_id, access_name, access_category_id, resource_id, action_id, requires_approval, is_active, created_at " +
        "FROM user_access WHERE access_name = ?";
    
    private static final String SELECT_ALL_PERMISSIONS_SQL = 
        "SELECT access_id, access_name, access_category_id, resource_id, action_id, requires_approval, is_active, created_at " +
        "FROM user_access ORDER BY access_name";
    
    private static final String SELECT_PERMISSIONS_BY_CATEGORY_SQL = 
        "SELECT access_id, access_name, access_category_id, resource_id, action_id, requires_approval, is_active, created_at " +
        "FROM user_access WHERE access_category_id = ? ORDER BY access_name";
    
    private static final String SELECT_ACTIVE_PERMISSIONS_SQL = 
        "SELECT access_id, access_name, access_category_id, resource_id, action_id, requires_approval, is_active, created_at " +
        "FROM user_access WHERE is_active = TRUE ORDER BY access_name";
    
    private static final String UPDATE_PERMISSION_SQL = 
        "UPDATE user_access SET access_name = ?, access_category_id = ?, resource_id = ?, action_id = ?, requires_approval = ?, is_active = ? " +
        "WHERE access_id = ?";
    
    private static final String ASSIGN_PERMISSION_TO_ROLE_SQL = 
    "INSERT INTO role_access (role_id, access_id) VALUES (?, ?)";

    private static final String REMOVE_PERMISSION_FROM_ROLE_SQL = 
    "DELETE FROM role_access WHERE role_id = ? AND access_id = ?";

    private static final String SELECT_PERMISSIONS_BY_ROLE_SQL = 
    "SELECT ua.access_id, ua.access_name, ua.access_category_id, ua.resource_id, ua.action_id, ua.requires_approval, ua.is_active, ua.created_at " +
    "FROM user_access ua " +
    "INNER JOIN role_access ra ON ua.access_id = ra.access_id " +
    "WHERE ra.role_id = ? AND ua.is_active = TRUE " +
    "ORDER BY ua.access_name";

     private static final String CHECK_ROLE_PERMISSION_EXISTS_SQL = 
    "SELECT COUNT(*) FROM role_access WHERE role_id = ? AND access_id = ?";
    
    private static final String UPDATE_PERMISSION_STATUS_SQL = 
        "UPDATE user_access SET is_active = ? WHERE access_id = ?";
    
    private static final String DELETE_PERMISSION_SQL = 
        "DELETE FROM user_access WHERE access_id = ?";
    
    private static final String CHECK_PERMISSION_NAME_EXISTS_SQL = 
        "SELECT COUNT(*) FROM user_access WHERE access_name = ?";
    
    private static final String COUNT_PERMISSIONS_SQL = 
        "SELECT COUNT(*) FROM user_access";
    
    private static final String COUNT_ACTIVE_PERMISSIONS_SQL = 
        "SELECT COUNT(*) FROM user_access WHERE is_active = TRUE";
    
    /**
     * ResultSetMapper for converting database rows to Permission objects.
     */
    private static final EntityMapper<Permission> PERMISSION_MAPPER = resultSet -> {
        Permission permission = new Permission();
        permission.setAccessId(resultSet.getInt("access_id"));
        permission.setAccessName(resultSet.getString("access_name"));
        permission.setAccessCategoryId(resultSet.getInt("access_category_id"));
        permission.setResourceId(resultSet.getInt("resource_id"));
        permission.setActionId(resultSet.getInt("action_id"));
        permission.setRequiresApproval(resultSet.getBoolean("requires_approval"));
        permission.setActive(resultSet.getBoolean("is_active"));
        
        // Handle potential null timestamp from database
        Timestamp createdAt = resultSet.getTimestamp("created_at");
        if (createdAt != null) {
            permission.setCreatedAt(createdAt);
        }
        
        return permission;
    };
    
    /**
     * Constructor initializes database connection.
     */
    public PermissionDAOImpl() {
        try {
            this.connection = DatabaseService.connectToMotorPH();
            logger.info("PermissionDAOImpl initialized successfully with database connection");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to initialize PermissionDAOImpl", e);
            throw new RuntimeException("Unable to establish database connection for PermissionDAO", e);
        }
    }
    
    /**
     * Constructor with external connection (useful for testing)
     * 
     * @param connection the database connection to be used by this DAO
     */
    public PermissionDAOImpl(Connection connection) {
        this.connection = connection;
    }
//    
    @Override
    public int createPermission(Permission permission) throws DuplicatePermissionException, DataAccessException {
        // Check if permission name already exists
        if (permissionNameExists(permission.getAccessName())) {
            throw new DuplicatePermissionException("Permission name '" + permission.getAccessName() + "' already exists in the system");
        }
        
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_PERMISSION_SQL, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, permission.getAccessName());
            stmt.setInt(2, permission.getAccessCategoryId());
            stmt.setInt(3, permission.getResourceId());
            stmt.setInt(4, permission.getActionId());
            stmt.setBoolean(5, permission.isRequiresApproval());
            stmt.setBoolean(6, permission.isActive());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new DataAccessException("Failed to create permission - no rows affected");
            }
            
            // Retrieve the generated permission ID
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedPermissionId = generatedKeys.getInt(1);
                    permission.setAccessId(generatedPermissionId);
                    logger.info(() -> "Successfully created permission with ID: " + generatedPermissionId + ", name: " + permission.getAccessName());
                    return generatedPermissionId;
                } else {
                    throw new DataAccessException("Failed to retrieve generated permission ID after insertion");
                }
            }
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while creating permission: " + permission.getAccessName(), e);
            throw new DataAccessException("Failed to create permission due to database error", e);
        }
    }
    
    @Override
    public Optional<Permission> findById(int permissionId) throws DataAccessException {
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_PERMISSION_BY_ID_SQL)) {
            stmt.setInt(1, permissionId);
            
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    Permission permission = PERMISSION_MAPPER.map(resultSet);
                    logger.fine(() -> "Found permission by ID: " + permissionId);
                    return Optional.of(permission);
                } else {
                    logger.fine(() -> "No permission found with ID: " + permissionId);
                    return Optional.empty();
                }
            }
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while finding permission by ID: " + permissionId, e);
            throw new DataAccessException("Failed to find permission by ID", e);
        }
    }
    
    @Override
    public Optional<Permission> findByName(String permissionName) throws DataAccessException {
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_PERMISSION_BY_NAME_SQL)) {
            stmt.setString(1, permissionName);
            
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    Permission permission = PERMISSION_MAPPER.map(resultSet);
                    logger.fine(() -> "Found permission by name: " + permissionName);
                    return Optional.of(permission);
                } else {
                    logger.fine(() -> "No permission found with name: " + permissionName);
                    return Optional.empty();
                }
            }
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while finding permission by name: " + permissionName, e);
            throw new DataAccessException("Failed to find permission by name", e);
        }
    }
    
    @Override
    public List<Permission> findAll() throws DataAccessException {
        List<Permission> permissions = new ArrayList<>();
        
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_PERMISSIONS_SQL);
             ResultSet resultSet = stmt.executeQuery()) {
            
            while (resultSet.next()) {
                permissions.add(PERMISSION_MAPPER.map(resultSet));
            }
            
            logger.info(() -> "Retrieved " + permissions.size() + " permissions from database");
            return permissions;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while retrieving all permissions", e);
            throw new DataAccessException("Failed to retrieve all permissions", e);
        }
    }
    
    @Override
    public List<Permission> findByCategory(int categoryId) throws DataAccessException {
        List<Permission> permissions = new ArrayList<>();
        
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_PERMISSIONS_BY_CATEGORY_SQL)) {
            stmt.setInt(1, categoryId);
            
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    permissions.add(PERMISSION_MAPPER.map(resultSet));
                }
            }
            
            logger.info(() -> "Retrieved " + permissions.size() + " permissions for category ID: " + categoryId);
            return permissions;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while finding permissions by category: " + categoryId, e);
            throw new DataAccessException("Failed to find permissions by category", e);
        }
    }
    
    @Override
    public List<Permission> findActivePermissions() throws DataAccessException {
        List<Permission> permissions = new ArrayList<>();
        
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_ACTIVE_PERMISSIONS_SQL);
             ResultSet resultSet = stmt.executeQuery()) {
            
            while (resultSet.next()) {
                permissions.add(PERMISSION_MAPPER.map(resultSet));
            }
            
            logger.info(() -> "Retrieved " + permissions.size() + " active permissions from database");
            return permissions;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while retrieving active permissions", e);
            throw new DataAccessException("Failed to retrieve active permissions", e);
        }
    }
    
    @Override
    public boolean updatePermission(Permission permission) throws PermissionNotFoundException, DataAccessException {
        // First verify the permission exists
        if (!findById(permission.getAccessId()).isPresent()) {
            throw new PermissionNotFoundException("Permission with ID " + permission.getAccessId() + " not found");
        }
        
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_PERMISSION_SQL)) {
            stmt.setString(1, permission.getAccessName());
            stmt.setInt(2, permission.getAccessCategoryId());
            stmt.setInt(3, permission.getResourceId());
            stmt.setInt(4, permission.getActionId());
            stmt.setBoolean(5, permission.isRequiresApproval());
            stmt.setBoolean(6, permission.isActive());
            stmt.setInt(7, permission.getAccessId());
            
            int affectedRows = stmt.executeUpdate();
            boolean updateSuccessful = affectedRows > 0;
            
            if (updateSuccessful) {
                logger.info(() -> "Successfully updated permission with ID: " + permission.getAccessId());
            } else {
                logger.warning(() -> "No rows affected when updating permission with ID: " + permission.getAccessId());
            }
            
            return updateSuccessful;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while updating permission: " + permission.getAccessId(), e);
            throw new DataAccessException("Failed to update permission", e);
        }
    }
    
    @Override
    public boolean setPermissionStatus(int permissionId, boolean isActive) throws PermissionNotFoundException, DataAccessException {
        // First verify the permission exists
        if (!findById(permissionId).isPresent()) {
            throw new PermissionNotFoundException("Permission with ID " + permissionId + " not found");
        }
        
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_PERMISSION_STATUS_SQL)) {
            stmt.setBoolean(1, isActive);
            stmt.setInt(2, permissionId);
            
            int affectedRows = stmt.executeUpdate();
            boolean updateSuccessful = affectedRows > 0;
            
            String statusText = isActive ? "activated" : "deactivated";
            if (updateSuccessful) {
                logger.info(() -> "Successfully " + statusText + " permission with ID: " + permissionId);
            } else {
                logger.warning(() -> "No rows affected when updating permission status with ID: " + permissionId);
            }
            
            return updateSuccessful;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while updating permission status: " + permissionId, e);
            throw new DataAccessException("Failed to update permission status", e);
        }
    }
    
    @Override
    public boolean deletePermission(int permissionId) throws PermissionNotFoundException, DataAccessException {
        // First verify the permission exists
        if (!findById(permissionId).isPresent()) {
            throw new PermissionNotFoundException("Permission with ID " + permissionId + " not found");
        }
        
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_PERMISSION_SQL)) {
            stmt.setInt(1, permissionId);
            
            int affectedRows = stmt.executeUpdate();
            boolean deletionSuccessful = affectedRows > 0;
            
            if (deletionSuccessful) {
                logger.info(() -> "Successfully deleted permission with ID: " + permissionId);
            } else {
                logger.warning(() -> "No rows affected when deleting permission with ID: " + permissionId);
            }
            
            return deletionSuccessful;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while deleting permission: " + permissionId, e);
            throw new DataAccessException("Failed to delete permission", e);
        }
    }
    
    @Override
    public boolean permissionNameExists(String permissionName) throws DataAccessException {
        try (PreparedStatement stmt = connection.prepareStatement(CHECK_PERMISSION_NAME_EXISTS_SQL)) {
            stmt.setString(1, permissionName);
            
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    boolean exists = resultSet.getInt(1) > 0;
                    logger.fine(() -> "Permission name existence check for '" + permissionName + "': " + exists);
                    return exists;
                }
            }
            
            return false;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while checking permission name existence: " + permissionName, e);
            throw new DataAccessException("Failed to check permission name existence", e);
        }
    }
    
    @Override
    public int getPermissionCount() throws DataAccessException {
        try (PreparedStatement stmt = connection.prepareStatement(COUNT_PERMISSIONS_SQL);
             ResultSet resultSet = stmt.executeQuery()) {
            
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                logger.fine(() -> "Total permission count: " + count);
                return count;
            }
            
            return 0;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while counting permissions", e);
            throw new DataAccessException("Failed to count permissions", e);
        }
    }
    
    /**
     * Assigns a permission to a role by creating a record in role_access table.
     */
    @Override
    public boolean assignToRole(int roleId, int permissionId) throws DataAccessException {
    // First check if the assignment already exists
    try (PreparedStatement checkStmt = connection.prepareStatement(CHECK_ROLE_PERMISSION_EXISTS_SQL)) {
        checkStmt.setInt(1, roleId);
        checkStmt.setInt(2, permissionId);
        
        try (ResultSet resultSet = checkStmt.executeQuery()) {
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                logger.info(() -> "Permission " + permissionId + " is already assigned to role " + roleId);
                return true; // Already assigned, consider it successful
            }
        }
    } catch (SQLException e) {
        logger.log(Level.SEVERE, "Database error while checking existing role-permission assignment", e);
        throw new DataAccessException("Failed to check existing role-permission assignment", e);
    }
    
    // Proceed with assignment
    try (PreparedStatement stmt = connection.prepareStatement(ASSIGN_PERMISSION_TO_ROLE_SQL)) {
        stmt.setInt(1, roleId);
        stmt.setInt(2, permissionId);
        
        int affectedRows = stmt.executeUpdate();
        boolean assignmentSuccessful = affectedRows > 0;
        
        if (assignmentSuccessful) {
            logger.info(() -> "Successfully assigned permission " + permissionId + " to role " + roleId);
        } else {
            logger.warning(() -> "No rows affected when assigning permission " + permissionId + " to role " + roleId);
        }
        
        return assignmentSuccessful;
        
    } catch (SQLException e) {
        logger.log(Level.SEVERE, "Database error while assigning permission to role", e);
        throw new DataAccessException("Failed to assign permission to role", e);
    }
}

    /**
    * Removes a permission from a role by deleting record from role_access table.
    */
   @Override
   public boolean removeFromRole(int roleId, int permissionId) throws DataAccessException {
        try (PreparedStatement stmt = connection.prepareStatement(REMOVE_PERMISSION_FROM_ROLE_SQL)) {
               stmt.setInt(1, roleId);
               stmt.setInt(2, permissionId);
        
        int affectedRows = stmt.executeUpdate();
        boolean removalSuccessful = affectedRows > 0;
        
        if (removalSuccessful) {
            logger.info(() -> "Successfully removed permission " + permissionId + " from role " + roleId);
        } else {
            logger.warning(() -> "No rows affected when removing permission " + permissionId + " from role " + roleId);
        }
        
        return removalSuccessful;
        
    } catch (SQLException e) {
        logger.log(Level.SEVERE, "Database error while removing permission from role", e);
        throw new DataAccessException("Failed to remove permission from role", e);
    }
}

    @Override
    public int getActivePermissionCount() throws DataAccessException {
        try (PreparedStatement stmt = connection.prepareStatement(COUNT_ACTIVE_PERMISSIONS_SQL);
             ResultSet resultSet = stmt.executeQuery()) {
            
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                logger.fine(() -> "Active permission count: " + count);
                return count;
            }
            
            return 0;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while counting active permissions", e);
            throw new DataAccessException("Failed to count active permissions", e);
        }
    }
    
   /**
    * Retrieves all permissions assigned to a specific role.
    */
   @Override
    public List<Permission> findByRole(int roleId) throws DataAccessException {
    List<Permission> permissions = new ArrayList<>();
    
    try (PreparedStatement stmt = connection.prepareStatement(SELECT_PERMISSIONS_BY_ROLE_SQL)) {
        stmt.setInt(1, roleId);
        
        try (ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                permissions.add(PERMISSION_MAPPER.map(resultSet));
            }
        }
        
        logger.info(() -> "Retrieved " + permissions.size() + " permissions for role ID: " + roleId);
        return permissions;
        
    } catch (SQLException e) {
        logger.log(Level.SEVERE, "Database error while finding permissions by role: " + roleId, e);
        throw new DataAccessException("Failed to find permissions by role", e);
       }
    }    
}
