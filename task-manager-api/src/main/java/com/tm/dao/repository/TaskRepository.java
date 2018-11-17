package com.tm.dao.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tm.dao.TaskMapper;
import com.tm.dto.Task;
import com.tm.dto.bean.task.TaskRegistRequestDto;

/**
 * TaskモデルのカスタムDTOです.
 */
@Mapper
public interface TaskRepository extends TaskMapper {

	// 新規タスクを登録します.
	Task registerTask(@Param("taskItem") TaskRegistRequestDto task);
}
