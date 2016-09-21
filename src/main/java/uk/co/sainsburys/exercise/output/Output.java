package uk.co.sainsburys.exercise.output;

import uk.co.sainsburys.exercise.model.RipeFruit;

import java.io.IOException;
import java.util.List;

/**
 * Defines the kind of output for the ripe fruits
 */
public interface Output {

    void writeTo(String fileName, double total, List<RipeFruit> ripeFruitList) throws IOException;
}
