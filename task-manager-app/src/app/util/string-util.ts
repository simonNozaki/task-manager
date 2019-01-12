/**
 * 文字列操作ユーティリティクラス。
 */
export class StringUtil {

    /**
     * 正規表現、メールアドレス形式
     */
    public static REGEX_FORMAT_EMAIL: RegExp = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

    /**
     * 正規表現、半角英数字
     */
    public static REGEX_FORMAT_HALF_SIZE: RegExp = /^[a-zA-Z0-9]*$/;

}
