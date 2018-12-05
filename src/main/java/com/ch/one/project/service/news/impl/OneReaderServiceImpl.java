package com.ch.one.project.service.news.impl;

import com.alibaba.fastjson.JSONObject;
import com.ch.one.core.util.HttpUtil;
import com.ch.one.project.dao.news.OneReaderDao;
import com.ch.one.project.entity.OneReader;
import com.ch.one.project.service.news.OneReaderService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: pch
 * @Date: 2018/11/30 16:51
 * @Description:
 */
@Service
public class OneReaderServiceImpl implements OneReaderService {

	@Autowired
	private OneReaderDao oneReaderDao;

	@Override
	public List<OneReader> pageOneReader(Integer start, Integer size) {
		PageHelper.startPage(start, size);
		List<OneReader> oneReaderList = oneReaderDao.pageOneReader();
		return oneReaderList;
	}

	@Override
	public String getOneReaderHtmlById(Integer id) {
		String html = getReaderHtml(id);
		return html;
	}

	private String getReaderHtml(Integer id){
		String url = "http://v3.wufazhuce.com:8000/api/essay/htmlcontent/" + id;
		String html = HttpUtil.getResponseByUrl(url);
		return html;
	}
}
