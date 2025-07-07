/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EntityManagement;

import CSVFileManager.CsvFile;
import com.motorph.employeemanagement.model.Employee;
import Class.IDManagement.IDPrefix;
import com.motorph.payrollprocessing.model.payroll.PayPeriod;
import com.motorph.attendancemanagement.model.DailyAttendance;
import com.motorph.attendancemanagement.model.Leave;
import com.motorph.attendancemanagement.model.LeaveType;
import com.motorph.attendancemanagement.model.Overtime;
import com.motorph.attendancemanagement.model.Request;
import com.motorph.usermanagement.model.User;

/**
 *
 * @author 63909
 */
public enum EntityType {
    EMPLOYEE(CsvFile.EMPLOYEEINFORMATION, IDPrefix.EMPLOYEE, Employee.class),
    USER(CsvFile.USER, IDPrefix.USER, User.class),
    REQUEST(CsvFile.REQUEST, IDPrefix.REQUEST, Request.class),
    LEAVE(CsvFile.LEAVE, IDPrefix.LEAVE, Leave.class),
    LEAVETYPE(CsvFile.LEAVETYPE, IDPrefix.LEAVE_TYPE, LeaveType.class),
    OVERTIME(CsvFile.OVERTIME, IDPrefix.OVERTIME, Overtime.class),
    DAILYATTENDANCE(CsvFile.DAILYATTENDANCE, IDPrefix.DAILY_ATTENDANCE, DailyAttendance.class),
    PAYPERIOD(CsvFile.PAYPERIOD, IDPrefix.PAY_PERIOD, PayPeriod.class);
    
    private final CsvFile csvFile;
    private final IDPrefix idPrefix;
    private final Class<?> entityClass; // New field

    EntityType(CsvFile csvFile, IDPrefix idPrefix, Class<?> entityClass) {
        this.csvFile = csvFile;
        this.idPrefix = idPrefix;
        this.entityClass = entityClass;
    }

    public CsvFile getCsvFile() {
        return csvFile;
    }

    public IDPrefix getIdPrefix() {
        return idPrefix;
    }

    public Class<?> getEntityClass() { // New method
        return entityClass;
    }
}

