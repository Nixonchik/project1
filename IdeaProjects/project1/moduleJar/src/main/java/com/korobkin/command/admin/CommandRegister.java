package com.korobkin.command.admin;

import com.korobkin.annotation.RequestMapper;
import com.korobkin.command.Command;
import com.korobkin.dao.AdminDAO;
import com.korobkin.dao.DAOFactory;
import com.korobkin.model.Admin;
import com.korobkin.properties.Message;
import com.korobkin.validation.CreateAdminValidation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Java Developer on 05.12.2015.
 */
@RequestMapper("/admin/register")
public class CommandRegister implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String login = request.getParameter("login");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        synchronized (AdminDAO.class) {
            CreateAdminValidation.Result result = CreateAdminValidation.isValid(login, password1, password2, firstName, lastName);
            if (!result.noData) {
                if (result.valid) {
                    Admin admin = new Admin();
                    admin.setLogin(login);
                    admin.setPassword(password1);
                    admin.setFirstName(firstName);
                    admin.setLastName(lastName);
                    int res = DAOFactory.adminDAO().addAdmin(admin);
                    if (res == 1) {
                        String message = Message.get(Message.ADMIN_SUCCESSFULLY_ADDED);
                        request.setAttribute("message", message);
                    } else if (res == -1) {
                        String error = Message.get(Message.SQL_EXCEPTION);
                        request.setAttribute("error", error);
                    } else if (res == 0) {
                        String error = Message.get(Message.ADMIN_IS_EXIST);
                        request.setAttribute("error", error);
                    }
                } else {
                    request.setAttribute("error", result.error);
                }
            }
        }

        return "admin/register";
    }
}
