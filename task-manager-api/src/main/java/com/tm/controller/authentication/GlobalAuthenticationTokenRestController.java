package com.tm.controller.authentication;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tm.consts.CtrlConst;
import com.tm.controller.framework.BaseRestController;

/**
 * 共通認証トークン発行RESTControllerクラス。
 */
@RestController
public class GlobalAuthenticationTokenRestController extends BaseRestController{

    /**
     * 認証トークンを発行します。
     * @param session HTTP通信のセッション情報
     * @return Map<String,String> トークン情報のマップインスタンス
     */
    @RequestMapping(CtrlConst.MAP_AUTH_TOKEN)
    public Map<String,String> token(HttpSession session) {
        return Collections.singletonMap("token", session.getId());
    }
}
