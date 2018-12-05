package com.ch.one;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ch.one.project.entity.OneReaderJOList;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * @Auther: pch
 * @Date: 2018/11/29 14:24
 * @Description:
 */
public class Demo {

	@Test
	public void te(){
		HttpUtil.downloadFile("http://t2.hddhhn.com/uploads/tu/201610/198/11111.jpg", "F:/dcache/123/456");

	}


	@Test
	public void fun2(){
		HttpRequest httpRequest = HttpRequest.get("http://v3.wufazhuce.com:8000/api/all/list/1?channel=mx&sign=e28faf34c8f0cd9665cfb1aee517334a&version=4.5.6&uuid=ffffffff-e456-d558-189b-2753028c7ebb&platform=android");
		HttpResponse response = httpRequest.execute();
		String body = response.body();
		//System.out.println(body);
		JSONObject jsonObject = JSONObject.parseObject(body);
		String htmlContent = jsonObject.getString("html_content");
		//String htmlContent = "allarticles=admin3586";
		//File file = new File("E:\\vueProject\\one\\dist\\1.html");
		//FileUtil.writeBytes(htmlContent.getBytes(),file);
		List<String> list = ReUtil.findAll("allarticles=(.*?)];",htmlContent , 1);
		if (list == null || list.size() == 0) {
			return;
		}
		String result = list.get(0) + "]";
		List<OneReaderJOList> joLists = JSONArray.parseArray(result, OneReaderJOList.class);
		System.out.println(1);

	}

	@Test
	public void fun3(){
		HttpResponse response = HttpRequest.get("http://v3.wufazhuce.com:8000/api/essay/htmlcontent/3586")
				.header("User-Agent","android-async-http/2.0 (http://loopj.com/android-async-http)")
				.execute();
		String body = response.body();
		JSONObject jsonObject = JSONObject.parseObject(body);
		String string = jsonObject.getJSONObject("data").getString("html_content");
		System.out.println(string);
	}
}
