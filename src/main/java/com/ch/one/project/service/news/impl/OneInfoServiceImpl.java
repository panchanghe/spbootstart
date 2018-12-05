package com.ch.one.project.service.news.impl;

import com.ch.one.project.dao.news.OneInfoDao;
import com.ch.one.project.entity.OneInfo;
import com.ch.one.project.mapper.OneInfoMapper;
import com.ch.one.project.service.news.OneInfoService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: pch
 * @Date: 2018/11/28 19:59
 * @Description:
 */
@Service
public class OneInfoServiceImpl implements OneInfoService {

	@Autowired
	private OneInfoDao oneInfoDao;
	@Autowired
	private OneInfoMapper oneInfoMapper;

	@Override
	public List<OneInfo> pageOneInfo(Integer start, Integer size) {
		PageHelper.startPage(start, size);
		List<OneInfo> oneInfoList = oneInfoDao.getOneInfo();
		return oneInfoList;
	}


}
