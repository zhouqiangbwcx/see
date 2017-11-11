package com.see.service.acp.core.configure;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

}
