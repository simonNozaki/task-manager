/**
 * 利用者サインインリクエストDTO。
 */
export class UserSigninRequestDto {

    /**
     * 利用者名
     */
    public userName: string;

    /**
     * 利用者メールアドレス
     */
    public email: string;

    /**
     * 利用者パスワード
     */
    public password: string;

    /**
     * 利用フラグ
     */
    public usedFlag: string;
}