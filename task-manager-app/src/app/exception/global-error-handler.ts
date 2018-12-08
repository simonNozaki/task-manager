import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { HttpErrorResponse } from "@angular/common/http";
import { Observable } from "rxjs";

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
          console.error('Errorが発生しました. : ', error.error.message);
        } 

        console.error(
        `API : ステータスコードを返却しました. ${error.status}, ` +
        `エラーメッセージ : ${error.error}`);
        
        return Observable.throw(error.error.message);
    }
}