/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.SQLService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OvertimeSQLService {
    private final Connection conn;

    public OvertimeSQLService(Connection conn) {
        this.conn = conn;
    }

    // CREATE: Save a new overtime record
    public boolean saveOvertime(String requestID, String employeeID, String dtrID) {
        String sql = "INSERT INTO overtime (request_id, employee_id, dtr_id) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, requestID);
            pstmt.setString(2, employeeID);
            pstmt.setString(3, dtrID);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error saving overtime: " + e.getMessage());
            return false;
        }
    }

    // READ: Get all overtime records
    public List<String[]> getAllOvertimeRecords() {
        List<String[]> list = new ArrayList<>();
        String sql = "SELECT * FROM overtime";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new String[]{
                    rs.getString("overtime_id"),
                    rs.getString("request_id"),
                    rs.getString("employee_id"),
                    rs.getString("dtr_id")
                });
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching overtime records: " + e.getMessage());
        }
        return list;
    }

    // DELETE: Delete overtime by ID (accepts String to match the service layer)
    public boolean deleteOvertimeById(String overtimeId) {
        String sql = "DELETE FROM overtime WHERE overtime_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, overtimeId); // changed from int to String
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error deleting overtime: " + e.getMessage());
            return false;
        }
    }

    // READ: Get overtime by request ID
    public List<String[]> getByRequestID(String requestID) {
        List<String[]> list = new ArrayList<>();
        String sql = "SELECT * FROM overtime WHERE request_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, requestID);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new String[]{
                        rs.getString("overtime_id"),
                        rs.getString("request_id"),
                        rs.getString("employee_id"),
                        rs.getString("dtr_id")
                    });
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching by request ID: " + e.getMessage());
        }
        return list;
    }

    // ✅ NEW METHOD: Get overtime by overtime ID
    public List<String[]> getByOvertimeID(String overtimeId) {
        List<String[]> list = new ArrayList<>();
        String sql = "SELECT * FROM overtime WHERE overtime_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, overtimeId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new String[]{
                        rs.getString("overtime_id"),
                        rs.getString("request_id"),
                        rs.getString("employee_id"),
                        rs.getString("dtr_id")
                    });
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error fetching by overtime ID: " + e.getMessage());
        }
        return list;
    }
}
