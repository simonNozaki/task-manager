import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ServiceConst } from '../const/service-const';
import { catchError } from 'rxjs/operators';
import { GlobalErrorHandler } from '../exception/global-error-handler';
import { UserSigninResponseDto } from '../dto/interface/user-signin-response.dto';
import { UserSigninRequestDto } from '../dto/interface/user-signin-request.dto';

/**
 * 利用者サインインサービスクラス。
 */
@Injectable()
export class SigninService {

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
        return this.http.post<UserSigninRequestDto>(ServiceConst.URL_USER_SIGNIN, userSigninRequestDto, options)
            .pipe(catchError(GlobalErrorHandler.handleError));
    }

}
