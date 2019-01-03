import { UserSigninResponseDto } from "../dto/interface/user-signin-response.dto";

/**
 * 利用者認証スペックのスタブデータ
 */
export const StubUsersSigninResponse: UserSigninResponseDto[] = [

    // 正常系テストユーザ
    {  authenticationToken : "", userId : "TM00000001", userName : "stubuser", email : "ok@mock.com", password : "stubstub", errors : null }
];