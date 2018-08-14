package com.tm.dto.bean.regist;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * タスク登録リクエストDTOです.
 */
public class RegistTaskRequestDto {
	/** タスクタイトル */
	private String taskTitle;
	/** タスクラベル */
	private String taskLabel;
	/** 開始日 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startDate;
	/** 期限日 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date deadline;
	/** 利用者ID */
	private String userId;

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
}
