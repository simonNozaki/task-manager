package com.tm.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tm.consts.AppLog;

/**
 * 業務プロセスのアスペクトクラスです。
 */
@Aspect
public class BusinessProcessAspect {

	/**
	* ロガーインスタンスの初期化.
	*/
	private final Logger logger = LoggerFactory.getLogger(BusinessProcessAspect.class);

	@Before("execution(public * *(..))")
	public void invokePublicMethod(JoinPoint joinPoint) {
		System.out.println("Method Invoked");
	}

	/**
	 * Serviceコール前にアドバイスを実行します.
	 * @param joinPoint
	 */
	@Before("execution(* com.tm.service.logic..*Service.*(..))")
	public void invokeBeforeServiceImplement(JoinPoint joinPoint) {
		logger.trace(AppLog.TMFWCM00021.getCode());
	}

	/**
	 * Serviceコール後にアドバイスを実行します.
	 * @param joinPoint
	 */
	@AfterReturning("execution(* com.tm.service.logic..*Service.*(..))")
	public void invokeAhterServiceImplement(JoinPoint joinPoint) {
		logger.trace(AppLog.TMFWCM00022.getCode());
	}

	/**
	 * Controllerコール前にアドバイスを実行します.
	 * @param joinPoint
	 */
	@Before("execution(* com.tm.controller..*RestController.*(..))")
	public void invokeBeforeControllerImplement(JoinPoint joinPoint) {
		logger.trace(AppLog.TMFWCM00011.getCode());
	}

	/**
	 * Controllerコール後にアドバイスを実行します.
	 * @param joinPoint
	 */
	@AfterReturning("execution(* com.tm.controller..*RestController.*(..))")
	public void invokeAhterControllerImplement(JoinPoint joinPoint) {
		logger.trace(AppLog.TMFWCM00012.getCode());
	}

}
