package com.catering.service.response;

import java.io.Serializable;


import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Project   candle
 * @Author    Md. Afrail Hossain
 * @Since     jan 21, 2024
 * @version   1.0.0
 */

@Data
public class CommonResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private boolean status;
	
	private String message;
	
	private String messageBn;
	
	private Object data;


	public CommonResponse generateSuccessResponse(Object obj, String... message) {
		CommonResponse response = new CommonResponse();
		response.setStatus(true);
		response.setData(obj);
		if (message.length > 1 && message[0] != null && message[1] != null) {
			response.setMessage(message[0]);
			response.setMessageBn(message[1]);
		}
		return response;
	}

	public CommonResponse generateErrorResponse(Object obj, String... message) {
		CommonResponse response = new CommonResponse();
		response.setStatus(false);
		response.setData(obj);
		if (message.length > 1 && message[0] != null && message[1] != null) {
			response.setMessage(message[0]);
			response.setMessageBn(message[1]);
		}
		return response;
	}
	
	
	
	
}
