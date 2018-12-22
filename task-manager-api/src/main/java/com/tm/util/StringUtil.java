package com.tm.util;

/**
 * 文字列の共通ユーティリティです.
 */
public class StringUtil {

	/**
	 * 文字列の桁数チェックを実施します.
	 * @param String target
	 * @param int max
	 * @return boolean
	 */
	public static boolean isOverSpecificLength(String target, int max) {
		if (target.length() > max) {
			return true;
		}
		return false;
	}

	/**
	 * 文字列の長さが指定された長さであることを検査します.
	 * @param String target
	 * @param int max
	 * @return boolean
	 */
	public static boolean isEqualToSpecificLength(String target, int max) {
		if (target.length() != max) {
			return true;
		}
		return false;
	}

	/**
	 * 文字列が空もしくはnullであることを確認します。
	 * @param target 対象文字列
	 * @return boolean 判定結果
	 */
	public static boolean isNullOrBlank(String target) {
	    return target == null || target == "";
	}

	/**
	 * nullの場合空文字に変換します。
	 * @param s 対象文字列
	 * @return 変換後文字列
	 */
	public static String convertNullToEmpty(String s) {
	    return s == null ? "" : s;
	}

}
