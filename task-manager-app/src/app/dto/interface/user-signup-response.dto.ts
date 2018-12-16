import { Errors } from "../common/errors";

/**
 * 利用者サインアップリクエストDTO。
 */
export class UserSignupResponseDto {

    /**
     * 利用者ID
     */
    private userId: string;

    /**
     * 利用者名
     */
    private userName: string;

    /**
     * エラーコード
     */
    private errors: Errors[];
}