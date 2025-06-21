/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Class.PPS;


import static junit.framework.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author emmar
 */
public class AllowanceCalculatorTest {
    
    /**
     * Sample case: rice=100, phone=50, clothing=150
     * (100 + 50 + 150) / 2 = 150.00
     */
    @Test
    public void testCalculateTotalAllowance_standardInputs() {
        double rice     = 100.0;
        double phone    =  50.0;
        double clothing = 150.0;
        double expected = (rice + phone + clothing) / 2.0;  // 150.0

        double actual = AllowanceCalculator.calculateTotalAllowance(rice, phone, clothing);
        assertEquals(
            "Allowance should be (100+50+150)/2",
            expected,
            actual,
            1e-6
        );
    }

    /** Edge case: all zeros → allowance = 0.00 */
    @Test
    public void testCalculateTotalAllowance_zeroInputs() {
        double actual = AllowanceCalculator.calculateTotalAllowance(0.0, 0.0, 0.0);
        assertEquals(
            "Zero inputs should yield 0.00",
            0.0,
            actual,
            1e-6
        );
    }
    
}
