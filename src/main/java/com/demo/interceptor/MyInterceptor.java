package com.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class MyInterceptor implements HandlerInterceptor {

	 private Logger logger = LoggerFactory.getLogger(MyInterceptor.class);
	//DespatcherServlet处理之后执行 --- 清理工作
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		logger.info("处理之后执行");
		
	}

	//请求出来方法之后执行
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		logger.info("请求出来方法之后执行");

		
	}

	
	//请求方法之前
	//返回true执行下一个拦截器 false 不执行下去
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		// TODO Auto-generated method stub
	
		logger.info("请求方法之前");
		
		
		return true;
		
	}

}
