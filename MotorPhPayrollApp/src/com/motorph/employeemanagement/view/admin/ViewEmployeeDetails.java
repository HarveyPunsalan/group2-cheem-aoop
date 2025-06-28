/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.motorph.employeemanagement.view.admin;

import com.motorph.employeemanagement.model.PersonalInformation;
import com.motorph.employeemanagement.service.InformationService;
import com.motorph.employeemanagement.model.GovernmentInformation;
import com.motorph.employeemanagement.model.EmploymentInformation;
import com.motorph.employeemanagement.model.Employee;
import com.motorph.employeemanagement.service.EmployeeService;
import com.motorph.usermanagement.model.Admin;
import com.motorph.usermanagement.model.NonAdmin;
import com.motorph.usermanagement.model.Access;
import com.motorph.common.util.Formatter;
import com.motorph.validation.ComponentsValidator;
import com.motorph.usermanagement.util.ValidationUtils;
import com.motorph.common.ui.dialog.MessageDialog;
import com.motorph.usermanagement.model.User;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
/**
 *
 * @author Charm
 */
public class ViewEmployeeDetails extends javax.swing.JFrame {
    SimpleDateFormat dateFormat = new SimpleDateFormat("M/dd/yyyy");
    EmployeeService employeeManager;
    String employeeID; // Employee ID for lookup
    Employee employee; // Employee object (not currently used in the provided code)
    boolean isNewEmployee; // Flag to determine if it's a new employee record
    String[] employeeInformation; // Array to store employee details
    User user;
    PersonalInformation personalInformation;
    GovernmentInformation governmentInformation;
    EmploymentInformation employmentInformation;

    /**
     * Creates new form ViewEmployeeDetails
     */
    public ViewEmployeeDetails() {
        initComponents();
        jLabelErrorMessage.setVisible(false);
        jButton1EditProfile.setVisible(false);
        this.isNewEmployee = true;
        jButton2SaveChanges.setText("Add Employee"); // Change button text to indicate adding a new employee     
    }
    
    public ViewEmployeeDetails(Admin admin) {
        initComponents();
        jLabelErrorMessage.setVisible(false);
        jButton1EditProfile.setVisible(false);
        this.isNewEmployee = true; 
        this.user = admin;
        admin.addLogoutListener(this);
        
        jButton2SaveChanges.setText("Add Employee"); // Change button text to indicate adding a new employee     
    }
    
