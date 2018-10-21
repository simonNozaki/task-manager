package com.tm.exception.adviser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.tm.consts.AppLog;

/**
 * Bean共通のインターセプタークラスです.
 */
@Aspect
@Component
public class BeanCommonAspectAdviser extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest servletRequest, HttpServletResponse servletResponse, Object handler) throws Exception {
		System.out.println(AppLog.TMFWCM00011.getCode());
		return true;
	}

	/**
	* ロガーインスタンスの初期化.
	*/
	private final Logger logger = LoggerFactory.getLogger(BeanCommonAspectAdviser.class);

	/**
	 * Serviceコール前にアドバイスを実行します.
	 * @param joinPoint
	 */
	@Before("execution(* com.tm.service.logic.impl.*.*(..))")
	public void invokeBeforeServiceImplement(JoinPoint joinPoint) {
		logger.info(AppLog.TMFWCM00021.getCode());
		System.out.println(AppLog.TMFWCM00021.getCode());
	}

	/**
	 * Serviceコール後にアドバイスを実行します.
	 * @param joinPoint
	 */
	@AfterReturning("execution(* com.tm.service.logic.impl.*.*(..))")
	public void invokeAhterServiceImplement(JoinPoint joinPoint) {
		logger.info(AppLog.TMFWCM00022.getCode());
		System.out.println(AppLog.TMFWCM00022.getCode());
	}

	/**
	 * Controllerコール前にアドバイスを実行します.
	 * @param joinPoint
	 */
	@Before("execution(* com.tm.controller.*.*(*))")
	public void invokeBeforeControllerImplement(JoinPoint joinPoint) {
		logger.info(AppLog.TMFWCM00011.getCode());
		System.out.println(AppLog.TMFWCM00011.getCode());
	}

	/**
	 * Controllerコール後にアドバイスを実行します.
	 * @param joinPoint
	 */
	@AfterReturning("execution(* com.tm.controller.*.*(*))")
	public void invokeAhterControllerImplement(JoinPoint joinPoint) {
		logger.info(AppLog.TMFWCM00012.getCode());
		System.out.println(AppLog.TMFWCM00012.getCode());
	}

}
