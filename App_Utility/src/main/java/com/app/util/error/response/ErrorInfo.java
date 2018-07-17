package com.app.util.error.response;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;



/**
 *
 *ErrorInfo class having information about custom exceptions
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@JsonSerialize(using = ErrorInfoSerializer.class)
@JsonInclude(value = Include.NON_NULL)
public class ErrorInfo implements Serializable{
   
	private static final long serialVersionUID = 67549136213141491L;

	private String responseCode;

    private String responseDescription;
    
    private Integer referenceNumber;
    
    private ErrorMessage errorMessage;
 
    public Integer getReferenceNumber() {
        return referenceNumber;
    }

    
    public void setReferenceNumber(Integer referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public ErrorMessage getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}


	public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }


	@Override
	public String toString() {
		return "ErrorInfo [responseCode=" + responseCode + ", responseDescription=" + responseDescription
				+ ", referenceNumber=" + referenceNumber + ", errorMessage=" + errorMessage + "]";
	}   
    

}
