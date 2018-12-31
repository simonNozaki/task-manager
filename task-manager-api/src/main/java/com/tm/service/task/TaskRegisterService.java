package com.tm.service.task;

import org.springframework.stereotype.Service;

import com.tm.dto.Task;
import com.tm.dto.bean.task.TaskRegistRequestDto;
import com.tm.dto.common.ServiceOut;

/**
 * タスク登録サービスのインターフェースクラスです.
 */
@Service
public interface TaskRegisterService {

	/**
	 * 新規タスクを登録します。
	 * @param TaskRegistRequestDto task
	 * @return ServiceOut<Task>
	 * @throws Exception
	 */
	public ServiceOut<Task> registerTask(TaskRegistRequestDto task) throws Exception;

}
