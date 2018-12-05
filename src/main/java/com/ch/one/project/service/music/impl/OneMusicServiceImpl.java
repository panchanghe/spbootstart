package com.ch.one.project.service.music.impl;

import com.alibaba.fastjson.JSONObject;
import com.ch.one.core.util.HttpUtil;
import com.ch.one.project.dao.music.OneMusicDao;
import com.ch.one.project.entity.OneMusic;
import com.ch.one.project.service.music.OneMusicService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: pch
 * @Date: 2018/12/1 16:20
 * @Description:
 */
@Service
public class OneMusicServiceImpl implements OneMusicService {
	@Autowired
	private OneMusicDao oneMusicDao;


	@Override
	public List<OneMusic> pageOneMusic(Integer start, Integer size) {
		PageHelper.startPage(start, size);
		List<OneMusic> oneMusicList = oneMusicDao.pageOneMusic();
		return oneMusicList;
	}

	@Override
	public Map<String, String> getOneMusicHtmlById(Integer id) {
		Map<String, String> map = new HashMap<>(2);
		String html = getOneMusicHtml(id);
		String musicUrl = getOneMusicData(id);
		map.put("html", html);
		map.put("musicUrl", musicUrl);
		return map;
	}

	private String getOneMusicHtml(Integer id){
		String url = "http://v3.wufazhuce.com:8000/api/music/htmlcontent/" + id;
		String html = HttpUtil.getResponseByUrl(url);
		return html;
	}

	private String getOneMusicData(Integer id){
		String url = "http://v3.wufazhuce.com:8000/api/music/htmlcontent/" + id;
		String response = HttpUtil.getResponseByApp(url);
		JSONObject jsonObject = JSONObject.parseObject(response);
		String musicUrl;
		try {
			musicUrl = jsonObject.getJSONObject("data").getString("music_id");
		} catch (Exception e) {
			musicUrl = "#";
		}
		return musicUrl;
	}
}
