/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.motorph.common.dao;

import java.util.List;
import java.util.Optional;

public interface BaseDAO<T> {
    int insert(T entity) throws DAOException;    

    int update(T entity) throws DAOException;

    int deleteById(int id) throws DAOException;
    
//    int delete(T entity) throws DAOException;
    
    Optional<T> findById(int id) throws DAOException;

    List<T> findAll() throws DAOException;
    
    default List<T> findAll(int offset, int limit) throws DAOException {
        throw new UnsupportedOperationException("Pagination not implemented");
    }
}

