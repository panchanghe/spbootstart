package com.ch.one.project.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Admin
 * @Date: 2018/11/30 09:56
 * @Description:
 */
@Aspect
@Component
public class LogAspect {
	public static Logger logger = LoggerFactory.getLogger(LogAspect.class);

	private String methodName;
	private ServletRequestAttributes attributes;
	private HttpServletRequest request;

	@Pointcut("execution(public * com.ch.one..*.*(..))")
	public void webLog(){}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) {
		// 记录下请求内容
		methodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
		logger.info(methodName + "执行开始....................  ");
		// 接收到请求，记录请求内容
		attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (attributes == null) {
			return;
		}
		request = attributes.getRequest();
		logger.info("请求参数: " + JSON.toJSONString(getParams(request.getParameterMap())));
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
