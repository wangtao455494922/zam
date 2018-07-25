package com.wjt.zam.common.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String resouce) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    
        dateFormat.setLenient(false);    
        try {    
            return dateFormat.parse(resouce);    
        } catch (ParseException e) {    
            e.printStackTrace();    
        }           
		return null;
	}

}
