package com.korobkin.command.admin.order.state;

import com.korobkin.controller.RequestHelper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CarWasReturnedOrderState implements OrderState {
    private static final Logger logger = Logger.getLogger(CarWasReturnedOrderState.class);

    @Override
    public String changeState(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("error", "You trying to change status of returned car.");
        //TODO

        return RequestHelper.getInstance().getCommand("/page/admin/orders").execute(request, response);
    }
}
