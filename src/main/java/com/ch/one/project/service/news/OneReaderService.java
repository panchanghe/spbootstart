package com.ch.one.project.service.news;

import com.ch.one.project.entity.OneReader;

import java.util.List;

/**
 * @Auther: pch
 * @Date: 2018/11/30 16:50
 * @Description:
 */
public interface OneReaderService {

	// 分页查询阅读
	List<OneReader> pageOneReader(Integer start, Integer size);

	// 根据id查阅
	String getOneReaderHtmlById(Integer id);

}
