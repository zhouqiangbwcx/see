
package com.see.service.acp.dao;

import java.util.Map;

import com.see.common.core.utils.PageBean;
import com.see.common.core.utils.PageParam;
import com.see.service.acp.core.mogodb.entity.RpTransactionMessage;
import com.see.web.acp.dto.rpTransactionMessage.RpTransactionMessageDTO;

public interface IRpTransactionMessageService {

	/**
	 * 预存储消息.
	 */
	public void saveMessageWaitingConfirm(RpTransactionMessageDTO rpTransactionMessageDTO);

	/**
	 * 确认并发送消息.
	 */
	public void confirmAndSendMessage(String messageId);

	/**
	 * 存储并发送消息.
	 */
	public void saveAndSendMessage(RpTransactionMessageDTO rpTransactionMessageDTO);




	/**
	 * 根据messageId重发某条消息.
	 */
	public void reSendMessageByMessageId(String messageId);

	/**
	 * 将消息标记为死亡消息.
	 */
	public void setMessageToAreadlyDead(String messageId);

	/**
	 * 根据消息ID获取消息
	 */
	public RpTransactionMessage getMessageByMessageId(String messageId);

	/**
	 * 根据消息ID删除消息
	 */
	public void deleteMessageByMessageId(String messageId);

	/**
	 * 重发某个消息队列中的全部已死亡的消息.
	 */
	public void reSendAllDeadMessageByQueueName(String queueName);

	/**
	 * 获取分页数据
	 */
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap);

}
