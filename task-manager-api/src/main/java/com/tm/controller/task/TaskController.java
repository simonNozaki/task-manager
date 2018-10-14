package com.tm.controller.task;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.tm.dto.bean.regist.RegistTaskRequestDto;
import com.tm.dto.bean.regist.RegistTaskResponseDto;
import com.tm.dto.common.Errors;
import com.tm.service.logic.FetchTaskService;
import com.tm.service.logic.RegistTaskService;

import ch.qos.logback.classic.Logger;

/**
 * タスクのCRUD操作を行うコントローラクラスです.
 */
@RestController
@RequestMapping(CtrlConst.URI_API_VERSION)
public class TaskController extends BaseRestController {

	@Autowired
	RegistTaskService registTaskService;
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
	public FetchTaskResponseDto fetchTask(@RequestParam("userId") String userId)
			throws Exception {
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

	/** 新規タスクを登録します.
	 * @throws Exception */
	@RequestMapping(value = CtrlConst.FUNC_TASKS + CtrlConst.MAP_REGIST_TASK, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@CrossOrigin
	public RegistTaskResponseDto registTask(@RequestBody RegistTaskRequestDto task) throws Exception {
		RegistTaskResponseDto res = new RegistTaskResponseDto();
		logger.info(AppLog.TM_TK_RG_INFO_001.getCode());
		// Serviceクラスを呼び出します.
		Task registResult = registTaskService.registerTask(task);
		// レスポンスオブジェクトを作成します.
		res.setTaskId(registResult.getTaskId());
		res.setTaskTitle(registResult.getTaskTitle());
		res.setErrors(errors);
		logger.info(AppLog.TM_TK_RG_INFO_002.getCode());
		return res;
	}
}
