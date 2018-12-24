import { TaskLabel } from "../../entity/task-label";
import { Errors } from "../common/errors";

/**
 * タスクラベル情報取得レスポンスDTOクラス。
 */
export class TaskLabelFetchResponseDto {

    /**
     * ラベル情報リスト
     */
    public labels: TaskLabel[];

    /**
     * エラー結果
     */
    public errors: Errors;
    
}