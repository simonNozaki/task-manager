package com.tm.exception.adviser;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tm.TaskManagerApiApplication;
import com.tm.consts.AppLog;

import ch.qos.logback.classic.Logger;

/**
 * AOPによる共通エラーハンドラです.
 * @author s.nozaki
 *
 */
@ControllerAdvice
public class CommonContorollerAdviser {

	/** ロガーインスタンス */
	Logger logger = (Logger) LoggerFactory.getLogger(TaskManagerApiApplication.class);

	/**
	 * アプリケーション全体で共通のシステムエラー捕捉メソッドです.
	 * @exception Exception e
	 */
	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public void handleSystemError(Exception e) {
		logger.error(AppLog.TM_TK_RG_ERR_001.getCode(), e.getMessage());
	}

}
