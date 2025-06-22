/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.SQLService;

import Class.TAT.Leave;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaveSQLService {
    private final Connection conn;

    public LeaveSQLService(Connection conn) {
        this.conn = conn;
    }

    // 🔹 CREATE
    public boolean addLeave(Leave leave) {
        String sql = "INSERT INTO employee_leave " +
                     "(leave_id, employee_id, leave_type, start_date, end_date, total_days, request_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, leave.getID());
            pstmt.setString(2, leave.getEmployeeID());
            pstmt.setString(3, leave.getLeaveType());
            pstmt.setDate(4, Date.valueOf(leave.getStartDate()));
            pstmt.setDate(5, Date.valueOf(leave.getEndDate()));
            pstmt.setDouble(6, leave.getTotalDays());
            pstmt.setString(7, leave.getRequestID());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error adding leave: " + e.getMessage());
            return false;
        }
    }

    // 🔹 READ ALL
    public List<Leave> getAllLeaves() {
        List<Leave> list = new ArrayList<>();
        String sql = "SELECT * FROM employee_leave";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Leave leave = new Leave(new String[] {
                    rs.getString("leave_id"),
                    rs.getString("employee_id"),
                    rs.getString("leave_type"),
                    rs.getDate("start_date").toString(),
                    rs.getDate("end_date").toString(),
                    rs.getString("total_days"),
                    rs.getString("request_id")
                });
                list.add(leave);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error fetching leaves: " + e.getMessage());
        }

        return list;
    }

    // 🔹 READ BY ID
    public Leave getLeaveById(String id) {
        String sql = "SELECT * FROM employee_leave WHERE leave_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Leave(new String[] {
                    rs.getString("leave_id"),
                    rs.getString("employee_id"),
                    rs.getString("leave_type"),
                    rs.getDate("start_date").toString(),
                    rs.getDate("end_date").toString(),
                    rs.getString("total_days"),
                    rs.getString("request_id")
                });
            }

        } catch (SQLException e) {
            System.err.println("❌ Error finding leave: " + e.getMessage());
        }

        return null;
    }

    // 🔹 UPDATE
    public boolean updateLeave(Leave leave) {
        String sql = "UPDATE employee_leave " +
                     "SET employee_id = ?, leave_type = ?, start_date = ?, end_date = ?, total_days = ?, request_id = ? " +
                     "WHERE leave_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, leave.getEmployeeID());
            pstmt.setString(2, leave.getLeaveType());
            pstmt.setDate(3, Date.valueOf(leave.getStartDate()));
            pstmt.setDate(4, Date.valueOf(leave.getEndDate()));
            pstmt.setDouble(5, leave.getTotalDays());
            pstmt.setString(6, leave.getRequestID());
            pstmt.setString(7, leave.getID());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error updating leave: " + e.getMessage());
            return false;
        }
    }

    // 🔹 DELETE
    public boolean deleteLeave(String id) {
        String sql = "DELETE FROM employee_leave WHERE leave_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error deleting leave: " + e.getMessage());
            return false;
        }
    }
}
