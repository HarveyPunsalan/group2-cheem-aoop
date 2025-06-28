/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.common.util.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 63909
 * @param <T>
 */
public interface ResultMapper<T> {
    T map(ResultSet rs) throws SQLException;
}
