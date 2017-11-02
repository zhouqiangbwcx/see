package com.see.core.common.enums;

public enum  ResultCode {
	SUCCESS("0000","处理成功")  
    ,EXCEPTION("9999","系统异常")  
    ,NOMETHOD("0001","无对应方法")  
    ,PARAMETER_ERROR("1000","参数不合法")  
    ;

	private String code;
	private String msg;

	public static ResultCode getErrorEnum(String code) {
		for (ResultCode e : ResultCode.values()) {
			if (e.code.equals(code)) {
				return e;
			}
		}
		return null;
	}

	ResultCode(String code, String msg) {  
        this.code = code;  
        this.msg = msg;  
    }

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
}
