package com.tm.service.logic;

import org.springframework.stereotype.Service;

import com.tm.dto.Task;
import com.tm.dto.bean.regist.RegistTaskRequestDto;

/**
 * タスク登録サービスのインターフェースクラスです.
 */
@Service
public interface RegistTaskService {

	// 新規タスクを登録します.
	public Task registerTask(RegistTaskRequestDto task) throws Exception;

}
