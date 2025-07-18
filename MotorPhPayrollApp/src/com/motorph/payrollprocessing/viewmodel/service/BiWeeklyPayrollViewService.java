/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.viewmodel.service;

import com.motorph.payrollprocessing.viewmodel.dao.BiWeeklyPayrollViewDAO;
import com.motorph.payrollprocessing.viewmodel.model.BiWeeklyPayrollViewModel;
import com.motorph.reportmanagement.model.PayrollBiWeeklySummaryReport;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author 63909
 */
public class BiWeeklyPayrollViewService {
    
    private final BiWeeklyPayrollViewDAO viewDAO;

    public BiWeeklyPayrollViewService(BiWeeklyPayrollViewDAO viewDAO) {
        this.viewDAO = viewDAO;
    }
    
    public Optional<BiWeeklyPayrollViewModel> getBiWeeklyPayroll(int payPeriodID) {
        try {
            return viewDAO.findById(payPeriodID);
        } catch (Exception e) {
            // log and wrap into service-level exception
            throw new RuntimeException("Failed to fetch bi-weekly payroll view", e);
        }
    }

    public List<BiWeeklyPayrollViewModel> getAllBiWeeklyPayroll() {
        try {
            return viewDAO.findAll();
        } catch (Exception e) {
            // log and wrap into service-level exception
            throw new RuntimeException("Failed to fetch bi-weekly payroll view", e);
        }
    }
    
    public List<PayrollBiWeeklySummaryReport> getAllBiWeeklyPayrollByEmployeeIds(int payPeriodId, List<Integer> employeeIds) {
        try {
            return viewDAO.findByIds(payPeriodId, employeeIds);
        } catch (Exception e) {
            // log and wrap into service-level exception
            throw new RuntimeException("Failed to fetch bi-weekly payroll view", e);
        }
    }
}
