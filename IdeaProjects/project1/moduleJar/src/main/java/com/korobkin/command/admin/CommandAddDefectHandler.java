package com.korobkin.command.admin;

import com.korobkin.annotation.RequestMapper;
import com.korobkin.command.Command;
import com.korobkin.controller.RequestHelper;
import com.korobkin.dao.DAOFactory;
import com.korobkin.model.Defect;
import com.korobkin.model.Order;
import com.korobkin.properties.Message;
import com.korobkin.util.CalendarUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

@RequestMapper("/admin/add_defect_handler")
public class CommandAddDefectHandler implements Command {
    private static final Logger logger = Logger.getLogger(CommandAddDefectHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String submit = request.getParameter("submit");
        if ("Cancel".equals(submit)) {
            return RequestHelper.getInstance().getCommand("/page/admin/change_order_status").execute(request, response);
        }
        String orderIdString = request.getParameter("order_id");
        String description = request.getParameter("description");
        String priceString = request.getParameter("price");
        String occurrenceDateString = request.getParameter("date");

        int orderId;
        float price;
        Calendar ocurrenceDate;
        try {
            orderId = Integer.parseInt(orderIdString);
            price = Float.parseFloat(priceString.replace(",", "."));
            ocurrenceDate = CalendarUtil.getCalendar(occurrenceDateString);
            if (ocurrenceDate.after(Calendar.getInstance())) throw new Exception("Occurrence Date cannot be in future.");
        } catch (Exception e) {
            logger.error(e);
            request.setAttribute("error", Message.get(Message.BAD_DATA));
            return RequestHelper.getInstance().getCommand("/page/admin/orders").execute(request, response);
        }

        Order order = DAOFactory.orderDAO().getOrder(orderId);
        boolean isPaid = order.getStatusInt()==1; //If client gets car, there is no reason to pay for it/
        Defect.Builder defectBuilder = Defect.getBuilder();
        defectBuilder
                .setCarId(order.getCar().getId())
                .setClientId(order.getClient().getId())
                .setDescription(description)
                .setDate(ocurrenceDate)
                .setPriceForClient(price)
                .setPaid(isPaid);
        Defect newDefect = defectBuilder.build();

        // Add defect to DateBase
        DAOFactory.defectDAO().addDefect(newDefect);

        return RequestHelper.getInstance().getCommand("/page/admin/change_order_status").execute(request, response);
    }
}
