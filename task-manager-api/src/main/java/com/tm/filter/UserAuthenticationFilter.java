package com.tm.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tm.consts.AuthenticationConst;
import com.tm.consts.CtrlConst;
import com.tm.dto.bean.user.UserAuthenticationRequestDto;
import com.tm.exception.TaskManagerErrorRuntimeException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 利用者認証フィルタークラス。トークンを発行して、ヘッダーに設定する処理を提供します。
 */
public class UserAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    private AuthenticationManager authenticationManager;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final String AUTHENTICATION_METHOD_POST = "POST";

    // デフォルトコンストラクタ。
    public UserAuthenticationFilter(AuthenticationManager authenticationManager, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authenticationManager = authenticationManager;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

        // 認証用のpathを変更する
        setRequiresAuthenticationRequestMatcher(
                new AntPathRequestMatcher(CtrlConst.URI_API_VERSION + CtrlConst.FUNC_USERS + CtrlConst.MAP_AUTHENTICATION, AUTHENTICATION_METHOD_POST));

        // 認証用のID（メールアドレス）、パスワードのパラメータ名を変更する
        setUsernameParameter(AuthenticationConst.EMAIL);
        setPasswordParameter(AuthenticationConst.PASSWORD);
    }

    /**
     * 認証機能を提供します。
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            // リクエストボディのパース
            UserAuthenticationRequestDto reqBody = new ObjectMapper().readValue(req.getInputStream(), UserAuthenticationRequestDto.class);

            // メールアドレス、パスワードを設定して認証結果を返却します
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            reqBody.getEmail(),
                            reqBody.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new TaskManagerErrorRuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
        Authentication auth) throws IOException, ServletException {

        // ヘッダーに設定するJWTを生成します。
        String token = Jwts.builder()
                .setSubject(((UserAuthenticationRequestDto)auth.getPrincipal()).getEmail())
                .setExpiration(new Date(System.currentTimeMillis() + AuthenticationConst.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512,AuthenticationConst. SECRET.getBytes())
                .compact();

        // トークンをヘッダーに設定します。
        res.addHeader(AuthenticationConst.HEADER_STRING, AuthenticationConst.TOKEN_PREFIX + token);
    }
}
