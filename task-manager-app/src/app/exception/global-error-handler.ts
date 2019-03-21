import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { HttpErrorResponse } from "@angular/common/http";
import { Observable } from "rxjs";
import { AppConst } from "../const/app.const";

/**
 * サービスクラス共通エラーハンドラ
 */
export class GlobalErrorHandler {

    /**
     * エラーハンドリングを提供します。
     * @param error: HttpErrorResponse
     * @returns ErrorObservable
     */
    public static handleError(error: HttpErrorResponse): ErrorObservable {

        if (error.error instanceof ErrorEvent) {
          console.error(AppConst.GLOBAL_ERROR_MESSAGE + error.error.message + AppConst.STR_NEW_LINE);
        } 

        console.error(AppConst.GLOBAL_ERROR_STATUS_CODE + error.status + AppConst.GLOBAL_ERROR_STATUS_MESSAGE + error.error + AppConst.STR_NEW_LINE);
        
        return Observable.throw(error.error.message);
    }
}