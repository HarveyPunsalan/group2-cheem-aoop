/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.TAT;

import Class.EMS.Employee;
import Class.PPS.PayPeriod;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import database.connection.DBConnection;

public class AttendanceService {
    private final Connection conn;

    public AttendanceService(Connection conn) {
        this.conn = conn;
    }

    // ===================== DAILY TIME RECORD (DTR) =====================

    public boolean addDailyAttendance(DailyAttendance attendance) {
        String sql = "INSERT INTO daily_time_record " +
                "(dtr_id, employee_id, date, login, logout, late_hours, overtime_hours, worked_hours) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, attendance.getDtrID());
            pstmt.setString(2, attendance.getEmployeeID());
            pstmt.setDate(3, java.sql.Date.valueOf(attendance.getDate()));

            if (attendance.getLogin() != null) {
                pstmt.setTime(4, Time.valueOf(attendance.getLogin()));
            } else {
                pstmt.setNull(4, Types.TIME);
            }

            if (attendance.getLogout() != null) {
                pstmt.setTime(5, Time.valueOf(attendance.getLogout()));
            } else {
                pstmt.setNull(5, Types.TIME);
            }

            pstmt.setObject(6, attendance.getLateHours(), Types.DOUBLE);
            pstmt.setObject(7, attendance.getOvertimeHours(), Types.DOUBLE);
            pstmt.setObject(8, attendance.getWorkedHours(), Types.DOUBLE);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<DailyAttendance> getAllDailyAttendances() {
        List<DailyAttendance> list = new ArrayList<>();
        String sql = "SELECT * FROM daily_time_record";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                DailyAttendance attendance = new DailyAttendance(
                        rs.getString("dtr_id"),
                        rs.getString("employee_id"),
                        rs.getDate("date").toLocalDate(),
                        rs.getTime("login") != null ? rs.getTime("login").toLocalTime() : null,
                        rs.getTime("logout") != null ? rs.getTime("logout").toLocalTime() : null,
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

    public boolean updateDailyAttendance(DailyAttendance attendance) {
        String sql = "UPDATE daily_time_record SET employee_id=?, date=?, login=?, logout=?, " +
                "late_hours=?, overtime_hours=?, worked_hours=? WHERE dtr_id=?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, attendance.getEmployeeID());
            pstmt.setDate(2, java.sql.Date.valueOf(attendance.getDate()));

            if (attendance.getLogin() != null) {
                pstmt.setTime(3, Time.valueOf(attendance.getLogin()));
            } else {
                pstmt.setNull(3, Types.TIME);
            }

            if (attendance.getLogout() != null) {
                pstmt.setTime(4, Time.valueOf(attendance.getLogout()));
            } else {
                pstmt.setNull(4, Types.TIME);
            }

            pstmt.setObject(5, attendance.getLateHours(), Types.DOUBLE);
            pstmt.setObject(6, attendance.getOvertimeHours(), Types.DOUBLE);
            pstmt.setObject(7, attendance.getWorkedHours(), Types.DOUBLE);
            pstmt.setString(8, attendance.getDtrID());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteDailyAttendance(String dtrID) {
        String sql = "DELETE FROM daily_time_record WHERE dtr_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dtrID);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ===================== ATTENDANCE PROCESSING =====================

    public boolean addAttendanceProcessing(AttendanceProcessing attendance) {
        String sql = "INSERT INTO attendance_processing " +
                "(employee_id, pay_period_id, total_late_hours, total_approved_overtime_hours, total_worked_hours, " +
                "total_paid_leave_hours, payable_hours, status, approved_by) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, attendance.getEmployeeID());
            pstmt.setInt(2, attendance.getPayPeriodID());
            pstmt.setDouble(3, attendance.getTotalLateHours());
            pstmt.setDouble(4, attendance.getTotalApprovedOvertimeHours());
            pstmt.setDouble(5, attendance.getTotalWorkedHours());
            pstmt.setDouble(6, attendance.getTotalPaidLeaveHours());
            pstmt.setDouble(7, attendance.getPayableHours());
            pstmt.setString(8, attendance.getStatus());
            pstmt.setInt(9, attendance.getApprovedBy());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<AttendanceProcessing> getAllAttendanceProcessing() {
        List<AttendanceProcessing> list = new ArrayList<>();
        String sql = "SELECT * FROM attendance_processing";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                AttendanceProcessing attendance = new AttendanceProcessing(
                        rs.getInt("attendance_processing_id"),
                        rs.getInt("employee_id"),
                        rs.getInt("pay_period_id"),
                        rs.getDouble("total_late_hours"),
                        rs.getDouble("total_approved_overtime_hours"),
                        rs.getDouble("total_worked_hours"),
                        rs.getDouble("total_paid_leave_hours"),
                        rs.getDouble("payable_hours"),
                        rs.getString("status"),
                        rs.getInt("approved_by")
                );
                list.add(attendance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public AttendanceProcessing getAttendanceProcessingById(int id) {
        String sql = "SELECT * FROM attendance_processing WHERE attendance_processing_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new AttendanceProcessing(
                            rs.getInt("attendance_processing_id"),
                            rs.getInt("employee_id"),
                            rs.getInt("pay_period_id"),
                            rs.getDouble("total_late_hours"),
                            rs.getDouble("total_approved_overtime_hours"),
                            rs.getDouble("total_worked_hours"),
                            rs.getDouble("total_paid_leave_hours"),
                            rs.getDouble("payable_hours"),
                            rs.getString("status"),
                            rs.getInt("approved_by")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateAttendanceProcessing(AttendanceProcessing attendance) {
        String sql = "UPDATE attendance_processing SET " +
                "employee_id = ?, pay_period_id = ?, total_late_hours = ?, " +
                "total_approved_overtime_hours = ?, total_worked_hours = ?, " +
                "total_paid_leave_hours = ?, payable_hours = ?, status = ?, approved_by = ? " +
                "WHERE attendance_processing_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, attendance.getEmployeeID());
            pstmt.setInt(2, attendance.getPayPeriodID());
            pstmt.setDouble(3, attendance.getTotalLateHours());
            pstmt.setDouble(4, attendance.getTotalApprovedOvertimeHours());
            pstmt.setDouble(5, attendance.getTotalWorkedHours());
            pstmt.setDouble(6, attendance.getTotalPaidLeaveHours());
            pstmt.setDouble(7, attendance.getPayableHours());
            pstmt.setString(8, attendance.getStatus());
            pstmt.setInt(9, attendance.getApprovedBy());
            pstmt.setInt(10, attendance.getID());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAttendanceProcessing(int id) {
        String sql = "DELETE FROM attendance_processing WHERE attendance_processing_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ===================== HELPERS / UI SUPPORT =====================

    public List<DailyAttendance> getFilteredDailyAttendance(Employee employee, PayPeriod payPeriod) {
        List<DailyAttendance> filtered = new ArrayList<>();
        String sql = "SELECT * FROM daily_time_record WHERE employee_id = ? AND date BETWEEN ? AND ? ORDER BY date ASC";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, employee.getEmployeeID());
            pstmt.setDate(2, java.sql.Date.valueOf(payPeriod.getStartDate()));
            pstmt.setDate(3, java.sql.Date.valueOf(payPeriod.getEndDate()));

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    DailyAttendance da = new DailyAttendance(
                        rs.getString("dtr_id"),
                        rs.getString("employee_id"),
                        rs.getDate("date").toLocalDate(),
                        rs.getTime("login") != null ? rs.getTime("login").toLocalTime() : null,
                        rs.getTime("logout") != null ? rs.getTime("logout").toLocalTime() : null,
                        rs.getDouble("late_hours"),
                        rs.getDouble("overtime_hours"),
                        rs.getDouble("worked_hours")
                    );
                    filtered.add(da);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filtered;
    }

    public DefaultTableModel getAttendanceTableModel(Employee employee, PayPeriod payPeriod) {
        String[] columns = {"DTR ID", "Date", "Login", "Logout", "Late (hrs)", "Overtime (hrs)", "Worked (hrs)"};
        List<DailyAttendance> records = getFilteredDailyAttendance(employee, payPeriod);
        Object[][] data = new Object[records.size()][columns.length];

        for (int i = 0; i < records.size(); i++) {
            DailyAttendance da = records.get(i);
            data[i][0] = da.getDtrID();
            data[i][1] = da.getDate();
            data[i][2] = da.getLogin();
            data[i][3] = da.getLogout();
            data[i][4] = da.getLateHours();
            data[i][5] = da.getOvertimeHours();
            data[i][6] = da.getWorkedHours();
        }

        return new DefaultTableModel(data, columns);
    }

    public DefaultTableModel getProcessedAttendanceTableModel(PayPeriod payPeriod) {
        String[] columns = {"Employee ID", "Total Late Hours", "Total Approved Overtime Hours", "Total Worked Hours", "Status"};
        List<AttendanceProcessing> records = new ArrayList<>();
        String sql = "SELECT * FROM attendance_processing WHERE pay_period_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, payPeriod.getPayPeriodID());  // FIXED here

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    AttendanceProcessing ap = new AttendanceProcessing(
                        rs.getInt("attendance_processing_id"),
                        rs.getInt("employee_id"),
                        rs.getInt("pay_period_id"),
                        rs.getDouble("total_late_hours"),
                        rs.getDouble("total_approved_overtime_hours"),
                        rs.getDouble("total_worked_hours"),
                        rs.getDouble("total_paid_leave_hours"),
                        rs.getDouble("payable_hours"),
                        rs.getString("status"),
                        rs.getInt("approved_by")
                    );
                    records.add(ap);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Object[][] data = new Object[records.size()][columns.length];

        for (int i = 0; i < records.size(); i++) {
            AttendanceProcessing ap = records.get(i);
            data[i][0] = ap.getEmployeeID();
            data[i][1] = ap.getTotalLateHours();
            data[i][2] = ap.getTotalApprovedOvertimeHours();
            data[i][3] = ap.getTotalWorkedHours();
            data[i][4] = ap.getStatus();
        }

        return new DefaultTableModel(data, columns);
    }

    public DefaultComboBoxModel<String> getOvertimeDatesComboBoxModel(List<DailyAttendance> attendanceList) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (DailyAttendance da : attendanceList) {
            if (da.getOvertimeHours() > 0) {
                model.addElement(da.getDate().toString());
            }
        }
        return model;
    }

    public DailyAttendance getEmployeeDailyAttendance(Employee employee, LocalDate date) {
        String sql = "SELECT * FROM daily_time_record WHERE employee_id = ? AND date = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, employee.getEmployeeID());
            pstmt.setDate(2, java.sql.Date.valueOf(date));

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new DailyAttendance(
                        rs.getString("dtr_id"),
                        rs.getString("employee_id"),
                        rs.getDate("date").toLocalDate(),
                        rs.getTime("login") != null ? rs.getTime("login").toLocalTime() : null,
                        rs.getTime("logout") != null ? rs.getTime("logout").toLocalTime() : null,
                        rs.getDouble("late_hours"),
                        rs.getDouble("overtime_hours"),
                        rs.getDouble("worked_hours")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
