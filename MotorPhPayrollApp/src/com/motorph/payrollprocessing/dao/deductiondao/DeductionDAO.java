/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.dao.deductiondao;

import com.motorph.common.dao.AbstractDAO;
import com.motorph.common.dao.DAOException;
import com.motorph.database.execution.SQLExecutor;
import com.motorph.database.execution.script.deduction.DeductionScript;
import com.motorph.payrollprocessing.model.deduction.Deduction;
import java.util.List;
import java.util.Optional;
import com.motorph.common.mapper.ModelMapper;

public class DeductionDAO extends AbstractDAO<Deduction> {

    public DeductionDAO(SQLExecutor executor, ModelMapper<Deduction> mapper) {
        super(executor, mapper);
    }

    @Override
    public int insert(Deduction entity) throws DAOException {
        return insert(entity, DeductionScript.INSERT);
    }

    @Override
    public int update(Deduction entity) throws DAOException {
        return update(entity, DeductionScript.UPDATE);
    }

    @Override
    public int deleteById(int id) throws DAOException {
        return deleteById(id, DeductionScript.DELETE_BY_ID);
    }
    
    @Override
    public Optional<Deduction> findById(int id) throws DAOException {
        return findById(id, DeductionScript.SELECT_BY_ID);
    }

    @Override
    public List<Deduction> findAll() throws DAOException {
        return findAll(DeductionScript.SELECT_ALL);
    }
}

