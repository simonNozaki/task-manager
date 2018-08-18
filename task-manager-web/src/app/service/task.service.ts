import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { HttpParams } from '@angular/common/http';
import { HttpErrorResponse } from '@angular/common/http';

import { Task } from '../entity/task';
import { ServiceConst } from '../const/service-const';
import { RegistTaskRequest } from '../dto/interface/regist-task-request';
import { Logger } from '../config/logger';
import { FetchTaskResponseDto } from '../dto/interface/fetch-task-response';
import { AppLog } from '../const/app-log';


/**
 * タスクの処理を行うServiceクラスです.
 */
@Injectable()
export class TaskService {

  /** HTTPクライアント、ロガーのデフォルトコンストラクタ */
  constructor(private http: HttpClient, private logger: Logger) { }

  /** エラーハンドラです. */
  private handleError(error: HttpErrorResponse) {
    // クライアントサイドのErrorハンドリングです.
    if (error.error instanceof ErrorEvent) {
      this.logger.errorLogger.error(AppLog.TW_TK_CM_001);
      console.error('Errorが発生しました. : ', error.error.message);
    // サーバサイドのErrorハンドリングです.
    } else {
      console.error(
      `サーバ次のステータスコードを返却しました. ${error.status}, ` +
      `エラーメッセージ : ${error.error}`);
    }
    return throwError(
    'Something bad happened; please try again later.');
  }

  /** タスクの一覧を取得するAPIをコールします. */
  fetchTask(userId: string): Observable<FetchTaskResponseDto> {
    // クエリパラメータを準備します.
    const params = new HttpParams({
      fromString: 'userId="$userId"'
    });
    // APIをコールして、結果を取得します.
    return this.http.request<FetchTaskResponseDto>(
      'GET',
      ServiceConst.URL_TASK_FETCH,
      {
        responseType: 'json',
        params
      }).pipe(catchError(this.handleError));
  }

  /** 新規タスクを登録するAPIをコールします. */
  registTask(task: Task): Observable<Task> {
    // HTTPリクエストのオプションをセットします.
    const httpOptions = {
      headers : new HttpHeaders({'Content-Type':  'application/json'})
    };
    // APIをコールします.
    return this.http.post<Task>(ServiceConst.URL_TASK_REGIST, task, httpOptions)
      .pipe(catchError(this.handleError));
  }

}
