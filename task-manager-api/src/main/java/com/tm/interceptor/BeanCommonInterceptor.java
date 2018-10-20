package com.tm.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tm.consts.AppLog;

/**
 * Bean共通のインターセプタークラスです.
 */
@Aspect
@Component
public class BeanCommonInterceptor {

	  /**
	  * ロガーインスタンスの初期化.
	  */
	  private final Logger logger = LoggerFactory.getLogger(BeanCommonInterceptor.class);

	/**
	 * Serviceコール前にアドバイスを実行します.
	 * @param joinPoint
	 */
	@Before("execution(*com.tm.service.logic.impl.*.*(..))")
	public void invokeBeforeServiceImplement(JoinPoint joinPoint) {
		logger.info(AppLog.TMFWCM00021.getCode());
	}

	/**
	 * Serviceコール後にアドバイスを実行します.
	 * @param joinPoint
	 */
	@AfterReturning("execution(*com.tm.service.logic.impl.*.*(..))")
	public void invokeAhterServiceImplement(JoinPoint joinPoint) {
		logger.info(AppLog.TMFWCM00022.getCode());
	}

	/**
	 * Controllerコール前にアドバイスを実行します.
	 * @param joinPoint
	 */
	@Before("execution(* * com.tm.controller.*.*(..))")
	public void invokeBeforeControllerImplement(JoinPoint joinPoint) {
		logger.info(AppLog.TMFWCM00011.getCode());
	}

	/**
	 * Controllerコール後にアドバイスを実行します.
	 * @param joinPoint
	 */
	@AfterReturning("execution(* * com.tm.controller.*.*(..))")
	public void invokeAhterControllerImplement(JoinPoint joinPoint) {
		logger.info(AppLog.TMFWCM00012.getCode());
	}

}
