package com.tm.dto.bean.user;

/**
 * 利用者認証レスポンスDTOクラス。
 */
public class UserAuthenticationResponseDto {

    /**
     * 認証トークン
     */
    private String authenticationToken;

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

}
