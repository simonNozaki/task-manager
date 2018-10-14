package com.tm.service.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tm.dao.service.TaskRepository;
import com.tm.dto.Task;
import com.tm.dto.bean.regist.RegistTaskRequestDto;
import com.tm.service.logic.RegistTaskService;

/**
 * タスク登録サービスの実装クラスです.
 */
@Service
public class RegistTaskServiceImpl implements RegistTaskService {

	@Autowired
	TaskRepository taskRepository;

	// タスクの新規登録.
	public Task registerTask(RegistTaskRequestDto task) throws Exception {
		// タスクをデータベースに登録します.
		Task registResult = taskRepository.registerTask(task);
		// レスポンスチェック
		if (registResult == null) {
			return null;
		}
		return registResult;
	}
}
