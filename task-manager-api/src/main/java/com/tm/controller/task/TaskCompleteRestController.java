package com.tm.controller.task;

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

import com.tm.consts.AppConst;
import com.tm.consts.CtrlConst;
import com.tm.consts.LogCode;
import com.tm.controller.framework.BaseRestController;
import com.tm.dto.bean.task.TaskCompleteRequestDto;
import com.tm.dto.bean.task.TaskCompleteResponseDto;
import com.tm.dto.common.Errors;
import com.tm.dto.common.ServiceOut;
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
                .hasNullValue(LogCode.TMTKCM10001.getCode())
                .violateSpecificLength(req.getTaskId(), AppConst.TASK_ID_LENGTH, LogCode.TMTKCM10015.getCode())
                .build();

        //------------------------------------
        // エラーがある場合レスポンス作成処理
        //------------------------------------
        if(!ObjectUtil.isNullOrEmpty(errors.getCodes())) {
            return responseProcessBuilder().of(TaskCompleteResponseDto::new)
                    .operate((TaskCompleteResponseDto res) -> {
                        res.setErrors(errors);
                        return res;
                    })
                    .apply();
        }

        //------------------------------------
        // サービスクラスの実行
        //------------------------------------
        ServiceOut<String> result = taskCompleteServcie.execute(req);

        //------------------------------------
        // レスポンス処理
        //------------------------------------
        return responseProcessBuilder().of(TaskCompleteResponseDto::new)
                .operate((TaskCompleteResponseDto dto) -> {
                    dto.setTaskId(result.getValue());
                    dto.setErrors(result.getErrors());
                    return dto;
                })
                .apply();
    }
}
