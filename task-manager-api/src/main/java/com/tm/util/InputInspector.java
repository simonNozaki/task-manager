package com.tm.util;

import java.util.function.Predicate;

import com.tm.consts.AppLog;
import com.tm.dto.common.Errors;

/**
 * 入力検査機能を提供するクラスです.
 * @param <T>
 */
public final class InputInspector<T> {

	Errors errors;
	T input;

	// デフォルトコンストラクタ禁止
	private InputInspector() {}

	private InputInspector(T input) {
		this.input = input;
	}

	/**
	 * インプットの入力検査パイプラインを開始します.入力チェック操作はInspectorクラスが提供します.
	 * @param T input
	 * @return InputInspector<T>
	 */
	public static <T> Inspector<T> of(T input) {
		return new Inspector<T>(input);
	}

	/**
	 * 入力検査機能を提供するクラスです.
	 * @param <T>
	 */
	public static final class Inspector<T> {

		T value;
		Errors errors;

		// デフォルトコンストラクタ禁止
		private Inspector(T value) {
			this.value = value;
			this.errors = null;
		}

		/**
		 * 入力がnullもしくは空の場合、エラーコードを設定します.
		 * @param value
		 * @return Inspector<T>
		 */
		public Inspector<T> hasNullValue() {
			Predicate<T> predicate = (T inputValue) -> ObjectUtil.isNullOrEmpty(value);
			return this.satisfyPredicateWithInput(value, predicate, AppLog.TMTKCM10001.getCode());
		}

		/**
		 * 桁数チェックを行い、上限を超えていればエラーコードを設定します.
		 * @param String target
		 * @param int max
		 * @param String code
		 * @return
		 */
		public <V> Inspector<T> violateMaxLength(V target, int max, String code) {
			// 文字列でない場合は評価を実施せず、そのままパイプラインを継続
			if (target.getClass() != String.class) {
				return this;
			}
			Predicate<V> predicate = (V inputValue) -> StringUtil.isOverSpecificLength(target.toString(), max);
			return this.satisfyPredicateWithInput(target, predicate, code);
		}

		/**
		 * 桁数チェックを行い、指定された文字長でなければエラーコードを設定します.
		 * @param String target
		 * @param int max
		 * @param String code
		 * @return
		 */
		public <V> Inspector<T> violateSpecificLength(V target, int max, String code) {
			// 文字列でない場合は評価を実施せず、そのままパイプラインを継続
			if (target.getClass() != String.class) {
				return this;
			}
			Predicate<V> predicate = (V inputValue) -> StringUtil.isEqualToSpecificLength(target.toString(), max);
			return this.satisfyPredicateWithInput(target, predicate, code);
		}

		/**
		 * カスタムの評価条件からエラーコードを設定します.
		 * @param Predicate<T> predicate
		 * @param String code
		 * @return Inspector<T>
		 */
		public Inspector<T> evaluateCustomCondition(Predicate<T> predicate, String code) {
			return this.satisfyPredicateWithInput(value, predicate, code);
		}

		/**
		 * 入力が指定された条件を満たすことを確認します.<br>
		 * 条件にマッチするエラーを入力することで、エラーコードを設定します.
		 * @param T input
		 * @param Predicate<T> predicate
		 * @param Errors errors
		 * @return Inspector<T>
		 */
		public <V> Inspector<T> satisfyPredicateWithInput(V input, Predicate<V> predicate, String code) {
			if (!predicate.test(input)) {
				this.errors.getCodes().add(code);
			}
			return this;
		}

		/**
		 * エラーを構築します.
		 * @return Errors
		 */
		public Errors build() {
			return this.errors;
		}

	}

}
