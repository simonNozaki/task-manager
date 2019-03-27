package com.tm.controller.user;

import java.util.Optional;

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
import com.tm.dto.bean.user.UserAuthenticationRequestDto;
import com.tm.dto.bean.user.UserAuthenticationResponseDto;
import com.tm.dto.common.Errors;
import com.tm.exception.TaskManagerErrorRuntimeException;
import com.tm.service.user.UserAuthenticationService;
import com.tm.util.InputInspector;
import com.tm.util.ObjectUtil;

/**
 * 利用者認証RESTControllerクラス。
 */
@RestController
@RequestMapping(CtrlConst.URI_API_VERSION)
public class UserSigninRestController extends BaseRestController {

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    /**
     * 実行メソッド
     * @param UserAuthenticationRequestDto 利用者認証処理のリクエストデータ
     * @return UserAUthenticationResponseDto 利用者認証処理の結果データ
     * @throws Exception
     */
    @RequestMapping(value = CtrlConst.FUNC_USERS + CtrlConst.MAP_SIGNIN, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    public UserAuthenticationResponseDto signin(@RequestBody UserAuthenticationRequestDto req) throws Exception {
        //------------------------------------
        // 入力内容の検査
        //------------------------------------
        Errors errors = InputInspector.of(req)
                            .logInput(req)
                            .isNull(req.getEmail(), TaskManagerErrorCode.ERR130001.getCode())
                            .isNull(req.getPassword(), TaskManagerErrorCode.ERR140001.getCode())
                            .violateMaxLength(req.getEmail(), AppConst.USER_EMAIL_MAX, TaskManagerErrorCode.ERR130002.getCode())
                            .violateMaxLength(req.getPassword(), AppConst.USER_PASSWORD_MAX, TaskManagerErrorCode.ERR140002.getCode())
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
        return responseProcessBuilder().executeService(userAuthenticationService.execute(req))
                   .map((Users user, Errors error) -> {
                       UserAuthenticationResponseDto res = new UserAuthenticationResponseDto();
                       Optional.ofNullable(error).ifPresent((Errors errs) -> res.setErrors(errs));
                       Optional.ofNullable(user).ifPresent((Users users) -> {
                           res.setUserId(users.getUserId());
                           res.setEmail(users.getEmail());
                           res.setUserName(users.getUserName());
                           res.setPassword(users.getPassword());
                       });
                       return res;
                   })
                   .log()
                   .apply();

    }
}
