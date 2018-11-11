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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tm.consts.AppConst;
import com.tm.consts.LogCode;
import com.tm.consts.CtrlConst;
import com.tm.controller.framework.BaseRestController;
import com.tm.dto.Users;
import com.tm.dto.bean.user.UserRegistRequestDto;
import com.tm.dto.bean.user.UserRegistResponseDto;
import com.tm.dto.common.Errors;
import com.tm.dto.common.ServiceOut;
import com.tm.service.user.UserRegistService;
import com.tm.util.InputInspector;
import com.tm.util.ObjectUtil;

/**
 * ユーザ登録機能を提供するControllerクラスです。
 */
@RestController
@RequestMapping(CtrlConst.URI_API_VERSION)
public class UserRegistRestController extends BaseRestController{

	@Autowired
	UserRegistService userRegistService;

	/**
	 * 実行メソッド
	 * @param UserRegistRequestDto user
	 * @return UserRegistResponseDto
	 */
	@RequestMapping(value = CtrlConst.FUNC_USERS + CtrlConst.MAP_REGIST, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	@CrossOrigin
	@ResponseStatus(HttpStatus.CREATED)
	public UserRegistResponseDto register(@RequestBody UserRegistRequestDto user) throws Exception {
	    ObjectMapper mapper = new ObjectMapper();
	    System.out.println(mapper.writeValueAsString(user));
		//------------------------------------
		// 入力内容の検査
		//------------------------------------
		Errors errors = InputInspector.of(user)
                            .hasNullValue(LogCode.TMURCM10001.getCode())
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
		// サービスクラスの実行
		//------------------------------------
		ServiceOut<Users> result = userRegistService.execute(user);

		//------------------------------------
		// レスポンス処理
		//------------------------------------
		return responseProcessBuilder().of(UserRegistResponseDto::new)
				.operate((UserRegistResponseDto res) -> {
							res.setUserId(result.getValue().getUserId());
							res.setUserName(result.getValue().getUserName());
							return res;
						})
						.apply();
	}
}
