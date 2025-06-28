/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.motorph.employeemanagement.view.admin;

import com.motorph.database.connection.DatabaseService;
import com.motorph.employeemanagement.model.Employee;
import com.motorph.employeemanagement.service.EmployeeUpdateService;
import com.motorph.employeemanagement.service.EmployeeCreationService;
import com.motorph.employeemanagement.service.EmployeeRetrievalService;
import com.motorph.usermanagement.model.*;
import com.motorph.database.execution.SQLExecutor;
import java.sql.Connection;
import java.sql.SQLException;
import java.math.BigDecimal;
import javax.swing.JOptionPane;

/**
 * JFrame for managing employee records.
 * 
 * This form supports two main functions:
 * 1. Adding a new employee.
 * 2. Viewing/editing details of an existing employee.
 * 
 * Access is restricted to admin users only.
 * 
 * Dependencies:
 * - EmployeeRetrievalService: fetches employee data.
 * - EmployeeCreationService: handles new employee creation.
 * - EmployeeUpdateService: updates existing employee records.
 */

public class ViewEmployeeDetails extends javax.swing.JFrame {

    private final Connection connection = DatabaseService.connectToMotorPH();
    private final EmployeeRetrievalService retrievalService;
    private final EmployeeCreationService creationService;
    private final EmployeeUpdateService updateService;

    private User user;
    private Employee currentEmployee;
    
    public ViewEmployeeDetails() {
        initComponents();
        this.user = null;

        this.retrievalService = new EmployeeRetrievalService(new SQLExecutor(connection));
        this.creationService = new EmployeeCreationService(connection);
        this.updateService = new EmployeeUpdateService(connection);

        this.currentEmployee = null; // Indicates adding new record mode
        jButton2SaveChanges.setText("Add Employee");
    }
    
        /**
    * Constructor for adding a new employee.Initializes UI components and services for employee creation.
    *
    *
     * @param employeeID 
    */
    public ViewEmployeeDetails(int employeeID) {
        initComponents();

        this.retrievalService = new EmployeeRetrievalService(new SQLExecutor(connection));
        this.creationService = new EmployeeCreationService(connection);
        this.updateService = new EmployeeUpdateService(connection);

        this.currentEmployee = null; // Indicates adding new record mode
        jButton2SaveChanges.setText("Add Employee");
        
        // Retrieve employee details from DB and populate fields
        this.currentEmployee = retrievalService.getEmployeeById(employeeID);
        loadEmployeeDetails(this.currentEmployee);
        disableAllTextFields();
    }

    /**
    * Constructor for adding a new employee.
    *
    * Initializes UI components and services for employee creation.
    * 
    * @param admin The admin user performing the operation.
    */
    public ViewEmployeeDetails(Admin admin) {
        initComponents();
        this.user = admin;

        this.retrievalService = new EmployeeRetrievalService(new SQLExecutor(connection));
        this.creationService = new EmployeeCreationService(connection);
        this.updateService = new EmployeeUpdateService(connection);

        admin.addLogoutListener(this);

        if (user instanceof NonAdmin) {
            jPanelAdmin.setVisible(false);
        }

        this.currentEmployee = null; // Indicates adding new record mode
        jButton2SaveChanges.setText("Add Employee");
    }

    /**
    * Constructor for viewing/editing an existing employee's record.
    *
    * Fetches employee data from the database and displays it in the form.
    * Fields are read-only by default to prevent unintended edits.
    * 
    * @param admin       The admin user performing the operation.
    * @param employeeID  The ID of the employee whose details are to be displayed.
    */
    public ViewEmployeeDetails(Admin admin, int employeeID) {
        initComponents();
        this.user = admin;

        this.retrievalService = new EmployeeRetrievalService(new SQLExecutor(connection));
        this.creationService = new EmployeeCreationService(connection);
        this.updateService = new EmployeeUpdateService(connection);

        admin.addLogoutListener(this);

        if (user instanceof NonAdmin) {
            jPanelAdmin.setVisible(false);
        }

        // Retrieve employee details from DB and populate fields
        this.currentEmployee = retrievalService.getEmployeeById(employeeID);
        loadEmployeeDetails(this.currentEmployee);
        disableAllTextFields();
    }

