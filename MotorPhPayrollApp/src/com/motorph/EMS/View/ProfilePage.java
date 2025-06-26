/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.motorph.EMS.View;

import com.motorph.EMS.Model.Employee;
import com.motorph.EMS.Service.EmployeeUpdateService;
import com.motorph.EMS.Service.EmployeeRetrievalService;
import com.motorph.database.execution.SQLExecutor;
import com.motorph.usermanagement.model.Access;
import com.motorph.usermanagement.model.Admin;
import com.motorph.usermanagement.model.NonAdmin;
import com.motorph.usermanagement.model.User;
import com.motorph.usermanagement.view.LoginPage;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * This frame displays an employee's complete profile.
 * It is part of the Employee Self-Service portal and is accessible by both admins and non-admin users.
 * 
 */
public class ProfilePage extends javax.swing.JFrame {

    private final User user;
    private final Connection connection;
    private final EmployeeRetrievalService retrievalService;
    private Employee currentEmployee;

    /**
     * Constructs the profile page and loads the current employee's details.
     *
     * @param user       The logged-in user (Admin or NonAdmin).
     * @param connection Active database connection.
     */
    public ProfilePage(User user, Connection connection) {
        initComponents();
        this.user = user;
        this.connection = connection;

        // Register logout listener
        user.addLogoutListener(this);

        // Hide admin-only components if the user is not an admin
        if (user instanceof NonAdmin) {
            jButton3AdminPortal.setVisible(false);
        }

        // Initialize service to retrieve employee data
        this.retrievalService = new EmployeeRetrievalService(new SQLExecutor((java.sql.Connection) connection));
        
        // TODO!!!: Replace hardcoded ID with dynamic retrieval when user.getEmployeeId() is functional
        loadEmployeeDetails(10052);

        disableAllTextFields(); // Prevent edits to the profile form
    }

    /**
     * Loads and displays an employee's profile details from the database.
     *
     * @param employeeId The ID of the employee to load.
     */
    private void loadEmployeeDetails(int employeeId) {
    try {
        currentEmployee = retrievalService.getEmployeeById(employeeId);

        if (currentEmployee == null) {
            JOptionPane.showMessageDialog(this, "Employee not found.");
            return;
        }

        // Personal info
        jTextField1EmployeeNo.setText(String.valueOf(currentEmployee.getEmployeeId()));
        jTextField1FirstName.setText(currentEmployee.getFirstName());
        jTextField2LastName.setText(currentEmployee.getLastName());
        jTextField2Birthday.setText(String.valueOf(currentEmployee.getBirthday()));
        jTextField4Email.setText(currentEmployee.getEmail());
        jTextField5PhoneNumber.setText(currentEmployee.getPhoneNumber());

        // Address
        jTextField4HouseNo.setText(currentEmployee.getHouseNumber());
        jTextField4Street.setText(currentEmployee.getStreet());
        jTextField4Baranggay.setText(currentEmployee.getBarangay());
        jTextField4Municipality.setText(currentEmployee.getMunicipality());
        jTextField4Province.setText(currentEmployee.getProvince());
        jTextField4PostalCode.setText(currentEmployee.getPostalCode());
        jTextField4Country.setText(currentEmployee.getCountry());
        jTextField4AddressType.setText(currentEmployee.getAddressType());

        // Government Info
        jTextField6SSS.setText(currentEmployee.getSssNumber());
        jTextField7Philhealth.setText(currentEmployee.getPhilhealthNumber());
        jTextField8TIN.setText(currentEmployee.getTaxIdentificationNumber());
        jTextField13Pagibig.setText(currentEmployee.getPagibigNumber());

        // Employment Info
        jTextField16EmployementType.setText(currentEmployee.getEmploymentType());
        jTextField15EmployementStatus.setText(currentEmployee.getEmploymentStatus());
        jTextFieldDateHired.setText(String.valueOf(currentEmployee.getDateHired()));
        jTextField10JobTitle.setText(currentEmployee.getJobTitle());
        jTextFieldDepartment.setText(currentEmployee.getDepartment());
        jTextFieldSupervisorId.setText(String.valueOf(currentEmployee.getSupervisorId()));

        // Salary Info
        jTextFieldSalaryGrade.setText(String.valueOf(currentEmployee.getSalaryGrade()));
        jTextField11BasicSalary.setText(currentEmployee.getBasicSalary().toString());
        jTextField12RiceSubsidy.setText(currentEmployee.getRiceSubsidy().toString());
        jTextField14PhoneAllowance.setText(currentEmployee.getPhoneAllowance().toString());
        jTextField9ClothingAllowance.setText(currentEmployee.getClothingAllowance().toString());
        jTextField17GrossSemiRate.setText(currentEmployee.getGrossSemiMonthlyRate().toString());
        jTextField18HourlyRate.setText(currentEmployee.getHourlyRate().toString());

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Failed to load employee data: " + e.getMessage());
    }
}
    
