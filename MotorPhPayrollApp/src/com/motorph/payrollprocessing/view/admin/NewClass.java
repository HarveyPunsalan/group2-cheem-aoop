/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.view.admin;

import com.motorph.common.util.DateUtil;
import com.motorph.database.execution.ExecutorProvider;
import com.motorph.database.execution.SQLExecutor;
import com.motorph.database.execution.script.payroll.PayPeriodScript;
import com.motorph.payrollprocessing.mapper.payrollmapper.PayPeriodMapper;
import com.motorph.payrollprocessing.service.core.PayPeriodService;
import com.motorph.payrollprocessing.service.core.ServiceFactory;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author 63909
 */
public class NewClass {
    public static void main(String[] args) throws SQLException {
        String str = "Bi-weekly Payroll: Feb 12th - Feb 25th";
        LocalDate[] dates = DateUtil.parse_LABEL_BiWeekly_MMM_ddo_ddo_to_MM_dd_yyyy(str, 2024);
        SQLExecutor executor = ExecutorProvider.getExecutor();
        PayPeriodMapper mapper = new PayPeriodMapper();
        System.out.print(executor.queryForObject(
                PayPeriodScript.SELECT_BY_DATES,
                List.of(dates[0], dates[1]),
                mapper
            ).get().getEndDate());
        PayPeriodService payPeriodService  = ServiceFactory.createPayPeriodServicewService();
    }
    
}
