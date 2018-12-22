package com.tm.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

import com.tm.config.AppLogger;
import com.tm.consts.log.LogCode;

/**
 * サービスインターセプター
 */
@Component
public class ServiceInterceptor implements MethodInterceptor{

    /**
     * サービスクラス実行時にトレースログを出力します。
     */
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        AppLogger.trace(LogCode.TMFWCM00021, null, invocation.getThis().getClass(), invocation.getMethod().getName(), null);
        Object response = invocation.proceed();
        AppLogger.trace(LogCode.TMFWCM00022, null, invocation.getThis().getClass(), invocation.getMethod().getName(), null);
        return response;
    }

}
