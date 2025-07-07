/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.payrollprocessing.mapper.deductionmapper;

import com.motorph.common.mapper.AbstractEntityMapper;
import com.motorph.payrollprocessing.model.deduction.DeductionChart;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeductionChartMapper extends AbstractEntityMapper<DeductionChart> {

    @Override
    public DeductionChart map(ResultSet resultSet) throws SQLException {
        return new DeductionChart.Builder()
            .deductionChartId(resultSet.getInt("gov_deduction_chart_id"))
            .deductionTypeId(resultSet.getInt("gov_deduction_type_id"))
            .minimumRange(resultSet.getBigDecimal("minimum_range"))
            .maximumRange(resultSet.getBigDecimal("maximum_range"))
            .fixedAmount(resultSet.getBigDecimal("fixed_amount"))
            .employeeDeductionRate(resultSet.getBigDecimal("employee_deduction_rate"))
            .employerContributionShare(resultSet.getBigDecimal("employer_contribution_share"))
            .hasContributionCap(resultSet.getBoolean("has_contribution_cap"))
            .minContribution(resultSet.getBigDecimal("min_contribution"))
            .maxContribution(resultSet.getBigDecimal("max_contribution"))
            .effectiveAt(resultSet.getDate("effective_at").toLocalDate())
            .updatedAt(resultSet.getDate("updated_at").toLocalDate())
            .notes(resultSet.getString("notes"))
            .build();
    }

    // No insert/update/delete binding needed for read-only reference data
    @Override
    public void toInsertParams(java.sql.PreparedStatement preparedStatement, DeductionChart deduction) {
        throw new UnsupportedOperationException("Insert not supported for GovernmentDeductionChart (read-only reference)");
    }

    @Override
    public void toUpdateParams(java.sql.PreparedStatement preparedStatement, DeductionChart deduction) {
        throw new UnsupportedOperationException("Update not supported for GovernmentDeductionChart (read-only reference)");
    }

    @Override
    public void toDeleteParams(java.sql.PreparedStatement preparedStatement, DeductionChart deduction) {
        throw new UnsupportedOperationException("Delete not supported for GovernmentDeductionChart (read-only reference)");
    }
}

