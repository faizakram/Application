package com.app.util.error.response;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ErrorInfoSerializer extends StdSerializer<ErrorInfo>{

	private static final long serialVersionUID = 1L;

	public ErrorInfoSerializer() {
        this(null);
    }
   
    public ErrorInfoSerializer(Class<ErrorInfo> responseJson) {
        super(responseJson);
    }
 
    @Override
    public void serialize(
    		ErrorInfo errorInfo, JsonGenerator jgen, SerializerProvider provider) 
      throws IOException, JsonProcessingException {
        jgen.writeStartObject();
        jgen.writeStringField("errorCode", errorInfo.getResponseCode());        
        if(errorInfo.getResponseDescription() != null)
        	jgen.writeStringField("errorDescription", errorInfo.getResponseDescription());
        if(errorInfo.getReferenceNumber() != null)
        	jgen.writeObjectField("error", errorInfo.getReferenceNumber());
        if(errorInfo.getErrorMessage() != null)
        	jgen.writeObjectField("errorMessage", errorInfo.getErrorMessage());
        
        jgen.writeEndObject();
    }

}
