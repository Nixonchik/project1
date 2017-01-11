package com.korobkin.command.admin.order.state;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface OrderState {

    /**
     * Request has attribute "order"
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return name of jsp page
     */
    String changeState(HttpServletRequest request, HttpServletResponse response);

}
