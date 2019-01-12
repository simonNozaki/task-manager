import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, AbstractControl } from '@angular/forms';
import { AppConst } from '../../../const/app.const';
import { Router } from '@angular/router';
import { SigninService } from '../../../service/signin.service';
import { CommonDeliveryService } from '../../../service/common-delivery.service';
import { UserSigninRequestDto } from '../../../dto/interface/user-signin-request.dto';
import { UserSigninResponseDto } from '../../../dto/interface/user-signin-response.dto';
import { ObjectUtil } from '../../../util/object.util';
import { ServiceConst } from '../../../const/service-const';
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
            Validators.email, Validators.pattern(StringUtil.REGEX_FORMAT_HALF_SIZE)]),
        passwordControl: new FormControl("", [Validators.required, Validators.maxLength(AppConst.USER_PASSWORD_MAX_LENGTH),
            Validators.pattern(StringUtil.REGEX_FORMAT_HALF_SIZE)])
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
        var email: AbstractControl = this.signinForm.get("emailControl");
        // メールアドレスの入力チェック。
        if (email.hasError('required') && (email.dirty || email.touched)) {
            this.checkedResult = AppConst.USER_SIGNUP_EMAIL_REQUIRED_VIOLATED;
            return true;
        } else if (email.hasError('maxlength') && (email.dirty || email.touched)) {
            this.checkedResult = AppConst.USER_SIGNUP_EMAIL_LENGTH_VIOLATED;
            return true;
        } else if (email.hasError("email") && (email.dirty || email.touched)) {
            this.checkedResult = AppConst.USER_SIGNUP_EMAIL_INVALID_FORMAT;
            return true;
        } else if (email.hasError("pattern") && (email.dirty || email.touched)) {
            this.checkedResult = AppConst.USER_EMAIL_NOT_HALF_SIZED;
            return true;
        }

        var password: AbstractControl = this.signinForm.get("passwordControl");
        // パスワードの入力チェック。
        if (password.hasError('required') && (password.dirty || password.touched)) {
            this.checkedResult = AppConst.USER_SIGNUP_PASSWORD_REQUIRED_VIOLATED;
            return true;
        } else if (password.hasError('maxlength') && (password.dirty || password.touched)) {
            this.checkedResult = AppConst.USER_SIGNUP_PASSWORD_LENGTH_VIOLATED;
            return true;
        } else if (password.hasError('pattern') && (password.dirty || password.touched)) {
            this.checkedResult = AppConst.USER_PASSWORD_NOT_HALF_SIZED;
            return true;
        }
        return false;
    }

}
