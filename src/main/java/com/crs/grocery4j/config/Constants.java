package com.crs.grocery4j.config;

/**
 * Application constants.
 */
public final class Constants {

    //Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_'.@A-Za-z0-9-]*$";

    public static final String SYSTEM_ACCOUNT = "system";

    public static final String ANONYMOUS_USER = "anonymoususer";

    public static final String APP_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

    public static final String DEFAULT_GROCERY_LIST_NAME = "Unassigned";

    public static final String DEFAULT_NEW_GROCERY_LIST_NAME = "New Grocery List";

    private Constants() {
    }
}
