package com.see.service.common.advice;

import org.apache.log4j.Logger;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.see.core.common.entitys.ResultBody;
import com.see.core.common.enums.ResultCode;
import com.see.core.common.exception.ResultException;
import com.see.core.common.utils.ResultUtils;

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
		if (e instanceof ResultException) {
			ResultException liuluException = (ResultException) e;
			return ResultUtils.warn(liuluException.getState(), liuluException.getMessage(), liuluException.getData());
		}
		logger.error("统一异常 end" + e.getMessage());
		return ResultUtils.warn(ResultCode.EXCEPTION, e.getMessage());
	}
}