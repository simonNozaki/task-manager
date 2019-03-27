package com.tm.service.task.impl;

import com.tm.consts.AppConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tm.consts.CtrlConst;
import com.tm.consts.error.TaskManagerErrorCode;
import com.tm.consts.log.LogCode;
import com.tm.dao.repository.TaskRepository;
import com.tm.dto.Task;
import com.tm.dto.bean.task.TaskCompleteRequestDto;
import com.tm.dto.common.ServiceOut;
import com.tm.service.framework.BaseService;
import com.tm.service.task.TaskCompleteService;
import com.tm.util.ObjectUtil;

@Service
public class TaskCompleteServiceImpl extends BaseService implements TaskCompleteService{

    @Autowired
    TaskRepository taskRepository;

    @Override
    public ServiceOut<String> execute(TaskCompleteRequestDto req) throws Exception {
        // タスクのステータスを更新します。
        Task target = new Task();
        target.setTaskId(req.getTaskId());
        target.setCompletedFlag(AppConst.TASK_COMPLETED_FLAG_COMPLETED);
        int updatedResult = taskRepository.updateByPrimaryKeySelective(target);

        // レスポンスチェック
        if (ObjectUtil.isNullOrEmpty(updatedResult) || updatedResult == 0) {
            return doPipeServiceOut()
                     .setNormalResult(req.getTaskId())
                     .setError(TaskManagerErrorCode.ERR999999.getCode())
                     .build();
        }

        // 履歴テーブルに更新したタスクを登録します。
        Task insertedTask = taskRepository.insertUpdatedTask(req.getTaskId());

        // レスポンスチェック
        if (ObjectUtil.isNullOrEmpty(insertedTask)) {
            return doPipeServiceOut()
                     .setNormalResult(req.getTaskId())
                     .setError(TaskManagerErrorCode.ERR999999.getCode())
                     .build();
        }

        // 履歴テーブルに登録したタスクを削除します。
        int deletedResult = taskRepository.deleteByPrimaryKey(insertedTask.getTaskId());

        // レスポンスチェック
        if (ObjectUtil.isNullOrEmpty(deletedResult) || deletedResult == 0) {
            return doPipeServiceOut()
                     .setNormalResult(req.getTaskId())
                     .setError(LogCode.TMTKCM90001.getCode())
                     .build();
        }

        // 正常結果の返却
        return doPipeServiceOut()
                 .setNormalResult(insertedTask.getTaskId())
                 .build();
    }

}
