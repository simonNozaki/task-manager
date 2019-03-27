package com.tm.controller.task

import com.tm.consts.AppConst
import com.tm.consts.CtrlConst
import com.tm.consts.error.TaskManagerErrorCode
import com.tm.controller.framework.BaseRestController
import com.tm.dto.bean.task.TaskCompleteRequestDto
import com.tm.dto.bean.task.TaskDeleteRequestDto
import com.tm.dto.bean.task.TaskDeleteResponseDto
import com.tm.exception.TaskManagerErrorRuntimeException
import com.tm.util.InputInspector
import com.tm.util.InputInspector.*
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * タスク削除REST Controller
 */
@RestController
@RequestMapping(CtrlConst.URI_API_VERSION)
class TaskDeleteRestController : BaseRestController() {

    /**
     * 実行メソッド
     */
    @RequestMapping(value = [CtrlConst.FUNC_TASKS + CtrlConst.MAP_DELETE], method = [RequestMethod.DELETE])
    @ResponseBody
    @CrossOrigin
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun delete(req: TaskDeleteRequestDto): TaskDeleteResponseDto {
        //------------------------------------
        // 入力内容の検査
        //------------------------------------
        var errors = InputInspector.of<TaskDeleteRequestDto>(req)
                .logInput<TaskDeleteRequestDto>(req)
                .isNull<String>(req.getTaskId(), TaskManagerErrorCode.ERR210001.code)
                .violateSpecificLength<String>(req.getTaskId(), AppConst.TASK_ID_LENGTH, TaskManagerErrorCode.ERR210003.code)
                .build()

        //------------------------------------
        // エラーがある場合レスポンス作成処理
        //------------------------------------
        if(errors.codes.isNotEmpty()){
            throw TaskManagerErrorRuntimeException(errors)
        }

        //------------------------------------
        // サービスクラスの実行およびレスポンス処理
        //------------------------------------


        return TaskDeleteResponseDto()
    }
}