package com.tm.service.task;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tm.dto.TaskLabel;
import com.tm.dto.common.ServiceOut;

/**
 * タスクラベル取得Serviceクラス。
 */
@Service
public interface TaskLabelFetchService {

    /**
     * 利用者が保持するタスクのラベルを取得します。
     * @param userId - 利用者ID
     * @return ServiceOut - タスクラベルの一覧
     * @throws Exception
     */
    public ServiceOut<List<TaskLabel>> fetch(String userId) throws Exception;

}
