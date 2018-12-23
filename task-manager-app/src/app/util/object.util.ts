/**
 * オブジェクト操作のユーティリティを提供します。
 */
export class ObjectUtil {

    /**
     * オブジェクトがnullもしくはundefinedであることを判定します。デフォルトはfalse。
     * @param t: any
     * @returns boolean
     */
    public static isNullOrUndefined(t: any): boolean {
        return (t == null || typeof t == "undefined") ? true : false;
    }

}
