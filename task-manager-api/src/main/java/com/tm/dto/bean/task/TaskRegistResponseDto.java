package com.tm.dto.bean.task;

import com.tm.dto.common.Errors;

/**
 * タスク登録レスポンスDTOです.
 */
public class TaskRegistResponseDto {

	/**
	 * タスクID
	 */
	private String taskId;

	/**
	 * タスクタイトル
	 */
	private String taskTitle;

	/**
	 * Error結果
	 */
	private Errors errors;

	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskTitle() {
		return taskTitle;
	}
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
	public Errors getErrors() {
		return errors;
	}
	public void setErrors(Errors errors) {
		this.errors = errors;
	}
}
