package com.ch.one.project.service.movie.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ch.one.core.util.JedisUtil;
import com.ch.one.core.util.VideoUtil;
import com.ch.one.project.entity.Video;
import com.ch.one.project.entity.VideoType;
import com.ch.one.project.service.movie.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: pch
 * @Date: 2018/12/2 18:37
 * @Description:
 */
@Service
public class VideoServiceImpl implements VideoService {
	@Value("${com.ch.video.baseUrl}")
	private String baseUrl;
	@Autowired
	private JedisUtil jedisUtil;

	@Override
	public List<Video> hotVideo() {
		//List<Video> videoList = VideoUtil.pageHotVideo(baseUrl + "recommend", start, size);
		// 从缓存中获取
		List<String> list = jedisUtil.lrange("hotVideo", 0, -1);
		List<Video> videoList = new ArrayList<>();
		for (String json : list) {
			videoList.add(JSONObject.parseObject(json, Video.class));
		}
		return videoList;
	}

	@Override
	public List<Video> pageTodayUpdateVideo(Integer start, Integer end) {
		List<String> list = jedisUtil.lrange("todayUpdate", start, end-1);
		List<Video> videoList = new ArrayList<>();
		for (String json : list) {
			videoList.add(JSONObject.parseObject(json, Video.class));
		}
		return videoList;
	}

	@Override
	public List<VideoType> getVideoType(Integer pid) {
		List<VideoType> videoTypes = VideoUtil.getVideoType(baseUrl + "getType", pid);
		return videoTypes;
	}

	@Override
	public List<Video> pageVideoByTypeId(Integer typeId, Integer start, Integer end) {
		List<String> list = jedisUtil.lrange("video_"+typeId, start, end-1);
		List<Video> videoList = new ArrayList<>();
		for (String json : list) {
			videoList.add(JSONObject.parseObject(json, Video.class));
		}
		return videoList;
	}

}
