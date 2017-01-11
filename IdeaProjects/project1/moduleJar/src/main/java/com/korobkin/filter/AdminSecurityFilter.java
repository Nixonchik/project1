package com.korobkin.filter;

import com.korobkin.dao.DAOFactory;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * This filter for all requests with Admin restrictions.
 * This filter checks is session authenticate.
 * If request has parameters "username" and "password" this filter tries to authenticate user and tries do next chain.
 * Otherwise it redirects to login page.
 *
 */
public class AdminSecurityFilter implements Filter {
    private static final Logger logger = Logger.getLogger("AdminSecurityFilter");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * Check authentication of user by request.
     * If the request contains "username" and "password" parameters it trying to give authentication to session.
     * If session hasn't got permit yet it redirect request to login page.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        boolean isAdmin = isAdmin(httpRequest);
        logger.debug("isAdmin - " + isAdmin);


        if (!isAdmin) {
            logger.debug("Redirect to login page.");

            // Adding current request to request Attribute to redirect there after successful authentication
            httpRequest.setAttribute("redirect", httpRequest.getRequestURI());
            Map<String, String> params = httpRequest.getParameterMap();
            httpRequest.setAttribute("params", params);


            // login.jsp uses this redirect Attribute to send form to current URL with current parameters
            request.getRequestDispatcher("/page/login").forward(request, response);
        } else {

            // In case of session consist authentication info do nothing
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

    /**
     * Check session authentication and give it in case of correct username and password
     *
     * @param request HttpRequest request
     * @return true if authenticated or false otherwise
     */
    boolean isAdmin(HttpServletRequest request) {
        // Authentication information stores in session container
        // So if session container has got attribute "admin", returns true.
        // No matter what value in session attribute admin, because all admins has the same permits.
        HttpSession session = request.getSession(false);
        if (session == null) return false;

        String admin = (String) session.getAttribute("admin");

        // If this request contains of username and password parameters there is ability to authenticate user-session as admin-session.
        // So we check this parameters if session hasn't been authenticated yet.
        if (admin == null) {
            String username = request.getParameter("username");
            if (username == null) return false;

            String password = request.getParameter("password");
            if (password == null) return false;

            logger.debug("Username: " + username + ", password: " + password);

            if (DAOFactory.adminDAO().isAdmin(username, password)) {
                session.setAttribute("admin", username);
            } else {
                return false;
            }
        }

        // Returns true if session contains admin attribute
        // or user has entered correct username and password.
        return true;
    }
}
