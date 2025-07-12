/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.dao;

import com.motorph.usermanagement.model.Permission;
import com.motorph.usermanagement.exception.DataAccessException;
import com.motorph.database.connection.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import com.motorph.database.execution.EntityMapper;

/**
 * Simplified JDBC implementation of PermissionDAO interface.
 * Focused on login and authorization functionality only.
 * Handles database operations related to Permission entity using the user_access table.
 *
 * @author Harvey
 */
public class PermissionDAOImpl implements PermissionDAO {
    private static final Logger logger = Logger.getLogger(PermissionDAOImpl.class.getName());
    private final Connection connection;
    
    // SQL queries for permission operations - focused on authorization needs
    private static final String SELECT_PERMISSIONS_BY_ROLE_SQL = 
        "SELECT ua.access_id, ua.access_name, ua.access_category_id, ua.resource_id, ua.action_id, ua.requires_approval, ua.is_active, ua.created_at " +
        "FROM user_access ua " +
        "INNER JOIN role_access ra ON ua.access_id = ra.access_id " +
        "WHERE ra.role_id = ? AND ua.is_active = TRUE " +
        "ORDER BY ua.access_name";
    
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
            logger.info("PermissionDAOImpl initialized successfully with database connection for login functionality");
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
    
    /**
     * Retrieves all permissions assigned to a specific role.
     * This is the primary method used by AuthorizerImpl for authorization checks.
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