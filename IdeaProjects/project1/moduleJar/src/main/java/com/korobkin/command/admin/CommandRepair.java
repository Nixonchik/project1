package com.korobkin.command.admin;

import com.korobkin.annotation.RequestMapper;
import com.korobkin.command.Command;
import com.korobkin.dao.DAOFactory;
import com.korobkin.dao.car.filters.CarFilter;
import com.korobkin.model.Car;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RequestMapper("/admin/repair")
public class CommandRepair implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        //Get list of all Cars
        List<Car> carList = DAOFactory.carDAO().findCars(new ArrayList<CarFilter>());

        request.setAttribute("cars", carList);

        return "admin/repair";
    }
}
