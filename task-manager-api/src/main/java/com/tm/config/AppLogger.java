package com.tm.config;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.tm.consts.LogCode;
import com.tm.consts.LogConst;

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

	/**
	 * ロガーインスタンス
	 */
	private static final Logger INSTANCE = LoggerFactory.getLogger(AppLogger.class.getCanonicalName());

	/**
	 * ログレベルに応じたログ出力を実施します。
	 * @param String level
	 * @param String msg
	 * @param Throwable th
	 * @param Object className
     * @param Object methodName
	 */
	private static void log(String level, LogCode logCode, Throwable th, Object className, Object methodName) {
	    // MDCを初期化
	    MDC.put(CLASS_NAME, className.toString());
	    MDC.put(METHOD_NAME, methodName.toString());
	    MDC.put(LOG_CODE, logCode.getCode());
	    MDC.put(LOG_MESSAGE, logCode.getMessage());

	    // マーカーを初期化
	    Marker logPrefix;

	    // 例外を受け付けられる形に変換する
	    Optional<Throwable> exception = Optional.ofNullable(th);

	    // ログレベルをロガーに入力
	    ch.qos.logback.classic.Logger loggerInstance = (ch.qos.logback.classic.Logger)INSTANCE;

	    // レベル別にログを出力
	    switch(level) {
	        case LogConst.LOG_LEVEL_ERROR:
	            loggerInstance.setLevel(Level.ERROR);
	            logPrefix = MarkerFactory.getMarker(LogConst.LOG_PREFIX_ERROR);
                INSTANCE.error(logPrefix, logCode.getCode(), exception);
	            break;
	        case LogConst.LOG_LEVEL_WARN:
	            loggerInstance.setLevel(Level.WARN);
	            logPrefix = MarkerFactory.getMarker(LogConst.LOG_PREFIX_WARN);
                INSTANCE.error(logPrefix, logCode.getCode(), exception);
                break;
	        case LogConst.LOG_LEVEL_INFO:
                loggerInstance.setLevel(Level.INFO);
	            logPrefix = MarkerFactory.getMarker(LogConst.LOG_PREFIX_INFO);
                INSTANCE.info(logPrefix, logCode.getCode(), exception);
	            break;
	        case LogConst.LOG_LEVEL_TRACE:
	            loggerInstance.setLevel(Level.TRACE);
	            logPrefix = MarkerFactory.getMarker(LogConst.LOG_PREFIX_TRACE);
	            INSTANCE.trace(logPrefix, logCode.getCode(), exception);
                break;
	        default:
	            break;
	    }
	    // MDCのクリア
	    clearLocalMDC();
	}

	/**
	 * トレースログを出力します。
	 * @param LogCode logCode
	 * @param Throwable th
	 * @param Object className
	 * @param Object methodName
	 */
	public static void trace(LogCode logCode, Throwable th, Object className, Object methodName) {
	    log(LogConst.LOG_LEVEL_INFO, logCode, th, className, methodName);
	}

	/**
	 * エラーログを出力します。
	 * @param LogCode logCode
     * @param Throwable th
     * @param Object className
     * @param Object methodName
	 */
	public static void error(LogCode logCode, Throwable th, Object className, Object methodName) {
	    log(LogConst.LOG_LEVEL_ERROR, logCode, th, className, methodName);
	}

	/**
	 * スレッドローカルなMDCのキー情報をクリアします。
	 */
	private static void clearLocalMDC() {
	    MDC.remove(CLASS_NAME);
	    MDC.remove(METHOD_NAME);
	    MDC.remove(LOG_CODE);
	}

}
