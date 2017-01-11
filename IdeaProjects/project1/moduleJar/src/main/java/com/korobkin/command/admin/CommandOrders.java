package com.korobkin.command.admin;

import com.korobkin.annotation.RequestMapper;
import com.korobkin.command.Command;
import com.korobkin.dao.DAOFactory;
import com.korobkin.dao.order.filters.AdminFilter;
import com.korobkin.dao.order.filters.CarFilter;
import com.korobkin.dao.order.filters.NoAdminFilter;
import com.korobkin.dao.order.filters.OrderFilter;
import com.korobkin.model.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Java Developer on 05.12.2015.
 */
@RequestMapper("/admin/orders")
public class CommandOrders implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<OrderFilter> orderFilters = new ArrayList<>();

        // Add Admin Filter
        String admin = request.getParameter("admin");
        if (admin == null) admin = (String) request.getSession().getAttribute("admin");
        OrderFilter orderFilter = null;
        if (!"null".equals(admin)) orderFilter = new AdminFilter(admin);
        else orderFilter = new NoAdminFilter();
        orderFilters.add(orderFilter);

        // Add Car Filter
        String carId = request.getParameter("car");
        if (carId != null) {
            OrderFilter orderFilter1 = new CarFilter(Integer.parseInt(carId));
            orderFilters.add(orderFilter1);
        }

        List<Order> orderList = DAOFactory.orderDAO().getOrdersWithoutClientAndCar(orderFilters);

        request.setAttribute("orderList", orderList);
        request.setAttribute("admin", admin);
        return "admin/orders";
    }
}
