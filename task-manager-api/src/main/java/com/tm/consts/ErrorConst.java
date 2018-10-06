package com.tm.consts;

/**
 * エラーメッセージ定義クラスです.
 */
public enum ErrorConst {

	/****************************************
	 * 入力不正系
	 ****************************************/
	/** 存在しない入力を参照しています. */
	TM_TK_RG_IN_001("TM_TK_RG_IN_001"),
	/****************************************
	 * 処理不正系
	 ****************************************/
	/** SQL実行中にエラーが発生しました. */
	TM_TK_RG_TH_001("TM_TK_RG_TH_001");

	private final String code;
    private ErrorConst(final String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

}
