package com.tm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tm.consts.CtrlConst;
import com.tm.filter.UserAuthenticationFilter;

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
            .antMatchers(CtrlConst.URI_API_VERSION + CtrlConst.FUNC_USERS + CtrlConst.MAP_AUTHENTICATION).permitAll()
            .anyRequest().authenticated()
            .and().logout()
            .and().csrf().disable()
            .addFilter(new UserAuthenticationFilter(authenticationManager(), bCryptPasswordEncoder()))
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
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
