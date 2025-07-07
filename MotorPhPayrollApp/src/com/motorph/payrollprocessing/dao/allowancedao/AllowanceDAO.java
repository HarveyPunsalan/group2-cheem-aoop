/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.dao.allowancedao;

import com.motorph.common.dao.AbstractDAO;
import com.motorph.common.dao.DAOException;
import com.motorph.database.execution.SQLExecutor;
import com.motorph.database.execution.script.allowance.AllowanceScript;
import com.motorph.payrollprocessing.model.allowance.Allowance;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.motorph.common.mapper.ModelMapper;

public class AllowanceDAO extends AbstractDAO<Allowance> {

    public AllowanceDAO(SQLExecutor executor, ModelMapper<Allowance> mapper) {
        super(executor, mapper);
    }

    @Override
    public int insert(Allowance entity) throws DAOException {
        return insert(entity, AllowanceScript.INSERT);
    }

    @Override
    public Optional<Allowance> findById(int id) throws DAOException {
        return findById(id, AllowanceScript.SELECT_BY_ID);
    }

    @Override
    public List<Allowance> findAll() throws DAOException {
        return findAll(AllowanceScript.SELECT_ALL);
    }

    @Override
    public int update(Allowance entity) throws DAOException {
        return update(entity, AllowanceScript.UPDATE);
    }

    @Override
    public int deleteById(int id) throws DAOException {
        return deleteById(id, AllowanceScript.DELETE_BY_ID);
    }
}
