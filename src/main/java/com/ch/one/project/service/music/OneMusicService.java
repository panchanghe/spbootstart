package com.ch.one.project.service.music;

import com.ch.one.project.entity.OneMusic;

import java.util.List;
import java.util.Map;

/**
 * @Auther: pch
 * @Date: 2018/12/1 16:19
 * @Description:
 */
public interface OneMusicService {

	// 分页查询 music
	List<OneMusic> pageOneMusic(Integer start,Integer size);

	// 根据id查询music html
	Map<String,String> getOneMusicHtmlById(Integer id);

}
