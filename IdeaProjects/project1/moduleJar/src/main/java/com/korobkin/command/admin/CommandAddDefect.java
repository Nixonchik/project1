package com.korobkin.command.admin;

import com.korobkin.annotation.RequestMapper;
import com.korobkin.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapper("/admin/add_defect")
public class CommandAddDefect implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "admin/add_defect";
    }
}
