package com.ch.one.core.util;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Auther: pch
 * @Date: 2018/11/30 20:50
 * @Description:
 */
public class HttpUtil {

	/**
	 * 得到app的响应
	 * @param url
	 * @return
	 */
	public static String getResponseByApp(String url){
		HttpResponse response = HttpRequest.get(url)
				.header("User-Agent","android-async-http/2.0 (http://loopj.com/android-async-http)")
				.execute();
		String body = response.body();
		return body;
	}

	/**
	 * 根绝url获取相应结果
	 * @param url
	 * @return
	 */
	public static String getResponseByUrl(String url){
		String response = getResponseByApp(url);
		JSONObject jsonObject = JSONObject.parseObject(response);
		String html;
		try {
			html = jsonObject.getJSONObject("data").getString("html_content");
		} catch (Exception e) {
			html = "<h1>阿偶，内容找不到了....</h1>";
		}
		return html;
	}

	/**
	 * 获取短视频id
	 * @param url
	 * @return
	 */
	public static List<String> grabIds(String url) {
		String body = new HttpRequest(url)
				.header("Accept-Encoding", "gzip")
				.header("User-Agent", "com.ss.android.ugc.awema/9940 (Linux; U; Android 8.1.0; zh_CN; 16th; Build/OPM1.171019.026; Cronet/58.0.2991.0)")
				.execute().body();
		JSONObject jsonObject = JSONObject.parseObject(body);
		JSONArray jsonArray = jsonObject.getJSONArray("aweme_list");
		Iterator<Object> iterator = jsonArray.iterator();
		JSONObject next;
		List<String> ids = new ArrayList<>();
		Connection.Response response = null;
		while (iterator.hasNext()) {
			next = (JSONObject) iterator.next();
			String id = next.getJSONObject("video").getJSONObject("download_addr").getString("uri");
			/*try {
				response = Jsoup.connect("https://aweme.snssdk.com/aweme/v1/playwm/?video_id=" + id).ignoreContentType(true).
						followRedirects(true).execute();
			} catch (IOException e) {
				e.printStackTrace();
			}
			ids.add(response.url().toString());*/
			ids.add("https://aweme.snssdk.com/aweme/v1/playwm/?video_id=" + id);
		}
		return ids;
	}





}
