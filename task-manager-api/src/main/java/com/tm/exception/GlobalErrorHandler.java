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

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tm.config.AppLogger;
import com.tm.consts.error.TaskManagerErrorCode;
import com.tm.consts.log.LogCode;
import com.tm.dto.bean.error.GeneralError;
import com.tm.dto.common.Errors;

/**
 * 共通エラーハンドラクラス。
 */
@RestControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler{

    /**
     * APIのシステムエラーを処理します。
     * @param TaskManagerErrorRuntimeException e
     * @throws Exception
     * @throws SecurityException
     */
	@ExceptionHandler(TaskManagerErrorRuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleTaskManagerErrorRuntimeException(TaskManagerErrorRuntimeException e, WebRequest request) throws SecurityException, Exception {
	    AppLogger.error(LogCode.TMFWCM90000, e, getCalledSource().get("class"), getCalledSource().get("method"));
	    AppLogger.traceTelegram(LogCode.TMFWCM90000, this.getClass(), new Object(){}.getClass().getEnclosingMethod().getName(), new ObjectMapper().writeValueAsString(e.getErrors()));
	    return super.handleExceptionInternal(e, new GeneralError(e.getErrors()), null, HttpStatus.BAD_REQUEST, request);
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
		return super.handleExceptionInternal(e, setHandledErrors(TaskManagerErrorCode.ERR990001.getCode()), null, HttpStatus.METHOD_NOT_ALLOWED, request);
	}

	/**
	 * リクエストのJSONマッピング例外を処理します。
	 * @param ex - JsonMappingException
	 * @param request
	 * @return エラーオブジェクト
	 */
	@ExceptionHandler(JsonMappingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleJsonMappingException(JsonMappingException ex, WebRequest request){
	    AppLogger.error(LogCode.TMFWCM90000, ex, getCalledSource().get("class"), getCalledSource().get("method"));
        return super.handleExceptionInternal(ex, setHandledErrors(TaskManagerErrorCode.ERR990002.getCode()), null, HttpStatus.BAD_REQUEST, request);
	}

	/**
	 * 補足しきれなかった、全ての実行時例外を補足して処理します。
	 * @param ex
	 * @param request
	 * @return エラーオブジェクト
	 */
	@ExceptionHandler(Exception.class)
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
	private GeneralError setHandledErrors(String errorCode) {
	    List<String> codes = new ArrayList<>();
	    codes.add(errorCode);
	    return new GeneralError(new Errors(codes));
	}


}
