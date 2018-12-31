package com.tm.service.task.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tm.consts.AppConst;
import com.tm.consts.error.TaskManagerErrorCode;
import com.tm.dao.repository.TaskRepository;
import com.tm.dto.Task;
import com.tm.dto.bean.task.TaskRegistRequestDto;
import com.tm.dto.common.ServiceOut;
import com.tm.service.framework.BaseService;
import com.tm.service.task.TaskRegisterService;
import com.tm.util.IdCounter;
import com.tm.util.ObjectUtil;

/**
 * タスク登録サービスの実装クラスです.
 */
@Service
public class TaskRegisterServiceImpl extends BaseService implements TaskRegisterService {

	@Autowired
	TaskRepository taskRepository;

	// タスクの新規登録.
	public ServiceOut<Task> registerTask(TaskRegistRequestDto task) throws Exception {
	    // DTOの初期化
		Task source = new Task();

		// プロパティをコピーして、必要な値を設定します。
		BeanUtils.copyProperties(task, source);
		source.setTaskId(IdCounter.assignIdForTask(new Date(), "yyMMdd", 4));
		source.setCompletedFlag(AppConst.TASK_COMPLETED_FLAG_NOT_COMPLETED);

		// DAOクラスの実行
		Task registedTask = taskRepository.register(source);

		// レスポンスチェック
		if (ObjectUtil.isNullOrEmpty(registedTask)) {
		    return doPipeServiceOut()
                    .setNormalResult(new Task())
                    .setError(TaskManagerErrorCode.ERR999999.getCode())
                    .build();
		}

		// 正常結果を返却
		return doPipeServiceOut()
                .setNormalResult(registedTask)
                .build();
	}
}
