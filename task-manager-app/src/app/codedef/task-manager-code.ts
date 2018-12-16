/**
 * task-manager-appのコード定義クラスです.
 */
export module TaskManagerCode {

   //----------------------------
   // タスクコード定義
   //----------------------------
  /** 
   * タスク完了フラグ：0, 登録済み
   */
  export const TASK_COMPLETED_FLAG_REGISTED = "0";
  
  /**
   * タスク完了フラグ：1, 完了
   */
  export const TASK_COMPLETED_FLAG_COMPLETED = "1";
  
  /**
   * タスク完了フラグ：2, 削除済み
   */
  export const TASK_COMPLETED_FLAG_DELETED = "2";

    //----------------------------
    // タスクコード定義
    //----------------------------
    /**
     * 利用者利用フラグ：0, 利用中
     */
    export const USER_USED_FLAG_USED = "0";

    /**
     * 利用者利用フラグ：1,退会済み
     */
    export const USER_USED_FLAG_DELETED = "1";

}