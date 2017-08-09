package com.thetestguys.slumber.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class handles reading of property file
 * 
 * @author Andi Santoso
 *
 */
public class PropertyFactory {
    private Properties props = null;
    private final String propFileLoc = "config.properties";

    /**
     * Constructor
     */
    public PropertyFactory() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileLoc);
        try {
            props = new Properties();
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get properties
     * @return Properties
     */
    public Properties getProps() {
        return props;
    }

    /**
     * Get properties file location
     * @return Properties file location
     */
    public String getPropFileLoc() {
        return propFileLoc;
    }

    /**
     * Validates, and return property value if exists
     * @param key Key of a property
     * @return Property value
     */
    private String propertyValidator(String key) {
        Throwable t = null;
        String propertyValue = "";
        try {
            propertyValue = props.getProperty(key);
        } catch (Exception e) {
            t = e;
        } finally {
            if (t != null) {
                throw new IllegalArgumentException("Key is not valid or does not exist");
            }
        }
        return propertyValue;
    }

    /**
     * Get String value of a property
     * @param key Property's key
     * @return Property value
     */
    public String getValueString(String key) {
        return propertyValidator(key);
    }

    /**
     * Get Boolean value of a property
     * @param key Property's key
     * @return Property value
     */
    public Boolean getValueBoolean(String key) {
        return Boolean.parseBoolean(propertyValidator(key));
    }

    /**
     * Get Integer value of a property
     * @param key Property's key
     * @return Property value
     */
    public int getValueInteger(String key) {
        return Integer.parseInt(propertyValidator(key));
    }
}