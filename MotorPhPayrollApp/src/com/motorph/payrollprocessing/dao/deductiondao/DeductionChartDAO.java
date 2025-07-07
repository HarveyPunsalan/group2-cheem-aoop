/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.dao.deductiondao;

import com.motorph.common.dao.AbstractDAO;
import com.motorph.common.dao.DAOException;
import com.motorph.database.execution.SQLExecutor;
import com.motorph.database.execution.script.deduction.DeductionChartScript;
import com.motorph.payrollprocessing.model.deduction.DeductionChart;
import java.util.List;
import java.util.Optional;
import com.motorph.common.mapper.ModelMapper;

public class DeductionChartDAO extends AbstractDAO<DeductionChart> {

    public DeductionChartDAO(SQLExecutor executor, ModelMapper<DeductionChart> mapper) {
        super(executor, mapper);
    }

    @Override
    public int insert(DeductionChart entity) throws DAOException {
        return insert(entity, DeductionChartScript.INSERT);
    }

    @Override
    public Optional<DeductionChart> findById(int id) throws DAOException {
        return findById(id, DeductionChartScript.SELECT_BY_ID);
    }

    @Override
    public List<DeductionChart> findAll() throws DAOException {
        return findAll(DeductionChartScript.SELECT_ALL);
    }

    @Override
    public int update(DeductionChart entity) throws DAOException {
        return update(entity, DeductionChartScript.UPDATE);
    }

    @Override
    public int deleteById(int id) throws DAOException {
        return deleteById(id, DeductionChartScript.DELETE_BY_ID);
    }
}

