package com.korobkin.command.user;

import com.korobkin.annotation.RequestMapper;
import com.korobkin.command.Command;
import com.korobkin.controller.RequestHelper;
import com.korobkin.dao.DAOFactory;
import com.korobkin.model.Car;
import com.korobkin.model.Order;
import com.korobkin.util.CalendarUtil;
import com.korobkin.util.ReservedDatesOfCar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Set;

/**
 * Created by Java Developer on 05.12.2015.
 */
@RequestMapper("/create_order")
public class CommandCreateOrder implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String car_id = request.getParameter("carId");
        if (car_id == null) {
            return RequestHelper.getInstance().getCommand("/page/catalog").execute(request, response);
        }
        int carId = Integer.parseInt(car_id);

        // When we are going back to this page from confirm page
        // We are going to remove reserve.
        if (request.getParameter("cancel") != null) {
            String date_from = request.getParameter("date_from");
            Calendar from = CalendarUtil.getCalendar(date_from);
            Order order = new Order();
            order.setCar(new Car(carId));
            order.setStart(from);
            ReservedDatesOfCar.removeDatesOfCar(carId, order);
        }

        Set<Order> reservedDates = DAOFactory.orderDAO().getBlockedDates(carId);
        reservedDates.addAll(ReservedDatesOfCar.getDatesOfCar(carId));

        request.setAttribute("reservedDates", reservedDates);
        request.setAttribute("carId", carId);

        return "user/create_order";
    }
}
