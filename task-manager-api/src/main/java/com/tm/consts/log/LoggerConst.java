package com.tm.consts.log;

/**
 * ログ設定用定数クラスです。
 */
public class LoggerConst {

    //------------------------------------
    // ログレベル
    //------------------------------------
    /**
     * ログレベル、エラー
     */
    public static final String LOG_LEVEL_ERROR = "ERROR";

    /**
     * ログレベル、警告
     */
    public static final String LOG_LEVEL_WARN = "WARN";

    /**
     * ログレベル、インフォ
     */
    public static final String LOG_LEVEL_INFO = "INFO";

    /**
     * ログレベル、トレース
     */
    public static final String LOG_LEVEL_TRACE = "TRACE";


    //------------------------------------
    // ログプレフィックス用レベル別定数
    //------------------------------------
    /**
     * マーカー、エラー
     */
    public static final String LOG_PREFIX_ERROR = "ERROR";

    /**
     * マーカー、警告
     */
    public static final String LOG_PREFIX_WARN = "WARN ";

    /**
     * マーカー、インフォ
     */
    public static final String LOG_PREFIX_INFO = "INFO ";

    /**
     * マーカー、トレース
     */
    public static final String LOG_PREFIX_TRACE = "     ";

    //------------------------------------
    // MDCキー
    //------------------------------------

    /**
     * MDCキー、クラス名
     */
    public static final String CLASS_NAME = "className";

    /**
     * MDCキー、メソッド名
     */
    public static final String METHOD_NAME = "methodName";

    /**
     * MDCキー、ログコード
     */
    public static final String LOG_CODE = "logCode";

    /**
     * MDCキー、ログメッセージ
     */
    public static final String LOG_MESSAGE = "logMessage";

    /**
     * MDCキー、JSONボディ
     */
    public static final String JSON_BODY = "jsonBody";

    //------------------------------------
    // logback出力文字列定数
    //------------------------------------

    /**
     * エラースタックトレース
     */
    public static final String ERR_STACK_TRACE = "errStackTrace";

    /**
     * 電文ログ開始
     */
    public static final String STACKTRACE_START = "----------------------Telegram Trace Start----------------------";

    /**
     * 電文ログ終了
     */
    public static final String STACKTRACE_END = "----------------------Telegram Trace End----------------------";

    /**
     * 例外スタックトレース開始
     */
    public static final String ERR_STACKTRACE_START = "[Handled Exception Start]";

    /**
     * 例外スタックトレース終了
     */
    public static final String ERR_STACKTRACE_END = "[Handled Exception End]";

    /**
     * ログファイル改行コード、\n
     */
    public static final String STR_NEWLINE = "\n";

}
