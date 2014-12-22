package org.my.web.bean;
 
import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
 
@Component("basicBean")
@Scope("view")
public class BasicBean implements Serializable {
 
	private static final long serialVersionUID = 7640570195887705916L;
	
	private String text;
 
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}