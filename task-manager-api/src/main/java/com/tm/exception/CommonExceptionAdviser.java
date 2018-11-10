package com.tm.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tm.consts.AppLog;
import com.tm.dto.common.Errors;

/**
 * AOPによる共通エラーハンドラです.
 * @author s.nozaki
 *
 */
@ControllerAdvice
public class CommonExceptionAdviser {

	/**
	 * ロガーインスタンス
	 */
	Logger logger = LoggerFactory.getLogger(CommonExceptionAdviser.class);

	/**
	 * 指定されたメソッド以外のリクエストを捕捉するメソッドです.
	 * @param HttpRequestMethodNotSupportedException e
	 * @return Errors
	 */
	@ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ResponseBody
	public Errors handleMethodNotAllowedError(HttpRequestMethodNotSupportedException e) {
		logger.error(AppLog.TMFWCM90000.getCode(), e.getMessage());
		Errors errors = new Errors();
		errors.getCodes().add("指定されたHTTPメソッドが設定されていません。");
		return errors;
	}

	/**
	 * アプリケーション全体で共通のシステムエラー捕捉メソッドです.
	 * @param Exception e
	 */
	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public void handleSystemError(Exception e) {
		logger.error(AppLog.TMTKRG90001.getCode(), e.getMessage());
	}

}
