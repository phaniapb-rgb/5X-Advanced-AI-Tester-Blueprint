package utilities;

import constants.FrameworkConstants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {
    private final Properties properties;

    private ConfigReader(Properties properties) {
        this.properties = properties;
    }

    public static ConfigReader load() {
        Properties properties = new Properties();
        try (InputStream input = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(FrameworkConstants.CONFIG_FILE)) {
            if (input == null) {
                throw new IllegalStateException("Missing configuration resource: " + FrameworkConstants.CONFIG_FILE);
            }
            properties.load(input);
            return new ConfigReader(properties);
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to read " + FrameworkConstants.CONFIG_FILE, exception);
        }
    }

    public String get(String key) {
        String environmentValue = System.getenv(environmentKey(key));
        if (environmentValue != null && !environmentValue.isBlank()) {
            return environmentValue.trim();
        }
        return properties.getProperty(key, "").trim();
    }

    public String getRequired(String key) {
        String value = get(key);
        if (value.isBlank()) {
            throw new IllegalStateException("Required configuration is missing: " + key
                    + " (or environment variable " + environmentKey(key) + ")");
        }
        return value;
    }

    public boolean getBoolean(String key) {
        String value = get(key);
        if (!value.equalsIgnoreCase("true") && !value.equalsIgnoreCase("false")) {
            throw new IllegalStateException("Configuration " + key + " must be true or false");
        }
        return Boolean.parseBoolean(value);
    }

    private String environmentKey(String key) {
        return "PLAYWRIGHT_" + key.replace('.', '_').replace('-', '_').toUpperCase();
    }
}
