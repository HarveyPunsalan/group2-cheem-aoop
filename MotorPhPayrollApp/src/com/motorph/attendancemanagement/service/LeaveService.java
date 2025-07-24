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
  
    public boolean createLeave(Leave leave) {
        String sql = "INSERT INTO employee_leave (employee_id, leave_type, start_date, end_date, total_days) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, leave.getEmployeeId());
            stmt.setString(2, leave.getLeaveType());
            stmt.setDate(3, Date.valueOf(leave.getStartDate()));
            stmt.setDate(4, Date.valueOf(leave.getEndDate()));
            stmt.setDouble(5, leave.getTotalDays());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet keys = stmt.getGeneratedKeys();
                if (keys.next()) {
                    leave.setLeaveRequestId(keys.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
  
    public List<Leave> getAllLeaves() {
        List<Leave> leaves = new ArrayList<>();
        String sql = "SELECT * FROM employee_leave";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Leave leave = new Leave();
                leave.setLeaveRequestId(rs.getInt("leave_id"));
                leave.setEmployeeId(rs.getInt("employee_id"));
                leave.setLeaveType(rs.getString("leave_type"));
                leave.setStartDate(rs.getDate("start_date").toLocalDate());
                leave.setEndDate(rs.getDate("end_date").toLocalDate());
                leave.setTotalDays(rs.getDouble("total_days"));
                leaves.add(leave);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leaves;
    }
  
    public Leave getLeaveById(int leaveRequestId) {
        String sql = "SELECT * FROM employee_leave WHERE leave_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, leaveRequestId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Leave leave = new Leave();
                    leave.setLeaveRequestId(rs.getInt("leave_id"));
                    leave.setEmployeeId(rs.getInt("employee_id"));
                    leave.setLeaveType(rs.getString("leave_type"));
                    leave.setStartDate(rs.getDate("start_date").toLocalDate());
                    leave.setEndDate(rs.getDate("end_date").toLocalDate());
                    leave.setTotalDays(rs.getDouble("total_days"));
                    return leave;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateLeave(Leave leave) {
        String sql = "UPDATE employee_leave SET employee_id = ?, leave_type = ?, start_date = ?, end_date = ?, " +
                     "total_days = ? WHERE leave_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, leave.getEmployeeId());
            stmt.setString(2, leave.getLeaveType());
            stmt.setDate(3, Date.valueOf(leave.getStartDate()));
            stmt.setDate(4, Date.valueOf(leave.getEndDate()));
            stmt.setDouble(5, leave.getTotalDays());
            stmt.setInt(6, leave.getLeaveRequestId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteLeave(int leaveRequestId) {
        String sql = "DELETE FROM employee_leave WHERE leave_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, leaveRequestId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