     /**
     * Constructor for viewing or editing an existing employee's details.
     * Loads employee information based on the provided Employee ID.
     *
     * @param user
     * @param employeeID ID of the employee to be viewed/edited.
     */
    public ViewEmployeeDetails(User user, String employeeID)  {
        initComponents();
        jLabelErrorMessage.setVisible(false);
        jButton1EditProfile.setVisible(false);
        isNewEmployee = false;
        this.employeeID = employeeID;     
        this.employee = new EmployeeService().getEmployeeInformation(employeeID);
        this.user = user;
        user.addLogoutListener(this);
        
//        // Populate the text fields with the existing employee data
//        jTextField1LastName.setText(this.employee.getLastName());
//        jTextField2FirstName.setText(this.employee.getFirstName());
//        ((JTextField)jDateChooserBirthday.getDateEditor().getUiComponent()).setText(this.employee.getBirthday());        
//        jTextField4Address.setText(this.employee.getAddress());
//        jTextField5PhoneNumber.setText(this.employee.getPhoneNumber());
//        jTextField6SSS.setText(this.employee.getSSSNumber());
//        jTextField7Philhealth.setText(this.employee.getPhilhealthNumber());
//        jTextField8TIN.setText(this.employee.getTinNumber());
//        jTextField13Pagibig.setText(this.employee.getPagibigNumber());
//        jTextField15Status.setText(this.employee.getEmploymentStatus());
//        jTextField16Position.setText(this.employee.getPosition());
//        jTextField10ImmediateSupervisor.setText(this.employee.getImmediateSupervisor());
//        jTextField11BasicSalary.setText(Formatter.formatAmount(this.employee.getBasicSalary()));
//        jTextField12RiceSubsidy.setText(Formatter.formatAmount(this.employee.getRiceSubsidy()));
//        jTextField14PhoneAllowance.setText(Formatter.formatAmount(this.employee.getPhoneAllowance()));
//        jTextField9ClothingAllowance.setText(Formatter.formatAmount(this.employee.getClothingAllowance()));
//        jTextField17GrossSemi.setText(Formatter.formatAmount(this.employee.getGrossSemiMonthlyRate()));
//        jTextField18HourlyRate.setText(Formatter.formatAmount(this.employee.getHourlyRate()));
               
        disableAllTextField(); // Disable text fields to prevent accidental modifications
        
        this.personalInformation = new InformationService().getPersonalInformation(employeeID);
        this.governmentInformation = new InformationService().getGovernmentInformation(employeeID);
        this.employmentInformation = new InformationService().getEmploymentInformation(employeeID);
        viewPersonalInformation(this.personalInformation);
        viewGovernmentInformation(this.governmentInformation);
        viewEmploymentInformation(this.employmentInformation);
        
        if (this.user instanceof NonAdmin){
            jPanelAdmin.setVisible(false);
        }
        
        jDateChooserBirthday.getDateEditor().getUiComponent().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jDateChooserBirthday.setEnabled(true);
            }
        });
        
        jDateChooserDateHired.getDateEditor().getUiComponent().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jDateChooserBirthday.setEnabled(true);
            }
        });
        
    }
    
    public void viewPersonalInformation(PersonalInformation personalInformation){      
    jTextField1LastName.setText(personalInformation.getLastName());
    jTextField2FirstName.setText(personalInformation.getFirstName());
    LocalDate date = this.personalInformation.getBirthday();
    ((JTextField) jDateChooserBirthday.getDateEditor().getUiComponent()).setText(dateFormat.format(date));
    jTextField4Address.setText(personalInformation.getAddress());
    jTextField5PhoneNumber.setText(personalInformation.getPhoneNumber());
    }
    
    public void viewGovernmentInformation(GovernmentInformation governmentInformation){      
        jTextField6SSS.setText(governmentInformation.getSssNumber());
        jTextField7Philhealth.setText(governmentInformation.getPhilhealthNumber());
        jTextField13Pagibig.setText(governmentInformation.getPagibigNumber());
        jTextField8TIN.setText(governmentInformation.getTaxIdentificationNumber());
    }
    
    public void viewEmploymentInformation(EmploymentInformation employmentInformation){      
        jTextField16Position.setText(employmentInformation.getJobTitle().getJobName());
        jTextField15Status.setText(employmentInformation.getEmploymentType());
        jTextField10ImmediateSupervisor.setText(employmentInformation.getImmediateSupervisor());
        LocalDate date = this.employmentInformation.getDateHired();
        ((JTextField) jDateChooserDateHired.getDateEditor().getUiComponent()).setText(dateFormat.format(date));
        jTextField11BasicSalary.setText(employmentInformation.getBasicSalary());
        jTextField12RiceSubsidy.setText(employmentInformation.getRiceSubsidy());
        jTextField14PhoneAllowance.setText(employmentInformation.getPhoneAllowance());
        jTextField9ClothingAllowance.setText(employmentInformation.getClothingAllowance());
        jTextField17GrossSemi.setText(employmentInformation.getGrossSemiMonthlyRate());
        jTextField18HourlyRate.setText(employmentInformation.getHourlyRate());
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
        jPanelAdmin = new javax.swing.JPanel();
        jButton1EmployeeInformation = new javax.swing.JButton();
        jButton3EmployeeRequest = new javax.swing.JButton();
        jButton4Payroll = new javax.swing.JButton();
        jButton6LogOut = new javax.swing.JButton();
        jButton3SelfServicePortal = new javax.swing.JButton();
        jButton4Attendance = new javax.swing.JButton();
        jTextField1LastName = new javax.swing.JTextField();
        jTextField2FirstName = new javax.swing.JTextField();
        jLabel1LastName = new javax.swing.JLabel();
        jLabel2FirstName = new javax.swing.JLabel();
        jLabel3Birthday = new javax.swing.JLabel();
        jLabel4Address = new javax.swing.JLabel();
        jTextField4Address = new javax.swing.JTextField();
        jLabel5EditEmployeeDetails = new javax.swing.JLabel();
        jLabel6PhoneNumber = new javax.swing.JLabel();
        jTextField5PhoneNumber = new javax.swing.JTextField();
        jLabel7SSS = new javax.swing.JLabel();
        jTextField6SSS = new javax.swing.JTextField();
        jLabel8Philhealth = new javax.swing.JLabel();
        jTextField7Philhealth = new javax.swing.JTextField();
        jLabel9TIN = new javax.swing.JLabel();
        jTextField8TIN = new javax.swing.JTextField();
        jTextField9ClothingAllowance = new javax.swing.JTextField();
        jLabel10ImmediateSupervisor = new javax.swing.JLabel();
        jTextField10ImmediateSupervisor = new javax.swing.JTextField();
        jLabel11BasicSalary = new javax.swing.JLabel();
        jTextField11BasicSalary = new javax.swing.JTextField();
        jLabel12RiceSubsidy = new javax.swing.JLabel();
        jTextField12RiceSubsidy = new javax.swing.JTextField();
        jLabel13Pagibig = new javax.swing.JLabel();
        jLabel14PhoneAllowance = new javax.swing.JLabel();
        jTextField13Pagibig = new javax.swing.JTextField();
        jLabel15Status = new javax.swing.JLabel();
        jTextField14PhoneAllowance = new javax.swing.JTextField();
        jTextField15Status = new javax.swing.JTextField();
        jLabel16Position = new javax.swing.JLabel();
        jLabel17ClothingAllowance = new javax.swing.JLabel();
        jTextField16Position = new javax.swing.JTextField();
        jLabel19GrossSemi = new javax.swing.JLabel();
        jLabel20HourlyRate = new javax.swing.JLabel();
        jTextField17GrossSemi = new javax.swing.JTextField();
        jTextField18HourlyRate = new javax.swing.JTextField();
        jButton1EditProfile = new javax.swing.JButton();
        jButton2SaveChanges = new javax.swing.JButton();
        jDateChooserBirthday = new com.toedter.calendar.JDateChooser();
        jLabelErrorMessage = new javax.swing.JLabel();
        jLabelDateHired = new javax.swing.JLabel();
        jDateChooserDateHired = new com.toedter.calendar.JDateChooser();

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

        jPanelAdmin.setBackground(new java.awt.Color(0, 102, 153));

        jButton1EmployeeInformation.setBackground(new java.awt.Color(0, 102, 153));
        jButton1EmployeeInformation.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1EmployeeInformation.setForeground(new java.awt.Color(255, 255, 255));
        jButton1EmployeeInformation.setText("Employee Information");
        jButton1EmployeeInformation.setBorder(null);
        jButton1EmployeeInformation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1EmployeeInformationActionPerformed(evt);
            }
        });

        jButton3EmployeeRequest.setBackground(new java.awt.Color(0, 102, 153));
        jButton3EmployeeRequest.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3EmployeeRequest.setForeground(new java.awt.Color(255, 255, 255));
        jButton3EmployeeRequest.setText("Employee Request");
        jButton3EmployeeRequest.setBorder(null);
        jButton3EmployeeRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3EmployeeRequestActionPerformed(evt);
            }
        });

        jButton4Payroll.setBackground(new java.awt.Color(0, 102, 153));
        jButton4Payroll.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4Payroll.setForeground(new java.awt.Color(255, 255, 255));
        jButton4Payroll.setText("Payroll");
        jButton4Payroll.setBorder(null);
        jButton4Payroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4PayrollActionPerformed(evt);
            }
        });

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

        jButton3SelfServicePortal.setBackground(new java.awt.Color(0, 102, 153));
        jButton3SelfServicePortal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3SelfServicePortal.setForeground(new java.awt.Color(255, 255, 255));
        jButton3SelfServicePortal.setText("Self Service Portal");
        jButton3SelfServicePortal.setBorder(null);
        jButton3SelfServicePortal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3SelfServicePortalActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanelAdminLayout = new javax.swing.GroupLayout(jPanelAdmin);
        jPanelAdmin.setLayout(jPanelAdminLayout);
        jPanelAdminLayout.setHorizontalGroup(
            jPanelAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1EmployeeInformation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3EmployeeRequest, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(jButton4Payroll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(jButton6LogOut, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(jButton3SelfServicePortal, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(jButton4Attendance, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelAdminLayout.setVerticalGroup(
            jPanelAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAdminLayout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jButton3SelfServicePortal)
                .addGap(30, 30, 30)
                .addComponent(jButton3EmployeeRequest)
                .addGap(30, 30, 30)
                .addComponent(jButton1EmployeeInformation)
                .addGap(30, 30, 30)
                .addComponent(jButton4Payroll)
                .addGap(30, 30, 30)
                .addComponent(jButton4Attendance)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6LogOut)
                .addContainerGap())
        );

        jTextField1LastName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1LastNameMouseClicked(evt);
            }
        });

        jTextField2FirstName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField2FirstNameMouseClicked(evt);
            }
        });

        jLabel1LastName.setText("Last Name");

        jLabel2FirstName.setText("First Name");

        jLabel3Birthday.setText("Birthday");

        jLabel4Address.setText("Address");

        jTextField4Address.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField4AddressMouseClicked(evt);
            }
        });

        jLabel5EditEmployeeDetails.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5EditEmployeeDetails.setText("Employee Details");

        jLabel6PhoneNumber.setText("Phone Number");

        jTextField5PhoneNumber.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField5PhoneNumberMouseClicked(evt);
            }
        });

        jLabel7SSS.setText("SSS #");

        jTextField6SSS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField6SSSMouseClicked(evt);
            }
        });

        jLabel8Philhealth.setText("Philhealth #");

        jTextField7Philhealth.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField7PhilhealthMouseClicked(evt);
            }
        });

        jLabel9TIN.setText("TIN #");

        jTextField8TIN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField8TINMouseClicked(evt);
            }
        });

        jTextField9ClothingAllowance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField9ClothingAllowanceMouseClicked(evt);
            }
        });

        jLabel10ImmediateSupervisor.setText("Immediate Supervisor");

        jTextField10ImmediateSupervisor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField10ImmediateSupervisorMouseClicked(evt);
            }
        });

        jLabel11BasicSalary.setText("Basic Salary");

        jTextField11BasicSalary.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField11BasicSalaryMouseClicked(evt);
            }
        });

        jLabel12RiceSubsidy.setText("Rice Subsidy");

        jTextField12RiceSubsidy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField12RiceSubsidyMouseClicked(evt);
            }
        });

        jLabel13Pagibig.setText("Pag-ibig #");

        jLabel14PhoneAllowance.setText("Phone Allowance");

        jTextField13Pagibig.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField13PagibigMouseClicked(evt);
            }
        });

        jLabel15Status.setText("Status");

        jTextField14PhoneAllowance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField14PhoneAllowanceMouseClicked(evt);
            }
        });

        jTextField15Status.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField15StatusMouseClicked(evt);
            }
        });

        jLabel16Position.setText("Position");

        jLabel17ClothingAllowance.setText("Clothing Allowance");

        jTextField16Position.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField16PositionMouseClicked(evt);
            }
        });

        jLabel19GrossSemi.setText("Gross Semi-monthly Rate");

        jLabel20HourlyRate.setText("Hourly Rate");

        jTextField17GrossSemi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField17GrossSemiMouseClicked(evt);
            }
        });

        jTextField18HourlyRate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField18HourlyRateMouseClicked(evt);
            }
        });

        jButton1EditProfile.setText("Edit Profile");
        jButton1EditProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1EditProfileActionPerformed(evt);
            }
        });

        jButton2SaveChanges.setText("Save Changes");
        jButton2SaveChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2SaveChangesActionPerformed(evt);
            }
        });

        jDateChooserBirthday.setDateFormatString("M/d/yyyy");
        jDateChooserBirthday.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDateChooserBirthdayMouseClicked(evt);
            }
        });

        jLabelErrorMessage.setForeground(java.awt.Color.red);
        jLabelErrorMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelErrorMessage.setText("Error Message");

        jLabelDateHired.setText("Date Hired");

        jDateChooserDateHired.setDateFormatString("M/d/yyyy");
        jDateChooserDateHired.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDateChooserDateHiredMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelErrorMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2FirstName, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                                    .addComponent(jLabel1LastName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDateChooserBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField2FirstName, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                        .addComponent(jTextField1LastName))))
                            .addComponent(jLabel5EditEmployeeDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6PhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4Address, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3Birthday, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7SSS, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel9TIN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel8Philhealth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel13Pagibig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel15Status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel16Position, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel10ImmediateSupervisor, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabelDateHired, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGap(86, 86, 86))))
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jTextField5PhoneNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                        .addGap(122, 122, 122)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11BasicSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12RiceSubsidy, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel14PhoneAllowance, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel17ClothingAllowance)
                                            .addComponent(jLabel19GrossSemi)
                                            .addComponent(jLabel20HourlyRate))
                                        .addGap(25, 25, 25)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField11BasicSalary, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                            .addComponent(jTextField12RiceSubsidy)
                                            .addComponent(jTextField9ClothingAllowance)
                                            .addComponent(jTextField17GrossSemi)
                                            .addComponent(jTextField18HourlyRate)
                                            .addComponent(jTextField14PhoneAllowance)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jButton1EditProfile)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton2SaveChanges))
                                    .addComponent(jTextField4Address, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField7Philhealth, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                    .addComponent(jTextField6SSS, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                    .addComponent(jTextField8TIN, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                    .addComponent(jTextField13Pagibig, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                    .addComponent(jTextField15Status, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                    .addComponent(jTextField16Position, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                    .addComponent(jTextField10ImmediateSupervisor, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                    .addComponent(jDateChooserDateHired, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel5EditEmployeeDetails)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelErrorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1LastName)
                            .addComponent(jTextField1LastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2FirstName)
                            .addComponent(jTextField2FirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3Birthday)
                            .addComponent(jDateChooserBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField4Address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4Address))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField11BasicSalary, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6PhoneNumber)
                                .addComponent(jTextField5PhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11BasicSalary)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7SSS)
                                    .addComponent(jLabel12RiceSubsidy)
                                    .addComponent(jTextField12RiceSubsidy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jTextField6SSS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8Philhealth)
                            .addComponent(jTextField7Philhealth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14PhoneAllowance)
                            .addComponent(jTextField14PhoneAllowance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9TIN)
                            .addComponent(jTextField8TIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17ClothingAllowance)
                            .addComponent(jTextField9ClothingAllowance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13Pagibig)
                            .addComponent(jTextField13Pagibig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19GrossSemi)
                            .addComponent(jTextField17GrossSemi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15Status)
                            .addComponent(jTextField15Status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20HourlyRate)
                            .addComponent(jTextField18HourlyRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16Position)
                            .addComponent(jTextField16Position, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10ImmediateSupervisor)
                            .addComponent(jTextField10ImmediateSupervisor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelDateHired)
                            .addComponent(jDateChooserDateHired, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2SaveChanges)
                            .addComponent(jButton1EditProfile))
                        .addGap(15, 15, 15))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1EmployeeInformationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1EmployeeInformationActionPerformed
        Access.accessEmployeeInformation((Admin) this.user);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1EmployeeInformationActionPerformed

    private void jButton6LogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6LogOutActionPerformed
        user.logout(this);
    }//GEN-LAST:event_jButton6LogOutActionPerformed

    /**
     * Saves changes to an employee record or adds a new employee.
     * If adding a new employee, the record is appended to the employee list.
     * If updating, the corresponding record is modified.
     */
    private void jButton2SaveChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2SaveChangesActionPerformed
        String validateRequiredTextFields = ComponentsValidator.validateRequiredJTextField(getAllTextField());
        if (!validateRequiredTextFields.isEmpty()) {
            MessageDialog.showErrorMessage(validateRequiredTextFields);
            return;
        }

        String validateRequiredJDateChooser = ComponentsValidator.validateRequiredJDateChooser(jDateChooserBirthday);
        if (!validateRequiredJDateChooser.isEmpty()) {
            MessageDialog.showErrorMessage(validateRequiredJDateChooser);
            return;
        }

        StringBuilder validationErrors = validateAllTextField();
        if (!validationErrors.toString().isEmpty()) {
            MessageDialog.showErrorMessage(validationErrors.toString());
            System.out.print("Still have Error");
            return;
        }       
        
        employeeManager = new EmployeeService();
        
        if (isNewEmployee) {
            Employee newEmployee = getEmployee(); 
            employeeManager.addEmployee(newEmployee);
            this.employeeID = newEmployee.getEmployeeID();
            
            employeeManager.addEmployeeInformation(getPersonalInformation());
            
//            employeeManager.addEmployeeInformation(getPersonalInformation(), getEmploymentInformation(), getGovernmentInformation());
            
            clearAllTextField(); // Prevent further edits

            JOptionPane.showMessageDialog(null, "Successfully Added Employee"); // Confirmation message
            return; // Exit method, keeping the form in adding mode
        }

