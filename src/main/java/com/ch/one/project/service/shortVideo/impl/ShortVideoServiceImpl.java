package com.ch.one.project.service.shortVideo.impl;

import com.ch.one.core.util.HttpUtil;
import com.ch.one.project.service.shortVideo.ShortVideoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @Auther: pch
 * @Date: 2018/12/2 15:26
 * @Description:
 */
@Service
public class ShortVideoServiceImpl implements ShortVideoService {

	@Value("${com.ch.shortVideo.grabIdUrl}")
	private String grabIdUrl;


	@Override
	public List<String> grabIds(){
		List<String> ids = HttpUtil.grabIds(grabIdUrl);
		return ids;
	}

}
