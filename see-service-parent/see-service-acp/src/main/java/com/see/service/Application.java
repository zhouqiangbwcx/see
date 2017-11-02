package com.see.service;

import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ImportResource;

@ServletComponentScan
@SpringBootApplication
@ImportResource(locations = { "classpath:config/druid-bean.xml" })
@EnableDiscoveryClient // 启动EnableEureka客户端
public class Application implements CommandLineRunner {
	private final Logger logger = Logger.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("启动完成！");

	}
}
