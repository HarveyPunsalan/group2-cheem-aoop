/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.common.dao;

import com.motorph.database.execution.SQLExecutor;
import com.motorph.database.execution.script.Script;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import com.motorph.common.mapper.ModelMapper;

public abstract class AbstractDAO<T> implements BaseDAO<T> {

    protected final SQLExecutor executor;
    protected final ModelMapper<T> mapper;

    public AbstractDAO(SQLExecutor executor, ModelMapper<T> mapper) {
        this.executor = executor;
        this.mapper = mapper;
    }

    protected int insert(T entity, Script insertScript) {
        try {
            return executor.executeUpdate(insertScript, ps -> mapper.toInsertParams(ps, entity));
        } catch (RuntimeException e) {
            throw new DAOException("Insert failed: " + insertScript.getQuery(), e);
        }
    }

    protected int update(T entity, Script updateScript) {
        try {
            return executor.executeUpdate(updateScript, ps -> mapper.toUpdateParams(ps,entity));
        } catch (RuntimeException e) {
            throw new DAOException("Update failed: " + updateScript.toString(), e);
        }
    }

    protected int deleteById(int id, Script deleteScript) {
        try {
            return executor.executeUpdate(deleteScript, List.of(id));
        } catch (RuntimeException e) {
            throw new DAOException("Delete failed: " + deleteScript.toString(), e);
        }
    }

    protected Optional<T> findById(int id, Script selectByIdScript) {
        try {
            return executor.queryForObject(selectByIdScript, List.of(id), mapper);
        } catch (SQLException e) {
            throw new DAOException("Find by ID failed: " + selectByIdScript.toString(), e);
        }
    }

    protected List<T> findAll(Script selectAllScript) {
        try {
            return executor.executeQuery(selectAllScript, mapper);
        } catch (SQLException e) {
            throw new DAOException("Find all failed: " + selectAllScript.toString(), e);
        }
    }
}

