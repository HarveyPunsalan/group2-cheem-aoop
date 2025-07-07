/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.dao.deductiondao;

import com.motorph.common.dao.AbstractDAO;
import com.motorph.common.dao.DAOException;
import com.motorph.database.execution.SQLExecutor;
import com.motorph.database.execution.script.deduction.EmployeeGovernmentDeductionScript;
import com.motorph.payrollprocessing.model.deduction.EmployeeGovernmentDeduction;
import java.sql.*;
import java.util.List;
import java.util.Optional;
import com.motorph.common.mapper.ModelMapper;

public class EmployeeGovernmentDeductionDAO extends AbstractDAO<EmployeeGovernmentDeduction> {

    public EmployeeGovernmentDeductionDAO(SQLExecutor executor, ModelMapper<EmployeeGovernmentDeduction> mapper) {
        super(executor, mapper);
    }

    @Override
    public int insert(EmployeeGovernmentDeduction entity) throws DAOException {
        return insert(entity, EmployeeGovernmentDeductionScript.INSERT);
    }

    @Override
    public int update(EmployeeGovernmentDeduction entity) throws DAOException {
        return update(entity, EmployeeGovernmentDeductionScript.UPDATE);
    }

    @Override
    public int deleteById(int id) throws DAOException {
        return deleteById(id, EmployeeGovernmentDeductionScript.DELETE_BY_ID);
    }    
    
    @Override
    public Optional<EmployeeGovernmentDeduction> findById(int id) throws DAOException {
        return findById(id, EmployeeGovernmentDeductionScript.SELECT_BY_ID);
    }

    @Override
    public List<EmployeeGovernmentDeduction> findAll() throws DAOException {
        return findAll(EmployeeGovernmentDeductionScript.SELECT_ALL);
    }

    // 🔍 Extra query: Find by chartId and payrollId
    public Optional<EmployeeGovernmentDeduction> findByChartAndPayroll(int chartId, int payrollId) throws DAOException {
        try {
            return executor.queryForObject(
                EmployeeGovernmentDeductionScript.SELECT_BY_CHART_AND_PAYROLL,
                List.of(chartId, payrollId),
                mapper
            );
        } catch (SQLException e) {
            throw new DAOException("Failed to find EmployeeGovernmentDeduction by chart and payroll", e);
        }
    }
}
