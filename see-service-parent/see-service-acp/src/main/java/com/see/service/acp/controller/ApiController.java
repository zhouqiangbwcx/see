package com.see.service.acp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.see.core.common.entitys.ResultBody;
import com.see.core.common.utils.ResultUtils;
import com.see.service.acp.mapper.SysLogMapper;
import com.see.service.acp.model.SysLog;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private SysLogMapper sysLogMapper;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public ResultBody get(@RequestParam(defaultValue = "1") Integer slgId) {
		SysLog sysLog = sysLogMapper.selectByPrimaryKey(slgId);

		return ResultUtils.success(sysLog);
	}
}
