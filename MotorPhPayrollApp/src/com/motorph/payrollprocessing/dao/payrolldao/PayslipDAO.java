/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.dao.payrolldao;

import com.motorph.common.dao.AbstractDAO;
import com.motorph.common.dao.DAOException;
import com.motorph.common.mapper.ModelMapper;
import com.motorph.database.execution.SQLExecutor;
import com.motorph.database.execution.script.payroll.PayslipScript;
import com.motorph.payrollprocessing.model.payroll.Payslip;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PayslipDAO extends AbstractDAO<Payslip> {

    public PayslipDAO(SQLExecutor executor, ModelMapper<Payslip> mapper) {
        super(executor, mapper);
    }

    @Override
    public int insert(Payslip entity) throws DAOException {
        return insert(entity, PayslipScript.INSERT);
    }

    @Override
    public int update(Payslip entity) throws DAOException {
        return update(entity, PayslipScript.UPDATE);
    }

    @Override
    public int deleteById(int id) throws DAOException {
        return deleteById(id, PayslipScript.DELETE_BY_ID);
    }

    @Override
    public Optional<Payslip> findById(int id) throws DAOException {
        return findById(id, PayslipScript.SELECT_BY_ID);
    }

    @Override
    public List<Payslip> findAll() throws DAOException {
        return findAll(PayslipScript.SELECT_ALL);
    }

    // 🔍 Extra Query: Find by pay period and employee
    public Optional<Payslip> findByPayPeriodAndEmployee(int payPeriodId, int employeeId) throws DAOException {
        try {
            return executor.queryForObject(
                PayslipScript.SELECT_BY_PAYPERIOD_AND_EMPLOYEE,
                List.of(payPeriodId, employeeId),
                mapper
            );
        } catch (SQLException e) {
            throw new DAOException("Failed to find payslip by pay period and employee", e);
        }
    }
}

