import { Optional } from "@angular/core";
import { Task } from "../entity/task";

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
        if (t == null) {
            return true;
        }

        if (typeof(t) == "undefined") {
            return true;
        }
        return false;
    }
}
