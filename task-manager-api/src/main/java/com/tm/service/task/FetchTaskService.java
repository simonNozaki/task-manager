package com.tm.service.task;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tm.dto.Task;

/**
 * タスク取得サービスのインターフェースクラスです.
 */
@Service
public interface FetchTaskService {

	/** 利用者IDに紐づくタスクのリストを取得します.
	 * @param String userId
	 * @return List<Task>
	 * @throws Exception
	 */
	public List<Task> fetchTask(String userId) throws Exception;
}
