package com.tm.consts;

/**
 * ログメッセージ定義クラスです.
 */
public enum AppLog {

	/*******************************************************
	 * 開始および終了
	 *******************************************************/
	/** タスクの登録処理を開始します. */
	TM_TK_RG_INFO_001("TM_TK_RG_INFO_001"),
	/** タスクの登録処理を終了します. */
	TM_TK_RG_INFO_002("TM_TK_RG_INFO_002"),
	/*******************************************************
	 * エラーログメッセージ
	 *******************************************************/
	/** タスクの登録処理中にErrorが発生しました. */
	TM_TK_RG_ERR_001("TM_TK_RG_ERR_001");

	private final String code;
    private AppLog(final String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

}
