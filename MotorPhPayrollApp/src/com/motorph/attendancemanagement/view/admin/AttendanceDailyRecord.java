/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.motorph.attendancemanagement.view.admin;

import com.motorph.employeemanagement.view.admin.EmployeeInformation;
import com.motorph.employeemanagement.model.Employee;
import com.motorph.attendancemanagement.service.AttendanceService;
import com.motorph.attendancemanagement.service.AttendanceCalculator;
import com.motorph.attendancemanagement.model.DailyAttendance;
import com.motorph.attendancemanagement.tablemodel.DailyAttendanceTableModel;
import com.motorph.common.swing.TableConfigurator;
import com.motorph.common.swing.validation.SelectionValidator;
import com.motorph.usermanagement.model.Admin;
import com.motorph.usermanagement.model.User;
import com.motorph.usermanagement.model.Access;
import com.motorph.payrollprocessing.model.payroll.PayPeriod;
import com.motorph.payrollprocessing.service.core.PayrollService;
import com.motorph.common.ui.renderer.PromptComboBoxRenderer;
import com.motorph.common.util.DateUtil;
import com.motorph.database.connection.DatabaseService;
import com.motorph.database.execution.SQLExecutor;
import com.motorph.employeemanagement.service.EmployeeRetrievalService;
import com.motorph.payrollprocessing.service.core.PayPeriodService;
import com.motorph.payrollprocessing.service.core.ServiceFactory;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Charm
 */
public class AttendanceDailyRecord extends javax.swing.JFrame {;
    Employee employee;
    PayrollService payPeriodList = new PayrollService();
    User user;
    String[] defaultResults = {"0", "0", "0"};
    
    private PayPeriodService payPeriodService;
    private AttendanceService attendanceService;
    private EmployeeRetrievalService retrievalService;
    private boolean initializing = true;
    private int employeeID;
    
    /**
     * Creates new form AttendanceRecord
     */
    public AttendanceDailyRecord() {
        initComponents();
        jComboBoxAttendancePeriod.setModel(payPeriodList.getComboBoxModel());
        jComboBoxAttendancePeriod.setRenderer(new PromptComboBoxRenderer("Select Pay Period") );
        jComboBoxAttendancePeriod.setSelectedIndex(-1);
   
    }
    
    public AttendanceDailyRecord(Admin admin, Employee employee) {
        initComponents();
        initService();
        
        initializing = true;
        setupPayPeriodComboBox();
        initializing = false;
        
        this.user = admin;
        this.employee = employee;
        admin.addLogoutListener(this);
                
        jLabelEmployeeName.setText(employee.getFirstName() + " " + employee.getLastName());
        
        setResultFields(defaultResults); 
    }
    
    public AttendanceDailyRecord(Admin admin, int employeeId, PayPeriod selectedPayPeriod) {
        initComponents();
        initService();
        
        initializing = true;
        setupPayPeriodComboBox();
        initializing = false;
        
        this.retrievalService = new EmployeeRetrievalService(new SQLExecutor(DatabaseService.connectToMotorPH()));
        this.employee = retrievalService.getEmployeeById(employeeId);
        this.user = admin;
        admin.addLogoutListener(this);
                
        jLabelEmployeeName.setText(employee.getFirstName() + " " + employee.getLastName());
        jComboBoxAttendancePeriod.setSelectedItem(DateUtil.formatDate(selectedPayPeriod.getStartDate()) + " : " + DateUtil.formatDate(selectedPayPeriod.getEndDate()));
                
        loadTable(selectedPayPeriod);
        
        List<DailyAttendance> employeeAttendance = this.attendanceService.getAttendanceRecordsByPayPeriod(this.employee, selectedPayPeriod);
        
        double payableHours = AttendanceCalculator.calculatePayableHours(employeeAttendance);
        double regularHours = AttendanceCalculator.calculateRegularWorkedHours(employeeAttendance);
        double overtime = AttendanceCalculator.calculateApprovedOverTimeHours(employeeAttendance);
        
        String[] results = {String.valueOf(payableHours),String.valueOf(regularHours),String.valueOf(overtime)};
        setResultFields(results); 
    }
    
    private void initService() {
        try {
            this.attendanceService = new AttendanceService();
            this.payPeriodService = ServiceFactory.createPayPeriodService();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to load service: " + e.getMessage());
        }
    }
    
    private void setupPayPeriodComboBox() {
        jComboBoxAttendancePeriod.setModel(payPeriodService.getPayPeriodComboBoxModel("MM-dd-yyyy"));
        jComboBoxAttendancePeriod.setRenderer(new PromptComboBoxRenderer("Select Pay Period") );
        jComboBoxAttendancePeriod.setSelectedIndex(-1);
    }
    
