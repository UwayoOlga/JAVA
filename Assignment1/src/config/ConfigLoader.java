package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private static Properties properties = new Properties();

    static {
        try {
            FileInputStream fileInputStream = new FileInputStream(".env");
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("Failed to load .env file: " + e.getMessage());
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
