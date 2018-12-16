import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UserSigninRequestDto } from '../dto/interface/user-signin-request.dto';
import { ServiceConst } from '../const/service-const';
import { catchError } from 'rxjs/operators';
import { GlobalErrorHandler } from '../exception/global-error-handler';
import { Observable } from 'rxjs';
import { UserSigninResponseDto } from '../dto/interface/user-signin-response.dto';

/**
 * 利用者サービスクラス。
 */
@Injectable()
export class UserService {

    /**
     * デフォルトコンストラクタ。
     */
    constructor(private http: HttpClient) { }

    /**
     * 新規利用者のサインインを実施します。
     * @param userSigninRequestDto
     * @returns Observable<UserSigninResponseDto>
     */
    public signin(userSigninRequestDto: UserSigninRequestDto): Observable<UserSigninResponseDto> {
        // ヘッダー情報を設定します。
        const options = {
          headers : new HttpHeaders({
              'Content-Type':  'application/json'
            })
        };

        // POSTリクエストを実行します。
        return this.http.post<UserSigninRequestDto>(ServiceConst.URL_USER_REGIST, userSigninRequestDto, options)
            .pipe(catchError(GlobalErrorHandler.handleError));
    }

}
