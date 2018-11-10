package com.tm.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 共通ログ出力設定クラスです.
 */
public class GlobalLoggerConfig {

	/**
	 * ロガーインスタンス
	 */
	public final Logger logger = LoggerFactory.getLogger(GlobalLoggerConfig.class);

}
