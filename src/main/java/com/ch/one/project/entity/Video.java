package com.ch.one.project.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Auther: pch
 * @Date: 2018/12/2 18:40
 * @Description:
 */
@Getter
@Setter
public class Video {
	private String name;
	private String arae;
	private String intro;
	// 导演
	private String director;
	private String language;
	private String imgUrl;
	private List<VideoPlayer> players;
	private String remarks;
	private String score;
	// 主演
	private String starring;
	private String year;


}
