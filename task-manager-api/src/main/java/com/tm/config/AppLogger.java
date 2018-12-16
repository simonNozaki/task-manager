package com.tm.config;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.tm.config.logger.AppTelegramLogger;
import com.tm.config.logger.AppTraceLogger;
import com.tm.consts.LogCode;
import com.tm.consts.LoggerConst;

import ch.qos.logback.classic.Level;

/**
 * 共通ログ出力設定クラスです.
 */
public class AppLogger {

    // MDCキーの定数
    private static final String CLASS_NAME = "className";
    private static final String METHOD_NAME = "methodName";
    private static final String LOG_CODE = "logCode";
    private static final String LOG_MESSAGE = "logMessage";
    private static final String JSON_BODY = "jsonBody";

    // ロガー用定数
    private static final String ERR_STACK_TRACE = "errStackTrace";
    private static final String STACKTRACE_START = "[Telegram Trace Start]";
    private static final String STACKTRACE_END = "[Telegram Trace End]";
    private static final String STR_NEWLINE = "\n";

	/**
	 * ロガーインスタンス、トレースログ用
	 */
	private static final Logger appTraceINSTANCE = LoggerFactory.getLogger(AppTraceLogger.class.getCanonicalName());

	/**
	 * ロガーインスタンス、電文ログ用
	 */
	private static final Logger appTelegramINSTANCE = LoggerFactory.getLogger(AppTelegramLogger.class.getCanonicalName());

	/**
	 * ログレベルに応じたログ出力を実施します。<br>
	 * bodyを指定せず、nullが設定された場合MDCのキーを設定しません。
	 * @param String level
	 * @param String msg
	 * @param Throwable th
	 * @param Object className
     * @param Object methodName
     * @param String body
	 */
	private static void log(String level, LogCode logCode, Throwable th, Object className, Object methodName, String body) {
	    // MDCを初期化
	    MDC.put(CLASS_NAME, className.toString());
	    MDC.put(METHOD_NAME, methodName.toString());
	    MDC.put(LOG_CODE, logCode.getCode());
	    MDC.put(LOG_MESSAGE, logCode.getMessage());
	    if (th != null) {
	        MDC.put(ERR_STACK_TRACE, getStackTraceString(th));
	    }

	    // マーカーを初期化
	    Marker logPrefix;

	    // 例外を受け付けられる形に変換する
	    Optional<Throwable> exception = Optional.ofNullable(th);

	    // ログレベルをロガーに入力
	    ch.qos.logback.classic.Logger loggerInstance = (ch.qos.logback.classic.Logger)appTraceINSTANCE;

	    // レベル別にログを出力
	    switch(level) {
	        case LoggerConst.LOG_LEVEL_ERROR:
	            loggerInstance.setLevel(Level.ERROR);
	            logPrefix = MarkerFactory.getMarker(LoggerConst.LOG_PREFIX_ERROR);
	            appTraceINSTANCE.error(logPrefix, logCode.getCode(), exception);
	            break;
	        case LoggerConst.LOG_LEVEL_WARN:
	            loggerInstance.setLevel(Level.WARN);
	            logPrefix = MarkerFactory.getMarker(LoggerConst.LOG_PREFIX_WARN);
	            appTraceINSTANCE.error(logPrefix, logCode.getCode(), exception);
                break;
	        case LoggerConst.LOG_LEVEL_INFO:
                loggerInstance.setLevel(Level.INFO);
	            logPrefix = MarkerFactory.getMarker(LoggerConst.LOG_PREFIX_INFO);
	            appTraceINSTANCE.info(logPrefix, logCode.getCode(), exception);
	            break;
	        case LoggerConst.LOG_LEVEL_TRACE:
	            loggerInstance.setLevel(Level.TRACE);
	            logPrefix = MarkerFactory.getMarker(LoggerConst.LOG_PREFIX_TRACE);
	            appTraceINSTANCE.trace(logPrefix, logCode.getCode(), exception);
                break;
	        default:
	            break;
	    }
	    // MDCのクリア
	    clearLocalMDC();
	}

