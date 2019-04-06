package com.tm.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tm.config.AppLogger;
import com.tm.consts.log.LogCode;
import com.tm.dto.bean.task.TaskDeleteRequestDto;
import com.tm.dto.common.Errors;
import org.jetbrains.annotations.NotNull;

/**
 * 入力検査機能を提供するクラスです.
 * @param <T>
 */
public final class InputInspector<T> {

	T input;

	// デフォルトコンストラクタ禁止
	private InputInspector() {}

	/**
	 * インプットの入力検査パイプラインを開始します.入力チェック操作はInspectorクラスが提供します。
	 * Kotlinソースからも利用するので、nullを許容しない
	 * @param T input
	 * @return InputInspector<T>
	 */
	@NotNull
	public static <T> Inspector<T> of(T input) throws IllegalArgumentException {
		try{
			return new Inspector<T>(input);
		}catch (IllegalArgumentException e){
			AppLogger.trace(LogCode.TMFWCM80001, e, new Object(){}.getClass().getEnclosingClass().getName(), new Object(){}.getClass().getEnclosingMethod().getName(), null);
		}
		return null;
	}

	/**
	 * 入力検査機能を提供するクラスです.
	 * @param <T>
	 */
	public static final class Inspector<T> {

		T value;

		Errors errors;

		/**
		 * デフォルトコンストラクタ
		 * @param value コンストラクタ引数
		 */
		private Inspector(T value) {
			this.value = value;
			this.errors = new Errors();
		}

		/**
		 * デフォルトコンストラクタ
		 * @param value コンストラクタ引数
		 * @param errors エラー引数
		 */
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
	        AppLogger.traceTelegram(LogCode.TMFWCM80001, this.getClass(), new Object(){}.getClass().getEnclosingMethod().getName(), new ObjectMapper().writeValueAsString(input));
		    return new Inspector<T>(value);
		}

		/**
		 * 入力がnullもしくは空の場合、エラーコードを設定します.
		 * @param value
		 * @return Inspector<T>
		 * @throws Exception
		 */
		public Inspector<T> hasNullValue(String code){
			return this.satisfyPredicateWithInput(this.value, (T inputValue) -> ObjectUtil.isNullOrEmpty(this.value), code);
		}

		/**
		 * プロパティの空チェック。空文字もしくはnullの場合エラーコードを設定します。
		 * @param subject 被検査対象
		 * @param code エラーコード
		 * @return Inspector
		 */
		public <V> Inspector<T> isNull(V subject, String code){
			return this.satisfyPredicateWithInput(subject, (V inputValue) -> ObjectUtil.isNullOrEmpty(subject), code);
		}

		/**
		 * 桁数チェックを行い、上限を超えていればエラーコードを設定します。
		 * @param String target
		 * @param int max
		 * @param String code
		 * @return
		 */
		public <V> Inspector<T> violateMaxLength(V target, int max, String code) {
		    if (ObjectUtil.isNullOrEmpty(target)) {
                return new Inspector<T>(this.value, this.errors);
            }
		    return this.satisfyPredicateWithInput(target, (V inputValue) -> StringUtil.isOverSpecificLength(target.toString(), max), code);
		}

		/**
		 * 桁数チェックを行い、指定された文字長でなければエラーコードを設定します。
		 * @param String target
		 * @param int max
		 * @param String code
		 * @return
		 */
		public <V> Inspector<T> violateSpecificLength(V target, int length, String code) {
		    if (ObjectUtil.isNullOrEmpty(target)) {
		        return new Inspector<T>(this.value, this.errors);
		    }
			return this.satisfyPredicateWithInput(target, (V inputValue) -> !StringUtil.isPaticularLength(target, length), code);
		}

		/**
		 * カスタムの評価条件からエラーコードを設定します.
		 * @param Predicate<T> predicate
		 * @param String code
		 * @return Inspector<T>
		 */
		public Inspector<T> evaluateCustomCondition(Predicate<T> predicate, String code) {
			return this.satisfyPredicateWithInput(this.value, predicate, code);
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
            if (predicate.test(input)) {
                // エラーコードのリストがない場合はリストを初期化する
                List<String> codes = Optional.ofNullable(this.errors.getCodes()).orElse(new ArrayList<>());
                codes.add(code);
                this.errors.setCodes(codes);
            }
            return new Inspector<T>(this.value, this.errors);
        }

		/**
		 * エラーを昇順に構築します。
		 * @return Errors エラー情報
		 * @throws Exception
		 */
		public Errors build(){
		    if (!ObjectUtil.isNullOrEmpty(this.errors)) {
		        List<String> sortedCodes = ObjectUtil.getStream(this.errors.getCodes())
		                .sorted(Comparator.comparing((String code) -> code))
		                .collect(Collectors.toList());
		        this.errors = new Errors(sortedCodes);
		    }
		    return this.errors;
		}
	}

}
