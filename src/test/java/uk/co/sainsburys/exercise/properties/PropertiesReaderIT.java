package uk.co.sainsburys.exercise.properties;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class PropertiesReaderIT {

    private static final String UNKNOWN_PROPERTY = "some.property";
    private static final String VALID_PROPERTY = "sainsburys.scraper.url";
    private static final String PROPERTIES_FILE = "/application.properties";

    @Test(expected = NullPointerException.class)
    public void givenNullFileNameThrowNullPointerException() {
        PropertiesReader propertiesReader = new PropertiesReader(null);
    }

    @Test
    public void givenAEmptyFileNameReturnEmptyPropertyValue() throws IOException {
        PropertiesReader propertiesReader = new PropertiesReader("");
        assertEquals("", propertiesReader.get(UNKNOWN_PROPERTY));
    }

    @Test
    public void givenAValidFileNameAndUnknownPropertyNameReturnEmpty() throws IOException {
        PropertiesReader propertiesReader = new PropertiesReader(PROPERTIES_FILE);
        assertEquals("", propertiesReader.get(UNKNOWN_PROPERTY));
    }

    @Test
    public void givenAValidFileNameAndKnownPropertyReturnValue() throws IOException {
        PropertiesReader propertiesReader = new PropertiesReader(PROPERTIES_FILE);
        assertEquals("http://some-url", propertiesReader.get(VALID_PROPERTY));
    }


}
