import { SigninService } from "../../service/signin.service";
import { StubUsersSigninRequest } from "../stub-users-signin-request";

/**
 * 利用者認証Serviceのスタブクラス。
 */
export class SigninServiceStub extends SigninService {

    /**
     * 認証モックメソッド。
     */
    public mockSingin() {
        super.signin(StubUsersSigninRequest[0]);
    }

}
