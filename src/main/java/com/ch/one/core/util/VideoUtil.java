package com.ch.one.core.util;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ch.one.project.entity.Video;
import com.ch.one.project.entity.VideoPlayer;
import com.ch.one.project.entity.VideoType;

import java.util.*;

/**
 * @Auther: pch
 * @Date: 2018/12/2 18:49
 * @Description:
 */
public class VideoUtil {

	public static List<Video> pageHotVideo(String url,Integer start, Integer size){
		Map<String, Object> map = new HashMap(2);
		map.put("page", start);
		map.put("pageSize", size);
		String result = HttpUtil.get(url,map);
		JSONObject jsonObject = JSONObject.parseObject(result);
		JSONArray jsonArray = jsonObject.getJSONArray("data");
		Iterator<Object> iterator = jsonArray.iterator();
		JSONObject next;
		List<Video> videoList = new ArrayList<>();
		Video video;
		while (iterator.hasNext()) {
			next = (JSONObject) iterator.next();
			video = new Video();
			video.setArae(next.getString("d_area"));
			video.setIntro(next.getString("d_content"));
			video.setDirector(next.getString("d_directed"));
			video.setLanguage(next.getString("d_lang"));
			video.setName(next.getString("d_name"));
			video.setImgUrl(next.getString("d_pic"));
			video.setPlayers(splitVideoUrl(next.getString("d_playurl")));
			if (video.getPlayers().size() > 5) {
				System.err.println(video.getName());
			}
			video.setScore(next.getString("d_score"));
			video.setStarring(next.getString("d_starring"));
			video.setYear(next.getString("d_year"));
			videoList.add(video);
		}
		return videoList;
	}

	// 获取 类型
	public static List<VideoType> getVideoType(String url,Integer pid) {
		Map<String, Object> map = new HashMap(1);
		map.put("pid", pid);
		String result = HttpUtil.get(url,map);
		JSONObject jsonObject = JSONObject.parseObject(result);
		JSONArray jsonArray = jsonObject.getJSONArray("data");
		Iterator<Object> iterator = jsonArray.iterator();
		JSONObject next;
		List<VideoType> videoTypes = new ArrayList<>();
		VideoType videoType;
		while (iterator.hasNext()) {
			next = (JSONObject) iterator.next();
			videoType = new VideoType();
			videoType.setName(next.getString("t_name"));
			videoType.setEnname(next.getString("t_enname"));
			videoType.setId(next.getInteger("t_id"));
			videoTypes.add(videoType);
		}
		return videoTypes;
	}

	// 根据type_id获取
	public static List<Video> pageVideoByTypeId(String url,Integer typeId,Integer start, Integer size){
		Map<String, Object> map = new HashMap(2);
		map.put("page", start);
		map.put("pageSize", size);
		map.put("d_type", typeId);
		String result = HttpUtil.get(url,map);
		JSONObject jsonObject = JSONObject.parseObject(result);
		JSONArray jsonArray = jsonObject.getJSONArray("data");
		Iterator<Object> iterator = jsonArray.iterator();
		JSONObject next;
		List<Video> videoList = new ArrayList<>();
		Video video;
		while (iterator.hasNext()) {
			next = (JSONObject) iterator.next();
			video = new Video();
			video.setArae(next.getString("d_area"));
			video.setIntro(next.getString("d_content"));
			video.setDirector(next.getString("d_directed"));
			video.setLanguage(next.getString("d_lang"));
			video.setName(next.getString("d_name"));
			video.setImgUrl(next.getString("d_pic"));
			video.setPlayers(splitVideoUrl(next.getString("d_playurl")));
			if (video.getPlayers().size() > 5) {
				System.err.println(video.getName());
			}
			video.setScore(next.getString("d_score"));
			video.setStarring(next.getString("d_starring"));
			video.setYear(next.getString("d_year"));
			videoList.add(video);
		}
		return videoList;
	}



	// 分隔
	private static List<VideoPlayer> splitVideoUrl(String playUrl){
		String[] urls = playUrl.split("#");
		String[] split;
		List<VideoPlayer> videoPlayerList = new ArrayList<>();
		VideoPlayer videoPlayer;
		for (String url : urls) {
			split = url.split("\\$");
			videoPlayer = new VideoPlayer();
			videoPlayer.setDefin(split[0]);
			if (split.length > 1) {
				videoPlayer.setUrl(split[1]);
			}
			videoPlayerList.add(videoPlayer);
		}
		return videoPlayerList;
	}

}
