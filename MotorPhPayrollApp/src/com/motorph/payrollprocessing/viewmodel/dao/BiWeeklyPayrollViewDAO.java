/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.viewmodel.dao;

import com.motorph.common.dao.AbstractDAO;
import com.motorph.common.dao.DAOException;
import com.motorph.database.execution.SQLExecutor;
import com.motorph.payrollprocessing.viewmodel.Script.BiWeeklyPayrollViewScript;
import java.util.List;
import java.util.Optional;
import com.motorph.common.mapper.ModelMapper;
import com.motorph.database.execution.script.Script;
import com.motorph.payrollprocessing.viewmodel.model.BiWeeklyPayrollViewModel;
import com.motorph.reportmanagement.model.PayrollBiWeeklySummaryReport;
import com.motorph.reportmanagement.service.PayrollBiWeeklySummaryResultMapper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class BiWeeklyPayrollViewDAO extends AbstractDAO<BiWeeklyPayrollViewModel> {
    
    public BiWeeklyPayrollViewDAO(SQLExecutor executor, ModelMapper<BiWeeklyPayrollViewModel> mapper) {
        super(executor, mapper);
    }
    
    @Override
    public Optional<BiWeeklyPayrollViewModel> findById(int id) throws DAOException {
        return findById(id, BiWeeklyPayrollViewScript.SELECT_BY_ID);
    }
    
    public List<PayrollBiWeeklySummaryReport> findByIds(int payPeriodId, List<Integer> employeeIds) throws DAOException, SQLException {
        if (employeeIds == null || employeeIds.isEmpty()) {
            return Collections.emptyList(); // or throw an IllegalArgumentException
        }
        
        Script script = BiWeeklyPayrollViewScript.SELECT_BY_IDs.formatScriptWithPlaceholders(employeeIds.size());
        
        List<Object> params = new ArrayList<>();
        params.add(payPeriodId);              // Add the single value
        params.addAll(employeeIds);           // Add the list
        
        return executor.executeQuery(script, params, PayrollBiWeeklySummaryResultMapper::map);
    }

    @Override
    public List<BiWeeklyPayrollViewModel> findAll() throws DAOException {
        return findAll(BiWeeklyPayrollViewScript.SELECT_ALL);
    }

    // Optional: Add methods like findByStatus, findByDateRange, etc.

    @Override
    public int insert(BiWeeklyPayrollViewModel entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(BiWeeklyPayrollViewModel entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int deleteById(int id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}