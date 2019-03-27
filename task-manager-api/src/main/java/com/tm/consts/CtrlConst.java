package com.tm.consts;

/**
 * アプリ共通の定数ファイルです.
 * @author s.nozaki
 */
public class CtrlConst {

	/*******************************************************
	 * URI定義
	 *******************************************************/
	/**
	 * アプリケーションコンテキスト
	 */
	public static final String URI_API_VERSION = "/api/v1";

	/**
	 * 処理大項目：タスク
	 */
	public static final String FUNC_TASKS = "/task";

	/**
	 * 処理大項目：利用者
	 */
	public static final String FUNC_USERS = "/user";

	/**
	 * 処理中項目：ラベル
	 */
	public static final String FUNC_TASKS_LABEL = "/label";

	/**
	 * 取得
	 */
	public static final String MAP_FETCH = "/fetch";

	/**
	 * 登録
	 */
	public static final String MAP_REGIST = "/regist";

	/**
	 * 更新
	 */
	public static final String MAP_UPDATE = "/update";

	/**
	 * 削除
	 */
	public static final String MAP_DELETE = "/delete";

	/**
	 * 完了
	 */
	public static final String MAP_COMPLETE = "/complete";

	/**
	 * トークン発行
	 */
    public static final String MAP_AUTH_TOKEN = "/token";

    /**
     * 認証
     */
    public static final String MAP_AUTHENTICATION = "/authentication";

    /**
     * サインアップ
     */
    public static final String MAP_SIGNUP = "/signup";

    /**
     * サインイン
     */
    public static final String MAP_SIGNIN = "/signin";

	/**
	 * ヘルスチェック
	 */
	public static final String MAP_HEALTHCHECK = "/healthcheck";

}
