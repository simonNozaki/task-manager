import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import 'rxjs/add/operator/map';

import { ServiceConst } from '../const/service-const';
import { FetchTaskResponseDto } from '../dto/interface/fetch-task-response';
import { RegistTaskRequest } from '../dto/interface/regist-task-request';
import { GlobalErrorHandler } from '../exception/global-error-handler';
import { TaskCompleteRequestDto } from '../dto/interface/task-complete-request.dto';
import { TaskCompleteResponseDto } from '../dto/interface/task-complete-response.dto';


/**
 * タスクの処理を行うServiceクラスです.
 */
@Injectable()
export class TaskService {

  /** 
   * デフォルトコンストラクタ 
   */
  constructor(private http: HttpClient) { }

    /** 
     * タスクの一覧を取得するAPIをコールします.
     */
    public fetchTask(userId: string): Observable<FetchTaskResponseDto> {
        // APIをコールして、結果を取得します.
        return this.http.get<FetchTaskResponseDto>(
            ServiceConst.URL_TASK_FETCH,
            { params: {
                userId: userId
              }
            })
            .pipe(catchError(GlobalErrorHandler.handleError));
    }

    /** 
     * 新規タスクを登録するAPIをコールします.
     * @param registTaskRequest: RegistTaskRequest
     * @returns Observable<RegistTaskRequest>
     */
    public registTask(registTaskRequest: RegistTaskRequest): Observable<RegistTaskRequest> {
        // HTTPリクエストのオプションをセットします.
        const httpOptions = {
            headers : new HttpHeaders({
                'Content-Type':  'application/json'
              })
        };
        // APIをコールします.
        return this.http.post<RegistTaskRequest>(ServiceConst.URL_TASK_REGIST, registTaskRequest, httpOptions)
            .pipe(catchError(GlobalErrorHandler.handleError));
    }

    /**
     * タスクを完了します。
     * @param taskCompleteRequestDto 
     * @returns Observable<TaskCompleteResponseDto>
     */
    public complete(taskCompleteRequestDto: TaskCompleteRequestDto): Observable<TaskCompleteResponseDto> {
        // HTTPリクエストのヘッダー情報を設定します。
        const options = {
            headers : new HttpHeaders({
              'Content-Type':  'application/json'
            })
        }

        // APIをコールし、コンポーネントにObservableを返却します。
        return this.http.patch<TaskCompleteRequestDto>(ServiceConst.URL_TASK_COMPLETE, taskCompleteRequestDto, options)
            .pipe(catchError(GlobalErrorHandler.handleError));

    }
    

}
