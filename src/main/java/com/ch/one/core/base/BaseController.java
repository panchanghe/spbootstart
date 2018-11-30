package com.ch.one.core.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * @Auther: pch
 * @Date: 2018/11/23 16:09
 * @Description:
 */
@Controller
public class BaseController {

	private static Logger LOG = LoggerFactory.getLogger(BaseController.class);

	public HttpSession getSession() {
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		LOG.debug("sessionId = " + session.getId());
		return session;
	}
}