    /**
    * Populates the form fields with the data of the specified employee.
    *
    * @param emp The employee whose details are to be loaded into the UI.
    */
    private void loadEmployeeDetails(Employee emp) {
        jTextField2FirstName.setText(emp.getFirstName());
        jTextField1LastName.setText(emp.getLastName());
        jDateChooserBirthday.setDate(emp.getBirthday());
        jTextField5PhoneNumber.setText(emp.getPhoneNumber());
        jTextField6Email.setText(emp.getEmail());

        jTextField5HouseNo.setText(emp.getHouseNumber());
        jTextField7Street.setText(emp.getStreet());
        jTextField8Barangay.setText(emp.getBarangay());
        jTextField13Municipality.setText(emp.getMunicipality());
        jTextField15Province.setText(emp.getProvince());
        jTextField16PostalCode.setText(emp.getPostalCode());
        jTextField10Country.setText(emp.getCountry());
        jTextField10AddressType.setText(emp.getAddressType());

        jTextField4SSS.setText(emp.getSssNumber());
        jTextFieldPhilhealth.setText(emp.getPhilhealthNumber());
        jTextField4Pagibig.setText(emp.getPagibigNumber());
        jTextField4TIN.setText(emp.getTaxIdentificationNumber());

        jTextField10EmployementType.setText(emp.getEmploymentType());
        jTextField4EmployementStatus.setText(emp.getEmploymentStatus());
        jDateChooserDateHired.setDate(emp.getDateHired());
        jTextField4JobTitle.setText(emp.getJobTitle());
        jTextField4Department.setText(emp.getDepartment());
        jTextField4SupervisorId.setText(String.valueOf(emp.getSupervisorId()));

        jTextField11SalaryGrade.setText(String.valueOf(emp.getSalaryGrade()));
        jTextField11BasicSalary.setText(emp.getBasicSalary().toString());
        jTextField12RiceSubsidy.setText(emp.getRiceSubsidy().toString());
        jTextField14PhoneAllowance.setText(emp.getPhoneAllowance().toString());
        jTextField9ClothingAllowance.setText(emp.getClothingAllowance().toString());
        jTextField17GrossSemi.setText(emp.getGrossSemiMonthlyRate().toString());
        jTextField18HourlyRate.setText(emp.getHourlyRate().toString());
    }

    /**
     * Saves the employee form data.
     * If currentEmployee is null, it creates a new employee.
     * Otherwise, it updates the existing employee.
     *
     * @throws SQLException if an error occurs during the save operation.
     */
    private void saveEmployee() throws SQLException {
        Employee emp = collectEmployeeFromFields();

        if (currentEmployee == null) {
            addEmployee(emp);
        } else {
            updateEmployee(emp);
        }
    }

    /**
     * Adds a new employee to the database.
     *
     * @param emp Employee object containing new employee data.
     * @throws SQLException if an error occurs during insertion.
     */
    private void addEmployee(Employee emp) throws SQLException {
        creationService.addEmployee(emp);
    }

    /**
     * Updates an existing employee in the database.
     *
     * @param emp Employee object containing updated data.
     * @throws SQLException if an error occurs during update.
     */
    private void updateEmployee(Employee emp) throws SQLException {
        emp.setEmployeeId(currentEmployee.getEmployeeId());
        updateService.updateEmployee(emp);
    }

