package com.korobkin.command.user;

import com.korobkin.annotation.RequestMapper;
import com.korobkin.command.Command;
import com.korobkin.dao.DAOFactory;
import com.korobkin.dao.car.filters.AutomatFilter;
import com.korobkin.dao.car.filters.CarFilter;
import com.korobkin.dao.car.filters.EngineFilter;
import com.korobkin.dao.car.filters.YearFilter;
import com.korobkin.model.Car;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Java Developer on 04.12.2015.
 */
@RequestMapper("/catalog")
public class CommandCatalog implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<CarFilter> filterList = new LinkedList<>();

        //Add ENGINE FILTER
        String engineFrom = request.getParameter("enginefrom");
        if (engineFrom != null && !engineFrom.equals("")) {
            String engineTo = request.getParameter("engineto");
            double from = Double.parseDouble(engineFrom);
            double to = Double.parseDouble(engineTo);
            CarFilter filter = new EngineFilter(from, to);
            filterList.add(filter);
        }

        //Add YEAR FILTER
        String yearFrom = request.getParameter("yearfrom");
        if (yearFrom != null && !yearFrom.equals("")) {
            String yearTo = request.getParameter("yearto");
            int from = Integer.parseInt(yearFrom);
            int to = Integer.parseInt(yearTo);
            CarFilter filter = new YearFilter(from, to);
            filterList.add(filter);
        }

        //Add AUTOMAT FILTER
        String automat = request.getParameter("automat");
        if (automat != null && !automat.equals("")) {
            CarFilter filter = null;
            if (automat.equals("0")) filter = new AutomatFilter(Car.Transmission.MANUAL);
            if (automat.equals("1")) filter = new AutomatFilter(Car.Transmission.AUTOMAT);
            if (filter != null) filterList.add(filter);
        }

        List<Car> cars = DAOFactory.carDAO().findCars(filterList);
        request.setAttribute("cars", cars);

        return "user/catalog";
    }




}

