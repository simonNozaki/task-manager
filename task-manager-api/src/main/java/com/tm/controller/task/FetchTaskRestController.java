package com.tm.controller.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tm.consts.AppLog;
import com.tm.consts.CtrlConst;
import com.tm.controller.framework.BaseRestController;
import com.tm.dto.Task;
import com.tm.dto.bean.fetch.FetchTaskResponseDto;
import com.tm.service.logic.FetchTaskService;

/**
 * タスクのCRUD操作を行うコントローラクラスです.
 */
@RestController
@RequestMapping(CtrlConst.URI_API_VERSION)
public class FetchTaskRestController extends BaseRestController {

	@Autowired
	private FetchTaskService fetchTaskService;

	/** 利用者に紐づくタスクの一覧を取得します. */
	@RequestMapping(value = CtrlConst.FUNC_TASKS + CtrlConst.MAP_FETCH_TASK,method = RequestMethod.GET)
	@ResponseBody
	@CrossOrigin
	public FetchTaskResponseDto fetchTask(@RequestParam("userId") String userId) throws Exception {
		// ユーザIDと紐づくタスクの一覧を取得します.
		List<Task> taskList = fetchTaskService.fetchTask(userId);
		// レスポンスオブジェクトを作成します.
		// リクエスト処理を終了し、レスポンスを返します.
		return ResponseProcessBuilder().of(FetchTaskResponseDto::new)
				.filter((FetchTaskResponseDto dto) -> dto != null)
				.manipulate((FetchTaskResponseDto dto) -> {
					dto.setTasks(taskList);
					return dto;
				})
				.logging(AppLog.TM_TK_FT_INFO_002.getCode(), "info", null)
				.apply();
	}

}
