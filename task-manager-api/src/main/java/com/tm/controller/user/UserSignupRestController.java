package com.tm.controller.user;

import java.util.stream.Stream;

import com.tm.consts.AppConst;
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

import com.tm.consts.CtrlConst;
import com.tm.consts.error.TaskManagerErrorCode;
import com.tm.controller.framework.BaseRestController;
import com.tm.dto.Users;
import com.tm.dto.bean.user.UserRegistRequestDto;
import com.tm.dto.bean.user.UserRegistResponseDto;
import com.tm.dto.common.Errors;
import com.tm.exception.TaskManagerErrorRuntimeException;
import com.tm.service.user.UserSignupService;
import com.tm.util.InputInspector;
import com.tm.util.ObjectUtil;

/**
 * ユーザ登録機能を提供するControllerクラスです。
 */
@RestController
@RequestMapping(CtrlConst.URI_API_VERSION)
public class UserSignupRestController extends BaseRestController{

	@Autowired
	UserSignupService userRegistService;

	/**
	 * 実行メソッド
	 * @param UserRegistRequestDto user
	 * @return UserRegistResponseDto
	 */
	@RequestMapping(value = CtrlConst.FUNC_USERS + CtrlConst.MAP_SIGNUP, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@CrossOrigin
	@ResponseStatus(HttpStatus.CREATED)
	public UserRegistResponseDto register(@RequestBody UserRegistRequestDto user) throws Exception {
		//------------------------------------
		// 入力内容の検査
		//------------------------------------
        Errors errors = InputInspector.of(user)
                            .logInput(user)
                            .violateMaxLength(user.getUserName(), AppConst.USER_NAME_MAX, TaskManagerErrorCode.ERR120002.getCode())
                            .violateMaxLength(user.getEmail(), AppConst.USER_EMAIL_MAX, TaskManagerErrorCode.ERR130002.getCode())
                            .violateMaxLength(user.getPassword(), AppConst.USER_PASSWORD_MAX, TaskManagerErrorCode.ERR140002.getCode())
                            .violateSpecificLength(user.getUsedFlag(), AppConst.USER_FLAG_LENGTH, TaskManagerErrorCode.ERR150003.getCode())
                            .evaluateCustomCondition((UserRegistRequestDto subject) -> {
                            	return !this.matchUserUsedFlag(subject.getUsedFlag());
                            }, TaskManagerErrorCode.ERR150004.getCode())
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
		return responseProcessBuilder().executeService(userRegistService.execute(user))
		    .map((Users target, Errors error) -> {
		    	UserRegistResponseDto res = new UserRegistResponseDto();
		    	res.setErrors(error);
		    	res.setUserId(target.getUserId());
		    	res.setUserName(target.getUserName());
		    	return res;
		    })
		    .log()
		    .apply();

	}

	/**
	 * 利用者フラグの存在チェックを実施します。
	 * @param subject 検査対象
	 * @return 定義されているフラグ情報であればtrueを返却します。
	 */
	private boolean matchUserUsedFlag(String subject) {
        return Stream.of(AppConst.USER_USED_FLAG_DELETED, AppConst.USER_USED_FLAG_REGISTERED)
                .anyMatch((String flag) -> {
                   return subject.equals(flag);
                });
    }
}
