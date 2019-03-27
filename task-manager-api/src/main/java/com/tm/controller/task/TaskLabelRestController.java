package com.tm.controller.task;

import java.util.stream.Stream;

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
import com.tm.dto.TaskLabel;
import com.tm.dto.bean.task.TaskLabelRegisterRequestDto;
import com.tm.dto.bean.task.TaskLabelRegisterResponseDto;
import com.tm.dto.common.Errors;
import com.tm.exception.TaskManagerErrorRuntimeException;
import com.tm.service.task.TaskLabelRegisterService;
import com.tm.util.InputInspector;
import com.tm.util.ObjectUtil;

/**
 * タスクラベル登録RESTControllerクラス。
 */
@RestController
@RequestMapping(CtrlConst.URI_API_VERSION)
public class TaskLabelRestController extends BaseRestController {

    @Autowired
    TaskLabelRegisterService taskLabelRegisterService;

    /**
     * 新規タスクラベルを登録します。
     * @param TaskLabelRegisterRequestDto - タスクラベル登録情報
     * @return TaskLabelRegisterResponseDto - タスクラベル登録処理結果
     * @throws Exception
     */
    @RequestMapping(value = CtrlConst.FUNC_TASKS + CtrlConst.FUNC_TASKS_LABEL, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public TaskLabelRegisterResponseDto register(@RequestBody TaskLabelRegisterRequestDto label) throws Exception {
        //------------------------------------
        // 入力内容の検査
        //------------------------------------
        Errors errors = InputInspector.of(label)
                            .logInput(label)
                            .isNull(label.getUserId(), TaskManagerErrorCode.ERR110001.getCode())
                            .isNull(label.getTaskLabel(), TaskManagerErrorCode.ERR230003.getCode())
                            .isNull(label.getUsedFlag(), TaskManagerErrorCode.ERR280001.getCode())
                            .violateMaxLength(label.getTaskLabel(), AppConst.TASK_LABEL_MAX, TaskManagerErrorCode.ERR230001.getCode())
                            .violateSpecificLength(label.getUserId(), AppConst.USER_ID_LENGTH, TaskManagerErrorCode.ERR110003.getCode())
                            .violateSpecificLength(label.getUsedFlag(), AppConst.USER_FLAG_LENGTH, TaskManagerErrorCode.ERR280003.getCode())
                            .evaluateCustomCondition((TaskLabelRegisterRequestDto subject) -> {
                                return !this.matchLabelUsedFlag(label.getUsedFlag());
                            }, TaskManagerErrorCode.ERR280004.getCode())
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
        return responseProcessBuilder().executeService(taskLabelRegisterService.register(label))
                   .map((TaskLabel taskLabel, Errors error) -> {
                       TaskLabelRegisterResponseDto res = new TaskLabelRegisterResponseDto();
                       res.setLabelId(taskLabel.getLabelId());
                       res.setTaskLabel(taskLabel.getTaskLabel());
                       res.setErrors(error);
                       return res;
                   })
                   .log()
                   .apply();
    }

    /**
     * ラベル利用フラグが定義されたものであることを判定します。
     * @param subject
     * @return 定義された文字列であればtrueを返します。
     */
    private boolean matchLabelUsedFlag(String subject) {
        return Stream.of(AppConst.TASK_LABEL_USED_FLAG_REGISTERED, AppConst.TASK_LABEL_USED_FLAG_DELETED)
                   .anyMatch((String flag) -> {
                       return subject.equals(flag);
                   });
    }

}
