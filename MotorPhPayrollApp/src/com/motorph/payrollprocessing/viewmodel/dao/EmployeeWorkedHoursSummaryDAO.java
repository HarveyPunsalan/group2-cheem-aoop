/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.viewmodel.dao;

import com.motorph.common.dao.AbstractDAO;
import com.motorph.common.dao.DAOException;
import com.motorph.common.mapper.ModelMapper;
import com.motorph.database.execution.SQLExecutor;
import com.motorph.payrollprocessing.viewmodel.Script.EmployeeWorkedHoursSummaryScript;
import com.motorph.payrollprocessing.viewmodel.model.EmployeeWorkedHoursSummaryViewModel;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class EmployeeWorkedHoursSummaryDAO extends AbstractDAO<EmployeeWorkedHoursSummaryViewModel> {

    public EmployeeWorkedHoursSummaryDAO(SQLExecutor executor, ModelMapper<EmployeeWorkedHoursSummaryViewModel> mapper) {
        super(executor, mapper);
    }

    @Override
    public List<EmployeeWorkedHoursSummaryViewModel> findAll() throws DAOException {
        return findAll(EmployeeWorkedHoursSummaryScript.SELECT_ALL);
    }

    public List<EmployeeWorkedHoursSummaryViewModel> findByPayPeriodId(int payPeriodId) throws DAOException {
        try {
            return executor.executeQuery(
                EmployeeWorkedHoursSummaryScript.SELECT_BY_PAY_PERIOD,
                List.of(payPeriodId),
                mapper
            );
        } catch (SQLException e) {
            throw new DAOException("Failed to fetch work hours summary for pay period ID: " + payPeriodId, e);
        }
    }

    public Optional<EmployeeWorkedHoursSummaryViewModel> findByPayPeriodIdAndEmployeeId(int payPeriodId, int employeeId) throws DAOException {
        try {
            return executor.queryForObject(
                EmployeeWorkedHoursSummaryScript.SELECT_BY_PAY_PERIOD_AND_EMPLOYEE_ID,
                List.of(employeeId, payPeriodId),
                mapper
            );
        } catch (SQLException e) {
            throw new DAOException("Failed to fetch work hours summary for pay period ID: " + payPeriodId, e);
        }
    }
    
    @Override
    public int insert(EmployeeWorkedHoursSummaryViewModel entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(EmployeeWorkedHoursSummaryViewModel entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int deleteById(int id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<EmployeeWorkedHoursSummaryViewModel> findById(int id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

