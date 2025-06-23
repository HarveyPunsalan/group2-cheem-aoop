/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.PPS;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 *
 * @author 63909
 */
public class PayrollSummary {
    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private final BigDecimal totalBasicSalary;
    private final BigDecimal totalGrossSalary;
    private final BigDecimal totalGovernmentContribution;
    private final BigDecimal totalWithholdingTax;
    private final BigDecimal totalDeductions;
    private final BigDecimal totalNetSalary;

    public PayrollSummary(
        BigDecimal totalBasicSalary,
        BigDecimal totalGrossSalary,
        BigDecimal totalGovernmentContribution,
        BigDecimal totalWithholdingTax,
        BigDecimal totalDeductions,
        BigDecimal totalNetSalary)
    {

    // normalize to 2-decimals half-up
        this.totalBasicSalary             = totalBasicSalary.setScale(2, RoundingMode.HALF_UP);
        this.totalGrossSalary             = totalGrossSalary.setScale(2, RoundingMode.HALF_UP);
        this.totalGovernmentContribution  = totalGovernmentContribution.setScale(2, RoundingMode.HALF_UP);
        this.totalWithholdingTax          = totalWithholdingTax.setScale(2, RoundingMode.HALF_UP);
        this.totalDeductions              = totalDeductions.setScale(2, RoundingMode.HALF_UP);
        this.totalNetSalary               = totalNetSalary.setScale(2, RoundingMode.HALF_UP);
    }
// If you still need a double-based constructor, you can overload here:
    public PayrollSummary(
        double totalBasicSalary,
        double totalGrossSalary,
        double totalGovernmentContribution,
        double totalWithholdingTax,
        double totalDeductions,
        double totalNetSalary
    ) {
        this(
          BigDecimal.valueOf(totalBasicSalary),
          BigDecimal.valueOf(totalGrossSalary),
          BigDecimal.valueOf(totalGovernmentContribution),
          BigDecimal.valueOf(totalWithholdingTax),
          BigDecimal.valueOf(totalDeductions),
          BigDecimal.valueOf(totalNetSalary)
        );
    }

    // ——— Getters ———

    public BigDecimal getTotalBasicSalary()            { return totalBasicSalary; }
    public BigDecimal getTotalGrossSalary()            { return totalGrossSalary; }
    public BigDecimal getTotalGovernmentContribution() { return totalGovernmentContribution; }
    public BigDecimal getTotalWithholdingTax()         { return totalWithholdingTax; }
    public BigDecimal getTotalDeductions()             { return totalDeductions; }
    public BigDecimal getTotalNetSalary()              { return totalNetSalary; }

    @Override
    public String toString() {
        return "PayrollSummary{" +
               "basic=" + totalBasicSalary +
               ", gross=" + totalGrossSalary +
               ", gov=" + totalGovernmentContribution +
               ", tax=" + totalWithholdingTax +
               ", ded=" + totalDeductions +
               ", net=" + totalNetSalary +
               '}';
    }
}

