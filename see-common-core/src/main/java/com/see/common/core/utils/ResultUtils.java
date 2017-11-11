package com.see.common.core.utils;

import com.alibaba.fastjson.JSONObject;
import com.see.common.core.entitys.ResultBody;
import com.see.common.core.enums.ResultCode;
import com.see.common.core.exception.ResultException;

public class ResultUtils {

	public static ResultBody success(Object data) {
		/* return new ResultBody<>(ResultCode.SUCCESS, data); */
		return new ResultBody(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), data);
	}

	public static ResultBody warn(ResultCode resultCode, String msg) {
		ResultBody result = new ResultBody(resultCode);
		result.setMsg(msg);
		return result;
	}

	/**
	 * 错误返回
	 * 
	 * @param ResultCode
	 * @return
	 */
	public static ResultBody warn(ResultCode resultCode) {
		return new ResultBody(resultCode);
	}

	/**
	 * 自定义错误返回结果
	 * 
	 * @param state
	 * @param msg
	 * @param data
	 * @return
	 */
	public static ResultBody warn(String state, String msg, String data) {
		return new ResultBody(state, msg, data);
	}

	/**
	 * 判断是否请求成功,不成功throw new ResultException(异常）
	 * 
	 * @param resultBody
	 * @return
	 */
	public static String noSuccessThrow(ResultBody resultBody) {
		if (ResultCode.SUCCESS.getCode().equals(resultBody.getCode())) {
			return JSONObject.toJSONString(resultBody.getData());
		} else {
			throw new ResultException(resultBody.getCode(), resultBody.getMsg());
		}

	}

}