package uk.co.sainsburys.exercise.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class RipeFruitTest {

    @Test
    public void givenARipeFruitValidateRipeFruit() {
        RipeFruit ripeFruit = new RipeFruit("title", "description", 1.5F, "100kb");
        assertEquals("title", ripeFruit.getTitle());
        assertEquals("description", ripeFruit.getDescription());
        assertEquals(1.5F, ripeFruit.getUnitPrice(), 0.0F);
        assertEquals("100kb", ripeFruit.getSize());
    }

}