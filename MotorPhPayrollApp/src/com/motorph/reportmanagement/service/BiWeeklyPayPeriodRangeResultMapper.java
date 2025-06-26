/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.reportmanagement.service;

import com.motorph.common.util.mapper.ResultMapper;
import com.motorph.reportmanagement.model.BiWeeklyPayPeriodRange;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 63909
 */
public class BiWeeklyPayPeriodRangeResultMapper implements ResultMapper<BiWeeklyPayPeriodRange> {
    @Override
    public BiWeeklyPayPeriodRange map(ResultSet resultSet) throws SQLException {
        String payPeriod = resultSet.getString("Pay Period");
        return new BiWeeklyPayPeriodRange(payPeriod);
    }
}
