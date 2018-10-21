package com.tm.controller.framework;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

/**
 * 基底Controllerクラスです.
 */
public class BaseRestController {

	/**
	 * レスポンス共通プロセッサクラスです.レスポンス生成に必要な機能を提供します.
	 */
	protected static final class ResponseProcessor<T> {

	  /**
	  * ロガーインスタンスの初期化.
	  */
	  private final Logger LOGGER = (Logger) LoggerFactory.getLogger("systemLogger");

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
	   * ログレベルとメッセージを指定して、ログ出力を実施します.スタックトレースに出すときは第三引数も指定します.<br>
	   * 指定されたログレベルがいづれも適切でない場合は処理を実施しません.
	   * @param message, level, th
	   * @return ResponseProcessor<T>
	   */
	  public ResponseProcessor<T> logging(String message, String level, Throwable th) {
	    switch(level) {
	      case "error":
	        LOGGER.error(message);
	        th.printStackTrace();
	        break;
	      case "warn":
	        LOGGER.warn(message);
	        th.printStackTrace();
	        break;
	      case "info":
	        LOGGER.info(message);
	        break;
	      case "debug":
	        LOGGER.debug(message);
	        break;
	      default:
	        break;
	    }
	    return this;
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
	   * 操作を実行したパイプラインからレスポンスインスタンスを適用します.
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
