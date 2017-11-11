package com.see.common.core.entitys;

import com.see.common.core.enums.ResultCode;

public class ResultBody {

	private String code;
	private String msg;
	private Object data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ResultBody(ResultCode errorEnum) {
		this.code = errorEnum.getCode();
		this.msg = errorEnum.getMsg();
	}

	public ResultBody(String code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public ResultBody(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public ResultBody(String code) {
		this.code = code;
	}

	public ResultBody() {
	}

	@Override
	public String toString() {
		return "ResultBody{" + "code=" + code + ", msg='" + msg + '\'' + ", data=" + data + '}';
	}
}