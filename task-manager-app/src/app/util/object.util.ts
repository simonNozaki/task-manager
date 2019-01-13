import { _ } from 'underscore';

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
        return (typeof t === null || typeof t === undefined) ? true : false;
    }

    /**
     * 指定されたリストから、検索条件を指定して一件削除します。
     * @param array, 削除する元ネタのリスト
     * @param key, 削除するオブジェクトのプロパティ
     * @param condition, 削除するオブジェクトの条件
     */
    public static deleteItem<T>(array: T[], key: any, condition: string | number): void{
        var object = {
            key : condition
        };

        // 削除対象の抽出
        var operand: T = _.where(array, object);
        // 削除対象の索引を取得
        var operandIndex: number = _.indexOf(array, operand);
        // 削除
        array.splice(operandIndex, 1);
    }

}
