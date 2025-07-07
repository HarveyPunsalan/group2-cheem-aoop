/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.database.execution;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 63909
 */

/**
 * Functional interface to map a row from a {@link ResultSet} into a Java object.
 * This abstraction allows for flexible and reusable result mapping strategies
 * across different DAO implementations.
 *
 * @param <T> the type of object to be mapped from the {@code ResultSet}
 */
@FunctionalInterface
public interface EntityMapper<T> {
    
     /**
     * Maps the current row of the given {@code ResultSet} to an object of type {@code T}.
     *
     * @param resultSet the {@code ResultSet} positioned at the current row
     * @return an instance of {@code T} populated with the row data
     * @throws SQLException if a database access error occurs
     */
    T map(ResultSet resultSet) throws SQLException;
}
