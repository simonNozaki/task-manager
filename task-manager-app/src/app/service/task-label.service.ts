import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TaskLabelFetchResponseDto } from '../dto/interface/task-label-fetch-response.dto';
import { Observable } from 'rxjs';
import { ServiceConst } from '../const/service-const';
import { catchError } from 'rxjs/operators';
import { GlobalErrorHandler } from '../exception/global-error-handler';
import { TaskLabelRegisterRequestDto } from '../dto/interface/task-label-register-request.dto';
import { TaskLabelRegisterResponseDto } from '../dto/interface/task-label-register-response.dto';

/**
 * タスクラベルをバックエンドとやりとりするServiceクラス。
 */
@Injectable()
export class TaskLabelService {

    /**
     * デフォルトコンストラクタ。
     */
    constructor(private http: HttpClient) { }

    /** 
     * タスクラベルの一覧を取得します。
     */
    public fetch(userId: string): Observable<TaskLabelFetchResponseDto> {
      // APIをコールして、結果を取得します.
      return this.http.get<TaskLabelFetchResponseDto>(
          ServiceConst.URL_TASK_LABEL,
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
    public registerLabel(req: TaskLabelRegisterRequestDto): Observable<TaskLabelRegisterResponseDto> {
      // HTTPリクエストのオプションをセットします.
      const httpOptions = {
          headers : new HttpHeaders({
              'Content-Type':  'application/json'
          })
      };
      // APIをコールします.
      return this.http.post<TaskLabelRegisterRequestDto>(ServiceConst.URL_TASK_LABEL, req, httpOptions)
          .pipe(catchError(GlobalErrorHandler.handleError));
  }

}
