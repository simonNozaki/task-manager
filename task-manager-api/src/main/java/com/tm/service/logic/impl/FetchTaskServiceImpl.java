package com.tm.service.logic.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tm.dao.service.TaskRepository;
import com.tm.dto.Task;
import com.tm.dto.TaskExample;
import com.tm.service.logic.FetchTaskService;

/**
 * タスク取得サービスの実装クラスです.
 */
@Service
public class FetchTaskServiceImpl implements FetchTaskService {

	@Autowired
	TaskRepository taskRepository;

	public List<Task> fetchTask(String userId) throws Exception {
		List<Task> taskList = new ArrayList<>();
		TaskExample taskExample = new TaskExample();
		taskExample.createCriteria().andUserIdEqualTo(userId);
		taskList = taskRepository.selectByExample(taskExample);
		// レスポンスチェック
		if(taskList == null) {
			return null;
		}
		return taskList;
	}
}
