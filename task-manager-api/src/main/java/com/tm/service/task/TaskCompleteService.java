package com.tm.service.task;

import org.springframework.stereotype.Service;

import com.tm.dto.bean.task.TaskCompleteRequestDto;
import com.tm.dto.common.ServiceOut;

/**
 * タスク完了サービスクラス。
 */
@Service
public interface TaskCompleteService {

    /**
     * タスクを完了します。
     * @param taskId
     * @return ServiceOut<String>
     */
    public ServiceOut<String> execute(TaskCompleteRequestDto req) throws Exception;
}
