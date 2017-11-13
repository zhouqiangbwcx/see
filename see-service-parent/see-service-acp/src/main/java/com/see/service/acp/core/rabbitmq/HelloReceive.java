package com.see.service.acp.core.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HelloReceive {

	@RabbitListener(queues = "see_acp") // 监听器监听指定的Queue
	public void processC(String str) {
		System.out.println("Receive:" + str);
	}

}