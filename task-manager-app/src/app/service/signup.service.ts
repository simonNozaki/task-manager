import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ServiceConst } from '../const/service-const';
import { catchError } from 'rxjs/operators';
import { GlobalErrorHandler } from '../exception/global-error-handler';
import { UserSignupRequestDto } from '../dto/interface/user-signup-request.dto';
import { UserSignupResponseDto } from '../dto/interface/user-signup-response.dto';

/**
 * 利用者サインアップServiceクラス。
 */
@Injectable()
export class SignupService {

    /**
     * デフォルトコンストラクタ。
     */
    constructor(private http: HttpClient) { }

    /**
     * 新規利用者のサインアップを実施します。
     * @param userSigninRequestDto
     * @returns Observable<UserSignupResponseDto>
     */
    public signup(userSignupRequestDto: UserSignupRequestDto): Observable<UserSignupResponseDto> {
        // ヘッダー情報を設定します。
        const options = {
          headers : new HttpHeaders({
              'Content-Type':  'application/json'
            })
        };

        // POSTリクエストを実行します。
        return this.http.post<UserSignupRequestDto>(ServiceConst.URL_USER_REGIST, userSignupRequestDto, options)
            .pipe(catchError(GlobalErrorHandler.handleError));
    }

}
