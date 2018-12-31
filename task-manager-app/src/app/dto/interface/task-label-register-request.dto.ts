/**
 * タスクラベル登録リクエストDTOクラス。
 */
export class TaskLabelRegisterRequestDto {

    /**
     * タスクラベル
     */
    public taskLabel: string;

    /**
     * タスクラベル利用フラグ
     */
    public usedFlag: string;
    
    /**
     * 利用者ID
     */
    public userId: string;
}