package com.korobkin.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface Command {

	/**
	 * This method returns path of jsp page without "jsp/"* and *".jsp"
	 * This method can use request container and update response container like Servlet does.
	 * This method is not thread-safe so it's bad practice to use class fields. Good idea
	 * to use only local variables.
	 * @param request HttpServletRequest from client.
	 * @param response HttpServletResponse to client.
	 * @return path to jsp-page like "user/name-of-page"
	 */
	public String execute(HttpServletRequest request, HttpServletResponse response);
}
