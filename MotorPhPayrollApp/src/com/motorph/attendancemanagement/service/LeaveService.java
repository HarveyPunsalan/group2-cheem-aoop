/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.attendancemanagement.service;

import com.motorph.attendancemanagement.model.Leave;
import com.motorph.database.connection.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaveService {

    private final Connection conn;

    public LeaveService() {
        this.conn = DatabaseService.connectToMotorPH();
    }

    /**
     * Creates a new leave entry in the database.
     */
    public boolean createLeave(Leave leave) {
        String sql = "INSERT INTO employee_leave (leave_id, employee_id, leave_type, start_date, end_date, total_days, request_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, leave.getLeaveID());
            stmt.setInt(2, leave.getEmployeeId());
            stmt.setString(3, leave.getLeaveType());
            stmt.setDate(4, Date.valueOf(leave.getStartDate()));
            stmt.setDate(5, Date.valueOf(leave.getEndDate()));
            stmt.setDouble(6, leave.getTotalDays());
            stmt.setInt(7, leave.getRequestId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Returns all leave entries from the database.
     */
    public List<Leave> getAllLeaves() {
        List<Leave> leaves = new ArrayList<>();
        String sql = "SELECT * FROM employee_leave";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Leave leave = new Leave();
                leave.setLeaveID(rs.getString("leave_id"));
                leave.setEmployeeId(rs.getInt("employee_id"));
                leave.setLeaveType(rs.getString("leave_type"));
                leave.setStartDate(rs.getDate("start_date").toLocalDate());
                leave.setEndDate(rs.getDate("end_date").toLocalDate());
                leave.setTotalDays(rs.getDouble("total_days"));
                leave.setRequestId(rs.getInt("request_id"));
                leaves.add(leave);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leaves;
    }

    /**
     * Retrieves a leave entry by its leave ID.
     */
    public Leave getLeaveById(String leaveId) {
        String sql = "SELECT * FROM employee_leave WHERE leave_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, leaveId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Leave leave = new Leave();
                    leave.setLeaveID(rs.getString("leave_id"));
                    leave.setEmployeeId(rs.getInt("employee_id"));
                    leave.setLeaveType(rs.getString("leave_type"));
                    leave.setStartDate(rs.getDate("start_date").toLocalDate());
                    leave.setEndDate(rs.getDate("end_date").toLocalDate());
                    leave.setTotalDays(rs.getDouble("total_days"));
                    leave.setRequestId(rs.getInt("request_id"));
                    return leave;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Updates an existing leave entry.
     */
    public boolean updateLeave(Leave leave) {
        String sql = "UPDATE employee_leave SET employee_id = ?, leave_type = ?, start_date = ?, end_date = ?, " +
                     "total_days = ?, request_id = ? WHERE leave_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, leave.getEmployeeId());
            stmt.setString(2, leave.getLeaveType());
            stmt.setDate(3, Date.valueOf(leave.getStartDate()));
            stmt.setDate(4, Date.valueOf(leave.getEndDate()));
            stmt.setDouble(5, leave.getTotalDays());
            stmt.setInt(6, leave.getRequestId());
            stmt.setString(7, leave.getLeaveID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Deletes a leave entry by its leave ID.
     */
    public boolean deleteLeave(String leaveId) {
        String sql = "DELETE FROM employee_leave WHERE leave_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, leaveId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
