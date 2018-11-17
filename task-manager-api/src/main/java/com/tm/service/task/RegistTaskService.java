package com.tm.service.task;

import org.springframework.stereotype.Service;

import com.tm.dto.Task;
import com.tm.dto.bean.task.TaskRegistRequestDto;

/**
 * タスク登録サービスのインターフェースクラスです.
 */
@Service
public interface RegistTaskService {

	// 新規タスクを登録します.
	public Task registerTask(TaskRegistRequestDto task) throws Exception;

}
