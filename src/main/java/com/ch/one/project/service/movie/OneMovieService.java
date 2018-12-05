package com.ch.one.project.service.movie;

import com.ch.one.project.entity.OneMovie;

import java.util.List;

/**
 * @Auther: pch
 * @Date: 2018/12/1 21:11
 * @Description:
 */
public interface OneMovieService {

	// 分页查询 movie
	List<OneMovie> pageOneMovie(Integer start, Integer size);

	// 根据id查询movie html
	String getOneMovieHtmlById(Integer id);
}
