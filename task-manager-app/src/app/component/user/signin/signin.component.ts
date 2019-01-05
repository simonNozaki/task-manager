import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AppConst } from '../../../const/app.const';
import { Router } from '@angular/router';
import { SigninService } from '../../../service/signin.service';
import { CommonDeliveryService } from '../../../service/common-delivery.service';
import { UserSigninRequestDto } from '../../../dto/interface/user-signin-request.dto';
import { UserSigninResponseDto } from '../../../dto/interface/user-signin-response.dto';
import { ObjectUtil } from '../../../util/object.util';
import { ServiceConst } from '../../../const/service-const';
import { CustomInputChecker } from '../../../util/custom-input-checker';
import { StringUtil } from '../../../util/string-util';


/**
 * 利用者認証コンポーネントクラス。
 */
@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {

    /**
     * デフォルトコンストラクタ。
     */
    constructor(private signinService: SigninService, private router: Router, private commonDeliveryService: CommonDeliveryService) {
        this.router = router;
    }

    /** 
     * バリデーションチェック結果
     */
    public checkedResult: string;

    /**
     * 利用者認証フォームグループ
     */
    public signinForm: FormGroup = new FormGroup({
        emailControl: new FormControl("", [Validators.required, Validators.maxLength(AppConst.USER_EMAIL_MAX_LENGTH),
            CustomInputChecker.matchFormat(StringUtil.REGEX_FORMAT_EMAIL)]),
        passwordControl: new FormControl("", [Validators.required, Validators.maxLength(AppConst.USER_PASSWORD_MAX_LENGTH)])
    });

    /**
     * 利用者ID
     */
    public userId: string;

    ngOnInit() {
    }  

    /**
     * サインインを実行します。
     */
    public signin(): void {
        // リクエストの作成。
        var req: UserSigninRequestDto = new UserSigninRequestDto();
        req.email = this.signinForm.get("emailControl").value;
        req.password = this.signinForm.get("passwordControl").value;

        // サービスクラスを実行します。
        this.signinService.signin(req).subscribe((res: UserSigninResponseDto) => {
            // 正常な場合、サービス経由で利用者IDをタスクコンポーネントに引き渡してリダイレクト
            if (ObjectUtil.isNullOrUndefined(res.errors)) {
                this.commonDeliveryService.emitUserIdChange(res.userId);
                this.router.navigateByUrl(ServiceConst.BASE_SLASH + ServiceConst.URL_WEB_TASK);
            } else {
                this.checkedResult = AppConst.USER_INFO_INVALID;
            }
        })
    }

    /**
     * 入力チェックに適合していることをチェックします。
     * @returns boolean
     */
    public violateRistriction(): boolean{
        // メールアドレスの入力チェック。
        if (this.signinForm.get('emailControl').hasError('required')) {
            this.checkedResult = AppConst.USER_SIGNUP_EMAIL_REQUIRED_VIOLATED;
            return true;
        } else if (this.signinForm.get('emailControl').hasError('maxlength')) {
            this.checkedResult = AppConst.USER_SIGNUP_EMAIL_LENGTH_VIOLATED;
            return true;
        }

        // メールアドレスの入力チェック。
        if (this.signinForm.get('passwordControl').hasError('required')) {
            this.checkedResult = AppConst.USER_SIGNUP_PASSWORD_REQUIRED_VIOLATED;
            return true;
        } else if (this.signinForm.get('passwordControl').hasError('maxlength')) {
            this.checkedResult = AppConst.USER_SIGNUP_PASSWORD_LENGTH_VIOLATED;
            return true;
        }
        return false;
    }

}
