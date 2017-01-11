package com.korobkin.command.admin;

import com.korobkin.annotation.RequestMapper;
import com.korobkin.command.Command;
import com.korobkin.controller.RequestHelper;
import com.korobkin.dao.DAOFactory;
import com.korobkin.model.Car;
import com.korobkin.model.Client;
import com.korobkin.model.Order;
import com.korobkin.properties.Message;
import com.korobkin.util.CalendarUtil;
import com.korobkin.util.ReservedDatesOfCar;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;

@RequestMapper("/admin/edit_order_handler")
public class CommandEditOrderHandler implements Command{
    private static final Logger logger = Logger.getLogger(CommandEditOrderHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            if ("Cancel".equals(request.getParameter("submit"))) {
                return RequestHelper.getInstance().getCommand("/page/admin/orders").execute(request, response);
            }
            HttpSession session = request.getSession();
            Order actualOrder = (Order) session.getAttribute("orderForEdit");

            int orderId = Integer.parseInt(request.getParameter("order_id"));
            int clientId = Integer.parseInt(request.getParameter("client_id"));
            int carId = Integer.parseInt(request.getParameter("car_id"));

            String firstName = request.getParameter("firstname");
            String lastName = request.getParameter("lastname");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");

            String adminLogin = request.getParameter("admin_login");
            if ("".equals(adminLogin)) {
                adminLogin = (String) session.getAttribute("admin");
            }

            Calendar startDate = null;
            try {
                startDate = CalendarUtil.getCalendar(request.getParameter("start_date"));
            } catch (NumberFormatException e) {
                startDate = actualOrder.getStart();
            }

            Calendar endDate = null;
            try {
                endDate = CalendarUtil.getCalendar(request.getParameter("end_date"));
            } catch (NumberFormatException e) {
                endDate = actualOrder.getEnd();
            }

            String details = request.getParameter("details");

            boolean childChair = request.getParameter("child_chair") != null;
            boolean gps = request.getParameter("gps") != null;


            Client client = new Client();
            client.setId(clientId);
            client.setFirstName(firstName);
            client.setLastName(lastName);
            client.setPhone(phone);
            client.setEmail(email);

            String message = "";
            String error = "";
            if (!client.equals(actualOrder.getClient())) {
                if (DAOFactory.clientDAO().updateClient(client)) {
                    message = "Client has been changed successfully. \r\n";
                } else {
                    error = Message.get(Message.SQL_EXCEPTION);
                }

            }

            Order.Builder builder = Order.newBuilder();
            Order newOrder = builder
                    .setId(orderId)
                    .setClient(new Client(clientId))
                    .setAdmin(adminLogin)
                    .setCar(new Car(carId))
                    .setStart(startDate)
                    .setEnd(endDate)
                    .setDetails(details)
                    .setChildChair(childChair)
                    .setGps(gps)
                    .build();

            if(!newOrder.equals(actualOrder)) {
                synchronized (ReservedDatesOfCar.class) {
                    if (DAOFactory.orderDAO().isAvailableDateToEditOrder(newOrder.getStart(), newOrder.getEnd(), newOrder.getCar().getId(), newOrder.getId())
                            && ReservedDatesOfCar.isAvailableDate(newOrder)) {
                        if (DAOFactory.orderDAO().updateOrder(newOrder)) {
                            message += "Order has been changed successfully.";
                        } else {
                            error = Message.get(Message.SQL_EXCEPTION);
                        }
                    } else {
                        error = Message.get(Message.DATE_IS_BLOCKED);
                    }
                }
            }

            if (!message.equals("")) {
                request.setAttribute("message", message);
            }
            if (!error.equals("")) {
                request.setAttribute("error", error);
            }

        } catch (NullPointerException e) {
            logger.error(e);
        }

        return RequestHelper.getInstance().getCommand("/page/admin/orders").execute(request, response);
    }
}