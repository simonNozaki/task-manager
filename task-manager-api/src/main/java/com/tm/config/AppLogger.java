package com.tm.config;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.tm.consts.LogConst;

/**
 * 共通ログ出力設定クラスです.
 */
public class AppLogger {

	/**
	 * ロガーインスタンス
	 */
	private static final Logger INSTANCE = LoggerFactory.getLogger(AppLogger.class.getCanonicalName());

	/**
	 * ログレベルに応じたログ出力を実施します。
	 * @param level
	 * @param mesg
	 * @param th
	 */
	private static void log(String level, String msg, Throwable th) {
	    // マーカーを初期化
	    Marker logPrefix;

	    // 例外を受け付けられる形に変換する
	    Optional<Throwable> exception = Optional.ofNullable(th);

	    // レベル別にログを出力
	    switch(level) {
	        case LogConst.LOG_LEVEL_ERROR:
	            logPrefix = MarkerFactory.getMarker(LogConst.LOG_PREFIX_ERROR);
                INSTANCE.error(logPrefix, msg, exception);
	            break;
	        case LogConst.LOG_LEVEL_WARN:
                break;
	        case LogConst.LOG_LEVEL_INFO:
	            logPrefix = MarkerFactory.getMarker(LogConst.LOG_PREFIX_INFO);
                INSTANCE.info(logPrefix, msg, exception);
	            break;
	        case LogConst.LOG_LEVEL_TRACE:
	            logPrefix = MarkerFactory.getMarker(LogConst.LOG_PREFIX_TRACE);
	            INSTANCE.trace(logPrefix, msg, exception);
                break;
	        default:
	            break;
	    }
	}

	/**
	 * トレースログを出力します。
	 * @param String msg
	 * @param Throwable th
	 */
	public static void trace(String msg, Throwable th) {
	    log(LogConst.LOG_PREFIX_TRACE, msg, th);
	}

	/**
	 * エラーログを出力します。
	 * @param msg
	 * @param th
	 */
	public static void error(String msg, Throwable th) {
	    log(LogConst.LOG_LEVEL_ERROR, msg, th);
	}

}
