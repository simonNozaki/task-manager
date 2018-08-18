package com.tm.controller.task;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tm.TaskManagerApiApplication;
import com.tm.consts.AppLog;
import com.tm.consts.CtrlConst;
import com.tm.consts.ErrorConst;
import com.tm.controller.framework.TmRestBaseController;
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
public class TaskController extends TmRestBaseController {

	@Autowired
	RegistTaskService registTaskService;
	@Autowired
	FetchTaskService fetchTaskService;
	Errors errors = new Errors();
	List<String> codes = new ArrayList<>();

	/** ロガーインスタンス */
	Logger logger = (Logger) LoggerFactory.getLogger(TaskManagerApiApplication.class);

	/** 利用者に紐づくタスクの一覧を取得します. */
	@RequestMapping(value = CtrlConst.FUNC_TASKS + CtrlConst.MAP_FETCH_TASK,
			method = RequestMethod.GET)
	@ResponseBody
	public FetchTaskResponseDto fetchTask(@RequestParam("userId") String userId)
			throws Exception {
		FetchTaskResponseDto res = new FetchTaskResponseDto();
		try {
			// リクエスト処理を開始します.
			logger.info(AppLog.TM_TK_RG_INFO_001.getCode());
			// ユーザIDと紐づくタスクの一覧を取得します.
			List<Task> taskList = fetchTaskService.fetchTask(userId);
			// レスポンスオブジェクトを作成します.
			res.setTasks(taskList);
			res.setErrors(errors);
			// リクエスト処理を終了し、レスポンスを返します.
			logger.info(AppLog.TM_TK_RG_INFO_002.getCode());
		} catch(Exception e) {
			// エラーをログ出力します.
			logger.error(AppLog.TM_TK_RG_ERR_001.getCode(), e.getMessage());
			// Error結果の一覧を作成します.
			if(e instanceof SQLException || e instanceof PSQLException) {
				codes.add(ErrorConst.TM_TK_RG_TH_001.getCode());
			}else if(e instanceof NullPointerException) {
				codes.add(ErrorConst.TM_TK_RG_IN_001.getCode());
			}
			// レスポンスオブジェクトを作成します.
			errors.setCodes(codes);
			res.setErrors(errors);
		}
		return res;
	}

	/** 新規タスクを登録します. */
	@RequestMapping(value = CtrlConst.FUNC_TASKS + CtrlConst.MAP_REGIST_TASK,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			 method = RequestMethod.POST)
	@ResponseBody
	public RegistTaskResponseDto registTask(@RequestBody RegistTaskRequestDto task) {
		RegistTaskResponseDto res = new RegistTaskResponseDto();
		try {
			logger.info(AppLog.TM_TK_RG_INFO_001.getCode());
			// Serviceクラスを呼び出します.
			Task registResult = registTaskService.registerTask(task);

			// レスポンスオブジェクトを作成します.
			res.setTaskId(registResult.getTaskId());
			res.setTaskTitle(registResult.getTaskTitle());
			res.setErrors(errors);
			logger.info(AppLog.TM_TK_RG_INFO_002.getCode());
		}catch(Exception e) {
			// エラーをログ出力します.
			logger.error(AppLog.TM_TK_RG_ERR_001.getCode(), e.getMessage());
			// Error結果の一覧を作成します.
			errors.setTaskTitle(task.getTaskTitle());
			if(e instanceof SQLException || e instanceof PSQLException) {;
				codes.add(ErrorConst.TM_TK_RG_TH_001.getCode());
			}else if(e instanceof NullPointerException) {
				codes.add(ErrorConst.TM_TK_RG_IN_001.getCode());
			}
			// レスポンスオブジェクトを作成します.
			res.setTaskTitle(task.getTaskTitle());
			errors.setCodes(codes);
			res.setErrors(errors);
		}
		return res;
	}
}
