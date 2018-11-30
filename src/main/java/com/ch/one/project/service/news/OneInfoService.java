package com.ch.one.project.service.news;

import com.ch.one.project.entity.OneInfo;

import java.util.List;

/**
 * @Auther: pch
 * @Date: 2018/11/28 19:59
 * @Description:
 */
public interface OneInfoService {

	// 分页查询one
	List<OneInfo> pageOneInfo(Integer start, Integer size);
}
