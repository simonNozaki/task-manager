import { ObjectUtil } from "./object.util";

/**
 * コレクション操作のユーティリティ
 */
export class CollectionUtil<T> {

    /**
     * コレクションがNullやUndefinedではない要素を一つ以上もつことを確認します。
     * @param t コレクションオブジェクト
     * @returns 結果、条件をみたさない場合falseを返却する
     */
    public static hasItems<T>(t: T[]): Boolean{
        // null
        if(ObjectUtil.isNullOrUndefined(t)) return false;

        // サイズ0以下
        if(Object.keys(t).length <= 0) return false;

        return true;
    }
}
