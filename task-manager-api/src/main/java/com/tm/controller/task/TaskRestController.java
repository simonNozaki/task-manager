package com.tm.controller.task;

import java.util.Optional;

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
import com.tm.consts.CtrlConst;
import com.tm.consts.error.TaskManagerErrorCode;
import com.tm.controller.framework.BaseRestController;
import com.tm.dto.Task;
import com.tm.dto.bean.task.TaskRegistRequestDto;
import com.tm.dto.bean.task.TaskRegistResponseDto;
import com.tm.dto.common.Errors;
import com.tm.dto.common.ServiceOut;
import com.tm.service.task.TaskRegisterService;
import com.tm.util.InputInspector;
import com.tm.util.ObjectUtil;

/**
 * タスク登録.
 *
 */
@RestController
@RequestMapping(CtrlConst.URI_API_VERSION)
public class TaskRestController extends BaseRestController {

	@Autowired
	private TaskRegisterService registTaskService;

	/** 新規タスクを登録します.
	 * @param TaskRegistRequestDto task
	 * @return RegistTaskResponseDto
	 * @throws Exception
	 */
	@RequestMapping(value = CtrlConst.FUNC_TASKS + CtrlConst.MAP_REGIST, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@CrossOrigin
	@ResponseStatus(HttpStatus.ACCEPTED)
	public TaskRegistResponseDto register(@RequestBody TaskRegistRequestDto task) throws Exception {
		//------------------------------------
		// 入力内容の検査
		//------------------------------------
		Errors errors = InputInspector.of(task)
		                    .logInput(task)
		                    .hasNullValue(TaskManagerErrorCode.ERR910001.getCode())
                            .violateMaxLength(Optional.ofNullable(task.getTaskTitle()), AppConst.TASK_TITLE_MAX, TaskManagerErrorCode.ERR220002.getCode())
                            .violateMaxLength(Optional.ofNullable(task.getTaskLabel()), AppConst.TASK_LABEL_MAX, TaskManagerErrorCode.ERR230001.getCode())
                            .violateMaxLength(Optional.ofNullable(task.getTaskNote()), AppConst.TASK_NOTE_MAX, TaskManagerErrorCode.ERR240001.getCode())
//                            .violateSpecificLength(Optional.ofNullable(task.getUserId()), AppConst.USER_ID_LENGTH, TaskManagerErrorCode.ERR110003.getCode())
                            .build();

        //------------------------------------
        // エラーがある場合レスポンス作成処理
        //------------------------------------
		if(!ObjectUtil.isNullOrEmpty(errors.getCodes())) {
            return responseProcessBuilder().of(TaskRegistResponseDto::new)
					.operate((TaskRegistResponseDto res) -> {
					    Optional.ofNullable(task.getTaskTitle()).ifPresent((String taskTitle) -> {
					        res.setTaskTitle(taskTitle);
					    });
						res.setErrors(errors);
						return res;
					})
					.logOutput(errors)
					.apply();
		}

		//------------------------------------
		// サービスクラスの実行
		//------------------------------------
		ServiceOut<Task> registResult = registTaskService.registerTask(task);

		//------------------------------------
		// レスポンス処理
		//------------------------------------
		return responseProcessBuilder().of(TaskRegistResponseDto::new)
		        .logOutput(registResult.getValue())
				.operate((TaskRegistResponseDto res) -> {
					res.setTaskId(registResult.getValue().getTaskId());
					res.setTaskTitle(registResult.getValue().getTaskTitle());
					return res;
				})
				.apply();
	}
}
