package com.tm.controller.task;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tm.TaskManagerApiApplication;
import com.tm.consts.AppLog;
import com.tm.consts.CtrlConst;
import com.tm.controller.framework.BaseRestController;
import com.tm.dto.Task;
import com.tm.dto.bean.regist.RegistTaskRequestDto;
import com.tm.dto.bean.regist.RegistTaskResponseDto;
import com.tm.service.logic.RegistTaskService;

import ch.qos.logback.classic.Logger;

/**
 * タスク登録.
 *
 */
@RestController
@RequestMapping(CtrlConst.URI_API_VERSION)
public class RegistTaskRestController extends BaseRestController{

	@Autowired
	private RegistTaskService registTaskService;

	/** ロガーインスタンス */
	Logger logger = (Logger) LoggerFactory.getLogger(TaskManagerApiApplication.class);

	/** 新規タスクを登録します.
	 * @throws Exception */
	@RequestMapping(value = CtrlConst.FUNC_TASKS + CtrlConst.MAP_REGIST_TASK, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@CrossOrigin
	public RegistTaskResponseDto registTask(@RequestBody RegistTaskRequestDto task) throws Exception {
		logger.info(AppLog.TM_TK_RG_INFO_001.getCode());
		// Serviceクラスを呼び出します.
		Task registResult = registTaskService.registerTask(task);
		// レスポンスオブジェクトを作成します.
		return super.ResponseProcessBuilder().of(RegistTaskResponseDto::new)
				.filter((RegistTaskResponseDto res) -> registResult != null)
				.manipulate((RegistTaskResponseDto res) -> {
					res.setTaskId(registResult.getTaskId());
					res.setTaskTitle(registResult.getTaskTitle());
					return res;
				})
				.apply();
	}
}
