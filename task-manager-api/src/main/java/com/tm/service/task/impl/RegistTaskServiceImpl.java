package com.tm.service.task.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tm.dao.repository.TaskRepository;
import com.tm.dto.Task;
import com.tm.dto.bean.task.TaskRegistRequestDto;
import com.tm.service.task.RegistTaskService;

/**
 * タスク登録サービスの実装クラスです.
 */
@Service
public class RegistTaskServiceImpl implements RegistTaskService {

	@Autowired
	TaskRepository taskRepository;

	// タスクの新規登録.
	public Task registerTask(TaskRegistRequestDto task) throws Exception {
		// タスクをデータベースに登録します.
		Task registResult = taskRepository.registerTask(task);
		// レスポンスチェック
		if (registResult == null) {
			return null;
		}
		return registResult;
	}
}
