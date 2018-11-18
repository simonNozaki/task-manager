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
        AppLogger.trace(LogCode.TMFWCM00011.getCode(), null);
        System.out.println("prehandle handled. Executed method : " + this.getHandledMethod(handler));
        return true;
    }

    /**
     * Controllerメソッド正常終了時処理
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception{
        AppLogger.trace(LogCode.TMFWCM00012.getCode(), null);
        System.out.println("Method Executed. Executed method : " + this.getHandledMethod(handler));
    }


    /**
     * ハンドラメソッドから、ハンドリングしたメソッドの情報を取得します。
     * @param handler
     */
    private String getHandledMethod(Object handler) {
        // ハンドラメソッドのインスタンスを引数に取るとき、実行メソッド名を取得します。
        if (handler instanceof HandlerMethod) {
            return ((HandlerMethod) handler).getMethod().getName();
        }
        return null;
    }
}
