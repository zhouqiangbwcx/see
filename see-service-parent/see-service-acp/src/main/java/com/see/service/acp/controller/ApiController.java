package com.see.service.acp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.see.common.core.entitys.ResultBody;
import com.see.service.acp.core.rabbitmq.sender.HelloSender;
import com.see.service.acp.dao.IRpTransactionMessageService;

@RestController
@RequestMapping("/api")
public class ApiController {

	//@Autowired
	//private SysLogMapper sysLogMapper;

	@Autowired
	private HelloSender helloSender;

	@Autowired
	private IRpTransactionMessageService rpTransactionMessageService;

	/*
	 * @Autowired private RansactionMessageDao ransactionMessageDao;
	 */

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public ResultBody get(@RequestParam(defaultValue = "1") Integer slgId) {
		//SysLog sysLog = sysLogMapper.selectByPrimaryKey(slgId);

		return new ResultBody();
	}

	/*
	 * @RequestMapping(value = "/send", method = RequestMethod.GET) public
	 * ResultBody send() { helloSender.send(); return
	 * ResultUtils.success("xxxxx"); }
	 * 
	 * @RequestMapping(value = "/mongo", method = RequestMethod.GET) public
	 * ResultBody mongo() { PageBean page = new PageBean(); Map<String, Object>
	 * parameter = new HashMap<String, Object>(); parameter.put("areadlyDead",
	 * "no"); page.setCountResultMap(parameter); page =
	 * ransactionMessageDao.getPageRansactionMessage(page);
	 * System.out.println("第oo个：" + JSONObject.toJSONString(page)); return
	 * ResultUtils.success(page); }
	 */

}
