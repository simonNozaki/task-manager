package com.tm.util;

import java.util.List;
import java.util.stream.Stream;

/**
 *オブジェクト操作のユーティリティクラス.
 */
public final class ObjectUtil {

	/**
	 * オブジェクトの空、もしくはnullを検査します.
	 * @param input
	 * @return
	 */
	public static <T> boolean isNullOrEmpty(T input) {
		// 入力がnullのケース
		if (input == null) {
			return true;
		}

		// 入力が空のケース
		if(input.equals("")) {
			return true;
		}

		return false;
	}

	/**
	 * 任意の型のリストからStreamを返します.
	 * @param List<T> list
	 * @return Stream<T>
	 */
	public static <T> Stream<T> getStream(List<T> list) {
		if(list == null) {
			return null;
		}
		return list.stream();
	}
}
