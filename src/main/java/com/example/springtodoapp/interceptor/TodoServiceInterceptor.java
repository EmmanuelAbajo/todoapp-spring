package com.example.springtodoapp.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TodoServiceInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(TodoServiceInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		long startTime = System.currentTimeMillis();
		logger.info("Request URL::" + request.getRequestURL().toString() + ":: Start Time=" + System.currentTimeMillis());
		request.setAttribute("startTime", startTime);
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("Request URL:: {} Sent to Handler :: Current Time = {}", request.getRequestURL().toString(),System.currentTimeMillis());
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long startTime = (Long) request.getAttribute("startTime");
		logger.info("Request URL:: {} :: End Time = {}",request.getRequestURL().toString(),System.currentTimeMillis());
		logger.info("Request URL:: {} :: Time Taken = {}", request.getRequestURL().toString(), (System.currentTimeMillis() - startTime));
	}
}
