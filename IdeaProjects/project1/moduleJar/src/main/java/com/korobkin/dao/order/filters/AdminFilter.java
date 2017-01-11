package com.korobkin.dao.order.filters;

/**
 * Created by Java Developer on 29.11.2015.
 */
public class AdminFilter implements OrderFilter {
    String adminLogin;

    public AdminFilter(String adminLogin) {
        this.adminLogin = adminLogin;
    }

    @Override
    public String stringPattern() {
        return "admin_login = '" + adminLogin + "'";
    }
}
