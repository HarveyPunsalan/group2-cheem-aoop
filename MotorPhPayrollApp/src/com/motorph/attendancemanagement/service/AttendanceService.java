/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.attendancemanagement.service;

import com.motorph.attendancemanagement.model.DailyAttendance;
import com.motorph.employeemanagement.model.Employee;
import com.motorph.database.connection.DatabaseService;
import com.motorph.payrollprocessing.model.payroll.PayPeriod;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AttendanceService {

    // Retrieve attendance records for an employee
    public List<DailyAttendance> getAllAttendanceRecords(Employee employee) {
        List<DailyAttendance> attendanceList = new ArrayList<>();
        String sql = "SELECT attendance_id, employee_id, date, time_in, time_out, late_hours, overtime_hours, worked_hours " +
                     "FROM attendance WHERE employee_id = ?";

        try (Connection conn = DatabaseService.connectToMotorPH();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employee.getEmployeeId());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    DailyAttendance attendance = mapResultSetToAttendance(rs, employee);
                    attendanceList.add(attendance);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendanceList;
    }
    
    public List<DailyAttendance> getAttendanceRecordsByPayPeriod(Employee employee, PayPeriod payPeriod) {
        List<DailyAttendance> attendanceList = new ArrayList<>();
        String sql = "CALL get_attendance_by_payperiod(?, ?)";

        try (Connection conn = DatabaseService.connectToMotorPH();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employee.getEmployeeId());
            stmt.setInt(2, payPeriod.getPayPeriodId());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    DailyAttendance attendance = mapResultSetToAttendance(rs, employee);
                    attendanceList.add(attendance);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendanceList;
    }

    // Create a new attendance record
    public boolean createAttendance(DailyAttendance attendance) {
        String sql = "INSERT INTO attendance (attendance_id, employee_id, date, time_in, time_out, late_hours, overtime_hours, worked_hours) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseService.connectToMotorPH();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, attendance.getAttendanceId());
            stmt.setInt(2, attendance.getEmployee().getEmployeeId());
            stmt.setDate(3, Date.valueOf(attendance.getDate()));
            stmt.setTime(4, Time.valueOf(attendance.getTimeIn()));
            stmt.setTime(5, Time.valueOf(attendance.getTimeOut()));
            stmt.setDouble(6, attendance.getHoursLate());
            stmt.setDouble(7, attendance.getHoursOvertime());
            stmt.setDouble(8, attendance.getHoursWorked());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update an existing attendance record
    public boolean updateAttendance(DailyAttendance attendance) {
        String sql = "UPDATE attendance SET date = ?, time_in = ?, time_out = ?, late_hours = ?, overtime_hours = ?, worked_hours = ? " +
                     "WHERE attendance_id = ?";

        try (Connection conn = DatabaseService.connectToMotorPH();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(attendance.getDate()));
            stmt.setTime(2, Time.valueOf(attendance.getTimeIn()));
            stmt.setTime(3, Time.valueOf(attendance.getTimeOut()));
            stmt.setDouble(4, attendance.getHoursLate());
            stmt.setDouble(5, attendance.getHoursOvertime());
            stmt.setDouble(6, attendance.getHoursWorked());
            stmt.setString(7, attendance.getAttendanceId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete attendance record by ID
    public boolean deleteAttendance(String attendanceId) {
        String sql = "DELETE FROM attendance WHERE attendance_id = ?";

        try (Connection conn = DatabaseService.connectToMotorPH();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, attendanceId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get a single attendance record by ID
    public DailyAttendance getAttendanceById(String attendanceId, Employee employee) {
        String sql = "SELECT attendance_id, employee_id, date, time_in, time_out, late_hours, overtime_hours, worked_hours " +
                     "FROM attendance WHERE attendance_id = ?";

        try (Connection conn = DatabaseService.connectToMotorPH();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, attendanceId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToAttendance(rs, employee);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Helper: maps ResultSet row to DailyAttendance object
    private DailyAttendance mapResultSetToAttendance(ResultSet rs, Employee employee) throws SQLException {
        String attendanceId = rs.getString("dtr_id");
        LocalDate date = rs.getDate("date").toLocalDate();
        LocalTime timeIn = rs.getTime("login").toLocalTime();
        LocalTime timeOut = rs.getTime("logout").toLocalTime();
        double lateHours = rs.getDouble("late_hours");
        double overtimeHours = rs.getDouble("overtime_hours");
        double workedHours = rs.getDouble("worked_hours");

        return new DailyAttendance(attendanceId, employee, date, timeIn, timeOut, lateHours, overtimeHours, workedHours);
    }
}
