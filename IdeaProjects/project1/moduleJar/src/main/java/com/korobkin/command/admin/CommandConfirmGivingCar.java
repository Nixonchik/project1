package com.korobkin.command.admin;

import com.korobkin.annotation.RequestMapper;
import com.korobkin.command.Command;
import com.korobkin.controller.RequestHelper;
import com.korobkin.dao.DAOFactory;
import com.korobkin.model.Order;
import com.korobkin.properties.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapper("/admin/confirm_giving_car")
public class CommandConfirmGivingCar implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String idOrder = request.getParameter("orderId");

        if (idOrder == null) return RequestHelper.getInstance().getCommand("/page/admin/orders").execute(request, response);

        int orderId = Integer.parseInt(idOrder);

        if (DAOFactory.orderDAO().changeOrderStatus(orderId, Order.Status.CAR_WAS_GOT)) {
            DAOFactory.orderDAO().changeAdminOfOrder(orderId, (String) request.getSession().getAttribute("admin"));
            request.setAttribute("message", Message.get(Message.CAR_WAS_GIVEN));
        } else {
            request.setAttribute("error", Message.get(Message.SQL_EXCEPTION));
        }

        return RequestHelper.getInstance().getCommand("/page/admin/orders").execute(request, response);
    }
}
