package com.tm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tm.config.AppLogger;
import com.tm.consts.LogCode;

/**
 * AOPによる共通エラーハンドラです.
 * @author s.nozaki
 *
 */
@ControllerAdvice
public class GlobalErrorHandler {

    /**
     * APIのシステムエラーを処理します。
     * @param TaskManagerErrorRuntimeException e
     */
	@ExceptionHandler(TaskManagerErrorRuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
	public void handleTaskManagerErrorRuntimeException(TaskManagerErrorRuntimeException e) {
	    AppLogger.trace(e.getErrorMessage(), e);
	    System.out.println(e.getErrorMessage());
	}


	/**
	 * 指定されたメソッド以外のリクエストを捕捉するメソッドです.
	 * @param HttpRequestMethodNotSupportedException e
	 * @return Errors
	 */
	@ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ResponseBody
	public void handleMethodNotAllowedError(HttpRequestMethodNotSupportedException e) {
		AppLogger.trace(LogCode.TMFWCM90000.getCode(), e);
	}

	/**
	 * 共通のシステムエラー捕捉メソッドです.
	 * @param Exception e
	 */
	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public void handleSystemError(Exception e) {
		AppLogger.trace(LogCode.TMTKRG90001.getCode(), e);
	}

}
