package com.ch.one;

import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ch.one.project.dao.movie.OneMovieDao;
import com.ch.one.project.dao.music.OneMusicDao;
import com.ch.one.project.dao.news.OneReaderDao;
import com.ch.one.project.entity.*;
import com.ch.one.project.mapper.OneReaderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: pch
 * @Date: 2018/11/30 15:44
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OneReaderTest {
	@Autowired
	private OneReaderDao oneReaderDao;
	@Autowired
	private OneMusicDao oneMusicDao;
	@Autowired
	private OneMovieDao oneMovieDao;

	@Test
	public void fun1() {
		HttpRequest httpRequest = HttpRequest.get("http://v3.wufazhuce.com:8000/api/all/list/1?channel=mx&sign=e28faf34c8f0cd9665cfb1aee517334a&version=4.5.6&uuid=ffffffff-e456-d558-189b-2753028c7ebb&platform=android");
		HttpResponse response = httpRequest.execute();
		String body = response.body();
		JSONObject jsonObject = JSONObject.parseObject(body);
		String htmlContent = jsonObject.getString("html_content");
		List<String> list = ReUtil.findAll("allarticles=(.*?)];",htmlContent , 1);
		if (list == null || list.size() == 0) {
			return;
		}
		String result = list.get(0) + "]";;
		List<OneReaderJOList> joLists = JSONArray.parseArray(result, OneReaderJOList.class);
		List<OneReaderJO> readerJOS;
		List<OneReader> oneReaderList = new ArrayList<>();
		OneReader oneReader;
		for (OneReaderJOList joList : joLists) {
			readerJOS = joList.getList();
			for (OneReaderJO jo : readerJOS) {
				oneReader = new OneReader();
				oneReader.setId(jo.getId());
				oneReader.setTitle(jo.getT());
				oneReader.setAuthor(jo.getSu());
				oneReader.setImgCode(jo.getCo());
				oneReader.setCreateTime(jo.getD());
				oneReaderList.add(oneReader);
			}
			if (oneReaderList.size() > 0) {
				oneReaderDao.batchInsert(oneReaderList);
				oneReaderList.clear();
			}
		}
	}

	@Test
	public void oneMusic(){
		HttpRequest httpRequest = HttpRequest.get("http://v3.wufazhuce.com:8000/api/all/list/4?channel=mx&sign=e28faf34c8f0cd9665cfb1aee517334a&version=4.5.6&uuid=ffffffff-e456-d558-189b-2753028c7ebb&platform=android");
		HttpResponse response = httpRequest.execute();
		String body = response.body();
		JSONObject jsonObject = JSONObject.parseObject(body);
		String htmlContent = jsonObject.getString("html_content");
		List<String> list = ReUtil.findAll("allarticles=(.*?)];",htmlContent , 1);
		if (list == null || list.size() == 0) {
			return;
		}
		String result = list.get(0) + "]";
		List<OneReaderJOList> joLists = JSONArray.parseArray(result, OneReaderJOList.class);
		List<OneReaderJO> readerJOS;
		List<OneMusic> oneMusicList = new ArrayList<>();
		OneMusic oneMusic;
		for (OneReaderJOList joList : joLists) {
			readerJOS = joList.getList();;
			for (OneReaderJO jo : readerJOS) {
				oneMusic = new OneMusic();
				oneMusic.setId(jo.getId());
				oneMusic.setTitle(jo.getT());
				oneMusic.setAuthor(jo.getSu());
				oneMusic.setImgCode(jo.getCo());
				oneMusic.setCreateTime(jo.getD());
				oneMusicList.add(oneMusic);
			}
			if (oneMusicList.size() > 0) {
				oneMusicDao.batchInsert(oneMusicList);
				oneMusicList.clear();
			}
		}
	}

	@Test
	public void oneMovie(){
		HttpRequest httpRequest = HttpRequest.get("http://v3.wufazhuce.com:8000/api/all/list/5?channel=mx&sign=e28faf34c8f0cd9665cfb1aee517334a&version=4.5.6&uuid=ffffffff-e456-d558-189b-2753028c7ebb&platform=android");
		HttpResponse response = httpRequest.execute();
		String body = response.body();
		JSONObject jsonObject = JSONObject.parseObject(body);
		String htmlContent = jsonObject.getString("html_content");
		List<String> list = ReUtil.findAll("allarticles=(.*?)];",htmlContent , 1);
		if (list == null || list.size() == 0) {
			return;
		}
		String result = list.get(0) + "]";
		List<OneReaderJOList> joLists = JSONArray.parseArray(result, OneReaderJOList.class);
		List<OneReaderJO> readerJOS;
		List<OneMovie> oneMovieList = new ArrayList<>();
		OneMovie oneMovie;
		for (OneReaderJOList joList : joLists) {
			readerJOS = joList.getList();;
			for (OneReaderJO jo : readerJOS) {
				oneMovie = new OneMovie();
				oneMovie.setId(jo.getId());
				oneMovie.setTitle(jo.getT());
				oneMovie.setAuthor(jo.getSu());
				oneMovie.setImgCode(jo.getCo());
				oneMovie.setCreateTime(jo.getD());
				oneMovieList.add(oneMovie);
			}
			if (oneMovieList.size() > 0) {
				oneMovieDao.batchInsert(oneMovieList);
				oneMovieList.clear();
			}
		}
	}
}
