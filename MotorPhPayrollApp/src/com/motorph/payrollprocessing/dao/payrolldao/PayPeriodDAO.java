/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.dao.payrolldao;

import com.motorph.common.dao.AbstractDAO;
import com.motorph.common.dao.DAOException;
import com.motorph.database.execution.SQLExecutor;
import com.motorph.database.execution.script.payroll.PayPeriodScript;
import com.motorph.payrollprocessing.model.payroll.PayPeriod;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import com.motorph.common.mapper.ModelMapper;

public class PayPeriodDAO extends AbstractDAO<PayPeriod> {

    public PayPeriodDAO(SQLExecutor executor, ModelMapper<PayPeriod> mapper) {
        super(executor, mapper);
    }

    @Override
    public int insert(PayPeriod entity) throws DAOException {
        return insert(entity, PayPeriodScript.INSERT);
    }

    @Override
    public int update(PayPeriod entity) throws DAOException {
        return update(entity, PayPeriodScript.UPDATE);
    }

    @Override
    public int deleteById(int id) throws DAOException {
        return deleteById(id, PayPeriodScript.DELETE_BY_ID);
    }

    @Override
    public Optional<PayPeriod> findById(int id) throws DAOException {
        return findById(id, PayPeriodScript.SELECT_BY_ID);
    }

    @Override
    public List<PayPeriod> findAll() throws DAOException {
        return findAll(PayPeriodScript.SELECT_ALL);
    }
    
    // 🔍 Extra query: Find by start and end dates
    public Optional<PayPeriod> findByDateRange(LocalDate startDate, LocalDate endDate) throws DAOException {
        try {
            return executor.queryForObject(
                PayPeriodScript.SELECT_BY_DATES,
                List.of(Date.valueOf(startDate), Date.valueOf(endDate)),
                mapper
            );
        } catch (SQLException e) {
            throw new DAOException("Failed to find PayPeriod by date range", e);
        }
    }
}
