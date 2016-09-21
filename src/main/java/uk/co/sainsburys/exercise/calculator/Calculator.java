package uk.co.sainsburys.exercise.calculator;

import uk.co.sainsburys.exercise.model.RipeFruit;

import java.util.List;

/**
 * Class to calculate the total sum of products
 */
public class Calculator {

    /**
     * To retrieve the total sum of ripe fruits
     * @param ripeFruitList List of ripe fruits to calculate the sum
     * @return total price
     */
    public double getTotalPrice(final List<RipeFruit> ripeFruitList) {
        return ripeFruitList.stream().mapToDouble(RipeFruit::getUnitPrice).sum();
    }
}
