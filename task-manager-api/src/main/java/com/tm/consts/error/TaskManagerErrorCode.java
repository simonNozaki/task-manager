package com.tm.consts.error;

/**
 * エラーコード定数クラス。
 */
public enum TaskManagerErrorCode {

	//------------------------------------
	// 1. 利用者データ系
	//------------------------------------

    ERR110001("ERR110001"),
    ERR110002("ERR110002"),
    ERR110003("ERR110003"),

    ERR120001("ERR120001"),
    ERR120002("ERR120002"),
    ERR120003("ERR120003"),

    ERR130001("ERR130001"),
    ERR130002("ERR130002"),
    ERR130003("ERR130003"),
    ERR130004("ERR130004"),

    ERR140001("ERR140001"),
    ERR140002("ERR140002"),

    ERR150001("ERR150001"),
    ERR150002("ERR150002"),
    ERR150003("ERR150003"),

    //------------------------------------
    // 2. タスクデータ系
    //------------------------------------

    ERR210001("ERR210001"),
    ERR210002("ERR210002"),
    ERR210003("ERR210001"),

    ERR220001("ERR220001"),
    ERR220002("ERR220002"),
    ERR220003("ERR220003"),

    ERR230001("ERR230001"),
    ERR230002("ERR230002"),

    ERR240001("ERR240001"),
    ERR240002("ERR240002"),

    ERR250001("ERR250001"),
    ERR250002("ERR250002"),
    ERR250003("ERR250003"),

    ERR260001("ERR260001"),
    ERR260002("ERR260002"),

    ERR270001("ERR270001"),
    ERR270002("ERR270002"),
    ERR270003("ERR270003");


	private final String code;

	/**
	 * デフォルトコンストラクタ。不可視。
	 * @param code エラーコード
	 */
    private TaskManagerErrorCode(final String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

}
