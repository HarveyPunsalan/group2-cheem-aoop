/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.TAT;

public class AttendanceProcessing {
    private int attendanceProcessingID;
    private int employeeID;
    private int payPeriodID;
    private double totalLateHours;
    private double totalApprovedOvertimeHours;
    private double totalWorkedHours;
    private double totalPaidLeaveHours;
    private double payableHours;
    private String status;
    private int approvedBy;

    // Constructor
    public AttendanceProcessing(int attendanceProcessingID, int employeeID, int payPeriodID,
                                double totalLateHours, double totalApprovedOvertimeHours, double totalWorkedHours,
                                double totalPaidLeaveHours, double payableHours, String status, int approvedBy) {
        this.attendanceProcessingID = attendanceProcessingID;
        this.employeeID = employeeID;
        this.payPeriodID = payPeriodID;
        this.totalLateHours = totalLateHours;
        this.totalApprovedOvertimeHours = totalApprovedOvertimeHours;
        this.totalWorkedHours = totalWorkedHours;
        this.totalPaidLeaveHours = totalPaidLeaveHours;
        this.payableHours = payableHours;
        this.status = status;
        this.approvedBy = approvedBy;
    }

    // Overloaded constructor for INSERT (no ID needed because it's auto-generated)
    public AttendanceProcessing(int employeeID, int payPeriodID,
                                double totalLateHours, double totalApprovedOvertimeHours, double totalWorkedHours,
                                double totalPaidLeaveHours, double payableHours, String status, int approvedBy) {
        this.employeeID = employeeID;
        this.payPeriodID = payPeriodID;
        this.totalLateHours = totalLateHours;
        this.totalApprovedOvertimeHours = totalApprovedOvertimeHours;
        this.totalWorkedHours = totalWorkedHours;
        this.totalPaidLeaveHours = totalPaidLeaveHours;
        this.payableHours = payableHours;
        this.status = status;
        this.approvedBy = approvedBy;
    }

    // Getters and Setters
    public int getID() {
        return attendanceProcessingID;
    }

    public void setID(int attendanceProcessingID) {
        this.attendanceProcessingID = attendanceProcessingID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getPayPeriodID() {
        return payPeriodID;
    }

    public void setPayPeriodID(int payPeriodID) {
        this.payPeriodID = payPeriodID;
    }

    public double getTotalLateHours() {
        return totalLateHours;
    }

    public void setTotalLateHours(double totalLateHours) {
        this.totalLateHours = totalLateHours;
    }

    public double getTotalApprovedOvertimeHours() {
        return totalApprovedOvertimeHours;
    }

    public void setTotalApprovedOvertimeHours(double totalApprovedOvertimeHours) {
        this.totalApprovedOvertimeHours = totalApprovedOvertimeHours;
    }

    public double getTotalWorkedHours() {
        return totalWorkedHours;
    }

    public void setTotalWorkedHours(double totalWorkedHours) {
        this.totalWorkedHours = totalWorkedHours;
    }

    public double getTotalPaidLeaveHours() {
        return totalPaidLeaveHours;
    }

    public void setTotalPaidLeaveHours(double totalPaidLeaveHours) {
        this.totalPaidLeaveHours = totalPaidLeaveHours;
    }

    public double getPayableHours() {
        return payableHours;
    }

    public void setPayableHours(double payableHours) {
        this.payableHours = payableHours;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(int approvedBy) {
        this.approvedBy = approvedBy;
    }
}


//test