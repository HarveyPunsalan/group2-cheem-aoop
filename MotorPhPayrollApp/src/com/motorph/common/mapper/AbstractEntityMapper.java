/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.common.mapper;

import java.sql.Date; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides a reusable default implementation for common mapper logic.- mapAll() is implemented here.
 * - map() is left abstract for concrete mappers to define.
 * @param <T>
 */
public abstract class AbstractEntityMapper<T> implements ModelMapper<T> {

    @Override
    public List<T> mapAll(ResultSet rs) throws SQLException {
        List<T> results = new ArrayList<>();
        while (rs.next()) {
            results.add(map(rs));
        }
        return results;
    }

    // Must be implemented in concrete subclass
    @Override
    public abstract T map(ResultSet rs) throws SQLException;
    
    protected LocalDate toLocalDateSafe(ResultSet rs, String columnLabel) throws SQLException {
        Date sqlDate = rs.getDate(columnLabel);
        return (sqlDate != null) ? sqlDate.toLocalDate() : null;
    }
}

