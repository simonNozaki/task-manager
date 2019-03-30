/**
 * Service共通の定数定義モジュールです.
 */
export module ServiceConst {

  //-----------------------------
  //APIURLの基本コンポーネント定義
  //-----------------------------
  
  /**
   * ドメイン
   */
  export const BASE_URL = 'http://localhost:18080/';
  
  /**
   * コンテキストパス
   */
  export const BASE_API_VERSION = 'api/v1';
  
  /**
   * 処理大項目：タスク
   */
  export const BASE_URL_FUNC = '/task';
  
  /**
   * 処理大項目：利用者
   */
  export const BASE_URL_USER = '/user';

  /**
   * 処理中項目：タスクラベル
   */
  export const BASE_URL_TASK_LABEL_FUNC = '/label';
  
  /**
   * スラッシュ
   */
  export const BASE_SLASH = '/';

  //-----------------------------
  // 画面URL定義
  //-----------------------------
  /**
   * タスク一覧画面
   */
  export const URL_WEB_TASK = 'task';

  /**
   * タスクタイムライン表示
   */
  export const URL_WEB_TIMELINE: string = URL_WEB_TASK + "/timeline";

  /**
   * 利用者登録画面
   */
  export const URL_WEB_USER_SIGNUP = 'signup';

  /**
   * 利用者認証画面
   */
  export const URL_WEB_USER_SIGNIN = 'signin';

  //-----------------------------
  // コールするAPIのURL定義
  //-----------------------------
  /** 
   * タスクの新規登録 
   */
  export const URL_TASK_REGIST = BASE_URL + BASE_API_VERSION + BASE_URL_FUNC + '/regist';
  
  /** 
   * タスクの取得
   */
  export const URL_TASK_FETCH = BASE_URL + BASE_API_VERSION + BASE_URL_FUNC + '/fetch';
  
  /** 
   * タスクの編集
   */
  export const URL_TASK_UPDATE = BASE_URL + BASE_API_VERSION + BASE_URL_FUNC + '/update';
  
  /** 
   * タスクの削除 
   */
  export const URL_TASK_DELETE = BASE_URL + BASE_API_VERSION + BASE_URL_FUNC + '/delete';

  /**
   * タスクの完了
   */
  export const URL_TASK_COMPLETE = BASE_URL + BASE_API_VERSION + BASE_URL_FUNC + '/complete';

  /**
   * タスクラベル、取得および登録：/task/label
   */
  export const URL_TASK_LABEL = BASE_URL + BASE_API_VERSION + BASE_URL_FUNC + BASE_URL_TASK_LABEL_FUNC;

  /**
   * 利用者の新規登録
   */
  export const URL_USER_REGIST = BASE_URL + BASE_API_VERSION + BASE_URL_USER + '/signup';

  /**
   * 利用者のサインイン
   */
  export const URL_USER_SIGNIN = BASE_URL + BASE_API_VERSION + BASE_URL_USER + '/signin';
}