	/**
	 * 電文ログの出力実施を提供します。
	 * @param logLevelInfo
	 * @param logCode
	 * @param object
	 * @param className
	 * @param methodName
	 * @param body
	 */
	private static void logTelegram(String level, LogCode logCode, Object className, Object methodName, String body) {
	 // MDCを初期化
        MDC.put(CLASS_NAME, className.toString());
        MDC.put(METHOD_NAME, methodName.toString());
        MDC.put(LOG_CODE, logCode.getCode());
        MDC.put(LOG_MESSAGE, logCode.getMessage());
        MDC.put(JSON_BODY, getStackTraceString(body));

        // マーカーを初期化
        Marker logPrefix;

        // ログレベルをロガーに入力
        ch.qos.logback.classic.Logger loggerInstance = (ch.qos.logback.classic.Logger)appTelegramINSTANCE;

        // レベル別にログを出力
        switch(level) {
            case LoggerConst.LOG_LEVEL_ERROR:
                loggerInstance.setLevel(Level.ERROR);
                logPrefix = MarkerFactory.getMarker(LoggerConst.LOG_PREFIX_ERROR);
                appTelegramINSTANCE.error(logPrefix, logCode.getCode());
                break;
            case LoggerConst.LOG_LEVEL_WARN:
                loggerInstance.setLevel(Level.WARN);
                logPrefix = MarkerFactory.getMarker(LoggerConst.LOG_PREFIX_WARN);
                appTelegramINSTANCE.error(logPrefix, logCode.getCode());
                break;
            case LoggerConst.LOG_LEVEL_INFO:
                loggerInstance.setLevel(Level.INFO);
                logPrefix = MarkerFactory.getMarker(LoggerConst.LOG_PREFIX_INFO);
                appTelegramINSTANCE.info(logPrefix, logCode.getCode());
                break;
            case LoggerConst.LOG_LEVEL_TRACE:
                loggerInstance.setLevel(Level.TRACE);
                logPrefix = MarkerFactory.getMarker(LoggerConst.LOG_PREFIX_TRACE);
                appTelegramINSTANCE.trace(logPrefix, logCode.getCode());
                break;
            default:
                break;
        }
        // MDCのクリア
        clearLocalMDC();

	}

	/**
	 * トレースログを出力します。
	 * @param body
	 * @param LogCode logCode
	 * @param Throwable th
	 * @param Object className
	 * @param Object methodName
	 */
	public static void trace(LogCode logCode, Throwable th, Object className, Object methodName, String body) {
	    log(LoggerConst.LOG_LEVEL_INFO, logCode, th, className, methodName, body);
	}

	/**
	 * エラーログを出力します。
	 * @param LogCode logCode
     * @param Throwable th
     * @param Object className
     * @param Object methodName
	 */
	public static void error(LogCode logCode, Throwable th, Object className, Object methodName, String body) {
	    log(LoggerConst.LOG_LEVEL_ERROR, logCode, th, className, methodName, body);
	}

	/**
	 * 電文ログを出力します。
	 * @param logCode
	 * @param th
	 * @param className
	 * @param methodName
	 * @param body
	 */
	public static void traceTelegram(LogCode logCode, Object className, Object methodName, String body) {
	    logTelegram(LoggerConst.LOG_LEVEL_INFO, logCode, className, methodName, body);
	}

    /**
	 * スレッドローカルなMDCのキー情報をクリアします。
	 */
	private static void clearLocalMDC() {
	    MDC.remove(CLASS_NAME);
	    MDC.remove(METHOD_NAME);
	    MDC.remove(LOG_CODE);
	    MDC.remove(LOG_MESSAGE);
	    if (MDC.get(ERR_STACK_TRACE) != null) {
	        MDC.remove(ERR_STACK_TRACE);
	    }
	}

	/**
	 * [例外クラス用]スタックトレース書き出し文字列を作成します。
	 * @param Throwable excp
	 * @return String
	 */
	private static String getStackTraceString(Throwable excp) {
        StringBuilder sb = new StringBuilder();
        sb.append(STR_NEWLINE);
        sb.append(STACKTRACE_START);
        sb.append(STR_NEWLINE);
        StringWriter stringWriter = new StringWriter();
        excp.printStackTrace(new PrintWriter(stringWriter));
        sb.append(stringWriter.toString());
        sb.append(STR_NEWLINE);
        sb.append(STACKTRACE_END);

        return sb.toString();
    }

	/**
     * [普通の文字列用]スタックトレース書き出し文字列を作成します。<br>
     * 例：<br>
     * [Interface Body Start]<br>
     * ジャーナルログ本文<br>
     * [Interface Body End]<br>
     * @param Throwable excp
     * @return String
     */
    private static String getStackTraceString(String body) {
        StringBuilder sb = new StringBuilder();
        sb.append(STR_NEWLINE);
        sb.append(STACKTRACE_START);
        sb.append(STR_NEWLINE);
        sb.append(body);
        sb.append(STR_NEWLINE);
        sb.append(STACKTRACE_END);

        return sb.toString();
    }

}
