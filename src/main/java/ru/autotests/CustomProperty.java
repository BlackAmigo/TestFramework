package ru.autotests;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CustomProperty {

    private static CustomProperty property;
    private Properties properties;
    private String driverType;

    private CustomProperty() {
        loadProp();
        driverType = properties.getProperty("webdriver.type");
    }

    public static CustomProperty getInstance(){
        if (property == null)
            property = new CustomProperty();
        return property;
    }

    private void loadProp(){
        properties = new Properties();
        try (InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public Properties getProperties() {
        return properties;
    }

    public String getDriverType() {
        return driverType;
    }
}
