package org.springframework.spring.security.custom;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.InvalidSessionStrategy;

public class CustomInvalidSessionStrategy implements InvalidSessionStrategy{
	private String invalidSessionUrl;
	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private boolean createNewSession = true;

	public void setInvalidSessionUrl(String invalidSessionUrl) {
		this.invalidSessionUrl = invalidSessionUrl;
	}

	public void setCreateNewSession(boolean createNewSession) {
		this.createNewSession = createNewSession;
	}

	public void onInvalidSessionDetected(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		if (createNewSession) {
            request.getSession();
        }
		//判断是否是jsf的ajax请求
		if("partial/ajax".equals(request.getHeader("Faces-Request"))||request.getParameterMap().containsKey("javax.faces.partial.ajax")){
			response.getWriter().print("<?xml version=\"1.0\" encoding=\"UTF-8\"?><partial-response><redirect url=\""+request.getContextPath()+invalidSessionUrl+"\"></redirect></partial-response>");
			response.flushBuffer();
		}else{
			redirectStrategy.sendRedirect(request, response, invalidSessionUrl);
		}
	}
}