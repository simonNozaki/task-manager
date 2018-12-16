package com.tm.service.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.tm.dto.common.Errors;
import com.tm.dto.common.ServiceOut;

/**
 * 基底Serviceクラス。
 */
public class BaseService {

	/**
	 * Serviceクラスの出力生成パイプラインを開始します。
	 * @param value
	 * @return
	 */
	protected static <T> ServiceOutBuilder<T> doPipeServiceOut() {
		return new ServiceOutBuilder<T>();
	}

	/**
	 * Serviceクラスの、アウトプットを出力する操作を提供するクラスです。
	 * @param <T>
	 */
	protected static class ServiceOutBuilder<T> {
		T value;

		Errors errors;

		// デフォルトコンストラクタ禁止
		private ServiceOutBuilder() {
			this.errors = new Errors();
		}

		private ServiceOutBuilder(T value) {
			this.value = Objects.requireNonNull(value);
			this.errors = new Errors();
		}

		/**
		 * エラーコードを設定します。これは中間操作です。
		 * @param String code
		 * @return ServiceOutBuilder<T>
		 */
		public ServiceOutBuilder<T> setError(String code) {
			List<String> codes = new ArrayList<>();
			codes.add(code);
			errors.setCodes(codes);
			return new ServiceOutBuilder<T>();
		}

		/**
		 * 正常結果を設定します。
		 * @param input
		 * @return
		 */
		public <V> ServiceOutBuilder<V> setNormalResult(V input) {
			return new ServiceOutBuilder<V>(input);
		}

		/**
		 * 設定した値をもとにServiceクラスの出力を構築します。
		 * @return ServiceOut<T>
		 */
		public ServiceOut<T> build() {
			ServiceOut<T> serviceOut = new ServiceOut<T>();
			serviceOut.setValue(value);
			serviceOut.setErrors(errors);
			return serviceOut;
		}
	}
}
