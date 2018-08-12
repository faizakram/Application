package com.app.util.dto;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ResponseJsonSerializer extends StdSerializer<ResponseJson>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResponseJsonSerializer() {
        this(null);
    }
   
    public ResponseJsonSerializer(Class<ResponseJson> responseJson) {
        super(responseJson);
    }
 
    @Override
    public void serialize(
    		ResponseJson responseJson, JsonGenerator jgen, SerializerProvider provider) 
      throws IOException, JsonProcessingException {
        jgen.writeStartObject();
        jgen.writeStringField("responseCode", responseJson.getResponseCode());
        if(responseJson.getResponseDescription() != null)
        	jgen.writeStringField("responseDescription", responseJson.getResponseDescription());
        if(responseJson.getResponse() != null)
        	jgen.writeObjectField("response", responseJson.getResponse());
        
        jgen.writeEndObject();
    }

}
