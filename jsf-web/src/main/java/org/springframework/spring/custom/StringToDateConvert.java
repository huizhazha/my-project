package org.springframework.spring.custom;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

public class StringToDateConvert implements Converter<String, Date>{
	
	public Date convert(String text) {
		if (!StringUtils.hasText(text)) {
			return null;
		}
		Date check = null;
		DateFormat[] dfs = getDateFormats();
        for (DateFormat df1 : dfs) {
            try {
                check = df1.parse(text);
                if (check != null) {
                    break;
                }
            }
            catch (ParseException ex) {
			}
        }
        if (check == null){
        	throw new IllegalArgumentException("Could not parse date");
        }
        return check;
	}
	
	private DateFormat[] getDateFormats() {
		DateFormat dft1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat dft2 = new SimpleDateFormat("yyyy-MM-dd"); 
        DateFormat dt1 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.LONG);
        DateFormat dt2 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM);
        DateFormat dt3 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);

        DateFormat d1 = DateFormat.getDateInstance(DateFormat.SHORT);
        DateFormat d2 = DateFormat.getDateInstance(DateFormat.MEDIUM);
        DateFormat d3 = DateFormat.getDateInstance(DateFormat.LONG);

        DateFormat rfc3399 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        DateFormat[] dfs = {dft1, dft2, dt1, dt2, dt3, rfc3399, d1, d2, d3}; //added RFC 3339 date format (XW-473)
        return dfs;
    }

}
