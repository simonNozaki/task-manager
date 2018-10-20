package com.tm.consts;

/**
 * エラーメッセージ定義クラスです.
 */
public enum ErrorConst {

	//------------------------------------
	// 入力不正
	//------------------------------------
	/**
	 * 存在しない入力を参照しています.
	 */
	TMTKRGIN00001("TMTKRGIN00001"),

	//------------------------------------
	// 入力内容の検査
	//------------------------------------
	/**
	 * SQL実行中にエラーが発生しました.
	 */
	TMTKRGTH00001("TMTKRGTH00001");

	private final String code;
    private ErrorConst(final String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

}
