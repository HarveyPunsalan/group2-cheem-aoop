/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.TAT;

import Class.EMS.Employee;
import Class.PPS.PayPeriod;

public class PayPeriodAttendance {
    private String payPeriodAttendanceID;
    private Employee employee;
    private PayPeriod payPeriod;
    private double totalWorkedHours;
    private double totalLateHours;
    private double totalApprovedOvertimeHours;
    private double totalPaidLeaveHours;
    private double payableHours;
    private String status;
    private Employee approvedBy;

    public PayPeriodAttendance() {}

    public PayPeriodAttendance(String payPeriodAttendanceID) {
        this.payPeriodAttendanceID = payPeriodAttendanceID;
    }

    public PayPeriodAttendance(
        String payPeriodAttendanceID,
        Employee employee,
        PayPeriod payPeriod,
        double totalWorkedHours,
        double totalLateHours,
        double totalApprovedOvertimeHours,
        double totalPaidLeaveHours,
        double payableHours,
        String status,
        Employee approvedBy
    ) {
        this.payPeriodAttendanceID = payPeriodAttendanceID;
        this.employee = employee;
        this.payPeriod = payPeriod;
        this.totalWorkedHours = totalWorkedHours;
        this.totalLateHours = totalLateHours;
        this.totalApprovedOvertimeHours = totalApprovedOvertimeHours;
        this.totalPaidLeaveHours = totalPaidLeaveHours;
        this.payableHours = payableHours;
        this.status = status;
        this.approvedBy = approvedBy;
    }

    // ======= Getters =======
    public String getPayPeriodAttendanceID() {
        return payPeriodAttendanceID;
    }

    public Employee getEmployee() {
        return employee;
    }

    public PayPeriod getPayPeriod() {
        return payPeriod;
    }

    public int getPayPeriodId() {
        return (payPeriod != null) ? payPeriod.getPayPeriodID() : -1;  // fixed to return int
    }

    public double getTotalWorkedHours() {
        return totalWorkedHours;
    }

    public double getTotalLateHours() {
        return totalLateHours;
    }

    public double getTotalApprovedOvertimeHours() {
        return totalApprovedOvertimeHours;
    }

    public double getTotalPaidLeaveHours() {
        return totalPaidLeaveHours;
    }

    public double getPayableHours() {
        return payableHours;
    }

    public String getStatus() {
        return status;
    }

    public Employee getApprovedBy() {
        return approvedBy;
    }

    // ======= Setters =======
    public void setStatus(String status) {
        this.status = status;
    }

    public void setApprovedBy(Employee approvedBy) {
        this.approvedBy = approvedBy;
    }
}
