/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.dao.allowancedao;

import com.motorph.common.dao.AbstractDAO;
import com.motorph.common.dao.DAOException;
import com.motorph.database.execution.SQLExecutor;
import com.motorph.database.execution.script.allowance.EmployeeDefaultAllowanceScript;
import com.motorph.payrollprocessing.model.allowance.EmployeeDefaultAllowance;
import java.sql.*;
import java.util.List;
import java.util.Optional;
import com.motorph.common.mapper.ModelMapper;

public class EmployeeDefaultAllowanceDAO extends AbstractDAO<EmployeeDefaultAllowance> {

    public EmployeeDefaultAllowanceDAO(SQLExecutor executor, ModelMapper<EmployeeDefaultAllowance> mapper) {
        super(executor, mapper);
    }

    @Override
    public int insert(EmployeeDefaultAllowance entity) throws DAOException {
        return insert(entity, EmployeeDefaultAllowanceScript.INSERT);
    }

    @Override
    public Optional<EmployeeDefaultAllowance> findById(int id) throws DAOException {
        return findById(id, EmployeeDefaultAllowanceScript.SELECT_BY_ID);
    }

    @Override
    public List<EmployeeDefaultAllowance> findAll() throws DAOException {
        return findAll(EmployeeDefaultAllowanceScript.SELECT_ALL);
    }

    @Override
    public int update(EmployeeDefaultAllowance entity) throws DAOException {
        return update(entity, EmployeeDefaultAllowanceScript.UPDATE);
    }

    @Override
    public int deleteById(int id) throws DAOException {
        return deleteById(id, EmployeeDefaultAllowanceScript.DELETE_BY_ID);
    }

    // 🔍 Extra Query
    public Optional<EmployeeDefaultAllowance> findByEmployeeIdAndAllowanceId(int employeeId, int allowanceId) throws DAOException {
        try {
            return executor.queryForObject(
                EmployeeDefaultAllowanceScript.SELECT_BY_EMPLOYEE_AND_ALLOWANCE,
                List.of(employeeId, allowanceId),
                mapper
            );
        } catch (SQLException e) {
            throw new DAOException("Failed to find by employee and allowance ID", e);
        }
    }
}

