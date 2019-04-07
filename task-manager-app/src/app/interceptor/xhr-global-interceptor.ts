import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest, HttpEvent, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

/**
 * HTTP共通インターセプタークラス。
 */
@Injectable()
export class XhrGlobalInterceptor implements HttpInterceptor {
    
    /**
     * 全てのHTTPリクエスト共通の処理。オーバーライド。
     */
    public intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        // 認証用HTTPヘッダーを設定
        // TODO 独自ヘッダーはここで設定するようにする、あとで定数モジュールに移動する
        const xhr = req.clone({
            headers: req.headers.set("X-Requested-With", "XMLHttpRequest")
        });

        // レスポンス内容をコンソールに表示する
        // next.handle(req).do((event: any) => {
        //     if (event instanceof HttpResponse) {
        //         console.log(JSON.stringify(event.body))
        //     }
        // })

        // 動かないときはreqに戻す
        return next.handle(req).catch((error, caught) => {
            if(error.status === 401) {

            } 
            return Observable.throw(error);
        }) as any;
    }
}
