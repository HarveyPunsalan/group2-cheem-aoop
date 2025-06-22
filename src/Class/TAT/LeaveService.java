/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.TAT;

import javax.swing.DefaultComboBoxModel;
import java.sql.*;
import java.util.*;
import Class.SQLService.LeaveSQLService;
import java.util.List;

public class LeaveService {

    private Connection conn;

    // UI-friendly constructor (no DB)
    public LeaveService() {
        this.conn = null;
    }

    // DB-use constructor
    public LeaveService(Connection conn) {
        this.conn = conn;
    }

    // Create leave type
    public boolean createLeaveType(LeaveType leave) {
        if (conn == null) return false;

        String sql = "INSERT INTO leave_type (leave_type_id, leave_type_name, description, is_paid_leave, min_days_allowed, max_days_allowed) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, leave.getID());
            pstmt.setString(2, leave.getLeaveTypeName());
            pstmt.setString(3, leave.getDescription());
            pstmt.setBoolean(4, leave.isPaidLeave());
            pstmt.setInt(5, leave.getMinDaysAllowed());
            pstmt.setInt(6, leave.getMaxDaysAllowed());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error adding leave type:");
            e.printStackTrace();
            return false;
        }
    }

    // Read all leave types
    public List<LeaveType> getAllLeaveTypes() {
        List<LeaveType> leaveList = new ArrayList<>();
        if (conn == null) return leaveList;

        String sql = "SELECT * FROM leave_type";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                leaveList.add(new LeaveType(
                    rs.getString("leave_type_id"),
                    rs.getString("leave_type_name"),
                    rs.getString("description"),
                    rs.getBoolean("is_paid_leave"),
                    rs.getInt("min_days_allowed"),
                    rs.getInt("max_days_allowed")
                ));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error reading leave types:");
            e.printStackTrace();
        }

        return leaveList;
    }

    // Read leave type by ID
    public LeaveType getLeaveTypeByID(String id) {
        if (conn == null) return null;

        String sql = "SELECT * FROM leave_type WHERE leave_type_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new LeaveType(
                    rs.getString("leave_type_id"),
                    rs.getString("leave_type_name"),
                    rs.getString("description"),
                    rs.getBoolean("is_paid_leave"),
                    rs.getInt("min_days_allowed"),
                    rs.getInt("max_days_allowed")
                );
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching leave type by ID:");
            e.printStackTrace();
        }

        return null;
    }

    // Update leave type
    public boolean updateLeaveType(LeaveType leave) {
        if (conn == null) return false;

        String sql = "UPDATE leave_type SET leave_type_name = ?, description = ?, is_paid_leave = ?, min_days_allowed = ?, max_days_allowed = ? WHERE leave_type_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, leave.getLeaveTypeName());
            pstmt.setString(2, leave.getDescription());
            pstmt.setBoolean(3, leave.isPaidLeave());
            pstmt.setInt(4, leave.getMinDaysAllowed());
            pstmt.setInt(5, leave.getMaxDaysAllowed());
            pstmt.setString(6, leave.getID());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error updating leave type:");
            e.printStackTrace();
            return false;
        }
    }

    // Delete leave type
    public boolean deleteLeaveType(String id) {
        if (conn == null) return false;

        String sql = "DELETE FROM leave_type WHERE leave_type_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error deleting leave type:");
            e.printStackTrace();
            return false;
        }
    }

    // Set database connection
    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    // UI combo box model for leave types
    public DefaultComboBoxModel<String> getLeaveTypeComboBoxModel() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        List<LeaveType> leaveTypes = getAllLeaveTypes();

        for (LeaveType leave : leaveTypes) {
            model.addElement(leave.getLeaveTypeName());
        }

        return model;
    }

    // ✅ ADDED: Get a leave by its ID
    public Leave getLeaveByID(String leaveID) {
        if (conn == null) return null;

        String sql = "SELECT * FROM employee_leave WHERE leave_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, leaveID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Leave(
                    rs.getString("leave_id"),
                    rs.getString("employee_id"),
                    rs.getString("leave_type"),
                    rs.getDate("start_date").toLocalDate(),
                    rs.getDate("end_date").toLocalDate(),
                    rs.getDouble("total_days"),
                    rs.getString("request_id")
                );
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching leave by ID:");
            e.printStackTrace();
        }

        return null;
    }

    // ✅ ADDED: Save a leave record (insert or update)
    public boolean saveLeave(Leave leave) {
        if (conn == null) return false;

        String sql = "INSERT INTO employee_leave (leave_id, employee_id, leave_type, start_date, end_date, total_days, request_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE employee_id = VALUES(employee_id), leave_type = VALUES(leave_type), start_date = VALUES(start_date), " +
                     "end_date = VALUES(end_date), total_days = VALUES(total_days), request_id = VALUES(request_id)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, leave.getID());
            pstmt.setString(2, leave.getEmployeeID());
            pstmt.setString(3, leave.getLeaveType());
            pstmt.setDate(4, java.sql.Date.valueOf(leave.getStartDate()));
            pstmt.setDate(5, java.sql.Date.valueOf(leave.getEndDate()));
            pstmt.setDouble(6, leave.getTotalDays());
            pstmt.setString(7, leave.getRequestID());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error saving leave:");
            e.printStackTrace();
            return false;
        }
    }

    // ✅ ADDED: Get all leave records
    public List<Leave> getAllLeaves() {
        List<Leave> leaves = new ArrayList<>();
        if (conn == null) return leaves;

        String sql = "SELECT * FROM employee_leave";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                leaves.add(new Leave(
                    rs.getString("leave_id"),
                    rs.getString("employee_id"),
                    rs.getString("leave_type"),
                    rs.getDate("start_date").toLocalDate(),
                    rs.getDate("end_date").toLocalDate(),
                    rs.getDouble("total_days"),
                    rs.getString("request_id")
                ));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching all leaves:");
            e.printStackTrace();
        }

        return leaves;
    }
}
