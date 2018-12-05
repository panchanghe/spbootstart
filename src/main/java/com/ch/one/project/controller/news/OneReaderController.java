package com.ch.one.project.controller.news;

import cn.hutool.core.util.StrUtil;
import com.ch.one.core.base.BaseController;
import com.ch.one.project.entity.OneInfo;
import com.ch.one.project.entity.OneReader;
import com.ch.one.project.service.news.OneReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: pch
 * @Date: 2018/11/30 16:49
 * @Description:
 */
@RestController
@RequestMapping("/oneReader")
public class OneReaderController extends BaseController {

	@Autowired
	private OneReaderService oneReaderService;

	/**
	 * 分页查询 阅读
	 * @param start
	 * @param size
	 * @return
	 */
	@RequestMapping("/pageOneReader")
	public Object pageOneReader(Integer start, Integer size) {
		List<OneReader> oneReaderList = oneReaderService.pageOneReader(start, size);
		return renderSuccess(oneReaderList);
	}

	@RequestMapping("/getOneReaderById")
	public Object getOneReaderById(Integer id){
		if (id == null) {
			return renderError("id为空");
		}
		String html = oneReaderService.getOneReaderHtmlById(id);
		return renderSuccess(html);
	}

}
