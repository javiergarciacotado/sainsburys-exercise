package uk.co.sainsburys.exercise.properties;

import java.io.IOException;
import java.util.Properties;

/**
 * Class to read the properties file for the exercise
 */
public class PropertiesReader {

    private final Properties properties = new Properties();

    /**
     * loads and stores the properties from the given fileName
     * @param fileName Input to read properties
     * @throws IOException if {@code fileName} is not found
     */
    public PropertiesReader(String fileName) throws IOException {
        properties.load(getClass().getResourceAsStream(fileName));
    }

    /**
     * To retrieve the property name
     * @param propertyName property to look for
     * @return Property Value or Empty
     */
    public String get(String propertyName) {
        final String property = properties.getProperty(propertyName);
        return property != null && !property.isEmpty() ? property : "";
    }
}
