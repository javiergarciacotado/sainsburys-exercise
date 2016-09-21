package uk.co.sainsburys.exercise.properties;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PropertiesReaderIT {

    private static final String UNKNOWN_PROPERTY = "some.property";
    private static final String VALID_PROPERTY = "sainsburys.scraper.url";
    private static final String PROPERTIES_FILE = "/application.properties";
    private static final String URL = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";

    @Test(expected = NullPointerException.class)
    public void givenNullFileNameThrowNullPointerException() throws IOException {
        PropertiesReader propertiesReader = new PropertiesReader(null);
        assertNull(propertiesReader);
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
        assertEquals(URL, propertiesReader.get(VALID_PROPERTY));
    }


}
