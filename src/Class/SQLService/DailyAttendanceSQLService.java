/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.SQLService;

import Class.TAT.DailyAttendance;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DailyAttendanceSQLService {
    private final Connection conn;

    public DailyAttendanceSQLService(Connection conn) {
        this.conn = conn;
    }

    // 🔹 CREATE
    public boolean addAttendance(DailyAttendance attendance) {
        String sql = "INSERT INTO daily_time_record (dtr_id, employee_id, date, login, logout, late_hours, overtime_hours, worked_hours) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, attendance.getAttendanceID());
            pstmt.setString(2, attendance.getEmployeeID());
            pstmt.setDate(3, Date.valueOf(attendance.getDate()));
            pstmt.setTime(4, Time.valueOf(attendance.getTimeIn()));
            pstmt.setTime(5, Time.valueOf(attendance.getTimeOut()));
            pstmt.setDouble(6, attendance.getHoursLate());
            pstmt.setDouble(7, attendance.getHoursOvertime());
            pstmt.setDouble(8, attendance.getHoursWorked());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 READ ALL
    public List<DailyAttendance> getAllAttendances() {
        List<DailyAttendance> list = new ArrayList<>();
        String sql = "SELECT * FROM daily_time_record";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                DailyAttendance attendance = new DailyAttendance(
                    rs.getString("dtr_id"),
                    rs.getString("employee_id"),
                    rs.getDate("date").toLocalDate(),
                    rs.getTime("login").toLocalTime(),
                    rs.getTime("logout").toLocalTime(),
                    rs.getDouble("late_hours"),
                    rs.getDouble("overtime_hours"),
                    rs.getDouble("worked_hours")
                );
                list.add(attendance);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // 🔹 READ BY ID
    public DailyAttendance getAttendanceById(String id) {
        String sql = "SELECT * FROM daily_time_record WHERE dtr_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new DailyAttendance(
                    rs.getString("dtr_id"),
                    rs.getString("employee_id"),
                    rs.getDate("date").toLocalDate(),
                    rs.getTime("login").toLocalTime(),
                    rs.getTime("logout").toLocalTime(),
                    rs.getDouble("late_hours"),
                    rs.getDouble("overtime_hours"),
                    rs.getDouble("worked_hours")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // 🔹 UPDATE
    public boolean updateAttendance(DailyAttendance attendance) {
        String sql = "UPDATE daily_time_record SET employee_id = ?, date = ?, login = ?, logout = ?, " +
                     "late_hours = ?, overtime_hours = ?, worked_hours = ? WHERE dtr_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, attendance.getEmployeeID());
            pstmt.setDate(2, Date.valueOf(attendance.getDate()));
            pstmt.setTime(3, Time.valueOf(attendance.getTimeIn()));
            pstmt.setTime(4, Time.valueOf(attendance.getTimeOut()));
            pstmt.setDouble(5, attendance.getHoursLate());
            pstmt.setDouble(6, attendance.getHoursOvertime());
            pstmt.setDouble(7, attendance.getHoursWorked());
            pstmt.setString(8, attendance.getAttendanceID());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 DELETE
    public boolean deleteAttendance(String id) {
        String sql = "DELETE FROM daily_time_record WHERE dtr_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

