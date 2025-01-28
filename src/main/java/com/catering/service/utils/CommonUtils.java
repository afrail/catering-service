package com.catering.service.utils;


import com.catering.service.common.BaseEntity;
import com.catering.service.response.CommonResponse;
import com.catering.service.response.MessageConstants;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Component
@AllArgsConstructor
public class CommonUtils implements MessageConstants {

	
	//================================== *** ==================================
	//									Server Message
	//================================== *** ==================================
	
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

    public CommonResponse generateErrorResponse(Exception e) {
		CommonResponse response = new CommonResponse();
		response.setStatus(false);
		String msgType = getMessageType(e.getMessage());
		if(msgType.equals("uk") || msgType.equals("re")) {
			response.setMessage(DATA_ALRADY_EXISTS_MESSAGE);
			response.setMessageBn(DATA_ALRADY_EXISTS_MESSAGE_BN);
		}else if(msgType.equals("fk")) {
			response.setMessage(CHILD_RECORD_FOUND);
			response.setMessageBn(CHILD_RECORD_FOUND_BN);
		}else {
			//response.setMessage(e.getMessage());
			response.setMessage("Failed");
		}
		
//		e.printStackTrace();
		
//		System.out.println(e.getMessage());
		return response;
	}

    //================================== *** ==================================
	//								Entity User
	//================================== *** ==================================
	
	public void setEntryUserInfo(Object obj) {
		BaseEntity entity = (BaseEntity) obj;
		

	}

	/**
	 * first object is current entity from api
	 * second object is database entity
	 * @param obj
	 * @param obj2
	 */
    public void setUpdateUserInfo(Object obj, Object obj2) {
		BaseEntity entity = (BaseEntity) obj;
		BaseEntity data = (BaseEntity) obj2;
		
		/* set previous entry User Info */

	}

	public void setUpdateUserInfoAct(Object obj, Object obj2) {
		BaseEntity entity = (BaseEntity) obj;
		BaseEntity data = (BaseEntity) obj2;

		/* set previous entry User Info */
	}

    public PageRequest getPageRequest(int page, int size) {
//      PageRequest.of(page, size, Sort.by("price").descending().and(Sort.by("name")));
//      PageRequest pageRequest = PageRequest.of(page, size, Sort.by("entryDate").descending());

		PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id").descending());
		return pageRequest;
	}

	//================================== *** ==================================
	//									Helper
	//================================== *** ==================================
	
	public String commonCode(String str, String lastNumber) {
		return str.concat(lastNumber);
	}

    private String getMessageType(String message) {
    	System.out.println("12 > " + message);
//    	System.out.println("13 > " + message.substring(52,54));
		if(message != null && message.length() > 55) {
			return message.substring(52,54);
		}
		return "";
	}
	
	public String getRendom4Digit() {
		Random random = new Random();
		return String.format("%04d", random.nextInt(10000));
	}
	
	public String generateCode(String prefix, Integer maxId, Integer totalDigit) {
		String padding = String.format("%0"+totalDigit+"d" , maxId);
		return prefix.concat(padding);
	}
	
	public MenuItem getCommonModule() {
		MenuItem module = new MenuItem();
        return module;
	}
	
	//================================== *** ==================================
	//									Date Helper
	//================================== *** ==================================
	
	public String formatSqlDate(Date sqlDate) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
		return dateFormat.format(sqlDate);  
	}
	
	public String formatSqlTime(String sqlTime) {
		try {
			DateFormat sqlTimeFormat = new SimpleDateFormat("HH:mm");
			Date sTime = sqlTimeFormat.parse(sqlTime);
			DateFormat formTimeFormat = new SimpleDateFormat("hh:mm a");
			return formTimeFormat.format(sTime);
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}  
		
	}

	public Date addDay(Date date, int numberOfDays) {
		Calendar dateObj  = Calendar.getInstance();
		dateObj.setTime(date);
		dateObj.add(Calendar.DAY_OF_YEAR, numberOfDays);
		return dateObj.getTime();
	}

	public Date reduceDay(Date date, int numberOfDays) {
		numberOfDays = (-1) * numberOfDays;		// turn into Negative Number
		Calendar dateObj  = Calendar.getInstance();
		dateObj.setTime(date);
		dateObj.add(Calendar.DAY_OF_YEAR, numberOfDays);
		return dateObj.getTime();
	}
	
}
