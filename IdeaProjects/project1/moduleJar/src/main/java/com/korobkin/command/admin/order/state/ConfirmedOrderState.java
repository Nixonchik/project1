package com.korobkin.command.admin.order.state;

import com.korobkin.dao.DAOFactory;
import com.korobkin.model.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ConfirmedOrderState implements OrderState {

    /**
     * This method starts when client want to get car for rent.
     * Change state to CAR_WAS_GOT
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return
     */
    @Override
    public String changeState(HttpServletRequest request, HttpServletResponse response) {

        Order order = (Order) request.getAttribute("order");
        List<String> defectList = DAOFactory.defectDAO().getDescriptionsOfNotRepairedDefectsOfCar(order.getCar().getId());

        request.setAttribute("defectList", defectList);

        return "admin/give_car";
    }
}
