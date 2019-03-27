package com.tm.controller.task;

import com.tm.consts.AppConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tm.consts.CtrlConst;
import com.tm.consts.error.TaskManagerErrorCode;
import com.tm.controller.framework.BaseRestController;
import com.tm.dto.bean.task.TaskCompleteRequestDto;
import com.tm.dto.bean.task.TaskCompleteResponseDto;
import com.tm.dto.common.Errors;
import com.tm.exception.TaskManagerErrorRuntimeException;
import com.tm.service.task.TaskCompleteService;
import com.tm.util.InputInspector;
import com.tm.util.ObjectUtil;

/**
 * タスク完了Controller。
 */
@RestController
@RequestMapping(CtrlConst.URI_API_VERSION)
public class TaskCompleteRestController extends BaseRestController {

    @Autowired
    TaskCompleteService taskCompleteServcie;

    @RequestMapping(value = CtrlConst.FUNC_TASKS + CtrlConst.MAP_COMPLETE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PATCH)
    @ResponseBody
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    public TaskCompleteResponseDto complete(@RequestBody TaskCompleteRequestDto req) throws Exception {
        //------------------------------------
        // 入力内容の検査
        //------------------------------------
        Errors errors = InputInspector.of(req)
                .logInput(req)
                .isNull(req.getTaskId(), TaskManagerErrorCode.ERR210001.getCode())
                .violateSpecificLength(req.getTaskId(), AppConst.TASK_ID_LENGTH, TaskManagerErrorCode.ERR210003.getCode())
                .build();

        //------------------------------------
        // エラーがある場合レスポンス作成処理
        //------------------------------------
        if(!ObjectUtil.isNullOrEmpty(errors.getCodes())) {
        	throw new TaskManagerErrorRuntimeException(errors);
        }

        //------------------------------------
        // サービスクラスの実行およびレスポンス処理
        //------------------------------------
        return responseProcessBuilder().executeService(taskCompleteServcie.execute(req))
            .map((String taskId, Errors error) -> {
                TaskCompleteResponseDto res = new TaskCompleteResponseDto();
                res.setTaskId(taskId);
                res.setErrors(error);
                return res;
            })
            .log()
            .apply();
    }
}
