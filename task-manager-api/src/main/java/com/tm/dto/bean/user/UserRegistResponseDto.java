package com.tm.dto.bean.user;

import com.tm.dto.common.Errors;

/**
 * 利用者登録レスポンスDTO。
 */
public class UserRegistResponseDto {

	/**
	 * 利用者ID
	 */
	private String userId;

	/**
	 * 利用者名
	 */
	private String userName;

	/**
	 * エラー結果
	 */
	private Errors errors;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Errors getErrors() {
		return errors;
	}

	public void setErrors(Errors errors) {
		this.errors = errors;
	}
}
