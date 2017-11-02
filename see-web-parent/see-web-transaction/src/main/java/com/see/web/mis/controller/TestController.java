package com.see.web.mis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.see.core.common.cahe.JedisClient;
import com.see.core.common.utils.UUIDBuild;

@Controller
@RequestMapping("/goodsBrand")
public class TestController {
	@Autowired
	private JedisClient jedisClient;

	
	public void gets() {
		jedisClient.set("test", "ok");
		System.out.println(jedisClient.get("test"));
		System.out.println(UUIDBuild.getUUID("test1", jedisClient));
	}
}
