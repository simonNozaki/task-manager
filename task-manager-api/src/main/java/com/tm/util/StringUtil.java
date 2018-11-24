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
		if (target.length() < max) {
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
		if (target.length() == max) {
			return true;
		}
		return false;
	}

}
