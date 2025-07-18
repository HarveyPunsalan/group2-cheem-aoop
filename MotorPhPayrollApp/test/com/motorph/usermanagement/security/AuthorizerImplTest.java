/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.motorph.usermanagement.security;

import com.motorph.usermanagement.model.Permission;
import com.motorph.usermanagement.model.User;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for AuthorizerImpl - testing employee login stuff.
 * Making sure the permission and role checks work when people log in.
 * 
 * @author Harvey
 */
public class AuthorizerImplTest {
    
    private AuthorizerImpl authorizer;
    private User adminUser;
    private User regularEmployee; //nonadmin
    private User invalidUser;
    
    public AuthorizerImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        authorizer = new AuthorizerImpl();
        
        // Setting up some test users like we'd have in real life
        adminUser = new User();
        adminUser.setEmployeeId(1001);
        adminUser.setUsername("admin.manager");
        adminUser.setRoleId(1); // This should be admin
        
        regularEmployee = new User();
        regularEmployee.setEmployeeId(2001);
        regularEmployee.setUsername("john.employee");
        regularEmployee.setRoleId(5); // Regular employee role
        
        // Making a user with bad role ID to test what happens
        invalidUser = new User();
        invalidUser.setEmployeeId(9999);
        invalidUser.setUsername("invalid.user");
        invalidUser.setRoleId(999); // This doesn't exist
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Check if admin users get recognized properly when they log in.
     * We need this to work so they get the right dashboard.
     */
    @Test
    public void testAdminUserLoginRoleCheck() throws Exception {
        System.out.println("Testing admin user role validation during login");
        
        // See if our admin user gets flagged as admin
        boolean isAdmin = authorizer.isAdminRole(adminUser);
        assertTrue("Admin user should be recognized as admin when logging in", isAdmin);
        
        // Double check with the other admin method
        boolean isAdminGeneral = authorizer.isAdmin(adminUser);
        assertTrue("Admin user should pass the general admin check too", isAdminGeneral);
        
        // Make sure admin doesn't get marked as regular employee
        boolean isNonAdmin = authorizer.isNonAdminRole(adminUser);
        assertFalse("Admin shouldn't be marked as regular employee", isNonAdmin);
    }

    /**
     * Check if regular employees get the right access level when logging in.
     * They shouldn't get admin stuff but should be able to use the system.
     */
    @Test
    public void testRegularEmployeeLoginRoleCheck() throws Exception {
        System.out.println("Testing regular employee role validation during login");
        
        // Regular employee should be marked as non-admin
        boolean isNonAdmin = authorizer.isNonAdminRole(regularEmployee);
        assertTrue("Regular employee should be marked as non-admin", isNonAdmin);
        
        // They definitely shouldn't be admin
        boolean isAdmin = authorizer.isAdminRole(regularEmployee);
        assertFalse("Regular employee shouldn't be marked as admin", isAdmin);
        
        // Double check with general admin method
        boolean isAdminGeneral = authorizer.isAdmin(regularEmployee);
        assertFalse("Regular employee should fail the general admin check", isAdminGeneral);
    }

    /**
     * What happens when someone passes null instead of a real user?
     * The system shouldn't crash, it should just say no.
     */
    @Test
    public void testNullUserLoginValidation() throws Exception {
        System.out.println("Testing null user login validation");
        
        // Try to check permissions with no user - should be false
        boolean hasPermission = authorizer.hasPermission(null, "system.login");
        assertFalse("Can't give permissions to nobody", hasPermission);
        
        // Try role checks with null user
        boolean isAdmin = authorizer.isAdminRole(null);
        assertFalse("Nobody can't be admin", isAdmin);
        
        boolean isNonAdmin = authorizer.isNonAdminRole(null);
        assertFalse("Nobody can't be non-admin either", isNonAdmin);
        
        // Try getting permissions for nobody
        List<Permission> permissions = authorizer.getUserPermissions(null);
        assertNotNull("Should give back empty list, not crash", permissions);
        assertTrue("Should be empty list for nobody", permissions.isEmpty());
    }

    /**
     * Test what happens with weird permission names.
     * Like empty strings or just spaces - should handle it gracefully.
     */
    @Test
    public void testInvalidPermissionNamesValidation() throws Exception {
        System.out.println("Testing invalid permission names during login");
        
        // Try with empty string
        boolean hasEmptyPermission = authorizer.hasPermission(adminUser, "");
        assertFalse("Empty permission name should be false", hasEmptyPermission);
        
        // Try with null permission
        boolean hasNullPermission = authorizer.hasPermission(adminUser, null);
        assertFalse("Null permission should be false", hasNullPermission);
        
        // Try with just spaces
        boolean hasWhitespacePermission = authorizer.hasPermission(adminUser, "   ");
        assertFalse("Whitespace permission should be false", hasWhitespacePermission);
    }

    /**
     * What if someone has a role ID that doesn't exist in the system?
     * Could happen if data gets messed up somehow.
     */
    @Test
    public void testInvalidRoleUserLogin() throws Exception {
        System.out.println("Testing user with invalid role ID during login");
        
        // User with bogus role shouldn't be admin
        boolean isAdmin = authorizer.isAdminRole(invalidUser);
        assertFalse("User with bogus role ID shouldn't be admin", isAdmin);
        
        // Also shouldn't be regular employee
        boolean isNonAdmin = authorizer.isNonAdminRole(invalidUser);
        assertFalse("User with bogus role ID shouldn't be non-admin either", isNonAdmin);
        
        // Should still return a list when getting permissions, just maybe empty
        List<Permission> permissions = authorizer.getUserPermissions(invalidUser);
        assertNotNull("Should still return a list for invalid role user", permissions);
        // Whether it's empty depends on how your DAO handles bad role IDs
    }

