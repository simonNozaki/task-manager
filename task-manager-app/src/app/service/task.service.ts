import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { HttpErrorResponse } from '@angular/common/http';
import 'rxjs/add/operator/map';

import { ServiceConst } from '../const/service-const';
import { RegistTaskResponse } from '../dto/interface/regist-task-response';
import { FetchTaskResponseDto } from '../dto/interface/fetch-task-response';
import { RegistTaskRequest } from '../dto/interface/regist-task-request';


/**
 * タスクの処理を行うServiceクラスです.
 */
@Injectable()
export class TaskService {

  /** HTTPクライアント、ロガーのデフォルトコンストラクタ */
  constructor(private http: HttpClient) { }

  /** エラーハンドラです. */
  private handleError(error: HttpErrorResponse) {
    // クライアントサイドのErrorハンドリングです.
    if (error.error instanceof ErrorEvent) {
      console.error('Errorが発生しました. : ', error.error.message);
    // サーバサイドのErrorハンドリングです.
    } else {
      console.error(
      `API : ステータスコードを返却しました. ${error.status}, ` +
      `エラーメッセージ : ${error.error}`);
    }
    return Observable.throw(error.error.message);
  }

  /** タスクの一覧を取得するAPIをコールします. */
  fetchTask(userId: string): Observable<FetchTaskResponseDto> {
    // APIをコールして、結果を取得します.
    return this.http.get<FetchTaskResponseDto>(
      ServiceConst.URL_TASK_FETCH,
      { params: {
        userId: userId
      } })
      .pipe(catchError(this.handleError));
  }

  /** 新規タスクを登録するAPIをコールします. */
  registTask(registTaskRequest: RegistTaskRequest): Observable<RegistTaskResponse> {
    // HTTPリクエストのオプションをセットします.
    const httpOptions = {
      headers : new HttpHeaders({'Content-Type':  'application/json'})
    };
    // APIをコールします.
    return this.http.post<RegistTaskResponse>(ServiceConst.URL_TASK_REGIST, registTaskRequest, httpOptions)
      .pipe(catchError(this.handleError));
  }

}
