package com.see.service.acp.core.rabbitmq.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.see.service.acp.core.mogodb.entity.RpTransactionMessage;
import com.see.web.acp.finals.RabbitMQFinal;

@Component
public class AccountSender {
	@Autowired
	private AmqpTemplate template;

	public void recharge(RpTransactionMessage rpTransactionMessage) {
		template.convertAndSend(rpTransactionMessage.getConsumerQueue(), rpTransactionMessage);
	}
}