//        employeeManager.updateInformation(getEmployee());
        employeeManager.updateInformation(getEmployee(), getPersonalInformation(), getEmploymentInformation(), getGovernmentInformation());
            
        disableAllTextField(); // Disable text fields after saving
            
        JOptionPane.showMessageDialog(null, "Successfully Updated"); // Confirmation message
        
    }//GEN-LAST:event_jButton2SaveChangesActionPerformed

    private void jTextField1LastNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1LastNameMouseClicked
        jTextField1LastName.setEnabled(true);
    }//GEN-LAST:event_jTextField1LastNameMouseClicked

    private void jTextField2FirstNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2FirstNameMouseClicked
        jTextField2FirstName.setEnabled(true);
    }//GEN-LAST:event_jTextField2FirstNameMouseClicked

    private void jTextField4AddressMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4AddressMouseClicked
        jTextField4Address.setEnabled(true);
    }//GEN-LAST:event_jTextField4AddressMouseClicked

    private void jTextField5PhoneNumberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField5PhoneNumberMouseClicked
        jTextField5PhoneNumber.setEnabled(true);
    }//GEN-LAST:event_jTextField5PhoneNumberMouseClicked

    private void jTextField6SSSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField6SSSMouseClicked
        jTextField6SSS.setEnabled(true);
    }//GEN-LAST:event_jTextField6SSSMouseClicked

    private void jTextField7PhilhealthMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField7PhilhealthMouseClicked
        jTextField7Philhealth.setEnabled(true);
    }//GEN-LAST:event_jTextField7PhilhealthMouseClicked

    private void jTextField8TINMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField8TINMouseClicked
        jTextField8TIN.setEnabled(true);
    }//GEN-LAST:event_jTextField8TINMouseClicked

    private void jTextField13PagibigMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField13PagibigMouseClicked
        jTextField13Pagibig.setEnabled(true);
    }//GEN-LAST:event_jTextField13PagibigMouseClicked

    private void jTextField15StatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField15StatusMouseClicked
        jTextField15Status.setEnabled(true);
    }//GEN-LAST:event_jTextField15StatusMouseClicked

    private void jTextField16PositionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField16PositionMouseClicked
        jTextField16Position.setEnabled(true);
    }//GEN-LAST:event_jTextField16PositionMouseClicked

    private void jTextField10ImmediateSupervisorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField10ImmediateSupervisorMouseClicked
        jTextField10ImmediateSupervisor.setEnabled(true);
    }//GEN-LAST:event_jTextField10ImmediateSupervisorMouseClicked

    private void jTextField11BasicSalaryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField11BasicSalaryMouseClicked
        jTextField11BasicSalary.setEnabled(true);
    }//GEN-LAST:event_jTextField11BasicSalaryMouseClicked

    private void jTextField12RiceSubsidyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField12RiceSubsidyMouseClicked
        jTextField12RiceSubsidy.setEnabled(true);
    }//GEN-LAST:event_jTextField12RiceSubsidyMouseClicked

    private void jTextField14PhoneAllowanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField14PhoneAllowanceMouseClicked
        jTextField14PhoneAllowance.setEnabled(true);
    }//GEN-LAST:event_jTextField14PhoneAllowanceMouseClicked

    private void jTextField9ClothingAllowanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField9ClothingAllowanceMouseClicked
        jTextField9ClothingAllowance.setEnabled(true);
    }//GEN-LAST:event_jTextField9ClothingAllowanceMouseClicked

    private void jTextField17GrossSemiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField17GrossSemiMouseClicked
        jTextField17GrossSemi.setEnabled(true);
    }//GEN-LAST:event_jTextField17GrossSemiMouseClicked

    private void jTextField18HourlyRateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField18HourlyRateMouseClicked
        jTextField18HourlyRate.setEnabled(true);
    }//GEN-LAST:event_jTextField18HourlyRateMouseClicked

    private void jButton3SelfServicePortalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3SelfServicePortalActionPerformed
        Access.accessProfilePage(this.user);
        this.setVisible(false);
    }//GEN-LAST:event_jButton3SelfServicePortalActionPerformed

    private void jButton4PayrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4PayrollActionPerformed
        Access.accessPayrollList((Admin) this.user);
        this.setVisible(false);
    }//GEN-LAST:event_jButton4PayrollActionPerformed

    private void jButton4AttendanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4AttendanceActionPerformed
        Access.accessAttendanceBiweekly((Admin) this.user);
        this.setVisible(false);
    }//GEN-LAST:event_jButton4AttendanceActionPerformed

    private void jDateChooserBirthdayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooserBirthdayMouseClicked
