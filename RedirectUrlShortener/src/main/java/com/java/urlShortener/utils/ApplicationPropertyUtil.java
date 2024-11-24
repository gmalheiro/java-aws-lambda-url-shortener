package com.java.urlShortener.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationPropertyUtil {
    private final static Properties properties = new Properties();
    String property;

    static {
        try (InputStream input = ApplicationPropertyUtil.class
                .getClassLoader()
                .getResourceAsStream("application.properties")) {

            if (input == null) {
                throw new IllegalArgumentException("application.properties not found in resources.");
            }

            properties.load(input);

        } catch (IOException ex) {
            throw new RuntimeException("Error when loading application.properties", ex);
        }
    }

    public static String getProperty(String property) {
        return properties.getProperty(property);
    }


}