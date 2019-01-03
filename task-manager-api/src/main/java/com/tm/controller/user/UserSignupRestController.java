package com.tm.controller.user;

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
import com.tm.consts.log.LogCode;
import com.tm.controller.framework.BaseRestController;
import com.tm.dto.Users;
import com.tm.dto.bean.user.UserRegistRequestDto;
import com.tm.dto.bean.user.UserRegistResponseDto;
import com.tm.dto.common.Errors;
import com.tm.service.user.UserRegistService;
import com.tm.util.InputInspector;
import com.tm.util.ObjectUtil;

/**
 * ユーザ登録機能を提供するControllerクラスです。
 */
@RestController
@RequestMapping(CtrlConst.URI_API_VERSION)
public class UserSignupRestController extends BaseRestController{

	@Autowired
	UserRegistService userRegistService;

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
                            .violateMaxLength(user.getUserName(), AppConst.USER_NAME_MAX, LogCode.TMURCM10012.getCode())
                            .violateMaxLength(user.getEmail(), AppConst.USER_EMAIL_MAX, LogCode.TMURCM10013.getCode())
                            .violateMaxLength(user.getPassword(), AppConst.USER_PASSWORD_MAX, LogCode.TMURCM10014.getCode())
                            .violateSpecificLength(user.getUsedFlag(), AppConst.USER_FLAG_LENGTH, LogCode.TMURCM10015.getCode())
                            .build();

		//------------------------------------
		// エラーがある場合レスポンス作成処理
		//------------------------------------
		if(!ObjectUtil.isNullOrEmpty(errors.getCodes())) {
			return responseProcessBuilder().of(UserRegistResponseDto::new)
						.operate(res -> {res.setErrors(errors); return res;})
						.apply();
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
}
