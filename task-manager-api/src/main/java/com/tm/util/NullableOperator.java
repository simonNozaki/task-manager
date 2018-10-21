package com.tm.util;

import java.util.Optional;
import java.util.function.Function;

/**
 * Nullを許容するOptionalクラスの拡張機能を提供します.
 */
public final class NullableOperator {

	/**
	 * Nullを許容するオブジェクト操作パイプラインを開始します.
	 * @param T value
	 * @return Operator<T>
	 */
	public static <T> Operator<T> of(T value) {
		return new Operator<T>(value);
	}

	/**
	 * オブジェクト操作クラスです.
	 * @param <T>
	 */
	public static class Operator<T> {
		T value;

		// デフォルトコンストラクタ禁止
		private Operator() {}

		private Operator (T value) {
			this.value = Optional.ofNullable(value).orElse(null);
		}

		/**
		 * ローカル変数に値が存在する場合は処理を実施せず、存在しない場合は入力された値を変数に設定します.
		 * @param input
		 * @return
		 */
		public Operator<T> makeApplclicable(T input) {
			if (value == null) {
				return new Operator<T>(input);
			}
			return new Operator<T>(value);
		}

		/**
		 * 値が存在しない場合を想定した操作を提供します.
		 * @param Function<T, V> input
		 * @return Operator<V>
		 */
		public <U, V> Operator<V> mapExpectedNotPresent(Function<T, V> input) {
			return new Operator<V>(input.apply(value));
		}

		/**
		 * インスタンスを返却します.
		 * @return T
		 */
		public T build() {
			return this.value;
		}
	}
}
