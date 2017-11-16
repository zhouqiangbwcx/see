package com.see.service.acp.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.see.common.core.cahe.JedisClient;
import com.see.common.core.entitys.ResultBody;
import com.see.common.core.utils.PageBean;
import com.see.common.core.utils.PageParam;
import com.see.common.core.utils.StringUtils;
import com.see.service.acp.core.mogodb.dao.RpTransactionMessageDao;
import com.see.service.acp.core.mogodb.entity.RpTransactionMessage;
import com.see.service.acp.core.rabbitmq.sender.AccountSender;
import com.see.service.acp.dao.IRpTransactionMessageService;
import com.see.web.acp.dto.rpTransactionMessage.RpTransactionMessageDTO;
import com.see.web.acp.enums.AcpEnum;
import com.see.web.acp.exceptions.MessageBizException;

@Service
public class IRpTransactionMessageServiceImpl implements IRpTransactionMessageService {
	@Autowired
	private RpTransactionMessageDao rpTransactionMessageDao;
	@Autowired
	private AccountSender accountSender;

	@Autowired
	private JedisClient jedisClient;

	private final String REDISKEY_RP = "RP";

	/**
	 * 预存储消息.
	 */
	@Override
	public void saveMessageWaitingConfirm(RpTransactionMessageDTO rpTransactionMessageDTO) {
		RpTransactionMessage rtm = getMessageByMessageId(rpTransactionMessageDTO.getMessageId());
		if (null == rtm) {
			rtm = JSONObject.parseObject(JSONObject.toJSONString(rpTransactionMessageDTO), RpTransactionMessage.class);
			rtm.setStatus(AcpEnum.RPTRANSACTIONMESSAGE_STATUS_CONFIRM.name());
			rtm.setAreadlyDead(AcpEnum.AREADLYDEAD_NO.name());
			rpTransactionMessageDao.saveRpTransactionMessage(rtm);
		} else {
			throw new MessageBizException(MessageBizException.SAVA_MESSAGE_IS_NO_NULL, "消息的消费队列已经存在 ");
		}
	}

	/**
	 * 确认并发送消息.
	 */
	@Override
	public void confirmAndSendMessage(String messageId) {
		final RpTransactionMessage message = getMessageByMessageId(messageId);
		if (message == null) {
			throw new MessageBizException(MessageBizException.SAVA_MESSAGE_IS_NULL, "根据消息id查找的消息为空");
		}
		if (StringUtils.isNullOrEmpty(jedisClient.get(REDISKEY_RP + messageId))) {
			message.setStatus(AcpEnum.RPTRANSACTIONMESSAGE_STATUS_SENDING.name());
			message.setEditTime(new Date());
			rpTransactionMessageDao.editRpTransactionMessageById(message);// 修改

			jedisClient.set(REDISKEY_RP + messageId,
					JSONObject.toJSONString(rpTransactionMessageDao.findRpTransactionMessageByMessageId(messageId)), 5);

			accountSender.recharge(message);// 发送
		}

	}

	/**
	 * 存储并发送消息.
	 */
	@Override
	public void saveAndSendMessage(RpTransactionMessageDTO rpTransactionMessageDTO) {

		if (rpTransactionMessageDTO == null) {
			throw new MessageBizException(MessageBizException.SAVA_MESSAGE_IS_NULL, "保存的消息为空");
		}

		if (StringUtils.isNoNullOrEmpty(rpTransactionMessageDTO.getConsumerQueue())) {
			throw new MessageBizException(MessageBizException.MESSAGE_CONSUMER_QUEUE_IS_NULL, "消息的消费队列不能为空 ");
		}
		RpTransactionMessage rtm = getMessageByMessageId(rpTransactionMessageDTO.getMessageId());
		if (null == rtm) {
			rtm = JSONObject.parseObject(JSONObject.toJSONString(rpTransactionMessageDTO), RpTransactionMessage.class);
			rtm.setEditTime(new Date());
			rtm.setStatus(AcpEnum.RPTRANSACTIONMESSAGE_STATUS_SENDING.name());
			rtm.setAreadlyDead(AcpEnum.AREADLYDEAD_NO.name());
			rpTransactionMessageDao.saveRpTransactionMessage(rtm);
			confirmAndSendMessage(rtm.getMessageId());// 确认并发送消息.
		} else {
			throw new MessageBizException(MessageBizException.SAVA_MESSAGE_IS_NO_NULL, "消息的消费队列已经存在 ");
		}

	}

	/**
	 * 根据messageId重发某条消息.
	 */
	@Override
	public void reSendMessageByMessageId(String messageId) {
		if (StringUtils.isNullOrEmpty(jedisClient.get(REDISKEY_RP + messageId))) {
			final RpTransactionMessage message = getMessageByMessageId(messageId);
			message.setEditTime(new Date());

			rpTransactionMessageDao.editRpTransactionMessageById(message);
			accountSender.recharge(message);// 发送
		}
	}

	/**
	 * 将消息标记为死亡消息.
	 */
	@Override
	public void setMessageToAreadlyDead(String messageId) {
		RpTransactionMessage message = getMessageByMessageId(messageId);
		if (message == null) {
			throw new MessageBizException(MessageBizException.SAVA_MESSAGE_IS_NULL, "根据消息id查找的消息为空");
		}
		message.setAreadlyDead(AcpEnum.AREADLYDEAD_YES.name());
		message.setEditTime(new Date());

	}

	/**
	 * 根据消息ID获取消息
	 */
	@Override
	public RpTransactionMessage getMessageByMessageId(String messageId) {
		RpTransactionMessage message = rpTransactionMessageDao.findRpTransactionMessageByMessageId(messageId);
		return message;
	}

	/**
	 * 根据消息ID删除消息
	 */
	@Override
	public void deleteMessageByMessageId(String messageId) {
		rpTransactionMessageDao.deleteRpTransactionMessageByMessageId(messageId);
	}

	/**
	 * 重发某个消息队列中的全部已死亡的消息.
	 */
	@Override
	public void reSendAllDeadMessageByQueueName(String queueName) {
		PageParam pageParam = new PageParam();
		Map<String, Object> countResultMap = new HashMap<String, Object>();
		countResultMap.put("consumerQueue", queueName);
		pageParam.setNumPerPage(1000);
		pageParam.setPageNum(1);
		PageBean pageBean = listPage(pageParam, countResultMap);
		List<RpTransactionMessage> rtmList = pageBean.getRecordList();
		for (RpTransactionMessage rpTransactionMessage : rtmList) {
			reSendMessageByMessageId(rpTransactionMessage.getMessageId());
		}
		if (pageBean.getTotalPage() > 1) {
			reSendAllDeadMessageByQueueName(queueName);
		}

	}

	/**
	 * 获取分页数据
	 */
	@Override
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {

		PageBean pageBean = rpTransactionMessageDao.getPageRpTransactionMessage(pageParam, paramMap);
		return pageBean;
	}

}
