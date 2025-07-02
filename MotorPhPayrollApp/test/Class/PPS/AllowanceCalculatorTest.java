/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Class.PPS;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


/**
 *
 * @author emmar
 */
public class AllowanceCalculatorTest {
    
 @Test
    public void testCalculateTotalAllowance_Valid() {
        double rice = 1000, phone = 500, clothing = 250;
        double expected = (1000 + 500 + 250) / 2.0;
        double actual = AllowanceCalculator.calculateTotalAllowance(rice, phone, clothing);
        assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void testCalculateTotalAllowance_AllZero() {
        double actual = AllowanceCalculator.calculateTotalAllowance(0, 0, 0);
        assertEquals(0.0, actual, 0.0001);
    }

    @Test
    public void testCalculateTotalAllowance_BigDecimal_Valid() {
        assertEquals(
            new java.math.BigDecimal("875.00"),
            AllowanceCalculator.calculateTotalAllowance(
                new java.math.BigDecimal("1000"),
                new java.math.BigDecimal("500"),
                new java.math.BigDecimal("250")
            )
        );
    }
}
