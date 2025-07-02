/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Class.PPS;

import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author emmar
 */
public class TaxCalculatorTest {
    
    @Test
    public void testCalculateWithHoldingTax_Zero() {
        assertEquals(0.0, TaxCalculator.calculateWithHoldingTax(3249.00, 308.65), 0.01);
    }

    @Test
    public void testCalculateWithHoldingTax_Positive() {
        double tax = TaxCalculator.calculateWithHoldingTax(50000.00, 5000.00);
        assertTrue(tax > 0);
    }

    @Test
    public void testCalculateWithHoldingTax_BigDecimal() {
        BigDecimal tax = TaxCalculator.calculateWithHoldingTax(
            new BigDecimal("50000.00"), new BigDecimal("5000.00"));
        assertTrue(tax.doubleValue() > 0);
    }

    @Test
    public void testCalculateWithHoldingTax_AllZero() {
        assertEquals(0.0, TaxCalculator.calculateWithHoldingTax(0.0, 0.0), 0.0);
    }
}