    /**
     * Collects data from form fields and creates an Employee object.
     *
     * @return A populated Employee object.
     */
    private Employee collectEmployeeFromFields() {
        Employee emp = new Employee();

            emp.setFirstName(jTextField2FirstName.getText().trim());
            emp.setLastName(jTextField1LastName.getText().trim());
            emp.setBirthday(jDateChooserBirthday.getDate());
            emp.setPhoneNumber(jTextField5PhoneNumber.getText().trim());
            emp.setEmail(jTextField6Email.getText().trim());

            emp.setHouseNumber(jTextField5HouseNo.getText().trim());
            emp.setStreet(jTextField7Street.getText().trim());
            emp.setBarangay(jTextField8Barangay.getText().trim());
            emp.setMunicipality(jTextField13Municipality.getText().trim());
            emp.setProvince(jTextField15Province.getText().trim());
            emp.setPostalCode(jTextField16PostalCode.getText().trim());
            emp.setCountry(jTextField10Country.getText().trim());
            emp.setAddressType(jTextField10AddressType.getText().trim());

            emp.setSssNumber(jTextField4SSS.getText().trim());
            emp.setPhilhealthNumber(jTextFieldPhilhealth.getText().trim());
            emp.setPagibigNumber(jTextField4Pagibig.getText().trim());
            emp.setTaxIdentificationNumber(jTextField4TIN.getText().trim());

            emp.setEmploymentType(jTextField10EmployementType.getText().trim());
            emp.setEmploymentStatus(jTextField4EmployementStatus.getText().trim());
            emp.setDateHired(jDateChooserDateHired.getDate());
            emp.setJobTitle(jTextField4JobTitle.getText().trim());
            emp.setDepartment(jTextField4Department.getText().trim());
            emp.setSupervisorId(Integer.parseInt(jTextField4SupervisorId.getText().trim()));

            emp.setSalaryGrade(Integer.parseInt(jTextField11SalaryGrade.getText().trim()));
            emp.setBasicSalary(new BigDecimal(jTextField11BasicSalary.getText().trim()));
            emp.setRiceSubsidy(new BigDecimal(jTextField12RiceSubsidy.getText().trim()));
            emp.setPhoneAllowance(new BigDecimal(jTextField14PhoneAllowance.getText().trim()));
            emp.setClothingAllowance(new BigDecimal(jTextField9ClothingAllowance.getText().trim()));
            emp.setGrossSemiMonthlyRate(new BigDecimal(jTextField17GrossSemi.getText().trim()));
            emp.setHourlyRate(new BigDecimal(jTextField18HourlyRate.getText().trim()));

            return emp;
    }

