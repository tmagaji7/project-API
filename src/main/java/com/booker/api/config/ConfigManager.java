package com.booker.api.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Singleton Configuration Manager
 * Loads properties from config/qa.properties
 */
public class ConfigManager {

    private static final Properties properties = new Properties();
    private static boolean isLoaded = false;

    private ConfigManager() {
    }

    static {
        loadProperties();
    }

    private static void loadProperties() {
        if (!isLoaded) {
            try (InputStream input = ConfigManager.class.getClassLoader()
                    .getResourceAsStream("config/qa.properties")) {
                if (input != null) {
                    properties.load(input);
                    isLoaded = true;
                } else {
                    throw new RuntimeException("config/qa.properties not found in classpath");
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to load configuration", e);
            }
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static String get(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
