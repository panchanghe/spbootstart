package com.ch.one.project.dao.movie;

import com.ch.one.project.entity.OneMovie;
import com.ch.one.project.mapper.OneMovieMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther: pch
 * @Date: 2018/12/1 21:05
 * @Description:
 */
@Mapper
public interface OneMovieDao extends OneMovieMapper {

	// 分页查询
	List<OneMovie> pageOneMovie();
}
