package com.tm.service.task;

import org.springframework.stereotype.Service;

import com.tm.dto.TaskLabel;
import com.tm.dto.bean.task.TaskLabelRegisterRequestDto;
import com.tm.dto.common.ServiceOut;

/**
 * タスクラベル登録Serviceクラス。
 */
@Service
public interface TaskLabelRegisterService {

    /**
     * タスクのラベルを新規登録します。
     * @param TaskLabelRegisterRequestDto 登録対象ラベル
     * @return ServiceOut<TaskLabel> タスクラベル登録Serviceの処理結果
     * @throws Exception
     */
    public ServiceOut<TaskLabel> register(TaskLabelRegisterRequestDto label) throws Exception;
}
