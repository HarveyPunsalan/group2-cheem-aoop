/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.service;

import com.motorph.usermanagement.model.Permission;
import com.motorph.usermanagement.exception.PermissionNotFoundException;
import com.motorph.usermanagement.exception.DuplicatePermissionException;
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
 * Test class for PermissionService interface
 * Tests the contract behavior of permission management operations.
 * @author Harvey
 */
public class PermissionServiceTest {
    
    private PermissionService permissionService;
    private Permission testPermission;
    
    public PermissionServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Starting PermissionService tests...");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("Completed PermissionService tests.");
    }
    
    @Before
    public void setUp() {
        permissionService = new TestPermissionServiceImpl();
        testPermission = createTestPermission();
    }
    
    @After
    public void tearDown() {
        permissionService = null;
        testPermission = null;
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
     * Test creating a permission with valid data.
     */
    @Test
    public void testCreatePermission_ValidData() throws Exception {
        int result = permissionService.createPermission(testPermission);
        assertTrue("Permission creation should return positive ID", result > 0);
        assertEquals("Should return expected permission ID", 1, result);
    }
    
    /**
     * Test creating a permission with null input.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreatePermission_NullInput() throws Exception {
        permissionService.createPermission(null);
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
     * Test retrieving permission by invalid ID.
     */
    @Test
    public void testGetPermissionById_InvalidId() throws Exception {
        Optional<Permission> result = permissionService.getPermissionById(999);
        assertFalse("Should not find permission with invalid ID", result.isPresent());
    }

    /**
     * Test retrieving permission by valid name.
     */
    @Test
    public void testGetPermissionByName_ValidName() throws Exception {
        Optional<Permission> result = permissionService.getPermissionByName("Test Permission");
        assertTrue("Should find permission with valid name", result.isPresent());
        assertEquals("Should return correct permission", 1, result.get().getAccessId());
    }
    
    /**
     * Test retrieving permission by empty name.
     */
    @Test
    public void testGetPermissionByName_EmptyName() throws Exception {
        Optional<Permission> result = permissionService.getPermissionByName("");
        assertFalse("Should not find permission with empty name", result.isPresent());
    }

    /**
     * Test retrieving all permissions.
     */
    @Test
    public void testGetAllPermissions() throws Exception {
        List<Permission> result = permissionService.getAllPermissions();
        assertNotNull("Result should not be null", result);
        assertFalse("Should return non-empty list", result.isEmpty());
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
     * Test updating non-existent permission.
     */
    @Test(expected = PermissionNotFoundException.class)
    public void testUpdatePermission_NotFound() throws Exception {
        testPermission.setAccessId(999);
        permissionService.updatePermission(testPermission);
    }

    /**
     * Test setting permission status.
     */
    @Test
    public void testSetPermissionStatus() throws Exception {
        boolean result = permissionService.setPermissionStatus(1, false);
        assertTrue("Status change should succeed", result);
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
     * Test checking if permission name is available.
     */
    @Test
    public void testIsPermissionNameAvailable() throws Exception {
        boolean result = permissionService.isPermissionNameAvailable("New Permission");
        assertTrue("New permission name should be available", result);
        
        boolean existingResult = permissionService.isPermissionNameAvailable("Test Permission");
        assertFalse("Existing permission name should not be available", existingResult);
    }

    /**
     * Test getting permission count.
     */
    @Test
    public void testGetPermissionCount() throws Exception {
        int result = permissionService.getPermissionCount();
        assertTrue("Permission count should be positive", result > 0);
        assertEquals("Should return expected count", 2, result);
    }

    /**
     * Test getting active permission count.
     */
    @Test
    public void testGetActivePermissionCount() throws Exception {
        int result = permissionService.getActivePermissionCount();
        assertTrue("Active permission count should be positive", result >= 0);
        assertEquals("Should return expected active count", 1, result);
    }

    /**
     * Test assigning permission to role.
     */
    @Test
    public void testAssignPermissionToRole() throws Exception {
        boolean result = permissionService.assignPermissionToRole(1, 1);
        assertTrue("Permission assignment should succeed", result);
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
     * Test getting permissions for role.
     */
    @Test
    public void testGetPermissionsForRole() throws Exception {
        List<Permission> result = permissionService.getPermissionsForRole(1);
        assertNotNull("Result should not be null", result);
        assertEquals("Should return permissions for role", 1, result.size());
    }

    /**
     * Test implementation of PermissionService for testing purposes.
     * Provides controlled behavior for unit tests.
     */
    public class TestPermissionServiceImpl implements PermissionService {
        
        @Override
        public int createPermission(Permission permission) throws DuplicatePermissionException, DataAccessException {
            if (permission == null) {
                throw new IllegalArgumentException("Permission cannot be null");
            }
            if ("Duplicate Permission".equals(permission.getAccessName())) {
                throw new DuplicatePermissionException("Permission already exists");
            }
            return 1;
        }

        @Override
        public Optional<Permission> getPermissionById(int permissionId) throws DataAccessException {
            if (permissionId == 1) {
                return Optional.of(testPermission);
            }
            return Optional.empty();
        }

        @Override
        public Optional<Permission> getPermissionByName(String permissionName) throws DataAccessException {
            if (permissionName == null || permissionName.trim().isEmpty()) {
                return Optional.empty();
            }
            if ("Test Permission".equals(permissionName)) {
                return Optional.of(testPermission);
            }
            return Optional.empty();
        }

        @Override
        public List<Permission> getAllPermissions() throws DataAccessException {
            Permission p1 = createTestPermission();
            Permission p2 = createTestPermission();
            p2.setAccessId(2);
            p2.setAccessName("Another Permission");
            p2.setActive(false);
            return java.util.Arrays.asList(p1, p2);
        }

        @Override
        public List<Permission> getPermissionsByCategory(int categoryId) throws DataAccessException {
            if (categoryId == 1) {
                return java.util.Arrays.asList(testPermission);
            }
            return java.util.Collections.emptyList();
        }

        @Override
        public List<Permission> getActivePermissions() throws DataAccessException {
            return java.util.Arrays.asList(testPermission);
        }

        @Override
        public boolean updatePermission(Permission permission) throws PermissionNotFoundException, DataAccessException {
            if (permission.getAccessId() == 999) {
                throw new PermissionNotFoundException("Permission not found");
            }
            return true;
        }

        @Override
        public boolean setPermissionStatus(int permissionId, boolean isActive) throws PermissionNotFoundException, DataAccessException {
            if (permissionId == 999) {
                throw new PermissionNotFoundException("Permission not found");
            }
            return true;
        }

        @Override
        public boolean deletePermission(int permissionId) throws PermissionNotFoundException, DataAccessException {
            if (permissionId == 999) {
                throw new PermissionNotFoundException("Permission not found");
            }
            return true;
        }

        @Override
        public boolean isPermissionNameAvailable(String permissionName) throws DataAccessException {
            return !"Test Permission".equals(permissionName);
        }

        @Override
        public int getPermissionCount() throws DataAccessException {
            return 2;
        }

        @Override
        public int getActivePermissionCount() throws DataAccessException {
            return 1;
        }

        @Override
        public boolean assignPermissionToRole(int roleId, int permissionId) throws DataAccessException {
            return true;
        }

        @Override
        public boolean removePermissionFromRole(int roleId, int permissionId) throws DataAccessException {
            return true;
        }

        @Override
        public List<Permission> getPermissionsForRole(int roleId) throws DataAccessException {
            return java.util.Arrays.asList(testPermission);
        }
    }
}