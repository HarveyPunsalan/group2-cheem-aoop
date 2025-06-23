/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.PPS;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 *
 * @author 63909
 */
public class PayPeriod {
    private int       id;         // maps to pay_period_id
    private LocalDate startDate;  // start_date
    private LocalDate endDate;    // end_date
    private LocalDate payDay;     // pay_day
    private LocalDate payrollDue; // payroll_due

    // ISO format (yyyy-MM-dd) matches your dump and the JDBC DATE type
    private static final DateTimeFormatter ISO = DateTimeFormatter.ISO_LOCAL_DATE;

    public PayPeriod() { }

    /** Full constructor for JDBC or manual creation. */
    public PayPeriod(int id,
                     LocalDate startDate,
                     LocalDate endDate,
                     LocalDate payDay,
                     LocalDate payrollDue) {
        validateOrder(startDate, endDate,   "start must be ≤ end");
        validateOrder(endDate,   payDay,    "end must be < pay day");
        validateOrder(payDay,    payrollDue,"pay day must be ≤ payroll due");

        this.id          = id;
        this.startDate   = startDate;
        this.endDate     = endDate;
        this.payDay      = payDay;
        this.payrollDue  = payrollDue;
    }

    /** Convenience: parse all dates from ISO strings. */
    public PayPeriod(int id,
                     String startIso,
                     String endIso,
                     String payDayIso,
                     String payrollDueIso) {
        this(id,
             LocalDate.parse(startIso,   ISO),
             LocalDate.parse(endIso,     ISO),
             LocalDate.parse(payDayIso,  ISO),
             LocalDate.parse(payrollDueIso, ISO)
        );
    }
    /**
     * Quick two-arg constructor for GUI/list parsing.
     * Only start/end; leaves payDay & payrollDue null.
     */
    public PayPeriod(String startIso, String endIso) {
        LocalDate s = LocalDate.parse(startIso, ISO);
        LocalDate e = LocalDate.parse(endIso,   ISO);
        validateOrder(s, e, "startDate must be ≤ endDate");
        this.startDate = s;
        this.endDate   = e;
    }

    private void validateOrder(LocalDate a, LocalDate b, String msg) {
        if (a.isAfter(b)) throw new IllegalArgumentException(msg);
    }

    // — Getters & setters —

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) {
        validateOrder(startDate, this.endDate, "start must be ≤ end");
        this.startDate = startDate;
    }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) {
        validateOrder(this.startDate, endDate, "start must be ≤ end");
        this.endDate = endDate;
    }

    public LocalDate getPayDay() { return payDay; }
    public void setPayDay(LocalDate payDay) {
        validateOrder(this.endDate, payDay, "end must be < pay day");
        this.payDay = payDay;
    }

    public LocalDate getPayrollDue() { return payrollDue; }
    public void setPayrollDue(LocalDate payrollDue) {
        validateOrder(this.payDay, payrollDue, "pay day must be ≤ payroll due");
        this.payrollDue = payrollDue;
    }

    /** Inclusive length of the period in days. */
    public int getLengthDays() {
        return (int)(endDate.toEpochDay() - startDate.toEpochDay() + 1);
    }

    /** True if d is between startDate and endDate (inclusive). */
    public boolean includes(LocalDate d) {
        return !d.isBefore(startDate) && !d.isAfter(endDate);
    }
    public LocalDate getPayDate() {
    return payDay;
}

    @Override
    public String toString() {
        return String.format("PayPeriod[id=%d, %s → %s, payDay=%s, due=%s]",
                id, startDate, endDate, payDay, payrollDue);
    }
}
