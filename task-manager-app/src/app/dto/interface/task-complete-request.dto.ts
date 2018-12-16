/**
 * タスク完了リクエストDTOクラス。
 */
export class TaskCompleteRequestDto {

    /**
     * タスクID
     */
    private taskId: string;

    public getTaskId(): string {
        return this.taskId;
    }
    public setTaskId(taskId: string): void {
        this.taskId = taskId;
    }
}