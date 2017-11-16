package com.see.common.service.advice;

import org.apache.log4j.Logger;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.see.common.core.entitys.ResultBody;
import com.see.common.core.exception.BizException;

/**
 * @author Chery
 * @date 2017/5/1 - 下午1:39
 */
@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice {
	private final Logger logger = Logger.getLogger(ExceptionHandlerAdvice.class);

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResultBody handle(Exception e) {
		logger.error("统一异常 start" + e.toString());
		logger.error(e);
		e.printStackTrace();
		if (e instanceof BizException) {
			BizException liuluException = (BizException) e;
			return new ResultBody(liuluException.getCode(), liuluException.getMsg());
		}
		logger.error("统一异常 end" + e.getMessage());
		return new ResultBody(9999, e.getMessage());
	}
}