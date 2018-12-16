package com.tm.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tm.config.AppLogger;
import com.tm.consts.LogCode;
import com.tm.dto.common.Errors;

/**
 * 入力検査機能を提供するクラスです.
 * @param <T>
 */
public final class InputInspector<T> {

	T input;

	// デフォルトコンストラクタ禁止
	private InputInspector() {}

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
			this.errors = new Errors();
		}

		private Inspector(T value, Errors errors) {
		    this.value = value;
		    if (ObjectUtil.isNullOrEmpty(this.errors)) {
		        this.errors = new Errors();
		    }
		    this.errors = errors;
		}

		/**
		 * リクエストされた入力内容をログ出力します。<br>
		 * @param V input
		 * @return Inspector<T>
		 * @throws IOException
		 */
		public <V> Inspector<T> logInput(V input) throws IOException {
		    ObjectMapper mapper = new ObjectMapper();
	        AppLogger.traceTelegram(LogCode.TMFWCM80001, this.getClass(), new Object(){}.getClass().getEnclosingMethod().getName(), mapper.writeValueAsString(input));
		    return new Inspector<T>(value);
		}

		/**
		 * 入力がnullもしくは空の場合、エラーコードを設定します.
		 * @param value
		 * @return Inspector<T>
		 */
		public Inspector<T> hasNullValue(String code) {
			Predicate<T> predicate = (T value) -> ObjectUtil.isNullOrEmpty(value);
			return this.satisfyPredicateWithInput(value, predicate, code);
		}

		/**
		 * 桁数チェックを行い、上限を超えていればエラーコードを設定します.
		 * @param String target
		 * @param int max
		 * @param String code
		 * @return
		 */
		public <V> Inspector<T> violateMaxLength(V target, int max, String code) {
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
                // エラーコードのリストがない場合はリストを初期化する
                List<String> codes = Optional.ofNullable(this.errors.getCodes()).orElse(new ArrayList<>());
                codes.add(code);
                this.errors.setCodes(codes);
            }
            return new Inspector<T>(this.value, this.errors);
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
