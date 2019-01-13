package com.tm.util;

import java.util.List;
import java.util.Objects;
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
		if(Objects.equals(input, "")) {
			return true;
		}

		return false;
	}

	/**
	 * リスト型変数について、以下に該当する場合、trueを返却します。<br>
	 * <li>引数がnull
	 * <li>引数の要素数が0
	 * <li>リストの要素が全てnull
	 * @param list
	 * @return
	 */
	public static boolean isNullOrEmpty(List<?> list) {
	    // リストがnull
	    if (list == null) {
	        return true;
	    }

	    // 空チェック
	    if (list.isEmpty()) {
	        return true;
	    }

	    // 各要素のnullチェック
	    boolean ofRowIsNull = ObjectUtil.getStream(list)
	        .anyMatch((Object row) -> row.equals(null) || row.equals(""));

	    if (ofRowIsNull) {
	        return true;
	    }

	    return false;
	}

	/**
	 * 任意の型のリストからStreamを返します.リストが空の場合、空のStreamを返却します。
	 * @param List<T> list
	 * @return Stream<T>
	 */
	public static <T> Stream<T> getStream(List<T> list) {
		if(list == null) {
			return Stream.empty();
		}
		return list.stream();
	}
}
