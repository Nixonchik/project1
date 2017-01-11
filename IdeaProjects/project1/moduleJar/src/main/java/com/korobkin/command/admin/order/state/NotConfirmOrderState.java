package com.korobkin.command.admin.order.state;

import com.korobkin.controller.RequestHelper;
import com.korobkin.dao.DAOFactory;
import com.korobkin.model.Order;
import com.korobkin.properties.Message;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NotConfirmOrderState implements OrderState {
    private final static Logger logger = Logger.getLogger(NotConfirmOrderState.class);

    @Override
    public String changeState(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("admin");
        Order order = (Order) request.getAttribute("order");
        if (DAOFactory.orderDAO().changeOrderStatus(order.getId(), Order.Status.CONFIRMED) &&
                DAOFactory.orderDAO().changeAdminOfOrder(order.getId(), login)) {
            request.setAttribute("message", Message.get(Message.ORDER_CONFIRMED));
            order.setStatus(Order.Status.CONFIRMED);
        } else {
            request.setAttribute("error", Message.get(Message.ORDER_NOT_CONFIRMED));
        }

        return RequestHelper.getInstance().getCommand("/page/admin/orders").execute(request, response);
    }
}
