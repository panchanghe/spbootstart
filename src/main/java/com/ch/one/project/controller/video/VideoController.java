package com.ch.one.project.controller.video;

import com.ch.one.core.base.BaseController;
import com.ch.one.project.entity.Video;
import com.ch.one.project.entity.VideoType;
import com.ch.one.project.service.movie.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: pch
 * @Date: 2018/12/2 18:35
 * @Description:
 */
@RestController
@RequestMapping("/video")
public class VideoController extends BaseController {
	@Autowired
	private VideoService videoService;

	// 热门
	@RequestMapping("/hotVideo")
	public Object hotVideo() {
		List<Video> videoList = videoService.hotVideo();
		return renderSuccess(videoList);
	}

	/**
	 * 最近更新
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping("/pageTodayUpdateVideo")
	public Object pageTodayUpdateVideo(Integer start, Integer end){
		List<Video> videoList = videoService.pageTodayUpdateVideo(start,end);
		return renderSuccess(videoList);
	}

	// 获取video类型
	@RequestMapping("/getVideoType")
	public Object getVideoType(Integer pid){
		List<VideoType> videoTypes = videoService.getVideoType(pid);
		return renderSuccess(videoTypes);
	}

	@RequestMapping("/pageVideoByTypeId")
	public Object pageVideoByTypeId(Integer typeId,Integer start, Integer end) {
		List<Video> videoList = videoService.pageVideoByTypeId(typeId, start, end);
		return renderSuccess(videoList);
	}

}
