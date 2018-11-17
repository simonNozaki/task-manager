package com.tm.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import com.tm.config.AppLogger;
import com.tm.consts.LogCode;

/**
 * 業務プロセスのアスペクトクラスです。
 */
@Aspect
public class BusinessProcessAspect {

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
		AppLogger.trace(LogCode.TMFWCM00021.getCode(), null);
	}

	/**
	 * Serviceコール後にアドバイスを実行します.
	 * @param joinPoint
	 */
	@AfterReturning("execution(* com.tm.service.logic..*Service.*(..))")
	public void invokeAhterServiceImplement(JoinPoint joinPoint) {
		AppLogger.trace(LogCode.TMFWCM00022.getCode(), null);
	}

	/**
	 * Controllerコール前にアドバイスを実行します.
	 * @param joinPoint
	 */
	@Before("execution(* com.tm.controller..*RestController.*(..))")
	public void invokeBeforeControllerImplement(JoinPoint joinPoint) {
		AppLogger.trace(LogCode.TMFWCM00011.getCode(), null);
	}

	/**
	 * Controllerコール後にアドバイスを実行します.
	 * @param joinPoint
	 */
	@AfterReturning("execution(* com.tm.controller..*RestController.*(..))")
	public void invokeAhterControllerImplement(JoinPoint joinPoint) {
		AppLogger.trace(LogCode.TMFWCM00012.getCode(), null);
	}

}
