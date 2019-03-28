package com.tm.controller.framework;

import java.io.IOException;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tm.config.AppLogger;
import com.tm.consts.log.LogCode;
import com.tm.dto.common.Errors;
import com.tm.dto.common.ServiceOut;

/**
 * 基底Controllerクラスです.
 */
public class BaseRestController {

    /**
     * レスポンス共通プロセッサクラスです.レスポンス生成に必要な機能を提供します.
     */
    protected static final class ResponseProcessor<T> {

        private T value;

        private Errors errors;

        /**
         * nullを許容するデフォルトコンストラクタ.
         */
        public ResponseProcessor() {
            this.value = null;
        }

        /**
         * 引数をもらった場合のデフォルトコンストラクタ.
         * @param T 任意の型の値
         */
        private ResponseProcessor(T value) {
            this.value = Objects.requireNonNull(value);
        }

        /**
         * 引数をもらった場合のデフォルトコンストラクタ。エラー情報も同時に引き受けます。
         * @param T 任意の型の値
         */
        private ResponseProcessor(T value, Errors e) {
            this.value = Objects.requireNonNull(value);
            this.errors = e;
        }

        /**
         * プロセッサを開始します.
         * @param supplier レスポンスオブジェクトのサプライヤ
         * @return ResponseProcessor ローカルな自分のクラス
         */
        public <R> ResponseProcessor<R> of(Supplier<R> supplier) {
            return new ResponseProcessor<R>(supplier.get());
        }

        /**
         * プロセッサを開始します。具体的な値でコンストラクタを呼び出します。
         * @param input 任意の入力値
         * @return ResponseProcessor ローカルな自分のクラス
         */
        public <R> ResponseProcessor<R> with(R input) {
            return new ResponseProcessor<R>(input);
        }

        /**
         * サービスクラスを実行します。正常、異常結果をそれぞれローカル変数に格納してパイプラインを継続します。<br>
         * これは中間操作です。
         * @param out サービスクラスの実行結果
         * @return ResponseProcessor 結果を入力にしたプロセッサ
         */
        public <R> ResponseProcessor<R> executeService(ServiceOut<R> out) {
            return new ResponseProcessor<R>(out.getValue(), out.getErrors());
        }

        /**
         * 生成したDTOに対する中間操作を提供します.
         * @param function
         * @return
         */
        public <R> ResponseProcessor<R> operate(Function<T, R> function) {
            return new ResponseProcessor<R>(function.apply(value));
        }

        /**
         *
         * @param bifunction
         * @return
         */
        public <U, R> ResponseProcessor<R> map(BiFunction<T, Errors, R> bifunction) {
            return new ResponseProcessor<R>(bifunction.apply(value, errors));
        }

        /**
         * 事前の操作の適正性を評価して、正しければそのまま値を返します.
         * @param Predicate<T> predicate
         * @return ResponseProcessor<T>
         */
        public ResponseProcessor<T> filter(Predicate<T> predicate) {
            if (predicate.test(value)) {
                return new ResponseProcessor<T>(value);
            }
            return new ResponseProcessor<T>();
        }

        /**
         * 手動ログ出力機能を提供します。これは中間操作です。
         * @param Consumer<V> consumer
         * @return ResponseProcessor<T>
         * @throws IOException
         */
        public <V> ResponseProcessor<T> logOutput(V input) throws IOException {
            AppLogger.traceTelegram(LogCode.TMFWCM80002, this.getClass(), new Object(){}.getClass().getEnclosingMethod().getName(), new ObjectMapper().writeValueAsString(input));
            return new ResponseProcessor<T>(value);
        }

        /**
         * ログ出力機能を提供します。サービスクラス実行後に利用することで、引数なしで電文ログ出力ができます。<br>
         * サービス実行前にこのメソッドを実行しても、何も出力されません。
         * @return ResponseProcessor ローカルなResponseProcessorのインスタンスを返却します。
         * @throws IOException
         */
        public <V> ResponseProcessor<T> log() throws IOException {
            AppLogger.traceTelegram(LogCode.TMFWCM80002, this.getClass(), new Object(){}.getClass().getEnclosingMethod().getName(), new ObjectMapper().writeValueAsString(value));
            return new ResponseProcessor<T>(value);
        }

        /**
         * 操作を実行したパイプラインからレスポンスインスタンスを取得します。これは終端操作です。
         * @return T t
         */
        public T apply() {
            return this.value;
        }
    }

    /**
     * レスポンスプロセッサを返します.
     * @param T value
     * @return ResponseProcessor<T>
     */
    protected static <T> ResponseProcessor<T> responseProcessBuilder() {
        return new ResponseProcessor<T>();
    }


}
