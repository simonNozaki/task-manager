/**
 * 利用者サインアップリクエストDTO。
 */
export class UserSignupRequestDto {

    /**
     * 利用者名
     */
    private userName: string;

    /**
     * 利用者メールアドレス
     */
    private email: string;

    /**
     * 利用者パスワード
     */
    private password: string;

    /**
     * 利用フラグ
     */
    private usedFlag: string;

    public getUserName() {
        return this.userName;
    }
    public setUserName(userName: string) {
        this.userName = userName;
    }
    public getEmail() {
        return this.email;
    }
    public setEmail(email: string) {
        this.email = email;
    }
    public getPassword() {
        return this.password;
    }
    public setPassword(password: string) {
        this.password = password;
    }
    public getUsedFlag() {
        return this.usedFlag;
    }
    public setUsedFlag(usedFlag: string) {
        this.usedFlag = usedFlag;
    }
}