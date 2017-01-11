package com.korobkin.filter;


import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * This filter changes codding of requests and response to UTF-8
 */
public class CodingFilter implements Filter {
	private final Logger logger = Logger.getLogger("CodingFilter");
	private static final String CODING = "UTF-8";

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

	// Filter changes coding to UTF-8
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		logger.debug("Request - " + ((HttpServletRequest) request).getRequestURI());
		if (request.getCharacterEncoding() != CODING) {
			request.setCharacterEncoding(CODING);
			logger.trace("Request coding changed to UTF-8");
		}
		if (response.getCharacterEncoding()!=CODING){
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			logger.trace("Response coding changed to UTF-8");
		}
			chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
