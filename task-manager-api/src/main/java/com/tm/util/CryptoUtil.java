package com.tm.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * 暗号処理ユーティリティ
 */
public class CryptoUtil {

    @Autowired
    private static PasswordEncoder passwordEncoder;

    /**
     * パスワードエンコードのBean作成
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 平文のパスワードをハッシュにします。
     * @param password 平文のパスワード
     * @return String ハッシュにしたパスワード文字列
     */
    public static String encode(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    /**
     * ハッシュにしたパスワードと平文のパスワードが一致していることを確認します。
     * @param rawPassword 平文のパスワード
     * @param encodedPassword ハッシュにしたパスワード
     * @return 真偽値、デフォルトはfalse
     */
    public static boolean match(String rawPassword, String encodedPassword) {
        if (new BCryptPasswordEncoder().matches(rawPassword, encodedPassword)) {
            return true;
        }
        return false;
    }

}
