package uk.co.sainsburys.exercise.output.impl;

import uk.co.sainsburys.exercise.model.RipeFruit;

import java.util.ArrayList;
import java.util.List;

public class RipeFruitWrapper {

    private final double total;
    private final List<RipeFruit> results;

    public RipeFruitWrapper() {
        total = 0.0D;
        results = new ArrayList<>();
    }

    public List<RipeFruit> getResults() {
        return results;
    }

    public double getTotal() {
        return total;
    }

}
