package com.tm.consts;

/**
 * アプリ共通の定義クラスです.
 */
public class AppConst {

	//------------------------------
	// 完了フラグ
	//------------------------------//
	/**
	 * 未完了
	 */
	public static final String TASK_COMPLETED_FLAG_NOT_COMPLETED = "0";

	/**
	 * 完了済み
	 */
	public static final String TASK_COMPLETED_FLAG_COMPLETED = "1";

	/**
	 * 削除済み
	 */
	public static final String TASK_COMPLETED_FLAG_DELETED = "2";

	//------------------------------
	// 入力桁数
	//------------------------------
	/**
	 * タスクタイトル, 50桁
	 */
	public static final int TASK_TITLE_MAX = 50;

	/**
	 * タスクラベル, 20桁
	 */
	public static final int TASK_LABEL_MAX = 20;

	/**
	 * 完了フラグ, 1桁
	 */
	public static final int COMPLETED_FLAG_MAX = 1;

	/**
	 * タスクメモ, 200文字
	 */
	public static final int TASK_NOTE_MAX = 200;

	/**
	 * 利用者ID, 8桁
	 */
	public static final int USER_ID_LENGTH = 8;

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
