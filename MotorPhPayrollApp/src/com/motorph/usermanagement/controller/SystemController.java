/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.controller;

import com.motorph.usermanagement.view.SystemAdminPage;
import com.motorph.usermanagement.view.PermissionManagementPage;
import com.motorph.usermanagement.view.RoleManagementPage;
import com.motorph.usermanagement.view.UserManagementPage;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.JPanel;
/**
 * To  handles navigation between different management pages
 * @author harvey punsalan
 */
public class SystemController implements ActionListener {
    private static final Logger logger = Logger.getLogger(SystemController.class.getName());
    
    private final SystemAdminPage systemView;
    private final JPanel contentPanel;
    private final CardLayout cardLayout;
    
    // Page instances
    private PermissionManagementPage permissionPage;
    private PermissionController permissionController;
    private RoleManagementPage rolePage;
    private RoleController roleController;
    private UserManagementPage userPage;
    
    // Card names for navigation
    private static final String WELCOME_CARD = "WELCOME";
    private static final String PERMISSION_CARD = "PERMISSION";
    private static final String ROLE_CARD = "ROLE";
    private static final String USER_CARD = "USER";
    
    /**
     * Constructor initializes the controller with the main system view
     * 
     * @param systemView The main SystemAdminPage
     * @param contentPanel The panel where different pages will be displayed
     */
    public SystemController(SystemAdminPage systemView, JPanel contentPanel) {
        this.systemView = systemView;
        this.contentPanel = contentPanel;
        this.cardLayout = new CardLayout();
        
        // Set up the content panel with CardLayout
        this.contentPanel.setLayout(cardLayout);
        
        // Initialize pages
        initializePages();
        
        // Set up button listeners
        setupButtonListeners();
        
        logger.info("SystemController initialized successfully");
    }
    
    /**
     * Initialize all management pages
     */
    private void initializePages() {
        try {
            // Create permission management page
            permissionPage = new PermissionManagementPage();
            permissionController = new PermissionController(permissionPage);
            
            // Create role management page
            rolePage = new RoleManagementPage();
            roleController = new RoleController(rolePage);
            
            // Create user management page
            userPage = new UserManagementPage();
            // Note: UserController is already initialized in UserManagementPage constructor
            
            // Add pages to card layout
            contentPanel.add(permissionPage, PERMISSION_CARD);
            contentPanel.add(rolePage, ROLE_CARD);
            contentPanel.add(userPage, USER_CARD);
            
            // Show welcome screen initially (you can create a welcome panel later)
            JPanel welcomePanel = createWelcomePanel();
            contentPanel.add(welcomePanel, WELCOME_CARD);
            
            // Show welcome panel initially
            cardLayout.show(contentPanel, WELCOME_CARD);
            
            logger.info("All management pages initialized successfully");
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error initializing management pages", e);
        }
    }
    
    /**
     * Create a simple welcome panel (placeholder for now)
     */
    private JPanel createWelcomePanel() {
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(java.awt.Color.WHITE);
        
        javax.swing.JLabel welcomeLabel = new javax.swing.JLabel("Welcome to System Administration");
        welcomeLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 24));
        welcomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        welcomePanel.add(welcomeLabel);
        
        return welcomePanel;
    }
    
    /**
     * Set up action listeners for all buttons in the system view
     */
    private void setupButtonListeners() {
        try {
            // Add action listeners to buttons
            systemView.getPermissionButton().addActionListener(this);
            systemView.getRoleButton().addActionListener(this);
            systemView.getUserButton().addActionListener(this);
            systemView.getLogoutButton().addActionListener(this);
            
            logger.info("Button listeners set up successfully");
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error setting up button listeners", e);
        }
    }
    
    /**
     * Handle button click events
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String command = e.getActionCommand();
            logger.info(() -> "Button clicked: " + command);
            
            switch (command) {
                 case "Permission" -> showPermissionPage();
                 case "Role"          -> showRolePage();
                 case "User"         -> showUserPage();
                 case "Logout"      -> handleLogout();
                 default               -> logger.warning(() -> "Unknown command: " + command);
             } 
            
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error handling button action", ex);
        }
    }
    
    /**
     * Show the permission management page
     */
    private void showPermissionPage() {
        try {
            // Load permissions data
            permissionController.loadAllPermissions();
            
            // Switch to permission card
            cardLayout.show(contentPanel, PERMISSION_CARD);
            
            logger.info("Permission page displayed successfully");
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error showing permission page", e);
        }
    }
    
    /**
     * Show the role management page
     */
    private void showRolePage() {
        try {
            // Load roles data
            roleController.loadAllRoles();
            
            // Switch to role card
            cardLayout.show(contentPanel, ROLE_CARD);
            
            logger.info("Role page displayed successfully");
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error showing role page", e);
        }
    }
    
    /**
     * Show the user management page
     */
    private void showUserPage() {
        try {
            // Switch to user card
            cardLayout.show(contentPanel, USER_CARD);
            
            logger.info("User page displayed successfully");
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error showing user page", e);
        }
    }
    
    /**
     * Handle logout action
     */
    private void handleLogout() {
        try {
            int confirm = javax.swing.JOptionPane.showConfirmDialog(
                systemView,
                "Are you sure you want to logout?",
                "Confirm Logout",
                javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.QUESTION_MESSAGE
            );
            
            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                logger.info("User logged out");
                // Close the application or return to login screen
                System.exit(0);
            }
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error handling logout", e);
        }
    }
    
    /**
     * Get the permission controller instance
     * 
     * @return the PermissionController instance
     */
    public PermissionController getPermissionController() {
        return permissionController;
    }
    
    /**
     * Get the role controller instance
     * 
     * @return the RoleController instance
     */
    public RoleController getRoleController() {
        return roleController;
    }
    
    /**
     * Show welcome page
     */
    public void showWelcomePage() {
        cardLayout.show(contentPanel, WELCOME_CARD);
    }
}