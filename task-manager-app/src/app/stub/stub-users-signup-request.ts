import { UserSignupRequestDto } from "../dto/interface/user-signup-request.dto";

/**
 * 利用者登録リクエストのスタブデータ
 */
export const StubUsersSignupRequest: UserSignupRequestDto[] = [

    /**
     * 0:有効な利用者情報
     */
    { userName : "specuser", email : "stub@mock.com", password : "stubstub", usedFlag : "0" },

    /**
     * 1:入力が空
     */
    { userName : "", email : "", password : "", usedFlag : "0" },

    /**
     * 2:メールアドレス形式でない
     */
    { userName : "stubuser", email : "mock.com", password : "stubstub", usedFlag : "0" },

    /**
     * 3:メールアドレスが101文字
     */
    { userName : "stubuser", email : "Stubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstub@mock.com", password : "stubstub", usedFlag : "0" },

    /**
     * 4:メールアドレスが半角英数字でない
     */
    { userName : "stubuser", email : "ok@moｃｋ.coｍ", password : "stubstub", usedFlag : "0" },

    /**
     * 5:パスワードが101文字
     */
    { userName : "stubuser", email : "ok@mock.com", password : "stubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubstubs", usedFlag : "0" },

    /**
     * 6:パスワードが半角英数字でない
     */
    { userName : "stubuser", email : "ok@mock.com", password : "ｓｔｕｂｓｔｕｂ", usedFlag : "0" },

    /**
     * 7:利用者名が51文字
     */
    { userName : "tubstubstubstubstubstubstubstubstubstubstubstubuser", email : "ok@mock.com", password : "", usedFlag : "0" },

];