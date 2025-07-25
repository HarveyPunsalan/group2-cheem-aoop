/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.service.core;

import com.motorph.payrollprocessing.service.calculator.SalaryCalculator;
import com.motorph.payrollprocessing.service.calculator.AllowanceCalculator;
import com.motorph.payrollprocessing.service.calculator.DeductionCalculator;
import com.motorph.payrollprocessing.service.calculator.TaxCalculator;
import com.motorph.payrollprocessing.service.processor.PayrollCalculator;
import com.motorph.payrollprocessing.model.payroll.PayrollRecord;
import com.motorph.payrollprocessing.model.payroll.PayPeriod;
import com.motorph.employeemanagement.model.Employee;
//import com.motorph.employeemanagement.service.csvversion.EmployeeService;
import com.motorph.attendancemanagement.service.AttendanceService;
import com.motorph.attendancemanagement.service.AttendanceCalculator;
import com.motorph.attendancemanagement.model.DailyAttendance;
import com.motorph.database.connection.DatabaseService;
import com.motorph.database.execution.SQLExecutor;
import com.motorph.employeemanagement.service.EmployeeRetrievalService;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 63909
 */
public class PayrollService {
    private static final DateTimeFormatter formatterDate1  = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    private static final DateTimeFormatter formatterDate2  = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private List<PayPeriod> payPeriodList;
//    private EmployeeService employeeService;
    private final Connection connection = DatabaseService.connectToMotorPH();
    private final EmployeeRetrievalService retrievalService;
    private AttendanceService attendanceService;

    public PayrollService() {
//        this.payPeriodList = CsvFile.PAYPERIOD.readFile(PayPeriod::new);
//        this.employeeService = new EmployeeService();
        this.retrievalService = new EmployeeRetrievalService(new SQLExecutor(connection));
        this.attendanceService = new AttendanceService();
    }
       
