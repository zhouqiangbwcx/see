package com.see.service.callbak;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.see.service.configure.RabbitMQConfig;

@Service
public class ProductService implements RabbitTemplate.ConfirmCallback {

	private RabbitTemplate rabbitTemplate;

	public ProductService(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
		this.rabbitTemplate.setConfirmCallback(this);
	}

	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		System.out.println(" x消息id:" + correlationData);
		if (ack) {
			System.out.println("消息发送确认成功");
		} else {
			System.out.println("消息发送确认失败:" + cause);

		}
	}

	public void send(String msg) {
		// 执行保存
		String uuid = UUID.randomUUID().toString();
		CorrelationData correlationId = new CorrelationData(uuid);
		rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTINGKEY1, msg, correlationId);
	}
}