package com.tm.service.task.impl;

import java.util.List;

import com.tm.consts.AppConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tm.consts.CtrlConst;
import com.tm.dao.repository.TaskRepository;
import com.tm.dto.Task;
import com.tm.dto.TaskExample;
import com.tm.dto.common.ServiceOut;
import com.tm.service.framework.BaseService;
import com.tm.service.task.FetchTaskService;

/**
 * タスク取得サービスの実装クラスです.
 */
@Service
public class FetchTaskServiceImpl extends BaseService implements FetchTaskService {

	@Autowired
	TaskRepository taskRepository;

	/** 利用者IDに紐づくタスクのリストを取得します.
     * @param String userId
     * @return List タスクの取得結果
     * @throws Exception
     */
	public ServiceOut<List<Task>> fetchTask(String userId) throws Exception {
	    // ユーザIDに紐づくタスクの一覧を取得します。
		TaskExample taskExample = new TaskExample();
		taskExample.createCriteria().andUserIdEqualTo(userId).andCompletedFlagEqualTo(AppConst.TASK_COMPLETED_FLAG_NOT_COMPLETED);
		List<Task>  taskList = taskRepository.selectByExample(taskExample);

		// 結果の返却
		return doPipeServiceOut()
                .setNormalResult(taskList)
                .build();
	}
}
