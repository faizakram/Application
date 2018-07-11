package com.app.web.config.interceptor;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.app.util.constant.CommonConstants;
import com.app.util.custom.annotation.AllowedRoles;
import com.app.util.error.ErrorCodeHelper;
import com.app.util.error.response.ErrorInfo;
import com.app.util.error.response.ServiceException;

/**
 * Authorization interceptor - for checking user access rights
 *
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);


    @Autowired
    @Qualifier(CommonConstants.ERROR_CODE_HELPER)
    private ErrorCodeHelper errorCodeHelper;

    /**
     * Checks user access rights based on role in request object
     * 
     * @param request
     * @param response
     * @param handler
     * @return true if user has access rights
     */
    @SuppressWarnings("unchecked")
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {

        Set<String> userRoles;
        boolean isAuthorized = false;
        String httpMethod = request.getMethod();

        Set<String> rolesSet = new HashSet<>();

        if (httpMethod.equals(HttpMethod.OPTIONS.toString())) {
            return true;
        }

        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;

            if (method.getMethod().isAnnotationPresent(AllowedRoles.class)) {

                AllowedRoles req = method.getMethodAnnotation(AllowedRoles.class);
                rolesSet.addAll(Arrays.asList(req.values()));
            }

            if (method.getMethod().getDeclaringClass().isAnnotationPresent(AllowedRoles.class)) {

                AllowedRoles req = method.getMethod().getDeclaringClass().getAnnotation(AllowedRoles.class);
                rolesSet.addAll(Arrays.asList(req.values()));
            }
        }
        
        if(rolesSet.isEmpty() || rolesSet.contains(CommonConstants.ALLOW_ROLE_ALL))
        	return true;

 
        try {
            String token = request.getHeader(CommonConstants.HEADER_TOKEN);
            if (token != null) {
            	userRoles = (Set<String>)request.getAttribute(CommonConstants.ROLES);
            	isAuthorized = !Collections.disjoint(rolesSet, userRoles);
            			//;rolesSet.contains(userRoles);
            	
            //	for(String roles : rolesSet){
  
            	//}

            }

        }
        catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                logger.warn("token has been expired");

                ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(CommonConstants.E1001_ERROR_CODE,
                    CommonConstants.E1001_ERROR_DESCRIPTION);
                throw new ServiceException(errorInfo);

            }

            logger.error("Unable to load user permissions", e);
        }
        catch (Exception e) {
            logger.error("Unable to load user permissions", e);
        }

        // if user has access rights or not
        if (!isAuthorized) {
            logger.warn("User is not authorized for this operation");

            ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(CommonConstants.E1010_ERROR_CODE,
                CommonConstants.E1010_ERROR_DESCRIPTION);
            throw new ServiceException(errorInfo);

        }
        else {
            logger.info("User is authorized for this operation");
        }

        return true;
    }
}