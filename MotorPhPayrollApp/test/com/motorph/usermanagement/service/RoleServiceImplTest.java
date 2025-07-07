/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.service;

import com.motorph.usermanagement.model.Permission;
import com.motorph.usermanagement.model.Role;
import com.motorph.usermanagement.dao.RoleDAO;
import com.motorph.usermanagement.exception.DuplicateRoleException;
import com.motorph.usermanagement.exception.RoleNotFoundException;
import com.motorph.usermanagement.exception.DataAccessException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for RoleServiceImpl class.
 * Tests business logic and exception handling without database dependency.
 * 
 * @author Harvey
 */
public class RoleServiceImplTest {
    
    private RoleServiceImpl roleService;
    private TestRoleDAO mockRoleDAO;
    
    public RoleServiceImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        mockRoleDAO = new TestRoleDAO();
        roleService = new RoleServiceImpl(mockRoleDAO);
    }
    
    @After
    public void tearDown() {
        roleService = null;
        mockRoleDAO = null;
    }

    /**
     * Test successful role creation with valid data.
     */
    @Test
    public void testCreateRole_Success() throws Exception {
        Role role = new Role();
        role.setRoleName("Admin");
        role.setRoleDescription("Administrator role");
        
        mockRoleDAO.setRoleNameExists(false);
        mockRoleDAO.setNextRoleId(1);
        
        int result = roleService.createRole(role);
        
        assertEquals(1, result);
        assertTrue(mockRoleDAO.isCreateRoleCalled());
    }
    
    /**
     * Test role creation with duplicate name should throw exception.
     */
    @Test(expected = DuplicateRoleException.class)
    public void testCreateRole_DuplicateName() throws Exception {
        Role role = new Role();
        role.setRoleName("Admin");
        
        mockRoleDAO.setRoleNameExists(true);
        
        roleService.createRole(role);
    }
    
    /**
     * Test role creation with invalid name format should throw exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateRole_InvalidName() throws Exception {
        Role role = new Role();
        role.setRoleName("A"); // Too short
        
        roleService.createRole(role);
    }
    
    /**
     * Test role creation with very long name should throw exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateRole_NameTooLong() throws Exception {
        Role role = new Role();
        role.setRoleName("This is a very long role name that exceeds the maximum allowed length");
        
        roleService.createRole(role);
    }

    /**
     * Test getting role by ID when role exists.
     */
    @Test
    public void testGetRoleById_Found() throws Exception {
        Role expectedRole = new Role();
        expectedRole.setRoleId(1);
        expectedRole.setRoleName("Admin");
        
        mockRoleDAO.addRole(expectedRole);
        
        Optional<Role> result = roleService.getRoleById(1);
        
        assertTrue(result.isPresent());
        assertEquals("Admin", result.get().getRoleName());
    }
    
    /**
     * Test getting role by ID when role doesn't exist.
     */
    @Test
    public void testGetRoleById_NotFound() throws Exception {
        Optional<Role> result = roleService.getRoleById(999);
        
        assertFalse(result.isPresent());
    }

    /**
     * Test getting role by name when role exists.
     */
    @Test
    public void testGetRoleByName_Found() throws Exception {
        Role expectedRole = new Role();
        expectedRole.setRoleId(1);
        expectedRole.setRoleName("Admin");
        
        mockRoleDAO.addRole(expectedRole);
        
        Optional<Role> result = roleService.getRoleByName("Admin");
        
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getRoleId());
    }
    
    /**
     * Test getting role by name with null input.
     */
    @Test
    public void testGetRoleByName_NullInput() throws Exception {
        Optional<Role> result = roleService.getRoleByName(null);
        
        assertFalse(result.isPresent());
    }
    
    /**
     * Test getting role by name with empty string.
     */
    @Test
    public void testGetRoleByName_EmptyString() throws Exception {
        Optional<Role> result = roleService.getRoleByName("");
        
        assertFalse(result.isPresent());
    }

    /**
     * Test getting all roles when roles exist.
     */
    @Test
    public void testGetAllRoles_WithData() throws Exception {
        Role role1 = new Role();
        role1.setRoleId(1);
        role1.setRoleName("Admin");
        
        Role role2 = new Role();
        role2.setRoleId(2);
        role2.setRoleName("User");
        
        mockRoleDAO.addRole(role1);
        mockRoleDAO.addRole(role2);
        
        List<Role> result = roleService.getAllRoles();
        
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(r -> "Admin".equals(r.getRoleName())));
        assertTrue(result.stream().anyMatch(r -> "User".equals(r.getRoleName())));
    }
    
    /**
     * Test getting all roles when no roles exist.
     */
    @Test
    public void testGetAllRoles_EmptyResult() throws Exception {
        List<Role> result = roleService.getAllRoles();
        
        assertTrue(result.isEmpty());
    }

    /**
     * Test successful role update.
     */
    @Test
    public void testUpdateRole_Success() throws Exception {
        Role existingRole = new Role();
        existingRole.setRoleId(1);
        existingRole.setRoleName("Admin");
        
        mockRoleDAO.addRole(existingRole);
        
        Role updatedRole = new Role();
        updatedRole.setRoleId(1);
        updatedRole.setRoleName("Administrator");
        
        boolean result = roleService.updateRole(updatedRole);
        
        assertTrue(result);
        assertTrue(mockRoleDAO.isUpdateRoleCalled());
    }
    
    /**
     * Test updating non-existent role should throw exception.
     */
    @Test(expected = RoleNotFoundException.class)
    public void testUpdateRole_NotFound() throws Exception {
        Role role = new Role();
        role.setRoleId(999);
        role.setRoleName("NonExistent");
        
        roleService.updateRole(role);
    }
    
    /**
     * Test updating role with invalid name should throw exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateRole_InvalidName() throws Exception {
        Role existingRole = new Role();
        existingRole.setRoleId(1);
        existingRole.setRoleName("Admin");
        
        mockRoleDAO.addRole(existingRole);
        
        Role updatedRole = new Role();
        updatedRole.setRoleId(1);
        updatedRole.setRoleName("@#$%"); // Invalid characters
        
        roleService.updateRole(updatedRole);
    }

    /**
     * Test successful role deletion.
     */
    @Test
    public void testDeleteRole_Success() throws Exception {
        Role role = new Role();
        role.setRoleId(1);
        role.setRoleName("Admin");
        
        mockRoleDAO.addRole(role);
        mockRoleDAO.setUserCountForRole(1, 0); // No users assigned
        
        boolean result = roleService.deleteRole(1);
        
        assertTrue(result);
        assertTrue(mockRoleDAO.isDeleteRoleCalled());
    }
    
    /**
     * Test deleting non-existent role should throw exception.
     */
    @Test(expected = RoleNotFoundException.class)
    public void testDeleteRole_NotFound() throws Exception {
        roleService.deleteRole(999);
    }
    
    /**
     * Test deleting role with assigned users should throw exception.
     */
    @Test(expected = DataAccessException.class)
    public void testDeleteRole_HasAssignedUsers() throws Exception {
        Role role = new Role();
        role.setRoleId(1);
        role.setRoleName("Admin");
        
        mockRoleDAO.addRole(role);
        mockRoleDAO.setUserCountForRole(1, 3); // 3 users assigned
        
        roleService.deleteRole(1);
    }

    /**
     * Test checking if role name is available.
     */
    @Test
    public void testIsRoleNameAvailable_Available() throws Exception {
        mockRoleDAO.setRoleNameExists(false);
        
        boolean result = roleService.isRoleNameAvailable("NewRole");
        
        assertTrue(result);
    }
    
    /**
     * Test checking if role name is available when it exists.
     */
    @Test
    public void testIsRoleNameAvailable_NotAvailable() throws Exception {
        mockRoleDAO.setRoleNameExists(true);
        
        boolean result = roleService.isRoleNameAvailable("Admin");
        
        assertFalse(result);
    }
    
    /**
     * Test checking availability with null name.
     */
    @Test
    public void testIsRoleNameAvailable_NullName() throws Exception {
        boolean result = roleService.isRoleNameAvailable(null);
        
        assertFalse(result);
    }

    /**
     * Test getting role count.
     */
    @Test
    public void testGetRoleCount() throws Exception {
        mockRoleDAO.setRoleCount(5);
        
        int result = roleService.getRoleCount();
        
        assertEquals(5, result);
    }

    /**
     * Test getting user count for role.
     */
    @Test
    public void testGetUserCountForRole() throws Exception {
        mockRoleDAO.setUserCountForRole(1, 3);
        
        int result = roleService.getUserCountForRole(1);
        
        assertEquals(3, result);
    }

    /**
     * Test searching roles by name.
     */
    @Test
    public void testSearchRolesByName_WithResults() throws Exception {
        Role role1 = new Role();
        role1.setRoleId(1);
        role1.setRoleName("Admin");
        
        Role role2 = new Role();
        role2.setRoleId(2);
        role2.setRoleName("Administrator");
        
        mockRoleDAO.addRole(role1);
        mockRoleDAO.addRole(role2);
        
        List<Role> result = roleService.searchRolesByName("Admin");
        
        assertEquals(2, result.size());
    }
    
    /**
     * Test searching with empty term returns all roles.
     */
    @Test
    public void testSearchRolesByName_EmptyTerm() throws Exception {
        Role role = new Role();
        role.setRoleId(1);
        role.setRoleName("Admin");
        
        mockRoleDAO.addRole(role);
        
        List<Role> result = roleService.searchRolesByName("");
        
        assertEquals(1, result.size());
    }

    /**
     * Test successful permission assignment.
     */
    @Test
    public void testAssignPermissionsToRole_Success() throws Exception {
        Role role = new Role();
        role.setRoleId(1);
        role.setRoleName("Admin");
        
        mockRoleDAO.addRole(role);
        
        List<Integer> accessIds = Arrays.asList(1, 2, 3);
        
        boolean result = roleService.assignPermissionsToRole(1, accessIds);
        
        assertTrue(result);
        assertTrue(mockRoleDAO.isAssignPermissionsCalled());
    }
    
    /**
     * Test assigning permissions to non-existent role.
     */
    @Test(expected = RoleNotFoundException.class)
    public void testAssignPermissionsToRole_RoleNotFound() throws Exception {
        List<Integer> accessIds = Arrays.asList(1, 2, 3);
        
        roleService.assignPermissionsToRole(999, accessIds);
    }

    /**
     * Test successful permission removal.
     */
    @Test
    public void testRemovePermissionsFromRole_Success() throws Exception {
        Role role = new Role();
        role.setRoleId(1);
        role.setRoleName("Admin");
        
        mockRoleDAO.addRole(role);
        
        List<Integer> accessIds = Arrays.asList(1, 2);
        
        boolean result = roleService.removePermissionsFromRole(1, accessIds);
        
        assertTrue(result);
        assertTrue(mockRoleDAO.isRemovePermissionsCalled());
    }
    
    /**
     * Test removing permissions from non-existent role.
     */
    @Test(expected = RoleNotFoundException.class)
    public void testRemovePermissionsFromRole_RoleNotFound() throws Exception {
        List<Integer> accessIds = Arrays.asList(1, 2);
        
        roleService.removePermissionsFromRole(999, accessIds);
    }

    /**
     * Mock implementation of RoleDAO for testing.
     * Simulates database operations without requiring actual database connection.
     */
    private static class TestRoleDAO implements RoleDAO {
        private List<Role> roles = new ArrayList<>();
        private List<Permission> permissions = new ArrayList<>();
        private boolean roleNameExists = false;
        private int nextRoleId = 1;
        private int roleCount = 0;
        private int userCountForRole = 0;
        private int targetRoleId = 0;
        
        // Tracking method calls
        private boolean createRoleCalled = false;
        private boolean updateRoleCalled = false;
        private boolean deleteRoleCalled = false;
        private boolean assignPermissionsCalled = false;
        private boolean removePermissionsCalled = false;
        
        // Setter methods for test configuration
        public void setRoleNameExists(boolean exists) {
            this.roleNameExists = exists;
        }
        
        public void setNextRoleId(int id) {
            this.nextRoleId = id;
        }
        
        public void setRoleCount(int count) {
            this.roleCount = count;
        }
        
        public void setUserCountForRole(int roleId, int count) {
            this.targetRoleId = roleId;
            this.userCountForRole = count;
        }
        
        public void addRole(Role role) {
            roles.add(role);
        }
        
        // Method call tracking
        public boolean isCreateRoleCalled() { return createRoleCalled; }
        public boolean isUpdateRoleCalled() { return updateRoleCalled; }
        public boolean isDeleteRoleCalled() { return deleteRoleCalled; }
        public boolean isAssignPermissionsCalled() { return assignPermissionsCalled; }
        public boolean isRemovePermissionsCalled() { return removePermissionsCalled; }
        
        @Override
        public int createRole(Role role) throws DataAccessException {
            createRoleCalled = true;
            role.setRoleId(nextRoleId);
            roles.add(role);
            return nextRoleId;
        }
        
        @Override
        public Optional<Role> findById(int roleId) throws DataAccessException {
            return roles.stream()
                    .filter(role -> role.getRoleId() == roleId)
                    .findFirst();
        }
        
        @Override
        public Optional<Role> findByName(String roleName) throws DataAccessException {
            return roles.stream()
                    .filter(role -> roleName.equals(role.getRoleName()))
                    .findFirst();
        }
        
        @Override
        public List<Role> findAll() throws DataAccessException {
            return new ArrayList<>(roles);
        }
        
        @Override
        public boolean updateRole(Role role) throws DataAccessException {
            updateRoleCalled = true;
            return true;
        }
        
        @Override
        public boolean deleteRole(int roleId) throws DataAccessException {
            deleteRoleCalled = true;
            return roles.removeIf(role -> role.getRoleId() == roleId);
        }
        
        @Override
        public boolean roleNameExists(String roleName) throws DataAccessException {
            return roleNameExists;
        }
        
        @Override
        public int getRoleCount() throws DataAccessException {
            return roleCount;
        }
        
        @Override
        public int getUserCountForRole(int roleId) throws DataAccessException {
            return roleId == targetRoleId ? userCountForRole : 0;
        }
        
        @Override
        public List<Role> searchRolesByName(String searchTerm) throws DataAccessException {
            return roles.stream()
                    .filter(role -> role.getRoleName().toLowerCase().contains(searchTerm.toLowerCase()))
                    .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        }
        
        @Override
        public List<Permission> getPermissionsForRole(int roleId) throws DataAccessException {
            return new ArrayList<>(permissions);
        }
        
        @Override
        public boolean assignPermissionsToRole(int roleId, List<Integer> accessIds) throws DataAccessException {
            assignPermissionsCalled = true;
            return true;
        }
        
        @Override
        public boolean removePermissionsFromRole(int roleId, List<Integer> accessIds) throws DataAccessException {
            removePermissionsCalled = true;
            return true;
        }
    }
}