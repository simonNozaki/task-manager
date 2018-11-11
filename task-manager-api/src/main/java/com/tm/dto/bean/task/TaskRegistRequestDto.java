package com.tm.dto.bean.task;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * タスク登録リクエストDTOです.
 */
public class TaskRegistRequestDto {
	/**
	 * タスクタイトル
	 */
	private String taskTitle;

	/**
	 * タスクラベル
	 */
	private String taskLabel;

	/**
	 * 開始日
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date startDate;

	/**
	 * 期限日
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date deadline;

	/**
	 * 利用者ID
	 */
	private String userId;

	/**
	 * タスクメモ
	 */
	private String taskNote;

	public String getTaskTitle() {
		return taskTitle;
	}
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
	public String getTaskLabel() {
		return taskLabel;
	}
	public void setTaskLabel(String taskLabel) {
		this.taskLabel = taskLabel;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTaskNote() {
		return taskNote;
	}
	public void setTaskNote(String taskNote) {
		this.taskNote = taskNote;
	}
}
