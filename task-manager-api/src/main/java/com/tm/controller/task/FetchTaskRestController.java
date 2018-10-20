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
import com.tm.consts.AppLog;
import com.tm.consts.CtrlConst;
import com.tm.controller.framework.BaseRestController;
import com.tm.dto.Task;
import com.tm.dto.bean.fetch.FetchTaskResponseDto;
import com.tm.dto.common.Errors;
import com.tm.service.logic.FetchTaskService;
import com.tm.util.InputInspector;

/**
 * タスクのCRUD操作を行うコントローラクラスです.
 */
@RestController
@RequestMapping(CtrlConst.URI_API_VERSION)
public class FetchTaskRestController extends BaseRestController {

	@Autowired
	private FetchTaskService fetchTaskService;

	/** 利用者に紐づくタスクの一覧を取得します.
	 * @param String userId
	 * @return FetchTaskResponseDto
	 * @throwsException
	 */
	@RequestMapping(value = CtrlConst.FUNC_TASKS + CtrlConst.MAP_FETCH_TASK,method = RequestMethod.GET)
	@ResponseBody
	@CrossOrigin
	@ResponseStatus(HttpStatus.ACCEPTED)
	public FetchTaskResponseDto fetchTask(@RequestParam("userId") String userId) throws Exception {
		//------------------------------------
		// 入力内容の検査
		//------------------------------------
		Errors errors = InputInspector.of(userId)
				.violateSpecificLength(userId, AppConst.USER_ID_LENGTH, AppLog.TMURCM10011.getCode())
				.build();

		//------------------------------------
		// エラーがある場合レスポンス作成処理
		//------------------------------------
		if(errors != null) {
			return responseProcessBuilder().of(FetchTaskResponseDto::new)
					.manipulate((FetchTaskResponseDto res) -> {
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
		// リクエスト処理を終了し、レスポンスを返します.
		return responseProcessBuilder().of(FetchTaskResponseDto::new)
				.filter((FetchTaskResponseDto dto) -> dto != null)
				.manipulate((FetchTaskResponseDto dto) -> {
					dto.setTasks(taskList);
					return dto;
				})
				.apply();
	}

}
