package com.korobkin.command.user;

import com.korobkin.annotation.RequestMapper;
import com.korobkin.command.Command;
import com.korobkin.controller.RequestHelper;
import com.korobkin.dao.DAOFactory;
import com.korobkin.model.Car;
import com.korobkin.model.Order;
import com.korobkin.util.ReservedDatesOfCar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * Created by Java Developer on 05.12.2015.
 */
@RequestMapper("/car")
public class CommandCar implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String carId = request.getParameter("id");
        if (carId == null) {
            return RequestHelper.getInstance().getCommand("/page/catalog").execute(request, response);
        }
        int idCar = Integer.parseInt(carId);
        Car car = DAOFactory.carDAO().getCar(idCar);
        Set<Order> reservedDates = DAOFactory.orderDAO().getBlockedDates(idCar);
        reservedDates.addAll(ReservedDatesOfCar.getDatesOfCar(idCar));

        request.setAttribute("reservedDates", reservedDates);
        request.setAttribute("car", car);
        return "user/car";
    }

}
