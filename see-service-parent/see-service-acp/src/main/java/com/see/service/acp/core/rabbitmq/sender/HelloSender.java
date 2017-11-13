package com.see.service.acp.core.rabbitmq.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloSender {
	@Autowired
	private AmqpTemplate template;

	public void send() {
		template.convertAndSend("see_acp", "中文的。。。。");
	}
}