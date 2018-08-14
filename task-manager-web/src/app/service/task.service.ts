import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

import { Task } from '../entity/task';
import { ServiceConst } from '../modules/service-const';
import { RegistTaskRequest } from '../dto/interface/regist-task-request';
import { HttpErrorResponse } from '@angular/common/http';
import { Logger } from '../config/logger';

/**
 * タスクの処理を行うServiceです.
 */
@Injectable()
export class TaskService {

  /** HTTPクライアントのデフォルトコンストラクタ */
  constructor(private http: HttpClient) { }

  /** ロガーインスタンス */
  logger: Logger = new Logger();

  /** エラーハンドラです. */
  private handleError(error: HttpErrorResponse) {
    // クライアントサイドのErrorハンドリングです.
    if (error.error instanceof ErrorEvent) {
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

  // 新規タスクを登録するAPIをコールします.
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
