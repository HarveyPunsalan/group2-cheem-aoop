/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.attendancemanagement.service;

import com.motorph.attendancemanagement.model.Request;
import com.motorph.database.connection.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestService {

    private Connection connection;

    public RequestService() {
        connection = DatabaseService.connectToMotorPH(); // Ensure this returns a working connection
    }

    // CREATE
    public boolean addRequest(Request request) {
        String sql = "INSERT INTO request (employee_id, request_date, reason, request_status, created_at, processed_by, processed_date, remarks) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, request.getEmployeeId());
            stmt.setDate(2, request.getRequestDate());
            stmt.setString(3, request.getReason());
            stmt.setString(4, request.getRequestStatus());
            stmt.setTimestamp(5, request.getCreatedAt());

            // Optional: processed_by
            if (request.getProcessedBy() > 0) {
                stmt.setInt(6, request.getProcessedBy());
            } else {
                stmt.setNull(6, Types.INTEGER);
            }

            // Optional: processed_date
            if (request.getProcessedDate() != null) {
                stmt.setDate(7, request.getProcessedDate());
            } else {
                stmt.setNull(7, Types.DATE);
            }

            // Optional: remarks
            if (request.getRemarks() != null) {
                stmt.setString(8, request.getRemarks());
            } else {
                stmt.setNull(8, Types.VARCHAR);
            }

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // READ (GET BY ID)
    public Request getRequestById(int id) {
        String sql = "SELECT * FROM request WHERE request_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractRequestFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // READ (GET ALL)
    public List<Request> getAllRequests() {
        List<Request> requestList = new ArrayList<>();
        String sql = "SELECT * FROM request";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                requestList.add(extractRequestFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestList;
    }

    // UPDATE
    public boolean updateRequest(Request request) {
        String sql = "UPDATE request SET employee_id = ?, request_date = ?, reason = ?, request_status = ?, " +
                     "created_at = ?, processed_by = ?, processed_date = ?, remarks = ? WHERE request_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, request.getEmployeeId());
            stmt.setDate(2, request.getRequestDate());
            stmt.setString(3, request.getReason());
            stmt.setString(4, request.getRequestStatus());
            stmt.setTimestamp(5, request.getCreatedAt());

            // Optional: processed_by
            if (request.getProcessedBy() > 0) {
                stmt.setInt(6, request.getProcessedBy());
            } else {
                stmt.setNull(6, Types.INTEGER);
            }

            // Optional: processed_date
            if (request.getProcessedDate() != null) {
                stmt.setDate(7, request.getProcessedDate());
            } else {
                stmt.setNull(7, Types.DATE);
            }

            // Optional: remarks
            if (request.getRemarks() != null) {
                stmt.setString(8, request.getRemarks());
            } else {
                stmt.setNull(8, Types.VARCHAR);
            }

            stmt.setInt(9, request.getRequestId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean deleteRequest(int requestId) {
        String sql = "DELETE FROM request WHERE request_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, requestId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Helper to extract a Request object from a ResultSet row
    private Request extractRequestFromResultSet(ResultSet rs) throws SQLException {
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
