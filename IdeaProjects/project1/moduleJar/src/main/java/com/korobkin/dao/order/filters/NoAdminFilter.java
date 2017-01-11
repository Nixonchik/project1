package com.korobkin.dao.order.filters;

/**
 * Created by Java Developer on 06.12.2015.
 */
public class NoAdminFilter implements OrderFilter {
    @Override
    public String stringPattern() {
        return "admin_login is null";
    }
}
