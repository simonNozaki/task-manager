import { ValidatorFn, AbstractControl } from "@angular/forms";

/**
 * 独自バリデーションクラス。
 */
export class CustomInputChecker {

    /**
     * 任意の正規表現にマッチすることを確認します
     * @param regex 任意の正規表現
     * @returns バリデーション結果
     */
    public static  matchFormat(regex: RegExp): ValidatorFn {
        return (control: AbstractControl): {[key: string]: any} | null => {
            return regex.test(control.value) ? { "無効なメールアドレス形式です : " : { regexViolation: control.value } } : null;
        }
    }
}
