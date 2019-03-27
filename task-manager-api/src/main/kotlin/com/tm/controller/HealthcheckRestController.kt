package com.tm.controller

import com.tm.consts.CtrlConst
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * ヘルスチェックREST Controller
 */
@RestController
class HealthcheckRestController {

    /**
     * 実行メソッド
     * @return OK
     */
    @RequestMapping(value = [CtrlConst.MAP_HEALTHCHECK], method = [RequestMethod.GET])
    @ResponseBody
    @CrossOrigin
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun checkHealthy(): String{
        return "OK"
    }

}