package com.tm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tm.config.AppLogger;
import com.tm.consts.log.LogCode;

/**
 * Controllerのインターセプタークラス
 */
public class RestControllerInterceptor extends HandlerInterceptorAdapter{

    /**
     * Controllerメソッド実行前処理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        if (handler instanceof HandlerMethod) {
            ObjectMapper mapper = new ObjectMapper();
            AppLogger.trace(LogCode.TMFWCM00011, null, getCastedClassNmeForHnadlerMethod(handler), getCastedMethodNmeForHnadlerMethod(handler),
                    mapper.writeValueAsString(((HandlerMethod) handler).getBean()));
        } else {
            AppLogger.trace(LogCode.TMFWCM00011, null, getCastedClassNmeForHnadlerMethod(handler), getCastedMethodNmeForHnadlerMethod(handler), null);
        }
        return true;
    }

    /**
     * Controllerメソッド正常終了時処理
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception{
        if (handler instanceof HandlerMethod) {
            ObjectMapper mapper = new ObjectMapper();
            AppLogger.trace(LogCode.TMFWCM00012, null, getCastedClassNmeForHnadlerMethod(handler), getCastedMethodNmeForHnadlerMethod(handler),
                    mapper.writeValueAsString(((HandlerMethod) handler).getBean().toString()));
        } else {
            AppLogger.trace(LogCode.TMFWCM00012, null, getCastedClassNmeForHnadlerMethod(handler), getCastedMethodNmeForHnadlerMethod(handler), null);
        }
    }

    /**
     * インターセプトしたオブジェクトの型を判定し、HandlerMethoedのインスタンスの場合クラス名を取得します。
     * @param Object handler
     * @return Class<? extends Object>
     */
    private Class<? extends Object> getCastedClassNmeForHnadlerMethod(Object handler) {
        if (handler instanceof HandlerMethod) {
            return ((HandlerMethod) handler).getBean().getClass();
        }
        return handler.getClass();
    }

    /**
     * インターセプトしたオブジェクトの型を判定し、HandlerMethoedのインスタンスの場合メソッド名を取得します。
     * @param Object handler
     * @return String
     */
    private String getCastedMethodNmeForHnadlerMethod(Object handler) {
        if (handler instanceof HandlerMethod) {
            return ((HandlerMethod) handler).getMethod().getName();
        }
        return handler.getClass().getName();
    }

}
