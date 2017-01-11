package com.korobkin.properties;

import java.util.ResourceBundle;

/**
 * Global parameters of application.
 */
public class Config {

    private static Config instance = new Config();
    private ResourceBundle config = ResourceBundle.getBundle("config");

    public static final String CHILD_CHAIR_PRICE = "CHILD_CHAIR_PRICE";
    public static final String GPS_PRICE = "GPS_PRICE";
    public static final String DOMEN = "DOMEN";
    public static final String JSP_PATH = "JSP_PATH";


    private Config() {}

    public static String getProperty(String key) {
        return (String) instance.config.getObject(key);
    }
}
