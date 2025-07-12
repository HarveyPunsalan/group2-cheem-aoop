/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.attendancemanagement.service;

import com.motorph.attendancemanagement.model.Leave;
import com.motorph.attendancemanagement.model.LeaveType;
import com.motorph.common.util.CollectionUtils;
import com.motorph.database.connection.DatabaseService;

import javax.swing.DefaultComboBoxModel;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class LeaveService {
    private List<LeaveType> leaveTypeList;
    private Map<String, LeaveType> leaveMapByLeaveTypeID;

    public LeaveService() {
        this.leaveTypeList = loadLeaveTypesFromDB();
        this.leaveMapByLeaveTypeID = CollectionUtils.listToMap(leaveTypeList, LeaveType::getID);
    }

    // ✅ COMBOBOX for UI
    public DefaultComboBoxModel<String> getLeaveTypeComboBoxModel() {
        String[] leaveTypeArray = leaveTypeList.stream()
            .map(LeaveType::getLeaveTypeName)
            .toArray(String[]::new);
        return new DefaultComboBoxModel<>(leaveTypeArray);
    }

    // ✅ SQL → Load Leave Type Records
    private List<LeaveType> loadLeaveTypesFromDB() {
        List<LeaveType> types = new ArrayList<>();
        String sql = "SELECT * FROM leave_type";

        try (Connection conn = DatabaseService.connectToMotorPH();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                LeaveType type = new LeaveType(
                    rs.getString("leave_type_id"),
                    rs.getString("leave_type_name"),
                    rs.getString("description"),
                    rs.getBoolean("is_paid_leave"),
                    rs.getInt("min_days_allowed"),
                    rs.getInt("max_days_allowed")
                );
                types.add(type);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Log better in production
        }

        return types;
    }

    // ✅ SQL → Get leave records by employee ID
    public List<Leave> getLeavesByEmployeeId(int employeeId) {
        List<Leave> leaves = new ArrayList<>();
        String sql = "SELECT * FROM employee_leave WHERE employee_id = ?";

        try (Connection conn = DatabaseService.connectToMotorPH();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String[] leaveData = new String[] {
                        String.valueOf(rs.getString("leave_id")),
                        String.valueOf(rs.getInt("employee_id")),
                        rs.getString("leave_type"),
                        rs.getDate("start_date").toLocalDate().toString(),
                        rs.getDate("end_date").toLocalDate().toString(),
                        String.valueOf(rs.getDouble("total_days")),
                        String.valueOf(rs.getInt("request_id"))
                    };

                    Leave leave = new Leave(leaveData);
                    leaves.add(leave);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return leaves;
    }

    // ✅ SQL → Approve or Reject Leave by request_id
    public boolean approveLeave(int requestId, boolean isApproved) {
        String sql = "UPDATE request SET request_status = ?, processed_date = ?, processed_by = ? WHERE request_id = ?";
        String status = isApproved ? "APPROVED" : "REJECTED";

        try (Connection conn = DatabaseService.connectToMotorPH();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setInt(3, 999); // Replace with actual user ID
            stmt.setInt(4, requestId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ✅ SQL → INSERT new leave record
    public boolean insertLeave(Leave leave) {
        String sql = "INSERT INTO employee_leave (leave_id, employee_id, leave_type, start_date, end_date, total_days, request_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseService.connectToMotorPH();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, leave.getID());
            stmt.setInt(2, leave.getEmployeeID());
            stmt.setString(3, leave.getLeaveType());
            stmt.setDate(4, java.sql.Date.valueOf(leave.getStartDate()));
            stmt.setDate(5, java.sql.Date.valueOf(leave.getEndDate()));
            stmt.setDouble(6, leave.getTotalDays());
            stmt.setInt(7, leave.getRequestId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ✅ SQL → DELETE leave record by ID
    public boolean deleteLeaveById(String leaveId) {
        String sql = "DELETE FROM employee_leave WHERE leave_id = ?";

        try (Connection conn = DatabaseService.connectToMotorPH();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, leaveId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
