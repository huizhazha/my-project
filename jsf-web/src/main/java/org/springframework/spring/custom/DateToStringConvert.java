package org.springframework.spring.custom;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateToStringConvert implements Converter<Date, String>{
	public String convert(Date value) {
		String strValue = "";
		if(value!=null){
			DateFormat dateFormat = null;
			Calendar calendar =Calendar.getInstance();
			calendar.setTime(value);
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			int minute = calendar.get(Calendar.MINUTE);
			int second = calendar.get(Calendar.SECOND);
			if(hour!=0||minute!=0||second!=0){
				dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			}else{
				dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			}
			strValue = dateFormat.format(value);
		}
		return strValue;
	}

}
