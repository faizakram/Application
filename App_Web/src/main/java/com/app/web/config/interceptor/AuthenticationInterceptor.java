package com.app.web.config.interceptor;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.app.token.service.TokenService;
import com.app.util.constant.CommonConstants;
import com.app.util.error.ErrorCodeHelper;
import com.app.util.error.response.ErrorInfo;
import com.app.util.error.response.ServiceException;

/**
 * Interceptor to authenticate user
 *
 */

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private ErrorCodeHelper errorCodeHelper;

	/**
	 * parse token to authenticate user
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @return true if user is authenticated
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = request.getHeader(CommonConstants.HEADER_TOKEN);
		Set<String> roles;
		if (handler instanceof HandlerMethod) {
			if (token == null) {
				ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(CommonConstants.E1009_ERROR_CODE,
						CommonConstants.E1009_ERROR_DESCRIPTION);
				throw new ServiceException(errorInfo, HttpStatus.UNAUTHORIZED);
			} else {

				Map<String, Object> userDetail = tokenService.parseUserToken(token);
				Long userId = (Long) userDetail.get(CommonConstants.USER_ID);
				if (userId != null && userId != 0) {
					roles = (Set<String>) userDetail.get(CommonConstants.ROLES);
					request.setAttribute(CommonConstants.ROLES, roles);
					request.setAttribute(CommonConstants.USER_EMAIL_TXT,
							userDetail.get(CommonConstants.USER_EMAIL_TXT));
					request.setAttribute(CommonConstants.USER_ID, userId);
					return true;
				}
			}
			return false;
		}
		return true;
	}
}
