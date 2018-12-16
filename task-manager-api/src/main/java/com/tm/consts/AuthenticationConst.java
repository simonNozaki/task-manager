package com.tm.consts;

/**
 * 認証機能定数クラス。
 */
public class AuthenticationConst {

    /**
     * エンコード文字列の鍵bit長
     */
    public static final String SECRET = "nyasbasamplesecret";

    /**
     * 有効期間、8時間
     */
    public static final long EXPIRATION_TIME = 28_800_000; // 8hours

    /**
     * トークンプレフィックス
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * HTTPヘッダー文字列。Authorization
     */
    public static final String HEADER_STRING = "Authorization";

    /**
     * 認証情報、メールアドレス
     */
    public static final String EMAIL = "email";

    /**
     * 認証情報、パスワード
     */
    public static final String PASSWORD = "password";

    /**
     * セッション情報から取得するマップキー、トークン
     */
    public static final String SESSION_MAP_TOKEN = "token";
}
