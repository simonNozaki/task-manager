package com.tm.service.task.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tm.consts.AppConst;
import com.tm.consts.log.LogCode;
import com.tm.dao.repository.TaskLabelRepository;
import com.tm.dto.TaskLabel;
import com.tm.dto.TaskLabelExample;
import com.tm.dto.common.ServiceOut;
import com.tm.service.framework.BaseService;
import com.tm.service.task.TaskLabelFetchService;
import com.tm.util.ObjectUtil;

/**
 * タスクラベル取得Serviceの実装クラス。
 */
@Service
public class TaskLabelFetchServiceImpl extends BaseService implements TaskLabelFetchService {

    @Autowired
    TaskLabelRepository taskLabelRepository;

    /**
     * 利用者が保持するタスクのラベルを取得します。
     * @param userId - 利用者ID
     * @return ServiceOut - タスクラベルの一覧、およびエラー情報
     */
    @Override
    public ServiceOut<List<TaskLabel>> fetch(String userId) {
        // 返却するラベルリストを初期化
        List<TaskLabel> taskLabels = new ArrayList<>();

        // 取得対象ラベルオブジェクトを指定します。
        TaskLabelExample tle = new TaskLabelExample();
        tle.createCriteria().andUserIdEqualTo(userId).andUsedFlagEqualTo(AppConst.TASK_LABEL_USED_FLAG_REGISTERED);

        // 対象タスクラベルの一覧を取得します。
        taskLabels = taskLabelRepository.selectByExample(tle);

        // レスポンスチェック
        if (ObjectUtil.isNullOrEmpty(taskLabels)) {
            return doPipeServiceOut()
                     .setNormalResult(taskLabels)
                     .setError(LogCode.TMTKCM90001.getCode())
                     .build();
        }

        // 正常結果の返却
        return doPipeServiceOut()
                .setNormalResult(taskLabels)
                .build();
    }

}
