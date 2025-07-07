/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.service.core;

import com.motorph.database.execution.ExecutorProvider;
import com.motorph.database.execution.SQLExecutor;
import com.motorph.payrollprocessing.dao.payrolldao.PayPeriodDAO;
import com.motorph.payrollprocessing.dao.payrolldao.PayslipDAO;
import com.motorph.payrollprocessing.mapper.payrollmapper.PayPeriodMapper;
import com.motorph.payrollprocessing.mapper.payrollmapper.PayslipMapper;

/**
 *
 * @author 63909
 */
public class ServiceFactory {
    
    public static PayPeriodService createPayPeriodServicewService() {
        SQLExecutor executor = ExecutorProvider.getExecutor();
        PayPeriodMapper mapper = new PayPeriodMapper();
        PayPeriodDAO dao = new PayPeriodDAO(executor, mapper);
        return new PayPeriodService(dao);
    }
    
    public static PayslipService createPayslipServicewService() {
        SQLExecutor executor = ExecutorProvider.getExecutor();
        PayslipMapper mapper = new PayslipMapper();
        PayslipDAO dao = new PayslipDAO(executor, mapper);
        return new PayslipService(dao);
    }
}
