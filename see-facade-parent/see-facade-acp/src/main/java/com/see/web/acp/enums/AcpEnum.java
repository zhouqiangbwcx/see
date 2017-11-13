package com.see.web.acp.enums;

public enum AcpEnum {
	RPTRANSACTIONMESSAGE_STATUS_CONFIRM("WAITING_CONFIRM", "待确认"),
	RPTRANSACTIONMESSAGE_STATUS_SENDING("SENDING", "发送中"),
	AREADLYDEAD_YES("YES", "死亡"),
	AREADLYDEAD_NO("NO", "正常"),
	
    ;
	// 成员变量
	private String name;
	private String desc;

	// 构造方法
	private AcpEnum(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}

	// 普通方法
	public static String getName(String desc) {
		for (AcpEnum c : AcpEnum.values()) {
			if (c.getDesc().equals(desc)) {
				return c.name;
			}
		}
		return null;
	}

	// get set 方法
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
