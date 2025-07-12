/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.dao;

import com.motorph.usermanagement.model.Role;
import com.motorph.usermanagement.exception.DataAccessException;
import com.motorph.database.connection.DatabaseService;

import java.sql.*;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.logging.Level;
import com.motorph.database.execution.EntityMapper;

/**
 * Simplified RoleDAOImpl implementation focused on authentication and authorization.
 * Only contains methods needed by AuthorizerImpl for role-based access control.
 * 
 * @author Harvey
 */
public class RoleDAOImpl implements RoleDAO {
    private static final Logger logger = Logger.getLogger(RoleDAOImpl.class.getName());
    private final Connection connection;
    
    // SQL query for role lookup by ID
    private static final String SELECT_ROLE_BY_ID_SQL = 
        "SELECT role_id, role_name, role_description FROM user_role WHERE role_id = ?";
    
    /**
     * ResultSetMapper for converting database rows to Role objects.
     */
    private static final EntityMapper<Role> ROLE_MAPPER = resultSet -> {
        Role role = new Role();
        role.setRoleId(resultSet.getInt("role_id"));
        role.setRoleName(resultSet.getString("role_name"));
        role.setRoleDescription(resultSet.getString("role_description"));
        return role;
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
    public Optional<Role> findById(int roleId) throws DataAccessException {
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_ROLE_BY_ID_SQL)) {
            stmt.setInt(1, roleId);
            
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    Role role = ROLE_MAPPER.map(resultSet);
                    logger.fine(() -> "Found role by ID: " + roleId + " - " + role.getRoleName());
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
}