    //Disables all editable fields in the form to make it read-only.
    private void disableAllTextFields() {
        jTextField1LastName.setEnabled(false);
        jTextField2FirstName.setEnabled(false);
        jTextField5PhoneNumber.setEnabled(false);
        jTextField6Email.setEnabled(false);
        jTextField5HouseNo.setEnabled(false);
        jTextField7Street.setEnabled(false);
        jTextField8Barangay.setEnabled(false);
        jTextField13Municipality.setEnabled(false);
        jTextField15Province.setEnabled(false);
        jTextField16PostalCode.setEnabled(false);
        jTextField10Country.setEnabled(false);
        jTextField10AddressType.setEnabled(false);
        jTextField4SSS.setEnabled(false);
        jTextFieldPhilhealth.setEnabled(false);
        jTextField4Pagibig.setEnabled(false);
        jTextField4TIN.setEnabled(false);
        jTextField10EmployementType.setEnabled(false);
        jTextField4EmployementStatus.setEnabled(false);
        jTextField4JobTitle.setEnabled(false);
        jTextField4Department.setEnabled(false);
        jTextField4SupervisorId.setEnabled(false);
        jTextField11SalaryGrade.setEnabled(false);
        jTextField11BasicSalary.setEnabled(false);
        jTextField12RiceSubsidy.setEnabled(false);
        jTextField14PhoneAllowance.setEnabled(false);
        jTextField9ClothingAllowance.setEnabled(false);
        jTextField17GrossSemi.setEnabled(false);
        jTextField18HourlyRate.setEnabled(false);

        // Date choosers
        jDateChooserBirthday.getDateEditor().setEnabled(false);
        jDateChooserDateHired.getDateEditor().setEnabled(false);
    }
      
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField4Address6 = new javax.swing.JTextField();
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
        jLabel4HouseNo = new javax.swing.JLabel();
        jTextField4Department = new javax.swing.JTextField();
        jLabel5EditEmployeeDetails = new javax.swing.JLabel();
        jLabel6PhoneNumber = new javax.swing.JLabel();
        jTextField5PhoneNumber = new javax.swing.JTextField();
        jLabel7SSS = new javax.swing.JLabel();
        jTextField6Email = new javax.swing.JTextField();
        jLabel8Philhealth = new javax.swing.JLabel();
        jTextField7Street = new javax.swing.JTextField();
        jLabel9TIN = new javax.swing.JLabel();
        jTextField8Barangay = new javax.swing.JTextField();
        jTextField9ClothingAllowance = new javax.swing.JTextField();
        jLabel10JobTitle = new javax.swing.JLabel();
        jTextField10AddressType = new javax.swing.JTextField();
        jLabel11BasicSalary = new javax.swing.JLabel();
        jTextField11BasicSalary = new javax.swing.JTextField();
        jLabel12RiceSubsidy = new javax.swing.JLabel();
        jTextField12RiceSubsidy = new javax.swing.JTextField();
        jLabel13Pagibig = new javax.swing.JLabel();
        jLabel14PhoneAllowance = new javax.swing.JLabel();
        jTextField13Municipality = new javax.swing.JTextField();
        jLabel15EmployementType = new javax.swing.JLabel();
        jTextField14PhoneAllowance = new javax.swing.JTextField();
        jTextField15Province = new javax.swing.JTextField();
        jLabel16EmployementStatus = new javax.swing.JLabel();
        jLabel17ClothingAllowance = new javax.swing.JLabel();
        jTextField16PostalCode = new javax.swing.JTextField();
        jLabel19GrossSemi = new javax.swing.JLabel();
        jLabel20HourlyRate = new javax.swing.JLabel();
        jTextField17GrossSemi = new javax.swing.JTextField();
        jTextField18HourlyRate = new javax.swing.JTextField();
        jButton2SaveChanges = new javax.swing.JButton();
        jDateChooserBirthday = new com.toedter.calendar.JDateChooser();
        jLabelSupervisorId = new javax.swing.JLabel();
        jDateChooserDateHired = new com.toedter.calendar.JDateChooser();
        jLabel6PEmail = new javax.swing.JLabel();
        jTextField5HouseNo = new javax.swing.JTextField();
        jLabel11SalaryGrade = new javax.swing.JLabel();
        jTextField11SalaryGrade = new javax.swing.JTextField();
        jLabel11DateHired = new javax.swing.JLabel();
        jLabel11Department = new javax.swing.JLabel();
        jTextField10Country = new javax.swing.JTextField();
        jTextField10EmployementType = new javax.swing.JTextField();
        jLabel4AddressType = new javax.swing.JLabel();
        jLabel4Country = new javax.swing.JLabel();
        jTextField4EmployementStatus = new javax.swing.JTextField();
        jTextField4SSS = new javax.swing.JTextField();
        jTextField4SupervisorId = new javax.swing.JTextField();
        jTextFieldPhilhealth = new javax.swing.JTextField();
        jTextField4TIN = new javax.swing.JTextField();
        jTextField4JobTitle = new javax.swing.JTextField();
        jLabel4Street = new javax.swing.JLabel();
        jLabel4Municipality = new javax.swing.JLabel();
        jTextField4Pagibig = new javax.swing.JTextField();
        jLabel4Barangay = new javax.swing.JLabel();
        jLabel4Province = new javax.swing.JLabel();
        jLabel4PostalCode = new javax.swing.JLabel();

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

        jLabel4HouseNo.setText("House No.");

        jTextField4Department.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField4DepartmentMouseClicked(evt);
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

