package com.tm.controller.user;

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
import com.tm.consts.LogCode;
import com.tm.controller.framework.BaseRestController;
import com.tm.dto.Users;
import com.tm.dto.bean.user.UserAuthenticationRequestDto;
import com.tm.dto.bean.user.UserAuthenticationResponseDto;
import com.tm.dto.common.Errors;
import com.tm.dto.common.ServiceOut;
import com.tm.service.user.UserAuthenticationService;
import com.tm.util.InputInspector;
import com.tm.util.ObjectUtil;

/**
 * 利用者認証RESTControllerクラス。
 */
@RestController
@RequestMapping(CtrlConst.URI_API_VERSION)
public class UserAuthenticationRestController extends BaseRestController {

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    /**
     * 実行メソッド
     * @param UserAuthenticationRequestDto 利用者認証処理のリクエストデータ
     * @return UserAUthenticationResponseDto 利用者認証処理の結果データ
     * @throws Exception
     */
    @RequestMapping(value = CtrlConst.FUNC_USERS + CtrlConst.MAP_AUTHENTICATION, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin(allowedHeaders="x-auth-token")
    @ResponseStatus(HttpStatus.CREATED)
    public UserAuthenticationResponseDto signin(@RequestBody UserAuthenticationRequestDto req) throws Exception {
        //------------------------------------
        // 入力内容の検査
        //------------------------------------
        Errors errors = InputInspector.of(req)
                            .logInput(req)
                            .hasNullValue(LogCode.TMURCM10001.getCode())
                            .violateMaxLength(req.getEmail(), AppConst.USER_EMAIL_MAX, LogCode.TMURCM10013.getCode())
                            .violateMaxLength(req.getPassword(), AppConst.USER_PASSWORD_MAX, LogCode.TMURCM10014.getCode())
                            .build();

        //------------------------------------
        // エラーがある場合レスポンス作成処理
        //------------------------------------
        if(!ObjectUtil.isNullOrEmpty(errors.getCodes())) {
            return responseProcessBuilder().of(UserAuthenticationResponseDto::new)
                        .operate(res -> {res.setErrors(errors); return res;})
                        .apply();
        }

        //------------------------------------
        // サービスクラスの実行
        //------------------------------------
        ServiceOut<Users> result = userAuthenticationService.execute(req);

        //------------------------------------
        // レスポンス処理
        //------------------------------------
        return responseProcessBuilder().of(UserAuthenticationResponseDto::new)
                  .operate((UserAuthenticationResponseDto res) -> {
                      Optional.ofNullable(result.getErrors()).ifPresent((Errors errs) -> res.setErrors(errs));
                      Optional.ofNullable(result.getValue()).ifPresent((Users users) -> {
                          res.setUserId(users.getUserId());
                          res.setEmail(users.getEmail());
                          res.setUserName(users.getUserName());
                          res.setPassword(users.getPassword());
                      });
                      return res;
                  })
                  .logOutput(result)
                  .apply();
    }
}
