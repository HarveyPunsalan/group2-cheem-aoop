/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.TAT;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.util.List;

import Class.SQLService.RequestSQLService;
import Class.EMS.Employee;
import Class.EMS.EmployeeService;
import Class.TAT.Request;
import Class.TAT.Leave;
import Class.TAT.Overtime;

public class RequestService {

    private final RequestSQLService requestDAO;
    private final LeaveService leaveService;
    private final OvertimeService overtimeService;

    public RequestService(Connection conn) {
        this.requestDAO = new RequestSQLService(conn);
        this.leaveService = new LeaveService(conn);
        this.overtimeService = new OvertimeService(conn);
    }

    // ========== REQUEST ==========
    public Request getRequestRecord(int requestId) {
        return requestDAO.getRequestById(requestId);
    }

    public void saveRequestRecord(Request request) {
        if (request.getRequestId() == 0) {
            requestDAO.insertRequest(request);
        } else {
            requestDAO.updateRequest(request);
        }
    }

    public void deleteRequest(int requestId) {
        requestDAO.deleteRequest(requestId);
    }

    // ========== LEAVE ==========
    public Leave getLeaveRecord(String leaveID) {
        return leaveService.getLeaveByID(leaveID);
    }

    public void saveLeaveRecord(Leave leave) {
        leaveService.saveLeave(leave);
    }

    // ========== OVERTIME ==========
    public Overtime getOvertimeRecord(String overtimeID) {
        return overtimeService.getOvertimeByID(overtimeID);
    }

    public void saveOvertimeRecord(Overtime overtime) {
        overtimeService.saveOvertime(overtime);
    }

    // ========== UI SUPPORT ==========
    public DefaultComboBoxModel<String> getStatusComboBoxModel() {
        String[] statusArray = {"All", "PENDING", "APPROVED", "REJECTED"};
        return new DefaultComboBoxModel<>(statusArray);
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
        List<Leave> leaves = leaveService.getAllLeaves();

        for (Leave leave : leaves) {
            Request request = requestDAO.getRequestById(Integer.parseInt(leave.getRequestId()));
            if (request == null) continue;

            Employee employee = employeeService.getEmployeeInformation(leave.getEmployeeID());
            String employeeName = (employee != null)
                ? employee.getFirstName() + " " + employee.getLastName()
                : "Terminated Employee";

            model.addRow(new Object[] {
                request.getRequestId(),
                request.getRequestDate(),
                employeeName,
                leave.getStartDate(),
                leave.getEndDate(),
                leave.getLeaveType(),
                leave.getTotalDays(),
                request.getReason(),
                request.getRequestStatus()
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
        List<Overtime> overtimes = overtimeService.getAllOvertimes();

        for (Overtime overtime : overtimes) {
            Request request = requestDAO.getRequestById(Integer.parseInt(overtime.getRequestId()));
            if (request == null) continue;

            Employee employee = employeeService.getEmployeeInformation(overtime.getEmployeeID());
            String employeeName = (employee != null)
                ? employee.getFirstName() + " " + employee.getLastName()
                : "Terminated Employee";

            model.addRow(new Object[] {
                request.getRequestId(),
                request.getRequestDate(),
                employeeName,
                overtime.getStartTime(),
                overtime.getEndTime(),
                overtime.getTotalHours(),
                request.getReason(),
                request.getRequestStatus()
            });
        }

        return model;
    }
}
