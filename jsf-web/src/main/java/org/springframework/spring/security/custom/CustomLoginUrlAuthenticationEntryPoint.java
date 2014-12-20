package org.springframework.spring.security.custom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

public class CustomLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint{
	private String loginFormUrl;
	
	public CustomLoginUrlAuthenticationEntryPoint() {
	}
	
	public CustomLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
		this.loginFormUrl = loginFormUrl;
    }
	
	@Override
	protected String determineUrlToUseForThisRequest(
			HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) {
		return super.determineUrlToUseForThisRequest(request, response, exception);
	}
}
