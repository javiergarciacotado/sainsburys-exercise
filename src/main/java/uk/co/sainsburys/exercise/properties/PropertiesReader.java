package uk.co.sainsburys.exercise.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class to read the properties for the exercise
 */
public class PropertiesReader {

    private final InputStream inputStream;

    /**
     * Builds an input stream from the given filename resource
     * @param fileName
     */
    public PropertiesReader(String fileName) {
        inputStream = getClass().getResourceAsStream(fileName);
    }

    /**
     * To retrieve the property name
     * @param propertyName property to look for
     * @return Property Value or Empty
     * @throws IOException
     */
    public String get(String propertyName) throws IOException {
        final Properties properties = new Properties();
        properties.load(inputStream);
        final String property = properties.getProperty(propertyName);
        return property != null ? property : "";
    }
}
