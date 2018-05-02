package application;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String authToken = request.getHeader("auth-token");
		if (authToken == null) return false;
		try {
			if(isValid(authToken)) {
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
	
	public boolean isValid(String authToken) throws Exception {
		//TODO decrypt token and then validate timestamp
		long now = System.currentTimeMillis();
		long token = Long.parseLong(authToken);
		long elapsed = now - token;
		return elapsed < 3600000; //1h
	}
}
