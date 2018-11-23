package com.tm.service.task.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tm.consts.AppConst;
import com.tm.consts.LogCode;
import com.tm.dao.repository.TaskRepository;
import com.tm.dto.Task;
import com.tm.dto.bean.task.TaskRegistRequestDto;
import com.tm.dto.common.ServiceOut;
import com.tm.service.framework.BaseService;
import com.tm.service.task.RegistTaskService;
import com.tm.util.IdCounter;

/**
 * タスク登録サービスの実装クラスです.
 */
@Service
public class RegistTaskServiceImpl extends BaseService implements RegistTaskService {

	@Autowired
	TaskRepository taskRepository;

	// タスクの新規登録.
	public ServiceOut<Task> registerTask(TaskRegistRequestDto task) throws Exception {
	    // DTOの初期化
		Task source = new Task();

		// プロパティをコピーして、必要な値を設定します。
		BeanUtils.copyProperties(task, source);
		source.setTaskId(IdCounter.assignIdForTask(new Date(), "yymmdd", 4));
		source.setCompletedFlag(AppConst.TASK_COMPLETED_FLAG_NOT_COMPLETED);

		// DAOクラスの実行
		Task registedTask = taskRepository.register(source);

		// レスポンスチェック
		if (registedTask == null) {
		    return doPipeServiceOut()
                    .setNormalResult(new Task())
                    .setError(LogCode.TMTKRG90001.getCode())
                    .build();
		}

		// 正常結果を返却
		return doPipeServiceOut()
                .setNormalResult(registedTask)
                .build();
	}
}
