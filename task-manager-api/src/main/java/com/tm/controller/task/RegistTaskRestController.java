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
import com.tm.consts.AppLog;
import com.tm.consts.CtrlConst;
import com.tm.controller.framework.BaseRestController;
import com.tm.dto.Task;
import com.tm.dto.bean.regist.RegistTaskRequestDto;
import com.tm.dto.bean.regist.RegistTaskResponseDto;
import com.tm.dto.common.Errors;
import com.tm.service.logic.RegistTaskService;
import com.tm.util.InputInspector;
import com.tm.util.ObjectUtil;

/**
 * タスク登録.
 *
 */
@RestController
@RequestMapping(CtrlConst.URI_API_VERSION)
public class RegistTaskRestController extends BaseRestController {

	@Autowired
	private RegistTaskService registTaskService;

	/** 新規タスクを登録します.
	 * @param RegistTaskRequestDto task
	 * @return RegistTaskResponseDto
	 * @throws Exception
	 */
	@RequestMapping(value = CtrlConst.FUNC_TASKS + CtrlConst.MAP_REGIST_TASK, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@CrossOrigin
	@ResponseStatus(HttpStatus.CREATED)
	public RegistTaskResponseDto registTask(@RequestBody RegistTaskRequestDto task) throws Exception {
		//------------------------------------
		// 入力内容の検査
		//------------------------------------
		Errors errors = InputInspector.of(task)
				.hasNullValue()
				.violateMaxLength(task.getTaskTitle(), AppConst.TASK_TITLE_MAX, AppLog.TMTKCM10011.getCode())
				.violateMaxLength(task.getTaskLabel(), AppConst.TASK_LABEL_MAX, AppLog.TMTKCM10012.getCode())
				.violateMaxLength(task.getTaskNote(), AppConst.TASK_NOTE_MAX, AppLog.TMTKCM10014.getCode())
				.violateSpecificLength(task.getUserId(), AppConst.USER_ID_LENGTH, AppLog.TMURCM10011.getCode())
				.build();

		//------------------------------------
		// エラーがある場合レスポンス作成処理
		//------------------------------------
		if(!ObjectUtil.isNullOrEmpty(errors.getCodes())) {
			return responseProcessBuilder().of(RegistTaskResponseDto::new)
					.operate((RegistTaskResponseDto res) -> {
						res.setErrors(errors);
						return res;
					})
					.apply();
		}

		//------------------------------------
		// サービスクラスの実行
		//------------------------------------
		Task registResult = registTaskService.registerTask(task);

		//------------------------------------
		// レスポンス処理
		//------------------------------------
		return responseProcessBuilder().of(RegistTaskResponseDto::new)
				.operate((RegistTaskResponseDto res) -> {
					res.setTaskId(registResult.getTaskId());
					res.setTaskTitle(registResult.getTaskTitle());
					return res;
				})
				.apply();
	}
}
