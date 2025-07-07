/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.dao.allowancedao;

import com.motorph.common.dao.AbstractDAO;
import com.motorph.common.dao.DAOException;
import com.motorph.database.execution.SQLExecutor;
import com.motorph.database.execution.script.allowance.PayrollAllowanceScript;
import com.motorph.payrollprocessing.model.allowance.PayrollAllowance;
import java.sql.*;
import java.util.List;
import java.util.Optional;
import com.motorph.common.mapper.ModelMapper;

public class PayrollAllowanceDAO extends AbstractDAO<PayrollAllowance> {

    public PayrollAllowanceDAO(SQLExecutor executor, ModelMapper<PayrollAllowance> mapper) {
        super(executor, mapper);
    }

    @Override
    public int insert(PayrollAllowance entity) throws DAOException {
        return insert(entity, PayrollAllowanceScript.INSERT);
    }
    
    @Override
    public int update(PayrollAllowance entity) {
        throw new UnsupportedOperationException("PayrollAllowance is not designed for updates.");
    }

    @Override
    public int deleteById(int id) throws DAOException {
        return deleteById(id, PayrollAllowanceScript.DELETE_BY_ID);
    }

    @Override
    public Optional<PayrollAllowance> findById(int id) throws DAOException {
        return findById(id, PayrollAllowanceScript.SELECT_BY_ID);
    }

    @Override
    public List<PayrollAllowance> findAll() throws DAOException {
        return findAll(PayrollAllowanceScript.SELECT_ALL);
    }

    // 🔍 Extra Query
    public Optional<PayrollAllowance> findByPayrollIdAndAllowanceId(int payrollId, int sourceAllowanceId) throws DAOException {
        try {
            return executor.queryForObject(
                PayrollAllowanceScript.SELECT_BY_PAYROLL_AND_ALLOWANCE,
                List.of(payrollId, sourceAllowanceId),
                mapper
            );
        } catch (SQLException e) {
            throw new DAOException("Failed to find PayrollAllowance by composite key", e);
        }
    }
}

