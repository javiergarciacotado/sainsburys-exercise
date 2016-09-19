package uk.co.sainsburys.exercise.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    private final InputStream inputStream;

    public PropertiesReader(String fileName) {
        inputStream = getClass().getResourceAsStream(fileName);
    }

    public String get(String propertyName) throws IOException {
        final Properties properties = new Properties();
        properties.load(inputStream);
        final String property = properties.getProperty(propertyName);
        return property != null ? property : "";

    }
}
