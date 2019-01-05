package com.tm.exception;

import com.tm.dto.common.Errors;

/**
 * APIの業務エラー例外クラス。
 */
public class TaskManagerErrorRuntimeException extends RuntimeException {

    /**
     * デフォルトコンストラクタ。引数なし。
     */
    public TaskManagerErrorRuntimeException() {
        super();
    }

    /**
     * デフォルトコンストラクタ。エラーメッセージを引数にとります。
     * @param message
     */
    public TaskManagerErrorRuntimeException(String message) {
        super(message);
    }

    /**
     * デフォルトコンストラクタ。エラーメッセージと検査例外を引数にとります。
     * @param message
     * @param th
     */
    public TaskManagerErrorRuntimeException(String message, Throwable th) {
        super(message, th);
    }

    /**
     * デフォルトコンストラクタ。エラーコードリストを引数に取ります。
     * @param List<?> errorsList
     */
    public TaskManagerErrorRuntimeException(Errors errors) {
    	super();
        this.errors = errors;
    }

    /**
     * エラーメッセージ。String。
     */
    private String errorMessage;

    /**
     * エラーコードリスト。リストオブジェクト。
     */
    private Errors errors;

    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public Errors getErrors() {
        return errors;
    }
    public void setErrorsList(Errors errors) {
        this.errors = errors;
    }

}
