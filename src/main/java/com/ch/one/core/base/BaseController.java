package com.ch.one.core.base;

import com.ch.one.core.support.AjaxResult;
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

	/**
	 * 返回成功类型
	 * @return
	 */
	protected Object renderSuccess(){
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setSuccess(true);
		return ajaxResult;
	}

	protected Object renderSuccess(String msg){
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setSuccess(true);
		ajaxResult.setMsg(msg);
		return ajaxResult;
	}

	protected Object renderSuccess(Object o){
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setSuccess(true);
		ajaxResult.setObj(o);
		return ajaxResult;
	}

	/**
	 * 返回失败类型
	 * @return
	 */
	protected Object renderError(){
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setSuccess(false);
		return ajaxResult;
	}
	protected Object renderError(String msg){
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setSuccess(false);
		ajaxResult.setMsg(msg);
		return ajaxResult;
	}
}
