package com.korobkin.command.admin;

import com.korobkin.annotation.RequestMapper;
import com.korobkin.command.Command;
import com.korobkin.controller.RequestHelper;
import com.korobkin.dao.DAOFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapper("/admin/repair_defect")
public class CommandRepairDefect implements Command {
    private static final Logger logger = Logger.getLogger(CommandRepairDefect.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String defect_id = request.getParameter("defect_id");
        String price = request.getParameter("price");

        int defectId;
        try {
            defectId = Integer.parseInt(defect_id);
        } catch (NumberFormatException e) {
            logger.error(e);
            return RequestHelper.getInstance().getCommand("/page/admin/orders").execute(request, response);
        }

        DAOFactory.defectDAO().repairDefect(defectId, price);
        return RequestHelper.getInstance().getCommand("/page/admin/repair_car").execute(request, response);
    }
}
