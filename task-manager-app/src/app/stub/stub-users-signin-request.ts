import { UserSigninRequestDto } from "../dto/interface/user-signin-request.dto";

/**
 * 利用者認証スペックのスタブデータ
 */
export const StubUsersSigninRequest: UserSigninRequestDto[] = [

    /**
     * 0:正常系リクエストデータサンプル
     */
    { email : "ok@mock.com", password : "stubstub" },

    /**
     * 1:入力なし
     */
    { email : "", password : ""},

    /**
     * 2:メールアドレス形式でない
     */
    { email : "example.com", password : "stubstub"},

    /**
     * 3:半角英数字でない
     */
    { email : "ok@moｃｋ.coｍ", password : "stubstub"},

    /**
     * 4:メールアドレスが101文字
     */
    { email : "Stubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstub@mock.com", password : "stubstub"},

    /**
     * 5:パスワードが101文字
     */
    { email : "ok@mock.com", password : "stubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubs"},

    /**
     * 6:パスワードが半角英数字字でない
     */
    { email : "ok@mock.com", password : "ｓｔｕｂｓｔｕｂ"},

];