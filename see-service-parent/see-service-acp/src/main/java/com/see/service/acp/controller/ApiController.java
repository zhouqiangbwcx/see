package com.see.service.acp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.see.core.common.entitys.ResultBody;
import com.see.core.common.utils.Page;
import com.see.core.common.utils.ResultUtils;
import com.see.service.acp.mapper.SysLogMapper;
import com.see.service.acp.model.SysLog;
import com.see.service.acp.mogodb.dao.RansactionMessageDao;
import com.see.service.rabbitMQ.HelloSender;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private SysLogMapper sysLogMapper;

	@Autowired
	private HelloSender helloSender;

	@Autowired
	private RansactionMessageDao ransactionMessageDao;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public ResultBody get(@RequestParam(defaultValue = "1") Integer slgId) {
		SysLog sysLog = sysLogMapper.selectByPrimaryKey(slgId);

		return ResultUtils.success(sysLog);
	}

	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public ResultBody send() {
		helloSender.send();
		return ResultUtils.success("xxxxx");
	}

	@RequestMapping(value = "/mongo", method = RequestMethod.GET)
	public ResultBody mongo() {
		Page page = new Page();
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("areadlyDead", "no");
		page.setParameter(parameter);
		page = ransactionMessageDao.getPageRansactionMessage(page);
		System.out.println("第oo个：" + JSONObject.toJSONString(page));
		return ResultUtils.success(page);
	}

}
