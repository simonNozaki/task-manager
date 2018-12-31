package com.tm.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tm.config.AppLogger;
import com.tm.consts.error.TaskManagerErrorCode;
import com.tm.consts.log.LogCode;
import com.tm.dto.common.Errors;

/**
 * 共通エラーハンドラクラス。
 */
@RestControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler{

    /**
     * APIのシステムエラーを処理します。
     * @param TaskManagerErrorRuntimeException e
     */
	@ExceptionHandler(TaskManagerErrorRuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
	public void handleTaskManagerErrorRuntimeException(TaskManagerErrorRuntimeException e) {
	    AppLogger.error(LogCode.TMFWCM90000, e, getCalledSource().get("class"), getCalledSource().get("method"));
	}

	/**
	 * 指定されたメソッド以外のリクエストを捕捉するメソッドです.
	 * @param HttpRequestMethodNotSupportedException e
	 * @return Errors
	 */
	@Override
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ResponseBody
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
		AppLogger.error(LogCode.TMFWCM90000, e, getCalledSource().get("class"), getCalledSource().get("method"));
        return super.handleHttpRequestMethodNotSupported(e, headers, status, request);
	}

	/**
	 * 補足しきれなかった、全ての実行時例外を補足して処理します。
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler
    public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
	    AppLogger.error(LogCode.TMFWCM90000, ex, getCalledSource().get("class"), getCalledSource().get("method"));
        return super.handleExceptionInternal(ex, setHandledErrors(TaskManagerErrorCode.ERR999999.getCode()), null, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

	/**
	 * 実行元Sourceを返却します。
	 * @return Map クラスとメソッドが1対1で対応するマップ
	 */
	private Map<String, String> getCalledSource( ) {

	    StackTraceElement[] ste = Thread.currentThread().getStackTrace();
	    String threadName = Thread.class.getCanonicalName();
	    String thisName = getClass().getCanonicalName();

	    for(StackTraceElement element : ste) {
	        String target = element.getClassName();
	        if(!target.equals(threadName) && !element.getClassName().equals(thisName)) {
	            Map<String, String> calledSource = new HashMap<>();
	            calledSource.put("class", element.getClassName());
	            calledSource.put("method", element.getMethodName());
	            return calledSource;
	        }
	    }
	    return new HashMap<>();
	}

	/**
	 * エラーコードを設定して返却します。
	 * @param errorCode 補足したエラーのエラーコード
	 * @return Errors エラーコードセット
	 */
	private Errors setHandledErrors(String errorCode) {
	    Errors errors = new Errors();
	    List<String> codes = new ArrayList<>();
	    codes.add(errorCode);
	    errors.setCodes(codes);
	    return errors;
	}


}
