package application;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import application.service.AuthenticationService;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired AuthenticationService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String authToken = request.getHeader("auth-token");
		if (authToken == null) return false;
		try {
			if(service.isValid(authToken)) {
				return true;
			}else {
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				return false;
			}
		}catch(Exception e) {
			//TODO log error
			return false;
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}
}
