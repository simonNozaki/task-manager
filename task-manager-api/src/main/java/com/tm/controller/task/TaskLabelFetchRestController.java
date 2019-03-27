package com.tm.controller.task;

import java.util.List;

import com.tm.consts.AppConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tm.consts.CtrlConst;
import com.tm.consts.error.TaskManagerErrorCode;
import com.tm.controller.framework.BaseRestController;
import com.tm.dto.TaskLabel;
import com.tm.dto.bean.task.TaskLabelFetchResponseDto;
import com.tm.dto.common.Errors;
import com.tm.exception.TaskManagerErrorRuntimeException;
import com.tm.service.task.TaskLabelFetchService;
import com.tm.util.InputInspector;
import com.tm.util.ObjectUtil;

/**
 * タスクラベル情報取得Controllerクラス。
 */
@RestController
@RequestMapping(CtrlConst.URI_API_VERSION)
public class TaskLabelFetchRestController extends BaseRestController {

    @Autowired
    TaskLabelFetchService taskLabelFetchService;

    /** 利用者が保持するタスクラベルの一覧を取得します.
     * @param String userId
     * @return FetchTaskResponseDto
     * @throwsException
     */
    @RequestMapping(value = CtrlConst.FUNC_TASKS + CtrlConst.FUNC_TASKS_LABEL, method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TaskLabelFetchResponseDto fetch(@RequestParam("userId") String userId) throws Exception {
        //------------------------------------
        // 入力内容の検査
        //------------------------------------
        Errors errors = InputInspector.of(userId)
                        .logInput(arrangeLoggingString(userId))
                        .hasNullValue(TaskManagerErrorCode.ERR110001.getCode())
                        .violateSpecificLength(userId, AppConst.USER_ID_LENGTH, TaskManagerErrorCode.ERR110003.getCode())
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
        return responseProcessBuilder().executeService(taskLabelFetchService.fetch(userId))
                   .map((List<TaskLabel> labels, Errors error) -> {
                       TaskLabelFetchResponseDto res = new TaskLabelFetchResponseDto();
                       res.setLabels(labels);
                       res.setErrors(errors);
                       return res;
                   })
                   .log()
                   .apply();

    }

    /**
     * 電文ログ用メッセージを整形します。
     * @param String inputUserId
     * @return String
     */
    private String arrangeLoggingString(String inputUserId) {
        StringBuilder sb = new StringBuilder();
        final String leftBracket = "{";
        final String rightBracket = "}";
        final String colon = " : ";
        final String userId = "userId";
        sb.append(leftBracket);
        sb.append(userId);
        sb.append(colon);
        sb.append(inputUserId);
        sb.append(rightBracket);

        return sb.toString();

    }

}