    private void loadTable(PayPeriod selectedPayPeriod) {
        List<DailyAttendance> dailyAttendanceList = this.attendanceService.getAttendanceRecordsByPayPeriod(this.employee, selectedPayPeriod);
        DailyAttendanceTableModel tableModel = new DailyAttendanceTableModel(dailyAttendanceList);
        
        jTableDailyAttendanceList.setModel(tableModel);
        TableConfigurator.configureDailyAttendanceTable(jTableDailyAttendanceList);
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
        jButton1EmployeeInformation = new javax.swing.JButton();
        jButton3EmployeeRequest = new javax.swing.JButton();
        jButton4Payroll = new javax.swing.JButton();
        jButton6LogOut = new javax.swing.JButton();
        jButton3SelfServicePortal = new javax.swing.JButton();
        jButton4Attendance = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDailyAttendanceList = new javax.swing.JTable();
        jComboBoxAttendancePeriod = new javax.swing.JComboBox<>();
        jLabelEmployeeName = new javax.swing.JLabel();
        jLabelTotalHours = new javax.swing.JLabel();
        jLabelTotalHoursResult = new javax.swing.JLabel();
        jLabelRegular = new javax.swing.JLabel();
        jLabelRegularResult = new javax.swing.JLabel();
        jLabelOvertime = new javax.swing.JLabel();
        jLabelOvertimeResult = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1EmployeeInformation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3EmployeeRequest, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(jButton4Payroll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(jButton6LogOut, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(jButton3SelfServicePortal, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(jButton4Attendance, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 285, Short.MAX_VALUE)
                .addComponent(jButton6LogOut)
                .addContainerGap())
        );

        jTableDailyAttendanceList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableDailyAttendanceList.setShowGrid(true);
        jTableDailyAttendanceList.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableDailyAttendanceList);
        jTableDailyAttendanceList.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jComboBoxAttendancePeriod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAttendancePeriodActionPerformed(evt);
            }
        });

        jLabelEmployeeName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelEmployeeName.setText("EmployeeName");

        jLabelTotalHours.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelTotalHours.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTotalHours.setText("Payable Hours");

        jLabelTotalHoursResult.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelTotalHoursResult.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTotalHoursResult.setText("Total Hours");

        jLabelRegular.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelRegular.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelRegular.setText("Regular Hours");

        jLabelRegularResult.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelRegularResult.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelRegularResult.setText("Total Hours");

        jLabelOvertime.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelOvertime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelOvertime.setText("Overtime");

        jLabelOvertimeResult.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelOvertimeResult.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelOvertimeResult.setText("Overtime");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxAttendancePeriod, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelEmployeeName, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelTotalHours, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelRegular, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelOvertime, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelTotalHoursResult, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelRegularResult, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelOvertimeResult, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 838, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabelEmployeeName)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxAttendancePeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelTotalHours)
                            .addComponent(jLabelRegular)
                            .addComponent(jLabelOvertime))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelTotalHoursResult)
                            .addComponent(jLabelRegularResult)
                            .addComponent(jLabelOvertimeResult))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6LogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6LogOutActionPerformed
        user.logout(this);
    }//GEN-LAST:event_jButton6LogOutActionPerformed

    private void jComboBoxAttendancePeriodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAttendancePeriodActionPerformed
        if (initializing) return;
        if (SelectionValidator.isItemSelected(jComboBoxAttendancePeriod, "Please select 1 Pay Period")) return;
        
        String[] dates = jComboBoxAttendancePeriod.getSelectedItem().toString().split(" : ");
        String startDate = dates[0];
        String endDate = dates[1];
        
        PayPeriodService payPeriodService  = ServiceFactory.createPayPeriodService();
        
        PayPeriod selectedPayPeriod = payPeriodService.searchByDateRange(dates[0], dates[1]).get();
        
        loadTable(selectedPayPeriod);
        
        List<DailyAttendance> employeeAttendance = this.attendanceService.getAttendanceRecordsByPayPeriod(this.employee, selectedPayPeriod);
        
        double payableHours = AttendanceCalculator.calculatePayableHours(employeeAttendance);
        double regularHours = AttendanceCalculator.calculateRegularWorkedHours(employeeAttendance);
        double overtime = AttendanceCalculator.calculateApprovedOverTimeHours(employeeAttendance);
        
        String[] results = {String.valueOf(payableHours),String.valueOf(regularHours),String.valueOf(overtime)};
        setResultFields(results);      
        
    }//GEN-LAST:event_jComboBoxAttendancePeriodActionPerformed

    private void jButton1EmployeeInformationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1EmployeeInformationActionPerformed
        Access.accessEmployeeInformation((Admin) this.user);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1EmployeeInformationActionPerformed

    private void jButton3SelfServicePortalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3SelfServicePortalActionPerformed
        Access.accessProfilePage(user);
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

    private void jButton3EmployeeRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3EmployeeRequestActionPerformed
//        Access.accessEmployeeRequests((Admin) this.user);
//        this.setVisible(false);
    }//GEN-LAST:event_jButton3EmployeeRequestActionPerformed

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
            java.util.logging.Logger.getLogger(EmployeeInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AttendanceDailyRecord().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1EmployeeInformation;
    private javax.swing.JButton jButton3EmployeeRequest;
    private javax.swing.JButton jButton3SelfServicePortal;
    private javax.swing.JButton jButton4Attendance;
    private javax.swing.JButton jButton4Payroll;
    private javax.swing.JButton jButton6LogOut;
    private javax.swing.JComboBox<String> jComboBoxAttendancePeriod;
    private javax.swing.JLabel jLabelEmployeeName;
    private javax.swing.JLabel jLabelOvertime;
    private javax.swing.JLabel jLabelOvertimeResult;
    private javax.swing.JLabel jLabelRegular;
    private javax.swing.JLabel jLabelRegularResult;
    private javax.swing.JLabel jLabelTotalHours;
    private javax.swing.JLabel jLabelTotalHoursResult;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDailyAttendanceList;
    // End of variables declaration//GEN-END:variables

    public void setResultFields(String[] results) {
        jLabelTotalHoursResult.setText(results[0]);
        jLabelRegularResult.setText(results[1]);
        jLabelOvertimeResult.setText(results[2]);
    }

}

