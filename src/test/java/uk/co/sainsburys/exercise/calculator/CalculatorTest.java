package uk.co.sainsburys.exercise.calculator;

import org.junit.Before;
import org.junit.Test;
import uk.co.sainsburys.exercise.model.RipeFruit;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setup() {
        calculator = new Calculator();
    }

    @Test
    public void givenAnEmptyUnitPriceListShouldReturnZero() {
        List<RipeFruit> ripeFruitList = Collections.emptyList();
        assertEquals(0.0D, calculator.getTotalPrice(ripeFruitList), 0.0D);
    }

    @Test
    public void givenAUnitPriceListShouldReturnSum() {
        RipeFruit rp1 = new RipeFruit("","", 3.2D, "");
        RipeFruit rp2 = new RipeFruit("","", 4.3D, "");
        List<RipeFruit> ripeFruitList = Arrays.asList(rp1, rp2);

        assertEquals(7.5D, calculator.getTotalPrice(ripeFruitList), 0.0D);
    }
}