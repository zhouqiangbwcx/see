package com.see.common.service.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;

@Aspect
@Component
@Order(-5)
public class WebLogAspect {

	private Logger logger = Logger.getLogger(WebLogAspect.class);

	/**
	 * 定义AOP扫描路径 第一个注解只扫描aopTest方法
	 */
	// @Pointcut("execution(public *
	// com.aston.reader.controller.StudentController.aopTest())")
	@Pointcut("execution(public * com..controller.*.*(..))")
	public void logger() {

	}

	@Before("logger()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>> ");
		// 记录下请求内容
		logger.info("请求地址 : " + request.getRequestURL().toString());
		logger.info("HTTP METHOD : " + request.getMethod());
		logger.info("IP : " + request.getRemoteAddr());
		logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName());
		logger.info("参数 : " + Arrays.toString(joinPoint.getArgs()));

	}

	@Around("logger()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object ob = pjp.proceed();// ob 为方法的返回值
		logger.info("耗时 : " + (System.currentTimeMillis() - startTime));
		return ob;
	}

	@AfterReturning(returning = "ret", pointcut = "logger()") // returning的值和doAfterReturning的参数名一致
	public void doAfterReturning(Object ret) throws Throwable {
		// 处理完请求，返回内容
		logger.info("返回值 : " + JSONObject.toJSONString(ret));
		logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}
}
