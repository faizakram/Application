package com.app.web.config.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Provide CORS origin support to application
 *
 */
@Component
public class CORSInterceptor extends HandlerInterceptorAdapter {

	/**
	 * Set CORS properties in the header of response
	 * 
	 * @return true if properties are set
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");// you can validate here
		response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, HEAD, OPTIONS, PUT, DELETE");
		response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,
				"Content-Type, X-Requested-With,X-Auth-Token, accept,ticket, Origin,Access-Control-Request-Method, Access-Control-Request-Headers");
		response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,
				"Access-Control-Allow-Origin, Access-Control-Allow-Credentials");
		response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
		response.setHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600");

		return true;
	}
}
