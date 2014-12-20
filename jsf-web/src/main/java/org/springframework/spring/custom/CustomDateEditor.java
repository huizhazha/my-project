package org.springframework.spring.custom;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.StringUtils;

public class CustomDateEditor extends PropertyEditorSupport{
	private DateFormat dateFormat;
	private final boolean allowEmpty;
	private final int exactDateLength;
	
	public CustomDateEditor(boolean allowEmpty) {
		this.allowEmpty = allowEmpty;
		this.exactDateLength = -1;
	}
	
	public CustomDateEditor(boolean allowEmpty, int exactDateLength) {
		this.allowEmpty = allowEmpty;
		this.exactDateLength = exactDateLength;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (this.allowEmpty && !StringUtils.hasText(text)) {
			// Treat empty String as null value.
			setValue(null);
		}
		else if (text != null && this.exactDateLength >= 0 && text.length() != this.exactDateLength) {
			throw new IllegalArgumentException(
					"Could not parse date: it is not exactly" + this.exactDateLength + "characters long");
		}
		else {
			Date check = null;
			DateFormat[] dfs = getDateFormats();
            for (DateFormat df1 : dfs) {
                try {
                    check = df1.parse(text);
                    if (check != null) {
                    	dateFormat = df1;
                        break;
                    }
                }
                catch (ParseException ex) {
    			}
            }
            if (check == null){
            	throw new IllegalArgumentException("Could not parse date");
            }
            setValue(check);
		}
	}
	
	@Override
	public String getAsText() {
		Date value = (Date) getValue();
		String strValue = "";
		if(value!=null){
			if(dateFormat==null){
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
			}
			strValue = dateFormat.format(value);
		}
		return strValue;
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