        jTextField6Email.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField6EmailMouseClicked(evt);
            }
        });

        jLabel8Philhealth.setText("Philhealth #");

        jTextField7Street.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField7StreetMouseClicked(evt);
            }
        });

        jLabel9TIN.setText("TIN #");

        jTextField8Barangay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField8BarangayMouseClicked(evt);
            }
        });

        jTextField9ClothingAllowance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField9ClothingAllowanceMouseClicked(evt);
            }
        });

        jLabel10JobTitle.setText("Job Title");

        jTextField10AddressType.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField10AddressTypeMouseClicked(evt);
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

        jTextField13Municipality.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField13MunicipalityMouseClicked(evt);
            }
        });

        jLabel15EmployementType.setText("Employement Type");

        jTextField14PhoneAllowance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField14PhoneAllowanceMouseClicked(evt);
            }
        });

        jTextField15Province.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField15ProvinceMouseClicked(evt);
            }
        });

        jLabel16EmployementStatus.setText("Employement Status");

        jLabel17ClothingAllowance.setText("Clothing Allowance");

        jTextField16PostalCode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField16PostalCodeMouseClicked(evt);
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

        jLabelSupervisorId.setText("Supervisor ID");

        jDateChooserDateHired.setDateFormatString("M/d/yyyy");
        jDateChooserDateHired.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDateChooserDateHiredMouseClicked(evt);
            }
        });

        jLabel6PEmail.setText("Email");

        jTextField5HouseNo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField5HouseNoMouseClicked(evt);
            }
        });

        jLabel11SalaryGrade.setText("Salary Grade");

        jTextField11SalaryGrade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField11SalaryGradeMouseClicked(evt);
            }
        });

        jLabel11DateHired.setText("Date Hired");

        jLabel11Department.setText("Department");

        jTextField10Country.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField10CountryMouseClicked(evt);
            }
        });

        jTextField10EmployementType.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField10EmployementTypeMouseClicked(evt);
            }
        });

        jLabel4AddressType.setText("Address Type");

        jLabel4Country.setText("Country");

        jTextField4EmployementStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField4EmployementStatusMouseClicked(evt);
            }
        });

        jTextField4SSS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField4SSSMouseClicked(evt);
            }
        });

        jTextField4SupervisorId.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField4SupervisorIdMouseClicked(evt);
            }
        });

        jTextFieldPhilhealth.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldPhilhealthMouseClicked(evt);
            }
        });

        jTextField4TIN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField4TINMouseClicked(evt);
            }
        });

        jTextField4JobTitle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField4JobTitleMouseClicked(evt);
            }
        });

        jLabel4Street.setText("Street");

        jLabel4Municipality.setText("Municipality");

        jTextField4Pagibig.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField4PagibigMouseClicked(evt);
            }
        });

        jLabel4Barangay.setText("Barangay");

        jLabel4Province.setText("Province");

        jLabel4PostalCode.setText("Postal Code");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5EditEmployeeDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6PEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6PhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2FirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3Birthday, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(152, 152, 152)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jDateChooserBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField5PhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel1LastName, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4HouseNo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4Street, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4Barangay, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4Municipality, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15EmployementType, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel16EmployementStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4Province, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel4PostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4Country, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4AddressType, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField2FirstName)
                                    .addComponent(jTextField10AddressType)
                                    .addComponent(jTextField16PostalCode)
                                    .addComponent(jTextField15Province)
                                    .addComponent(jTextField13Municipality)
                                    .addComponent(jTextField8Barangay)
                                    .addComponent(jTextField7Street)
                                    .addComponent(jTextField6Email)
                                    .addComponent(jTextField5HouseNo)
                                    .addComponent(jTextField10Country)
                                    .addComponent(jTextField10EmployementType)
                                    .addComponent(jTextField4EmployementStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                                    .addComponent(jTextField1LastName, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11BasicSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19GrossSemi)
                                    .addComponent(jLabel20HourlyRate)
                                    .addComponent(jLabel11SalaryGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel14PhoneAllowance, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel17ClothingAllowance))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel12RiceSubsidy, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel11DateHired, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8Philhealth, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7SSS, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13Pagibig, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9TIN, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10JobTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelSupervisorId, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11Department, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField4Pagibig, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4Department, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4SSS, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4SupervisorId, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldPhilhealth, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4TIN, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4JobTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooserDateHired, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(94, 94, 94)
                                    .addComponent(jButton2SaveChanges, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                                .addComponent(jTextField18HourlyRate, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField17GrossSemi, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField9ClothingAllowance, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField14PhoneAllowance, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField12RiceSubsidy, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField11BasicSalary, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField11SalaryGrade, javax.swing.GroupLayout.Alignment.LEADING)))))
                .addGap(0, 44, Short.MAX_VALUE))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2FirstName)
                            .addComponent(jTextField2FirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4SSS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7SSS))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1LastName)
                                    .addComponent(jTextField1LastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldPhilhealth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8Philhealth))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jDateChooserBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3Birthday))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField4Pagibig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel13Pagibig)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField5PhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6PhoneNumber)
                                    .addComponent(jTextField4TIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9TIN))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField6Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6PEmail))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField5HouseNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4HouseNo))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField7Street, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4Street))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField8Barangay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4Barangay))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField13Municipality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4Municipality))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextField15Province, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4Province))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextField16PostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4PostalCode))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextField10Country, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4Country))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextField10AddressType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4AddressType))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextField10EmployementType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel15EmployementType))
                                        .addGap(40, 40, 40))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel16EmployementStatus)
                                        .addComponent(jTextField4EmployementStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(143, 143, 143)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField4JobTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10JobTitle))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField4SupervisorId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelSupervisorId))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField4Department, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11Department))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11DateHired)
                                    .addComponent(jDateChooserDateHired, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField11SalaryGrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11SalaryGrade))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField11BasicSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11BasicSalary))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField12RiceSubsidy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12RiceSubsidy))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField14PhoneAllowance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14PhoneAllowance))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField9ClothingAllowance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17ClothingAllowance))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19GrossSemi, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField17GrossSemi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20HourlyRate)
                                    .addComponent(jTextField18HourlyRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(17, 17, 17)
                        .addComponent(jButton2SaveChanges)
                        .addContainerGap())))
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

    private void jButton2SaveChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2SaveChangesActionPerformed
    try {
        saveEmployee();  // Attempt to save the employee (either create or update)

        JOptionPane.showMessageDialog(this, "Action completed successfully."); // Notify the user

        if (currentEmployee != null) {
            disableAllTextFields(); // Disable fields again after update
        } else {
            clearAllTextFields(); // Clear the form after adding a new employee
        }

     } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error saving employee data. Please review your input and try again.");  // Show a generic error message
    }             
    }//GEN-LAST:event_jButton2SaveChangesActionPerformed

    private void jTextField1LastNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1LastNameMouseClicked
        jTextField1LastName.setEnabled(true);
    }//GEN-LAST:event_jTextField1LastNameMouseClicked

    private void jTextField2FirstNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2FirstNameMouseClicked
        jTextField2FirstName.setEnabled(true);
    }//GEN-LAST:event_jTextField2FirstNameMouseClicked

    private void jTextField4DepartmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4DepartmentMouseClicked
        jTextField4Department.setEnabled(true);
    }//GEN-LAST:event_jTextField4DepartmentMouseClicked

    private void jTextField5PhoneNumberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField5PhoneNumberMouseClicked
        jTextField5PhoneNumber.setEnabled(true);
    }//GEN-LAST:event_jTextField5PhoneNumberMouseClicked

    private void jTextField6EmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField6EmailMouseClicked
        jTextField6Email.setEnabled(true);
    }//GEN-LAST:event_jTextField6EmailMouseClicked

    private void jTextField7StreetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField7StreetMouseClicked
        jTextField7Street.setEnabled(true);
    }//GEN-LAST:event_jTextField7StreetMouseClicked

    private void jTextField8BarangayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField8BarangayMouseClicked
        jTextField8Barangay.setEnabled(true);
    }//GEN-LAST:event_jTextField8BarangayMouseClicked

    private void jTextField13MunicipalityMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField13MunicipalityMouseClicked
        jTextField13Municipality.setEnabled(true);
    }//GEN-LAST:event_jTextField13MunicipalityMouseClicked

    private void jTextField15ProvinceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField15ProvinceMouseClicked
        jTextField15Province.setEnabled(true);
    }//GEN-LAST:event_jTextField15ProvinceMouseClicked

    private void jTextField16PostalCodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField16PostalCodeMouseClicked
        jTextField16PostalCode.setEnabled(true);
    }//GEN-LAST:event_jTextField16PostalCodeMouseClicked

    private void jTextField10AddressTypeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField10AddressTypeMouseClicked
        jTextField10AddressType.setEnabled(true);
    }//GEN-LAST:event_jTextField10AddressTypeMouseClicked

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
        jDateChooserBirthday.getDateEditor().setEnabled(true);
    }//GEN-LAST:event_jDateChooserBirthdayMouseClicked

    private void jButton3EmployeeRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3EmployeeRequestActionPerformed
        Access.accessEmployeeRequests((Admin) this.user);
        this.setVisible(false);
    }//GEN-LAST:event_jButton3EmployeeRequestActionPerformed

    private void jDateChooserDateHiredMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooserDateHiredMouseClicked
        jDateChooserDateHired.getDateEditor().setEnabled(true);
    }//GEN-LAST:event_jDateChooserDateHiredMouseClicked

    private void jTextField5HouseNoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField5HouseNoMouseClicked
        jTextField5HouseNo.setEnabled(true);
    }//GEN-LAST:event_jTextField5HouseNoMouseClicked

    private void jTextField11SalaryGradeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField11SalaryGradeMouseClicked
        jTextField11SalaryGrade.setEnabled(true);
    }//GEN-LAST:event_jTextField11SalaryGradeMouseClicked

    private void jTextField10CountryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField10CountryMouseClicked
        jTextField10Country.setEnabled(true);
    }//GEN-LAST:event_jTextField10CountryMouseClicked

    private void jTextField10EmployementTypeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField10EmployementTypeMouseClicked
        jTextField10EmployementType.setEnabled(true);
    }//GEN-LAST:event_jTextField10EmployementTypeMouseClicked

    private void jTextField4EmployementStatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4EmployementStatusMouseClicked
        jTextField4EmployementStatus.setEnabled(true);
    }//GEN-LAST:event_jTextField4EmployementStatusMouseClicked

    private void jTextField4SSSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4SSSMouseClicked
        jTextField4SSS.setEnabled(true);
    }//GEN-LAST:event_jTextField4SSSMouseClicked

    private void jTextField4SupervisorIdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4SupervisorIdMouseClicked
        jTextField4SupervisorId.setEnabled(true);
    }//GEN-LAST:event_jTextField4SupervisorIdMouseClicked

    private void jTextFieldPhilhealthMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldPhilhealthMouseClicked
        jTextFieldPhilhealth.setEnabled(true);
    }//GEN-LAST:event_jTextFieldPhilhealthMouseClicked

    private void jTextField4TINMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4TINMouseClicked
        jTextField4TIN.setEnabled(true);
    }//GEN-LAST:event_jTextField4TINMouseClicked

    private void jTextField4JobTitleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4JobTitleMouseClicked
        jTextField4JobTitle.setEnabled(true);
    }//GEN-LAST:event_jTextField4JobTitleMouseClicked

    private void jTextField4PagibigMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4PagibigMouseClicked
        jTextField4Pagibig.setEnabled(true);
    }//GEN-LAST:event_jTextField4PagibigMouseClicked
