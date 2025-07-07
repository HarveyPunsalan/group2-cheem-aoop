/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.viewmodel.service;

import com.motorph.database.execution.ExecutorProvider;
import com.motorph.database.execution.SQLExecutor;
import com.motorph.payrollprocessing.viewmodel.dao.BiWeeklyPayrollViewDAO;
import com.motorph.payrollprocessing.viewmodel.dao.EmployeeWorkedHoursSummaryDAO;
import com.motorph.payrollprocessing.viewmodel.mapper.BiWeeklyPayrollViewMapper;
import com.motorph.payrollprocessing.viewmodel.mapper.EmployeeWorkedHoursSummaryMapper;
import com.motorph.reportmanagement.service.PayrollBiWeeklySummaryReportService;

/**
 *
 * @author 63909
 */
public class ViewModelServiceFactory {
    
    // ✅ For BiWeeklyPayrollView
    public static BiWeeklyPayrollViewService createBiWeeklyPayrollViewService() {
        SQLExecutor executor = ExecutorProvider.getExecutor();
        BiWeeklyPayrollViewMapper mapper = new BiWeeklyPayrollViewMapper();
        BiWeeklyPayrollViewDAO dao = new BiWeeklyPayrollViewDAO(executor, mapper);
        return new BiWeeklyPayrollViewService(dao);
    }
    
    public static EmployeeWorkedHoursSummaryService createEmployeeWorkedHoursSummaryServiceService() {
        SQLExecutor executor = ExecutorProvider.getExecutor();
        EmployeeWorkedHoursSummaryMapper mapper = new EmployeeWorkedHoursSummaryMapper();
        EmployeeWorkedHoursSummaryDAO dao = new EmployeeWorkedHoursSummaryDAO(executor, mapper);
        return new EmployeeWorkedHoursSummaryService(dao);
    }
    
    public static PayrollBiWeeklySummaryReportService createPayrollBiWeeklySummaryReportService() {
        SQLExecutor executor = ExecutorProvider.getExecutor();
        EmployeeWorkedHoursSummaryMapper mapper = new EmployeeWorkedHoursSummaryMapper();
        EmployeeWorkedHoursSummaryDAO dao = new EmployeeWorkedHoursSummaryDAO(executor, mapper);
        return new PayrollBiWeeklySummaryReportService();
    }
    
}
