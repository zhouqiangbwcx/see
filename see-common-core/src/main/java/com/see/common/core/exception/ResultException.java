package com.see.common.core.exception;

import com.see.common.core.entitys.ResultBody;

/**
 * 结果异常，会被 ExceptionHandler 捕捉并返回给前端
 *
 * @author see
 * @date 2017/3/28
 */
public class ResultException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7702090986168913626L;
	private String state;
	private String data;

	public ResultException() {
	}

	public ResultException(ResultBody resultBody) {
		super(resultBody.getMsg());
		this.state = resultBody.getCode();
		this.state = resultBody.getMsg();
	}

	public ResultException(String state, String message) {
		super(message);
		this.state = state;
	}

	public ResultException(String message, String state, String data) {
		super(message);
		this.state = state;
		this.data = data;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
