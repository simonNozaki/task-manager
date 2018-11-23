package com.tm.consts;

/**
 * ログメッセージ定義クラスです.
 */
public enum LogCode {


	//------------------------------------
	// API機能共通
	//------------------------------------

	/**
	 * Controllerの処理を開始します。
	 */
	TMFWCM00011("TMFWCM00011", "Controllerの処理を開始します。"),

	/**
	 * Controllerの処理を終了します。
	 */
	TMFWCM00012("TMFWCM00012", "Controllerの処理を終了します。"),

	/**
	 * Serviceの処理を開始します。
	 */
	TMFWCM00021("TMFWCM00021", "Serviceの処理を開始します。"),

	/**
	 * Serviceの処理を終了します。
	 */
	TMFWCM00022("TMFWCM00022", "Serviceの処理を終了します。"),

	/**
	 * 業務処理中にエラーが発生しました。
	 */
	TMFWCM90000("TMFWCM90000", "業務処理中にエラーが発生しました。"),

	TMFWCM90001("TMFWCM90002", "正常結果 : "),

	TMFWCM90002("TMFWCM90002", "エラー結果 : "),

	//------------------------------------
	// タスク管理
	//------------------------------------
	/**
	 * タスクの登録処理を開始します.
	 */
	TMTKRG00001("TMTKRG00001", "タスクの登録処理を開始します."),

	/**
	 * タスクの登録処理を終了します.
	 */
	TMTKRG00002("TMTKRG00002", "タスクの登録処理を終了します."),

	/**
	 * タスクの取得処理を開始します.
	 */
	TMTKFT00001("TMTKFT00001", "タスクの取得処理を開始します."),

	/**
	 * タスクの取得処理を終了します.
	 */
	TMTKFT00002("TMTKFT00002", "タスクの取得処理を終了します."),

	//------------------------------------
	// 利用者管理
	//------------------------------------


	//------------------------------------
	// エラーログ
	//------------------------------------
	/**
	 * タスク処理中にエラーを補足しました.
	 */
	TMTKCM90001("TMTKCM90001", "タスク処理中にエラーを補足しました."),

	/**
	 * 入力がありません.
	 */
	TMTKCM10001("TMTKCM10001", "入力がありません."),

	/**
	 * タスクタイトル、50桁以上
	 */
	TMTKCM10011("TMTKCM10011", "タスクタイトルが50桁以下ではありません。"),

	/**
	 * タスクラベル、20桁以上
	 */
	TMTKCM10012("TMTKCM10012", "タスクラベルが20桁以下ではありません。"),

	/**
	 * 完了フラグ、1桁以上
	 */
	TMTKCM10013("TMTKCM10013", "完了フラグが1桁ではありません。"),

	/**
	 * タスクメモ、200桁以上
	 */
	TMTKCM10014("TMTKCM10014", "タスクメモが200桁以下ではありません。"),

	/**
	 * タスクの登録処理中にエラーが発生しました.
	 */
	TMTKRG90001("TMTKRG90001", "タスクの登録処理中にエラーが発生しました."),

	/**
	 * 利用者の処理中にエラーを補足しました.
	 */
	TMURCM90001("TMURCM90001", "利用者の処理中にエラーを補足しました."),

	/**
	 * 入力がない
	 */
	TMURCM10001("TMURCM10001", "入力がありません。"),

	/**
	 * 利用者IDが10桁でない
	 */
	TMURCM10011("TMURCM10011", "利用者IDが10桁でない"),

	/**
	 * 利用者氏名、50桁以上
	 */
	TMURCM10012("TMTKCM10012", "利用者氏名が50桁以下ではありません。"),

	/**
	 * 利用者メールアドレス、100桁以上
	 */
	TMURCM10013("TMTKCM10013", "利用者メールアドレスが100桁以下ではありません。"),

	/**
	 * 利用者パスワード、100桁以上
	 */
	TMURCM10014("TMTKCM10014", "利用者パスワードが100桁以下ではありません。"),

	/**
	 * 利用者フラグ、1桁でない
	 */
	TMURCM10015("TMTKCM10015", "利用者フラグが1桁ではありません。");


    /**
     * ログID
     */
	private final String code;

	/**
	 * ログメッセージ
	 */
	private String message;

	/**
	 * デフォルトコンストラクタ
	 * @param code
	 */
	private LogCode(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}