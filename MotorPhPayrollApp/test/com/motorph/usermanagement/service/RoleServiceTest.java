/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.service;

import com.motorph.usermanagement.exception.DataAccessException;
import com.motorph.usermanagement.exception.DuplicateRoleException;
import com.motorph.usermanagement.exception.RoleNotFoundException;
import com.motorph.usermanagement.model.Permission;
import com.motorph.usermanagement.model.Role;
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
 * Test class for RoleService implementation.
 * Tests role management functionality that supports login system.
 * 
 * @author Harvey
 */
public class RoleServiceTest {
    
    private RoleService roleService;
    private Role testRole;
    private List<Permission> testPermissions;
    
    public RoleServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Starting RoleService tests...");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("RoleService tests completed.");
    }
    
    @Before
    public void setUp() {
        roleService = new TestRoleServiceImpl();
        
        // Create test role for login scenarios
        testRole = new Role();
        testRole.setRoleId(1);
        testRole.setRoleName("EMPLOYEE");
        // Note: Using only available methods for Role class
        
        // Create test permissions
        testPermissions = new ArrayList<>();
        Permission loginPermission = new Permission();
        // Note: Using only available methods for Permission class
        testPermissions.add(loginPermission);
        
        Permission viewPermission = new Permission();
        testPermissions.add(viewPermission);
    }
    
    @After
    public void tearDown() {
        roleService = null;
        testRole = null;
        testPermissions = null;
    }

    /**
     * Test creating a new role for login system.
     * This is important for setting up user roles during account creation.
     */
    @Test
    public void testCreateRole_ValidRole() throws Exception {
        Role newRole = new Role();
        newRole.setRoleName("MANAGER");
        
        int result = roleService.createRole(newRole);
        
        assertTrue("Role creation should return positive ID", result > 0);
        assertEquals("Generated role ID should be 2", 2, result);
    }
    
    /**
     * Test creating role with duplicate name should throw exception.
     */
    @Test(expected = DuplicateRoleException.class)
    public void testCreateRole_DuplicateName() throws Exception {
        Role duplicateRole = new Role();
        duplicateRole.setRoleName("EMPLOYEE"); // Already exists in test data
        
        roleService.createRole(duplicateRole);
    }
    
    /**
     * Test creating role with null input should throw exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateRole_NullRole() throws Exception {
        roleService.createRole(null);
    }

    /**
     * Test retrieving role by ID - essential for login validation.
     * When user logs in, system needs to fetch their role.
     */
    @Test
    public void testGetRoleById_ExistingRole() throws Exception {
        Optional<Role> result = roleService.getRoleById(1);
        
        assertTrue("Role should be found", result.isPresent());
        assertEquals("Role name should match", "EMPLOYEE", result.get().getRoleName());
    }
    
    /**
     * Test retrieving non-existent role by ID.
     */
    @Test
    public void testGetRoleById_NonExistentRole() throws Exception {
        Optional<Role> result = roleService.getRoleById(999);
        
        assertFalse("Role should not be found", result.isPresent());
    }
    
    /**
     * Test retrieving role with invalid ID.
     */
    @Test
    public void testGetRoleById_InvalidId() throws Exception {
        Optional<Role> result = roleService.getRoleById(-1);
        
        assertFalse("Role should not be found for negative ID", result.isPresent());
    }

    /**
     * Test retrieving role by name - used during login process.
     * System often needs to validate user role by name.
     */
    @Test
    public void testGetRoleByName_ExistingRole() throws Exception {
        Optional<Role> result = roleService.getRoleByName("EMPLOYEE");
        
        assertTrue("Role should be found", result.isPresent());
        assertEquals("Role ID should match", 1, result.get().getRoleId());
        assertEquals("Role name should match", "EMPLOYEE", result.get().getRoleName());
    }
    
    /**
     * Test retrieving role by name with different case.
     */
    @Test
    public void testGetRoleByName_CaseInsensitive() throws Exception {
        Optional<Role> result = roleService.getRoleByName("employee");
        
        assertTrue("Role should be found regardless of case", result.isPresent());
        assertEquals("Role name should match", "EMPLOYEE", result.get().getRoleName());
    }
    
    /**
     * Test retrieving non-existent role by name.
     */
    @Test
    public void testGetRoleByName_NonExistentRole() throws Exception {
        Optional<Role> result = roleService.getRoleByName("NONEXISTENT");
        
        assertFalse("Role should not be found", result.isPresent());
    }
    
    /**
     * Test retrieving role with null name.
     */
    @Test
    public void testGetRoleByName_NullName() throws Exception {
        Optional<Role> result = roleService.getRoleByName(null);
        
        assertFalse("Role should not be found for null name", result.isPresent());
    }

    /**
     * Test retrieving all roles - useful for admin login functionality.
     */
    @Test
    public void testGetAllRoles() throws Exception {
        List<Role> result = roleService.getAllRoles();
        
        assertNotNull("Role list should not be null", result);
        assertFalse("Role list should not be empty", result.isEmpty());
        assertTrue("Should contain at least one role", result.size() >= 1);
        
        // Check if our test role is in the list
        boolean foundTestRole = result.stream()
                .anyMatch(role -> "EMPLOYEE".equals(role.getRoleName()));
        assertTrue("Test role should be in the list", foundTestRole);
    }

    /**
     * Test updating role - important for role management in login system.
     */
    @Test
    public void testUpdateRole_ValidRole() throws Exception {
        Role updatedRole = new Role();
        updatedRole.setRoleId(1);
        updatedRole.setRoleName("EMPLOYEE");
        
        boolean result = roleService.updateRole(updatedRole);
        
        assertTrue("Role update should succeed", result);
    }
    
    /**
     * Test updating non-existent role.
     */
    @Test(expected = RoleNotFoundException.class)
    public void testUpdateRole_NonExistentRole() throws Exception {
        Role nonExistentRole = new Role();
        nonExistentRole.setRoleId(999);
        nonExistentRole.setRoleName("NONEXISTENT");
        
        roleService.updateRole(nonExistentRole);
    }

    /**
     * Test deleting role - important for role management.
     */
    @Test
    public void testDeleteRole_ValidRole() throws Exception {
        // Create a role that can be deleted (not assigned to users)
        Role roleToDelete = new Role();
        roleToDelete.setRoleName("TEMP_ROLE");
        int roleId = roleService.createRole(roleToDelete);
        
        boolean result = roleService.deleteRole(roleId);
        
        assertTrue("Role deletion should succeed", result);
    }
    
    /**
     * Test deleting non-existent role.
     */
    @Test(expected = RoleNotFoundException.class)
    public void testDeleteRole_NonExistentRole() throws Exception {
        roleService.deleteRole(999);
    }

    /**
     * Test getting permissions for role - crucial for login authorization.
     * After login, system needs to know what user can do.
     */
    @Test
    public void testGetPermissionsForRole_ValidRole() throws Exception {
        List<Permission> result = roleService.getPermissionsForRole(1);
        
        assertNotNull("Permission list should not be null", result);
        assertFalse("Permission list should not be empty", result.isEmpty());
        
        // Check that we have some permissions (basic validation)
        assertTrue("Role should have at least one permission", result.size() > 0);
    }
    
    /**
     * Test getting permissions for non-existent role.
     */
    @Test
    public void testGetPermissionsForRole_NonExistentRole() throws Exception {
        List<Permission> result = roleService.getPermissionsForRole(999);
        
        assertNotNull("Permission list should not be null", result);
        assertTrue("Permission list should be empty for non-existent role", result.isEmpty());
    }

    /**
     * Test checking if role name is available - used during role creation.
     */
    @Test
    public void testIsRoleNameAvailable_AvailableName() throws Exception {
        boolean result = roleService.isRoleNameAvailable("NEW_ROLE");
        
        assertTrue("New role name should be available", result);
    }
    
    /**
     * Test checking if existing role name is available.
     */
    @Test
    public void testIsRoleNameAvailable_ExistingName() throws Exception {
        boolean result = roleService.isRoleNameAvailable("EMPLOYEE");
        
        assertFalse("Existing role name should not be available", result);
    }

    /**
     * Test getting role count - useful for admin dashboard.
     */
    @Test
    public void testGetRoleCount() throws Exception {
        int result = roleService.getRoleCount();
        
        assertTrue("Role count should be positive", result > 0);
        assertEquals("Should have at least 1 role", 1, result);
    }

    /**
     * Test getting user count for role - important for role management.
     */
    @Test
    public void testGetUserCountForRole_ValidRole() throws Exception {
        int result = roleService.getUserCountForRole(1);
        
        assertTrue("User count should be non-negative", result >= 0);
    }

    /**
     * Test searching roles by name - useful for admin interface.
     */
    @Test
    public void testSearchRolesByName_ValidSearch() throws Exception {
        List<Role> result = roleService.searchRolesByName("EMPLOYEE");
        
        assertNotNull("Search result should not be null", result);
        assertFalse("Search result should not be empty", result.isEmpty());
        
        boolean foundMatch = result.stream()
                .anyMatch(role -> role.getRoleName().contains("EMPLOYEE"));
        assertTrue("Search should find matching role", foundMatch);
    }
    
    /**
     * Test searching roles with no matches.
     */
    @Test
    public void testSearchRolesByName_NoMatches() throws Exception {
        List<Role> result = roleService.searchRolesByName("NONEXISTENT");
        
        assertNotNull("Search result should not be null", result);
        assertTrue("Search result should be empty", result.isEmpty());
    }

    /**
     * Test assigning permissions to role - critical for login system security.
     */
    @Test
    public void testAssignPermissionsToRole_ValidInput() throws Exception {
        List<Integer> accessIds = Arrays.asList(1, 2);
        
        boolean result = roleService.assignPermissionsToRole(1, accessIds);
        
        assertTrue("Permission assignment should succeed", result);
    }
    
    /**
     * Test assigning permissions to non-existent role.
     */
    @Test(expected = RoleNotFoundException.class)
    public void testAssignPermissionsToRole_NonExistentRole() throws Exception {
        List<Integer> accessIds = Arrays.asList(1, 2);
        
        roleService.assignPermissionsToRole(999, accessIds);
    }

    /**
     * Test removing permissions from role.
     */
    @Test
    public void testRemovePermissionsFromRole_ValidInput() throws Exception {
        List<Integer> accessIds = Arrays.asList(1);
        
        boolean result = roleService.removePermissionsFromRole(1, accessIds);
        
        assertTrue("Permission removal should succeed", result);
    }

    /**
     * Test implementation that simulates actual RoleService behavior
     * without requiring database connection.
     */
    private class TestRoleServiceImpl implements RoleService {
        private List<Role> roles = new ArrayList<>();
        private List<Permission> permissions = new ArrayList<>();
        private int nextRoleId = 2;
        
        public TestRoleServiceImpl() {
            // Initialize test data - add the testRole after it's been set up
            initializeTestData();
        }
        
        private void initializeTestData() {
            // Create initial role data
            Role employeeRole = new Role();
            employeeRole.setRoleId(1);
            employeeRole.setRoleName("EMPLOYEE");
            roles.add(employeeRole);
            
            // Create initial permissions
            Permission perm1 = new Permission();
            Permission perm2 = new Permission();
            permissions.add(perm1);
            permissions.add(perm2);
        }

        @Override
        public int createRole(Role role) throws DuplicateRoleException, DataAccessException {
            if (role == null) {
                throw new IllegalArgumentException("Role cannot be null");
            }
            
            if (role.getRoleName() == null || role.getRoleName().trim().isEmpty()) {
                throw new IllegalArgumentException("Role name cannot be null or empty");
            }
            
            // Check for duplicate name
            boolean exists = roles.stream()
                    .anyMatch(r -> r.getRoleName().equalsIgnoreCase(role.getRoleName()));
            if (exists) {
                throw new DuplicateRoleException("Role name already exists");
            }
            
            // Create new role with generated ID
            Role newRole = new Role();
            newRole.setRoleId(nextRoleId++);
            newRole.setRoleName(role.getRoleName());
            roles.add(newRole);
            
            return newRole.getRoleId();
        }

        @Override
        public Optional<Role> getRoleById(int roleId) throws DataAccessException {
            if (roleId <= 0) {
                return Optional.empty();
            }
            
            return roles.stream()
                    .filter(role -> role.getRoleId() == roleId)
                    .findFirst();
        }

        @Override
        public Optional<Role> getRoleByName(String roleName) throws DataAccessException {
            if (roleName == null || roleName.trim().isEmpty()) {
                return Optional.empty();
            }
            
            return roles.stream()
                    .filter(role -> role.getRoleName().equalsIgnoreCase(roleName.trim()))
                    .findFirst();
        }

        @Override
        public List<Role> getAllRoles() throws DataAccessException {
            return new ArrayList<>(roles);
        }

        @Override
        public boolean updateRole(Role role) throws RoleNotFoundException, DataAccessException {
            Optional<Role> existingRole = getRoleById(role.getRoleId());
            if (!existingRole.isPresent()) {
                throw new RoleNotFoundException("Role not found");
            }
            
            Role existing = existingRole.get();
            existing.setRoleName(role.getRoleName());
            // Only update fields that are available in the Role class
            
            return true;
        }

        @Override
        public boolean deleteRole(int roleId) throws RoleNotFoundException, DataAccessException {
            Optional<Role> roleToDelete = getRoleById(roleId);
            if (!roleToDelete.isPresent()) {
                throw new RoleNotFoundException("Role not found with ID: " + roleId);
            }
            
            return roles.removeIf(r -> r.getRoleId() == roleId);
        }

        @Override
        public List<Permission> getPermissionsForRole(int roleId) throws DataAccessException {
            // Check if role exists first
            Optional<Role> role = getRoleById(roleId);
            if (!role.isPresent()) {
                return new ArrayList<>();
            }
            
            // Return copy of permissions for existing roles
            return new ArrayList<>(permissions);
        }

        @Override
        public boolean isRoleNameAvailable(String roleName) throws DataAccessException {
            if (roleName == null || roleName.trim().isEmpty()) {
                return false;
            }
            
            return roles.stream()
                    .noneMatch(role -> role.getRoleName().equalsIgnoreCase(roleName.trim()));
        }

        @Override
        public int getRoleCount() throws DataAccessException {
            return roles.size();
        }

        @Override
        public int getUserCountForRole(int roleId) throws DataAccessException {
            // For testing, return 0 for simplicity
            return 0;
        }

        @Override
        public List<Role> searchRolesByName(String searchTerm) throws DataAccessException {
            if (searchTerm == null || searchTerm.trim().isEmpty()) {
                return new ArrayList<>();
            }
            
            List<Role> results = new ArrayList<>();
            for (Role role : roles) {
                if (role.getRoleName().toLowerCase().contains(searchTerm.toLowerCase().trim())) {
                    results.add(role);
                }
            }
            return results;
        }

        @Override
        public boolean assignPermissionsToRole(int roleId, List<Integer> accessIds) 
                throws RoleNotFoundException, DataAccessException {
            if (!getRoleById(roleId).isPresent()) {
                throw new RoleNotFoundException("Role not found with ID: " + roleId);
            }
            
            if (accessIds == null || accessIds.isEmpty()) {
                return false;
            }
            
            return true;
        }

        @Override
        public boolean removePermissionsFromRole(int roleId, List<Integer> accessIds) 
                throws RoleNotFoundException, DataAccessException {
            if (!getRoleById(roleId).isPresent()) {
                throw new RoleNotFoundException("Role not found with ID: " + roleId);
            }
            
            if (accessIds == null || accessIds.isEmpty()) {
                return false;
            }
            
            return true;
        }
    }
}