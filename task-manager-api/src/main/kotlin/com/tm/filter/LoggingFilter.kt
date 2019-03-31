package com.tm.filter

import com.github.isrsal.logging.ResponseWrapper
import com.github.isrsal.logging.RequestWrapper
import com.tm.config.AppLogger
import com.tm.consts.AppConst
import com.tm.consts.log.LogCode
import com.tm.util.ObjectUtil
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.util.concurrent.atomic.AtomicLong



/**
 * ロギングフィルタークラス。
 */
open class LoggingFilter : OncePerRequestFilter() {

    private var id = AtomicLong(1)

    override fun doFilterInternal(request: HttpServletRequest?, response: HttpServletResponse?, filterChain: FilterChain) {

        var wrappedRequest: RequestWrapper? = RequestWrapper(id.incrementAndGet(), request)
        var wrappedResponse: ResponseWrapper? = ResponseWrapper(id.incrementAndGet(), response)

        try{
            filterChain.doFilter(request, response)
        }catch (e: ServletException){
            AppLogger.trace(LogCode.TMFWCM00022, null, Object().`class`.enclosingClass.canonicalName, Object().`class`.enclosingMethod.name, null)
        }finally {
            // リクエストのロギング
            if(ObjectUtil.isNullOrEmpty(wrappedRequest)){
                logRequest(wrappedRequest!!)
            }

            if(ObjectUtil.isNullOrEmpty(wrappedResponse)){
                logResponse(wrappedResponse!!)
            }
        }
    }

    /**
     * リクエスト情報をログ出力します。
     * @param ラップされたHttpServletRequestインスタンス
     */
    private fun logRequest(req: RequestWrapper): Unit {
        var msg: java.lang.StringBuilder = StringBuilder()

        // リクエストID
        msg.append("id=").append(req.id).append(AppConst.STR_SEMI_COLON + AppConst.STR_SPACE)
        // HTTPメソッド
        msg.append("method=").append(req.getMethod()).append(AppConst.STR_SEMI_COLON + AppConst.STR_SPACE)
        // URI
        msg.append("uri=").append(req.getRequestURI())
        if(ObjectUtil.isNullOrEmpty(req.queryString)){
            msg.append('?').append(req.getQueryString());
        }
        // Payload
        msg.append(AppConst.STR_SEMI_COLON + AppConst.STR_SPACE)
                .append("payload=").append(req.toByteArray())

        AppLogger.traceTelegram(LogCode.TMFWCM80005, Object().`class`.enclosingClass.canonicalName, Object().`class`.enclosingMethod.name, msg.toString())
    }

    /**
     * レスポンス情報をログ出力します。
     * @param ラップされたHttpServletResponseインスタンス
     */
    private fun logResponse(res: ResponseWrapper): Unit {
        var msg: java.lang.StringBuilder = StringBuilder()

        // リクエストID
        msg.append("id=").append(res.id).append(AppConst.STR_SEMI_COLON + AppConst.STR_SPACE)
        // Payload
        msg.append("payload=").append(res.toByteArray())

        AppLogger.traceTelegram(LogCode.TMFWCM80006, Object().`class`.enclosingClass.canonicalName, Object().`class`.enclosingMethod.name, msg.toString())
    }
}