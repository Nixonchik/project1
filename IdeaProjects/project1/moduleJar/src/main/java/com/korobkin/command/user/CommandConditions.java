package com.korobkin.command.user;

import com.korobkin.annotation.RequestMapper;
import com.korobkin.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapper("/conditions")
public class CommandConditions implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "conditions";
    }
}
