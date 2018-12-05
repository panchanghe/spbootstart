package com.ch.one.project.scheduled;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ch.one.core.util.JedisUtil;
import com.ch.one.core.util.VideoUtil;
import com.ch.one.project.entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: pch
 * @Date: 2018/11/30 10:45
 * @Description:
 */
@Component
public
class SysScheduled {
	@Value("${com.ch.video.baseUrl}")
	private String baseUrl;

	@Autowired
	private JedisUtil jedisUtil;


	// 热门
	//@Scheduled(cron = "0 0 1 * * ?")
	public void crawHotVideo(){
		Jedis jedis = jedisUtil.getJedis();
		List<String> jsons = new ArrayList<>();
		List<Video> videoList = VideoUtil.pageHotVideo(baseUrl + "recommend", 0, 5000);
		System.err.println(videoList.size());
		for (Video video : videoList) {
			String s = JSONObject.toJSONString(video);
			jedis.rpush("hotVideo", s);
		}
		jedis.close();
	}

	// 今日更新
	public void crawToDayUpdateVideo(){
		Jedis jedis = jedisUtil.getJedis();
		List<String> jsons = new ArrayList<>();
		List<Video> videoList = VideoUtil.pageHotVideo(baseUrl + "getTodayUp", 0, 5000);
		System.err.println(videoList.size());
		for (Video video : videoList) {
			String s = JSONObject.toJSONString(video);
			jedis.rpush("toDayUpdate", s);
		}
		jedis.close();
	}

	// 电影
	public void crawMovieByTypeId(Integer typeId){
		Jedis jedis = jedisUtil.getJedis();
		List<Video> videoList = VideoUtil.pageVideoByTypeId(baseUrl + "getVodWithPage",typeId, 0, 1000);
		System.err.println(videoList.size());
		for (Video video : videoList) {
			String s = JSONObject.toJSONString(video);
			jedis.rpush("video_"+typeId, s);
		}
		jedis.close();
	}


}
