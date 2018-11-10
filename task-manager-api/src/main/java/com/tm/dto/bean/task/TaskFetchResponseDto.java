package com.tm.dto.bean.task;

import java.util.List;

import com.tm.dto.Task;
import com.tm.dto.common.Errors;

/**
 * タスク取得レスポンスDTOです.
 */
public class TaskFetchResponseDto {

	/** タスク情報のリスト */
	private List<Task> tasks;
	/** Error結果 */
	private Errors errors;

	public Errors getErrors() {
		return errors;
	}
	public void setErrors(Errors errors) {
		this.errors = errors;
	}
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
}