    /**
     * Disables all input fields to ensure the profile is read-only.
     * Used when displaying employee information without allowing edits.
     */
    private void disableAllTextFields() {
        jTextField1EmployeeNo.setEnabled(false);
        jTextField1FirstName.setEnabled(false);
        jTextField2LastName.setEnabled(false);
        jTextField2Birthday.setEnabled(false);
        jTextField6SSS.setEnabled(false);
        jTextField7Philhealth.setEnabled(false);
        jTextField8TIN.setEnabled(false);
        jTextField13Pagibig.setEnabled(false);
        jTextField10JobTitle.setEnabled(false);
        jTextField15EmployementStatus.setEnabled(false);
        jTextField16EmployementType.setEnabled(false);
        jTextFieldDateHired.setEnabled(false);
        jTextFieldDepartment.setEnabled(false);
        jTextFieldSupervisorId.setEnabled(false);
        jTextFieldSalaryGrade.setEnabled(false);
        jTextField11BasicSalary.setEnabled(false);
        jTextField12RiceSubsidy.setEnabled(false);
        jTextField14PhoneAllowance.setEnabled(false);
        jTextField9ClothingAllowance.setEnabled(false);
        jTextField17GrossSemiRate.setEnabled(false);
        jTextField18HourlyRate.setEnabled(false);
        jTextField5PhoneNumber.setEnabled(false);
        jTextField4Email.setEnabled(false);
        jTextField4HouseNo.setEnabled(false);
        jTextField4Street.setEnabled(false);
        jTextField4Baranggay.setEnabled(false);
        jTextField4Municipality.setEnabled(false);
        jTextField4Province.setEnabled(false);
        jTextField4PostalCode.setEnabled(false);
        jTextField4Country.setEnabled(false);
        jTextField4AddressType.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton6LogOut = new javax.swing.JButton();
        jButton3AdminPortal = new javax.swing.JButton();
        jButtonInformation = new javax.swing.JButton();
        jButton4Attendance = new javax.swing.JButton();
        jButton4Payroll3 = new javax.swing.JButton();
        jButtonRequest = new javax.swing.JButton();
        jLabel5EnterDetails = new javax.swing.JLabel();
        jTabbedPanelInformation = new javax.swing.JTabbedPane();
        jPanelPersonal = new javax.swing.JPanel();
        jLabel1FirstName = new javax.swing.JLabel();
        jLabel2FLastName = new javax.swing.JLabel();
        jTextField1FirstName = new javax.swing.JTextField();
        jTextField2LastName = new javax.swing.JTextField();
        jLabel3Birthday = new javax.swing.JLabel();
        jLabel4Email = new javax.swing.JLabel();
        jTextField4Email = new javax.swing.JTextField();
        jLabel6PhoneNumber = new javax.swing.JLabel();
        jTextField5PhoneNumber = new javax.swing.JTextField();
        jButtonUpdateRecord = new javax.swing.JButton();
        jLabel4Address = new javax.swing.JLabel();
        jTextField4HouseNo = new javax.swing.JTextField();
        jLabel4HouseNo = new javax.swing.JLabel();
        jLabel4Street = new javax.swing.JLabel();
        jTextField4Street = new javax.swing.JTextField();
        jLabel4Baranggay = new javax.swing.JLabel();
        jTextField4Baranggay = new javax.swing.JTextField();
        jLabel4Municipality = new javax.swing.JLabel();
        jTextField4Municipality = new javax.swing.JTextField();
        jLabel4Province = new javax.swing.JLabel();
        jTextField4Province = new javax.swing.JTextField();
        jLabel4PostalCode = new javax.swing.JLabel();
        jTextField4PostalCode = new javax.swing.JTextField();
        jLabel4Country = new javax.swing.JLabel();
        jTextField4Country = new javax.swing.JTextField();
        jLabel4AddressType = new javax.swing.JLabel();
        jTextField4AddressType = new javax.swing.JTextField();
        jTextField1EmployeeNo = new javax.swing.JTextField();
        jLabel1EmployeeNo = new javax.swing.JLabel();
        jTextField2Birthday = new javax.swing.JTextField();
        jPanelGovernment = new javax.swing.JPanel();
        jLabel7SSS = new javax.swing.JLabel();
        jTextField6SSS = new javax.swing.JTextField();
        jLabel8Philhealth = new javax.swing.JLabel();
        jLabel9TIN = new javax.swing.JLabel();
        jLabel13Pagibig = new javax.swing.JLabel();
        jTextField7Philhealth = new javax.swing.JTextField();
        jTextField8TIN = new javax.swing.JTextField();
        jTextField13Pagibig = new javax.swing.JTextField();
        jPanelEmployment = new javax.swing.JPanel();
        jLabel15EmployementStatus = new javax.swing.JLabel();
        jLabel10JobTitle = new javax.swing.JLabel();
        jLabel16EmployementType = new javax.swing.JLabel();
        jTextField16EmployementType = new javax.swing.JTextField();
        jTextField10JobTitle = new javax.swing.JTextField();
        jTextField14PhoneAllowance = new javax.swing.JTextField();
        jLabel17ClothingAllowance = new javax.swing.JLabel();
        jLabel19GrossSemiRate = new javax.swing.JLabel();
        jLabel20HourlyRate = new javax.swing.JLabel();
        jTextField17GrossSemiRate = new javax.swing.JTextField();
        jTextField18HourlyRate = new javax.swing.JTextField();
        jTextField9ClothingAllowance = new javax.swing.JTextField();
        jLabel11BasicSalary = new javax.swing.JLabel();
        jTextField11BasicSalary = new javax.swing.JTextField();
        jLabel12RiceSubsidy = new javax.swing.JLabel();
        jTextField12RiceSubsidy = new javax.swing.JTextField();
        jLabel14PhoneAllowance = new javax.swing.JLabel();
        jTextFieldSupervisorId = new javax.swing.JTextField();
        jLabelDateHired = new javax.swing.JLabel();
        jTextField15EmployementStatus = new javax.swing.JTextField();
        jLabelDepartment = new javax.swing.JLabel();
        jTextFieldDepartment = new javax.swing.JTextField();
        jLabel16SalaryGrade = new javax.swing.JLabel();
        jLabel10SupervisorId = new javax.swing.JLabel();
        jTextFieldSalaryGrade = new javax.swing.JTextField();
        jTextFieldDateHired = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));

        jButton6LogOut.setBackground(new java.awt.Color(0, 102, 153));
        jButton6LogOut.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jButton6LogOut.setForeground(new java.awt.Color(255, 255, 255));
        jButton6LogOut.setText("Log Out");
        jButton6LogOut.setBorder(null);
        jButton6LogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6LogOutActionPerformed(evt);
            }
        });

        jButton3AdminPortal.setBackground(new java.awt.Color(0, 102, 153));
        jButton3AdminPortal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3AdminPortal.setForeground(new java.awt.Color(255, 255, 255));
        jButton3AdminPortal.setText("Admin Portal");
        jButton3AdminPortal.setBorder(null);
        jButton3AdminPortal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3AdminPortalActionPerformed(evt);
            }
        });

        jButtonInformation.setBackground(new java.awt.Color(0, 102, 153));
        jButtonInformation.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonInformation.setForeground(new java.awt.Color(255, 255, 255));
        jButtonInformation.setText("Informations");
        jButtonInformation.setBorder(null);

        jButton4Attendance.setBackground(new java.awt.Color(0, 102, 153));
        jButton4Attendance.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4Attendance.setForeground(new java.awt.Color(255, 255, 255));
        jButton4Attendance.setText("Attendance");
        jButton4Attendance.setBorder(null);
        jButton4Attendance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4AttendanceActionPerformed(evt);
            }
        });

        jButton4Payroll3.setBackground(new java.awt.Color(0, 102, 153));
        jButton4Payroll3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4Payroll3.setForeground(new java.awt.Color(255, 255, 255));
        jButton4Payroll3.setText("Payslip");
        jButton4Payroll3.setBorder(null);
        jButton4Payroll3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4Payroll3ActionPerformed(evt);
            }
        });

        jButtonRequest.setBackground(new java.awt.Color(0, 102, 153));
        jButtonRequest.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonRequest.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRequest.setText("Request");
        jButtonRequest.setBorder(null);
        jButtonRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRequestActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6LogOut, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(jButton3AdminPortal, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(jButtonInformation, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(jButton4Attendance, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(jButton4Payroll3, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(jButtonRequest, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jButton3AdminPortal)
                .addGap(30, 30, 30)
                .addComponent(jButtonInformation)
                .addGap(30, 30, 30)
                .addComponent(jButton4Attendance)
                .addGap(30, 30, 30)
                .addComponent(jButton4Payroll3)
                .addGap(30, 30, 30)
                .addComponent(jButtonRequest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 274, Short.MAX_VALUE)
                .addComponent(jButton6LogOut)
                .addContainerGap())
        );

        jLabel5EnterDetails.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5EnterDetails.setText("Employee Information");

        jTabbedPanelInformation.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPanelInformationStateChanged(evt);
            }
        });

        jLabel1FirstName.setText("First Name");

        jLabel2FLastName.setText("Last Name");

        jLabel3Birthday.setText("Birthday");

        jLabel4Email.setText("Email");

        jTextField4Email.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField4EmailMouseClicked(evt);
            }
        });

        jLabel6PhoneNumber.setText("Phone Number");

        jTextField5PhoneNumber.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField5PhoneNumberMouseClicked(evt);
            }
        });

        jButtonUpdateRecord.setText("Update Record");
        jButtonUpdateRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateRecordActionPerformed(evt);
            }
        });

        jLabel4Address.setText("Address:");

        jTextField4HouseNo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField4HouseNoMouseClicked(evt);
            }
        });

        jLabel4HouseNo.setText("House no.");

        jLabel4Street.setText("Street");

        jTextField4Street.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField4StreetMouseClicked(evt);
            }
        });

        jLabel4Baranggay.setText("Barangay");

        jTextField4Baranggay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField4BaranggayMouseClicked(evt);
            }
        });

        jLabel4Municipality.setText("Municipality");

        jTextField4Municipality.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField4MunicipalityMouseClicked(evt);
            }
        });

        jLabel4Province.setText("Province");

        jTextField4Province.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField4ProvinceMouseClicked(evt);
            }
        });

        jLabel4PostalCode.setText("Postal Code");

        jTextField4PostalCode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField4PostalCodeMouseClicked(evt);
            }
        });

        jLabel4Country.setText("Country");

        jTextField4Country.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField4CountryMouseClicked(evt);
            }
        });

        jLabel4AddressType.setText("Address Type");

        jTextField4AddressType.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField4AddressTypeMouseClicked(evt);
            }
        });

        jLabel1EmployeeNo.setText("Employee No.");

        javax.swing.GroupLayout jPanelPersonalLayout = new javax.swing.GroupLayout(jPanelPersonal);
        jPanelPersonal.setLayout(jPanelPersonalLayout);
        jPanelPersonalLayout.setHorizontalGroup(
            jPanelPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPersonalLayout.createSequentialGroup()
                .addGroup(jPanelPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPersonalLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanelPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6PhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4Email, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPersonalLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2FLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3Birthday, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1FirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1EmployeeNo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanelPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPersonalLayout.createSequentialGroup()
                        .addGroup(jPanelPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelPersonalLayout.createSequentialGroup()
                                .addComponent(jTextField1FirstName)
                                .addGap(168, 168, 168)
                                .addComponent(jLabel4HouseNo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelPersonalLayout.createSequentialGroup()
                                .addComponent(jTextField1EmployeeNo)
                                .addGap(168, 168, 168)
                                .addComponent(jLabel4Address, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField4HouseNo, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelPersonalLayout.createSequentialGroup()
                        .addComponent(jTextField2LastName)
                        .addGap(168, 168, 168)
                        .addComponent(jLabel4Street, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField4Street, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelPersonalLayout.createSequentialGroup()
                        .addGroup(jPanelPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField4Email, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                            .addComponent(jTextField5PhoneNumber))
                        .addGap(168, 168, 168)
                        .addGroup(jPanelPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelPersonalLayout.createSequentialGroup()
                                .addComponent(jLabel4Province, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField4Province, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelPersonalLayout.createSequentialGroup()
                                .addComponent(jLabel4Municipality, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField4Municipality, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelPersonalLayout.createSequentialGroup()
                                .addComponent(jLabel4PostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField4PostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelPersonalLayout.createSequentialGroup()
                                .addComponent(jLabel4Country, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField4Country, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelPersonalLayout.createSequentialGroup()
                                .addComponent(jLabel4AddressType, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField4AddressType, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPersonalLayout.createSequentialGroup()
                        .addComponent(jTextField2Birthday)
                        .addGap(168, 168, 168)
                        .addComponent(jLabel4Baranggay, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField4Baranggay, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(68, 68, 68))
            .addGroup(jPanelPersonalLayout.createSequentialGroup()
                .addGap(379, 379, 379)
                .addComponent(jButtonUpdateRecord)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelPersonalLayout.setVerticalGroup(
            jPanelPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPersonalLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanelPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelPersonalLayout.createSequentialGroup()
                        .addGroup(jPanelPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4Address)
                            .addComponent(jTextField1EmployeeNo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1EmployeeNo))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField4HouseNo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4HouseNo)
                            .addComponent(jTextField1FirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1FirstName))
                        .addGap(21, 21, 21)
                        .addGroup(jPanelPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2LastName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2FLastName)))
                    .addGroup(jPanelPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField4Street, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4Street)))
                .addGroup(jPanelPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPersonalLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanelPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField4Baranggay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4Baranggay))
                            .addComponent(jLabel3Birthday))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPersonalLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2Birthday, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanelPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField4Municipality, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4Municipality)
                        .addComponent(jTextField5PhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6PhoneNumber)))
                .addGap(18, 18, 18)
                .addGroup(jPanelPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField4Province, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4Province)
                        .addComponent(jTextField4Email, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4Email)))
                .addGap(18, 18, 18)
                .addGroup(jPanelPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4PostalCode)
                    .addComponent(jTextField4PostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4Country)
                    .addComponent(jTextField4Country, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4AddressType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4AddressType))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(jButtonUpdateRecord)
                .addContainerGap())
        );

        jTabbedPanelInformation.addTab("Personal", jPanelPersonal);

        jLabel7SSS.setText("SSS #");

        jLabel8Philhealth.setText("Philhealth #");

        jLabel9TIN.setText("TIN #");

        jLabel13Pagibig.setText("Pag-ibig #");

        javax.swing.GroupLayout jPanelGovernmentLayout = new javax.swing.GroupLayout(jPanelGovernment);
        jPanelGovernment.setLayout(jPanelGovernmentLayout);
        jPanelGovernmentLayout.setHorizontalGroup(
            jPanelGovernmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGovernmentLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanelGovernmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel13Pagibig, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9TIN, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8Philhealth, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7SSS, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanelGovernmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelGovernmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField6SSS)
                        .addComponent(jTextField7Philhealth)
                        .addComponent(jTextField8TIN, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField13Pagibig, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(624, Short.MAX_VALUE))
        );
        jPanelGovernmentLayout.setVerticalGroup(
            jPanelGovernmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGovernmentLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanelGovernmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6SSS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7SSS))
                .addGap(26, 26, 26)
                .addGroup(jPanelGovernmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7Philhealth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8Philhealth))
                .addGap(27, 27, 27)
                .addGroup(jPanelGovernmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField13Pagibig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13Pagibig))
                .addGap(34, 34, 34)
                .addGroup(jPanelGovernmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField8TIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9TIN))
                .addContainerGap(246, Short.MAX_VALUE))
        );

        jTabbedPanelInformation.addTab("Government", jPanelGovernment);

        jLabel15EmployementStatus.setText("Employement Status");

        jLabel10JobTitle.setText("Job Title");

        jLabel16EmployementType.setText("Employement Type");

        jLabel17ClothingAllowance.setText("Clothing Allowance");

        jLabel19GrossSemiRate.setText("Gross Semi-monthly Rate");

        jLabel20HourlyRate.setText("Hourly Rate");

        jLabel11BasicSalary.setText("Basic Salary");

        jLabel12RiceSubsidy.setText("Rice Subsidy");

        jLabel14PhoneAllowance.setText("Phone Allowance");

        jLabelDateHired.setText("Date Hired");

        jLabelDepartment.setText("Department");

        jLabel16SalaryGrade.setText("Salary Grade");

        jLabel10SupervisorId.setText("Supervisor ID");

        javax.swing.GroupLayout jPanelEmploymentLayout = new javax.swing.GroupLayout(jPanelEmployment);
        jPanelEmployment.setLayout(jPanelEmploymentLayout);
        jPanelEmploymentLayout.setHorizontalGroup(
            jPanelEmploymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEmploymentLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelEmploymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10JobTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16EmployementType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15EmployementStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelDateHired, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10SupervisorId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelDepartment, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16SalaryGrade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanelEmploymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelEmploymentLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanelEmploymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField15EmployementStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                            .addComponent(jTextField10JobTitle)
                            .addComponent(jTextFieldSupervisorId)
                            .addComponent(jTextField16EmployementType)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelEmploymentLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanelEmploymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldSalaryGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelEmploymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldDateHired)
                                .addComponent(jTextFieldDepartment)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addGroup(jPanelEmploymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14PhoneAllowance, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12RiceSubsidy, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11BasicSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17ClothingAllowance)
                    .addComponent(jLabel20HourlyRate)
                    .addComponent(jLabel19GrossSemiRate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelEmploymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField11BasicSalary, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                    .addComponent(jTextField12RiceSubsidy)
                    .addComponent(jTextField14PhoneAllowance)
                    .addComponent(jTextField9ClothingAllowance)
                    .addComponent(jTextField17GrossSemiRate)
                    .addComponent(jTextField18HourlyRate))
                .addGap(80, 80, 80))
        );
        jPanelEmploymentLayout.setVerticalGroup(
            jPanelEmploymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEmploymentLayout.createSequentialGroup()
                .addGroup(jPanelEmploymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelEmploymentLayout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addGroup(jPanelEmploymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField10JobTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10JobTitle)
                            .addComponent(jLabel14PhoneAllowance)
                            .addComponent(jTextField14PhoneAllowance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanelEmploymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldSupervisorId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10SupervisorId)
                            .addComponent(jLabel17ClothingAllowance)
                            .addComponent(jTextField9ClothingAllowance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelEmploymentLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanelEmploymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField16EmployementType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16EmployementType)
                            .addComponent(jLabel11BasicSalary)
                            .addComponent(jTextField11BasicSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanelEmploymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField15EmployementStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15EmployementStatus)
                            .addComponent(jLabel12RiceSubsidy)
                            .addComponent(jTextField12RiceSubsidy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(34, 34, 34)
                .addGroup(jPanelEmploymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDepartment)
                    .addComponent(jLabel19GrossSemiRate)
                    .addComponent(jTextField17GrossSemiRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanelEmploymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDateHired, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDateHired)
                    .addComponent(jLabel20HourlyRate)
                    .addComponent(jTextField18HourlyRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanelEmploymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSalaryGrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16SalaryGrade))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        jTabbedPanelInformation.addTab("Employment", jPanelEmployment);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel5EnterDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPanelInformation)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel5EnterDetails)
                        .addGap(18, 18, 18)
                        .addComponent(jTabbedPanelInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6LogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6LogOutActionPerformed
        LoginPage loginPage = new LoginPage();
        loginPage.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton6LogOutActionPerformed

    private void jButton4AttendanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4AttendanceActionPerformed
        Access.accessEmployeeAttendance(user);
        this.setVisible(false);
    }//GEN-LAST:event_jButton4AttendanceActionPerformed

    private void jButton4Payroll3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4Payroll3ActionPerformed
        Access.accessEmployeePayslip(user);
        this.setVisible(false);
    }//GEN-LAST:event_jButton4Payroll3ActionPerformed

    private void jTabbedPanelInformationStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPanelInformationStateChanged
        if(jTabbedPanelInformation.getSelectedIndex() == 1 || jTabbedPanelInformation.getSelectedIndex() == 2){
            if (user instanceof NonAdmin){
                jButtonUpdateRecord.setVisible(false);
                return;
            }
        }
        jButtonUpdateRecord.setVisible(true);
    }//GEN-LAST:event_jTabbedPanelInformationStateChanged

    private void jButtonRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRequestActionPerformed
        Access.accessRequestCenter(user);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonRequestActionPerformed

    private void jButton3AdminPortalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3AdminPortalActionPerformed
        Access.accessCompanyHomePage((Admin) user); 
        this.setVisible(false);
    }//GEN-LAST:event_jButton3AdminPortalActionPerformed

    private void jButtonUpdateRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateRecordActionPerformed
    try {
        boolean isModified = false; // Flag to track if any of these fields have been modified

        String newEmail = jTextField4Email.getText().trim();
        if (!newEmail.equals(currentEmployee.getEmail())) {
            currentEmployee.setEmail(newEmail);
            isModified = true;
        }

        String newPhone = jTextField5PhoneNumber.getText().trim();
        if (!newPhone.equals(currentEmployee.getPhoneNumber())) {
            currentEmployee.setPhoneNumber(newPhone);
            isModified = true;
        }

        String newHouse = jTextField4HouseNo.getText().trim();
        if (!newHouse.equals(currentEmployee.getHouseNumber())) {
            currentEmployee.setHouseNumber(newHouse);
            isModified = true;
        }

        String newStreet = jTextField4Street.getText().trim();
        if (!newStreet.equals(currentEmployee.getStreet())) {
            currentEmployee.setStreet(newStreet);
            isModified = true;
        }

        String newBarangay = jTextField4Baranggay.getText().trim();
        if (!newBarangay.equals(currentEmployee.getBarangay())) {
            currentEmployee.setBarangay(newBarangay);
            isModified = true;
        }

        String newMunicipality = jTextField4Municipality.getText().trim();
        if (!newMunicipality.equals(currentEmployee.getMunicipality())) {
            currentEmployee.setMunicipality(newMunicipality);
            isModified = true;
        }

        String newProvince = jTextField4Province.getText().trim();
        if (!newProvince.equals(currentEmployee.getProvince())) {
            currentEmployee.setProvince(newProvince);
            isModified = true;
        }

        String newPostalCode = jTextField4PostalCode.getText().trim();
        if (!newPostalCode.equals(currentEmployee.getPostalCode())) {
            currentEmployee.setPostalCode(newPostalCode);
            isModified = true;
        }

        String newCountry = jTextField4Country.getText().trim();
        if (!newCountry.equals(currentEmployee.getCountry())) {
            currentEmployee.setCountry(newCountry);
            isModified = true;
        }

        String newAddressType = jTextField4AddressType.getText().trim();
        if (!newAddressType.equals(currentEmployee.getAddressType())) {
            currentEmployee.setAddressType(newAddressType);
            isModified = true;
        }

        //Perform update if any field has changed
        if (isModified) {
            EmployeeUpdateService updateService = new EmployeeUpdateService((java.sql.Connection) connection);
            updateService.updateEmployee(currentEmployee);
            JOptionPane.showMessageDialog(this, "Successfully updated.");
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Update failed. Please check your input or try again.");
    }
    }//GEN-LAST:event_jButtonUpdateRecordActionPerformed

    private void jTextField4HouseNoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4HouseNoMouseClicked
        jTextField4HouseNo.setEnabled(true);
    }//GEN-LAST:event_jTextField4HouseNoMouseClicked

    private void jTextField4StreetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4StreetMouseClicked
        jTextField4Street.setEnabled(true);
    }//GEN-LAST:event_jTextField4StreetMouseClicked

    private void jTextField4BaranggayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4BaranggayMouseClicked
        jTextField4Baranggay.setEnabled(true);
    }//GEN-LAST:event_jTextField4BaranggayMouseClicked

    private void jTextField4MunicipalityMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MunicipalityMouseClicked
        jTextField4Municipality.setEnabled(true);
    }//GEN-LAST:event_jTextField4MunicipalityMouseClicked

    private void jTextField4ProvinceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4ProvinceMouseClicked
        jTextField4Province.setEnabled(true);
    }//GEN-LAST:event_jTextField4ProvinceMouseClicked

    private void jTextField4PostalCodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4PostalCodeMouseClicked
        jTextField4PostalCode.setEnabled(true);
    }//GEN-LAST:event_jTextField4PostalCodeMouseClicked

    private void jTextField4CountryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4CountryMouseClicked
        jTextField4Country.setEnabled(true);
    }//GEN-LAST:event_jTextField4CountryMouseClicked

    private void jTextField4AddressTypeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4AddressTypeMouseClicked
        jTextField4AddressType.setEnabled(true);
    }//GEN-LAST:event_jTextField4AddressTypeMouseClicked

    private void jTextField4EmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4EmailMouseClicked
        jTextField4Email.setEnabled(true);
    }//GEN-LAST:event_jTextField4EmailMouseClicked

    private void jTextField5PhoneNumberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField5PhoneNumberMouseClicked
        jTextField5PhoneNumber.setEnabled(true);
    }//GEN-LAST:event_jTextField5PhoneNumberMouseClicked

    /**
     * @param args the command line arguments
     */
