/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.motorph.common.mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ModelMapper<T> {
    T map(ResultSet resultSet) throws SQLException;
    List<T> mapAll(ResultSet resultSet) throws SQLException;
    void toInsertParams(PreparedStatement ps, T model) throws SQLException;
    void toUpdateParams(PreparedStatement ps, T model) throws SQLException;
    void toDeleteParams(PreparedStatement ps, T model) throws SQLException;
}
