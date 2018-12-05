import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AppConst } from '../../../const/app.const';
import { SignupService } from '../../../service/signup.service';
import { UserSignupRequestDto } from '../../../dto/interface/user-signup-request.dto';
import { TaskManagerCode } from '../../../codedef/task-manager-code';
import { UserSignupResponseDto } from '../../../dto/interface/user-signup-response.dto';

/**
 * 利用者サインアップコンポーネントクラス。
 */
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

    //-----------------------------
    // コンポーネント内プロパティ
    //-----------------------------

    /**
     * デフォルトコンストラクタ
     */
    constructor(private signupService: SignupService) { }

    ngOnInit() {
    }

    /** 
     * バリデーションチェック結果
     */
    public checkedResult: string;

    /**
     * 利用者情報登録フォームグループ
     */
    public signupForm: FormGroup = new FormGroup({
        userNameControl: new FormControl("", [Validators.required, Validators.maxLength(AppConst.USER_NAME_MAX_LENGTH)]),
        emailControl: new FormControl("", [Validators.required, Validators.maxLength(AppConst.USER_EMAIL_MAX_LENGTH)]),
        passwordControl: new FormControl("", [Validators.required, Validators.maxLength(AppConst.USER_PASSWORD_MAX_LENGTH)])
    })

    /**
     * サインアップを実行します。
     */
    public signip() {
        if (!this.violateRistriction()) {
            // リクエストの生成
            var req: UserSignupRequestDto = new UserSignupRequestDto();
            req.setUserName(this.signupForm.get("userNameControl").value);
            req.setEmail(this.signupForm.get("emailControl").value);
            req.setPassword(this.signupForm.get("passwordControl").value);
            req.setUsedFlag(TaskManagerCode.USER_USED_FLAG_USED);

            // Serviceクラスを実行します。
            this.signupService.signup(req).subscribe((res: UserSignupResponseDto) => {
                console.log(JSON.stringify(res));
            })
        }
      
    }


    /**
     * 入力チェックに適合していることをチェックします。
     * @returns boolean
     */
    public violateRistriction(): boolean{
        // 名前の入力チェック。
        if (this.signupForm.get('userNameControl').hasError('required')) {
          this.checkedResult = AppConst.USER_SIGNUP_USERNAME_REQUIRED_VIOLATED;
          return true;
        } else if (this.signupForm.get('userNameControl').hasError('maxlength')) {
          this.checkedResult = AppConst.USER_SIGNUP_USERNAME_LENGTH_VIOLATED;
          return true;
        }

        // メールアドレスの入力チェック。
        if (this.signupForm.get('emailControl').hasError('required')) {
          this.checkedResult = AppConst.USER_SIGNUP_EMAIL_REQUIRED_VIOLATED;
          return true;
        } else if (this.signupForm.get('emailControl').hasError('maxlength')) {
          this.checkedResult = AppConst.USER_SIGNUP_EMAIL_LENGTH_VIOLATED;
          return true;
        }

        // メールアドレスの入力チェック。
        if (this.signupForm.get('passwordControl').hasError('required')) {
          this.checkedResult = AppConst.USER_SIGNUP_PASSWORD_REQUIRED_VIOLATED;
          return true;
        } else if (this.signupForm.get('passwordControl').hasError('maxlength')) {
          this.checkedResult = AppConst.USER_SIGNUP_PASSWORD_LENGTH_VIOLATED;
          return true;
        }

        return false;
    }

}
