package com.ch.one.project.intercepetor;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: pch
 * @Date: 2018/11/23 11:28
 * @Description: 系统拦截器 打印日志
 */
public class SystemInterceptor implements HandlerInterceptor {
	private final static Logger LOG = LoggerFactory.getLogger(SystemInterceptor.class);
	private HandlerMethod handlerMethod;
	private String className;
	private String fullName;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			handlerMethod = (HandlerMethod) handler;
			className = handlerMethod.getBean().getClass().getName();
			fullName = className + "." + handlerMethod.getMethod().getName();
			LOG.info("请求 "+fullName+"开始..........................");
			LOG.info("请求参数:"+JSON.toJSONString(getParams(request.getParameterMap())));
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (handlerMethod !=null) {
			LOG.info("执行方法" + fullName + "中---------------------------");
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		if (handlerMethod !=null) {
			LOG.info("方法执行" + fullName + "结束!!!!!!!!!!!!!!!!!!!!!!!!!!");
		}
	}

	/**
	 * 获取参数
	 * @param map
	 * @return
	 */
	private Map<String, Object> getParams(Map<String, String[]> map) {
		Map<String, Object> params = new HashMap<>();
		for (Map.Entry<String, String[]> e : map.entrySet()) {
			params.put(e.getKey(), e.getValue());
		}
		return params;
	}
}
