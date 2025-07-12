/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.attendancemanagement.service;

import com.motorph.attendancemanagement.model.Overtime;
import com.motorph.attendancemanagement.model.Request;
import com.motorph.attendancemanagement.model.Leave;
import com.motorph.common.util.CollectionUtils;
import com.motorph.employeemanagement.model.Employee;
import com.motorph.employeemanagement.service.csvversion.EmployeeService;
import com.motorph.database.connection.DatabaseService;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.*;

public class RequestService {

    private final List<Request> requestList;
    private final List<Leave> leaveList;
    private final List<Overtime> overtimeList;

    private final Map<String, Request> requestMapByRequestID;
    private final Map<String, Leave> leaveMapByLeaveID;
    private final Map<String, Overtime> overtimeMapByOvertimeID;

    public RequestService() {
        this.requestList = fetchAllRequests();
        this.leaveList = fetchAllLeaveRequests();
        this.overtimeList = fetchAllOvertimeRequests();

        this.requestMapByRequestID = CollectionUtils.listToMap(requestList, Request::getID);
        this.leaveMapByLeaveID = CollectionUtils.listToMap(leaveList, Leave::getID);
        this.overtimeMapByOvertimeID = CollectionUtils.listToMap(overtimeList, Overtime::getID);
    }

    private Connection getConnection() throws SQLException {
        return DatabaseService.connectToMotorPH();
    }

    private List<Request> fetchAllRequests() {
        List<Request> requests = new ArrayList<>();
        String query = "SELECT * FROM request";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                requests.add(new Request(new String[]{
                        rs.getString("request_id"),
                        rs.getString("request_type_id"),
                        rs.getString("employee_id"),
                        rs.getString("request_date"),
                        rs.getString("reason"),
                        rs.getString("request_status"),
                        rs.getString("processed_by"),
                        rs.getString("processed_date"),
                        rs.getString("remarks"),
                        rs.getString("created_at")
                }));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return requests;
    }

    private List<Leave> fetchAllLeaveRequests() {
        List<Leave> leaves = new ArrayList<>();
        String query = "SELECT * FROM employee_leave";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                leaves.add(new Leave(new String[]{
                        rs.getString("leave_id"),
                        rs.getString("employee_id"),
                        rs.getString("leave_type"),
                        rs.getString("start_date"),
                        rs.getString("end_date"),
                        rs.getString("total_days"),
                        rs.getString("request_id")
                }));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return leaves;
    }

    private List<Overtime> fetchAllOvertimeRequests() {
        List<Overtime> overtimes = new ArrayList<>();
        String query = "SELECT * FROM overtime";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                overtimes.add(new Overtime(new String[]{
                        rs.getString("overtime_id"),
                        rs.getString("employee_id"),
                        rs.getString("date"),
                        rs.getString("start_time"),
                        rs.getString("end_time"),
                        rs.getString("total_hours"),
                        rs.getString("payable_hours"),
                        rs.getString("is_approved"),
                        rs.getString("request_id"),
                        rs.getString("dtr_id")
                }));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return overtimes;
    }

    public Leave getLeaveRecord(String leaveID) {
        return leaveMapByLeaveID.get(leaveID);
    }

    public Overtime getOvertimeRecord(String overtimeID) {
        return overtimeMapByOvertimeID.get(overtimeID);
    }

    public Request getRequestRecord(String requestID) {
        return requestMapByRequestID.get(requestID);
    }

    public DefaultComboBoxModel<String> getStatusComboBoxModel() {
        return new DefaultComboBoxModel<>(new String[]{"All", "PENDING", "APPROVED", "REJECTED"});
    }

    public DefaultTableModel getLeaveRequestTableModel() {
        String[] columnNames = {
                "Request ID", "Request Date", "Employee Name",
                "Start Date", "End Date", "Leave Type", "Total Days", "Notes", "Status"
        };

        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        EmployeeService employeeService = new EmployeeService();

        for (Leave leave : leaveList) {
            Employee employee = employeeService.getEmployeeInformation(leave.getEmployeeID());
            Request request = getRequestRecord(String.valueOf(leave.getRequestId()));

            String employeeName = (employee != null)
                    ? employee.getFirstName() + " " + employee.getLastName()
                    : "Terminated Employee";

            model.addRow(new Object[]{
                    leave.getID(),
                    request != null ? request.getRequestDate() : "",
                    employeeName,
                    leave.getStartDate(),
                    leave.getEndDate(),
                    leave.getLeaveType(),
                    leave.getTotalDays(),
                    request != null ? request.getReason() : "",
                    request != null ? request.getStatus() : ""
            });
        }

        return model;
    }

    public DefaultTableModel getOvertimeRequestTableModel() {
        String[] columnNames = {
                "Request ID", "Request Date", "Employee Name",
                "Start Time", "End Time", "Total Hours", "Notes", "Status"
        };

        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        EmployeeService employeeService = new EmployeeService();

        for (Overtime overtime : overtimeList) {
            Employee employee = employeeService.getEmployeeInformation(overtime.getEmployeeID());
            Request request = getRequestRecord(String.valueOf(overtime.getRequestId()));

            String employeeName = (employee != null)
                    ? employee.getFirstName() + " " + employee.getLastName()
                    : "Terminated Employee";

            model.addRow(new Object[]{
                    overtime.getID(),
                    request != null ? request.getRequestDate() : "",
                    employeeName,
                    overtime.getStartTime(),
                    overtime.getEndTime(),
                    overtime.getTotalHours(),
                    request != null ? request.getReason() : "",
                    request != null ? request.getStatus() : ""
            });
        }

        return model;
    }
}
