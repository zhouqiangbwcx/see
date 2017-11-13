package com.see.common.core.entitys;

public class ResultBody {

	private int code = 0000;
	private Object data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ResultBody(int code, Object data) {
		this.code = code;
		this.data = data;
	}

	public ResultBody(Object data) {

		this.data = data;
	}

	public ResultBody(int code) {
		this.code = code;
	}

	public ResultBody() {
	}

	@Override
	public String toString() {
		return "ResultBody{" + "code=" + code + ", data=" + data + '}';
	}
}