package uk.co.sainsburys.exercise.properties;

import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PropertiesReaderTest {

    private static final String UNKNOWN_PROPERTY = "some.property";
    private static final String VALID_PROPERTY = "sainsburys.scraper.url";

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
        PropertiesReader propertiesReader = new PropertiesReader("/application.properties");
        assertEquals("", propertiesReader.get(UNKNOWN_PROPERTY));
    }

    @Test
    public void givenAValidFileNameAndKnownPropertyReturnValue() throws IOException {
        PropertiesReader propertiesReader = new PropertiesReader("/application.properties");
        assertEquals("http://some-url", propertiesReader.get(VALID_PROPERTY));
    }


}
