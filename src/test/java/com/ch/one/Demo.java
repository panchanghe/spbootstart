package com.ch.one;

import cn.hutool.http.HttpUtil;
import org.junit.Test;

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
}