public static void main(String args[]) {
    try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        java.util.logging.Logger.getLogger(ProfilePage.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
    }

    java.awt.EventQueue.invokeLater(() -> {
            try {
                Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/payrollsystem_db", "root", "admin"
                );

            // Dummy user for now (replace with a real User object once available)!!!
            NonAdmin dummyUser = new NonAdmin("nonadminUsername", "nonadminPassword");

            new ProfilePage(dummyUser, conn).setVisible(true);
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Database connection failed: " + e.getMessage());
        }
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3AdminPortal;
    private javax.swing.JButton jButton4Attendance;
    private javax.swing.JButton jButton4Payroll3;
    private javax.swing.JButton jButton6LogOut;
    private javax.swing.JButton jButtonInformation;
    private javax.swing.JButton jButtonRequest;
    private javax.swing.JButton jButtonUpdateRecord;
    private javax.swing.JLabel jLabel10JobTitle;
    private javax.swing.JLabel jLabel10SupervisorId;
    private javax.swing.JLabel jLabel11BasicSalary;
    private javax.swing.JLabel jLabel12RiceSubsidy;
    private javax.swing.JLabel jLabel13Pagibig;
    private javax.swing.JLabel jLabel14PhoneAllowance;
    private javax.swing.JLabel jLabel15EmployementStatus;
    private javax.swing.JLabel jLabel16EmployementType;
    private javax.swing.JLabel jLabel16SalaryGrade;
    private javax.swing.JLabel jLabel17ClothingAllowance;
    private javax.swing.JLabel jLabel19GrossSemiRate;
    private javax.swing.JLabel jLabel1EmployeeNo;
    private javax.swing.JLabel jLabel1FirstName;
    private javax.swing.JLabel jLabel20HourlyRate;
    private javax.swing.JLabel jLabel2FLastName;
    private javax.swing.JLabel jLabel3Birthday;
    private javax.swing.JLabel jLabel4Address;
    private javax.swing.JLabel jLabel4AddressType;
    private javax.swing.JLabel jLabel4Baranggay;
    private javax.swing.JLabel jLabel4Country;
    private javax.swing.JLabel jLabel4Email;
    private javax.swing.JLabel jLabel4HouseNo;
    private javax.swing.JLabel jLabel4Municipality;
    private javax.swing.JLabel jLabel4PostalCode;
    private javax.swing.JLabel jLabel4Province;
    private javax.swing.JLabel jLabel4Street;
    private javax.swing.JLabel jLabel5EnterDetails;
    private javax.swing.JLabel jLabel6PhoneNumber;
    private javax.swing.JLabel jLabel7SSS;
    private javax.swing.JLabel jLabel8Philhealth;
    private javax.swing.JLabel jLabel9TIN;
    private javax.swing.JLabel jLabelDateHired;
    private javax.swing.JLabel jLabelDepartment;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelEmployment;
    private javax.swing.JPanel jPanelGovernment;
    private javax.swing.JPanel jPanelPersonal;
    private javax.swing.JTabbedPane jTabbedPanelInformation;
    private javax.swing.JTextField jTextField10JobTitle;
    private javax.swing.JTextField jTextField11BasicSalary;
    private javax.swing.JTextField jTextField12RiceSubsidy;
    private javax.swing.JTextField jTextField13Pagibig;
    private javax.swing.JTextField jTextField14PhoneAllowance;
    private javax.swing.JTextField jTextField15EmployementStatus;
    private javax.swing.JTextField jTextField16EmployementType;
    private javax.swing.JTextField jTextField17GrossSemiRate;
    private javax.swing.JTextField jTextField18HourlyRate;
    private javax.swing.JTextField jTextField1EmployeeNo;
    private javax.swing.JTextField jTextField1FirstName;
    private javax.swing.JTextField jTextField2Birthday;
    private javax.swing.JTextField jTextField2LastName;
    private javax.swing.JTextField jTextField4AddressType;
    private javax.swing.JTextField jTextField4Baranggay;
    private javax.swing.JTextField jTextField4Country;
    private javax.swing.JTextField jTextField4Email;
    private javax.swing.JTextField jTextField4HouseNo;
    private javax.swing.JTextField jTextField4Municipality;
    private javax.swing.JTextField jTextField4PostalCode;
    private javax.swing.JTextField jTextField4Province;
    private javax.swing.JTextField jTextField4Street;
    private javax.swing.JTextField jTextField5PhoneNumber;
    private javax.swing.JTextField jTextField6SSS;
    private javax.swing.JTextField jTextField7Philhealth;
    private javax.swing.JTextField jTextField8TIN;
    private javax.swing.JTextField jTextField9ClothingAllowance;
    private javax.swing.JTextField jTextFieldDateHired;
    private javax.swing.JTextField jTextFieldDepartment;
    private javax.swing.JTextField jTextFieldSalaryGrade;
    private javax.swing.JTextField jTextFieldSupervisorId;
    // End of variables declaration//GEN-END:variables
   
}

