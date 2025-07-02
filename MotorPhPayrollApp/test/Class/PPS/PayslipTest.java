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
public class PayslipTest {
    
    // PPM-08: Payslip Generation Valid
    @Test
    public void testPayslipGeneration() {
        Payslip payslip = new Payslip(
            1, 1, "Dev", "IT", new BigDecimal("20000.00"), new BigDecimal("1000.00"), 20,
            new BigDecimal("5.00"), new BigDecimal("5000.00"), new BigDecimal("35000.00"),
            new BigDecimal("7000.00"), new BigDecimal("28000.00")
        );

        assertEquals(1, payslip.getPayrollId());
        assertEquals("Dev", payslip.getPosition());
        assertEquals(new BigDecimal("28000.00"), payslip.getNet());
    }

    // PPM-09: Payslip missing data (should handle gracefully)
    @Test
    public void testPayslip_NullFields() {
        Payslip payslip = new Payslip();
        payslip.setPayrollId(99);
        payslip.setNet(null);
        assertEquals(99, payslip.getPayrollId());
        assertNull(payslip.getNet());
    }
}
