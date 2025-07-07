/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.viewmodel.dao;

import com.motorph.common.dao.AbstractDAO;
import com.motorph.common.dao.DAOException;
import com.motorph.database.execution.SQLExecutor;
import com.motorph.payrollprocessing.viewmodel.Script.BiWeeklyPayrollViewScipt;
import java.util.List;
import java.util.Optional;
import com.motorph.common.mapper.ModelMapper;
import com.motorph.payrollprocessing.viewmodel.model.BiWeeklyPayrollViewModel;

public class BiWeeklyPayrollViewDAO extends AbstractDAO<BiWeeklyPayrollViewModel> {
    
    public BiWeeklyPayrollViewDAO(SQLExecutor executor, ModelMapper<BiWeeklyPayrollViewModel> mapper) {
        super(executor, mapper);
    }
    
    @Override
    public Optional<BiWeeklyPayrollViewModel> findById(int id) throws DAOException {
        return findById(id, BiWeeklyPayrollViewScipt.SELECT_BY_ID);
    }

    @Override
    public List<BiWeeklyPayrollViewModel> findAll() throws DAOException {
        return findAll(BiWeeklyPayrollViewScipt.SELECT_ALL);
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