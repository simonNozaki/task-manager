package com.tm.controller.task;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tm.TaskManagerApiApplication;
import com.tm.consts.AppLog;
import com.tm.consts.CtrlConst;
import com.tm.controller.framework.BaseRestController;
import com.tm.dto.Task;
import com.tm.dto.bean.fetch.FetchTaskResponseDto;
import com.tm.dto.common.Errors;
import com.tm.service.logic.FetchTaskService;

import ch.qos.logback.classic.Logger;

/**
 * タスクのCRUD操作を行うコントローラクラスです.
 */
@RestController
@RequestMapping(CtrlConst.URI_API_VERSION)
public class FetchTaskRestController extends BaseRestController {

	@Autowired
	FetchTaskService fetchTaskService;
	Errors errors = new Errors();
	List<String> codes = new ArrayList<>();

	/** ロガーインスタンス */
	Logger logger = (Logger) LoggerFactory.getLogger(TaskManagerApiApplication.class);

	/** 利用者に紐づくタスクの一覧を取得します. */
	@RequestMapping(value = CtrlConst.FUNC_TASKS + CtrlConst.MAP_FETCH_TASK,method = RequestMethod.GET)
	@ResponseBody
	@CrossOrigin
	public FetchTaskResponseDto fetchTask(@RequestParam("userId") String userId) throws Exception {
		FetchTaskResponseDto res = new FetchTaskResponseDto();
		// リクエスト処理を開始します.
		logger.info(AppLog.TM_TK_RG_INFO_001.getCode());
		// ユーザIDと紐づくタスクの一覧を取得します.
		List<Task> taskList = fetchTaskService.fetchTask(userId);
		// レスポンスオブジェクトを作成します.
		res.setTasks(taskList);
		res.setErrors(errors);
		// リクエスト処理を終了し、レスポンスを返します.
		logger.info(AppLog.TM_TK_RG_INFO_002.getCode());
		return res;
	}

}
