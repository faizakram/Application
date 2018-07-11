package com.app.util.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.app.util.constant.CommonConstants;
import com.app.util.reader.PropertyReader;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Component
@JsonInclude(Include.NON_NULL)
@JsonSerialize(using = ResponseJsonSerializer.class)
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ResponseJson{


	@Autowired
	@Qualifier(CommonConstants.SUCCESS_PROPERTY)
	private PropertyReader successProperty;

	private String responseCode;

	private String responseDescription;

	private Object response;
	
	/*public ResponseJson() {
		successProperty = ApplicationContextUtils.getApplicationContext()
				.getBean(CommonConstants.SUCCESS_PROPERTY, PropertyReader.class);
		responseCode = successProperty.getProperty(CommonConstants.S0001_SUCCESS_CODE);
	}*/


	public String getResponseCode() {
		if (this.responseCode == null) {
			responseCode = successProperty.getProperty(CommonConstants.S0001_SUCCESS_CODE);
		}
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = successProperty.getProperty(responseCode);
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = successProperty.getProperty(responseDescription);
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

}