//    /*
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//    try {
//        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//            if ("Nimbus".equals(info.getName())) {
//                javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                break;
//            }
//        }
//    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//        java.util.logging.Logger.getLogger(ViewEmployeeDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//    }
//
//    java.awt.EventQueue.invokeLater(() -> {
//        try {
//            Connection conn = DriverManager.getConnection(
//                "jdbc:mysql://localhost:3306/payrollsystem_db", "root", "admin"
//            );
//
//            // Temporary!!! Dummy admin for testing purposes only
//            Admin dummyAdmin = new Admin("adminUsername", "adminPassword");
//
//            // For Add New Record mode:
//            new ViewEmployeeDetails(dummyAdmin, conn).setVisible(true);
//
//        } catch (SQLException e) {
//            javax.swing.JOptionPane.showMessageDialog(null, "Database connection failed: " + e.getMessage());
//        }
//    });
//}
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
    private javax.swing.JButton jButton1EmployeeInformation;
    private javax.swing.JButton jButton2SaveChanges;
    private javax.swing.JButton jButton3EmployeeRequest;
    private javax.swing.JButton jButton3SelfServicePortal;
    private javax.swing.JButton jButton4Attendance;
    private javax.swing.JButton jButton4Payroll;
    private javax.swing.JButton jButton6LogOut;
    private com.toedter.calendar.JDateChooser jDateChooserBirthday;
    private com.toedter.calendar.JDateChooser jDateChooserDateHired;
    private javax.swing.JLabel jLabel10JobTitle;
    private javax.swing.JLabel jLabel11BasicSalary;
    private javax.swing.JLabel jLabel11DateHired;
    private javax.swing.JLabel jLabel11Department;
    private javax.swing.JLabel jLabel11SalaryGrade;
    private javax.swing.JLabel jLabel12RiceSubsidy;
    private javax.swing.JLabel jLabel13Pagibig;
    private javax.swing.JLabel jLabel14PhoneAllowance;
    private javax.swing.JLabel jLabel15EmployementType;
    private javax.swing.JLabel jLabel16EmployementStatus;
    private javax.swing.JLabel jLabel17ClothingAllowance;
    private javax.swing.JLabel jLabel19GrossSemi;
    private javax.swing.JLabel jLabel1LastName;
    private javax.swing.JLabel jLabel20HourlyRate;
    private javax.swing.JLabel jLabel2FirstName;
    private javax.swing.JLabel jLabel3Birthday;
    private javax.swing.JLabel jLabel4AddressType;
    private javax.swing.JLabel jLabel4Barangay;
    private javax.swing.JLabel jLabel4Country;
    private javax.swing.JLabel jLabel4HouseNo;
    private javax.swing.JLabel jLabel4Municipality;
    private javax.swing.JLabel jLabel4PostalCode;
    private javax.swing.JLabel jLabel4Province;
    private javax.swing.JLabel jLabel4Street;
    private javax.swing.JLabel jLabel5EditEmployeeDetails;
    private javax.swing.JLabel jLabel6PEmail;
    private javax.swing.JLabel jLabel6PhoneNumber;
    private javax.swing.JLabel jLabel7SSS;
    private javax.swing.JLabel jLabel8Philhealth;
    private javax.swing.JLabel jLabel9TIN;
    private javax.swing.JLabel jLabelSupervisorId;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelAdmin;
    private javax.swing.JTextField jTextField10AddressType;
    private javax.swing.JTextField jTextField10Country;
    private javax.swing.JTextField jTextField10EmployementType;
    private javax.swing.JTextField jTextField11BasicSalary;
    private javax.swing.JTextField jTextField11SalaryGrade;
    private javax.swing.JTextField jTextField12RiceSubsidy;
    private javax.swing.JTextField jTextField13Municipality;
    private javax.swing.JTextField jTextField14PhoneAllowance;
    private javax.swing.JTextField jTextField15Province;
    private javax.swing.JTextField jTextField16PostalCode;
    private javax.swing.JTextField jTextField17GrossSemi;
    private javax.swing.JTextField jTextField18HourlyRate;
    private javax.swing.JTextField jTextField1LastName;
    private javax.swing.JTextField jTextField2FirstName;
    private javax.swing.JTextField jTextField4Address6;
    private javax.swing.JTextField jTextField4Department;
    private javax.swing.JTextField jTextField4EmployementStatus;
    private javax.swing.JTextField jTextField4JobTitle;
    private javax.swing.JTextField jTextField4Pagibig;
    private javax.swing.JTextField jTextField4SSS;
    private javax.swing.JTextField jTextField4SupervisorId;
    private javax.swing.JTextField jTextField4TIN;
    private javax.swing.JTextField jTextField5HouseNo;
    private javax.swing.JTextField jTextField5PhoneNumber;
    private javax.swing.JTextField jTextField6Email;
    private javax.swing.JTextField jTextField7Street;
    private javax.swing.JTextField jTextField8Barangay;
    private javax.swing.JTextField jTextField9ClothingAllowance;
    private javax.swing.JTextField jTextFieldPhilhealth;
    // End of variables declaration//GEN-END:variables
 
    /**
    * Clears all input fields in the employee form.
    *
    * Used after successfully adding a new employee
    * to reset the form for the next entry.
    */
    private void clearAllTextFields() {
        jTextField2FirstName.setText("");
        jTextField1LastName.setText("");
        jDateChooserBirthday.setDate(null);
        jTextField5PhoneNumber.setText("");
        jTextField6Email.setText("");

        jTextField5HouseNo.setText("");
        jTextField7Street.setText("");
        jTextField8Barangay.setText("");
        jTextField13Municipality.setText("");
        jTextField15Province.setText("");
        jTextField16PostalCode.setText("");
        jTextField10Country.setText("");
        jTextField10AddressType.setText("");

        jTextField4SSS.setText("");
        jTextFieldPhilhealth.setText("");
        jTextField4Pagibig.setText("");
        jTextField4TIN.setText("");

        jTextField10EmployementType.setText("");
        jTextField4EmployementStatus.setText("");
        jDateChooserDateHired.setDate(null);
        jTextField4JobTitle.setText("");
        jTextField4Department.setText("");
        jTextField4SupervisorId.setText("");

        jTextField11SalaryGrade.setText("");
        jTextField11BasicSalary.setText("");
        jTextField12RiceSubsidy.setText("");
        jTextField14PhoneAllowance.setText("");
        jTextField9ClothingAllowance.setText("");
        jTextField17GrossSemi.setText("");
        jTextField18HourlyRate.setText("");
    }
}

