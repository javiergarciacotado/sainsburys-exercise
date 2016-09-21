package uk.co.sainsburys.exercise.properties;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PropertiesReaderTest {

    private static final String UNKNOWN_PROPERTY = "some.property";
    private static final String VALID_PROPERTY = "sainsburys.scraper.url";

    private PropertiesReader propertiesReader;

    @Before
    public void setup() {
        propertiesReader = mock(PropertiesReader.class);
    }

    @Test
    public void givenAValidFileNameAndUnknownPropertyNameReturnEmpty() {
        when(propertiesReader.get(UNKNOWN_PROPERTY)).thenReturn("");
        assertEquals("", propertiesReader.get(UNKNOWN_PROPERTY));
    }

    @Test
    public void givenAValidFileNameAndKnownPropertyReturnValue() {
        when(propertiesReader.get(VALID_PROPERTY)).thenReturn("some value");
        assertEquals("some value", propertiesReader.get(VALID_PROPERTY));
    }


}
