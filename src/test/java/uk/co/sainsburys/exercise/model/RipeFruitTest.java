package uk.co.sainsburys.exercise.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RipeFruitTest {

    @Test
    public void givenARipeFruitShouldReturnCorrectValues() {
        RipeFruit ripeFruit = new RipeFruit("title", "description", 1.5D, "100kb");
        assertEquals("title", ripeFruit.getTitle());
        assertEquals("description", ripeFruit.getDescription());
        assertEquals(1.5D, ripeFruit.getUnitPrice(), 0.0D);
        assertEquals("100kb", ripeFruit.getSize());
    }

}