package com.ch.one.core.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

/**
 * @Auther: pch
 * @Date: 2018/12/4 09:24
 * @Description:
 */
@Component
public class JedisUtil {

	private String host = "116.62.153.46";
	private int port = 6379;
	private JedisPool jedisPool;
	private Jedis jedis;

	public JedisUtil(){
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(50);
		config.setMaxIdle(10);
		System.err.println(host);
		System.err.println(port);
		jedisPool = new JedisPool(config, host, port);
		jedis = jedisPool.getResource();
	}


	public Jedis getJedis(){
		Jedis jedis = jedisPool.getResource();
		jedis.set("name", "aaaaaaaaaaaaa");
		return jedis;
	}

	public List<String> lrange(String key,long start,long end){
		List<String> list = jedis.lrange(key, start, end);
		return list;
	}


}
