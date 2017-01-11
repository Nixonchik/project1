package com.korobkin.command.user;

import com.korobkin.annotation.RequestMapper;
import com.korobkin.command.Command;
import com.korobkin.controller.RequestHelper;
import com.korobkin.dao.DAOFactory;
import com.korobkin.model.Client;
import com.korobkin.model.Order;
import com.korobkin.properties.Message;
import com.korobkin.util.ConfirmOrderMap;
import com.korobkin.util.EmailService;
import com.korobkin.util.ReservedDatesOfCar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapper("/confirm_order")
public class CommandConfirmOrder implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        if (order == null) {
            request.setAttribute("error", Message.get(Message.BAD_SESSION_TO_CONFIRM_ORDER));
        }
        Client client = order.getClient();
        int clientId = DAOFactory.clientDAO().addClient(client);
        client.setId(clientId);

        synchronized (ReservedDatesOfCar.class) {
            if (DAOFactory.orderDAO().isAvailableDateForCar(order.getStart(), order.getEnd(), order.getCar().getId())
                    && ReservedDatesOfCar.isAvailableDate(order)) {
                ReservedDatesOfCar.removeDatesOfCar(order.getCar().getId(), order);
                int addingResult = DAOFactory.orderDAO().addOrder(order);

                if (addingResult == -1) {
                    request.setAttribute("error", Message.get(Message.SQL_EXCEPTION));
                } else {
                    request.setAttribute("message", Message.get(Message.ORDER_SUCCESSFULLY_ADDED));
                }

                String code = ConfirmOrderMap.generateCode(order.getId());
                EmailService.sendEmail(order, code);

            } else {

                //Date is reserved
                request.setAttribute("error", Message.get(Message.DATE_IS_BLOCKED));
            }
        }

        return RequestHelper.getInstance().getCommand("/page/catalog").execute(request, response);
    }
}
