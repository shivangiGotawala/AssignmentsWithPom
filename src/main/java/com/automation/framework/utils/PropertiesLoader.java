package com.automation.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

public class PropertiesLoader {

    Properties properties;

    public PropertiesLoader() {
        properties = new Properties();
    }

    public Properties loadProperties(String filePath) {
        try {
            String path = System.getProperty("user.dir") + filePath;
            properties.load(Files.newInputStream(new File(path).toPath()));
        } catch (Exception e) {
        }
        return properties;
    }

    public String getPropertyValue(String propertyName) {
        return properties.getProperty(propertyName);
    }


}
