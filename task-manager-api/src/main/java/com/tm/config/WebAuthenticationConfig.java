package com.tm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tm.consts.CtrlConst;

/**
 * 認証機能設定クラス。
 */
@Configuration
@EnableWebSecurity
public class WebAuthenticationConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
            .and().authorizeRequests()
            .antMatchers(CtrlConst.URI_API_VERSION + CtrlConst.FUNC_USERS + CtrlConst.MAP_AUTHENTICATION, "/token", CtrlConst.URI_API_VERSION + CtrlConst.FUNC_TASKS + CtrlConst.MAP_FETCH)
            .permitAll()
            .anyRequest().authenticated() // 全てのリクエストは認証済みユーザのみアクセス可能
//            .and()
//            .csrf() // CSRF対策サポート、デフォルトは有効になっている
//            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // CSRFトークンをCookieに埋め込み
//            .addFilter(new UserAuthenticationFilter(authenticationManager(), bCryptPasswordEncoder()))
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            ;
        ;
    }

    /**
     * パスワードハッシュBeanをインジェクションします。
     * @return BCryptPasswordEncoder パスワードエンコーダクラス。Spring Securityのクラス。
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
