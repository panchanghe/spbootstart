package com.ch.one.project.controller.music;

import com.ch.one.core.base.BaseController;
import com.ch.one.project.entity.OneMusic;
import com.ch.one.project.service.music.OneMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Auther: pch
 * @Date: 2018/12/1 16:18
 * @Description:
 */
@RestController
@RequestMapping("/oneMusic")
public class OneMusicController extends BaseController {
	@Autowired
	private OneMusicService oneMusicService;

	/**
	 * 分页查询music
	 * @param start
	 * @param size
	 * @return
	 */
	@RequestMapping("/pageOneMusic")
	public Object pageOneMusic(Integer start,Integer size){
		List<OneMusic> oneMusicList = oneMusicService.pageOneMusic(start, size);
		return renderSuccess(oneMusicList);
	}

	@RequestMapping("/getOneMusicHtmlById")
	public Object getOneMusicHtmlById(Integer id){
		if (id == null) {
			return renderError("id为空");
		}
		Map<String, String> map = oneMusicService.getOneMusicHtmlById(id);
		return renderSuccess(map);
	}



}