//        jDateChooserBirthday.setEnabled(true);        
    }//GEN-LAST:event_jDateChooserBirthdayMouseClicked

    private void jButton3EmployeeRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3EmployeeRequestActionPerformed
        Access.accessEmployeeRequests((Admin) this.user);
        this.setVisible(false);
    }//GEN-LAST:event_jButton3EmployeeRequestActionPerformed

    private void jButton1EditProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1EditProfileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1EditProfileActionPerformed

    private void jDateChooserDateHiredMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooserDateHiredMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooserDateHiredMouseClicked

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//  
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ViewEmployeeDetails().setVisible(true);
//            }
//        });
//    }
    
        /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewEmployeeDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewEmployeeDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewEmployeeDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewEmployeeDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewEmployeeDetails().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1EditProfile;
    private javax.swing.JButton jButton1EmployeeInformation;
    private javax.swing.JButton jButton2SaveChanges;
    private javax.swing.JButton jButton3EmployeeRequest;
    private javax.swing.JButton jButton3SelfServicePortal;
    private javax.swing.JButton jButton4Attendance;
    private javax.swing.JButton jButton4Payroll;
    private javax.swing.JButton jButton6LogOut;
    private com.toedter.calendar.JDateChooser jDateChooserBirthday;
    private com.toedter.calendar.JDateChooser jDateChooserDateHired;
    private javax.swing.JLabel jLabel10ImmediateSupervisor;
    private javax.swing.JLabel jLabel11BasicSalary;
    private javax.swing.JLabel jLabel12RiceSubsidy;
    private javax.swing.JLabel jLabel13Pagibig;
    private javax.swing.JLabel jLabel14PhoneAllowance;
    private javax.swing.JLabel jLabel15Status;
    private javax.swing.JLabel jLabel16Position;
    private javax.swing.JLabel jLabel17ClothingAllowance;
    private javax.swing.JLabel jLabel19GrossSemi;
    private javax.swing.JLabel jLabel1LastName;
    private javax.swing.JLabel jLabel20HourlyRate;
    private javax.swing.JLabel jLabel2FirstName;
    private javax.swing.JLabel jLabel3Birthday;
    private javax.swing.JLabel jLabel4Address;
    private javax.swing.JLabel jLabel5EditEmployeeDetails;
    private javax.swing.JLabel jLabel6PhoneNumber;
    private javax.swing.JLabel jLabel7SSS;
    private javax.swing.JLabel jLabel8Philhealth;
    private javax.swing.JLabel jLabel9TIN;
    private javax.swing.JLabel jLabelDateHired;
    private javax.swing.JLabel jLabelErrorMessage;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelAdmin;
    private javax.swing.JTextField jTextField10ImmediateSupervisor;
    private javax.swing.JTextField jTextField11BasicSalary;
    private javax.swing.JTextField jTextField12RiceSubsidy;
    private javax.swing.JTextField jTextField13Pagibig;
    private javax.swing.JTextField jTextField14PhoneAllowance;
    private javax.swing.JTextField jTextField15Status;
    private javax.swing.JTextField jTextField16Position;
    private javax.swing.JTextField jTextField17GrossSemi;
    private javax.swing.JTextField jTextField18HourlyRate;
    private javax.swing.JTextField jTextField1LastName;
    private javax.swing.JTextField jTextField2FirstName;
    private javax.swing.JTextField jTextField4Address;
    private javax.swing.JTextField jTextField5PhoneNumber;
    private javax.swing.JTextField jTextField6SSS;
    private javax.swing.JTextField jTextField7Philhealth;
    private javax.swing.JTextField jTextField8TIN;
    private javax.swing.JTextField jTextField9ClothingAllowance;
    // End of variables declaration//GEN-END:variables
    
    /**
     * Disables all text fields to prevent editing.
     * This is used when viewing an existing employee record.
     */
    public void disableAllTextField(){
        jTextField1LastName.setEnabled(false);
        jTextField2FirstName.setEnabled(false);
        jDateChooserBirthday.setEnabled(false);
        jTextField4Address.setEnabled(false);
        jTextField5PhoneNumber.setEnabled(false);
        jTextField6SSS.setEnabled(false);
        jTextField7Philhealth.setEnabled(false);
        jTextField8TIN.setEnabled(false);
        jTextField13Pagibig.setEnabled(false);
        jTextField15Status.setEnabled(false);
        jTextField16Position.setEnabled(false);
        jTextField10ImmediateSupervisor.setEnabled(false);
        jDateChooserDateHired.setEnabled(false);
        jTextField11BasicSalary.setEnabled(false);
        jTextField12RiceSubsidy.setEnabled(false);
        jTextField14PhoneAllowance.setEnabled(false);
        jTextField9ClothingAllowance.setEnabled(false);
        jTextField17GrossSemi.setEnabled(false);
        jTextField18HourlyRate.setEnabled(false);
    }
    
    public void clearAllTextField(){
        jTextField1LastName.setText("");
        jTextField2FirstName.setText("");
        jDateChooserBirthday.setDate(null);
        jTextField4Address.setText("");
        jTextField5PhoneNumber.setText("");
        jTextField6SSS.setText("");
        jTextField7Philhealth.setText("");
        jTextField8TIN.setText("");
        jTextField13Pagibig.setText("");
        jTextField15Status.setText("");
        jTextField16Position.setText("");
        jTextField10ImmediateSupervisor.setText("");
        jDateChooserDateHired.setDate(null);       
        jTextField11BasicSalary.setText("");
        jTextField12RiceSubsidy.setText("");
        jTextField14PhoneAllowance.setText("");
        jTextField9ClothingAllowance.setText("");
        jTextField17GrossSemi.setText("");
        jTextField18HourlyRate.setText("");
    }
    
    /**
     * Gathers all employee details from input fields and returns them as an array.
     *
     * @return String array containing employee details.
     */
    public Employee getEmployee(){
        String[] i = {this.employeeID, 
                    jTextField1LastName.getText(),
                    jTextField2FirstName.getText(),
                    ((JTextField)jDateChooserBirthday.getDateEditor().getUiComponent()).getText(),
                    jTextField4Address.getText(),
                    Formatter.formatPhoneNumber(jTextField5PhoneNumber.getText()),
                    Formatter.formatSSS(jTextField6SSS.getText()),
                    jTextField7Philhealth.getText(),
                    Formatter.formatTIN(jTextField8TIN.getText()),
                    jTextField13Pagibig.getText(),
                    jTextField15Status.getText(),
                    jTextField16Position.getText(),
                    jTextField10ImmediateSupervisor.getText(),
                    jTextField11BasicSalary.getText(),
                    jTextField12RiceSubsidy.getText(),
                    jTextField14PhoneAllowance.getText(),
                    jTextField9ClothingAllowance.getText(),
                    jTextField17GrossSemi.getText(),
                    jTextField18HourlyRate.getText()
                    };        
        return this.employee = new Employee(i);
    }
    
