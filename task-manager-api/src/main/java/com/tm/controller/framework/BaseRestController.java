package com.tm.controller.framework;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 基底Controllerクラスです.
 */
public class BaseRestController {

	/**
	 * レスポンス共通プロセッサクラスです.レスポンス生成に必要な機能を提供します.
	 */
	protected static final class ResponseProcessor<T> {

	  private final T value;

	  /**
	   * nullを許容するデフォルトコンストラクタ.
	  */
	  public ResponseProcessor() {
	    this.value = null;
	  }

	  /**
	   * 引数をもらった場合のデフォルトコンストラクタ.
	   * @param T value
	   */
	  private ResponseProcessor(T value) {
	    this.value = Objects.requireNonNull(value);
	  }

	  /**
	   * プロセッサを開始します.
	   * @param supplier
	   */
	  public <R> ResponseProcessor<R> of(Supplier<R> supplier) {
	    return new ResponseProcessor<R>(supplier.get());
	  }

	  /**
	   * プロセッサを開始します。具体的な値でコンストラクタを呼び出します。
	   * @param input
	   * @return
	   */
	  public <R> ResponseProcessor<R> with(R input) {
		  return new ResponseProcessor<R>(input);
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
	   * 事前の操作の適正性を評価して、正しければそのまま値を返します.
	   * @param Predicate<T> predicate
	   * @return ResponseProcessor<T>
	   */
	  public ResponseProcessor<T> filter(Predicate<T> predicate) {
	    if (predicate.test(value)) {
	      return new ResponseProcessor<T>(value);
	    }
	    return null;
	  }

	  /**
	   * 手動ログ出力機能を提供します。これは中間操作です。
	   * @param Consumer<V> consumer
	   * @return ResponseProcessor<T>
	   */
	  public <V> ResponseProcessor<T> logOutput(T t) {
	      // リストオブジェクトの場合、結果を複数件にする
	      if (t instanceof List) {

	      }


	      return new ResponseProcessor<T>();
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
