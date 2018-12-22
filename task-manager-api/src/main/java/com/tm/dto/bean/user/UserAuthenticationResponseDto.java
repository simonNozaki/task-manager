package com.tm.dto.bean.user;

import com.tm.dto.common.Errors;

/**
 * 利用者認証レスポンスDTOクラス。
 */
public class UserAuthenticationResponseDto {

    /**
     * 認証トークン
     */
    private String authenticationToken;

    /**
     * 利用者ID
     */
    private String userId;

    /**
     * 利用者氏名
     */
    private String userName;

    /**
     * メールアドレス
     */
    private String email;

    /**
     * パスワード
     */
    private String password;

    /**
     * エラー結果
     */
    private Errors errors;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getAuthenticationToken() {
        return authenticationToken;
    }
    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Errors getErrors() {
        return errors;
    }
    public void setErrors(Errors errors) {
        this.errors = errors;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

}