//    public PersonalInformation getPersonalInformation() {
//        String[] i = {this.employeeID,
//            jTextField1LastName.getText(),
//            jTextField2FirstName.getText(),
//            ((JTextField) jDateChooserBirthday.getDateEditor().getUiComponent()).getText().trim(),
//            jTextField4Address.getText(),
//            Formatter.formatPhoneNumber(jTextField5PhoneNumber.getText())
//        };
//        return this.personalInformation = new PersonalInformation(this.employeeID, i);
//    }
    
    public PersonalInformation getPersonalInformation() {
        return this.personalInformation = new PersonalInformation(this.employeeID, 
                                                                jTextField2FirstName.getText(),
                                                                jTextField1LastName.getText(),                                                                
                                                                jDateChooserBirthday.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                                                                jTextField5PhoneNumber.getText(),                                                                
                                                                jTextField4Address.getText()
                                                                );
    }

    public GovernmentInformation getGovernmentInformation() {
        String[] i = {this.employeeID,
            Formatter.formatSSS(jTextField6SSS.getText()),
            jTextField7Philhealth.getText(),
            Formatter.formatTIN(jTextField8TIN.getText()),
            jTextField13Pagibig.getText()
        };
        return this.governmentInformation = new GovernmentInformation(this.employeeID, i);
    }

    public EmploymentInformation getEmploymentInformation() {
        String[] i = {this.employeeID,
            jTextField16Position.getText(),            
            jTextField15Status.getText(),
            "Active",            
            ((JTextField) jDateChooserDateHired.getDateEditor().getUiComponent()).getText().trim(),
            jTextField10ImmediateSupervisor.getText(),
            jTextField11BasicSalary.getText(),
            jTextField12RiceSubsidy.getText(),
            jTextField14PhoneAllowance.getText(),
            jTextField9ClothingAllowance.getText(),
            jTextField17GrossSemi.getText(),
            jTextField18HourlyRate.getText()
        };
        return this.employmentInformation = new EmploymentInformation(this.employeeID, i);
    }
    
    public JTextField[] getAllTextField(){
        JTextField[] textFields = {
            jTextField1LastName,
            jTextField2FirstName,
            jTextField4Address,
            jTextField5PhoneNumber,
//            jTextField6SSS,
//            jTextField7Philhealth,
//            jTextField8TIN,
//            jTextField13Pagibig,
//            jTextField15Status,
//            jTextField16Position,
//            jTextField10ImmediateSupervisor,
//            jTextField11BasicSalary,
//            jTextField12RiceSubsidy,
//            jTextField14PhoneAllowance,
//            jTextField9ClothingAllowance,
//            jTextField17GrossSemi,
//            jTextField18HourlyRate
        };
        return textFields;            
    }
    
    public StringBuilder validateAllTextField(){
        StringBuilder errors = new StringBuilder();
    
        appendError(errors, ValidationUtils.isValidPhoneNumber(jTextField5PhoneNumber.getText()));
        appendError(errors, ValidationUtils.isValidBirthday(jDateChooserBirthday, "M/d/yyyy"));
//        appendError(errors, Input.isValidGovernmentIDNumber(jTextField6SSS, "SSS", 10));
//        appendError(errors, Input.isValidGovernmentIDNumber(jTextField7Philhealth, "PhilHealth", 12));
//        appendError(errors, Input.isValidGovernmentIDNumber(jTextField8TIN, "TIN", 12));
//        appendError(errors, Input.isValidGovernmentIDNumber(jTextField13Pagibig, "Pag-IBIG", 12));
//        appendError(errors, Input.isValidAmount(jTextField11BasicSalary.getText(), "Basic Salary"));
//        appendError(errors, Input.isValidAmount(jTextField12RiceSubsidy.getText(), "Rice Subsidy"));
//        appendError(errors, Input.isValidAmount(jTextField14PhoneAllowance.getText(), "Phone Allowance"));
//        appendError(errors, Input.isValidAmount(jTextField9ClothingAllowance.getText(), "Clothing Allowance"));
//        appendError(errors, Input.isValidAmount(jTextField17GrossSemi.getText(), "Gross Semi-monthly Rate"));
//        appendError(errors, Input.isValidAmount(jTextField18HourlyRate.getText(), "Hourly Rate"));
//        appendError(errors, Input.isValidDateHired(jDateChooserDateHired, "M/d/yyyy"));
        

        return errors;         
    }
    
    // Helper method to append error messages only if they exist
    private void appendError(StringBuilder errors, String errorMessage) {
        if (!errorMessage.isEmpty()) {
            errors.append(errorMessage).append("\n");
        }
    }

    public String[] getEmployeeInformation() {
        return employeeInformation;
    }

    public void setEmployeeInformation(String[] employeeInformtation) {
        this.employeeInformation = employeeInformtation;
    } 
    
}
