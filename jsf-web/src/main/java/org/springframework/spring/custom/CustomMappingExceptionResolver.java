package org.springframework.spring.custom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class CustomMappingExceptionResolver extends SimpleMappingExceptionResolver{
	private String ajaxErrorView;
	public void setAjaxErrorView(String ajaxErrorView) {
		this.ajaxErrorView = ajaxErrorView;
	}
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
//		if(HttpUtil.isAjaxRequest(request)){
//			return getModelAndView(ajaxErrorView,ex);
//		}
		return super.doResolveException(request, response, handler, ex);
	}
}
