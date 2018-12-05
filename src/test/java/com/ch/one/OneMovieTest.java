package com.ch.one;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONObject;
import com.ch.one.core.util.HttpUtil;
import org.junit.Test;

/**
 * @Auther: pch
 * @Date: 2018/12/1 20:58
 * @Description:
 */
public class OneMovieTest {

	@Test
	public void fun1(){
		String url = "http://v3.wufazhuce.com:8000/api/all/list/5";
		String response = HttpUtil.getResponseByApp(url);
		JSONObject jsonObject = JSONObject.parseObject(response);
		String htmlContent = jsonObject.getString("html_content");
		FileUtil.writeBytes(htmlContent.getBytes(), "E:\\vueProject\\one\\dist\\1.html");
	}
}
