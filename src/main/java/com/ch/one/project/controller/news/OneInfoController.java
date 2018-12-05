package com.ch.one.project.controller.news;

import com.ch.one.core.base.BaseController;
import com.ch.one.core.util.JedisUtil;
import com.ch.one.project.entity.OneInfo;
import com.ch.one.project.service.news.OneInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @Auther: pch
 * @Date: 2018/11/26 17:49
 * @Description:
 */
@Controller
@RequestMapping("/oneInfo")
public class OneInfoController extends BaseController {
	@Autowired
	private OneInfoService oneInfoService;
	@Autowired
	private JedisUtil jedisUtil;

	@RequestMapping("/pageOneInfo")
	@ResponseBody
	public Object pageOneInfo(Integer start,Integer size){
		List<OneInfo> oneInfoList = oneInfoService.pageOneInfo(start, size);
		Jedis jedis = jedisUtil.getJedis();
		return renderSuccess(oneInfoList);
	}
}
