/**
 * アプリケーション共有の定数クラスです。
 */
export module AppConst {

    //-----------------------------
    // バリデーション定義
    //-----------------------------
    /**
     * タスクタイトル、100桁
     */
    export const TASK_TITLE_MAX_LENGTH: number = 100;

    /**
     * タスクラベル、50桁
     */
    export const TASK_LABEL_MAX_LENGTH: number = 50;

    /**
     * 完了フラグ、1桁
     */
    export const COMPLETED_FLAG_LENGTH: number = 1;

    /**
     * タスクメモ、200桁
     */
    export const TASK_NOTE_MAX_LENGTH: number = 200;

    /**
     * 利用者名、50桁
     */
    export const USER_NAME_MAX_LENGTH: number = 50;

    /**
     * 利用者メールアドレス、100桁
     */
    export const USER_EMAIL_MAX_LENGTH: number = 100;

    /**
     * 利用者パスワード、100桁
     */
    export const USER_PASSWORD_MAX_LENGTH: number = 100;

    /**
     * 利用者利用フラグ、1桁
     */
    export const USER_USED_FLAG_LENGTH: number = 1;

    //-----------------------------
    // バリデーション違反メッセージ
    //-----------------------------
    /**
     * タイトルが入力されていません。タイトルは必須です。
     */
    export const TASK_TITLE_REQUIRED_VIOLATED: string = "タイトルが入力されていません。タイトルは必須です。";

    /**
     * ラベル名が入力されていません。ラベル名は必須入力です。
     */
    export const TASK_LABEL_REQUIRED_VIOLATED: string = "ラベル名が入力されていません。ラベル名は必須入力です。";

    /**
     * タイトルが長すぎます。100文字までです。
     */
    export const TASK_TITLE_LENGTH_VIOLATED: string = "タイトルが長すぎます。100文字までです。";

    /**
     * ラベルが長すぎます。50文字までです。
     */
    export const TASK_LABEL_LENGTH_VIOLATED: string = "ラベルが長すぎます。50文字までです。";

    /**
     * 完了フラグが1桁ではありません。
     */
    export const COMPLETED_FLAG_LENGTH_VIOLATED: string = "完了フラグが1桁ではありません。";

    /**
     * タスクのメモが長すぎます。メモは200文字までです。
     */
    export const TASK_NOTE_LENGTH_VIOLATED: string = "タスクのメモが長すぎます。メモは200文字までです。";

    /**
     * 名前が入力されていません。名前は必須入力です。
     */
    export const USER_SIGNUP_USERNAME_REQUIRED_VIOLATED: string = "名前が入力されていません。名前は必須入力です。";

    /**
     * 名前が長すぎます。名前は50文字までです。
     */
    export const USER_SIGNUP_USERNAME_LENGTH_VIOLATED: string = "名前が長すぎます。名前は50文字までです。";

    /**
     * メールアドレスが入力されていません。メールアドレスは必須入力です。
     */
    export const USER_SIGNUP_EMAIL_REQUIRED_VIOLATED: string = "メールアドレスが入力されていません。メールアドレスは必須入力です。";

    /**
     * 入力された文字列はメールアドレス形式ではありません。
     */
    export const USER_SIGNUP_EMAIL_INVALID_FORMAT: string = "入力された文字列はメールアドレス形式ではありません。";

    /**
     * メールアドレスが長すぎます。メールアドレスは100文字までです。
     */
    export const USER_SIGNUP_EMAIL_LENGTH_VIOLATED: string = "メールアドレスが長すぎます。メールアドレスは100文字までです。";

    /**
     * パスワードが入力されていません。パスワードは必須入力です。
     */
    export const USER_SIGNUP_PASSWORD_REQUIRED_VIOLATED: string = "パスワードが入力されていません。パスワードは必須入力です。";

    /**
     * パスワードが長すぎます。パスワードは100文字までです。
     */
    export const USER_SIGNUP_PASSWORD_LENGTH_VIOLATED: string = "パスワードが長すぎます。パスワードは100文字までです。";

    /**
     * メールアドレスは半角英数字のみ使用してください。
     */
    export const USER_EMAIL_NOT_HALF_SIZED: string = "メールアドレスは半角英数字のみ使用してください。";

    /**
     * パスワードは半角英数字のみ使用してください。
     */
    export const USER_PASSWORD_NOT_HALF_SIZED: string = "パスワードは半角英数字のみ使用してください。";

    /**
     * 入力されたパスワードはすでに利用されています。
     */
    export const USER_ALREADY_REGISTERD: string = "入力されたパスワードはすでに利用されています。";

    /**
     * 入力された利用者情報は存在しません。メールアドレス、もしくはパスワードが正しいことを確認してください。
     */
    export const USER_INFO_INVALID: string = "入力された利用者情報は存在しません。メールアドレス、もしくはパスワードが正しいことを確認してください。";

}