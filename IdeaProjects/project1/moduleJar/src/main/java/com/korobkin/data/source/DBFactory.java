package com.korobkin.data.source;

import java.sql.Connection;

/**
 * Class for getting DB connections.
 */
public class DBFactory {

    /**
     * @return DB Connection from Connection pool.
     */
    public static Connection getDBConnection() {
        return ConnectionPool.getInstance.getConnection();
    }

    /**
     * Release Connection to use it time after time.
     */
    public static void releaseDBConnection(Connection connection) {
        ConnectionPool.getInstance.releaseConnection(connection);
    }
}
