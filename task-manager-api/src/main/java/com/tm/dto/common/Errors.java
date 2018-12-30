package com.tm.dto.common;

import java.util.List;

/**
 * 共通ErrorコードDTOです.
 */
public class Errors {

    /**
     * デフォルトコンストラクタ、引数なし
     */
    public Errors() {}

    /**
     * デフォルトコンストラクタ、エラーコードあり
     * @param codes
     */
    public Errors(List<String> codes) {
        this.codes = codes;
    }

	/**
	 * エラーコードリスト
	 */
	private List<String> codes;

	/**
	 * ID
	 */
	private String id;

	public List<String> getCodes() {
		return codes;
	}
	public void setCodes(List<String> codes) {
		this.codes = codes;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
