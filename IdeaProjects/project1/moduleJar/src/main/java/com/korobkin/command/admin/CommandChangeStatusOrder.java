package com.korobkin.command.admin;

import com.korobkin.annotation.RequestMapper;
import com.korobkin.command.Command;
import com.korobkin.command.admin.order.state.OrderState;
import com.korobkin.controller.RequestHelper;
import com.korobkin.dao.DAOFactory;
import com.korobkin.model.Order;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapper("/admin/change_order_status")
public class CommandChangeStatusOrder implements Command {
    private static final Logger logger = Logger.getLogger(CommandChangeStatusOrder.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String idOrder = request.getParameter("order_id");
        int orderId = Integer.parseInt(idOrder);

        Order order = DAOFactory.orderDAO().getOrder(orderId);
        if (order == null) {
            return RequestHelper.getInstance().getCommand("/page/admin/orders").execute(request, response);
        }

        OrderState state = order.getState();

        request.setAttribute("order", order);
        return state.changeState(request, response);
    }
}
