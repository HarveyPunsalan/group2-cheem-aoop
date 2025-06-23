/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.PPS;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


/**
 *
 * @author 63909
 */
public class PayrollCalculator {
    
    /**
     * Primitive‐based convenience overload.  Simply wraps
     * the BigDecimal version, using 0 for employeeId, payPeriodId,
     * overtimeHours and overtimeRate.
     */
    public static PayrollRecord calculatePayrollRecord(
            double hoursWorked,
            double hourlyRate,
            double rice,
            double phone,
            double clothing
    ) {
        return calculatePayrollRecord(
            0,                        // employeeId (set by service if needed)
            0,                        // payPeriodId
            BigDecimal.valueOf(hoursWorked),
            BigDecimal.valueOf(hourlyRate),
            BigDecimal.valueOf(rice),
            BigDecimal.valueOf(phone),
            BigDecimal.valueOf(clothing),
            BigDecimal.ZERO,          // overtimeHours
            BigDecimal.ZERO           // overtimeRate
        );
    }

    /**
     * Full BigDecimal‐based pay record calculator.
     *
     * @param employeeId     id of the employee (for the record)
     * @param payPeriodId    id of the pay period (for the record)
     * @param hoursWorked    total regular hours worked
     * @param hourlyRate     hourly rate
     * @param rice           rice subsidy allowance
     * @param phone          phone allowance
     * @param clothing       clothing allowance
     * @param overtimeHours  overtime hours worked
     * @param overtimeRate   overtime rate
     * @return fully populated PayrollRecord
     */
    public static PayrollRecord calculatePayrollRecord(
            int employeeId,
            int payPeriodId,
            BigDecimal hoursWorked,
            BigDecimal hourlyRate,
            BigDecimal rice,
            BigDecimal phone,
            BigDecimal clothing,
            BigDecimal overtimeHours,
            BigDecimal overtimeRate
    ) {
        
        // Ensure all values are not null and not negative
        hoursWorked = safeNonNull(hoursWorked);
        hourlyRate = safeNonNull(hourlyRate);
        rice = safeNonNull(rice);
        phone = safeNonNull(phone);
        clothing = safeNonNull(clothing);
        overtimeHours = safeNonNull(overtimeHours);
        overtimeRate = safeNonNull(overtimeRate);
        
        // 1. Basic pay
        BigDecimal basicPay = SalaryCalculator.calculateBasicSalary(hoursWorked, hourlyRate);

        // 2. Overtime pay
        BigDecimal overtimePay = SalaryCalculator.calculateOvertimePay(overtimeHours, overtimeRate);

        // 3. Allowance
        BigDecimal totalAllowance = AllowanceCalculator.calculateTotalAllowance(rice, phone, clothing, BigDecimal.ZERO);

        // 4. Gross pay (basic + overtime + allowance)
        BigDecimal grossPay = basicPay.add(overtimePay).add(totalAllowance);

        // 5. Government contributions
        BigDecimal sss        = BigDecimal.valueOf(DeductionCalculator.calculateSSS(grossPay.doubleValue()));
        BigDecimal philHealth = BigDecimal.valueOf(DeductionCalculator.calculatePhilHealth(grossPay.doubleValue()));
        BigDecimal pagIbig    = BigDecimal.valueOf(DeductionCalculator.calculatePagIbig(grossPay.doubleValue()));
        BigDecimal govContrib = sss.add(philHealth).add(pagIbig);

        // 6. Withholding tax
        BigDecimal withholdingTax = TaxCalculator.calculateWithHoldingTax(grossPay, govContrib);

        // 7. Total deductions
        BigDecimal totalDeductions = govContrib.add(withholdingTax);

        // 8. Net pay
        BigDecimal netPay = grossPay.subtract(totalDeductions);

        // 9. Build record (all rounded to 2 decimals)
        PayrollRecord rec = new PayrollRecord();
        rec.setEmployeeId(employeeId);
        rec.setPayPeriodId(payPeriodId);
        rec.setHoursWorked(hoursWorked.setScale(2, RoundingMode.HALF_UP));
        rec.setBasicPay(basicPay.setScale(2, RoundingMode.HALF_UP));
        rec.setGrossPay(grossPay.setScale(2, RoundingMode.HALF_UP));
        rec.setSss(sss.setScale(2, RoundingMode.HALF_UP));
        rec.setPhilHealth(philHealth.setScale(2, RoundingMode.HALF_UP));
        rec.setPagIbig(pagIbig.setScale(2, RoundingMode.HALF_UP));
        rec.setWithholdingTax(withholdingTax.setScale(2, RoundingMode.HALF_UP));
        rec.setTotalDeductions(totalDeductions.setScale(2, RoundingMode.HALF_UP));
        rec.setOvertimeHours(overtimeHours.setScale(2, RoundingMode.HALF_UP));
        rec.setOvertimePay(overtimePay.setScale(2, RoundingMode.HALF_UP));
        rec.setNetPay(netPay.setScale(2, RoundingMode.HALF_UP));
        return rec;
    }

    // Utility to make all inputs safe for calculation (null/negative -> 0)
    private static BigDecimal safeNonNull(BigDecimal val) {
        return (val == null || val.compareTo(BigDecimal.ZERO) < 0) ? BigDecimal.ZERO : val;
    }

    public static PayrollSummary calculatePayrollSummary(List<PayrollRecord> records) {
        BigDecimal totalBasic            = BigDecimal.ZERO;
        BigDecimal totalGross            = BigDecimal.ZERO;
        BigDecimal totalGovContrib       = BigDecimal.ZERO;
        BigDecimal totalWithholdingTaxes = BigDecimal.ZERO;
        BigDecimal totalDeductions       = BigDecimal.ZERO;
        BigDecimal totalNet              = BigDecimal.ZERO;

        for (PayrollRecord r : records) {
            totalBasic            = totalBasic.add(r.getBasicPay());
            totalGross            = totalGross.add(r.getGrossPay());
            totalGovContrib       = totalGovContrib
                                        .add(r.getSss())
                                        .add(r.getPhilHealth())
                                        .add(r.getPagIbig());
            totalWithholdingTaxes = totalWithholdingTaxes.add(r.getWithholdingTax());
            totalDeductions       = totalDeductions.add(r.getTotalDeductions());
            totalNet              = totalNet.add(r.getNetPay());
        }

        return new PayrollSummary(
            totalBasic.setScale(2, RoundingMode.HALF_UP),
            totalGross.setScale(2, RoundingMode.HALF_UP),
            totalGovContrib.setScale(2, RoundingMode.HALF_UP),
            totalWithholdingTaxes.setScale(2, RoundingMode.HALF_UP),
            totalDeductions.setScale(2, RoundingMode.HALF_UP),
            totalNet.setScale(2, RoundingMode.HALF_UP)
        );
    }
}
