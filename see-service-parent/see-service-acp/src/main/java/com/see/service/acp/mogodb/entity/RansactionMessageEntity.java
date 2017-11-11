package com.see.service.acp.mogodb.entity;

import java.io.Serializable;
import java.util.Date;

public class RansactionMessageEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3983446608422049675L;
	/**
	 * 主键ID
	 */
	private String id;
	/**
	 * '版本号'
	 */
	private Integer version;
	/**
	 * '修改者'
	 */
	private String editor;
	/**
	 * '创建者'
	 */
	private String creater;
	/**
	 * '最后修改时间'
	 */
	private Date editTime;
	/**
	 * '创建时间'
	 */
	private Date createTime;
	/**
	 * '消息ID'
	 */
	private String messageId;
	/**
	 * '消息内容'
	 */
	private String messageBody;
	/**
	 * 消息数据类型
	 */
	private String messageDataType;
	/**
	 * 消费队列
	 */
	private String consumerQueue;
	/**
	 * '消息下次重发时间'
	 */
	private Date sendTime;
	/**
	 * '消息重发次数'
	 */
	private int messageSendTimes;
	/**
	 * '是否死亡'
	 */
	private String areadlyDead;
	/**
	 * '状态'
	 */
	private String status;
	/**
	 * '备注'
	 */
	private String remark;
	/**
	 * 扩展字段1
	 */
	private String field1;
	/**
	 * 扩展字段3
	 */
	private String field2;
	/**
	 * 扩展字段2
	 */
	private String field3;

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public int getMessageSendTimes() {
		return messageSendTimes;
	}

	public void setMessageSendTimes(int messageSendTimes) {
		this.messageSendTimes = messageSendTimes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor == null ? null : editor.trim();
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater == null ? null : creater.trim();
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId == null ? null : messageId.trim();
	}

	public String getMessageDataType() {
		return messageDataType;
	}

	public void setMessageDataType(String messageDataType) {
		this.messageDataType = messageDataType == null ? null : messageDataType.trim();
	}

	public String getConsumerQueue() {
		return consumerQueue;
	}

	public void setConsumerQueue(String consumerQueue) {
		this.consumerQueue = consumerQueue == null ? null : consumerQueue.trim();
	}

	public String getAreadlyDead() {
		return areadlyDead;
	}

	public void setAreadlyDead(String areadlyDead) {
		this.areadlyDead = areadlyDead == null ? null : areadlyDead.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1 == null ? null : field1.trim();
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2 == null ? null : field2.trim();
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3 == null ? null : field3.trim();
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody == null ? null : messageBody.trim();
	}
}