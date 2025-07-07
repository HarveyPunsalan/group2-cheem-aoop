/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.dao.payrolldao;

import com.motorph.common.dao.AbstractDAO;
import com.motorph.common.dao.DAOException;
import com.motorph.database.execution.SQLExecutor;
import com.motorph.database.execution.script.payroll.PayrollRecordScript;
import com.motorph.payrollprocessing.model.payroll.PayrollRecord;
import java.sql.*;
import java.util.List;
import java.util.Optional;
import com.motorph.common.mapper.ModelMapper;

public class PayrollRecordDAO extends AbstractDAO<PayrollRecord> {

    public PayrollRecordDAO(SQLExecutor executor, ModelMapper<PayrollRecord> mapper) {
        super(executor, mapper);
    }

    @Override
    public int insert(PayrollRecord entity) throws DAOException {
        return insert(entity, PayrollRecordScript.INSERT);
    }
    
     @Override
    public int update(PayrollRecord entity) throws DAOException {
        return update(entity, PayrollRecordScript.UPDATE);
    }

    @Override
    public int deleteById(int id) throws DAOException {
        return deleteById(id, PayrollRecordScript.DELETE_BY_ID);
    }

    @Override
    public Optional<PayrollRecord> findById(int id) throws DAOException {
        return findById(id, PayrollRecordScript.SELECT_BY_ID);
    }

    @Override
    public List<PayrollRecord> findAll() throws DAOException {
        return findAll(PayrollRecordScript.SELECT_ALL);
    }

    // 🔍 Extra Query: Find payroll for specific employee and period
    public Optional<PayrollRecord> findByEmployeeAndPayPeriod(int employeeId, int payPeriodId) throws DAOException {
        try {
            return executor.queryForObject(
                PayrollRecordScript.SELECT_BY_EMPLOYEE_AND_PERIOD,
                List.of(employeeId, payPeriodId),
                mapper
            );
        } catch (SQLException e) {
            throw new DAOException("Failed to find PayrollRecord by employee and pay period", e);
        }
    }
}
