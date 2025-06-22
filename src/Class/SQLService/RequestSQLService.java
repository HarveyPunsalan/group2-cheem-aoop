/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.SQLService;

import Class.TAT.Request;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestSQLService {
    private final Connection conn;

    public RequestSQLService(Connection conn) {
        this.conn = conn;
    }

    // 🔹 CREATE
    public boolean insertRequest(Request req) {
        String sql = "INSERT INTO request (employee_id, request_date, reason, request_status, created_at, processed_by, processed_date, remarks) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, req.getEmployeeId());
            pstmt.setDate(2, req.getRequestDate());
            pstmt.setString(3, req.getReason());
            pstmt.setString(4, req.getRequestStatus());
            pstmt.setTimestamp(5, req.getCreatedAt());
            pstmt.setInt(6, req.getProcessedBy());
            pstmt.setDate(7, req.getProcessedDate());
            pstmt.setString(8, req.getRemarks());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error inserting request: " + e.getMessage());
            return false;
        }
    }

    // 🔹 READ (all)
    public List<Request> getAllRequests() {
        List<Request> list = new ArrayList<>();
        String sql = "SELECT * FROM request";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(mapResultSetToRequest(rs));
            }

        } catch (SQLException e) {
            System.err.println("❌ Error fetching requests: " + e.getMessage());
        }

        return list;
    }

    // 🔹 READ (by ID)
    public Request getRequestById(int requestId) {
        String sql = "SELECT * FROM request WHERE request_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, requestId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) return mapResultSetToRequest(rs);
        } catch (SQLException e) {
            System.err.println("❌ Error fetching request by ID: " + e.getMessage());
        }

        return null;
    }

    // 🔹 READ (by employee)
    public List<Request> getRequestsByEmployee(int employeeId) {
        List<Request> list = new ArrayList<>();
        String sql = "SELECT * FROM request WHERE employee_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employeeId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(mapResultSetToRequest(rs));
            }

        } catch (SQLException e) {
            System.err.println("❌ Error fetching requests by employee: " + e.getMessage());
        }

        return list;
    }

    // 🔹 UPDATE
    public boolean updateRequest(Request req) {
        String sql = "UPDATE request SET " +
                     "employee_id = ?, request_date = ?, reason = ?, request_status = ?, " +
                     "created_at = ?, processed_by = ?, processed_date = ?, remarks = ? " +
                     "WHERE request_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, req.getEmployeeId());
            pstmt.setDate(2, req.getRequestDate());
            pstmt.setString(3, req.getReason());
            pstmt.setString(4, req.getRequestStatus());
            pstmt.setTimestamp(5, req.getCreatedAt());
            pstmt.setInt(6, req.getProcessedBy());
            pstmt.setDate(7, req.getProcessedDate());
            pstmt.setString(8, req.getRemarks());
            pstmt.setInt(9, req.getRequestId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error updating request: " + e.getMessage());
            return false;
        }
    }

    // 🔹 DELETE
    public boolean deleteRequest(int requestId) {
        String sql = "DELETE FROM request WHERE request_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, requestId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Error deleting request: " + e.getMessage());
            return false;
        }
    }

    // 🔸 Mapping helper
    private Request mapResultSetToRequest(ResultSet rs) throws SQLException {
        return new Request(
            rs.getInt("request_id"),
            rs.getInt("employee_id"),
            rs.getDate("request_date"),
            rs.getString("reason"),
            rs.getString("request_status"),
            rs.getTimestamp("created_at"),
            rs.getInt("processed_by"),
            rs.getDate("processed_date"),
            rs.getString("remarks")
        );
    }
}

