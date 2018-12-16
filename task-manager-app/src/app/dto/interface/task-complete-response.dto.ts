import { Errors } from "../common/errors";

/**
 * タスク完了レスポンスDTOクラス。
 */
export class TaskCompleteResponseDto {

    /**
     * タスクID
     */
    public taskId: string;

    /**
     * エラーコード
     */
    private errors: Errors[];

    public getTaskId(): string {
        return this.taskId;
    }
    public setTaskId(taskId: string): void {
        this.taskId = taskId;
    }
    public getErrors(): Errors[]|null|undefined {
        return this.errors;
    }
    public setErrors(errors: Errors[]) {
        this.errors = errors;
    }
    
}