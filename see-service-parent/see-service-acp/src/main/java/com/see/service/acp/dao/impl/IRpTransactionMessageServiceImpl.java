package com.see.service.acp.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.see.common.core.utils.PageBean;
import com.see.common.core.utils.PageParam;
import com.see.service.acp.dao.IRpTransactionMessageService;
import com.see.web.entity.acp.RpTransactionMessage;

@Service
public class IRpTransactionMessageServiceImpl implements IRpTransactionMessageService {

	@Override
	public int saveMessageWaitingConfirm(RpTransactionMessage rpTransactionMessage) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void confirmAndSendMessage(String messageId) {
		// TODO Auto-generated method stub

	}

	@Override
	public int saveAndSendMessage(RpTransactionMessage rpTransactionMessage) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void directSendMessage(RpTransactionMessage rpTransactionMessage) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reSendMessage(RpTransactionMessage rpTransactionMessage) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reSendMessageByMessageId(String messageId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMessageToAreadlyDead(String messageId) {
		// TODO Auto-generated method stub

	}

	@Override
	public RpTransactionMessage getMessageByMessageId(String messageId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMessageByMessageId(String messageId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reSendAllDeadMessageByQueueName(String queueName, int batchSize) {
		// TODO Auto-generated method stub

	}

	@Override
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return null;
	}

}
