/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Class.PPS;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author emmar
 */
public class DeductionCalculatorTest {
    
    @Test
    public void testCalculateSSS_LowerThreshold() {
        assertEquals(146.20, DeductionCalculator.calculateSSS(1000.00), 0.01);
    }

    @Test
    public void testCalculateSSS_UpperThreshold() {
        assertEquals(146.20, DeductionCalculator.calculateSSS(3249.00), 0.01); // Adjust per your SSS table!
    }

    @Test
    public void testCalculatePagIbig_Cap() {
        assertEquals(100.00, DeductionCalculator.calculatePagIbig(10000.00), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateSSS_Negative() {
        DeductionCalculator.calculateSSS(-5000.00);
    }
    
}
