package com.ch.one.project.controller.movie;

import com.ch.one.core.base.BaseController;
import com.ch.one.project.entity.OneMovie;
import com.ch.one.project.service.movie.OneMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Auther: pch
 * @Date: 2018/12/1 21:15
 * @Description:
 */
@RestController
@RequestMapping("/oneMovie")
public class OneMovieController extends BaseController {
	@Autowired
	private OneMovieService oneMovieService;

	/**
	 * 分页查询
	 * @param start
	 * @param size
	 * @return
	 */
	@RequestMapping("/pageOneMovie")
	public Object pageOneMovie(Integer start, Integer size) {
		List<OneMovie> oneMovieList = oneMovieService.pageOneMovie(start, size);
		return renderSuccess(oneMovieList);
	}

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	@RequestMapping("/getOneMovieHtmlById")
	public Object getOneMovieHtmlById(Integer id){
		if (id == null) {
			return renderError("id为空");
		}
		String html = oneMovieService.getOneMovieHtmlById(id);
		return renderSuccess(html);
	}
}
