package com.ch.one.project.controller.shortVideo;

import com.ch.one.core.base.BaseController;
import com.ch.one.project.service.shortVideo.ShortVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: pch
 * @Date: 2018/12/2 15:22
 * @Description:
 */
@RestController
@RequestMapping("/shortVideo")
public class ShortVideoController extends BaseController {

	@Autowired
	private ShortVideoService shortVideoService;

	@RequestMapping("/grabIds")
	public Object grabIds(){
		List<String> ids = shortVideoService.grabIds();
		return renderSuccess(ids);
	}

}
