package com.tm.consts;

/**
 * アプリ共通定数クラス。
 */
public class AppConst {

	//------------------------------
	// 文字
	//------------------------------//
	/**
	 * セミコロン
	 */
	public static final String STR_SEMI_COLON = ";";

	/**
	 * 半角スペース
	 */
	public static final String STR_SPACE = " ";

	//------------------------------
	// タスク完了フラグ
	//------------------------------//
	/**
	 * 未完了, 0
	 */
	public static final String TASK_COMPLETED_FLAG_NOT_COMPLETED = "0";

	/**
	 * 完了済み, 1
	 */
	public static final String TASK_COMPLETED_FLAG_COMPLETED = "1";

	/**
	 * 削除済み, 2
	 */
	public static final String TASK_COMPLETED_FLAG_DELETED = "2";

	//------------------------------
    // タスクラベル利用フラグ
    //------------------------------//
    /**
     * タスクラベル：登録済み, 0
     */
    public static final String TASK_LABEL_USED_FLAG_REGISTERED = "0";

    /**
     * タスクラベル：削除済み, 1
     */
    public static final String TASK_LABEL_USED_FLAG_DELETED = "1";

    //------------------------------
    // 利用者利用フラグ
    //------------------------------//
    /**
     * 利用者利用フラグ：登録済み、0
     */
    public static final String USER_USED_FLAG_REGISTERED = "0";

    /**
     * 利用者利用フラグ：削除済み、1
     */
    public static final String USER_USED_FLAG_DELETED = "1";


	//------------------------------
	// 入力桁数
	//------------------------------
	/**
	 * タスクID, 10桁
	 */
	public static final int TASK_ID_LENGTH = 10;

	/**
	 * タスクタイトル, 50桁
	 */
	public static final int TASK_TITLE_MAX = 50;

	/**
	 * タスクラベル, 50桁
	 */
	public static final int TASK_LABEL_MAX = 50;

	/**
	 * 完了フラグ, 1桁
	 */
	public static final int COMPLETED_FLAG_MAX = 1;

	/**
	 * タスクメモ, 200文字
	 */
	public static final int TASK_NOTE_MAX = 200;

	/**
	 * 利用者ID, 10桁
	 */
	public static final int USER_ID_LENGTH = 10;

	/**
	 * 利用者名, 50桁
	 */
	public static final int USER_NAME_MAX = 50;

	/**
	 * 利用者メールアドレス, 100桁
	 */
	public static final int USER_EMAIL_MAX = 100;

	/**
	 * 利用者パスワード, 100桁
	 */
	public static final int USER_PASSWORD_MAX = 100;

	/**
	 * 利用者フラグ, 1桁
	 */
	public static final int USER_FLAG_LENGTH = 1;

}