    /**
     * Testing if users can access specific resources after login.
     * Like can they view the dashboard or edit user profiles.
     */
    @Test
    public void testResourceAccessValidationDuringLogin() throws Exception {
        System.out.println("Testing resource access validation during login");
        
        // Null user shouldn't access anything
        boolean hasNullResourceAccess = authorizer.hasResourceAccess(null, "dashboard", "view");
        assertFalse("Nobody shouldn't have access to anything", hasNullResourceAccess);
        
        // Real user but null resource name
        boolean hasResourceWithNullName = authorizer.hasResourceAccess(adminUser, null, "view");
        assertFalse("Can't access nothing", hasResourceWithNullName);
        
        // Real user and resource but null action
        boolean hasResourceWithNullAction = authorizer.hasResourceAccess(adminUser, "dashboard", null);
        assertFalse("Can't do nothing to a resource", hasResourceWithNullAction);
    }

    /**
     * Sometimes login might need to check multiple permissions at once.
     * Like "can this user login AND view reports"
     */
    @Test
    public void testMultiplePermissionValidationDuringLogin() throws Exception {
        System.out.println("Testing multiple permission validation during login");
        
        // Check with null list of permissions
        boolean hasAnyNull = authorizer.hasAnyPermission(adminUser, null);
        assertFalse("Can't have any permissions from null list", hasAnyNull);
        
        boolean hasAllNull = authorizer.hasAllPermissions(adminUser, null);
        assertFalse("Can't have all permissions from null list", hasAllNull);
        
        // Check with null user
        boolean nullUserHasAny = authorizer.hasAnyPermission(null, java.util.Arrays.asList("login"));
        assertFalse("Nobody can't have any permissions", nullUserHasAny);
        
        boolean nullUserHasAll = authorizer.hasAllPermissions(null, java.util.Arrays.asList("login"));
        assertFalse("Nobody can't have all permissions", nullUserHasAll);
    }

    /**
     * Testing all the different admin role IDs we have.
     * Making sure they all work and that role 5 stays as regular employee.
     */
    @Test
    public void testCoreAdminIdentificationLogic() throws Exception {
        System.out.println("Testing core admin identification logic");
        
        // Making users for each admin role ID
        User admin1 = new User();
        admin1.setEmployeeId(1001);
        admin1.setUsername("admin1");
        admin1.setRoleId(1);
        
        User admin2 = new User();
        admin2.setEmployeeId(1002);
        admin2.setUsername("admin2");
        admin2.setRoleId(2);
        
        User admin3 = new User();
        admin3.setEmployeeId(1003);
        admin3.setUsername("admin3");
        admin3.setRoleId(3);
        
        User admin4 = new User();
        admin4.setEmployeeId(1004);
        admin4.setUsername("admin4");
        admin4.setRoleId(4);
        
        // And one regular employee with role 5
        User nonAdminEmployee = new User();
        nonAdminEmployee.setEmployeeId(5001);
        nonAdminEmployee.setUsername("regular.employee");
        nonAdminEmployee.setRoleId(5);
        
        // All the admin roles should work
        assertTrue("Role 1 should be admin", authorizer.isAdminRole(admin1));
        assertTrue("Role 2 should be admin", authorizer.isAdminRole(admin2));
        assertTrue("Role 3 should be admin", authorizer.isAdminRole(admin3));
        assertTrue("Role 4 should be admin", authorizer.isAdminRole(admin4));
        
        // Role 5 should be regular employee
        assertTrue("Role 5 should be non-admin", authorizer.isNonAdminRole(nonAdminEmployee));
        assertFalse("Role 5 should NOT be admin", authorizer.isAdminRole(nonAdminEmployee));
        
        // Double check with the other admin method
        assertTrue("Role 1 should pass admin check", authorizer.isAdmin(admin1));
        assertTrue("Role 2 should pass admin check", authorizer.isAdmin(admin2));
        assertTrue("Role 3 should pass admin check", authorizer.isAdmin(admin3));
        assertTrue("Role 4 should pass admin check", authorizer.isAdmin(admin4));
        
        // Role 5 should fail admin check
        assertFalse("Role 5 should fail admin check", authorizer.isAdmin(nonAdminEmployee));
    }

    /**
     * Test employee permissions retrieval during login.
     * This ensures the system can properly load user permissions for authorization.
     */
    @Test
    public void testEmployeePermissionsRetrievalDuringLogin() throws Exception {
        System.out.println("Testing employee permissions retrieval during login");
        
        // Test permissions retrieval for admin user
        List<Permission> adminPermissions = authorizer.getUserPermissions(adminUser);
        assertNotNull("Admin permissions should not be null", adminPermissions);
        
        // Test permissions retrieval for regular employee
        List<Permission> employeePermissions = authorizer.getUserPermissions(regularEmployee);
        assertNotNull("Employee permissions should not be null", employeePermissions);
        
        // Test permissions retrieval for invalid user
        List<Permission> invalidPermissions = authorizer.getUserPermissions(invalidUser);
        assertNotNull("Invalid user permissions should not be null", invalidPermissions);
    }
}