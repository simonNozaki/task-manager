package com.tm.dto.bean.error;

import com.tm.dto.common.Errors;

/**
 * システムエラー共通エラーオブジェクトクラス。
 */
public class GeneralError {

    /**
     * デフォルトコンストラクタ、引数なし
     */
    public GeneralError() {}

    /**
     * デフォルトコンストラクタ、引数あり
     * @param errors - エラーオブジェクト
     */
    public GeneralError(Errors errors) {
        this.errors = errors;
    }

    /**
     * エラーコードセット
     */
    private Errors errors;

    public Errors getErrors() {
        return errors;
    }
    public void setErrors(Errors errors) {
        this.errors = errors;
    }

}
