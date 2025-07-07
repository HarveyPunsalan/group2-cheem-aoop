/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.motorph.usermanagement.view;

import com.motorph.usermanagement.controller.UserController;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Harvey 
 */
public class UserManagementPage extends javax.swing.JPanel {

    private final UserController userController;

    /**
     * Creates new form UserManagementPage
     */
    public UserManagementPage() {
        initComponents();
        
        // Initialize controller after components are created
        userController = new UserController(this);
        
        // Add event listeners
        setupEventListeners();
        
        // Load initial data
        userController.loadAllUsers();
        userController.updateUserCount();
    }
    
    /**
     * Sets up event listeners for buttons and other components
     */
    private void setupEventListeners() {
        // Search functionality
        jButtonSearch.addActionListener(e -> {
            String searchTerm = jTextFieldUserNameSearch.getText();
            userController.searchUsers(searchTerm);
        });
        
        // Clear search
        jButtonClear.addActionListener(e -> {
            jTextFieldUserNameSearch.setText("");
            userController.loadAllUsers();
        });
        
        // Load all users
        jButtonLoadAllUsers.addActionListener(e -> userController.loadAllUsers());
        
        // Load active users only
        jButtonLoadActiveOnly.addActionListener(e -> userController.loadActiveUsers());
        
        // Refresh count
        jButtonRefreshCount.addActionListener(e -> userController.updateUserCount());
                
        // Create user
        jButtonCreateUser.addActionListener(e -> {
            String username = jTextFieldPutUsername.getText();
            String password = jTextFieldPutPassword.getText();
            String employeeIdText = jTextFieldPutEmployeeID.getText();
            String roleSelection = (String) jComboBoxChooseRoleID.getSelectedItem();
            
            // Convert employeeIdText to int - THIS IS THE FIX
            int employeeId = Integer.parseInt(employeeIdText);
            
            // Extract role ID from selection (e.g., "Admin (1)" -> 1)
            int roleId = extractRoleId(roleSelection);
            
            // Call controller to create user
            userController.createUser(username, password, employeeId, roleId);
            
            // Clear form after creation
            clearUserForm();
        });
        
        // Clear placeholder text when user clicks on text fields
        jTextFieldUserNameSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (jTextFieldUserNameSearch.getText().equals("Enter Username")) {
                    jTextFieldUserNameSearch.setText("");
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (jTextFieldUserNameSearch.getText().isEmpty()) {
                    jTextFieldUserNameSearch.setText("Enter Username");
                }
            }
        });
        
        
        
        jTextFieldPutUsername.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (jTextFieldPutUsername.getText().equals("Enter Username")) {
                    jTextFieldPutUsername.setText("");
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (jTextFieldPutUsername.getText().isEmpty()) {
                    jTextFieldPutUsername.setText("Enter Username");
                }
            }
        });
        
        jTextFieldPutEmployeeID.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (jTextFieldPutEmployeeID.getText().equals("Enter employee ID")) {
                    jTextFieldPutEmployeeID.setText("");
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (jTextFieldPutEmployeeID.getText().isEmpty()) {
                    jTextFieldPutEmployeeID.setText("Enter employee ID");
                }
            }
        });
        
        jTextFieldPutPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (jTextFieldPutPassword.getText().equals("Enter Password")) {
                    jTextFieldPutPassword.setText("");
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (jTextFieldPutPassword.getText().isEmpty()) {
                    jTextFieldPutPassword.setText("Enter Password");
                }
            }
        });
    }
    
    /**
     * Extracts role ID from combo box selection
     */
    private int extractRoleId(String roleSelection) {
        if (roleSelection.contains("(1)")) return 1;
        if (roleSelection.contains("(2)")) return 2;
        if (roleSelection.contains("(3)")) return 3;
        return 1; // Default to Admin
    }
    
    /**
     * Clears the user creation form
     */
    private void clearUserForm() {
        jTextFieldPutUsername.setText("Enter Username");
        jTextFieldPutEmployeeID.setText("Enter employee ID");
        jTextFieldPutPassword.setText("Enter Password");
        jComboBoxChooseRoleID.setSelectedIndex(0);
    }
    
    /**
     * Sets the table model for the user table
     * This method is called by UserController
     * 
     * @param tableModel the data model to be applied to the user table
     */
    public void setTableModel(DefaultTableModel tableModel) {
        jTableShowInfo.setModel(tableModel);
        
        // Set column widths for better display
        if (jTableShowInfo.getColumnModel().getColumnCount() > 0) {
            jTableShowInfo.getColumnModel().getColumn(0).setPreferredWidth(60);  // User ID
            jTableShowInfo.getColumnModel().getColumn(1).setPreferredWidth(100); // Username
            jTableShowInfo.getColumnModel().getColumn(2).setPreferredWidth(80);  // Employee ID
            jTableShowInfo.getColumnModel().getColumn(3).setPreferredWidth(60);  // Role ID
            jTableShowInfo.getColumnModel().getColumn(4).setPreferredWidth(70);  // Status
            jTableShowInfo.getColumnModel().getColumn(5).setPreferredWidth(120); // Created Date
            jTableShowInfo.getColumnModel().getColumn(6).setPreferredWidth(120); // Last Login
        }
    }
    
    /**
     * Updates the user count display
     * This method is called by UserController
     * 
     * @param totalUsers the total number of users in the system
     * @param activeUsers the number of currently active users
     */
    public void updateUserCountDisplay(int totalUsers, int activeUsers) {
        int inactiveUsers = totalUsers - activeUsers;
        
        jTextFieldUpdateTotalUsers.setText(String.valueOf(totalUsers));
        jTextFieldUpdateActiveUsers.setText(String.valueOf(activeUsers));
        jTextFieldUpdateInactiveUsers.setText(String.valueOf(inactiveUsers));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanelHeader = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldUpdateTotalUsers = new javax.swing.JTextField();
        jTextFieldUpdateInactiveUsers = new javax.swing.JTextField();
        jTextFieldUpdateActiveUsers = new javax.swing.JTextField();
        jLabelTotalUsers = new javax.swing.JLabel();
        jLabelActiveUsers = new javax.swing.JLabel();
        jLabelInactiveUsers = new javax.swing.JLabel();
        jLabelSearch = new javax.swing.JLabel();
        jTextFieldUserNameSearch = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jButtonClear = new javax.swing.JButton();
        jButtonLoadAllUsers = new javax.swing.JButton();
        jButtonLoadActiveOnly = new javax.swing.JButton();
        jButtonRefreshCount = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableShowInfo = new javax.swing.JTable();
        jLabelAddNewUser = new javax.swing.JLabel();
        jLabelUserName = new javax.swing.JLabel();
        jLabelEmployeeID = new javax.swing.JLabel();
        jTextFieldPutUsername = new javax.swing.JTextField();
        jTextFieldPutEmployeeID = new javax.swing.JTextField();
        jLabelPassword = new javax.swing.JLabel();
        jLabelRoleID = new javax.swing.JLabel();
        jTextFieldPutPassword = new javax.swing.JTextField();
        jComboBoxChooseRoleID = new javax.swing.JComboBox<>();
        jButtonCreateUser = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(844, 0));

        jPanel1.setPreferredSize(new java.awt.Dimension(844, 0));

        jPanelHeader.setBackground(new java.awt.Color(0, 102, 153));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("User Management");

        javax.swing.GroupLayout jPanelHeaderLayout = new javax.swing.GroupLayout(jPanelHeader);
        jPanelHeader.setLayout(jPanelHeaderLayout);
        jPanelHeaderLayout.setHorizontalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHeaderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(330, 330, 330))
        );
        jPanelHeaderLayout.setVerticalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jLabelTotalUsers.setText("Total Users");

        jLabelActiveUsers.setText("Active Users");

        jLabelInactiveUsers.setText("Inactive Users");

        jLabelSearch.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabelSearch.setText("Search:");

        jTextFieldUserNameSearch.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldUserNameSearch.setText("Enter Username");

        jButtonSearch.setText("Search");

        jButtonClear.setText("Clear");

        jButtonLoadAllUsers.setText("Load All Users");

        jButtonLoadActiveOnly.setText("Load Active Only");

        jButtonRefreshCount.setText("Refresh Count");

        jTableShowInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "User ID", "Username", "Employee ID", "Role ID", "Status", "Created Date", "Last Login", "Actions"
            }
        ));
        jScrollPane1.setViewportView(jTableShowInfo);

        jLabelAddNewUser.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabelAddNewUser.setText("Add New Employee");

        jLabelUserName.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabelUserName.setText("Username:");

        jLabelEmployeeID.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabelEmployeeID.setText("Employee ID:");

        jTextFieldPutUsername.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldPutUsername.setText("Enter Username");

        jTextFieldPutEmployeeID.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldPutEmployeeID.setText("Enter employee ID");

        jLabelPassword.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabelPassword.setText("Password:");

        jLabelRoleID.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabelRoleID.setText("Role ID:");

        jTextFieldPutPassword.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldPutPassword.setText("Enter Password");

        jComboBoxChooseRoleID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin (1)", "User (2)", "Manager (3)" }));

        jButtonCreateUser.setText("Create User");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelHeader, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(173, 173, 173)
                .addComponent(jTextFieldUpdateTotalUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextFieldUpdateActiveUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(189, 189, 189)
                .addComponent(jTextFieldUpdateInactiveUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(196, 196, 196))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelAddNewUser)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabelEmployeeID)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextFieldPutEmployeeID))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabelUserName)
                                        .addGap(27, 27, 27)
                                        .addComponent(jTextFieldPutUsername))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabelSearch)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldUserNameSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButtonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(44, 44, 44)
                                        .addComponent(jButtonLoadAllUsers)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonLoadActiveOnly)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonRefreshCount))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(218, 218, 218)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jComboBoxChooseRoleID, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jTextFieldPutPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonCreateUser, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelTotalUsers)
                                .addGap(158, 158, 158)
                                .addComponent(jLabelActiveUsers))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelRoleID)
                                .addComponent(jLabelPassword)))
                        .addGap(146, 146, 146)
                        .addComponent(jLabelInactiveUsers)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldUpdateTotalUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldUpdateInactiveUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldUpdateActiveUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTotalUsers)
                    .addComponent(jLabelActiveUsers)
                    .addComponent(jLabelInactiveUsers))
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSearch)
                    .addComponent(jTextFieldUserNameSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSearch)
                    .addComponent(jButtonClear)
                    .addComponent(jButtonLoadAllUsers)
                    .addComponent(jButtonLoadActiveOnly)
                    .addComponent(jButtonRefreshCount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelAddNewUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUserName)
                    .addComponent(jTextFieldPutUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPassword)
                    .addComponent(jTextFieldPutPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelEmployeeID)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldPutEmployeeID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelRoleID)
                        .addComponent(jComboBoxChooseRoleID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonCreateUser)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 847, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonCreateUser;
    private javax.swing.JButton jButtonLoadActiveOnly;
    private javax.swing.JButton jButtonLoadAllUsers;
    private javax.swing.JButton jButtonRefreshCount;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JComboBox<String> jComboBoxChooseRoleID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelActiveUsers;
    private javax.swing.JLabel jLabelAddNewUser;
    private javax.swing.JLabel jLabelEmployeeID;
    private javax.swing.JLabel jLabelInactiveUsers;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelRoleID;
    private javax.swing.JLabel jLabelSearch;
    private javax.swing.JLabel jLabelTotalUsers;
    private javax.swing.JLabel jLabelUserName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableShowInfo;
    private javax.swing.JTextField jTextFieldPutEmployeeID;
    private javax.swing.JTextField jTextFieldPutPassword;
    private javax.swing.JTextField jTextFieldPutUsername;
    private javax.swing.JTextField jTextFieldUpdateActiveUsers;
    private javax.swing.JTextField jTextFieldUpdateInactiveUsers;
    private javax.swing.JTextField jTextFieldUpdateTotalUsers;
    private javax.swing.JTextField jTextFieldUserNameSearch;
    // End of variables declaration//GEN-END:variables
}
