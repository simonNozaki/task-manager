package com.tm.controller.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tm.consts.AppConst;
import com.tm.consts.CtrlConst;
import com.tm.consts.LogCode;
import com.tm.controller.framework.BaseRestController;
import com.tm.dto.Task;
import com.tm.dto.bean.task.TaskFetchResponseDto;
import com.tm.dto.common.Errors;
import com.tm.service.task.FetchTaskService;
import com.tm.util.InputInspector;
import com.tm.util.ObjectUtil;

/**
 * タスク登録Controllerです.
 */
@RestController
@RequestMapping(CtrlConst.URI_API_VERSION)
public class TaskFetchRestController extends BaseRestController {

	@Autowired
	private FetchTaskService fetchTaskService;

	/** 利用者に紐づくタスクの一覧を取得します.
	 * @param String userId
	 * @return FetchTaskResponseDto
	 * @throwsException
	 */
	@RequestMapping(value = CtrlConst.FUNC_TASKS + CtrlConst.MAP_FETCH, method = RequestMethod.GET)
	@ResponseBody
	@CrossOrigin
	@ResponseStatus(HttpStatus.ACCEPTED)
	public TaskFetchResponseDto fetch(@RequestParam("userId") String userId) throws Exception {
		//------------------------------------
		// 入力内容の検査
		//------------------------------------
		Errors errors = InputInspector.of(userId)
                        .logInput(arrangeLoggingString(userId))
                        .violateSpecificLength(userId, AppConst.USER_ID_LENGTH, LogCode.TMURCM10011.getCode())
                        .build();

		//------------------------------------
		// エラーがある場合レスポンス作成処理
		//------------------------------------
		if(!ObjectUtil.isNullOrEmpty(errors.getCodes())) {
			return responseProcessBuilder().of(TaskFetchResponseDto::new)
                        .operate((TaskFetchResponseDto res) -> {
                        errors.setId(userId);
                        res.setErrors(errors);
                        return res;
                        })
                        .apply();
		}

		//------------------------------------
		// サービスクラスの実行
		//------------------------------------
		List<Task> taskList = fetchTaskService.fetchTask(userId);

		//------------------------------------
		// レスポンス処理
		//------------------------------------
		return responseProcessBuilder().of(TaskFetchResponseDto::new)
        		        .logOutput(taskList)
                        .operate((TaskFetchResponseDto dto) -> {
                        dto.setTasks(taskList);
                        return dto;
                        })
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
