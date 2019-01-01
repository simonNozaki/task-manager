package com.tm.config;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tm.interceptor.RestControllerInterceptor;

/**
 * WebMvcCOnfigurerBean定義クラス
 */
@Configuration
@EnableWebMvc
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
     * HTTPリクエストのJSONパース形式をカスタマイズします。
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT) // インデント付きでシリアライズ
                .enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)  // 空の配列[]をnullでデシリアライズ
                .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                .enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);  // Bean定義にないプロパティは無視する

        converters.add(new MappingJackson2HttpMessageConverter(objectMapper));
        converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
    }

}
