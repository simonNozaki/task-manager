package com.tm.dao.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tm.dao.TaskMapper;
import com.tm.dto.Task;

/**
 * TaskモデルのカスタムDTOです.
 */
@Mapper
public interface TaskRepository extends TaskMapper {

	/**
	 * 新規タスクを登録します。登録結果をエンティティにバインドして返却します。
	 * @param Task task
	 * @return Task
	 */
	public Task register(@Param("taskItem") Task task);
}
