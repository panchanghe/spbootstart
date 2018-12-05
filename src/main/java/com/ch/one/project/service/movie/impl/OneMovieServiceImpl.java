package com.ch.one.project.service.movie.impl;

import com.ch.one.core.util.HttpUtil;
import com.ch.one.project.dao.movie.OneMovieDao;
import com.ch.one.project.entity.OneMovie;
import com.ch.one.project.service.movie.OneMovieService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: pch
 * @Date: 2018/12/1 21:11
 * @Description:
 */
@Service
public class OneMovieServiceImpl implements OneMovieService {
	@Autowired
	private OneMovieDao oneMovieDao;


	@Override
	public List<OneMovie> pageOneMovie(Integer start, Integer size) {
		PageHelper.startPage(start, size);
		List<OneMovie> oneMovieList = oneMovieDao.pageOneMovie();
		return oneMovieList;
	}

	@Override
	public String getOneMovieHtmlById(Integer id) {
		String html = getOneMovieHtml(id);
		return html;
	}

	private String getOneMovieHtml(Integer id){
		String url = "http://v3.wufazhuce.com:8000/api/movie/htmlcontent/" + id;
		String html = HttpUtil.getResponseByUrl(url);
		return html;
	}
}
