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
public class SalaryCalculatorTest {
    
    @Test
    public void testCalculateBasicSalary_Double() {
        assertEquals(9600.00, SalaryCalculator.calculateBasicSalary(80, 120), 0.01);
    }

    @Test
    public void testCalculateOvertimePay_Double() {
        assertEquals(1875.00, SalaryCalculator.calculateOvertimePay(10, 150), 0.01);
    }

    @Test
    public void testCalculateBasicSalary_BigDecimal() {
        BigDecimal result = SalaryCalculator.calculateBasicSalary(new BigDecimal("80"), new BigDecimal("120"));
        assertEquals(new BigDecimal("9600.00"), result);
    }

    @Test
    public void testHighPrecisionRounding() {
        BigDecimal result = SalaryCalculator.calculateBasicSalary(new BigDecimal("1.234"), new BigDecimal("99.999"));
        assertEquals(new BigDecimal("123.40"), result); // adjust to your rounding
    }
}
