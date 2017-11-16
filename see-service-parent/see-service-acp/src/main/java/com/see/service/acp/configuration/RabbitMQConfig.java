package com.see.service.acp.configuration;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.see.web.acp.finals.RabbitMQFinal;

/**
 * <dl>
 * <dt>ConnectionFactory</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 2017/8/31</dd>
 * </dl>
 *
 * @author Administrator
 */
@Configuration
public class RabbitMQConfig {
	@Bean
	public Queue queue() {
		return new Queue("see_acp", true);
	}
	/**
	 * 充值成功
	 * @return
	 */
	@Bean
	public Queue account_recharge() {
		return new Queue(RabbitMQFinal.ACCOUNT_RECHARGE, true);
	}

}
