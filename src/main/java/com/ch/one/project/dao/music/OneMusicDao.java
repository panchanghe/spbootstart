package com.ch.one.project.dao.music;

import com.ch.one.project.entity.OneMusic;
import com.ch.one.project.mapper.OneMusicMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther: pch
 * @Date: 2018/12/1 16:14
 * @Description:
 */
@Mapper
public interface OneMusicDao extends OneMusicMapper {

	// 分页查询music
	List<OneMusic> pageOneMusic();

}
