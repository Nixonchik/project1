package com.korobkin.command.user;

import com.korobkin.annotation.RequestMapper;
import com.korobkin.command.Command;
import com.korobkin.controller.RequestHelper;
import com.korobkin.dao.DAOFactory;
import com.korobkin.model.Order;
import com.korobkin.properties.Message;
import com.korobkin.util.ConfirmOrderMap;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapper("/confirm_order_from_email")
public class CommandConfirmOrderFromEmailHandler implements Command {
    private static final Logger logger = Logger.getLogger(CommandConfirmOrderFromEmailHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String idOrder = request.getParameter("order_id");
        String code = request.getParameter("code");
        String confirm = request.getParameter("confirm");


        try {
            int orderId = Integer.parseInt(idOrder);
            if (ConfirmOrderMap.isValidCode(orderId, code)) {
                if ("true".equals(confirm)) {
                    if (DAOFactory.orderDAO().changeOrderStatus(orderId, Order.Status.CONFIRMED)) {
                        request.setAttribute("message", Message.get(Message.ORDER_CONFIRMED));
                    } else {
                        request.setAttribute("error", Message.get(Message.BAD_DATA));
                    }
                }
                if ("false".equals(confirm)) {
                    if (DAOFactory.orderDAO().deleteOrder(orderId)) {
                        request.setAttribute("message", Message.get(Message.ORDER_DELETED));
                    } else {
                        request.setAttribute("error", Message.get(Message.BAD_DATA));
                    }
                }
            } else {
                request.setAttribute("error", Message.get(Message.BAD_DATA));
            }

        } catch (NumberFormatException|NullPointerException e) {
            logger.error(e);
            request.setAttribute("error", Message.get(Message.BAD_DATA));
        }
        return RequestHelper.getInstance().getCommand("/page/catalog").execute(request, response);

    }
}
