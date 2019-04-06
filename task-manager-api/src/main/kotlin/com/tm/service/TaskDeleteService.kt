package com.tm.service

import com.tm.dto.bean.task.TaskDeleteRequestDto
import com.tm.dto.common.ServiceOut
import org.springframework.stereotype.Service
import java.lang.Exception

/**
 * タスク削除サービスクラス。
 */
@Service
interface TaskDeleteService {

    /**
     * 実行メソッド
     * @param req タスク削除リクエストデータ
     * @return ServiceOut サービス処理結果
     * @throws Exception
     */
    @Throws(Exception::class)
    fun delete(req: TaskDeleteRequestDto?): ServiceOut<String>
}