package com.see.service.acp.core.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.see.service.acp.core.mogodb.entity.RpTransactionMessage;

@Component
public class HelloReceive {

	@RabbitListener(queues = "see_acp") // 监听器监听指定的Queue
	public void processC(RpTransactionMessage rpTransactionMessage) {
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxx:" + JSONObject.toJSONString(rpTransactionMessage));
	}

}