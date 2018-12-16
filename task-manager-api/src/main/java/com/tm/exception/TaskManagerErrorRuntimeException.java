package com.tm.exception;

import java.util.List;

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
    public TaskManagerErrorRuntimeException(List<?> errorsList) {
        this.errorsList = errorsList;
    }

    /**
     * エラーメッセージ。String。
     */
    private String errorMessage;

    /**
     * エラーコードリスト。リストオブジェクト。
     */
    private List<?> errorsList;

    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public List<?> getErrorsList() {
        return errorsList;
    }
    public void setErrorsList(List<?> errorsList) {
        this.errorsList = errorsList;
    }

}
