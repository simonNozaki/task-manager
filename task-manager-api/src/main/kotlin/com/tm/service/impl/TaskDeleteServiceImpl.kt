package com.tm.service.impl

import com.tm.consts.AppConst
import com.tm.consts.error.TaskManagerErrorCode
import com.tm.dao.repository.TaskRepository
import com.tm.dto.Task
import com.tm.dto.TaskExample
import com.tm.dto.bean.task.TaskDeleteRequestDto
import com.tm.dto.common.ServiceOut
import com.tm.service.TaskDeleteService
import com.tm.service.framework.BaseService
import com.tm.util.ObjectUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception
import javax.inject.Inject

/**
 * タスク削除サービス実装クラス。
 */
@Service
open class TaskDeleteServiceImpl : BaseService(), TaskDeleteService {

    /**
     * RepositoryのDI、遅延初期化でnullにしない
     */
    @Autowired
    var taskRepository: TaskRepository? = null;

    /**
     * 実行メソッド
     * @param req タスク削除リクエストデータ
     * @return ServiceOut サービス処理結果
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun delete(req: TaskDeleteRequestDto?): ServiceOut<String> {

        // DBから該当レコードを取得
        var target: Task = taskRepository!!.selectByPrimaryKey(req?.taskId)

        // 削除済みが指定されていたら、エラーを返す
        if(target.completedFlag.equals(AppConst.TASK_COMPLETED_FLAG_DELETED) && req != null){
            return BaseService.doPipeServiceOut<String>()
                    .setNormalResult(req.taskId)
                    .setError(TaskManagerErrorCode.ERR999999.code) // TODO 適切なエラーコードを設定する
                    .build()
        }

        // 該当タスクを削除
        taskRepository?.deleteByPrimaryKey(req?.taskId)

        // 履歴テーブルに更新したタスクを登録
        var insertedTask = taskRepository?.insertUpdatedTask(req?.taskId)

        // レスポンスチェック
        if (ObjectUtil.isNullOrEmpty(insertedTask)  && req != null) {
            return BaseService.doPipeServiceOut<Any>()
                    .setNormalResult(req.taskId)
                    .setError(TaskManagerErrorCode.ERR999999.code)
                    .build()
        }

        // 正常結果の返却
        return BaseService.doPipeServiceOut<Any>()
                .setNormalResult(insertedTask!!.taskId)
                .build()
    }
}