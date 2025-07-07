/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.service;

import com.motorph.usermanagement.model.Permission;
import com.motorph.usermanagement.model.Role;
import com.motorph.usermanagement.dao.PermissionDAO;
import com.motorph.usermanagement.dao.RoleDAO;
import com.motorph.usermanagement.exception.PermissionNotFoundException;
import com.motorph.usermanagement.exception.DuplicatePermissionException;
import com.motorph.usermanagement.exception.RoleNotFoundException;
import com.motorph.usermanagement.exception.DuplicateRoleException;
import com.motorph.usermanagement.exception.DataAccessException;
import java.util.List;
import java.util.Optional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for PermissionServiceImpl
 * @author Harvey
 */
public class PermissionServiceImplTest {
    
    private PermissionServiceImpl permissionService;
    private Permission testPermission;
    private TestPermissionDAO mockPermissionDAO;
    private TestRoleDAO mockRoleDAO;
    
    public PermissionServiceImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Starting PermissionServiceImpl tests...");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("Completed PermissionServiceImpl tests.");
    }
    
    @Before
    public void setUp() {
        mockPermissionDAO = new TestPermissionDAO();
        mockRoleDAO = new TestRoleDAO();
        permissionService = new PermissionServiceImpl(mockPermissionDAO, mockRoleDAO);
        testPermission = createTestPermission();
    }
    
    @After
    public void tearDown() {
        permissionService = null;
        testPermission = null;
        mockPermissionDAO = null;
        mockRoleDAO = null;
    }
    
    private Permission createTestPermission() {
        Permission permission = new Permission();
         permission.setAccessId(1);
         permission.setAccessName("Test Permission");
         permission.setActive(true);
         permission.setAccessCategoryId(1);
         return permission;
    }

    /**
     * Test creating permission with valid data.
     */
    @Test
    public void testCreatePermission_ValidData() throws Exception {
        int result = permissionService.createPermission(testPermission);
        assertEquals("Should return generated permission ID", 1, result);
    }
    
    /**
     * Test creating permission with invalid name format.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreatePermission_InvalidName() throws Exception {
        testPermission.setAccessName("AB"); // Too short
        permissionService.createPermission(testPermission);
    }
    
    /**
     * Test creating permission with duplicate name.
     */
    @Test(expected = DuplicatePermissionException.class)
    public void testCreatePermission_DuplicateName() throws Exception {
        testPermission.setAccessName("Existing Permission");
        permissionService.createPermission(testPermission);
    }

    /**
     * Test retrieving permission by valid ID.
     */
    @Test
    public void testGetPermissionById_ValidId() throws Exception {
        Optional<Permission> result = permissionService.getPermissionById(1);
        assertTrue("Should find permission with valid ID", result.isPresent());
        assertEquals("Should return correct permission", "Test Permission", result.get().getAccessName());
    }
    
    /**
     * Test retrieving permission by non-existent ID.
     */
    @Test
    public void testGetPermissionById_NonExistentId() throws Exception {
        Optional<Permission> result = permissionService.getPermissionById(999);
        assertFalse("Should not find permission with non-existent ID", result.isPresent());
    }

    /**
     * Test retrieving permission by valid name.
     */
    @Test
    public void testGetPermissionByName_ValidName() throws Exception {
        Optional<Permission> result = permissionService.getPermissionByName("Test Permission");
        assertTrue("Should find permission with valid name", result.isPresent());
    }
    
    /**
     * Test retrieving permission by null name.
     */
    @Test
    public void testGetPermissionByName_NullName() throws Exception {
        Optional<Permission> result = permissionService.getPermissionByName(null);
        assertFalse("Should not find permission with null name", result.isPresent());
    }
    
    /**
     * Test retrieving permission by empty name.
     */
    @Test
    public void testGetPermissionByName_EmptyName() throws Exception {
        Optional<Permission> result = permissionService.getPermissionByName("   ");
        assertFalse("Should not find permission with empty name", result.isPresent());
    }

    /**
     * Test retrieving all permissions.
     */
    @Test
    public void testGetAllPermissions() throws Exception {
        List<Permission> result = permissionService.getAllPermissions();
        assertNotNull("Result should not be null", result);
        assertEquals("Should return expected number of permissions", 2, result.size());
    }

    /**
     * Test retrieving permissions by category.
     */
    @Test
    public void testGetPermissionsByCategory() throws Exception {
        List<Permission> result = permissionService.getPermissionsByCategory(1);
        assertNotNull("Result should not be null", result);
        assertEquals("Should return permissions for category", 1, result.size());
    }

    /**
     * Test retrieving active permissions.
     */
    @Test
    public void testGetActivePermissions() throws Exception {
        List<Permission> result = permissionService.getActivePermissions();
        assertNotNull("Result should not be null", result);
        for (Permission p : result) {
            assertTrue("All returned permissions should be active", p.isActive());
        }
    }

    /**
     * Test updating permission with valid data.
     */
    @Test
    public void testUpdatePermission_ValidData() throws Exception {
        boolean result = permissionService.updatePermission(testPermission);
        assertTrue("Permission update should succeed", result);
    }
    
    /**
     * Test updating permission with invalid name.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdatePermission_InvalidName() throws Exception {
        testPermission.setAccessName("AB"); // Too short
        permissionService.updatePermission(testPermission);
    }
    
    /**
     * Test updating non-existent permission.
     */
    @Test(expected = PermissionNotFoundException.class)
    public void testUpdatePermission_NotFound() throws Exception {
        testPermission.setAccessId(999);
        permissionService.updatePermission(testPermission);
    }

    /**
     * Test setting permission status for existing permission.
     */
    @Test
    public void testSetPermissionStatus_ValidId() throws Exception {
        boolean result = permissionService.setPermissionStatus(1, false);
        assertTrue("Status change should succeed", result);
    }
    
    /**
     * Test setting permission status for non-existent permission.
     */
    @Test(expected = PermissionNotFoundException.class)
    public void testSetPermissionStatus_NotFound() throws Exception {
        permissionService.setPermissionStatus(999, false);
    }

    /**
     * Test deleting existing permission.
     */
    @Test
    public void testDeletePermission_ValidId() throws Exception {
        boolean result = permissionService.deletePermission(1);
        assertTrue("Permission deletion should succeed", result);
    }
    
    /**
     * Test deleting non-existent permission.
     */
    @Test(expected = PermissionNotFoundException.class)
    public void testDeletePermission_NotFound() throws Exception {
        permissionService.deletePermission(999);
    }

    /**
     * Test checking if valid permission name is available.
     */
    @Test
    public void testIsPermissionNameAvailable_ValidName() throws Exception {
        boolean result = permissionService.isPermissionNameAvailable("New Permission");
        assertTrue("New permission name should be available", result);
    }
    
    /**
     * Test checking if existing permission name is available.
     */
    @Test
    public void testIsPermissionNameAvailable_ExistingName() throws Exception {
        boolean result = permissionService.isPermissionNameAvailable("Existing Permission");
        assertFalse("Existing permission name should not be available", result);
    }
    
    /**
     * Test checking if null permission name is available.
     */
    @Test
    public void testIsPermissionNameAvailable_NullName() throws Exception {
        boolean result = permissionService.isPermissionNameAvailable(null);
        assertFalse("Null permission name should not be available", result);
    }

    /**
     * Test getting total permission count.
     */
    @Test
    public void testGetPermissionCount() throws Exception {
        int result = permissionService.getPermissionCount();
        assertEquals("Should return expected count", 5, result);
    }

    /**
     * Test getting active permission count.
     */
    @Test
    public void testGetActivePermissionCount() throws Exception {
        int result = permissionService.getActivePermissionCount();
        assertEquals("Should return expected active count", 3, result);
    }

    /**
     * Test assigning permission to existing role.
     */
    @Test
    public void testAssignPermissionToRole_ValidIds() throws Exception {
        boolean result = permissionService.assignPermissionToRole(1, 1);
        assertTrue("Permission assignment should succeed", result);
    }
    
    /**
     * Test assigning permission to non-existent role.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAssignPermissionToRole_InvalidRole() throws Exception {
        permissionService.assignPermissionToRole(999, 1);
    }
    
    /**
     * Test assigning non-existent permission to role.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAssignPermissionToRole_InvalidPermission() throws Exception {
        permissionService.assignPermissionToRole(1, 999);
    }

    /**
     * Test removing permission from role.
     */
    @Test
    public void testRemovePermissionFromRole() throws Exception {
        boolean result = permissionService.removePermissionFromRole(1, 1);
        assertTrue("Permission removal should succeed", result);
    }

    /**
     * Test getting permissions for existing role.
     */
    @Test
    public void testGetPermissionsForRole() throws Exception {
        List<Permission> result = permissionService.getPermissionsForRole(1);
        assertNotNull("Result should not be null", result);
        assertEquals("Should return permissions for role", 1, result.size());
    }

    /**
     * Mock PermissionDAO for testing.
     */
    private class TestPermissionDAO implements PermissionDAO {
        
        @Override
        public int createPermission(Permission permission) throws DataAccessException {
            return 1;
        }
        
        @Override
        public Optional<Permission> findById(int id) throws DataAccessException {
            if (id == 1) {
                Permission p = new Permission();
                p.setAccessId(1);
                p.setAccessName("Test Permission");
                p.setActive(true);
                return Optional.of(p);
            }
            return Optional.empty();
        }
        
        @Override
        public Optional<Permission> findByName(String name) throws DataAccessException {
            if ("Test Permission".equals(name)) {
                Permission p = new Permission();
                p.setAccessId(1);
                p.setAccessName("Test Permission");
                return Optional.of(p);
            }
            return Optional.empty();
        }
        
        @Override
        public List<Permission> findAll() throws DataAccessException {
            Permission p1 = new Permission();
            p1.setAccessId(1);
            p1.setAccessName("Permission 1");
            p1.setActive(true);
            
            Permission p2 = new Permission();
            p2.setAccessId(2);
            p2.setAccessName("Permission 2");
            p2.setActive(false);
            
            return java.util.Arrays.asList(p1, p2);
        }
        
        @Override
        public List<Permission> findByCategory(int categoryId) throws DataAccessException {
            if (categoryId == 1) {
                Permission p = new Permission();
                p.setAccessId(1);
                p.setAccessName("Category Permission");
                p.setAccessCategoryId(1);
                return java.util.Arrays.asList(p);
            }
            return java.util.Collections.emptyList();
        }
        
        @Override
        public List<Permission> findActivePermissions() throws DataAccessException {
            Permission p = new Permission();
            p.setAccessId(1);
            p.setAccessName("Active Permission");
            p.setActive(true);
            return java.util.Arrays.asList(p);
        }
        
        @Override
        public boolean updatePermission(Permission permission) throws DataAccessException {
            return true;
        }
        
        @Override
        public boolean setPermissionStatus(int permissionId, boolean isActive) throws DataAccessException {
            return true;
        }
        
        @Override
        public boolean deletePermission(int permissionId) throws DataAccessException {
            return true;
        }
        
        @Override
        public boolean permissionNameExists(String name) throws DataAccessException {
            return "Existing Permission".equals(name);
        }
        
        @Override
        public int getPermissionCount() throws DataAccessException {
            return 5;
        }
        
        @Override
        public int getActivePermissionCount() throws DataAccessException {
            return 3;
        }
        
        @Override
        public boolean assignToRole(int roleId, int permissionId) throws DataAccessException {
            return true;
        }
        
        @Override
        public boolean removeFromRole(int roleId, int permissionId) throws DataAccessException {
            return true;
        }
        
        @Override
        public List<Permission> findByRole(int roleId) throws DataAccessException {
            Permission p = new Permission();
            p.setAccessId(1);
            p.setAccessName("Role Permission");
            return java.util.Arrays.asList(p);
        }
    }
    
    /**
     * Mock RoleDAO for testing.
     */
    private class TestRoleDAO implements RoleDAO {
        
        @Override
        public int createRole(Role role) throws DuplicateRoleException, DataAccessException {
            return 1;
        }
        
        @Override
        public Optional<Role> findById(int roleId) throws DataAccessException {
            if (roleId == 1) {
                Role role = new Role();
                role.setRoleId(1);
                role.setRoleName("Test Role");
                return Optional.of(role);
            }
            return Optional.empty();
        }
        
        @Override
        public Optional<Role> findByName(String roleName) throws DataAccessException {
            if ("Test Role".equals(roleName)) {
                Role role = new Role();
                role.setRoleId(1);
                role.setRoleName("Test Role");
                return Optional.of(role);
            }
            return Optional.empty();
        }
        
        @Override
        public List<Role> findAll() throws DataAccessException {
            Role role1 = new Role();
            role1.setRoleId(1);
            role1.setRoleName("Test Role 1");
            
            Role role2 = new Role();
            role2.setRoleId(2);
            role2.setRoleName("Test Role 2");
            
            return java.util.Arrays.asList(role1, role2);
        }
        
        @Override
        public boolean updateRole(Role role) throws RoleNotFoundException, DataAccessException {
            return true;
        }
        
        @Override
        public boolean deleteRole(int roleId) throws RoleNotFoundException, DataAccessException {
            return true;
        }
        
        @Override
        public boolean roleNameExists(String roleName) throws DataAccessException {
            return "Existing Role".equals(roleName);
        }
        
        @Override
        public int getRoleCount() throws DataAccessException {
            return 5;
        }
        
        @Override
        public int getUserCountForRole(int roleId) throws DataAccessException {
            return 3;
        }
        
        @Override
        public List<Role> searchRolesByName(String searchTerm) throws DataAccessException {
            Role role = new Role();
            role.setRoleId(1);
            role.setRoleName("Test Role");
            return java.util.Arrays.asList(role);
        }
        
        @Override
        public List<Permission> getPermissionsForRole(int roleId) throws DataAccessException {
            Permission permission = new Permission();
            permission.setAccessId(1);
            permission.setAccessName("Role Permission");
            return java.util.Arrays.asList(permission);
        }
        
        @Override
        public boolean assignPermissionsToRole(int roleId, List<Integer> accessIds) throws DataAccessException {
            return true;
        }
        
        @Override
        public boolean removePermissionsFromRole(int roleId, List<Integer> accessIds) throws DataAccessException {
            return true;
        }
    }
}