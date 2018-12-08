package com.tm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.tm.config.AppLogger;
import com.tm.consts.LogCode;

/**
 * Controllerのインターセプタークラス
 */
public class RestControllerInterceptor extends HandlerInterceptorAdapter{

    /**
     * Controllerメソッド実行前処理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        AppLogger.trace(LogCode.TMFWCM00011, null, ((HandlerMethod) handler).getBean().getClass(), ((HandlerMethod) handler).getMethod().getName());
        return true;
    }

    /**
     * Controllerメソッド正常終了時処理
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception{
        AppLogger.trace(LogCode.TMFWCM00012, null, ((HandlerMethod) handler).getBean().getClass(), ((HandlerMethod) handler).getMethod().getName());
    }

}
