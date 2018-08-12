package com.app.web.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.app.util.constant.CommonConstants;
import com.app.util.error.ErrorCodeHelper;
import com.app.util.error.response.ErrorInfo;
import com.app.util.error.response.ErrorMessage;
import com.app.util.error.response.ServiceException;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

	@Autowired
	private ErrorCodeHelper errorCodeHelper;

	/**
	 * 
	 * Handles cases when required request parameters are missing
	 * 
	 * @param request
	 * @param ex
	 * @param headers
	 * @param status
	 * 
	 * @return
	 */
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpStatus httpStatus = HttpStatus.OK;
		ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(CommonConstants.E1000_ERROR_CODE,
				CommonConstants.E1000_ERROR_DESCRIPTION);
		return handleExceptionInternal(ex, errorInfo, headers, httpStatus, request);

	}

	/**
	 * Handles unsupported Media type error
	 * 
	 * @param request
	 * @param ex
	 * @param headers
	 * @param status
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpStatus httpStatus = HttpStatus.OK;
		ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(CommonConstants.E1001_ERROR_CODE,
				CommonConstants.E1001_ERROR_DESCRIPTION);
		return handleExceptionInternal(ex, errorInfo, headers, httpStatus, request);
	}

	/**
	 * 
	 * Handles cases when invalid input parameter is passed like when input json
	 * format is incorrect
	 * 
	 * @param request
	 * @param ex
	 * @param headers
	 * @param status
	 * 
	 * @return
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpStatus httpStatus = HttpStatus.OK;
		ErrorInfo errorInfo = errorCodeHelper.getErrorInfo(CommonConstants.E1002_ERROR_CODE,
				CommonConstants.E1002_ERROR_DESCRIPTION);
		return handleExceptionInternal(ex, errorInfo, headers, httpStatus, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpStatus httpStatus = HttpStatus.OK;		
		
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		 
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
        
        List<String> errors = new ArrayList<>(fieldErrors.size() + globalErrors.size());
        
        String error;
        
        for (FieldError fieldError : fieldErrors) {
            error = fieldError.getField() + ": " + fieldError.getDefaultMessage();
            errors.add(error);
        }
        for (ObjectError objectError : globalErrors) {
            error = objectError.getObjectName() + ": " + objectError.getDefaultMessage();
            errors.add(error);
        }
        
        ErrorMessage errorMessage = new ErrorMessage(errors);
        
        ErrorInfo errorInfo = errorCodeHelper.getError(CommonConstants.E1002_ERROR_CODE,
        		errorMessage);
        
		return handleExceptionInternal(ex, errorInfo, headers, httpStatus, request);
	}

	@ExceptionHandler({ ServiceException.class })
	protected ResponseEntity<Object> handleServiceException(RuntimeException e, WebRequest request) {
		ServiceException exception = (ServiceException) e;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpStatus httpStatus = HttpStatus.OK;
		
		/*ServletWebRequest req = (ServletWebRequest) request;
		String uri = req.getRequest().getRequestURI();*/
		
		return handleExceptionInternal(exception, exception.getErrorInfo(), headers, httpStatus, request);
	}

}
