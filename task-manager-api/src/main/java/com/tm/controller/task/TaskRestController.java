package com.tm.controller.task;

import java.util.Optional;

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
import com.tm.dto.Task;
import com.tm.dto.bean.task.TaskRegistRequestDto;
import com.tm.dto.bean.task.TaskRegistResponseDto;
import com.tm.dto.common.Errors;
import com.tm.exception.TaskManagerErrorRuntimeException;
import com.tm.service.task.TaskRegisterService;
import com.tm.util.InputInspector;
import com.tm.util.ObjectUtil;

/**
 * タスク登録.
 *
 */
@RestController
@RequestMapping(CtrlConst.URI_API_VERSION)
public class TaskRestController extends BaseRestController {

    @Autowired
    private TaskRegisterService registTaskService;

    /** 新規タスクを登録します.
     * @param TaskRegistRequestDto task
     * @return RegistTaskResponseDto
     * @throws Exception
     */
    @RequestMapping(value = CtrlConst.FUNC_TASKS + CtrlConst.MAP_REGIST, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public TaskRegistResponseDto register(@RequestBody TaskRegistRequestDto task) throws Exception {
        //------------------------------------
        // 入力内容の検査
        //------------------------------------
        Errors errors = InputInspector.of(task)
                .logInput(task)
                .isNull(task.getTaskTitle(), TaskManagerErrorCode.ERR220001.getCode())
                .isNull(task.getUserId(), TaskManagerErrorCode.ERR110001.getCode())
                .violateMaxLength(Optional.ofNullable(task.getTaskTitle()), AppConst.TASK_TITLE_MAX, TaskManagerErrorCode.ERR220002.getCode())
                .violateMaxLength(Optional.ofNullable(task.getTaskLabel()), AppConst.TASK_LABEL_MAX, TaskManagerErrorCode.ERR230001.getCode())
                .violateMaxLength(Optional.ofNullable(task.getTaskNote()), AppConst.TASK_NOTE_MAX, TaskManagerErrorCode.ERR240001.getCode())
                .violateSpecificLength(task.getUserId(), AppConst.USER_ID_LENGTH, TaskManagerErrorCode.ERR110003.getCode())
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
        return responseProcessBuilder().executeService(registTaskService.registerTask(task))
                .map((Task result, Errors error) -> {
                    TaskRegistResponseDto res = new TaskRegistResponseDto();
                    res.setTaskId(result.getTaskId());
                    res.setTaskTitle(result.getTaskTitle());
                    res.setErrors(error);
                    return res;
                })
                .log()
                .apply();
    }

}
