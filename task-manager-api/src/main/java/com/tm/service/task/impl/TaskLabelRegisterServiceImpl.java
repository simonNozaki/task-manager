package com.tm.service.task.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tm.consts.AppConst;
import com.tm.consts.error.TaskManagerErrorCode;
import com.tm.dao.repository.TaskLabelRepository;
import com.tm.dto.TaskLabel;
import com.tm.dto.bean.task.TaskLabelRegisterRequestDto;
import com.tm.dto.common.ServiceOut;
import com.tm.service.framework.BaseService;
import com.tm.service.task.TaskLabelRegisterService;
import com.tm.util.IdCounter;

/**
 * タスクラベル登録Serviceの実装クラス。
 */
@Service
public class TaskLabelRegisterServiceImpl extends BaseService implements TaskLabelRegisterService {

    @Autowired
    TaskLabelRepository taskLabelRepository;

    /**
     * タスクのラベルを新規登録します。
     * @param TaskLabelRegisterRequestDto 登録対象ラベル
     * @return ServiceOut<TaskLabel> タスクラベル登録Serviceの処理結果
     */
    @Override
    public ServiceOut<TaskLabel> register(TaskLabelRegisterRequestDto label) throws Exception{
        // 登録データの初期化
        TaskLabel source = new TaskLabel();

        // プロパティをコピーして、必要な値を設定します。
        BeanUtils.copyProperties(label, source);
        source.setLabelId(IdCounter.assignIdForTaskLabel(8));
        source.setUsedFlag(AppConst.TASK_LABEL_USED_FLAG_REGISTERED);

        // ラベル情報を登録します。
        int result = taskLabelRepository.insert(source);

        // レスポンスチェック
        if (result == 0) {
            return doPipeServiceOut()
                    .setNormalResult(new TaskLabel())
                    .setError(TaskManagerErrorCode.ERR999999.getCode())
                    .build();
        }

        // 正常結果を返却
        return doPipeServiceOut()
                .setNormalResult(source)
                .build();
    }

}
