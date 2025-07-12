/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.attendancemanagement.service;

import com.motorph.attendancemanagement.model.Overtime;
import com.motorph.database.connection.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OvertimeService {

    /**
     * Retrieves a list of Overtime records for a given employee ID.
     * @param employeeId The employee's ID.
     * @return List of Overtime objects, empty if none found.
     */
    public List<Overtime> getOvertimeByEmployeeId(int employeeId) {
        List<Overtime> overtimeList = new ArrayList<>();
        String sql = "SELECT * FROM overtime WHERE employee_id = ?";

        try (Connection conn = DatabaseService.connectToMotorPH();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Overtime overtime = new Overtime();
                    overtime.setID(String.valueOf(rs.getInt("overtime_id")));
                    overtime.setRequestId(rs.getInt("request_id"));
                    overtime.setEmployeeID(rs.getInt("employee_id"));
                    overtime.setDtrId(rs.getInt("dtr_id"));
                    overtimeList.add(overtime);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return overtimeList;
    }

    /**
     * Inserts a new Overtime record into the database.
     * Note: The request_id, employee_id, and dtr_id must already exist in their tables.
     * @param overtime The Overtime object to save.
     * @return true if insert succeeded; false otherwise.
     */
    public boolean saveOvertime(Overtime overtime) {
        String sql = "INSERT INTO overtime (request_id, employee_id, dtr_id) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseService.connectToMotorPH();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, overtime.getRequestId());
            stmt.setInt(2, overtime.getEmployeeID());
            stmt.setInt(3, overtime.getDtrId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Updates the request_id of an existing overtime record.
     * @param overtimeId The ID of the overtime record to update.
     * @param newRequestId The new request ID to set.
     * @return true if update succeeded; false otherwise.
     */
    public boolean updateOvertimeRequest(String overtimeId, int newRequestId) {
        String sql = "UPDATE overtime SET request_id = ? WHERE overtime_id = ?";

        try (Connection conn = DatabaseService.connectToMotorPH();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newRequestId);
            stmt.setInt(2, Integer.parseInt(overtimeId));

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Deletes an overtime record by its ID.
     * @param overtimeId The ID of the overtime record to delete.
     * @return true if delete succeeded; false otherwise.
     */
    public boolean deleteOvertimeById(String overtimeId) {
        String sql = "DELETE FROM overtime WHERE overtime_id = ?";

        try (Connection conn = DatabaseService.connectToMotorPH();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, Integer.parseInt(overtimeId));

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
