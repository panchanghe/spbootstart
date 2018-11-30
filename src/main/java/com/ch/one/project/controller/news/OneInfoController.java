package com.ch.one.project.controller.news;

import com.ch.one.project.entity.OneInfo;
import com.ch.one.project.service.news.OneInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: pch
 * @Date: 2018/11/26 17:49
 * @Description:
 */
@Controller
@RequestMapping("/oneInfo")
public class OneInfoController {
	@Autowired
	private OneInfoService oneInfoService;

	@RequestMapping("/pageOneInfo")
	@ResponseBody
	public Object pageOneInfo(Integer start,Integer size){
		int i = 1/0;
		List<OneInfo> oneInfoList = oneInfoService.pageOneInfo(start, size);
		return oneInfoList;
	}
}
