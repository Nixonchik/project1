package com.korobkin.properties;

import java.util.ResourceBundle;

/**
 * Queries to DB stores in suited properties files.
 */
public class SQLQueries {
    private static SQLQueries instance = new SQLQueries();
    private ResourceBundle adminQueries = ResourceBundle.getBundle("queries.adminQueries");
    private ResourceBundle carQueries = ResourceBundle.getBundle("queries.carQueries");
    private ResourceBundle clientQueries = ResourceBundle.getBundle("queries.clientQueries");
    private ResourceBundle defectQueries = ResourceBundle.getBundle("queries.defectQueries");
    private ResourceBundle orderQueries = ResourceBundle.getBundle("queries.orderQueries");

    private SQLQueries(){}

    public static String adminQuery(String key) {
        return (String) instance.adminQueries.getObject(key);
    }

    public static String carQuery(String key) {
        return (String) instance.carQueries.getObject(key);
    }

    public static String clientQuery(String key) {
        return (String) instance.clientQueries.getObject(key);
    }

    public static String defectQuery(String key) {
        return (String) instance.defectQueries.getObject(key);
    }

    public static String orderQuery(String key) {
        return (String) instance.orderQueries.getObject(key);
    }
}
