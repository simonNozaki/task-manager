/**
 * アプリケーション共有の定数クラスです。
 */
export module AppConst {

    //-----------------------------
    // バリデーション定義
    //-----------------------------
    /**
     * タスクラベル、20桁
     */
    export const TASK_LABEL_MAX_LENGTH: number = 20;

    /**
     * 完了フラグ、1桁
     */
    export const COMPLETED_FLAG_LENGTH: number = 1;

    /**
     * タスクメモ、200桁
     */
    export const TASK_NOTE_MAX_LENGTH: number= 200;

    //-----------------------------
    // バリデーション違反メッセージ
    //-----------------------------
    /**
     * タイトルが入力されていません。タイトルは必須です。
     */
    export const TASK_TITLE_REQUIRED_VIOLATED: string = "";

    /**
     * ラベルが長すぎます。20文字までです。
     */
    export const TASK_LABEL_LENGTH_VIOLATED: string = "ラベルが長すぎます。20文字までです。";

    /**
     * 完了フラグが1桁ではありません。
     */
    export const COMPLETED_FLAG_LENGTH_VIOLATED: string = "完了フラグが1桁ではありません。";

    /**
     * タスクのメモが長すぎます。メモは200文字までです。
     */
    export const TASK_NOTE_LENGTH_VIOLATED: string = "タスクのメモが長すぎます。メモは200文字までです。";


}