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

	/** タスクの取得 */
	public static final String MAP_FETCH = "/fetch";
	/** タスクの登録 */
	public static final String MAP_REGIST = "/regist";
	/** タスクの更新 */
	public static final String MAP_UPDATE = "/update";
	/** タスクの削除 */
	public static final String MAP_DELETE = "/delete";
}
