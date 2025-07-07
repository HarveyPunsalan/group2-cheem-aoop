/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.viewmodel.service;

import com.motorph.common.dao.DAOException;
import com.motorph.payrollprocessing.viewmodel.dao.EmployeeWorkedHoursSummaryDAO;
import com.motorph.payrollprocessing.viewmodel.model.EmployeeWorkedHoursSummaryViewModel;
import java.util.List;
import java.util.Optional;

public class EmployeeWorkedHoursSummaryService {

    private final EmployeeWorkedHoursSummaryDAO summaryDAO;

    public EmployeeWorkedHoursSummaryService(EmployeeWorkedHoursSummaryDAO summaryDAO) {
        this.summaryDAO = summaryDAO;
    }

    public List<EmployeeWorkedHoursSummaryViewModel> getSummaryByPayPeriodId(int payPeriodId) throws DAOException {
        return summaryDAO.findByPayPeriodId(payPeriodId);
    }
    
    public Optional<EmployeeWorkedHoursSummaryViewModel> getSummaryByPayPeriodIdAndEmployeeId(int payPeriodId, int employeeID) throws DAOException {
        return summaryDAO.findByPayPeriodIdAndEmployeeId(payPeriodId, employeeID);
    }
}

