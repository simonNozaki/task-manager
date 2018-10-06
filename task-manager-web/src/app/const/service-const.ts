/**
 * Service共通の定数定義モジュールです.
 */
export module ServiceConst {

  /****************************************
   *APIURLの基本コンポーネント定義
   *****************************************/
  export const BASE_URL = 'http://localhost:18080/';
  export const BASE_API_VERSION = '/api/v1';
  export const BASE_URL_FUNC = '/task';

  /****************************************
   *画面URL定義
   *****************************************/
  /** タスク一覧画面 */
  export const URL_WEB_TASK = '/task';

  /****************************************
   *コールするAPIのURL定義
   *****************************************/
  /** タスクの新規登録 */
  export const URL_TASK_REGIST = BASE_API_VERSION + URL + BASE_API_VERSION + BASE_URL_FUNC + '/regist';
  /** タスクの取得 */
  export const URL_TASK_FETCH = BASE_API_VERSION + URL + BASE_API_VERSION + BASE_URL_FUNC + '/fetch';
  /** タスクの編集 */
  export const URL_TASK_UPDATE = BASE_API_VERSION + URL + BASE_API_VERSION + BASE_URL_FUNC + '/update';
  /** タスクの削除 */
  export const URL_TASK_DELETE = BASE_API_VERSION + URL + BASE_API_VERSION + BASE_URL_FUNC + '/delete';
}

