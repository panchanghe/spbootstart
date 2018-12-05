package com.ch.one.project.service.movie;

import com.ch.one.project.entity.Video;
import com.ch.one.project.entity.VideoType;

import java.util.List;
import java.util.Map;

/**
 * @Auther: pch
 * @Date: 2018/12/2 18:36
 * @Description:
 */
public interface VideoService {

	// 分页加载热门
	List<Video> hotVideo();

	// 最近更新
	List<Video> pageTodayUpdateVideo(Integer start, Integer end);

	// 获取类型
	List<VideoType> getVideoType(Integer typeId);

	// 根据typeId获取
	List<Video> pageVideoByTypeId(Integer typeId,Integer start, Integer end);

}
