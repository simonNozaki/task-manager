package com.tm.dto.common;

import java.util.List;

/**
 * 共通ErrorコードDTOです.
 */
public class Errors {
	/**
	 * タスクタイトル
	 */
	private String taskTitle;
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
	public String getTaskTitle() {
		return taskTitle;
	}
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
