package com.tm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.tm.interceptor.RestControllerInterceptor;

/**
 * ControllerInterceptorのBean定義クラス
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * Interceptorを登録レジストリに追加します。オーバーライド。
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 実装を提供するInterceptorをレジストリに登録する
        registry.addInterceptor(new RestControllerInterceptor())
            .addPathPatterns("/**");
    }
}
