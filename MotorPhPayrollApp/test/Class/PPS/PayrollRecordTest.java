/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Class.PPS;

import java.math.BigDecimal;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author emmar
 */
public class PayrollRecordTest {
    
    @Test
    public void testPayrollRecordFields() {
        PayrollRecord rec = new PayrollRecord();

        rec.setId(11);
        rec.setEmployeeId(99);
        rec.setPayPeriodId(202406);
        rec.setHoursWorked(new BigDecimal("80.0"));
        rec.setBasicPay(new BigDecimal("12000.50"));
        rec.setGrossPay(new BigDecimal("15000.25"));
        rec.setSss(new BigDecimal("500.00"));
        rec.setPhilHealth(new BigDecimal("300.50"));
        rec.setPagIbig(new BigDecimal("200.25"));
        rec.setWithholdingTax(new BigDecimal("100.00"));
        rec.setTotalDeductions(new BigDecimal("1100.75"));
        rec.setOvertimeHours(new BigDecimal("8.00"));
        rec.setOvertimePay(new BigDecimal("960.00"));
        rec.setNetPay(new BigDecimal("14899.50"));

        assertEquals(11, rec.getId());
        assertEquals(99, rec.getEmployeeId());
        assertEquals(202406, rec.getPayPeriodId());
        assertEquals(new BigDecimal("80.0"), rec.getHoursWorked());
        assertEquals(new BigDecimal("12000.50"), rec.getBasicPay());
        assertEquals(new BigDecimal("15000.25"), rec.getGrossPay());
        assertEquals(new BigDecimal("500.00"), rec.getSss());
        assertEquals(new BigDecimal("300.50"), rec.getPhilHealth());
        assertEquals(new BigDecimal("200.25"), rec.getPagIbig());
        assertEquals(new BigDecimal("100.00"), rec.getWithholdingTax());
        assertEquals(new BigDecimal("1100.75"), rec.getTotalDeductions());
        assertEquals(new BigDecimal("8.00"), rec.getOvertimeHours());
        assertEquals(new BigDecimal("960.00"), rec.getOvertimePay());
        assertEquals(new BigDecimal("14899.50"), rec.getNetPay());
    }

    @Test
    public void testPayrollRecordDefaultConstructor() {
        PayrollRecord rec = new PayrollRecord();
        assertEquals(0, rec.getId());
        assertEquals(0, rec.getEmployeeId());
        assertEquals(0, rec.getPayPeriodId());
        assertNull(rec.getHoursWorked());
        assertNull(rec.getBasicPay());
        assertNull(rec.getGrossPay());
        assertNull(rec.getSss());
        assertNull(rec.getPhilHealth());
        assertNull(rec.getPagIbig());
        assertNull(rec.getWithholdingTax());
        assertNull(rec.getTotalDeductions());
        assertNull(rec.getOvertimeHours());
        assertNull(rec.getOvertimePay());
        assertNull(rec.getNetPay());
    }
}
