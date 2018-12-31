import { Errors } from "../common/errors";

/**
 * タスクラベル登録レスポンスDTOクラス。
 */
export class TaskLabelRegisterResponseDto {

    /**
     * ラベルID
     */
    public labelId: string;
    
    /**
     * タスクラベル（名）
     */
    public taskLabel: string;

    /**
     * エラー結果
     */
    public errors: Errors;

}