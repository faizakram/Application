package com.app.util.error.response;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

/**
 * 
 * Custom Service Exception class 
 *
 */
public class ServiceException extends RuntimeException implements Serializable{

	private static final long serialVersionUID = -6532700309112308482L;
	
    private final ErrorInfo errorInfo;
    
    private  HttpStatus httpStatus;

    /**
     * Constructor with ErrorInfo
     * @param errorInfo
     */
    public ServiceException(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

    /**
     * Constructor with error info and specified Http status
     * 
     * @param errorInfo
     * @param httpStatus
     */
    public ServiceException(ErrorInfo errorInfo, HttpStatus httpStatus) {
        this.errorInfo = errorInfo;
        this.httpStatus = httpStatus;
    }

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    /**
     * @return The httpStatus
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
