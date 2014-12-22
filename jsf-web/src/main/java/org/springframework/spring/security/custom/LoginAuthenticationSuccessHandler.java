package org.springframework.spring.security.custom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.my.core.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.StringUtils;

public class LoginAuthenticationSuccessHandler extends
		AbstractAuthenticationTargetUrlRequestHandler implements
		AuthenticationSuccessHandler {
	private String defaultAdminTargetUrl;
	private boolean useReferer = false;
	
	public String getDefaultAdminTargetUrl() {
		return defaultAdminTargetUrl;
	}

	public void setDefaultAdminTargetUrl(String defaultAdminTargetUrl) {
		this.defaultAdminTargetUrl = defaultAdminTargetUrl;
	}

	public boolean isUseReferer() {
		return useReferer;
	}

	public void setUseReferer(boolean useReferer) {
		this.useReferer = useReferer;
	}

	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
	}

	protected final void clearAuthenticationAttributes(
			HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	@Override
	protected void handle(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String targetUrl = determineTargetUrl(request, response,authentication);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
	}
	
	protected String determineTargetUrl(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) {
		if (isAlwaysUseDefaultTargetUrl()) {
			if(authentication.getPrincipal() instanceof User){
				return defaultAdminTargetUrl;
			}else{
				return getDefaultTargetUrl();
			}
        }

        // Check for the parameter and use that if available
        String targetUrl = null;

        if (getTargetUrlParameter() != null  ) {
            targetUrl = request.getParameter(getTargetUrlParameter());

            if (StringUtils.hasText(targetUrl)) {
                logger.debug("Found targetUrlParameter in request: " + targetUrl);

                return targetUrl;
            }
        }

        if (useReferer && !StringUtils.hasLength(targetUrl)) {
            targetUrl = request.getHeader("Referer");
            logger.debug("Using Referer header: " + targetUrl);
        }

        if (!StringUtils.hasText(targetUrl)) {
            targetUrl = getDefaultTargetUrl();
            logger.debug("Using default Url: " + targetUrl);
        }

        return targetUrl;
	}
}
