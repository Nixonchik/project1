package com.korobkin.properties;

import java.util.ResourceBundle;

/**
 * All messages stores in messages.properties to do program multi-language
 */
public class Message {

    private static Message instance = new Message();
    private ResourceBundle resource= ResourceBundle.getBundle(BUNDLE_NAME);
    private static final String BUNDLE_NAME = "messages.messages";
    public static final String WRONG_PASSWORD = "WRONG_PASSWORD";

    public static final String NOT_ALL_PARAMETERS = "NOT_ALL_PARAMETERS";
    public static final String PASSWORDS_ARE_NOT_EQUALS = "PASSWORDS_ARE_NOT_EQUALS";
    public static final String ADMIN_IS_EXIST = "ADMIN_IS_EXIST";
    public static final String ADMIN_SUCCESSFULLY_ADDED = "ADMIN_SUCCESSFULLY_ADDED";
    public static final String BAD_PHONE_NUMBER = "BAD_PHONE_NUMBER";
    public static final String BAD_EMAIL = "BAD_EMAIL";
    public static final String SQL_EXCEPTION = "SQL_EXCEPTION";
    public static final String CAR_NOT_ADDED = "CAR_NOT_ADDED";
    public static final String CAR_WAS_ADDED = "CAR_WAS_ADDED";
    public static final String BAD_DATA = "BAD_DATA";
    public static final String DATE_IS_BLOCKED = "DATE_IS_BLOCKED";
    public static final String FROM_NOT_AFTER_TO_DATE = "FROM_NOT_AFTER_TO_DATE";
    public static final String FROM_NOT_AFTER_NOW_DATE = "FROM_NOT_AFTER_NOW_DATE";
    public static final String BAD_DATE = "BAD_DATE";
    public static final String BAD_SESSION_TO_CONFIRM_ORDER = "BAD_SESSION_TO_CONFIRM_ORDER";
    public static final String ORDER_SUCCESSFULLY_ADDED = "ORDER_SUCCESSFULLY_ADDED";
    public static final String ORDER_CONFIRMED = "ORDER_CONFIRMED";
    public static final String ORDER_NOT_CONFIRMED = "ORDER_NOT_CONFIRMED";
    public static final String ORDER_DELETED = "ORDER_DELETED";
    public static final String CAR_WAS_GIVEN = "CAR_WAS_GIVEN";
    public static final String SUCCESSFUL = "SUCCESSFUL";

    private Message() {
    }

    public static String get(String key) {
        return (String) instance.resource.getObject(key);
    }
}
