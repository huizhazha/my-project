package org.springframework.spring.security.custom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.rippletech.payment.exception.CaptchaIncorrectException;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	private boolean postOnly = true;
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("不支持"+request.getMethod()+"方法认证");
        }
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }
//        System.out.println(username+password);
        username = username.trim();
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
		
//		//从request中获取session
		HttpSession session = request.getSession();
//		//验证码的判断,为空或不正确则throw exception
		String fomrValidateCode = request.getParameter("captcha");
		String captcha = (String)session.getAttribute("captcha");
		if(StringUtils.isBlank(fomrValidateCode)|| !fomrValidateCode.equalsIgnoreCase(captcha)){
			CaptchaIncorrectException e = new CaptchaIncorrectException("验证码错误！");
			e.setAuthentication(authRequest);
			throw e;
		}
        return this.getAuthenticationManager().authenticate(authRequest);
	}
	
}