    /**
     * Generates a list of PayrollRecords for all employees in a given pay period.
     *
     * @param payPeriod        the PayPeriod object (can be used for filtering or validation if needed)
     * @return a List of PayrollRecord objects, one for each employee
     */
    public List<PayrollRecord> generatePayrollRecord(PayPeriod payPeriod) {
        List<PayrollRecord> payrollRecords = new ArrayList<>();
        try {
            for (Employee employee : this.retrievalService.getActiveEmployees()) {
                
                // Retrieve payroll inputs from each Employee object
                BigDecimal payableHours = new BigDecimal(0); //BigDecimal.valueOf(AttendanceCalculator.calculatePayableHours(attendanceService.getFilteredDailyAttendance(employee, payPeriod)));
                BigDecimal hourlyRate = employee.getHourlyRate();
                BigDecimal rice = employee.getRiceSubsidy();
                BigDecimal phone = employee.getPhoneAllowance();
                BigDecimal clothing = employee.getClothingAllowance();
                
                // Calculate the payroll record for the employee using the PayrollCalculator
                PayrollRecord record = PayrollCalculator.calculatePayrollRecord(payableHours, hourlyRate, rice, phone, clothing);
                payrollRecords.add(record);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PayrollService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return payrollRecords;
    }
    
    public DefaultComboBoxModel<String> getComboBoxModel(){
        // Convert payPeriodList to a String[]
        String[] payPeriodArray = payPeriodList.stream()
            .map(payPeriod -> payPeriod.getStartDate().format(formatterDate1) + " : " + payPeriod.getEndDate().format(formatterDate1))
            .toArray(String[]::new); // Proper way to get a String[]

        return new DefaultComboBoxModel<String>(payPeriodArray); // Use DefaultComboBoxModel
    }
    
    public DefaultTableModel getPayrollListTableModel(){
        // Define column headers
        String[] columnNames = {"Pay Period", "Pay Date", "Employee","Total Payment", "Status", "Approve Date"};
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        for (PayPeriod payPeriod : payPeriodList) {
            // Construct a row using the aggregated values.
            Object[] row = {
                "Biweekly Salary : " + payPeriod.getStartDate().format(formatterDate2) + " " + payPeriod.getEndDate().format(formatterDate2),
                payPeriod.getPayDate(),
                "",
                "",
                "",
                ""
            };

            model.addRow(row);
        }

        return model;
    }
    
    public DefaultTableModel getEmployeeSelectionTableModel(PayPeriod payPeriod) {
            // Define column headers
            String[] columnNames = {
                "Employee ID", "Employee Name", "Employee Type",
                "Total Regular Hours", "Total Overtime"
            };
            
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            
        try {
            for (Employee employee : this.retrievalService.getActiveEmployees()) {
                
                List<DailyAttendance> filteredRecords = null; // attendanceService.getFilteredDailyAttendance(employee, payPeriod);
                
                double totalRegular = AttendanceCalculator.calculateRegularWorkedHours(filteredRecords);
                double totalOvertime = AttendanceCalculator.calculateApprovedOverTimeHours(filteredRecords);
                
                // Construct a row using the aggregated values.
                Object[] row = {
                    employee.getEmployeeId(),
                    employee.getFirstName() + " " + employee.getLastName(),
                    employee.getEmploymentStatus(),
                    totalRegular,
                    totalOvertime,
                };
                
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PayrollService.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            return model;
    }
    
    public DefaultTableModel getPayrollEmployeeEarningsTableModel(PayPeriod payPeriod) {
        // Define column headers
        String[] columnNames = {
            "Employee ID", "Employee Name", "Payable Hours", "Base Salary", "Rice Subsidy", "Phone Allowance", "Clothing Allowance","Gross Pay"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        try {
            for (Employee employee : this.retrievalService.getActiveEmployees()) {

                //            List<DailyAttendance> filteredRecords = attendanceService.getFilteredDailyAttendance(employee, payPeriod);

                BigDecimal payableHours = new BigDecimal(0); //BigDecimal.valueOf(AttendanceCalculator.calculatePayableHours(filteredRecords));
                BigDecimal baseSalary = SalaryCalculator.calculateBasicSalary(payableHours, employee.getHourlyRate());
                BigDecimal totalAllowance = AllowanceCalculator.calculateTotalAllowance(employee.getRiceSubsidy(),
                        employee.getPhoneAllowance(),
                        employee.getClothingAllowance());
                BigDecimal grossSalary = SalaryCalculator.calculateGrossSalary(baseSalary, totalAllowance);

                // Construct a row using the aggregated values.
                Object[] row = {
                    employee.getEmployeeId(),
                    employee.getFirstName() + " " + employee.getLastName(),
                    payableHours,
                    baseSalary,
                    employee.getRiceSubsidy(),
                    employee.getPhoneAllowance(),
                    employee.getClothingAllowance(),
                    grossSalary,
                };

                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PayrollService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return model;
    }
    
    public DefaultTableModel getPayrollEmployeeDeductionsTableModel(PayPeriod payPeriod) {
        // Define column headers
        String[] columnNames = {
            "Employee ID", "Employee Name", "SSS", "PhilHealth", "Pag-IBIG", "Withholding Tax" ,"Total Deduction"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        try {
            for (Employee employee : this.retrievalService.getActiveEmployees()) {

                //            List<DailyAttendance> filteredRecords = attendanceService.getFilteredDailyAttendance(employee, payPeriod);

                BigDecimal payableHours = new BigDecimal(0); //BigDecimal.valueOf(AttendanceCalculator.calculatePayableHours(filteredRecords));
                BigDecimal baseSalary = SalaryCalculator.calculateBasicSalary(payableHours, employee.getHourlyRate());
                BigDecimal totalAllowance = AllowanceCalculator.calculateTotalAllowance(employee.getRiceSubsidy(),
                        employee.getPhoneAllowance(),
                        employee.getClothingAllowance());
                BigDecimal grossSalary = SalaryCalculator.calculateGrossSalary(baseSalary, totalAllowance);
                BigDecimal sss = DeductionCalculator.calculateSSS(grossSalary);
                BigDecimal philhealth = DeductionCalculator.calculatePhilHealth(grossSalary);
                BigDecimal pagibig = DeductionCalculator.calculatePagIbig(grossSalary);
                BigDecimal govermentContribution = DeductionCalculator.calculateGovernmentContribution(sss, philhealth, pagibig);
                BigDecimal tax = TaxCalculator.calculateWithHoldingTax(grossSalary, govermentContribution);
                BigDecimal totalDeduction = DeductionCalculator.calculateTotalDeductions(govermentContribution, tax);


                // Construct a row using the aggregated values.
                Object[] row = {
                    employee.getEmployeeId(),
                    employee.getFirstName() + " " + employee.getLastName(),
                    sss,
                    philhealth,
                    pagibig,
                    tax,
                    totalDeduction,
                };

                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PayrollService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return model;
    }
    
    public DefaultTableModel getPayrollEmployeeNetPayTableModel(PayPeriod payPeriod) {
        // Define column headers
        String[] columnNames = {
            "Employee ID", "Employee Name", "Payable Hours", "Basic Salary", "Gross Salary", "Total Deduction", "Net Salary"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        try {
            for (Employee employee : this.retrievalService.getActiveEmployees()) {

                //            List<DailyAttendance> filteredRecords = attendanceService.getFilteredDailyAttendance(employee, payPeriod);

                BigDecimal payableHours = new BigDecimal(0); //BigDecimal.valueOf(AttendanceCalculator.calculatePayableHours(filteredRecords));
                BigDecimal baseSalary = SalaryCalculator.calculateBasicSalary(payableHours, employee.getHourlyRate());
                BigDecimal totalAllowance = AllowanceCalculator.calculateTotalAllowance(employee.getRiceSubsidy(),
                        employee.getPhoneAllowance(),
                        employee.getClothingAllowance());
                BigDecimal grossSalary = SalaryCalculator.calculateGrossSalary(baseSalary, totalAllowance);
                BigDecimal sss = DeductionCalculator.calculateSSS(grossSalary);
                BigDecimal philhealth = DeductionCalculator.calculatePhilHealth(grossSalary);
                BigDecimal pagibig = DeductionCalculator.calculatePagIbig(grossSalary);
                BigDecimal govermentContribution = DeductionCalculator.calculateGovernmentContribution(sss, philhealth, pagibig);
                BigDecimal tax = TaxCalculator.calculateWithHoldingTax(grossSalary, govermentContribution);
                BigDecimal totalDeduction = DeductionCalculator.calculateTotalDeductions(govermentContribution, tax);
                BigDecimal netSalary = SalaryCalculator.calculateNetSalary(grossSalary, totalDeduction, tax);


                // Construct a row using the aggregated values.
                Object[] row = {
                    employee.getEmployeeId(),
                    employee.getFirstName() + " " + employee.getLastName(),
                    payableHours,
                    baseSalary,
                    grossSalary,
                    totalDeduction,
                    totalDeduction,
                };

                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PayrollService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return model;
    }
}
