package com.korobkin.command.admin;

import com.korobkin.annotation.RequestMapper;
import com.korobkin.command.Command;
import com.korobkin.controller.RequestHelper;
import com.korobkin.dao.DAOFactory;
import com.korobkin.model.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapper("/admin/order")
public class CommandOrder implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String idOrder = request.getParameter("orderId");

        if (idOrder == null) {
            return RequestHelper.getInstance().getCommand("/page/admin/orders").execute(request, response);
        }

        int orderId = Integer.parseInt(idOrder);

        Order order = DAOFactory.orderDAO().getOrder(orderId);

        request.setAttribute("order", order);

        return "admin/order";
    }
}
