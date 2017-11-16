package com.see.web.mis.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.see.common.core.cahe.JedisClient;
import com.see.common.core.utils.UUIDBuild;

@Controller

public class TemplateController {
	/**
	 * 
	 * 返回html模板.
	 * 
	 */

	@RequestMapping("/helloHtml")
	public String helloHtml(Map<String, Object> map) {

		map.put("hello", "from TemplateController.helloHtml");
		return "/helloHtml";
	}
}
