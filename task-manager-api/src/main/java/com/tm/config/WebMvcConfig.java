package com.tm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.tm.consts.CtrlConst;
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

    /**
     * カスタム認証トークンヘッダーをCORSに対して許可します。
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(CtrlConst.URI_API_VERSION)
                .allowedHeaders("x-auth-token");
    }

}
